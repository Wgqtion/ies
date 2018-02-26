<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<vsc:pagerForm action="#rel#" id="pagerForm"></vsc:pagerForm>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/work/yuding" method="post">
		<div class="searchBar">
			<ul class="searchContent">

				<li><label>会员名:</label> <input type="text" value="${param['search_LIKE_user.name']}" name="search_LIKE_user.name" /></li>
				<li><label>车牌号:</label> <input type="text" value="${param.search_LIKE_carNo}" name="search_LIKE_carNo" /></li>
				<li><label>申请时间:</label> <input type="text" class="date" size="9" value="${param.search_GTE_createTime}" dateFmt="yyyy-MM-dd" name="search_GTE_createTime" readonly="true" />- <input type="text" class="date" size="9"
					value="${param.search_LTE_createTime}" dateFmt="yyyy-MM-dd 23:59:59" name="search_LTE_createTime" readonly="true" /></li>

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
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="${ctx}/work/yuding/delete" class="delete"><span>删除</span></a></li>
			<li class="line">line</li>
			<li><a class="edit" title="预约设置" href="${ctx}/work/yudingsetting/update?navTabId=work_yuding" target="dialog" rel="yuding_setting_update"><span>预约设置</span></a></li>
			
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="40" align="center">序号</th>
				<th width="30"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th>会员名</th>
				<th>车牌号</th>	
				<th>预约停车区</th>			
				<th <vsc:orderField name="yuyueTime"/>>预约时间</th>				 
				<th <vsc:orderField name="createTime"/>>申请时间</th> 				 
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.content}" var="varitem" varStatus="varindex">
				<tr target="sid" rel="${varitem.id}">
					<td align="center">${varindex.count+(page.number * page.size)}</td>
					<td><input name="ids" value="${varitem.id }" type="checkbox"></td>
					<td>${varitem.user.name}</td>
					<td><a href="${ctx}/work/yuding/view/${varitem.id}" target="dialog" title="查看预约" rel="yuding_view">${varitem.carNo}</a></td>
					<td>${varitem.parkingLotArea.fullIndexName}</td>
					<td><fmt:formatDate value='${varitem.yuyueTime}' pattern='yyyy-MM-dd HH:mm' /></td>
					<td><fmt:formatDate value='${ varitem.createTime}' pattern='yyyy-MM-dd HH:mm' /></td>
					 
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<vsc:pagination page="${page}" numPerPageOnchange="navTabPageBreak({numPerPage:this.value})"></vsc:pagination>
	</div>
</div>
