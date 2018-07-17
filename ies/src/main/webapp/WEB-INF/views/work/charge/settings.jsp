<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>

<div class="pageContent">
	<form method="post" action="${ctx}/work/charge/settings/${action}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="id" value="${id}">
		<vsc:token tokenName="work.charge.create"></vsc:token>
		<vsc:callback></vsc:callback>
		<div class="pageFormContent" layoutH="56">
			<table class="formTable">
				<tr>
					<td class="fieldName">名称:</td>
					<td class="fieldInput"><label><input type="text" id="name" name="name" value="${vm.name}" validate="{required:true}" /></label><span for="name" generated="true" style="display: none" class="error"></span></td>
					<td class="fieldName">计费类型:</td>
					<td class="fieldInput">
						<label><select class="combox" name="chargeStandard" selectedValue="${vm.chargeStandard}"  dataUrl="${ctx}/static/js/data/chargesSettings_chargeStandard.json">
							<vsc:headoption></vsc:headoption>
						</select></label>
						<%--<label><form:radiobutton path="vm.garageType" value="0" validate="{required:true}" /> <s:message code="parkinggarage.garageType.0" /> </label> <label> <form:radiobutton path="vm.garageType" value="1" /> <s:message
								code="parkinggarage.garageType.1" />
					</label>--%>
						<span for="chargeStandard" generated="true" style="display: none" class="error"></span></td>
				</tr>
				<tr>
					<td class="fieldName">按次收费单价:</td>
					<td class="fieldInput" colspan="3"><label><input type="text" id="unitPrice" name="unitPrice" value="${vm.unitPrice}" validate="{required:true}" /></label><span for="unitPrice" generated="true" style="display: none" class="error"></span></td>
				</tr>
				<tr>
					<td class="fieldName">按时收费时间单位:</td>
					<td class="fieldInput"><label><input type="text" id="priceTimeString" name="priceTimeString" value="${vm.priceTimeString}" validate="{required:true}" /></label><span for="priceTimeString" generated="true" style="display: none" class="error"></span></td>
					<td class="fieldName">优惠减免费用:</td>
					<td class="fieldInput"><label><input type="text" id="privilegeFee" name="privilegeFee" value="${vm.privilegeFee}" validate="{required:true}" /></label><span for="privilegeFee" generated="true" style="display: none" class="error"></span></td>
				</tr>
				<tr>
					<td class="fieldName">最高费用:</td>
					<td class="fieldInput"><label><input type="text" id="maxFee" name="maxFee" value="${vm.maxFee}" validate="{required:true}" /></label><span for="maxFee" generated="true" style="display: none" class="error"></span></td>
				</tr>

				<tr>
					<td class="fieldName">备注:</td>
					<td class="fieldInput" colspan="3"><label><textarea name="description" rows="5" cols="80">${vm.description}</textarea> </label><span for="description" generated="true" style="display: none" class="error"></span></td>

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