package com.vsc.business.core.web.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.vsc.business.core.web.BaseController;
import com.vsc.util.CoreUtils;

/**
 * LoginController负责打开登录页面(GET请求)和登录出错页面(POST请求)，

 * 真正登录的POST请求由Filter完成,
 * 
 * @author calvin
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController extends BaseController {
	
	
	public static final String V_PATH_LOGIN =  "account/login";
	public static final String V_PATH_AJAX_LOGIN =  "account/unauthorized";
	public static final String V_PATH_AJAX_DIALOG =  "account/logindialog";

	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String login(HttpServletRequest request) {
		//ajax请求返回无授权json
		if (CoreUtils.isMobileClientRequest(request)) {
			return "account/unauthorized";
		}
		if (CoreUtils.isAjax(request)) {
			return "account/unauthorized";
		}
		return "account/login";
	}

	@RequestMapping(value = "")
	public String dologin(@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String username,
			@RequestParam String password, @RequestParam(required = false, defaultValue = "false") boolean rememberMe,
			Model model, HttpServletRequest request, HttpServletResponse response) {
		String host = request.getRemoteHost();
		AuthenticationToken token = new UsernamePasswordToken(username, password, rememberMe, host);
		boolean flag = executeLogin(token);
		if (!flag) {
			//ajax请求返回无授权json
			if (CoreUtils.isAjax(request)) {
				return V_PATH_AJAX_LOGIN;
			}
			model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, username);
			model.addAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME, "登录失败，请重试.");
			return V_PATH_LOGIN;
		}
		return "redirect:/index";
	}

	@RequestMapping(value = "dialog", method = RequestMethod.GET)
	public String dialog() {
		return "account/logindialog";
	}

	@RequestMapping(value = "ajax", method = RequestMethod.POST)
	public ModelAndView ajaxlogin(@RequestParam String username, @RequestParam String password,
			@RequestParam(required = false, defaultValue = "false") boolean rememberMe, HttpServletRequest request,
			HttpServletResponse response) {
		String host = request.getRemoteHost();
		AuthenticationToken token = new UsernamePasswordToken(username, password, rememberMe, host);
		try {
			Subject subject = SecurityUtils.getSubject();
			subject.login(token);
			return this.ajaxDoneSuccess("登录成功");

		} catch (AuthenticationException e) {
			//登陆失败
			return this.ajaxDoneError("登录失败");
		}

	}

	@RequestMapping(value = "unauthorized", method = RequestMethod.GET)
	public String unauthorized() {
		return "account/unauthorized";
	}

	private boolean executeLogin(AuthenticationToken token) {
		try {
			Subject subject = SecurityUtils.getSubject();
			subject.login(token);
			return true;
		} catch (AuthenticationException e) {
			//登陆失败
			return false;
		}
	}
	
}
