  <%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<vsc:pagerForm action="#rel#" id="pagerForm"></vsc:pagerForm>

<script>

	var searchzNodesParkingParam =[
		<c:forEach items="${parkingLotTree}" var="entity" varStatus="index">
			{ id:'${entity.code}', pId:'${entity.parent.code}', name:'${entity.name}'}<c:if test="${!index.last}">,</c:if>
    	</c:forEach>
	];
	
	$(function(){
		GenerateSearcchZTree(searchzNodesParkingParam,'ParkingParam','${param.search_IN_parkingLotCode}');
	});

	
</script>

<div id="searchBodyParkingParam" >
	<div class="pageHeader">
		<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/work/parkingParam" method="post">
			<div class="searchBar">
				<ul class="searchContent">
					<li>
						<label>场区:</label>
						<input type="hidden" id="searchCodeParkingParam" name="search_IN_parkingLotCode" value="${param.search_IN_parkingLotCode}"/>
						<input  id="searchNameParkingParam" name="searchNameParkingParam" value="${param.searchNameParkingParam}" readonly="readonly" onclick="showSearchZTree(this,'ParkingParam');"/>
					</li>
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
				<li><a class="add" title="添加" href="${ctx}/work/parkingParam/new?navTabId=basic_parkingParam" target="dialog" rel="parkingParam_new"><span>添加</span></a></li>
				<li><a class="edit" title="编辑" href="${ctx}/work/parkingParam/update/{sid}?navTabId=basic_parkingParam" target="dialog" rel="parkingParam_update"
					warn="请选择一个记录"><span>编辑</span></a></li>
				<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="${ctx}/work/parkingParam/delete" class="delete"><span>删除</span></a></li>
				<li class="line">line</li>
				 	</ul>
		</div>
		<table class="table" width="100%" layoutH="110">
			<thead>
				<tr>
					<th width="40" align="center">序号</th>
					<th width="30"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
					<th>场区名称</th>
					<th <vsc:orderField name="reserveMin"/>>预约保留分钟</th>
					<th <vsc:orderField name="cancelNum"/>>预约取消次数上限</th>
					<th <vsc:orderField name="freeReserveMin"/>>预约免费分钟</th>
					<th <vsc:orderField name="freeParkingMin"/>>停车免费分钟</th>
					<th <vsc:orderField name="privilegeReserveMin"/>>预约优惠分钟</th>
					<th <vsc:orderField name="privilegeParkingMin"/>>停车优惠分钟</th>
					<th <vsc:orderField name="maxReserveFee"/>>预约上限费用</th>
					<th <vsc:orderField name="maxParkingFee"/>>停车上限费用</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.content}" var="varitem" varStatus="varindex">
					<tr target="sid" rel="${varitem.id}">
						<td align="center">${varindex.count+(page.number * page.size)}</td>
						<td><input name="ids" value="${varitem.id }" type="checkbox"></td>
						<td>${varitem.parkingLot.name}</td>
						<td>${varitem.reserveMin}</td>  
						<td>${varitem.cancelNum}</td>  
						<td>${varitem.freeReserveMin}</td>  
						<td>${varitem.freeParkingMin}</td> 
						<td>${varitem.privilegeReserveMin}</td>  
						<td>${varitem.privilegeParkingMin}</td>  
						<td>${varitem.maxReserveFee}</td>  
						<td>${varitem.maxParkingFee}</td>  
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="panelBar">
			<vsc:pagination page="${page}" numPerPageOnchange="navTabPageBreak({numPerPage:this.value})"></vsc:pagination>
		</div>
	</div>
	<div id="searchContentParkingParam" class="menuContent" style="display: none;position: absolute;">
	   <ul id="searchTreeParkingParam" class="ztree editZtree" style="margin-top:0; width:200px;"></ul>
	</div>
</div>