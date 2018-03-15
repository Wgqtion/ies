<%@page import="org.apache.shiro.SecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<div class="pageContent">
	<form method="post" action="${ctx}/work/wxUser/${action}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="id" value="${id}">
		<vsc:token tokenName="work.wxUser.create"></vsc:token>
		<vsc:callback></vsc:callback>
		<div class="pageFormContent" layoutH="56">
			<table class="formTable">
				<tr>
					<td class="fieldName"><span class="required">*</span>姓名：</td>
					<td class="fieldInput"><input name="name" class="required" type="text" size="30" value="${vm.name}" validate="{required:true,rangelength:[1,30]}" /></td>
				</tr>
				<tr>
					<td class="fieldName"><span class="required">*</span>手机：</td>
					<td class="fieldInput"><label> <input type="text" name="telphone"  value="${vm.telphone}" validate="{required:true}" />
					</label><span for="telphone" generated="true" style="display: none" class="error"></span></td>
				</tr>
				<tr>
					<td class="fieldName"><span class="required">*</span>车牌号：</td>
					<td class="fieldInput"><label> <input type="text" name="carNo"  value="${vm.carNo}" validate="{required:true}" />
					</label><span for="carNo" generated="true" style="display: none" class="error"></span></td>
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
