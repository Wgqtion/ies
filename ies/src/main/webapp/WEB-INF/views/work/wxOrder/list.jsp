
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<vsc:pagerForm action="#rel#" id="pagerForm"></vsc:pagerForm>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/work/wxOrder" method="post">
		<div class="searchBar">
			<ul class="searchContent">
				<li><label>订单号：</label> <input type="text" name="search_LIKE_code" value="${param.search_LIKE_code}" /></li>
				<li><label>状态:</label> 
					<select class="combox" name="search_EQ_status" selectedValue="${param['search_EQ_status']}"  dataUrl="${ctx}/static/js/data/wxOrder_status.json">
						  <vsc:headoption></vsc:headoption>
					</select>
				</li>
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
				<th <vsc:orderField name="ipAddress"/>>订单号</th>
				<th <vsc:orderField name="weixinId"/>>用户</th>
				<th>总费用</th>
				<th <vsc:orderField name="status"/>>状态</th>
				<th <vsc:orderField name="createTime"/>>创建时间</th>
				<th <vsc:orderField name="payTime"/>>支付时间</th>
				<th>明细</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.content}" var="varitem" varStatus="varindex">
				<tr target="sid" rel="${varitem.id}">
					<td align="center">${varindex.count+(page.number * page.size)}</td>
					<td>${varitem.code} </td>
					<td>${varitem.wxUser.name} </a></td>
					<td>${varitem.totalFee}</td>
					<td><s:message code="wxOrder.status.${varitem.status}"/></td>
					<td><fmt:formatDate value='${ varitem.createTime}' pattern='yyyy-MM-dd HH:mm:ss' /></td>
					<td><fmt:formatDate value='${ varitem.payTime}' pattern='yyyy-MM-dd HH:mm:ss' /></td>
					<td><a title="查询明细" target="dialog" rel="wxOrder_view" href="${ctx}/work/wxOrder/view/${varitem.id}" class="btnInfo">查看</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<vsc:pagination page="${page}" numPerPageOnchange="navTabPageBreak({numPerPage:this.value})"></vsc:pagination>
	</div>
</div>
