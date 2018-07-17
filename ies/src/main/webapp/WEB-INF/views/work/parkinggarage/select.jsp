<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<vsc:pagerForm action="#rel#" id="pagerForm" cacheName="hids"></vsc:pagerForm>

<script>

	var searchzNodesParkingGarageSelect =[
		<c:forEach items="${parkingLotTree}" var="entity" varStatus="index">
			{ id:'${entity.code}', pId:'${entity.parent.code}', name:'${entity.name}'}<c:if test="${!index.last}">,</c:if>
    	</c:forEach>
	];
	
	$(function(){
		GenerateSearcchZTree(searchzNodesParkingGarageSelect,'ParkingGarageSelect','${searchCodeParkingGarageSelect}');
	});

	
</script>
<div id="searchBodyParkingGarageSelect" >
	<div class="pageHeader">
		<form rel="pagerForm" method="post" action="${ctx}/work/parkinggarage/select" onsubmit="return dwzSearch(this, 'dialog');">
			<div class="searchBar">
				<ul class="searchContent">
					<li>
						<label>场区:</label>
						<input type="hidden" id="searchCodeParkingGarageSelect" name="search_IN_parkingLotCode" value="${param.search_IN_parkingLotCode}"/>
						<input  id="searchNameParkingGarageSelect" name="searchNameParkingGarageSelect" value="${param.searchNameParkingGarageSelect}" readonly="readonly" onclick="showSearchZTree(this,'ParkingGarageSelect');"/>
					</li>
					<li><label>车位名称:</label> <input type="text" value="${param.search_LIKE_name}" name="search_LIKE_name" /></li>
				</ul>
				<div class="subBar">
					<ul>
						<li>
							<div class="buttonActive">
								<div class="buttonContent">
									<button type="submit">查询</button>
								</div>
							</div>
						</li>
						<li>
							<div class="button">
								<div class="buttonContent">
									<button type="button" onclick="javascript:$.bringBack({id:'',name:''})">清空</button>
								</div>
							</div>
						</li>
					</ul>
				</div>
			</div>
		</form>
	</div>
	<div class="pageContent">
		<table class="table" layoutH="108" targetType="dialog" width="100%">
			<thead>
				<tr>
					<th width="30" align="center"></th>			
					<th <vsc:orderField name="name"/>>车位名称</th>		
					<th>场区名称</th>	
					<th <vsc:orderField name="isEnabled"/>>状态</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.content}" var="item" varStatus="index">
					<tr>
						<td><a title="查找带回" href="javascript:$.bringBack({id:'${item.id}', name:'${item.name}'})" class="btnSelect">选择</a></td>
						<td><a href="${ctx}/work/parkinggarage/view/${item.id}" target="dialog" title="查看停车位" rel="parkinggarage_view">${item.name}</a></td>
						<td>${item.parkingLot.name}</td>
						<td><s:message code="parkinggarage.isenabled.${item.isEnabled}"/></td>			
						 
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="panelBar">
			<vsc:pagination page="${page}" targetType="dialog" numPerPageOnchange="dwzPageBreak({targetType:'dialog', data:{numPerPage:this.value}})"></vsc:pagination>
		</div>
	</div>
	<div id="searchContentParkingGarageSelect" class="menuContent" style="display: none;position: absolute;">
	   <ul id="searchTreeParkingGarageSelect" class="ztree editZtree" style="margin-top:0; width:200px;"></ul>
	</div>
</div>