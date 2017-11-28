<#include "/macro.include"/> <#include "/custom.include"/> <#assign className = table.className> <#assign classNameLower = className?uncap_first> <#assign classNameFullLower =
className?lower_case> <#assign navTabId = funcname?substring(funcname?last_index_of("/")+1)+"_"+classNameFullLower>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<vsc:pagerForm action="#rel#" id="pagerForm"></vsc:pagerForm>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="<@jspEl 'ctx'/>/${funcname}/${classNameFullLower}" method="post">
		<div class="searchBar">
			<ul class="searchContent">
				<#list table.notPkColumns?chunk(4) as row> <#list row as column> <#if !column.htmlHidden> <#if column_index==2><#break></#if>
				<li><label>${column.constantName}:</label> <#if column.isDateTimeColumn> <input type="text" class="date" size="9"
					value="<@jspEl 'param.search_GTE_'+column.columnNameLower/>" dateFmt="yyyy-MM-dd" name="search_GTE_${column.columnNameLower}" readonly="true" />- <input type="text"
					class="date" size="9" value="<@jspEl 'param.search_LTE_'+column.columnNameLower/>" dateFmt="yyyy-MM-dd" name="search_LTE_${column.columnNameLower}" readonly="true" /> <#else>
					<input type="text" value="<@jspEl 'param.search_LIKE_'+column.columnNameLower/>" name="search_LIKE_${column.columnNameLower}" /> </#if></li> </#if></#list></#list>
			</ul>
			<div class="subBar">
				<ul>
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">检索</button>
							</div>
						</div></li>
					<li><a rel="${funcname}_${classNameFullLower}_search" class="button" href="<@jspEl 'ctx'/>/${funcname}/${classNameFullLower}/search" target="dialog" minable="false"
						fresh="false" title="高级检索"><span>高级检索</span></a></li>
				</ul>
			</div>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" title="添加" href="<@jspEl 'ctx'/>/${funcname}/${classNameFullLower}/new?navTabId=${navTabId}" target="dialog" rel="${classNameFullLower}_new"><span>添加</span></a></li>
			<li><a class="edit" title="编辑" href="<@jspEl 'ctx'/>/${funcname}/${classNameFullLower}/update/{sid}?navTabId=${navTabId}" target="dialog" rel="${classNameFullLower}_update"
				warn="请选择一个记录"><span>编辑</span></a></li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="<@jspEl 'ctx'/>/${funcname}/${classNameFullLower}/delete" class="delete"><span>删除</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="<@jspEl 'ctx'/>/${funcname}/${classNameFullLower}/export" target="dwzExport" targetType="navTab" title="确实要导出这些记录吗?"><span>导出</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="40" align="center">序号</th>
				<th width="30"><input type="checkbox" group="ids" class="checkboxCtrl"></th> <#list table.columns as column> <#if !column.htmlHidden>
				<th <vsc:orderField name="${column.columnNameLower}"/>>${column.sqlName}</th> </#if> </#list>
				<th width="200">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="<@jspEl 'page.content' />" var="varitem" varStatus="varindex">
				<tr target="sid" rel="<@jspEl 'varitem.id'/>">
					<td align="center"><@jspEl 'varindex.count+(page.number * page.size)' /></td>
					<td><input name="ids" value="<@jspEl 'varitem.id '/>" type="checkbox"></td> <#list table.columns as column> <#if !column.htmlHidden>
					<td><#rt> <#compress> <#if column.isDateTimeColumn> <fmt:formatDate value='<@jspEl ' varitem.'+column.columnNameLower />' pattern='yyyy-MM-dd'/> <#else> <a
						href="<@jspEl 'ctx'/>/${funcname}/${classNameFullLower}/view/<@jspEl 'varitem.id'/>" target="dialog" title="查看"> <@jspEl 'varitem.'+column.columnNameLower/> </a> </#if>
						</#compress> <#lt>
					</td> </#if> </#list>
					<td><a title="编辑" target="dialog" ref="${classNameFullLower}_update" href="<@jspEl 'ctx'/>/${funcname}/${classNameFullLower}/update/<@jspEl 'varitem.id'/>?navTabId=${navTabId}" class="btnEdit">编辑</a> <a title="删除"
						target="ajaxTodo" href="<@jspEl 'ctx'/>/${funcname}/${classNameFullLower}/delete/<@jspEl 'varitem.id'/>" class="btnDel">删除</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<vsc:pagination page="<@jspEl 'page'/>" numPerPageOnchange="navTabPageBreak({numPerPage:this.value})"></vsc:pagination>
	</div>
</div>
