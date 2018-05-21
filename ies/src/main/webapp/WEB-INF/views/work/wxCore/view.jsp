<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<div class="pageContent">

	<div class="pageFormContent" layoutH="56">
		<table class="viewTable">
			<tbody>
				<tr>
					<td align="right">类型:</td>
					<td align="left"><s:message code="wxCore.type.${vm.type}"/></td>
					<td align="right">状态:</td>
					<td align="left"><s:message code="wxCore.status.${vm.status}"/></td>
				</tr>
				<tr>
					<td align="right">场区名称:</td>
					<td align="left">${vm.parkingLock.parkingGarage.parkingLot.name}</td>
					<td align="right">车位名称:</td>
					<td align="left">${vm.parkingLock.parkingGarage.name}</td>
				</tr>
				<tr>
					<td align="right">用户:</td>
					<td align="left">${vm.wxUser.name}</td>
					<td align="right">费用:</td>
					<td align="left">${vm.amount}</td>
				</tr>
				<tr>
					<td align="right">订单号:</td>
					<td align="left">${vm.wxOrder.code}</td>
					<td align="right">是否免费:</td>
					<td align="left"><s:message code="wxCore.isFree.${vm.isFree}"/></td>
				</tr>
				<tr>
					<td align="right">开始时间:</td>
					<td align="left"><fmt:formatDate value='${ vm.startTime}' pattern='yyyy-MM-dd HH:mm:ss' /></td>
					<td align="right">结束时间:</td>
					<td align="left"><fmt:formatDate value='${ vm.endTime}' pattern='yyyy-MM-dd HH:mm:ss' /></td>
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