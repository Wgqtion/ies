<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<div class="pageContent">

	<div class="pageFormContent" layoutH="56">
		<table class="viewTable">
			<tbody>
				<tr>
					<td align="right">区域编号:</td>
					<td align="left">${vm.ipAddress}</td>
					<td align="right">地锁编号:</td>
					<td align="left">${vm.lockNum}</td>
				</tr>
				
				<tr>
					<td align="right">使用状态:</td>
					<td align="left"><s:message code="parkinggarage.isenabled.${vm.isEnabled}" /></td>
					<td align="right">永久状态:</td>
					<td align="left">${vm.isForeverOpenClose}</td>
				</tr>
				<tr>
					<td align="right">所属停车片区:</td>
					<td align="left">${vm.parkingGarage.parkingLotArea.name}</td>
					<td align="right">关联车位:</td>
					<td align="left">${vm.parkingGarage.name}</td>
				</tr>
				<tr>
					<td align="right">在线状态:</td>
					<td align="left">${vm.isOnline}</td>
					<td align="right">是否有车:</td>
					<td align="left">${vm.isCarOn}</td>
				</tr>				
				<tr>
					<td align="right">开关状态:</td>
					<td align="left">${vm.isOpen}</td>
					<td align="right">余位判断:</td>
					<td align="left">
						<input disabled="disabled" name="surplusDetections" type="checkbox" value="1" 
							<c:if test="${fn:contains(vm.surplusDetection,'1')}"> checked="checked" </c:if>
							validate="{required:true}" />超声波
						<input disabled="disabled" name="surplusDetections" type="checkbox" value="2" 
							<c:if test="${fn:contains(vm.surplusDetection,'2')}"> checked="checked" </c:if>
							validate="{required:true}" />地锁开关
					</td>
				</tr>		
				<tr>
					<td align="right">备注:</td>
					<td align="left"  colspan="3">${vm.description}</td>
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