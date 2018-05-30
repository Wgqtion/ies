<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<vsc:pagerForm action="#rel#" id="pagerForm"></vsc:pagerForm>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/work/report/parkingGarageStatus" method="post">
		<div class="searchBar">
			<ul class="searchContent">	
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
				<th <vsc:orderField name="plname"/>>场区名称</th>
				<th <vsc:orderField name="pgname"/>>车位名称</th>	
				<th <vsc:orderField name="itudelong"/>>纬度坐标</th>
				<th <vsc:orderField name="itudelat"/>>经度坐标</th>
				<th <vsc:orderField name="iscarstatus"/>>是否有车</th> 
				<th <vsc:orderField name="plateno"/>>车牌号</th> 		
				<th <vsc:orderField name="intime"/>>车辆停入时间</th> 
				
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${lm}" var="varitem" varStatus="varindex">
				<tr target="sid" rel="${varitem.id}">
					<td align="center">${varindex.count}</td>
					<td>${varitem.plname}</td>
					<td>${varitem.pgname}</td>
					<td>${varitem.itudelong}</td>
					<td>${varitem.itudelat}</td>
					<td><s:message code="parkinggarage.iscarstatus.${varitem.iscarstatus}"/></td>
					<td><c:if test="${varitem.iscarstatus eq 1}">${varitem.plateno}</c:if></td>
					<td><c:if test="${varitem.iscarstatus eq 1}">${varitem.intime}</c:if></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
