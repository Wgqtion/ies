<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<vsc:pagerForm action="#rel#" id="pagerForm"></vsc:pagerForm>
<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/sys/loginlog" method="post">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>时间段：</label>
				<input type="text" size="10" name="search_GT_createTime" class="date" value="${param.search_GT_createTime}"/>
				至
				<input type="text" size="10" name="search_LT_createTime" class="date" value="${param.search_LT_createTime}"/>
			</li>
			<li>
				<label>用户名：</label>
				<input type="text" name="search_EQ_userName" value="${param.search_EQ_userName}"/>
			</li>
			<li>
				<label>姓名：</label>
				<input type="text" name="search_LIKE_user:name" value="${param["search_LIKE_user:name"]}"/>
			</li>
		</ul> 
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="${ctx}/sys/loginlog/delete" class="delete"><span>批量删除</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
			    <th width="50" align="center">序号</th>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="150" align="center"  <vsc:orderField name="createTime"/>>登录时间</th>
				<th width="120" >用户名</th>
				<th width="120" >姓名</th>
				<th width="120" align="center">登录IP</th>
				<th width="" >类型</th>
				<th width="80">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.content}" var="item" varStatus="index">
				<tr target="sid" rel="${item.id}">
					<td align="center">${index.count}</td>
					<td><input name="ids" value="${item.id}" type="checkbox"></td>
					<td>${item.createTime}</td>
					<td>${item.userName}</td>
					<td>${item.user.name}</td>
					<td align="center">${item.userIp}</td>
					<td>${item.logContent}</td>
					<td>
						<a title="删除" target="ajaxTodo" href="${ctx}/sys/loginlog/delete/${item.id}" class="btnDel">删除</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<vsc:pagination page="${page}" numPerPageOnchange="navTabPageBreak({numPerPage:this.value})"></vsc:pagination></div>
</div>