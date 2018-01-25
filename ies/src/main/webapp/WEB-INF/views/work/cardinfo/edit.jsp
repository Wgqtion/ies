<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>

<div class="pageContent">
	<form method="post" action="${ctx}/work/cardinfo/${action}"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="id" value="${id}"/>
		<vsc:token tokenName="work.cardinfo.create"></vsc:token>
		<vsc:callback></vsc:callback>
		<div class="pageFormContent" layoutH="56">
			<table class="formTable">
				

				<tr>
					<td class="fieldName">车主姓名:</td>
					<td class="fieldInput"><label><input type="text"
							id="owner" name="owner" value="${vm.owner}"
							validate="{required:true}" /></label><span for="owner" generated="true"
						style="display: none" class="error"></span></td>
					<td class="fieldName">车牌:</td>
					<td class="fieldInput"><label><input type="text"
							id="carNo" name="carNo" value="${vm.carNo}"
							validate="{required:true}" /></label><span for="carNo" generated="true"
						style="display: none" class="error"></span></td>
				</tr>
				<tr>
					<td class="fieldName">车辆编号:</td>
					<td class="fieldInput"><label><input type="text"
							id="userNo" name="userNo" value="${vm.userNo}"
							validate="{required:true}" /></label><span for="userNo" generated="true"
						style="display: none" class="error"></span></td>
					<td class="fieldName">有效期:</td>
					<td class="fieldInput"><label><input type="text"
							id="expireDate" name="expireDate" class="date"
							value="<fmt:formatDate value='${vm.expireDate}' pattern='yyyy-MM-dd'/>"
							dateFmt="yyyy-MM-dd" readonly="true" validate="{required:true}" /></label><span
						for="expireDate" generated="true" style="display: none"
						class="error"></span></td>
				
					 
				</tr>
				 
				<tr>
					<td class="fieldName"><span class="required">*</span>车辆类型：</td>
					<td class="fieldInput"><label><form:radiobutton
								path="vm.carType" value="1" validate="{required:true}" /><s:message code="cardinfo.carType.1"></s:message></label> <label><form:radiobutton
								path="vm.carType" value="2" /><s:message code="cardinfo.carType.2"></s:message></label><label><form:radiobutton
								path="vm.carType" value="3" /><s:message code="cardinfo.carType.3"></s:message></label><span for="carType"
						generated="true" class="error" style="display: none"></span></td>
						 
				</tr>
				 
				<tr>
					<td class="fieldName">电话:</td>
					<td class="fieldInput"><label><input type="text"
							id="mobile" name="mobile" value="${vm.mobile}"
							validate="{required:true}" /></label><span for="mobile" generated="true"
						style="display: none" class="error"></span></td>
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