<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<vsc:pagerForm action="#rel#" id="pagerForm"></vsc:pagerForm>

<div class="pageContent">
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="40" align="center">序号</th>
				<th <vsc:orderField name="plateNo"/>>车牌号</th>
				<th>停车场</th>
				<th <vsc:orderField name="inTime"/>>进场时间</th>
				<th>进口</th>
				<th <vsc:orderField name="outTime"/>>出场时间</th>
				<th>出口</th>
				<th <vsc:orderField name="payTime"/>>支付时间</th>
				<th>应收金额</th>
				<th>实收金额</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.content}" var="varitem" varStatus="varindex">
				<tr target="sid" rel="${varitem.id}">
					<td align="center">${varindex.count+(page.number * page.size)}</td>
					<td>${varitem.plateNo}</td>
					<td>${varitem.inPassages.parkinglot.name}</td>
					<td><fmt:formatDate value='${varitem.inTime}' pattern='yyyy-MM-dd HH:mm' /></td>
					<td>${varitem.inPassages.name}</td>
					<td><fmt:formatDate value='${varitem.outTime}' pattern='yyyy-MM-dd HH:mm' /></td>
					<td>${varitem.outPassages.name}</td>
					<td><fmt:formatDate value='${varitem.payTime}' pattern='yyyy-MM-dd HH:mm' /></td>
					<td>${varitem.ysPayAmount}</td>
					<td>${varitem.ssPayAmount}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
