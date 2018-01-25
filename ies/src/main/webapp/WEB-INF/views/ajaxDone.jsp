<%@page import="com.vsc.util.CoreUtils"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
{
	"statusCode":"${statusCode}", 	 
	<c:if test="${not empty param.navTabId}">
	"navTabId":"${param.navTabId}",
	</c:if>
	<c:if test="${not empty callbackData}">
	"callbackData":${callbackData},
	</c:if>	
	<c:if test="${not empty param.rel}">
	"rel":"${param.rel}",
	</c:if>
	<c:if test="${not empty param.callbackType}">
	"callbackType":"${param.callbackType}",
	</c:if>
	<c:if test="${not empty param.callbackFun}">
	"callbackFun":"${param.callbackFun}",
	</c:if>
	<c:if test="${not empty param.forwardUrl}">
	"forwardUrl":"${param.forwardUrl}",
	</c:if>
	"message":"${message}"
}

