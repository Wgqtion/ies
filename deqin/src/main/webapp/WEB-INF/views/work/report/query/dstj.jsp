<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<c:forEach items="${vl}"  var="item" >
<li style="line-height: 36px; font-weight: bold; color: #a2a2a2; font-size: 18px;">地锁总数${item['total']}个</li>
<li style="line-height: 36px; font-weight: bold; color: #a2a2a2; font-size: 18px;">地锁正常${item['tonline']}个</li>
<li style="line-height: 36px; font-weight: bold; color: #a2a2a2; font-size: 18px;">地锁异常${item['total']-item['tonline']}个</li>
<li style="line-height: 36px; font-weight: bold; color: #a2a2a2; font-size: 18px;">地锁已用${item['total']-item['topen']}个</li>
<li style="line-height: 36px; font-weight: bold; color: #a2a2a2; font-size: 18px;">地锁可用${item['topen']}个</li>
</c:forEach>