
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<vsc:pagerForm action="#rel#" id="pagerForm"></vsc:pagerForm>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);"
		action="${ctx}/work/cardinfo" method="post">
		<div class="searchBar">
			<ul class="searchContent">
				<li><label>车牌号:</label> <input type="text"
					value="${param.search_LIKE_name}" name="search_LIKE_name" /></li>
				<li><label>车主姓名:</label> <input type="text"
					value="${param.search_LIKE_owner}" name="search_LIKE_owner" /></li>
				<li><label>有效期:</label><input type="text" class="date" size="7" value="${param.search_GTE_expireDate}" dateFmt="yyyy-MM-dd" name="search_GTE_expireDate"
					readonly="true" /> - <input type="text" class="date" size="7" value="${param.search_LTE_expireDate}" dateFmt="yyyy-MM-dd 23:59:59"  name="search_LTE_expireDate" readonly="true" /></li>		
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
			<li><a class="add" title="添加"
				href="${ctx}/work/cardinfo/new?navTabId=work_cardinfo"
				target="dialog" rel="cardinfo_new"><span>添加</span></a></li>
			<li><a class="edit" title="编辑"
				href="${ctx}/work/cardinfo/update/{sid}?navTabId=work_cardinfo"
				target="dialog" rel="cardinfo_update" warn="请选择一个记录"><span>编辑</span></a></li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids"
				href="${ctx}/work/cardinfo/delete" class="delete"><span>删除</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="40" align="center">序号</th>
				<th width="30"><input type="checkbox" group="ids"
					class="checkboxCtrl"></th>
				<th <vsc:orderField name="owner"/>>车主姓名</th>
				<th <vsc:orderField name="mobile"/>>电话</th>
				<th <vsc:orderField name="carNo"/>>车牌</th>
				<th <vsc:orderField name="userNo"/>>车辆编号</th>
				<th <vsc:orderField name="expireDate"/>>有效期</th> 
				<th <vsc:orderField name="carType"/>>车辆类型</th> 
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.content}" var="varitem" varStatus="varindex">
				<tr target="sid" rel="${varitem.id}">
					<td align="center">${varindex.count+(page.number * page.size)}</td>
					<td><input name="ids" value="${varitem.id }" type="checkbox"></td>
					<td>${varitem.owner}</td>
					<td>${varitem.mobile}</td>
					<td>${varitem.carNo}</td>
					<td>${varitem.userNo}</td>
					<td><fmt:formatDate value="${varitem.expireDate}" pattern="yyyy-MM-dd" /></td> 				 
					<td><s:message code="cardinfo.carType.${varitem.carType}"></s:message></td>					 
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<vsc:pagination page="${page}"
			numPerPageOnchange="navTabPageBreak({numPerPage:this.value})"></vsc:pagination>
	</div>
</div>
