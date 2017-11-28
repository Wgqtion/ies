<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>

<div class="pageContent">
	<form method="post" action="${ctx}/work/parkinglock/open" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="id" value="${id}">
		<vsc:token tokenName="work.parkinglock.open.create"></vsc:token>
		<vsc:callback></vsc:callback>
		<div class="pageFormContent" layoutH="56">
			<table class="formTable">
				<tr>
					<td class="fieldName"><label>地锁编号:</label></td>
					<td class="fieldInput"><label>${vm.lockNum}</td>
					<td class="fieldName"><label>设备编号:</label></td>
					<td class="fieldInput"><label>${vm.deviceNum}</label></td>

				</tr>
				<tr>
					<td class="fieldName"><label>IP地址:</label></td>
					<td class="fieldInput"><label>${vm.ipAddress}</label></td>
					<td class="fieldName"><label>关联车位:</label></td>
					<td class="fieldInput"><label>${vm.parkingGarage.name}</label></td>
				</tr>
				<tr>
					<td class="fieldName"><label>开关设置:</label></td>
					<td class="fieldInput" colspan="3">
					<label><input type="radio" name="openClose" value="0" validate="{required:true}"/> <s:message code="parkinglockoperationevent.eventtype.0" /> </label>
					<label><input type="radio" name="openClose" value="1" /><s:message code="parkinglockoperationevent.eventtype.1" /></label> 
					  
					<span style="display: none" class="error" generated="true" for="openClose"></span></td>
				</tr>

				 
			</table>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">保存</button>
						</div>
					</div></li>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" class="close">取消</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>