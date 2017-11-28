  <%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<vsc:pagerForm action="#rel#" id="pagerForm"></vsc:pagerForm>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/work/passages" method="post">
		<div class="searchBar">
			<ul class="searchContent">
				   
				<li><label>所属停车场:</label> 
					<input type="text" value="${param['search_LIKE_parkinglot.name']}" name="search_LIKE_parkinglot.name" /> </li>   
				<li><label>出入口名称:</label> 
					<input type="text" value="${param.search_LIKE_name}" name="search_LIKE_name" /> </li>   
				<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">检索</button>
							</div>
						</div></li>   
			</ul>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" title="添加" href="${ctx}/work/passages/new?navTabId=work_passages" target="dialog" rel="passages_new"><span>添加</span></a></li>
			<li><a class="edit" title="编辑" href="${ctx}/work/passages/update/{sid}?navTabId=work_passages" target="dialog" rel="passages_update"
				warn="请选择一个记录"><span>编辑</span></a></li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="${ctx}/work/passages/delete" class="delete"><span>删除</span></a></li>
			<li class="line">line</li>
			 	</ul>
	</div>
	<table class="table" width="100%" layoutH="110">
		<thead>
			<tr>
				<th width="40" align="center">序号</th>
				<th width="30"><input type="checkbox" group="ids" class="checkboxCtrl"></th>    
				<th>所属停车场</th>   
				<th <vsc:orderField name="name"/>>出入口名称</th>   
				<th <vsc:orderField name="xcoordinate"/>>X坐标</th>   
				<th <vsc:orderField name="ycoordinate"/>>Y坐标</th>   
				<th <vsc:orderField name="isEnabled"/>>状态</th>   
				<th <vsc:orderField name="mark"/>>备注</th>  
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.content}" var="varitem" varStatus="varindex">
				<tr target="sid" rel="${varitem.id}">
					<td align="center">${varindex.count+(page.number * page.size)}</td>
					<td><input name="ids" value="${varitem.id }" type="checkbox"></td>    
					<td> <a href="${ctx}/work/passages/view/${varitem.id}" target="dialog" title="查看"> ${varitem.parkinglot.name} </a> 
					</td>   
					<td>${varitem.name}</td>   
					<td>${varitem.xcoordinate}</td>   
					<td>${varitem.ycoordinate}</td>   
					<td><s:message code="passages.isenabled.${varitem.isEnabled}"/></td>   
					<td>${varitem.mark}</td>  
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<vsc:pagination page="${page}" numPerPageOnchange="navTabPageBreak({numPerPage:this.value})"></vsc:pagination>
	</div>
</div>
