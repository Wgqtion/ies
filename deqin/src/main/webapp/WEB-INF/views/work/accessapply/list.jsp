  <%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<vsc:pagerForm action="#rel#" id="pagerForm"></vsc:pagerForm>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/work/accessapply" method="post">
		<div class="searchBar">
			<ul class="searchContent">
				<li><label>车牌号:</label> <input type="text" value="${param['search_LIKE_cardInfo.carNo']}" name="search_LIKE_cardInfo.carNo" /></li>
				<li><label>车辆编号:</label> <input type="text" value="${param['search_LIKE_cardInfo.userNo']}" name="search_LIKE_cardInfo.userNo" /></li>
				<li><label>停车场:</label> <input type="text" value="${param['search_LIKE_passages.parkinglot.name']}" name="search_LIKE_passages.parkinglot.name" /></li>
				<li><label>申请时间:</label><input type="text" class="date" size="7" value="${param.search_GTE_applyTime}" dateFmt="yyyy-MM-dd" name="search_GTE_applyTime" readonly="true" /> - <input type="text" class="date" size="7" value="${param.search_LTE_applyTime}" dateFmt="yyyy-MM-dd 23:59:59" name="search_LTE_applyTime" readonly="true" /></li>


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
			<li><a class="add" title="添加" href="${ctx}/work/accessapply/new?navTabId=work_accessapply" target="dialog" rel="accessapply_new"><span>添加</span></a></li>
			<li><a class="edit" title="编辑" href="${ctx}/work/accessapply/update/{sid}?navTabId=work_accessapply" target="dialog" rel="accessapply_update"
				warn="请选择一个记录"><span>编辑</span></a></li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="${ctx}/work/accessapply/delete" class="delete"><span>删除</span></a></li>
			<li class="line">line</li>
	    </ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="40" align="center">序号</th>
				<th width="30"><input type="checkbox" group="ids" class="checkboxCtrl"></th>    
				<th>车辆编号</th>   
				<th>车牌号</th>   
				<th <vsc:orderField name="accessStatus"/>>出入状态</th>   
				<th <vsc:orderField name="applyTime"/>>申请时间</th>   
				<th>申请人</th>   
				<th >出入口</th>  
				<th >停车场</th>  
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.content}" var="varitem" varStatus="varindex">
				<tr target="sid" rel="${varitem.id}">
					<td align="center">${varindex.count+(page.number * page.size)}</td>
					<td><input name="ids" value="${varitem.id }" type="checkbox"></td>    
					<td>${varitem.cardInfo.userNo}</td>   
					<td>${varitem.cardInfo.carNo}</td>   
					<td><s:message code="accessapply.accessStatus.${varitem.accessStatus}" ></s:message> </td>   
					<td><fmt:formatDate value="${varitem.applyTime}" pattern="yyyy-MM-dd HH:mm" /></td> 
					<td>${varitem.user.name}</td>   
					<td>${varitem.passages.name}</td>   
					<td>${varitem.passages.parkinglot.name}</td>   
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<vsc:pagination page="${page}" numPerPageOnchange="navTabPageBreak({numPerPage:this.value})"></vsc:pagination>
	</div>
</div>
