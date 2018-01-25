package com.vsc.business.gerd.web.httpservices;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.vsc.business.core.web.BaseController;

public abstract class HttpServiceBaseController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(HttpServiceBaseController.class);

	public static final String PATH_BASE = "httpservices";
	public static final String V_PATH_BASE = "httpservices";

	public static final String AUTHORIZATION_IDENTIFIER = "identifier";

	public static final String AUTHORIZATION_CREDENTIAL = "credential";

	//认证结果枚举
	public enum AuthenticationCode {
		//成功
		successful,
		//用户为空
		identifier_empty,
		//密码为空
		credential_empty,
		//ip为空
		ip_empty,
		//用户不存在
		identifier_error,
		//密码错误
		credential_error,
		//ip绑定错误
		ip_error
	}

	protected ModelAndView ajaxDone403(String message, String data) {
		return ajaxDone(403, message, data);
	}

	protected ModelAndView ajaxDone301(String message, String data) {
		return ajaxDone(301, message, data);
	}

	protected ModelAndView ajaxDone500(String message, String data) {
		return ajaxDone(500, message, data);
	}

	protected ModelAndView ajaxDone302(String message, String data) {
		return ajaxDone(302, message, data);
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	protected AuthenticationCode authentication(HttpServletRequest request) {
		//认证暂时去掉
		/*String identifier = request.getParameter(HttpServiceBaseController.AUTHORIZATION_IDENTIFIER);
		String credential = request.getParameter(HttpServiceBaseController.AUTHORIZATION_CREDENTIAL);
		String ip = CoreUtils.getIpAddr(request);*/

		return AuthenticationCode.successful;
	}

	@ExceptionHandler(Throwable.class)
	public ModelAndView handleIOException(Throwable ex, HttpServletRequest request) {
		logger.error("http接口调用出错", ex);
		return this.ajaxDone500(this.getMessage("httpservices.service_runerror"), Boolean.FALSE.toString());
	}
}
