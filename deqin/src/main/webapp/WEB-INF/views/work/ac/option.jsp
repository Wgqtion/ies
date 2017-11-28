<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>  
<c:if test="${not empty vl}">[
<c:forEach items="${vl}" var="vm" varStatus="vi">
    ["<c:out value="${vm['bankType']}" />", "<c:out value="${vm['bankType']}" />"]<c:if test="${!vi.last}">,</c:if>  
</c:forEach> 
]
</c:if>   
 