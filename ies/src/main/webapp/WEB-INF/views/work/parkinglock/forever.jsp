<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>

<div class="pageContent">
	<form method="post" action="${ctx}/work/parkinglock/forever" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="id" value="${id}">
		<vsc:token tokenName="work.parkinglock.forever.create"></vsc:token>
		<vsc:callback></vsc:callback>
		<div class="pageFormContent" layoutH="56">
			<table class="formTable">
				<tr>
					<td class="fieldName">地锁编号:</td>
					<td class="fieldInput"><label>${vm.lockNum}</td>
					<td class="fieldName">设备编号:</td>
					<td class="fieldInput"><label>${vm.deviceNum}</label></td>

				</tr>
				<tr>
					<td class="fieldName">IP地址:</td>
					<td class="fieldInput"><label>${vm.ipAddress}</label></td>
					<td class="fieldName">关联车位:</td>
					<td class="fieldInput"><label>${vm.parkingGarage.name}</label></td>
				</tr>
				<tr>
					<td class="fieldName">当前永久状态</td>
					<td class="fieldInput"><label><s:message code="parkinglock.isForeverOpenClose.${vm.isForeverOpenClose}" /></label></td>
					<td class="fieldName"></td>
					<td class="fieldInput"></td>
				</tr>
				<tr>
					<td class="fieldName">更新永久状态:</td>
					<td class="fieldInput" colspan="3">
					<label><input type="radio" name="foreverOpenClose" value="0" validate="{required:true}" /> <s:message code="parkinglock.isForeverOpenClose.0" /> </label>
					<label><input type="radio" name="foreverOpenClose" value="1" /> <s:message code="parkinglock.isForeverOpenClose.1" /></label> 
					<label><input type="radio" name="foreverOpenClose" value="2" /> <s:message code="parkinglock.isForeverOpenClose.2" /></label> 
					<span style="display: none" class="error" generated="true" for="foreverOpenClose"></span></td>
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