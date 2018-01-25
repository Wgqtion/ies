  <%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<vsc:pagerForm action="#rel#" id="pagerForm"></vsc:pagerForm>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/work/yudingsetting" method="post">
		<div class="searchBar">
			<ul class="searchContent">
				   
				<li><label>MONDAY_START_TIME:</label> 
					<input type="text" value="${param.search_LIKE_mondayStartTime}" name="search_LIKE_mondayStartTime" /> </li>   
				<li><label>TUESDAY_START_TIME:</label> 
					<input type="text" value="${param.search_LIKE_tuesdayStartTime}" name="search_LIKE_tuesdayStartTime" /> </li>      
				<li><label>FRIDAY_START_TIME:</label> 
					<input type="text" value="${param.search_LIKE_fridayStartTime}" name="search_LIKE_fridayStartTime" /> </li>   
				<li><label>SATURDAY_START_TIME:</label> 
					<input type="text" value="${param.search_LIKE_saturdayStartTime}" name="search_LIKE_saturdayStartTime" /> </li>      
				<li><label>TUESDAY_END_TIME:</label> 
					<input type="text" value="${param.search_LIKE_tuesdayEndTime}" name="search_LIKE_tuesdayEndTime" /> </li>   
				<li><label>WEDNESDAY_END_TIME:</label> 
					<input type="text" value="${param.search_LIKE_wednesdayEndTime}" name="search_LIKE_wednesdayEndTime" /> </li>      
				<li><label>SATURDAY_END_TIME:</label> 
					<input type="text" value="${param.search_LIKE_saturdayEndTime}" name="search_LIKE_saturdayEndTime" /> </li>   
				<li><label>SUNDAY_END_TIME:</label> 
					<input type="text" value="${param.search_LIKE_sundayEndTime}" name="search_LIKE_sundayEndTime" /> </li>      
				<li><label>LOCKED_MINUTES:</label> 
					<input type="text" value="${param.search_LIKE_lockedMinutes}" name="search_LIKE_lockedMinutes" /> </li>   
				<li><label>LOCKED_COST:</label> 
					<input type="text" value="${param.search_LIKE_lockedCost}" name="search_LIKE_lockedCost" /> </li>      
				<li><label>LASTTIME:</label> 
					<input type="text" value="${param.search_LIKE_lasttime}" name="search_LIKE_lasttime" /> </li> 
			</ul>
			<div class="subBar">
				<ul>
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">检索</button>
							</div>
						</div></li>
					<li><a rel="work_yudingsetting_search" class="button" href="${ctx}/work/yudingsetting/search" target="dialog" minable="false"
						fresh="false" title="高级检索"><span>高级检索</span></a></li>
				</ul>
			</div>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" title="添加" href="${ctx}/work/yudingsetting/new?navTabId=work_yudingsetting" target="dialog" rel="yudingsetting_new"><span>添加</span></a></li>
			<li><a class="edit" title="编辑" href="${ctx}/work/yudingsetting/update/{sid}?navTabId=work_yudingsetting" target="dialog" rel="yudingsetting_update"
				warn="请选择一个记录"><span>编辑</span></a></li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="${ctx}/work/yudingsetting/delete" class="delete"><span>删除</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="${ctx}/work/yudingsetting/export" target="dwzExport" targetType="navTab" title="确实要导出这些记录吗?"><span>导出</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="40" align="center">序号</th>
				<th width="30"><input type="checkbox" group="ids" class="checkboxCtrl"></th>    
				<th <vsc:orderField name="mondayStartTime"/>>MONDAY_START_TIME</th>   
				<th <vsc:orderField name="tuesdayStartTime"/>>TUESDAY_START_TIME</th>   
				<th <vsc:orderField name="wednesdayStartTime"/>>WEDNESDAY_START_TIME</th>   
				<th <vsc:orderField name="thursdayStartTime"/>>THURSDAY_START_TIME</th>   
				<th <vsc:orderField name="fridayStartTime"/>>FRIDAY_START_TIME</th>   
				<th <vsc:orderField name="saturdayStartTime"/>>SATURDAY_START_TIME</th>   
				<th <vsc:orderField name="sundayStartTime"/>>SUNDAY_START_TIME</th>   
				<th <vsc:orderField name="mondayEndTime"/>>MONDAY_END_TIME</th>   
				<th <vsc:orderField name="tuesdayEndTime"/>>TUESDAY_END_TIME</th>   
				<th <vsc:orderField name="wednesdayEndTime"/>>WEDNESDAY_END_TIME</th>   
				<th <vsc:orderField name="thursdayEndTime"/>>THURSDAY_END_TIME</th>   
				<th <vsc:orderField name="fridayEndTime"/>>FRIDAY_END_TIME</th>   
				<th <vsc:orderField name="saturdayEndTime"/>>SATURDAY_END_TIME</th>   
				<th <vsc:orderField name="sundayEndTime"/>>SUNDAY_END_TIME</th>   
				<th <vsc:orderField name="startAddMinutes"/>>START_ADD_MINUTES</th>   
				<th <vsc:orderField name="endAddMinutes"/>>END_ADD_MINUTES</th>   
				<th <vsc:orderField name="lockedMinutes"/>>LOCKED_MINUTES</th>   
				<th <vsc:orderField name="lockedCost"/>>LOCKED_COST</th>   
				<th <vsc:orderField name="lockedHourCost"/>>LOCKED_HOUR_COST</th>   
				<th <vsc:orderField name="createTime"/>>CREATE_TIME</th>   
				<th <vsc:orderField name="lasttime"/>>LASTTIME</th>  
				<th width="200">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.content}" var="varitem" varStatus="varindex">
				<tr target="sid" rel="${varitem.id}">
					<td align="center">${varindex.count+(page.number * page.size)}</td>
					<td><input name="ids" value="${varitem.id }" type="checkbox"></td>    
					<td> <a href="${ctx}/work/yudingsetting/view/${varitem.id}" target="dialog" title="查看"> ${varitem.mondayStartTime} </a> 
					</td>   
					<td> <a href="${ctx}/work/yudingsetting/view/${varitem.id}" target="dialog" title="查看"> ${varitem.tuesdayStartTime} </a> 
					</td>   
					<td> <a href="${ctx}/work/yudingsetting/view/${varitem.id}" target="dialog" title="查看"> ${varitem.wednesdayStartTime} </a> 
					</td>   
					<td> <a href="${ctx}/work/yudingsetting/view/${varitem.id}" target="dialog" title="查看"> ${varitem.thursdayStartTime} </a> 
					</td>   
					<td> <a href="${ctx}/work/yudingsetting/view/${varitem.id}" target="dialog" title="查看"> ${varitem.fridayStartTime} </a> 
					</td>   
					<td> <a href="${ctx}/work/yudingsetting/view/${varitem.id}" target="dialog" title="查看"> ${varitem.saturdayStartTime} </a> 
					</td>   
					<td> <a href="${ctx}/work/yudingsetting/view/${varitem.id}" target="dialog" title="查看"> ${varitem.sundayStartTime} </a> 
					</td>   
					<td> <a href="${ctx}/work/yudingsetting/view/${varitem.id}" target="dialog" title="查看"> ${varitem.mondayEndTime} </a> 
					</td>   
					<td> <a href="${ctx}/work/yudingsetting/view/${varitem.id}" target="dialog" title="查看"> ${varitem.tuesdayEndTime} </a> 
					</td>   
					<td> <a href="${ctx}/work/yudingsetting/view/${varitem.id}" target="dialog" title="查看"> ${varitem.wednesdayEndTime} </a> 
					</td>   
					<td> <a href="${ctx}/work/yudingsetting/view/${varitem.id}" target="dialog" title="查看"> ${varitem.thursdayEndTime} </a> 
					</td>   
					<td> <a href="${ctx}/work/yudingsetting/view/${varitem.id}" target="dialog" title="查看"> ${varitem.fridayEndTime} </a> 
					</td>   
					<td> <a href="${ctx}/work/yudingsetting/view/${varitem.id}" target="dialog" title="查看"> ${varitem.saturdayEndTime} </a> 
					</td>   
					<td> <a href="${ctx}/work/yudingsetting/view/${varitem.id}" target="dialog" title="查看"> ${varitem.sundayEndTime} </a> 
					</td>   
					<td> <a href="${ctx}/work/yudingsetting/view/${varitem.id}" target="dialog" title="查看"> ${varitem.startAddMinutes} </a> 
					</td>   
					<td> <a href="${ctx}/work/yudingsetting/view/${varitem.id}" target="dialog" title="查看"> ${varitem.endAddMinutes} </a> 
					</td>   
					<td> <a href="${ctx}/work/yudingsetting/view/${varitem.id}" target="dialog" title="查看"> ${varitem.lockedMinutes} </a> 
					</td>   
					<td> <a href="${ctx}/work/yudingsetting/view/${varitem.id}" target="dialog" title="查看"> ${varitem.lockedCost} </a> 
					</td>   
					<td> <a href="${ctx}/work/yudingsetting/view/${varitem.id}" target="dialog" title="查看"> ${varitem.lockedHourCost} </a> 
					</td>   
					<td> <fmt:formatDate value='${ varitem.createTime}' pattern='yyyy-MM-dd'/> 
					</td>   
					<td> <a href="${ctx}/work/yudingsetting/view/${varitem.id}" target="dialog" title="查看"> ${varitem.lasttime} </a> 
					</td>  
					<td><a title="编辑" target="dialog" ref="yudingsetting_update" href="${ctx}/work/yudingsetting/update/${varitem.id}?navTabId=work_yudingsetting" class="btnEdit">编辑</a> <a title="删除"
						target="ajaxTodo" href="${ctx}/work/yudingsetting/delete/${varitem.id}" class="btnDel">删除</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<vsc:pagination page="${page}" numPerPageOnchange="navTabPageBreak({numPerPage:this.value})"></vsc:pagination>
	</div>
</div>
