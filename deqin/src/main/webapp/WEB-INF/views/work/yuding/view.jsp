<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<div class="pageContent">
	<div class="pageFormContent" layoutH="56">
		<table class="viewTable">
			<tbody>
				<tr>
					<td align="right">会员名:</td>
					<td align="left">${vm.user.name}</td>
					<td align="right">车牌号:</td>
					<td align="left">${vm.carNo}</td>				
				</tr>
				<tr><td align="right">预订停车区:</td>
					<td align="left">${vm.parkingLotArea.fullIndexName}</td>
					<td align="right"></td>
					<td align="left"></td>	
				</tr>			
				<tr>
					<td align="right">保留开始时间:</td>
					<td align="left"><fmt:formatDate value='${vm.lockedStartTime}' pattern='yyyy-MM-dd HH:mm' /></td>
				</tr>
				<tr>
					<td align="right">预约时间:</td>
					<td align="left"><fmt:formatDate value='${vm.yuyueTime}' pattern='yyyy-MM-dd HH:mm' /></td>
				</tr>
				<tr>
					<td align="right">提前保留:</td>
					<td align="left">${vm.lockedMinutes}(分钟)</td>
				</tr>
				<tr>
					<td align="right">保留费用:</td>
					<td align="left">${vm.lockedCost}(元)</td>
				</tr>
				<tr>
					<td align="right">使用计费:</td>
					<td align="left">${vm.lockedHourCost}(元/小时)</td>
				</tr>
				<tr>
					<td align="right">申请时间:</td>
					<td align="left"><fmt:formatDate value='${vm.createTime}' pattern='yyyy-MM-dd HH:mm:ss' /></td>
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