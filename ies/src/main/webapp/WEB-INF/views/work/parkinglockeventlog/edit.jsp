<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>

<div class="pageContent">
	<form method="post" action="${ctx}/work/parkinglockeventlog/${action}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="id" value="${id}">
		<vsc:token tokenName="work.parkinglockeventlog.create"></vsc:token>
		<vsc:callback></vsc:callback>
		<div class="pageFormContent" layoutH="56">
			<table class="formTable">
					<tr>
					<td class="fieldName">EVENT_TYPE:</td>
						<td class="fieldInput"><label><input type="text" id="eventType" name="eventType" value="${vm.eventType}" validate="{required:true}"/></label><span for="eventType" generated="true" style="display: none" class="error"></span>
						</td>
					</tr>
					<tr>
					<td class="fieldName">SOURCE_TYPE:</td>
						<td class="fieldInput"><label><input type="text" id="sourceType" name="sourceType" value="${vm.sourceType}" validate="{required:true}"/></label><span for="sourceType" generated="true" style="display: none" class="error"></span>
						</td>
					</tr>
					<tr>
					<td class="fieldName">PARKING_LOCK_ID:</td>
						<td class="fieldInput"><label><input type="text" id="parkingLockId" name="parkingLockId" value="${vm.parkingLockId}" validate="{required:true}"/></label><span for="parkingLockId" generated="true" style="display: none" class="error"></span>
						</td>
					</tr>
					<tr>
					<td class="fieldName">REPORTED_TIME:</td>
						<td class="fieldInput"><label><input type="text" id="reportedTime" name="reportedTime" class="date" value="<fmt:formatDate value='${vm.reportedTime}' pattern='yyyy-MM-dd'/>" dateFmt="yyyy-MM-dd" readonly="true" validate="{required:true}"/></label><span for="reportedTime" generated="true" style="display: none" class="error"></span>
						</td>
					</tr>
					<tr>
					<td class="fieldName">CREATE_TIME:</td>
						<td class="fieldInput"><label><input type="text" id="createTime" name="createTime" class="date" value="<fmt:formatDate value='${vm.createTime}' pattern='yyyy-MM-dd'/>" dateFmt="yyyy-MM-dd" readonly="true" validate="{required:true}"/></label><span for="createTime" generated="true" style="display: none" class="error"></span>
						</td>
					</tr>
					<tr>
					<td class="fieldName">MESSAGE:</td>
						<td class="fieldInput"><label><input type="text" id="message" name="message" value="${vm.message}" validate="{required:true}"/></label><span for="message" generated="true" style="display: none" class="error"></span>
						</td>
					</tr>
					<tr>
					<td class="fieldName">LOCK_NUM:</td>
						<td class="fieldInput"><label><input type="text" id="lockNum" name="lockNum" value="${vm.lockNum}" validate="{required:true}"/></label><span for="lockNum" generated="true" style="display: none" class="error"></span>
						</td>
					</tr>
					<tr>
					<td class="fieldName">DEVICE_NUM:</td>
						<td class="fieldInput"><label><input type="text" id="deviceNum" name="deviceNum" value="${vm.deviceNum}" validate="{required:true}"/></label><span for="deviceNum" generated="true" style="display: none" class="error"></span>
						</td>
					</tr>
					<tr>
					<td class="fieldName">IP_ADDRESS:</td>
						<td class="fieldInput"><label><input type="text" id="ipAddress" name="ipAddress" value="${vm.ipAddress}" validate="{required:true}"/></label><span for="ipAddress" generated="true" style="display: none" class="error"></span>
						</td>
					</tr>
			</table>
		</div>
		<div class="formBar">
			<ul> 
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>