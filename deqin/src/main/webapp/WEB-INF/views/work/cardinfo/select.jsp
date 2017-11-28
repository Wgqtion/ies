<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<vsc:pagerForm action="#rel#" id="pagerForm" cacheName="uids"></vsc:pagerForm>
<div class="pageHeader">
	<form rel="pagerForm" method="post" action="${ctx}/work/cardinfo/select" onsubmit="return dwzSearch(this, 'dialog');">
		<div class="searchBar">

			<ul class="searchContent">
				<li><label>车牌号：</label> <input class="textInput" name="search_LIKE_carNo" value="${param.search_LIKE_carNo}" type="text"></li>
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
					<c:if test="${empty param.single}">
						<li>
							<div class="button">
								<div class="buttonContent">
									<button type="button" multLookup="uids" warn="请选择车辆">选择</button>
								</div>
							</div>
						</li>
					</c:if>
					<li>
						<div class="button">
							<div class="buttonContent">
								<button type="button" onclick="javascript:$.bringBack({id:'',userNo:'', carNo:'',owner:''})">清空</button>
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
				<th width="30"><c:if test="${empty param.single}">
						<input type="checkbox" class="checkboxCtrl" cacheElementId="pagerForm" cacheName="uids" group="ids" />
					</c:if></th>				 
				<th <vsc:orderField name="carNo"/>>车牌号码</th>
				<th <vsc:orderField name="owner"/>>车主</th>
			   <th <vsc:orderField name="userNo"/>>车辆编号</th>
				<th <vsc:orderField name="expireDate"/>>有效期</th>  
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.content}" var="item" varStatus="index">
				<tr>
					<td><c:if test="${empty param.single}">
							<input <vsc:defaultIfIndexOfParameterValues cacheName="uids" objectToFind="{id:'${item.id}', carNo:'${item.carNo}',owner:'${item.owner}'}"  yesStr="checked=\"checked\""/> onclick="javascript:pagePass(this,'pagerForm','uids')" type="checkbox" name="ids" value="{id:'${item.id}', carNo:'${item.carNo}',owner:'${item.owner}'}" />							 
						</c:if> <c:if test="${not empty param.single}">
							<a title="查找带回" href="javascript:$.bringBack({id:'${item.id}', userNo:'${item.userNo}',carNo:'${item.carNo}',owner:'${item.owner}'})" class="btnSelect">选择</a>
						</c:if></td>
					<td>${item.carNo}</td>
					<td>${item.owner}</td>
					<td>${item.userNo}</td>
					<td><fmt:formatDate value="${item.expireDate}" pattern="yyyy-MM-dd" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<vsc:pagination page="${page}" targetType="dialog" numPerPageOnchange="dwzPageBreak({targetType:'dialog', data:{numPerPage:this.value}})"></vsc:pagination>
	</div>
</div>
