<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%> 
<vsc:pagerForm action="#rel#" id="pagerForm" cacheName="selectAuthorityids"></vsc:pagerForm> 
<script>
	function allCheck(e){
		if($(e).is(":checked")){
			$("[name=ids]:checkbox").each(function(){
				if(!$(this).is(":checked")){
					$(this).click();
					$(this).click();
					$(this).attr("checked",true);
				}
			});
		}else{
			$("[name=ids]:checkbox").each(function(){
				if($(this).is(":checked")){
					$(this).click();
					$(this).click();
					$(this).attr("checked",false);
				}
			});
		}
	}
	
</script>
<div class="pageHeader">
	<form rel="pagerForm" method="post" action="${ctx}/sys/authority/select" onsubmit="return dwzSearch(this, 'dialog');">
		<div class="searchBar">
			<ul class="searchContent">
				<li><label>停车场:</label> <input type="text" value="${param['search_LIKE_parkingLot.name']}" name="search_LIKE_name" /></li>
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
								<button type="button" multLookup="orgAuthorityIds" warn="请选择资源">选择</button>
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
	<table class="table" layoutH="118" targetType="dialog" width="100%">
		<thead>
			<tr>
				<th width="30"><input type="checkbox" onclick="allCheck(this);"/></th>
				<th <vsc:orderField name="name"/>>停车场名称</th>
				<th <vsc:orderField name="isEnabled"/>>状态</th>
				<th <vsc:orderField name="createDate"/>>创建时间</th> 
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.content}" var="item" varStatus="index">
				<tr>
					<td><input <vsc:defaultIfIndexOfParameterValues cacheName="orgAuthorityIds" objectToFind="{id:'${item.id}',name:'${item.name}'}"  yesStr="checked=\"checked\""/> onclick="javascript:pagePass(this,'pagerForm','orgAuthorityIds')" type="checkbox" name="ids" value="{id:'${item.id}',name:'${item.name}'}" /></td>
					<td><a href="${ctx}/work/parkinglot/view/${item.id}" target="dialog" title="查看停车场" rel="parkinglot_view">${item.name}</a></td>
					<td><s:message code="parkinglot.isenabled.${item.isEnabled}"/></td>
					<td><fmt:formatDate value='${item.createDate}' pattern='yyyy-MM-dd HH:mm' /></td> 
				</tr>
			</c:forEach>
		</tbody>
	</table> 
	<div class="panelBar">
	<vsc:pagination page="${page}" targetType="dialog" numPerPageOnchange="dwzPageBreak({targetType:'dialog', data:{numPerPage:this.value}})"></vsc:pagination>	 
	</div>
</div>
