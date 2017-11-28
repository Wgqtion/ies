<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>

<div class="pageContent">
	<form method="post" action="${ctx}/work/parkinggaragecarnolog/${action}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="id" value="${id}">
		<vsc:token tokenName="work.parkinggaragecarnolog.create"></vsc:token>
		<vsc:callback></vsc:callback>
		<div class="pageFormContent" layoutH="56">
			<table class="formTable">
				<tr>
					<td class="fieldName">PARKING_NAME:</td>
					<td class="fieldInput"><label><input type="text" id="parkingName" name="parkingName" value="${vm.parkingName}" validate="{required:true}"/></label><span for="parkingName" generated="true" style="display: none" class="error"></span></td>
				</tr>
				<tr>
					<td class="fieldName">CAMERA_IP:</td>
					<td class="fieldInput"><label><input type="text" id="cameraIp" name="cameraIp" value="${vm.cameraIp}" validate="{required:true}"/></label><span for="cameraIp" generated="true" style="display: none" class="error"></span></td>
				</tr>
				<tr>
					<td class="fieldName">STATUS:</td>
					<td class="fieldInput"><label><input type="text" id="status" name="status" value="${vm.status}" validate="{required:true}"/></label><span for="status" generated="true" style="display: none" class="error"></span></td>
				</tr>
				<tr>
					<td class="fieldName">CAR_NO:</td>
					<td class="fieldInput"><label><input type="text" id="carNo" name="carNo" value="${vm.carNo}" validate="{required:true}"/></label><span for="carNo" generated="true" style="display: none" class="error"></span></td>
				</tr>
				<tr>
					<td class="fieldName">INTIME:</td>
					<td class="fieldInput"><label><input type="text" id="intime" name="intime" class="date" value="<fmt:formatDate value='${vm.intime}' pattern='yyyy-MM-dd'/>" dateFmt="yyyy-MM-dd" readonly="true" validate="{required:true}"/></label><span for="intime" generated="true" style="display: none" class="error"></span></td>
				</tr>
				<tr>
					<td class="fieldName">CREATE_TIME:</td>
					<td class="fieldInput"><label><input type="text" id="createTime" name="createTime" class="date" value="<fmt:formatDate value='${vm.createTime}' pattern='yyyy-MM-dd'/>" dateFmt="yyyy-MM-dd" readonly="true" validate="{required:true}"/></label><span for="createTime" generated="true" style="display: none" class="error"></span></td>
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