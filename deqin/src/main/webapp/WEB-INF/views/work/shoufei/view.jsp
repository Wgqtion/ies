<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<div class="pageContent">

	<div class="pageFormContent" layoutH="56"> 
		<table class="viewTable">
			<tbody>
				<tr>
					<td width="10%" align="right">CARD_TYPE_ID:</td>
					<td align="left">
${vm.cardTypeId}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">PARKING_LOT_ID:</td>
					<td align="left">
${vm.parkingLotId}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">DAY_TIME:</td>
					<td align="left">
${vm.dayTime}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">NIGHT_TIME:</td>
					<td align="left">
${vm.nightTime}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">DAY_HOUR_MONEY:</td>
					<td align="left">
${vm.dayHourMoney}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">NIGHT_HOUR_MONEY:</td>
					<td align="left">
${vm.nightHourMoney}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">DAY_MAX_MONEY:</td>
					<td align="left">
${vm.dayMaxMoney}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">NIGHT_MAX_MONEY:</td>
					<td align="left">
${vm.nightMaxMoney}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">EVERYDAY_MIANFEI_TIME:</td>
					<td align="left">
${vm.everydayMianfeiTime}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">LASTTIME:</td>
					<td align="left">
<fmt:formatDate value='${vm.lasttime}' pattern='yyyy-MM-dd HH:mm:ss'/>					</td>
				</tr>
				<tr>
					<td width="10%" align="right">CSHOUFEI_ID:</td>
					<td align="left">
${vm.cshoufeiId}					</td>
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