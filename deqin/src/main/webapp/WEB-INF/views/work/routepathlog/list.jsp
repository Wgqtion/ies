<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<vsc:pagerForm action="#rel#" id="pagerForm"></vsc:pagerForm>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/work/routepathlog" method="post">
		<div class="searchBar">
			<ul class="searchContent">
				<li><label>服务类型:</label>  </li>
				<li><label>创建时间:</label> <input type="text" class="date" size="7" value="${param.search_GTE_createTime}" dateFmt="yyyy-MM-dd" name="search_GTE_createTime" readonly="true" />- <input type="text" class="date" size="7" value="${param.search_LTE_createTime}" dateFmt="yyyy-MM-dd 23:59:59" name="search_LTE_createTime" readonly="true" /></li>
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
		<!-- 
			<li><a class="add" title="添加" href="${ctx}/work/routepathlog/new?navTabId=work_routepathlog" target="dialog" rel="routepathlog_new"><span>添加</span></a></li>
			<li><a class="edit" title="编辑" href="${ctx}/work/routepathlog/update/{sid}?navTabId=work_routepathlog" target="dialog" rel="routepathlog_update" warn="请选择一个记录"><span>编辑</span></a></li>
			 --><li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="${ctx}/work/routepathlog/delete" class="delete"><span>删除</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="40" align="center">序号</th>
				<th width="30"><input type="checkbox" group="ids" class="checkboxCtrl"></th>				
				<th <vsc:orderField name="subtype"/>>服务类型</th>
				<th>申请参数</th>
				<th <vsc:orderField name="createTime"/>>创建时间</th>			 
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.content}" var="varitem" varStatus="varindex">
				<tr target="sid" rel="${varitem.id}">
					<td align="center">${varindex.count+(page.number * page.size)}</td>
					<td><input name="ids" value="${varitem.id }" type="checkbox"></td>
					<td> 
					<s:message code="routepathlog.subtype.${varitem.subtype}"></s:message>
					</td>
					<td>${varitem.logContent}</td>					 
					<td><fmt:formatDate value='${ varitem.createTime}' pattern='yyyy-MM-dd HH:mm' /></td>				
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<vsc:pagination page="${page}" numPerPageOnchange="navTabPageBreak({numPerPage:this.value})"></vsc:pagination>
	</div>
</div>
