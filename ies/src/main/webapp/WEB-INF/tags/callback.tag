<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<%@ attribute name="navTabId" type="java.lang.String"%>
<%@ attribute name="rel" type="java.lang.String"%>
<%@ attribute name="callbackType" type="java.lang.String"%>
<%@ attribute name="forwardUrl" type="java.lang.String"%>
<%@ attribute name="callbackFun" type="java.lang.String"%>
<c:choose>
	<c:when test="${not empty navTabId}">
		<input type="hidden" value="${navTabId}" name="navTabId">
	</c:when>
	<c:when test="${not empty param.navTabId}">
		<input type="hidden" value="${param.navTabId}" name="navTabId">
	</c:when>
</c:choose>
<c:choose>
	<c:when test="${not empty rel}">
		<input type="hidden" value="${rel}" name="rel">
	</c:when>
	<c:when test="${not empty param.rel}">
		<input type="hidden" value="${param.rel}" name="rel">
	</c:when>
</c:choose>
<c:choose>
	<c:when test="${not empty callbackType}">
		<input type="hidden" value="${callbackType}" name="callbackType">
	</c:when>
	<c:when test="${not empty param.callbackType}">
		<input type="hidden" value="${param.callbackType}" name="callbackType">
	</c:when>
	<c:otherwise>
		<input type="hidden" value="closeCurrent" name="callbackType">
	</c:otherwise>
</c:choose>
<c:choose>
	<c:when test="${not empty forwardUrl}">
		<input type="hidden" value="${forwardUrl}" name="forwardUrl">
	</c:when>
	<c:when test="${not empty param.forwardUrl}">
		<input type="hidden" value="${param.forwardUrl}" name="forwardUrl">
	</c:when>
</c:choose>

<c:choose>
	<c:when test="${not empty callbackFun}">
		<input type="hidden" value="${callbackFun}" name="callbackFun">
	</c:when>
	<c:when test="${not empty param.callbackFun}">
		<input type="hidden" value="${param.callbackFun}" name="callbackFun">
	</c:when>
</c:choose>
