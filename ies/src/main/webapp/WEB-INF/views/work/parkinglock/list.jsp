<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<vsc:pagerForm action="#rel#" id="pagerForm"></vsc:pagerForm>

<script>

	var searchzNodesParkingLock =[
		<c:forEach items="${parkingLotTree}" var="entity" varStatus="index">
			{ id:'${entity.code}', pId:'${entity.parent.code}', name:'${entity.name}'}<c:if test="${!index.last}">,</c:if>
    	</c:forEach>
	];
	
	$(function(){
		GenerateSearcchZTree(searchzNodesParkingLock,'ParkingLock','${searchCodeParkingLock}');
	});

	
</script>

<div id="searchBodyParkingLock" >
	<div class="pageHeader">
		<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/work/parkinglock" method="post">
			<div class="searchBar">
				<ul class="searchContent">
					<li>
						<label>场区:</label>
						<input type="hidden" id="searchCodeParkingLock" name="search_IN_parkingGarage.parkingLotCode" value="${searchCodeParkingLock}"/>
						<input  id="searchNameParkingLock" name="searchNameParkingLock" value="${param.searchNameParkingLock}" readonly="readonly" onclick="showSearchZTree(this,'ParkingLock');"/>
					</li>
					<li><label>区域编号:</label> <input type="text" value="${param.search_LIKE_ipAddress}" name="search_LIKE_ipAddress" /></li>
					<li><label>地锁编号:</label> <input type="text" value="${param.search_LIKE_lockNum}" name="search_LIKE_lockNum" /></li>
					<li><label>使用状态:</label> 
					<select class="combox" name="search_EQ_isEnabled" selectedValue="${param['search_EQ_isEnabled']}"  dataUrl="${ctx}/static/js/data/parkinglock_isEnabled.json">
						  <vsc:headoption></vsc:headoption>
					</select>
					</li>
					<li><label>开关状态:</label> 
						<select class="combox" name="search_EQ_isCarOn" selectedValue="${param['search_EQ_isCarOn']}"  dataUrl="${ctx}/static/js/data/parkinglock_isCarOn.json">
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
				<li><a class="add" title="添加地锁" href="${ctx}/work/parkinglock/new?navTabId=device_lock_info" target="dialog" rel="parkinglock_new"><span>添加</span></a></li>
				<li><a class="edit" title="编辑地锁" href="${ctx}/work/parkinglock/update/{sid}?navTabId=device_lock_info" target="dialog" rel="parkinglock_update" warn="请选择一个记录"><span>编辑</span></a></li>
				<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="${ctx}/work/parkinglock/delete" class="delete"><span>删除</span></a></li>
				<li class="line">line</li>
				<li><a class="edit" title="确实要开启这些地锁吗?"   target="selectedTodo" rel="ids"  href="${ctx}/work/parkinglock/reverse?state=02"   ><span>开启</span></a></li>
				<li><a class="edit" title="确实要关闭这些地锁吗?"  target="selectedTodo" rel="ids"  href="${ctx}/work/parkinglock/reverse?state=01"   ><span>关闭</span></a></li>
				<%-- <li><a class="edit" title="确实要永久开启这些地锁吗?"  target="selectedTodo" rel="ids"  href="${ctx}/work/parkinglock/reverse?state=32"   ><span>开锁定</span></a></li>
				<li><a class="edit" title="确实要永久关闭这些地锁吗?" target="selectedTodo" rel="ids"  href="${ctx}/work/parkinglock/reverse?state=30"  ><span>关锁定</span></a></li> --%>
				
			</ul>
		</div>
		<table class="table" width="100%" layoutH="162">
			<thead>
				<tr>
					<th width="40" align="center">序号</th>
					<th width="30"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
					<th>场区名称</th>
					<th <vsc:orderField name="ipAddress"/>>区域编号</th>
					<th <vsc:orderField name="lockNum"/>>地锁编号</th>
					<th <vsc:orderField name="isEnabled"/>>使用状态</th>
					<th>关联车位</th>
					<th <vsc:orderField name="isCarOn"/>>超声波状态</th>
					<th <vsc:orderField name="isOnline"/>>是否在线</th>
					<th <vsc:orderField name="isOpen"/>>开关状态</th>
					<th <vsc:orderField name="logUpdateTime"/>>最后上报时间</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.content}" var="varitem" varStatus="varindex">
					<tr target="sid" rel="${varitem.id}">
						<td align="center">${varindex.count+(page.number * page.size)}</td>
						<td><input name="ids" value="${varitem.id }" type="checkbox"></td>
						<td>${varitem.parkingGarage.parkingLot.name}</td>
						<td>${varitem.ipAddress}</td>
						<td><a href="${ctx}/work/parkinglock/view/${varitem.id}" target="dialog" title="查看地锁信息" rel="parkinglock__view">${varitem.lockNum} </a></td>
						<td><s:message code="parkinglock.isenabled.${varitem.isEnabled}" /></td>
						<td><a href="${ctx}/work/parkinggarage/view/${varitem.parkingGarage.id}" target="dialog" title="查看停车位" rel="parkinggarage_view">${varitem.parkingGarage.name} </a></td>
						<td><s:message code="parkinglock.isCarOn.${varitem.isCarOn}" /></td>
						<td><s:message code="parkinglock.isOnline.${varitem.isOnline}" /></td>
						<td><s:message code="parkinglock.isOpen.${varitem.isOpen}" /></td>
						<td><fmt:formatDate value='${ varitem.logUpdateTime}' pattern='yyyy-MM-dd HH:mm:ss' /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="panelBar">
			<vsc:pagination page="${page}" numPerPageOnchange="navTabPageBreak({numPerPage:this.value})"></vsc:pagination>
		</div>
	</div>
	<div id="searchContentParkingLock" class="menuContent" style="display: none;position: absolute;">
	   <ul id="searchTreeParkingLock" class="ztree editZtree" style="margin-top:0; width:200px;"></ul>
	</div>
</div>