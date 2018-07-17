<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>

<div class="pageContent">
	<form method="post" action="${ctx}/work/parkingCamera/${action}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="id" value="${id}">
		<vsc:token tokenName="work.parkingCamera.create"></vsc:token>
		<vsc:callback></vsc:callback>
		<div class="pageFormContent" layoutH="56">
			<table class="formTable">
				<tr>
					<td class="fieldName"><label><span class="required">*</span>关联车位:</label></td>
					<td class="fieldInput">
					<label>
					<input name="parkingGarageGroup.id" value="${vm.parkingGarage.id}" type="hidden" />
					<input validate="{required:true}" readonly="readonly" id="parkingGarageGroup_name" name="parkingGarageGroup.name" value="${vm.parkingGarage.name}" type="text"/> 
					<a class="btnLook" rel="parkinglock_parkingGarage_select" title="选择关联车位" href="${ctx}/work/parkinggarage/select?single=true" lookupGroup="parkingGarageGroup">查找带回</a> <span class="info">(选择关联车位)</span>
					</label><span for="parkingGarageGroup_name" generated="true" style="display: none" class="error"></span>
					</td>
					<td class="fieldName"><label><span class="required">*</span>相机IP:</label></td>
					<td class="fieldInput"><label><input type="text" id="cameraIp" name="cameraIp" value="${vm.cameraIp}" validate="{required:true}" /></label><span for="cameraIp" generated="true" style="display: none" class="error"></span></td>
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