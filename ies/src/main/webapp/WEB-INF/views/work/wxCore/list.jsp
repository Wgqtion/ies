<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<vsc:pagerForm action="#rel#" id="pagerForm"></vsc:pagerForm>


<script>

	var searchzNodesWxCore =[
		<c:forEach items="${parkingLotTree}" var="entity" varStatus="index">
			{ id:'${entity.code}', pId:'${entity.parent.code}', name:'${entity.name}'}<c:if test="${!index.last}">,</c:if>
    	</c:forEach>
	];
	
	$(function(){
		GenerateSearcchZTree(searchzNodesWxCore,'WxCore','${searchCodeWxCore}');
	});

	
</script>

<div id="searchBodyWxCore" >
	<div class="pageHeader">
		<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/work/wxCore" method="post">
			<div class="searchBar">
				<ul class="searchContent">
					<li>
						<label>场区:</label>
						<input type="hidden" id="searchCodeWxCore" name="search_IN_parkingLock.parkingGarage.parkingLotCode" value="${searchCodeWxCore}"/>
						<input  id="searchNameWxCore" name="searchNameWxCore" value="${param.searchNameWxCore}" readonly="readonly" onclick="showSearchZTree(this,'WxCore');"/>
					</li>
					<li><label>类型:</label> 
						<select class="combox" name="search_EQ_type" selectedValue="${param['search_EQ_type']}"  dataUrl="${ctx}/static/js/data/wxCore_type.json">
							  <vsc:headoption></vsc:headoption>
						</select>
					</li>
					<li><label>状态:</label> 
						<select class="combox" name="search_EQ_status" selectedValue="${param['search_EQ_status']}"  dataUrl="${ctx}/static/js/data/wxCore_status.json">
							  <vsc:headoption></vsc:headoption>
						</select>
					</li>
					<li><label>是否免费:</label> 
						<select class="combox" name="search_EQ_isFree" selectedValue="${param['search_EQ_isFree']}"  dataUrl="${ctx}/static/js/data/wxCore_isFree.json">
							  <vsc:headoption></vsc:headoption>
						</select>
					</li>
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
			</ul>
		</div>
		<table class="table" width="100%" layoutH="138">
			<thead>
				<tr>
					<th width="40" align="center">序号</th>
					<th <vsc:orderField name="type"/>>类型</th>
					<th <vsc:orderField name="weixinId"/>>用户</th>
					<th>场区名称</th>
					<th>车位名称</th>
					<th>费用</th>
					<th <vsc:orderField name="status"/>>状态</th>
					<th <vsc:orderField name="isFree"/>>是否免费</th>
					<th <vsc:orderField name="startTime"/>>开始时间</th>
					<th <vsc:orderField name="endTime"/>>结束时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.content}" var="varitem" varStatus="varindex">
					<tr target="sid" rel="${varitem.id}">
						<td align="center">${varindex.count+(page.number * page.size)}</td>
						<td><s:message code="wxCore.type.${varitem.type}"/></td>
						<td>${varitem.wxUser.name} </a></td>
						<td>${varitem.parkingLock.parkingGarage.parkingLot.name} </a></td>
						<td>${varitem.parkingLock.parkingGarage.name} </a></td>
						<td>${varitem.amount}</td>
						<td><s:message code="wxCore.status.${varitem.status}"/></td>
						<td><s:message code="wxCore.isFree.${varitem.isFree}"/></td>
						<td><fmt:formatDate value='${ varitem.startTime}' pattern='yyyy-MM-dd HH:mm:ss' /></td>
						<td><fmt:formatDate value='${ varitem.endTime}' pattern='yyyy-MM-dd HH:mm:ss' /></td>
						<td><a href="${ctx}/work/wxCore/view/${varitem.id}" target="dialog" title="查看明细" rel="wxCore_view" class="btnInfo">明细</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="panelBar">
			<vsc:pagination page="${page}" numPerPageOnchange="navTabPageBreak({numPerPage:this.value})"></vsc:pagination>
		</div>
	</div>
	<div id="searchContentWxCore" class="menuContent" style="display: none;position: absolute;">
	   <ul id="searchTreeWxCore" class="ztree editZtree" style="margin-top:0; width:200px;"></ul>
	</div>
</div>