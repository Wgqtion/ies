<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<div class="pageContent">

	<div class="pageFormContent" layoutH="56"> 
		<table class="viewTable">
			<tbody>
				<tr>
					<td width="10%" align="right">CARD_NO:</td>
					<td align="left">
${vm.cardNo}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">CARD_TYPE:</td>
					<td align="left">
${vm.cardType}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">CARD_TYPE_ID:</td>
					<td align="left">
${vm.cardTypeId}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">STATUS:</td>
					<td align="left">
${vm.status}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">OWNER:</td>
					<td align="left">
${vm.owner}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">CAR_NO:</td>
					<td align="left">
${vm.carNo}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">EXPIRE_DATE:</td>
					<td align="left">
<fmt:formatDate value='${vm.expireDate}' pattern='yyyy-MM-dd HH:mm:ss'/>					</td>
				</tr>
				<tr>
					<td width="10%" align="right">YNAME:</td>
					<td align="left">
${vm.yname}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">XM_IDS:</td>
					<td align="left">
${vm.xmIds}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">LASTTIME:</td>
					<td align="left">
${vm.lasttime}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">TYPE_NAME:</td>
					<td align="left">
${vm.typeName}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">TYPE_ID:</td>
					<td align="left">
${vm.typeId}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">USER_NO:</td>
					<td align="left">
${vm.userNo}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">USER_NAME:</td>
					<td align="left">
${vm.userName}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">FRONT_PRINTS:</td>
					<td align="left">
${vm.frontPrints}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">BG_PRINTS:</td>
					<td align="left">
${vm.bgPrints}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">USER_TYPE_ID:</td>
					<td align="left">
${vm.userTypeId}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">IS_PAY:</td>
					<td align="left">
${vm.isPay}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">CAR_TYPE:</td>
					<td align="left">
${vm.carType}					</td>
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
					<td width="10%" align="right">USE_STATUS:</td>
					<td align="left">
${vm.useStatus}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">DEPT_NO:</td>
					<td align="left">
${vm.deptNo}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">DEPT_NAME:</td>
					<td align="left">
${vm.deptName}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">MOBILE:</td>
					<td align="left">
${vm.mobile}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">NOTE:</td>
					<td align="left">
${vm.note}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">USER_ID:</td>
					<td align="left">
${vm.userId}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">TEMP_ID:</td>
					<td align="left">
${vm.tempId}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">OLD_CARD_INFO_ID:</td>
					<td align="left">
${vm.oldCardInfoId}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">CREATE_TIME:</td>
					<td align="left">
<fmt:formatDate value='${vm.createTime}' pattern='yyyy-MM-dd HH:mm:ss'/>					</td>
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