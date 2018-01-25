<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" import="com.vsc.util.TokenHelper"%>
<%@ attribute name="tokenName" type="java.lang.String" required="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${action=='create'}">
<%
	request.setAttribute("tokenName", tokenName);
	String tokenValue = TokenHelper.setToken(request, tokenName);
	request.setAttribute("tokenValue", tokenValue);
%>
<input type="hidden" value="${tokenName}" name="<%=TokenHelper.TOKEN_NAME_FIELD %>">
<input type="hidden" value="${tokenValue}" name="${tokenName}">
</c:if> 