<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<vsc:pagerForm action="#rel#" id="pagerForm"></vsc:pagerForm>
<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/work/smslog" method="post">
		<div class="searchBar">
			<ul class="searchContent">
				<li><label>姓名:</label> <input type="text" value="${param['search_LIKE_patient:name']}" name="search_LIKE_patient:name" /></li>
				<li><label>手机号:</label> <input type="text" value="${param['search_EQ_patient:mobile']}" name="search_EQ_patient:mobile" /></li>
			</ul>
			<div class="subBar">
				<ul>
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">检索</button>
							</div>
						</div></li> 
				</ul>
			</div>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			 
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="40" align="center">序号</th>
				<th width="30"> </th>
				<th>姓名</th>
				<th>手机号</th>
				<th <vsc:orderField name="sendTime"/>>发送时间</th>
				<th>发送内容</th>
				<th>发送结果</th>
				<th width="200"></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.content}" var="varitem" varStatus="varindex">
				<tr target="sid" rel="${varitem.id}">
					<td align="center">${varindex.count+(page.number * page.size)}</td>
					<td></td>
					<td>
					<a href="${ctx}/work/patient/view/${varitem.patient.id}" target="dialog" title="查看患者" rel="work_patient_view"> 
					 ${varitem.patient.name} </a></td>
					<td><a href="${ctx}/work/smslog/view/${varitem.id}" target="dialog" title="查看" rel="work_smslog_view">${varitem.patient.mobile}</a></td>
					<td><fmt:formatDate value='${varitem.sendTime}' pattern='yyyy-MM-dd HH:mm:ss' /></td>
					 
					<td>${varitem.content}</td>
					<td>${varitem.results}</td>
					<td></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<vsc:pagination page="${page}" numPerPageOnchange="navTabPageBreak({numPerPage:this.value})"></vsc:pagination>
	</div>
</div>
