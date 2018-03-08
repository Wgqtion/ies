<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<vsc:pagerForm action="#rel#" id="pagerForm" cacheName="uids"></vsc:pagerForm>
<div class="pageHeader">
	<form rel="pagerForm" method="post" action="${ctx}/sys/user/select" onsubmit="return dwzSearch(this, 'dialog');">
		<div class="searchBar">
			<ul class="searchContent">
				<li><label>姓名：</label> <input class="textInput" name="search_LIKE_name" value="${param.search_LIKE_name}" type="text"></li>
				<li><label>登录名：</label> <input class="textInput" name="search_LIKE_loginName" value="${param.search_LIKE_loginName}" type="text"></li>
			</ul>
			<div class="subBar">
				<ul>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">查询</button>
							</div>
						</div>
					</li>
					<c:if test="${empty param.single}">
						<li>
							<div class="button">
								<div class="buttonContent">
									<button type="button" multLookup="uids" warn="请选择人员">选择</button>
								</div>
							</div>
						</li>
					</c:if>
					<li>
						<div class="button">
							<div class="buttonContent">
								<button type="button" onclick="javascript:$.bringBack({id:'', loginName:'',name:''})">清空</button>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</form>
</div>
<div class="pageContent">
	<table class="table" layoutH="108" targetType="dialog" width="100%">
		<thead>
			<tr>
				<th width="30"><c:if test="${empty param.single}">
						<input type="checkbox" class="checkboxCtrl" cacheElementId="pagerForm" cacheName="uids" group="ids" />
					</c:if></th>				 
				<th <vsc:orderField name="loginName"/>>登录名</th>
				<th <vsc:orderField name="name"/>>姓名</th>
				 
				<th <vsc:orderField name="isEnabled"/>>使用状态</th>
				<th <vsc:orderField name="createUser"/>>创建人</th>
				<th width="140" align="center" <vsc:orderField name="createDate"/>>创建时间</th>
				 
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.content}" var="item" varStatus="index">
				<tr>
					<td><c:if test="${empty param.single}">
							<input <vsc:defaultIfIndexOfParameterValues cacheName="uids" objectToFind="{id:'${item.id}', loginName:'${item.loginName}',name:'${item.name}'}"  yesStr="checked=\"checked\""/> onclick="javascript:pagePass(this,'pagerForm','uids')" type="checkbox" name="ids" value="{id:'${item.id}', loginName:'${item.loginName}',name:'${item.name}'}" />							 
						</c:if> <c:if test="${not empty param.single}">
							<a title="查找带回" href="javascript:$.bringBack({id:'${item.id}', loginName:'${item.loginName}',name:'${item.name}'})" class="btnSelect">选择</a>
						</c:if></td>
					<td><a href="${ctx}/sys/user/view/${item.id}" target="dialog" title="查看用户" rel="sys_user_view"> ${item.loginName} </a></td>
					<td>${item.name}</td>
					<td><s:message code="user.isenabled.${item.isEnabled}"></s:message></td>
					<td>${item.createUser.name}</td>
					<td><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd HH:mm" /></td>					 
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<vsc:pagination page="${page}" targetType="dialog" numPerPageOnchange="dwzPageBreak({targetType:'dialog', data:{numPerPage:this.value}})"></vsc:pagination>
	</div>
</div>
