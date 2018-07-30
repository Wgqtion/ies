<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<vsc:pagerForm action="#rel#" id="pagerForm"></vsc:pagerForm>

<script>

	var searchzNodesParkingCamera =[
		<c:forEach items="${parkingLotTree}" var="entity" varStatus="index">
			{ id:'${entity.code}', pId:'${entity.parent.code}', name:'${entity.name}'}<c:if test="${!index.last}">,</c:if>
    	</c:forEach>
	];
	
	$(function(){
		GenerateSearcchZTree(searchzNodesParkingCamera,'ParkingCamera','${searchCodeParkingCamera}');
	});

	
</script>

<div id="searchBodyParkingCamera" >
	<div class="pageHeader">
		<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/work/parkingCamera" method="post">
			<div class="searchBar">
				<ul class="searchContent">
					<li>
						<label>场区:</label>
						<input type="hidden" id="searchCodeParkingCamera" name="search_IN_parkingGarage.parkingLotCode" value="${searchCodeParkingCamera}"/>
						<input  id="searchNameParkingCamera" name="searchNameParkingCamera" value="${param.searchNameParkingCamera}" readonly="readonly" onclick="showSearchZTree(this,'ParkingCamera');"/>
					</li>
					<li><label>相机IP:</label> <input type="text" value="${param.search_LIKE_cameraIp}" name="search_LIKE_cameraIp" /></li>
					</li>
					<li><label>状态:</label> 
						<select class="combox" name="search_EQ_status" selectedValue="${param['search_EQ_status']}"  dataUrl="${ctx}/static/js/data/parkingCamera_status.json">
							  <vsc:headoption></vsc:headoption>
						</select>
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
				<li><a class="add" title="添加相机" href="${ctx}/work/parkingCamera/new?navTabId=device_parkingCamera_info" target="dialog" rel="parkingCamera_new"><span>添加</span></a></li>
				<li><a class="edit" title="编辑相机" href="${ctx}/work/parkingCamera/update/{sid}?navTabId=device_parkingCamera_info" target="dialog" rel="parkingCamera_update" warn="请选择一个记录"><span>编辑</span></a></li>
				<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="${ctx}/work/parkingCamera/delete" class="delete"><span>删除</span></a></li>
				<li class="line">line</li>
			</ul>
		</div>
		<table class="table" width="100%" layoutH="162">
			<thead>
				<tr>
					<th width="40" align="center">序号</th>
					<th width="30"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
					<th>场区名称</th>
					<th>场区编码</th>
					<th <vsc:orderField name="cameraIp"/>>相机IP</th>
					<th>关联车位</th>
					<th <vsc:orderField name="status"/>>状态</th>
					<th <vsc:orderField name="plateNo"/>>车牌号</th>
					<th <vsc:orderField name="logUpdateTime"/>>最后上报时间</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.content}" var="varitem" varStatus="varindex">
					<tr target="sid" rel="${varitem.id}">
						<td align="center">${varindex.count+(page.number * page.size)}</td>
						<td><input name="ids" value="${varitem.id }" type="checkbox"></td>
						<td>${varitem.parkingGarage.parkingLot.name}</td>
						<td>${fn:substring(varitem.parkingGarage.parkingLot.code, 0, 4)}</td>
						<td>${varitem.cameraIp}</td>
						<td><a href="${ctx}/work/parkinggarage/view/${varitem.parkingGarage.id}" target="dialog" title="查看停车位" rel="parkinggarage_view">${varitem.parkingGarage.name} </a></td>
						<td><s:message code="parkingCamera.status.${varitem.status}" /></td>
						<td>${varitem.plateNo}<c:if test="${varitem.plateNo!=null}"><a title="查看照片" rel="device_parkingCamera_picture" target="_blank" href="${ctx}/work/parkingCamera/picture/${varitem.id}" class="btnAttach"></a><img height="50" width="150" src="${ctx}/work/parkingCamera/picture/${varitem.id}"></c:if></td>
						<td><fmt:formatDate value='${ varitem.logUpdateTime}' pattern='yyyy-MM-dd HH:mm:ss' /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="panelBar">
			<vsc:pagination page="${page}" numPerPageOnchange="navTabPageBreak({numPerPage:this.value})"></vsc:pagination>
		</div>
	</div>
	<div id="searchContentParkingCamera" class="menuContent" style="display: none;position: absolute;">
	   <ul id="searchTreeParkingCamera" class="ztree editZtree" style="margin-top:0; width:200px;"></ul>
	</div>
</div>