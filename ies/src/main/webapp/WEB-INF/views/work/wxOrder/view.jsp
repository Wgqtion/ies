<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<div class="pageContent">

	<div class="pageFormContent" layoutH="56">
		<table class="viewTable">
			<tbody>
				<tr>
					<td align="right">订单号:</td>
					<td align="left">${vm.code}</td>
					<td align="right">用户:</td>
					<td align="left">${vm.wxUser.name}</td>
				</tr>
				<tr>
					<td align="right">状态:</td>
					<td align="left"><s:message code="wxOrder.status.${vm.status}" /></td>
					<td align="right">总费用:</td>
					<td align="left">${vm.totalFee}</td>
				</tr>
				<tr>
					<td align="right">创建时间:</td>
					<td align="left"><fmt:formatDate value='${ vm.createTime}' pattern='yyyy-MM-dd HH:mm:ss' /></td>
					<td align="right">支付时间:</td>
					<td align="left"><fmt:formatDate value='${ vm.payTime}' pattern='yyyy-MM-dd HH:mm:ss' /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<c:forEach items="${vm.wxCores}" var="wxCore">
					<tr>
						<td colspan="4">${wxCore.typeStr}明细:</td>
					</tr>
					<tr>
						<td align="right">使用车位:</td>
						<td align="left" colspan="3">${wxCore.parkingLock.parkingGarage.name}</td>
					</tr>	
					<tr>
						<td align="right">开始时间:</td>
						<td align="left"><fmt:formatDate value='${ wxCore.startTime}' pattern='yyyy-MM-dd HH:mm:ss' /></td>
						<td align="right">结束时间:</td>
						<td align="left"><fmt:formatDate value='${ wxCore.endTime}' pattern='yyyy-MM-dd HH:mm:ss' /></td>
					</tr>
					<tr>
						<td align="right">是否免费:</td>
						<td align="left"><s:message code="wxCore.isFree.${wxCore.isFree}"/></td>
						<td align="right">费用:</td>
						<td align="left">${wxCore.amount}</td>
					</tr>
				</c:forEach>
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