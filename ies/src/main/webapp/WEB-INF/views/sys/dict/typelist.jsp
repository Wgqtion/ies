<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<c:if test="${not empty typelist}">
[
<c:forEach items="${typelist}" var="type" varStatus="vstatus"> 
{
"id":"<c:out value="${type}"/>",
"type":"<c:out value="${type}"/>"
}
<c:if test="${!vstatus.last}">
,
</c:if>
</c:forEach>
]
</c:if>
