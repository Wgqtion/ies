<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<ul class="index_news_list">
	<c:forEach items="${page.content}" var="varitem" varStatus="varindex"> 
		<li><div class="links"><a href="${ctx}/work/ann/send/view/${varitem.id}" target="dialog" title="查看公告">${varitem.announcement.title}</a></div> <div class="time"> <fmt:formatDate value="${varitem.sendDate}" pattern="yyyy-MM-dd"/></div></li>
	</c:forEach>
</ul>



