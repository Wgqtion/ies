<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<vsc:pagerForm action="#rel#" id="pagerForm" cacheName="hids"></vsc:pagerForm>
<div class="pageHeader">
	<form rel="pagerForm" method="post" action="${ctx}/work/campus/select" onsubmit="return dwzSearch(this, 'dialog');">
		<div class="searchBar">
			<ul class="searchContent">
				<li><label>校门名称:</label> <input type="text" value="${param.search_LIKE_name}" name="search_LIKE_name" /></li>
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
								<button type="button" multLookup="selectCampusIds" warn="请选择资源">选择</button>
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
				<th width="30"><input type="checkbox" class="checkboxCtrl" group="ids" /></th>
				<th <vsc:orderField name="name"/>>校门名称</th>
				<th>所属校区</th>
				<th <vsc:orderField name="createTime"/>>创建时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.content}" var="item" varStatus="index">
				<tr>
					<!-- 
					<td><input <vsc:defaultIfIndexOfParameterValues cacheName="selectCampusIds" objectToFind="{id:'${item.id}',name:'${item.name}'}"  yesStr="checked=\"checked\""/> onclick="javascript:pagePass(this,'pagerForm','selectCampusIds')" type="checkbox" name="ids" value="{id:'${item.id}',name:'${item.name}'}" /></td>
					 -->					
					<td><a title="查找带回" href="javascript:$.bringBack({id:'${item.id}', name:'${item.name}'})" class="btnSelect">选择</a></td>
					<td><a href="${ctx}/work/campus/view/${item.id}" target="dialog" title="查看校门" rel="work_campus_new"> ${item.name} </a></td>
					<td>${item.parkingLot.name}</td>
					<td><fmt:formatDate value='${item.createTime}' pattern='yyyy-MM-dd HH:mm' /></td>
					 
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<vsc:pagination page="${page}" targetType="dialog" numPerPageOnchange="dwzPageBreak({targetType:'dialog', data:{numPerPage:this.value}})"></vsc:pagination>
	</div>
</div>
