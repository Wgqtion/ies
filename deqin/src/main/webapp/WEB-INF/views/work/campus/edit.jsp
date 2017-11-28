<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>

<div class="pageContent">
	<form method="post" action="${ctx}/work/campus/${action}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="id" value="${id}">
		<vsc:token tokenName="work.campus.create"></vsc:token>
		<vsc:callback></vsc:callback>
		<div class="pageFormContent" layoutH="56">
			<table class="formTable">
					<tr>
					<td class="fieldName">校门名称:</td>
						<td class="fieldInput"><label><input type="text" id="name" name="name" value="${vm.name}" validate="{required:true}"/></label><span for="name" generated="true" style="display: none" class="error"></span>
						</td>
					</tr>
					<tr>
					<td class="fieldName">校区(停车场):</td>
						<td class="fieldInput">
						<label>
						<input type="text" id="parkingLotGroup_name" name="parkingLotGroup.name" value="${vm.parkingLot.name}" validate="{required:true}"/>
						<a class="btnLook" rel="campus_parkingLot_select" title="停车场" href="${ctx}/work/parkinglot/select?single=true&search_EQ_isEnabled=1" lookupGroup="parkingLotGroup">查找带回</a> <span class="info">(校区)</span>
						</label><span for="parkingLotGroup_name" generated="true" style="display: none" class="error"></span>
						</td>
					</tr>
					<tr>
					<td class="fieldName"><span class="required">*</span>是否启用:</td>
						<td class="fieldInput" colspan="3"><label><form:radiobutton path="vm.isEnabled" value="true" validate="{required:true}" /> <s:message code="user.isenabled.true" /></label> <label><form:radiobutton path="vm.isEnabled" value="false" />
							<s:message code="user.isenabled.false" /></label> <span for="isEnabled" generated="true" class="error" style="display: none"></span></td>
					</tr>
					<tr>
					<td class="fieldName">备注:</td>
						<td class="fieldInput"><label><input type="text" id="remark" name="remark" value="${vm.remark}" validate="{required:true}"/></label><span for="remark" generated="true" style="display: none" class="error"></span>
						</td>
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