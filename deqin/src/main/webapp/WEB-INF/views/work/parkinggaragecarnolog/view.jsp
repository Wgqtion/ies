<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<div class="pageContent">

	<div class="pageFormContent" layoutH="56"> 
		<table class="viewTable">
			<tbody>
				<tr>
					<td width="10%" align="right">PARKING_NAME:</td>
					<td align="left">
${vm.parkingName}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">CAMERA_IP:</td>
					<td align="left">
${vm.cameraIp}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">STATUS:</td>
					<td align="left">
${vm.status}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">CAR_NO:</td>
					<td align="left">
${vm.carNo}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">INTIME:</td>
					<td align="left">
<fmt:formatDate value='${vm.intime}' pattern='yyyy-MM-dd HH:mm:ss'/>					</td>
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