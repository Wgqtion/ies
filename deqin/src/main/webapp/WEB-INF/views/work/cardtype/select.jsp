<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<vsc:pagerForm action="#rel#" id="pagerForm" cacheName="hids"></vsc:pagerForm>
<div class="pageHeader">
	<form rel="pagerForm" method="post" action="${ctx}/work/cardtype/select" onsubmit="return dwzSearch(this, 'dialog');">
		<div class="searchBar">
			<ul class="searchContent">
				<li><label>进校证名称:</label> <input type="text" value="${param.search_LIKE_name}" name="search_LIKE_name" /></li>
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
				<th <vsc:orderField name="cardType"/>>时效类型</th>
				<th <vsc:orderField name="name"/>>进校证名称</th>
				<th <vsc:orderField name="cardNumber"/>>首字母编号</th>
				<th <vsc:orderField name="mod"/>>人群</th>
				<th <vsc:orderField name="needRel"/>>关系证明</th>				 
				<th <vsc:orderField name="year"/>>适用年份</th>
				<th <vsc:orderField name="fee"/>>年费标准</th> 
				<th <vsc:orderField name="buban"/>>补办收费倍数</th>
				<th <vsc:orderField name="biangeng"/>>变更收费倍数</th> 
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.content}" var="item" varStatus="index">
				<tr>
					<td><a title="查找带回" href="javascript:$.bringBack({id:'${item.id}', name:'${item.name}'})" class="btnSelect">选择</a></td>
					 
					<td> ${item.cardType}</td>
					<td><a href="${ctx}/work/cardtype/view/${item.id}" target="dialog" title="查看进校证" rel="cardtype_view">${item.name}</a></td>
					<td> ${item.cardNumber}</td>
					<td> ${item.cmod}</td>
					<td> ${item.needRel}</td> 
					<td> ${item.year}</td>
					<td> ${item.fee} </td> 
					<td> ${item.buban} </td>
					<td> ${item.biangeng}</td>
					 
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<vsc:pagination page="${page}" targetType="dialog" numPerPageOnchange="dwzPageBreak({targetType:'dialog', data:{numPerPage:this.value}})"></vsc:pagination>
	</div>
</div>
