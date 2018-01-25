<%@tag import="org.apache.commons.lang3.StringUtils"%>
<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<%@ attribute name="page" type="org.springframework.data.domain.Page" required="true"%>
<%@ attribute name="targetType" type="java.lang.String" required="false"%>
<%@ attribute name="rel" type="java.lang.String" required="false"%>
<%@ attribute name="numPerPageOnchange" type="java.lang.String" required="true"%>
<c:if test="${empty targetType}">
<c:set value="navTab" var="targetType"></c:set>
</c:if>
<div class="pages">
	<span>显示</span> <select class="combox" name="numPerPage" onchange="${numPerPageOnchange}" selectedValue="${param.numPerPage}<c:if test="${empty param.numPerPage}"><%=com.vsc.business.core.web.BaseController.DEFAUTL_PAGE_SIZE%></c:if>" 
		dataUrl="${ctx}/static/js/data/page_option.js">
	</select> <span>条，共 ${page.totalPages}页，合计 ${page.totalElements}条</span>
</div>
<div <c:if test="${empty rel}">rel="${rel}"</c:if> class="pagination" targetType="${targetType}" totalCount="${page.totalElements}" numPerPage="${page.size}" currentPage="${page.number+1}"></div>


