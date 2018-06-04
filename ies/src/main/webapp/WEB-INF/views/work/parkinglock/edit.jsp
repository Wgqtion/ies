<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>

<div class="pageContent">
	<form method="post" action="${ctx}/work/parkinglock/${action}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="id" value="${id}">
		<vsc:token tokenName="work.parkinglock.create"></vsc:token>
		<vsc:callback></vsc:callback>
		<div class="pageFormContent" layoutH="56">
			<table class="formTable">
				<tr>
					<td class="fieldName"><label><span class="required">*</span>区域编号:</label></td>
					<td class="fieldInput"><label><input type="text" id="ipAddress" name="ipAddress" value="${vm.ipAddress}" validate="{required:true}" /></label><span for="ipAddress" generated="true" style="display: none" class="error"></span></td>
					<td class="fieldName"><label><span class="required">*</span>地锁编号:</label></td>
					<td class="fieldInput"><label><input type="text" id="lockNum" name="lockNum" value="${vm.lockNum}" validate="{required:true}" /></label><span for="lockNum" generated="true" style="display: none" class="error"></span></td>

				</tr>
				<tr>
					<td class="fieldName"><label><span class="required">*</span>关联车位:</label></td>
					<td class="fieldInput">
					<label>
					<input name="parkingGarageGroup.id" value="${vm.parkingGarage.id}" type="hidden" />
					<input validate="{required:true}" readonly="readonly" id="parkingGarageGroup_name" name="parkingGarageGroup.name" value="${vm.parkingGarage.name}" type="text"/> 
					<a class="btnLook" rel="parkinglock_parkingGarage_select" title="选择关联车位" href="${ctx}/work/parkinggarage/select?single=true" lookupGroup="parkingGarageGroup">查找带回</a> <span class="info">(选择关联车位)</span>
					</label><span for="parkingGarageGroup_name" generated="true" style="display: none" class="error"></span>
					</td>
					<td class="fieldName"><label><span class="required">*</span>使用状态:</label></td>
					<td class="fieldInput"><label><form:radiobutton path="vm.isEnabled" value="false" validate="{required:true}" /> <s:message code="parkinggarage.isenabled.false" /> </label> <label> <form:radiobutton path="vm.isEnabled" value="true" />
							<s:message code="parkinggarage.isenabled.true" />
					</label> <span style="display: none" class="error" generated="true" for="isEnabled"></span></td>
				</tr>
				<tr>
					<td class="fieldName"><label><span class="required">*</span>小程序余位判断:</label></td>
					<td class="fieldInput">
						<label><input name="surplusDetection" type="checkbox" value="1" 
							<c:if test="${fn:contains(vm.surplusDetection,'1')}"> checked="checked" </c:if>
							validate="{required:true}" />超声波</label>
						<label><input name="surplusDetection" type="checkbox" value="2" 
							<c:if test="${fn:contains(vm.surplusDetection,'2')}"> checked="checked" </c:if>
							validate="{required:true}" />地锁开关</label>
					</td>
				</tr>

				<tr>
					<td class="fieldName"><label>备注:</label></td>
					<td class="fieldInput" colspan="3"><label><textarea rows="5" cols="80" name="description">${vm.description}</textarea></label>
					<span for="description" generated="true" style="display: none"	class="error"></span></td>
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