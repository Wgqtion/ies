<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<div class="pageContent">

	<div class="pageFormContent" layoutH="56">
		<table class="viewTable">
			<tbody>
				<tr>
					<td width="25%" align="right">车牌号:</td>
					<td align="left">${vm.inPlateNo}</td>
					<td align="right">车辆编号:</td>
					<td align="left">${vm.inChargeUser}</td>
				</tr>
				<tr>
				<tr>
					<td align="right">停车位编号:</td>
					<td align="left" colspan="3">${vm.parkingGarage.name}</td>
				</tr>
				<tr>
					<td align="right">进口:</td>
					<td align="left">${vm.inDoor.name}</td>
					<td align="right">进场时间:</td>
					<td align="left"><fmt:formatDate value='${vm.inTime}' pattern='yyyy-MM-dd HH:mm' /></td>

				</tr>

				<tr>
					<td align="right">出口:</td>
					<td align="left">${vm.outDoor.name}</td>
					<td align="right">出场时间:</td>
					<td align="left"><fmt:formatDate value='${vm.outTime}' pattern='yyyy-MM-dd HH:mm' /></td>
				</tr>
				<tr>
					<td align="right">车位图片查看:</td>
					<td align="left" colspan="3"></td>
				</tr>
				<tr>
					<td align="right">视屏记录:</td>
					<td align="left" colspan="3"></td>
				</tr>
				<tr>
					<td align="right">车辆路径日志:</td>
					<td align="left" colspan="3"></td>
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