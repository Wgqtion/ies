package com.vsc.modules.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.vsc.util.TokenHelper;

/**
 * 该拦截器实现 表单 Token的验证，防止重复提交表单
 *
 * @author 付翀
 * @since 2012.12-21
 * @see com.vsc.modules.servlet.TokenSessionStoreInterceptor
 */
public class TokenSessionStoreInterceptor extends HandlerInterceptorAdapter {
	
	private   String invalidTokenView = "/WEB-INF/views/error/invalid/tokenerror.jsp";
	
	
	public String getInvalidTokenView() {
		return invalidTokenView;
	}


	public void setInvalidTokenView(String invalidTokenView) {
		this.invalidTokenView = invalidTokenView;
	}


	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		HttpSession session = request.getSession(true);

		synchronized (session) {
			if (!TokenHelper.validToken(request)) {
				request.getRequestDispatcher(invalidTokenView).forward(request, response);
				return false;
			}

			//return handleValidToken(invocation);
		}

		return true;
	}
}
