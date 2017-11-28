<%@page import="com.vsc.util.CoreUtils"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" %>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<% 
if(CoreUtils.isMobileClientRequest(request)){
	String error = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
	if (error != null){
%>
		{
			"statusCode":"403",
			"message":"登录失败",
			"data":"false"
		}
<%}else{%>
		{
			"statusCode":"301",
			"message":"您没有登录或会话超时",
			"data":""
		}
<%		
	}
}else if(CoreUtils.isAjax(request)){
%> 
{
	"statusCode":"301",
	"message":"您的会话超时或没有该资源的访问授权",
	"navTabId":"",
	"callbackType":"",
	"forwardUrl":""
}

<%}else{
	//response.sendRedirect(request.getContextPath());
	out.print("您的会话超时或没有该资源的访问授权");
} %>

