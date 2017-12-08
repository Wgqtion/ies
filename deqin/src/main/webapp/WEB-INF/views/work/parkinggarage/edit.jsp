<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>

<div class="pageContent">
	<form method="post" action="${ctx}/work/parkinggarage/${action}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="id" value="${id}">
		<vsc:token tokenName="work.parkinggarage.create"></vsc:token>
		<vsc:callback></vsc:callback>
		<div class="pageFormContent" layoutH="56">
			<table class="formTable">
				<tr>
					<td class="fieldName">所属停车区:</td>
					<td class="fieldInput" colspan="3"><label>
					<input name="parkinglotareaGroup.id" value="${vm.parkingLotArea.id}" type="hidden" />
					<input validate="{required:true}" id="parkinglotareaGroup_name" name="parkinglotareaGroup.name" value="${vm.parkingLotArea.name}" type="text"/> 
					<a class="btnLook" rel="parkinggarage_parkinglotarea_select" title="选择车位所属停车区" href="${ctx}/work/parkinglotarea/select?single=true&search_EQ_isEnabled=1" lookupGroup="parkinglotareaGroup">查找带回</a> <span class="info">(选择停车片区)</span>
					</label><span for="parkinglotareaGroup_name" generated="true" style="display: none" class="error"></span></td>
				</tr>
				<tr>
					<td class="fieldName">停车位编号:</td>
					<td class="fieldInput"><label><input type="text" id="code" name="code" value="${vm.code}" validate="{required:true}" /></label><span for="code" generated="true" style="display: none" class="error"></span></td>
					<td class="fieldName">停车位名称:</td>
					<td class="fieldInput"><label><input type="text" id="name" name="name" value="${vm.name}" validate="{required:true}" /></label><span for="name" generated="true" style="display: none" class="error"></span></td>
				</tr>
				<tr>
					<td class="fieldName">停车位X坐标:</td>
					<td class="fieldInput"><label><input type="text" id="name" name="xcoordinate" value="${vm.xcoordinate}" validate="{required:true}" /></label><span for="name" generated="true" style="display: none" class="error"></span></td>
					<td class="fieldName">停车位Y坐标:</td>
					<td class="fieldInput"><label><input type="text" id="name" name="ycoordinate" value="${vm.ycoordinate}" validate="{required:true}" /></label><span for="name" generated="true" style="display: none" class="error"></span></td>
				</tr>
				<tr>
					<td class="fieldName">状态:</td>
					<td class="fieldInput" colspan="3"><label><form:radiobutton path="vm.isEnabled" value="false" validate="{required:true}" /> <s:message code="parkinggarage.isenabled.false" /> </label> <label> <form:radiobutton path="vm.isEnabled" value="true" />
							<s:message code="parkinggarage.isenabled.true" />
					</label> <span style="display: none" class="error" generated="true" for="isEnabled"></span></td>
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