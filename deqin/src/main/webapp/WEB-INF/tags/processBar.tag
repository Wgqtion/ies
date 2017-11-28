<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<%@ attribute name="value" type="java.lang.Object"%> 
<div class="process-bar">
    <div style="width: <fmt:formatNumber value="${value}"  type="percent" maxFractionDigits="2" minFractionDigits="0"></fmt:formatNumber>;">
        <span><fmt:formatNumber value="${value}" type="percent" maxFractionDigits="2" minFractionDigits="0"></fmt:formatNumber>
			</span>
    </div>
</div>
