<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<vsc:pagerForm action="#rel#" id="pagerForm"></vsc:pagerForm>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/work/parkingorder" method="post">
		<div class="searchBar">
			<ul class="searchContent">

				<li><label>车牌号:</label> <input type="text" value="${param.search_LIKE_carNo}" name="search_LIKE_carNo" /></li>
				<li><label>停车场:</label> <input type="text" value="${param['search_LIKE_inPassages.parkingLot.name']}" name="search_LIKE_inPassages.parkingLot.name" /></li>
				<li><label>收费员:</label> <input type="text" value="${param['search_LIKE_memberName']}" name="search_LIKE_memberName" /></li>
				<li><label></label> </li>
				<li><label>进场时间:</label><input type="text" class="date" size="7" value="${param.search_GTE_inTime}" dateFmt="yyyy-MM-dd" name="search_GTE_inTime" readonly="true" /> - <input type="text" class="date" size="7" value="${param.search_LTE_inTime}" dateFmt="yyyy-MM-dd 23:59:59" name="search_LTE_inTime" readonly="true" /></li>
				<li><label>出场时间:</label> <input type="text" class="date" size="7" value="${param.search_GTE_outTime}" dateFmt="yyyy-MM-dd" name="search_GTE_outTime" readonly="true" /> - <input type="text" class="date" size="7" value="${param.search_LTE_outTime}" dateFmt="yyyy-MM-dd 23:59:59" name="search_LTE_outTime" readonly="true" /></li>
				

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
				<th <vsc:orderField name="carNo"/>>车牌号</th>
				<th>停车场</th>
				<th <vsc:orderField name="inTime"/>>进场时间</th>
				<th>进口</th>
				<th <vsc:orderField name="outTime"/>>出场时间</th>
				<th>出口</th>
				<th <vsc:orderField name="payTime"/>>支付时间</th>
				<th <vsc:orderField name="memberName"/>>收费员</th>
				<th <vsc:orderField name="ysPayAmount"/>>应收金额</th>
				<th <vsc:orderField name="ssPayAmount"/>>实收金额</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.content}" var="varitem" varStatus="varindex">
				<tr target="sid" rel="${varitem.id}">
					<td align="center">${varindex.count+(page.number * page.size)}</td>
					<td>${varitem.carNo}</td>
					<td>${varitem.inPassages.parkingLot.name}</td>
					<td><fmt:formatDate value='${varitem.inTime}' pattern='yyyy-MM-dd HH:mm' /></td>
					<td>${varitem.inPassages.name}</td>
					<td><fmt:formatDate value='${varitem.outTime}' pattern='yyyy-MM-dd HH:mm' /></td>
					<td>${varitem.outPassages.name}</td>
					<td><fmt:formatDate value='${varitem.payTime}' pattern='yyyy-MM-dd HH:mm' /></td>
					<td>${varitem.memberName}</td>
					<td>${varitem.ysPayAmount}</td>
					<td>${varitem.ssPayAmount}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<vsc:pagination page="${page}" numPerPageOnchange="navTabPageBreak({numPerPage:this.value})"></vsc:pagination>
	</div>
</div>
