<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<div class="pageContent">

	<div class="pageFormContent" layoutH="56"> 
		<table class="viewTable">
			<tbody>
				<tr>
					<td width="10%" align="right">CARD_TYPE:</td>
					<td align="left">
${vm.cardType}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">NAME:</td>
					<td align="left">
${vm.name}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">CARD_NUMBER:</td>
					<td align="left">
${vm.cardNumber}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">MOD:</td>
					<td align="left">
${vm.cmod}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">NEED_REL:</td>
					<td align="left">
${vm.needRel}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">DCAR:</td>
					<td align="left">
${vm.dcar}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">CAN_SEARCH:</td>
					<td align="left">
${vm.canSearch}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">DAY_STOP_MF:</td>
					<td align="left">
${vm.dayStopMf}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">NIGHT_STOP_MF:</td>
					<td align="left">
${vm.nightStopMf}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">YEAR:</td>
					<td align="left">
${vm.year}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">FEE:</td>
					<td align="left">
${vm.fee}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">XM_IDS:</td>
					<td align="left">
${vm.xmIds}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">BUBAN:</td>
					<td align="left">
${vm.buban}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">BIANGENG:</td>
					<td align="left">
${vm.biangeng}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">LASTTIME:</td>
					<td align="left">
${vm.lasttime}			</td>
				</tr>
				<tr>
					<td width="10%" align="right">STATUS:</td>
					<td align="left">
${vm.status}					</td>
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