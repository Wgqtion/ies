<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<vsc:pagerForm action="#rel#" id="pagerForm"></vsc:pagerForm>
<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/work/wxUser" method="post">
		<div class="searchBar">
			<ul class="searchContent">
				<li><label>姓名：</label> <input type="text" name="search_LIKE_name" value="${param.search_LIKE_name}" /></li>
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
			<li><a class="edit" title="编辑用户" rel="work_wxUser_update" href="${ctx}/work/wxUser/update/{sid}?navTabId=work_wxUser" target="dialog" warn="请选择一个会员"><span>编辑</span></a></li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="${ctx}/work/wxUser/delete" class="delete"><span>删除</span></a></li>
			<li class="line">line</li>
			 
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="40" align="center">序号</th>
				<th width="30"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th <vsc:orderField name="name"/>>姓名</th>
				<th>车牌号</th>
				<th>手机</th>
				<th width="140" align="center" <vsc:orderField name="createTime"/>>创建时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.content}" var="item" varStatus="index">
				<tr target="sid" rel="${item.id}">
					<td align="center">${index.count + (page.number * page.size)}</td>
					<td><input name="ids" value="${item.id}" type="checkbox"></td>
					<td>${item.name}</td>
					<td>${item.carNumber}</td>
					<td>${item.telephone}</td>
					<td><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd HH:mm" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<vsc:pagination page="${page}" numPerPageOnchange="navTabPageBreak({numPerPage:this.value})"></vsc:pagination>
	</div>
</div>
