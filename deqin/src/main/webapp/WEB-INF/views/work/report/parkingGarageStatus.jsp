<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<vsc:pagerForm action="#rel#" id="pagerForm"></vsc:pagerForm>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/work/report/parkingGarageStatus" method="post">
		<div class="searchBar">
			<ul class="searchContent">	
				<li><label>停车场:</label> <input type="text" value="${reportView.entity.parkingLotArea.parkingLot.name}" name="parkingLotArea.parkingLot.name" /></li>
				<li><label>所属停车区:</label> <input type="text" value="${reportView.entity.parkingLotArea.name}" name="parkingLotArea.name" /></li>		
				<li><label>车位编号:</label> <input type="text" value="${reportView.entity.code}" name="code" /></li>
				<li><label>车位名称:</label> <input type="text" value="${reportView.entity.name}" name="name" /></li>	
				<li><label>车牌号:</label> <input type="text" value="${reportView.value}" name="value" /></li>
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
	<table class="table" width="100%" layoutH="171">
		<thead>
			<tr>
				<th width="40" align="center">序号</th>
				<th width="30"><input type="checkbox" group="ids" class="checkboxCtrl"></th>		
				<th <vsc:orderField name="plname"/>>停车场名称</th> 
				<th <vsc:orderField name="planame"/>>片区名称</th> 
				<th <vsc:orderField name="pgname"/>>车位名称</th>	
				<th <vsc:orderField name="pgcode"/>>车位编号</th>	
				<th <vsc:orderField name="xcoordinate"/>>X坐标</th>
				<th <vsc:orderField name="ycoordinate"/>>Y坐标</th>
				<th <vsc:orderField name="plateno"/>>车牌号</th> 		
				<th <vsc:orderField name="iscarstatus"/>>是否有车</th> 
				<th <vsc:orderField name="intime"/>>车辆停入时间</th> 
				
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${lm}" var="varitem" varStatus="varindex">
				<tr target="sid" rel="${varitem.id}">
					<td align="center">${varindex.count}</td>
					<td><input name="ids" value="${varitem.id}" type="checkbox"></td>
					<td>${varitem.plname}</td>
					<td>${varitem.planame}</td>
					<td>${varitem.pgname}</td>
					<td>${varitem.pgcode}</td>
					<td>${varitem.xcoordinate}</td>
					<td>${varitem.ycoordinate}</td>
					<td>${varitem.plateno}</td>
					<td><s:message code="parkinggarage.iscarstatus.${varitem.iscarstatus}"/></td>
					<td>${varitem.intime}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
