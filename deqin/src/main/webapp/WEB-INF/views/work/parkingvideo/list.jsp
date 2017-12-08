  <%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<vsc:pagerForm action="#rel#" id="pagerForm"></vsc:pagerForm>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/work/parkingvideo" method="post">
		<div class="searchBar">
			<ul class="searchContent">
				   
				<li><label>车位编号:</label> 
					<input type="text" value="${param.search_LIKE_parkingCode}" name="search_LIKE_parkingCode" /> </li>
				<li><label>车牌号:</label> 
					<input type="text" value="${param.search_LIKE_plateNo}" name="search_LIKE_plateNo" /> </li>   
				<li><label>相机IP:</label> 
					<input type="text" value="${param.search_LIKE_cameraIp}" name="search_LIKE_cameraIp" /> </li>      
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
				<th width="30"><input type="checkbox" group="ids" class="checkboxCtrl"></th>    
				<th <vsc:orderField name="parkingCode"/>>车位编号</th>   
				<th <vsc:orderField name="cameraIp"/>>相机IP</th>   
				<th <vsc:orderField name="status"/>>状态</th>   
				<th <vsc:orderField name="plateNo"/>>车牌号</th>   
				<th <vsc:orderField name="inTime"/>>停入时间</th>   
				<th <vsc:orderField name="createTime"/>>创建时间</th>  
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.content}" var="varitem" varStatus="varindex">
				<tr target="sid" rel="${varitem.id}">
					<td align="center">${varindex.count+(page.number * page.size)}</td>
					<td><input name="ids" value="${varitem.id }" type="checkbox"></td>    
					<td>${varitem.parkingCode}</td> 
					<td>${varitem.cameraIp}</td>			
					<td><s:message code="parkinggaragecarnolog.status.${varitem.status}"/></td> 
					<td>${varitem.plateNo}</td> 
					<td><fmt:formatDate value='${varitem.inTime}' pattern='yyyy-MM-dd HH:mm:ss'/></td>   
					<td><fmt:formatDate value='${varitem.createTime}' pattern='yyyy-MM-dd HH:mm:ss'/></td>  
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<vsc:pagination page="${page}" numPerPageOnchange="navTabPageBreak({numPerPage:this.value})"></vsc:pagination>
	</div>
</div>
