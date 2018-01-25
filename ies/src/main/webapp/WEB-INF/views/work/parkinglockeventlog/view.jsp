<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<div class="pageContent">
	<div class="pageFormContent" layoutH="56">
		<table class="viewTable">
			<tbody>
				<tr>
					<td  align="right">事件类型:</td>
					<td align="left">${vm.eventType}</td>
				</tr> 
				<tr>
					<td  align="right">地锁编号:</td>
					<td align="left">${vm.parkingLock.lockNum}</td>
				</tr>
				<tr>
					<td  align="right">上报时间:</td>
					<td align="left"><fmt:formatDate value='${vm.reportedTime}' pattern='yyyy-MM-dd HH:mm:ss' /></td>
				</tr>
				<tr>
					<td  align="right">创建时间:</td>
					<td align="left"><fmt:formatDate value='${vm.createTime}' pattern='yyyy-MM-dd HH:mm:ss' /></td>
				</tr>
					<tr>
					<td  align="right">设备状态和电量:</td>
					<td align="left">${vm.state}</td>
				</tr>
					<tr>
					<td  align="right">事件说明:</td>
					<td align="left">${vm.message}</td>
				</tr> 
				<tr>
					<td  align="right">地锁编号:</td>
					<td align="left">${vm.lockNum}</td>
				</tr>
				<tr>
					<td  align="right">设备编号:</td>
					<td align="left">${vm.deviceNum}</td>
				</tr>
				<tr>
					<td  align="right">区域编号:</td>
					<td align="left">${vm.ipAddress}</td>
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