package com.vsc.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 所有站点全局配置键
 * @author 付慧君
 * @version 2012-6-20
 */
public class GlobalsKeys {
	//全局站点配置
	public static final String SITES_CONFIG = "sites_config";
	//全局路径配置
	public static final String FILE_PATH_CONFIG = "path_config";

	/**
	 * 站点前缀字符串 例如站点主键ID为1的站点 在map中的key为site_1
	 */
	public static final String SITE_PREFIX_CONFIG = "site_";
	/**
	 * 默认站点配置，在没有匹配的域名时使用
	 */
	public static final String SITE_DEFAULT_CONFIG = "site_default";

	/**
	 * 获取HttpServletRequest
	 * @return
	 */
	public static HttpServletRequest REQUEST() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}

	/**
	 * 获取context上下文路径
	 * @return
	 */
	public static String CONTEXT_PATH() {
		HttpServletRequest request = REQUEST();
		String contextpath = request.getScheme() + "://" + SERVER_NAME();
		return contextpath;
	}

	/**
	 * 获取域名
	 * @return
	 */
	public static String SERVER_NAME() {
		return REQUEST().getServerName();
	}

	// 注册成功，邮件激活URL
	public static final String REG_ACTIVATE_URL = "account/active.action";

	// 找回密码URL
	public static final String PWD_ACTIVATE_URL = "account/retrievepwd_active.action";
}
