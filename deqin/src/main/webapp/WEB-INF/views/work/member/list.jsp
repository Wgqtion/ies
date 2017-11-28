<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<vsc:pagerForm action="#rel#" id="pagerForm"></vsc:pagerForm>
<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/work/member" method="post">
		<div class="searchBar">
			<ul class="searchContent">
				<li><label>姓名：</label> <input type="text" name="search_LIKE_name" value="${param.search_LIKE_name}" /></li>
				<li><label>登录名：</label> <input type="text" name="search_LIKE_loginName" value="${param.search_LIKE_loginName}" /></li>
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
			<li><a class="add" title="添加会员" rel="work_member_new" href="${ctx}/work/member/new?navTabId=work_member" target="dialog"><span>添加</span></a></li>
			<li><a class="edit" title="编辑会员" rel="work_member_update" href="${ctx}/work/member/update/{sid}?navTabId=work_member" target="dialog" warn="请选择一个会员"><span>编辑</span></a></li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="${ctx}/work/member/delete" class="delete"><span>删除</span></a></li>
			<li class="line">line</li>
			 
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="40" align="center">序号</th>
				<th width="30"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th <vsc:orderField name="loginName"/>>登录名</th>
				<th <vsc:orderField name="name"/>>姓名</th>
				<th>手机</th>
				<th>性别</th>
				<th>来源</th>				
				<th <vsc:orderField name="isEnabled"/>>使用状态</th>				
				<th width="140" align="center" <vsc:orderField name="createTime"/>>创建时间</th>
				<th width="120">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.content}" var="item" varStatus="index">
				<tr target="sid" rel="${item.id}">
					<td align="center">${index.count + (page.number * page.size)}</td>
					<td><input name="ids" value="${item.id}" type="checkbox"></td>
					<td><a href="${ctx}/work/member/view/${item.id}" target="dialog" title="查看会员" rel="work_member_view">${item.loginName}</a></td>
					<td>${item.name}</td>
					<td>${item.mobilePhone}</td>
					<td><s:message code="user.gender.${item.gender}"></s:message></td>
					<td>${item.sourceType}</td>
					<td><s:message code="user.isenabled.${item.isEnabled}"></s:message></td>					
					<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm" /></td>
					<td><a title="重置密码" rel="work_member_reset" target="dialog" href="${ctx}/work/member/reset/${item.id}" class="btnEdit">重置密码</a> </td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<vsc:pagination page="${page}" numPerPageOnchange="navTabPageBreak({numPerPage:this.value})"></vsc:pagination>
	</div>
</div>
