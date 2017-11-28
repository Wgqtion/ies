<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<vsc:pagerForm action="#rel#" id="pagerForm"></vsc:pagerForm>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/work/cardtype" method="post">
		<div class="searchBar">
			<ul class="searchContent">
				<li><label>进校证名称:</label> <input type="text" value="${param.search_LIKE_name}" name="search_LIKE_name" /></li>
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
			<li><a class="add" title="添加进校证类型" href="${ctx}/work/cardtype/new?navTabId=work_cardtype" target="dialog" rel="work_cardtype_new"><span>添加</span></a></li>
			<li><a class="edit" title="编辑进校证类型" href="${ctx}/work/cardtype/update/{sid}?navTabId=work_cardtype" target="dialog" rel="work_cardtype_update" warn="请选择一个记录"><span>编辑</span></a></li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="${ctx}/work/cardtype/delete" class="delete"><span>删除</span></a></li>
			<li class="line">line</li>
			<li><a title="确实从进校证系统同步进校证类型数据吗?" target="ajaxTodo" href="${ctx}/work/cardtype/sync?navTabId=work_cardtype" class="reload"><span>同步</span></a></li>
			 
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="40" align="center">序号</th>
				<th width="30"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th <vsc:orderField name="cardType"/>>时效类型</th>
				<th <vsc:orderField name="name"/>>进校证名称</th>
				<th <vsc:orderField name="cardNumber"/>>首字母编号</th>
				<th <vsc:orderField name="mod"/>>人群</th>
				<th <vsc:orderField name="needRel"/>>关系证明</th>				 
				<th <vsc:orderField name="year"/>>适用年份</th>
				<th <vsc:orderField name="fee"/>>年费标准</th> 
				<th <vsc:orderField name="buban"/>>补办收费倍数</th>
				<th <vsc:orderField name="biangeng"/>>变更收费倍数</th> 
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.content}" var="varitem" varStatus="varindex">
				<tr target="sid" rel="${varitem.id}">
					<td align="center">${varindex.count+(page.number * page.size)}</td>
					<td><input name="ids" value="${varitem.id }" type="checkbox"></td>
					<td><a href="${ctx}/work/cardtype/view/${varitem.id}" target="dialog" title="查看进校证" rel="work_cardtype_view"><s:message code="cardtype.cardtype.${varitem.cardType}"/></a></td>
					<td><a href="${ctx}/work/cardtype/view/${varitem.id}" target="dialog" title="查看进校证" rel="work_cardtype_view">${varitem.name}</a></td>
					<td> ${varitem.cardNumber}</td>
					<td> ${varitem.cmod}</td>
					<td> <s:message code="cardtype.needRel.${varitem.needRel}"/></td> 
					<td> ${varitem.year}</td>
					<td> ${varitem.fee} </td> 
					<td> ${varitem.buban} </td>
					<td> ${varitem.biangeng}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<vsc:pagination page="${page}" numPerPageOnchange="navTabPageBreak({numPerPage:this.value})"></vsc:pagination>
	</div>
</div>
