package com.vsc.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.subject.WebSubject;
import org.jfree.util.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vsc.business.core.entity.security.User;
import com.vsc.business.core.entity.sys.SysLog;
import com.vsc.business.core.service.security.ShiroDbRealm.ShiroUser;
import com.vsc.business.core.service.sys.SysLogService;
/**
 * 系统日志工具类，用户输出系统日志
 * @author Administrator
 *
 */
public class SysLogUtils {
	private static Logger logger = LoggerFactory.getLogger(SysLogUtils.class);
	private static SysLogService sysLogService;
	private static String _SYS_LOGTYPE = "系统日志";
	private static String _SYS_SUBTYPE_INFO = "信息";
	private static String _SYS_SUBTYPE_WARNING = "警告";
	private static String _SYS_SUBTYPE_ERROR = "错误";
	
	/**
	 * 记录系统日志
	 * @param logType		模块名
	 * @param subtype		子模块名
	 * @param logContent	日志信息
	 * @throws Exception
	 */
	public static void logInfo(String logType,String subtype,String logContent){
		if(StringUtils.isEmpty(logType)){
			logType = _SYS_LOGTYPE;
		}
		if(StringUtils.isEmpty(subtype)){
			subtype = _SYS_SUBTYPE_INFO;
		}
		SysLogUtils.log(logType, subtype, logContent);
	}
	/**
	 * 写入系统提示信息
	 * @param logContent
	 * @throws Exception
	 */
	public static void logSysInfo(String logContent){
		SysLogUtils.log(_SYS_LOGTYPE, _SYS_SUBTYPE_INFO, logContent);
	}
	/**
	 * 写入系统警告信息
	 * @param logContent
	 * @throws Exception
	 */
	public static void logSysWarning(String logContent){
		SysLogUtils.log(_SYS_LOGTYPE, _SYS_SUBTYPE_WARNING, logContent);
	}
	/**
	 * 写入系统错误信息
	 * @param logContent
	 * @throws Exception
	 */
	public static void logSysError(String logContent){
		SysLogUtils.log(_SYS_LOGTYPE, _SYS_SUBTYPE_ERROR, logContent);
	}
	
	private static void log(String logType,String subtype,String logContent){
		try{
			checkInit();
			SysLog entity = new SysLog();
			entity.setCreateTime(CoreUtils.nowtime());
			Subject subject = SecurityUtils.getSubject();
			ShiroUser su = (ShiroUser)subject.getPrincipal();
			if(su != null){
				User u = new User();
				u.setId(su.id);
				entity.setUserName(su.getName());
				entity.setUser(u);
			}
			if(subject instanceof WebSubject){
				entity.setUserIp(CoreUtils.getIpAddr((javax.servlet.http.HttpServletRequest)((WebSubject)subject).getServletRequest()));
			}
			entity.setLogType(logType);
			entity.setSubtype(subtype);
			entity.setLogContent(logContent);
			sysLogService.save(entity);
		}catch(Exception e){
			Log.error("记录系统日志时出错", e);
		}
	}
	
	private static void checkInit()throws Exception{
		if(sysLogService == null){
			throw new Exception("还未初始化,");
		}
	}
	public static void setSysLogService(SysLogService sysLogService) {
		SysLogUtils.sysLogService = sysLogService;
	}
	public static void setSysLogType(String sysLogType) {
		SysLogUtils._SYS_LOGTYPE = sysLogType;
	}
	public static void setSysSubtypeInfo(String sysSubtypeInfo) {
		SysLogUtils._SYS_SUBTYPE_INFO = sysSubtypeInfo;
	}
	public static void setSysSubtypeWarning(String sysSubtypeWarning) {
		SysLogUtils._SYS_SUBTYPE_WARNING = sysSubtypeWarning;
	}
	public static void setSysSubtypeError(String sysSubtypeError) {
		SysLogUtils._SYS_SUBTYPE_ERROR = sysSubtypeError;
	}
}
