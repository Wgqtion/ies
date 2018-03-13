<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<div class="pageContent">

	<div class="pageFormContent" layoutH="56">
		<table class="viewTable">
			<tbody>
				<tr>
					<td align="right">停车片区:</td>
					<td align="left">${vm.name}</td>
				</tr>
				
				<tr>
					<td align="right">状态:</td>
					<td align="left"><s:message code="parkinglot.isenabled.${vm.isEnabled}"/></td>
				</tr> 
				<tr>
					<td align="right">总车位</td>
					<td align="left">${vm.carNumber}</td>
				</tr>
				<tr>
					<td align="right">空余车位:</td>
					<td align="left">15</td>
				</tr>
				<tr>
					<td align="right">创建时间:</td>
					<td align="left"><fmt:formatDate value='${vm.createDate}'
							pattern='yyyy-MM-dd HH:mm:ss' /></td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="formBar">
		<ul>
			<li>
				<div class="button">
					<div class="buttonContent">
						<button type="button" class="close">关闭</button>
					</div>
				</div>
			</li>
		</ul>
	</div>

</div>