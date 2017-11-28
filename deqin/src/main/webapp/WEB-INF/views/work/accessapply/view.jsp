<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<div class="pageContent">

	<div class="pageFormContent" layoutH="56"> 
		<table class="viewTable">
			<tbody>
				<tr>
					<td width="10%" align="right">CAR_INFO_ID:</td>
					<td align="left">
${vm.carInfoId}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">CAR_NO:</td>
					<td align="left">
${vm.carNo}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">ACCESS_STATUS:</td>
					<td align="left">
${vm.accessStatus}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">CREATE_TIME:</td>
					<td align="left">
<fmt:formatDate value='${vm.createTime}' pattern='yyyy-MM-dd HH:mm:ss'/>					</td>
				</tr>
				<tr>
					<td width="10%" align="right">CREATE_USER_ID:</td>
					<td align="left">
${vm.createUserId}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">APPLY_TIME:</td>
					<td align="left">
<fmt:formatDate value='${vm.applyTime}' pattern='yyyy-MM-dd HH:mm:ss'/>					</td>
				</tr>
				<tr>
					<td width="10%" align="right">APPLY_USER_ID:</td>
					<td align="left">
${vm.applyUserId}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">APPLY_STATE:</td>
					<td align="left">
${vm.applyState}					</td>
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