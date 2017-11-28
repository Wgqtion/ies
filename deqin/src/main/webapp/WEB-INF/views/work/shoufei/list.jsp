  <%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<vsc:pagerForm action="#rel#" id="pagerForm"></vsc:pagerForm>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/work/shoufei" method="post">
		<div class="searchBar">
			<ul class="searchContent">
				<li><label>收费规则:</label> <input type="text" value="${param.search_LIKE_name}" name="search_LIKE_name" /></li>
			</ul>
			<div class="subBar">
				<ul>
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">检索</button>
							</div>
						</div></li>
					<li><a rel="work_shoufei_search" class="button" href="${ctx}/work/shoufei/search" target="dialog" minable="false"
						fresh="false" title="高级检索"><span>高级检索</span></a></li>
				</ul>
			</div>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" title="添加" href="${ctx}/work/shoufei/new?navTabId=work_shoufei" target="dialog" rel="shoufei_new"><span>添加</span></a></li>
			<li><a class="edit" title="编辑" href="${ctx}/work/shoufei/update/{sid}?navTabId=work_shoufei" target="dialog" rel="shoufei_update"
				warn="请选择一个记录"><span>编辑</span></a></li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="${ctx}/work/shoufei/delete" class="delete"><span>删除</span></a></li>
			<li class="line">line</li>
			<li><a title="确实从进校证系统同步收费规则数据吗?" target="ajaxTodo" href="${ctx}/work/shoufei/sync?navTabId=work_shoufei" class="reload"><span>同步</span></a></li>
			 
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="40" align="center">序号</th>
				<th width="30"><input type="checkbox" group="ids" class="checkboxCtrl"></th>    
				<th <vsc:orderField name="cardTypeId"/>>进校证类型</th>   
				<th <vsc:orderField name="parkingLotId"/>>校区</th>   
				<th <vsc:orderField name="dayTime"/>>白天免费停车时长</th>   
				<th <vsc:orderField name="nightTime"/>>晚上免费停车时长</th>   
				<th <vsc:orderField name="dayHourMoney"/>>白天收费(元/时)</th>   
				<th <vsc:orderField name="nightHourMoney"/>>晚上收费(元/时)</th>   
				<th <vsc:orderField name="dayMaxMoney"/>>白天最高收费</th>   
				<th <vsc:orderField name="nightMaxMoney"/>>晚上最高收费</th>   
				<th <vsc:orderField name="everydayMianfeiTime"/>>白天每天免费时长</th>   
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.content}" var="varitem" varStatus="varindex">
				<tr target="sid" rel="${varitem.id}">
					<td align="center">${varindex.count+(page.number * page.size)}</td>
					<td><input name="ids" value="${varitem.id }" type="checkbox"></td>    
					<td> ${varitem.cardType.name} </a> 
					</td>   
					<td>  ${varitem.parkingLot.name} </a> 
					</td>   
					<td>  ${varitem.dayTime} </a> 
					</td>   
					<td>  ${varitem.nightTime} </a> 
					</td>   
					<td>  ${varitem.dayHourMoney} </a> 
					</td>   
					<td>  ${varitem.nightHourMoney} </a> 
					</td>   
					<td>  ${varitem.dayMaxMoney} </a> 
					</td>   
					<td>  ${varitem.nightMaxMoney} </a> 
					</td>   
					<td>  ${varitem.everydayMianfeiTime} </a> 
					</td>   
					 
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<vsc:pagination page="${page}" numPerPageOnchange="navTabPageBreak({numPerPage:this.value})"></vsc:pagination>
	</div>
</div>
