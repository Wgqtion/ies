<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<div class="pageContent">

	<div class="pageFormContent" layoutH="56"> 
		<table class="viewTable">
			<tbody>
				<tr>
					<td width="10%" align="right">MONDAY_START_TIME:</td>
					<td align="left">
${vm.mondayStartTime}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">TUESDAY_START_TIME:</td>
					<td align="left">
${vm.tuesdayStartTime}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">WEDNESDAY_START_TIME:</td>
					<td align="left">
${vm.wednesdayStartTime}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">THURSDAY_START_TIME:</td>
					<td align="left">
${vm.thursdayStartTime}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">FRIDAY_START_TIME:</td>
					<td align="left">
${vm.fridayStartTime}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">SATURDAY_START_TIME:</td>
					<td align="left">
${vm.saturdayStartTime}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">SUNDAY_START_TIME:</td>
					<td align="left">
${vm.sundayStartTime}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">MONDAY_END_TIME:</td>
					<td align="left">
${vm.mondayEndTime}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">TUESDAY_END_TIME:</td>
					<td align="left">
${vm.tuesdayEndTime}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">WEDNESDAY_END_TIME:</td>
					<td align="left">
${vm.wednesdayEndTime}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">THURSDAY_END_TIME:</td>
					<td align="left">
${vm.thursdayEndTime}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">FRIDAY_END_TIME:</td>
					<td align="left">
${vm.fridayEndTime}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">SATURDAY_END_TIME:</td>
					<td align="left">
${vm.saturdayEndTime}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">SUNDAY_END_TIME:</td>
					<td align="left">
${vm.sundayEndTime}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">START_ADD_MINUTES:</td>
					<td align="left">
${vm.startAddMinutes}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">END_ADD_MINUTES:</td>
					<td align="left">
${vm.endAddMinutes}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">LOCKED_MINUTES:</td>
					<td align="left">
${vm.lockedMinutes}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">LOCKED_COST:</td>
					<td align="left">
${vm.lockedCost}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">LOCKED_HOUR_COST:</td>
					<td align="left">
${vm.lockedHourCost}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">CREATE_TIME:</td>
					<td align="left">
<fmt:formatDate value='${vm.createTime}' pattern='yyyy-MM-dd HH:mm:ss'/>					</td>
				</tr>
				<tr>
					<td width="10%" align="right">LASTTIME:</td>
					<td align="left">
${vm.lasttime}					</td>
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