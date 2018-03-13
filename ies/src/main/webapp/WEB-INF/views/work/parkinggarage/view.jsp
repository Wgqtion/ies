<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<div class="pageContent">

	<div class="pageFormContent" layoutH="56">
		<table class="viewTable">
			<tbody>
				<tr>
					<td align="right">停车位名称:</td>
					<td align="left">${vm.name}</td>
					<td align="right">停车位编码:</td>
					<td align="left">${vm.code}</td>
				<tr>
					<td align="right">所属停车场:</td>
					<td align="left">${vm.parkingLotArea.parkingLot.name}</td>
					<td align="right">所属停车区:</td>
					<td align="left">${vm.parkingLotArea.name}</td>
				</tr>	
				<tr>
					<td align="right">状态:</td>
					<td align="left" colspan="3"><s:message code="parkinggarage.isenabled.${vm.isEnabled}" /></td>
				</tr>			
				<tr>
					<td align="right">备注:</td>
					<td align="left" colspan="3">${vm.description}</td>
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