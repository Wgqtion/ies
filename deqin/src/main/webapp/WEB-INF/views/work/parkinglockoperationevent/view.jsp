<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<div class="pageContent">

	<div class="pageFormContent" layoutH="56">
		<table class="viewTable">
			<tbody>
				<tr>
					<td align="right">操作类型:</td>
					<td align="left">${vm.eventType}</td>

					<td align="right">来源:</td>
					<td align="left">${vm.sourceType}</td>
				</tr>

				<tr>
					<td align="right">地锁编号:</td>
					<td align="left">${vm.lockNum}</td>
					<td align="right">操作时间:</td>
					<td align="left"><fmt:formatDate value='${vm.reportedTime}' pattern='yyyy-MM-dd HH:mm:ss' /></td>


				</tr>
				<tr>


					<td align="right">设备编号:</td>
					<td align="left">${vm.deviceNum}</td>
					<td align="right">IP地址:</td>
					<td align="left">${vm.ipAddress}</td>
				</tr>
				<tr>


					<td align="right">操作说明:</td>
					<td align="left">${vm.message}</td>
				</tr>
				<tr>
					<td align="right">操作结果:</td>
					<td align="left">${vm.resultType}</td>

					<td align="right">操作人:</td>
					<td align="left">${vm.user.name}</td>
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