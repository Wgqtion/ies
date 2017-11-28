<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<ul class="index_news_list">
	<c:forEach items="${page.content}" var="varitem" varStatus="varindex"> 
		<li><div class="links"><a href="${ctx}/work/systemnotice/view/${varitem.id}" target="dialog" title="查看系统通知">${varitem.title}:${varitem.context}</a></div> <div class="time"><fmt:formatDate value="${varitem.sendTime}" pattern="yyyy-MM-dd HH:mm"/></div></li>
	</c:forEach>
</ul>



