package com.vsc.modules.aspect;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.subject.WebSubject;
import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import com.google.common.collect.Maps;
import com.google.common.io.Files;
import com.google.common.io.InputSupplier;
import com.vsc.business.core.service.security.ShiroDbRealm.ShiroUser;
import com.vsc.util.SysLogUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 写入系统操作日志切面
 * @author jerry.fu
 *
 */
public class SystemOperationLog {
	private static Logger logger = LoggerFactory.getLogger(SystemOperationLog.class);
	private static ThreadLocal<String> contextLocal = new ThreadLocal<String>();
	private static String charsetName = "UTF-8";
	private static String _LOGTYPE_SUFFIX = ".LOGTYPE";
	private static String _SUBTYPE_SUFFIX = ".SUBTYPE";
	private static String _LOGCONTENT_SUFFIX = ".LOGCONTENT";
	// 日志配置文件
	private static Properties properties = new Properties();
	//存放处理后的配置信息
	private static Map<String,CfgInfo> _METHOD_CFG_MAP = Maps.newTreeMap();
	// 读取日志配置文件
	static {
		try {
			File in = ResourceUtils.getFile("classpath:system_function_log.properties");
			properties.load(new InputStreamReader(new FileInputStream(in),charsetName));
		} catch (FileNotFoundException e) {
			logger.error("没有找到日志配置文件，请确认你的system_function_log.properties 文件路径是否正确。");
		} catch (IOException e) {
			logger.error("日志配置文件读写错误");
		}
	}
	public void beforeDo(JoinPoint joinPoint) {
		try{
			CfgInfo cfginfo = getCfgInfo(joinPoint);
			if(cfginfo != null && cfginfo.valid()){
				String contentStr = dealContent(cfginfo.logContent,joinPoint);
				contextLocal.set(contentStr);
				SysLogUtils.logInfo(cfginfo.logType, cfginfo.subtype, contentStr);
			}
		}catch(Exception e){
			logger.error("记录系统日志出错",e);
		}
	}
	public void afterDo(JoinPoint joinPoint){
		
	}
	public void throwDo(JoinPoint joinPoint,Exception e){
		try{
			CfgInfo cfginfo = getCfgInfo(joinPoint);
			if(cfginfo != null && cfginfo.valid()){
				SysLogUtils.logInfo(cfginfo.logType, cfginfo.subtype, "操作异常:"+e.getMessage() + ".\n" + contextLocal.get());
			}
		}catch(Exception e1){
			logger.error("记录系统日志出错",e1);
		}
	}
	/**
	 * 获取配置信息
	 * @param joinPoint
	 * @return
	 */
	private CfgInfo getCfgInfo(JoinPoint joinPoint){
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		String key = className + "." + methodName;
		CfgInfo info = null;
		if(_METHOD_CFG_MAP.containsKey(key)){
			info = _METHOD_CFG_MAP.get(key);
		}else{
			String str = getCfgValue(key,"",true);
			//有配置，需要记录日志
			if(StringUtils.isNotBlank(str)){
				info = new CfgInfo();
				//获取logType
				info.logType = getCfgValue(key,_LOGTYPE_SUFFIX,true);
				//获取subtype
				info.subtype = getCfgValue(key,_SUBTYPE_SUFFIX,true);
				//获取logContent
				info.logContent = getCfgValue(key,_LOGCONTENT_SUFFIX,false);
			}
			_METHOD_CFG_MAP.put(key, info);
		}
		return info;
	}
	/**
	 * 从配置信息中获取指定类方法的对应信息。
	 * 当没有配置时，会从类目录逐级查找
	 * @param key	className.methodName
	 * @param suffix	指定获取 logType、subtype 或 logContent
	 * @param loop 是否逐级查找 <br/>
	 * com.vsc.demo.web.sys.LoginLogController.list <br/>
	 * com.vsc.demo.web.sys.LoginLogController <br/>
	 * com.vsc.demo.web.sys
	 * @return
	 */
	private String getCfgValue(String key,String suffix,boolean loop){
		String v = "";
		int i;
		do{
			v = properties.getProperty(key+suffix);
			if(v != null){
				return v.trim();
			}else if(loop){
				i = key.lastIndexOf(".");
				key = key.substring(0,i != -1 ?i:0);
			}else{
				return "";	
			}
		}while(StringUtils.isNotBlank(key));
		return null;
	}
	
	private String dealContent(String templateStr,JoinPoint joinPoint)throws Exception{
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		String key = className + "." + methodName;
		Object[] obs = joinPoint.getArgs();
		Map<String,Object> param = new HashMap<String,Object>();
		for(int i=0;i<obs.length;i++){
			param.put("p"+(i+1), obs[i]);
		}
		Subject subject = SecurityUtils.getSubject();
		if(subject instanceof WebSubject){
			HttpServletRequest request = (HttpServletRequest)((WebSubject)subject).getServletRequest();
			param.put("request", request);
			param.put("param", request.getParameterMap());
		}
		ShiroUser su = (ShiroUser)subject.getPrincipal();
		if(su != null){
			param.put("u", su);
		}
		return FreeMarkerTemplateUtil.process(key, templateStr, param);
	}
	
	public void setCharsetName(String charsetName) {
		SystemOperationLog.charsetName = charsetName;
	}
	public void setLogTypeSuffix(String logTypeSuffix) {
		SystemOperationLog._LOGTYPE_SUFFIX = logTypeSuffix;
	}
	public void setSubtypeSuffix(String subtypeSuffix) {
		SystemOperationLog._SUBTYPE_SUFFIX = subtypeSuffix;
	}
	public void setLogContentSuffix(String logContentSuffix) {
		SystemOperationLog._LOGCONTENT_SUFFIX = logContentSuffix;
	}

	class CfgInfo{
		protected String logType;
		protected String subtype;
		protected String logContent;
		
		protected boolean valid(){
			return !StringUtils.isBlank(logContent);
		}
	}
}
/**
 * FreeMarker处理工具类
 * template缓存
 * @author Administrator
 *
 */
class FreeMarkerTemplateUtil{
	private static Configuration cfg = new Configuration();
	public static boolean isCache = true;
	private static Map<String,Template> templateCatch = Maps.newTreeMap();
	/**
	 * 根据提供模版和参数，进行处理
	 * @param templateKey		模版KEY，先从缓存中获取Template，如果没有会生成一个并且放入缓存
	 * @param templateStr		模版字符串
	 * @param param				参数
	 * @return
	 */
	public static String process(String templateKey,String templateStr,Map param)throws Exception{
		Template template = null;
		if(StringUtils.isNotBlank(templateKey)){
			if(isCache){
				template = templateCatch.get(templateKey);
				if(template == null){
					template = new Template(templateKey,new StringReader(templateStr),cfg);
					templateCatch.put(templateKey, template);
				}
			}
		}
		if(template == null){
			template = new Template(templateKey,new StringReader(templateStr),cfg);
		}
		StringWriter sw = new StringWriter();
		template.process(param, sw);
		sw.flush();
		return sw.toString();
	}
}
