<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>

<div class="pageContent">
	<form method="post" action="${ctx}/work/yudingsetting/${action}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="id" value="${vm.id}">
		<vsc:token tokenName="work.yudingsetting.create"></vsc:token>
		<vsc:callback></vsc:callback>
		<div class="pageFormContent" layoutH="56">
			<table class="formTable">
				<tr>
					<td class="fieldName">周一开放时间:</td> 
					<td class="fieldInput"><label><input type="text" id="mondayStartTime" class="date" dateFmt="HH:mm" readonly="true" name="mondayStartTime" value="${vm.mondayStartTime}" validate="{required:true}" /></label><span for="mondayStartTime" generated="true" style="display: none"
						class="error"></span></td>
					<td class="fieldInput"><label><input type="text" id="mondayEndTime" class="date" dateFmt="HH:mm" readonly="true" name="mondayEndTime" value="${vm.mondayEndTime}" validate="{required:true}" /></label><span for="mondayEndTime" generated="true" style="display: none"
						class="error"></span></td>

				</tr>
				<tr>
					<td class="fieldName">周二开放时间:</td>
					<td class="fieldInput"><label><input type="text" id="tuesdayStartTime" class="date" dateFmt="HH:mm" readonly="true" name="tuesdayStartTime" value="${vm.tuesdayStartTime}" validate="{required:true}" /></label><span for="tuesdayStartTime" generated="true"
						style="display: none" class="error"></span></td>
					<td class="fieldInput"><label><input type="text" id="tuesdayEndTime" class="date" dateFmt="HH:mm" readonly="true" name="tuesdayEndTime" value="${vm.tuesdayEndTime}" validate="{required:true}" /></label><span for="tuesdayEndTime" generated="true" style="display: none"
						class="error"></span></td>
				</tr>
				<tr>
					<td class="fieldName">周三开放时间:</td>
					<td class="fieldInput"><label><input type="text" id="wednesdayStartTime" class="date" dateFmt="HH:mm" readonly="true" name="wednesdayStartTime" value="${vm.wednesdayStartTime}" validate="{required:true}" /></label><span for="wednesdayStartTime" generated="true"
						style="display: none" class="error"></span></td>
					<td class="fieldInput"><label><input type="text" id="wednesdayEndTime" class="date" dateFmt="HH:mm" readonly="true" name="wednesdayEndTime" value="${vm.wednesdayEndTime}" validate="{required:true}" /></label><span for="wednesdayEndTime" generated="true"
						style="display: none" class="error"></span></td>
				</tr>
				<tr>
					<td class="fieldName">周四开放时间:</td>
					<td class="fieldInput"><label><input type="text" id="thursdayStartTime" class="date" dateFmt="HH:mm" readonly="true" name="thursdayStartTime" value="${vm.thursdayStartTime}" validate="{required:true}" /></label><span for="thursdayStartTime" generated="true"
						style="display: none" class="error"></span></td>
					<td class="fieldInput"><label><input type="text" id="thursdayEndTime" class="date" dateFmt="HH:mm" readonly="true" name="thursdayEndTime" value="${vm.thursdayEndTime}" validate="{required:true}" /></label><span for="thursdayEndTime" generated="true" style="display: none"
						class="error"></span></td>
				</tr>
				<tr>
					<td class="fieldName">周五开放时间:</td>
					<td class="fieldInput"><label><input type="text" id="fridayStartTime" class="date" dateFmt="HH:mm" readonly="true" name="fridayStartTime" value="${vm.fridayStartTime}" validate="{required:true}" /></label><span for="fridayStartTime" generated="true" style="display: none"
						class="error"></span></td>
					<td class="fieldInput"><label><input type="text" id="fridayEndTime" class="date" dateFmt="HH:mm" readonly="true" name="fridayEndTime" value="${vm.fridayEndTime}" validate="{required:true}" /></label><span for="fridayEndTime" generated="true" style="display: none"
						class="error"></span></td>
				</tr>
				<tr>
					<td class="fieldName">周六开放时间:</td>
					<td class="fieldInput"><label><input type="text" id="saturdayStartTime" class="date" dateFmt="HH:mm" readonly="true" name="saturdayStartTime" value="${vm.saturdayStartTime}" validate="{required:true}" /></label><span for="saturdayStartTime" generated="true"
						style="display: none" class="error"></span></td>
					<td class="fieldInput"><label><input type="text" id="saturdayEndTime" class="date" dateFmt="HH:mm" readonly="true" name="saturdayEndTime" value="${vm.saturdayEndTime}" validate="{required:true}" /></label><span for="saturdayEndTime" generated="true" style="display: none"
						class="error"></span></td>
				</tr>
				<tr>
					<td class="fieldName">周日开放时间:</td>
					<td class="fieldInput"><label><input type="text" id="sundayStartTime" class="date" dateFmt="HH:mm" readonly="true" name="sundayStartTime" value="${vm.sundayStartTime}" validate="{required:true}" /></label><span for="sundayStartTime" generated="true" style="display: none"
						class="error"></span></td>
					<td class="fieldInput"><label><input type="text" id="sundayEndTime" class="date" dateFmt="HH:mm" readonly="true" name="sundayEndTime" value="${vm.sundayEndTime}" validate="{required:true}" /></label><span for="sundayEndTime" generated="true" style="display: none"
						class="error"></span></td>
				</tr>

				<tr>
					<td class="fieldName">预约偏移时长:</td>
					<td class="fieldInput"><label><input type="text" id="startAddMinutes" name="startAddMinutes" value="${vm.startAddMinutes}" validate="{required:true}" />(分钟)</label><span for="startAddMinutes" generated="true"
						style="display: none" class="error"></span></td>
					<td class="fieldInput"><label><input type="text" id="endAddMinutes" name="endAddMinutes" value="${vm.endAddMinutes}" validate="{required:true}" />(分钟)</label><span for="endAddMinutes" generated="true" style="display: none"
						class="error"></span></td>
					<td class="fieldInput"></td>
				</tr>
				<tr>
					<td class="fieldName">预留时长:</td>
					<td class="fieldInput"><label><input type="text" id="lockedMinutes" name="lockedMinutes" value="${vm.lockedMinutes}" validate="{required:true}" />(分钟)</label><span for="lockedMinutes" generated="true" style="display: none"
						class="error"></span></td>
					<td class="fieldInput"></td>
				</tr>
				<tr>
					<td class="fieldName">预留收费:</td>
					<td class="fieldInput"><label><input type="text" id="lockedCost" name="lockedCost" value="${vm.lockedCost}" validate="{required:true}" />(元)</label><span for="lockedCost" generated="true" style="display: none" class="error"></span></td>
					<td class="fieldInput"></td>
				</tr>
				<tr>
					<td class="fieldName">预留计费:</td>
					<td class="fieldInput"><label><input type="text" id="lockedHourCost" name="lockedHourCost" value="${vm.lockedHourCost}" validate="{required:true}" />(元/小时)</label><span for="lockedHourCost" generated="true" style="display: none"
						class="error"></span></td>
					<td class="fieldInput"></td>
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