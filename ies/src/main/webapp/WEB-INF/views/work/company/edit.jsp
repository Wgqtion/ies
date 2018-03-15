<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>

<div class="pageContent">
	<form method="post" action="${ctx}/work/company/${action}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="id" value="${vm.id}">
		<input type="hidden" name="code" value="${vm.code}">
		<vsc:token tokenName="work.company.create"></vsc:token>
		<vsc:callback></vsc:callback>
		<div class="pageFormContent" layoutH="56">
			<table class="formTable" style="width: 50%;margin:auto">
			
				<tr>
					<td class="fieldName"><label><span class="required">*</span>名称:</label></td>
					<td class="fieldInput"><label><input type="text" id="name" name="name" value="${vm.name}" validate="{required:true}" /></label><span for="name" generated="true" style="display: none" class="error"></span></td>
					
				</tr>
				<tr>
					<td class="fieldName"><label>电话:</label></td>
					<td class="fieldInput"><label><input type="text" id="telephone" name="telephone" value="${vm.telephone}" validate="{required:false}" /></label><span for="telephone" generated="true" style="display: none" class="error"></span></td>
				</tr>
				<tr>
					<td class="fieldName"><label>地址:</label></td>
					<td class="fieldInput"><label><input type="text" id="address" name="address" value="${vm.address}" validate="{required:false}" /></label><span for="address" generated="true" style="display: none" class="error"></span></td>
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