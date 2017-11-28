<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<vsc:pagerForm action="#rel#" id="pagerForm"></vsc:pagerForm>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/work/parkingordernotfinished" method="post">
		<div class="searchBar">
			<ul class="searchContent">
				<li><label>停车场:</label> <input type="text" value="${param['search_LIKE_parkingLotArea.parkingLot.name']}" name="search_LIKE_parkingLotArea.parkingLot.name" /></li>
				<li><label>所属停车区:</label> <input type="text" value="${param['search_LIKE_parkingLotArea.name']}" name="search_LIKE_parkingLotArea.name" /></li>
				<li><label>停车位编号:</label> <input type="text" value="${param.search_LIKE_name}" name="search_LIKE_name" /></li>
				<li><label>停车位名称:</label> <input type="text" value="${param.search_EQ_ipAddress}" name="search_EQ_ipAddress" /></li>				
				<li><label>车牌号:</label> <input type="text" value="${param.search_LIKE_carNo}" name="search_LIKE_carNo" /></li>
				 
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
			<li><a title="车辆反寻"  href="${ctx}/work/parkingordernotfinished/view/{sid}" target="dialog" rel="parkingordernotfinished_view" warn="请选择一个记录" class="icon" height="660"><span>车辆反寻</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="171">
		<thead>
			<tr>
				<th width="40" align="center">序号</th>
				<th width="30"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th <vsc:orderField name="name"/>>停车位编号</th>	
				<th <vsc:orderField name="ipAddress"/>>停车位名称</th> 			
				<th>所属停车区</th><th>停车场</th>
				<th <vsc:orderField name="xcoordinate"/>>X坐标</th>
				<th <vsc:orderField name="ycoordinate"/>>Y坐标</th>
				<th <vsc:orderField name="garageType"/>>车位类型</th>			
				<th <vsc:orderField name="isEnabled"/>>状态</th> 
				<th <vsc:orderField name="isOnline"/>>是否有车</th> 
				<th <vsc:orderField name="carNo"/>>车牌号</th> 
				<th <vsc:orderField name="intime"/>>车辆停入时间</th> 
				
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.content}" var="varitem" varStatus="varindex">
				<tr target="sid" rel="${varitem.id}">
					<td align="center">${varindex.count+(page.number * page.size)}</td>
					<td><input name="ids" value="${varitem.id}" type="checkbox"></td>
					<td><a href="${ctx}/work/parkinggarage/view/${varitem.id}" target="dialog" title="查看停车位" rel="parkinggarage_view">${varitem.name}</a></td>
					<td>${varitem.ipAddress}</td>
					<td>${varitem.parkingLotArea.fullIndexName}</td>
					<td>${varitem.parkingLotArea.parkingLot.name}</td>
					<td>${varitem.xcoordinate}</td>
					<td>${varitem.ycoordinate}</td>
					<td><s:message code="parkinggarage.garageType.${varitem.garageType}"/></td>
					<td><s:message code="parkinggarage.isenabled.${varitem.isEnabled}"/></td>			
					 <td><s:message code="parkinggarage.isOnline.${varitem.isOnline}"/></td>
				<td>${varitem.carNo}</td>
				<td><fmt:formatDate value='${ varitem.intime}' pattern='yyyy-MM-dd HH:mm:ss'/></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<vsc:pagination page="${page}" numPerPageOnchange="navTabPageBreak({numPerPage:this.value})"></vsc:pagination>
	</div>
</div>
