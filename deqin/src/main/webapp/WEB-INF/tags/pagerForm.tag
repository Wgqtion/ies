<%@tag import="org.apache.commons.lang3.StringUtils"%>
<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<%@ attribute name="id" type="java.lang.String" required="true"%>
<%@ attribute name="action" type="java.lang.String" required="true"%>
<%@ attribute name="cacheName" type="java.lang.String" required="false"%>
<%@ attribute name="cacheId" type="java.lang.String" required="false"%>
<form id="${id}" method="post" action="${action}">
	<input type="hidden" name="pageNum" value="${param.pageNum}" />
	<input type="hidden" name="numPerPage" value="${param.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
    <vsc:pagepass cacheName="${cacheName}" cacheId="${cacheId}"></vsc:pagepass>
</form>

