<%@page import="org.apache.shiro.SecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<div class="pageContent">
	<form method="post" action="${ctx}/work/member/${action}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="id" value="${id}">
		<vsc:token tokenName="work.member.create"></vsc:token>
		<vsc:callback></vsc:callback>
		<div class="pageFormContent" layoutH="56">
			<table class="formTable">
				<tr>
					<td class="fieldName"><span class="required">*</span>登录名：</td>
					<td class="fieldInput"><input name="loginName" alt="请输入用户名" class="required ignoreKeyUp" type="text" size="30" value="${vm.loginName}"
						validate="{required:true,rangelength:[1,24],remote:'${ctx}/sys/user/checkloginname?oldLoginName=${vm.loginName}',messages:{remote:'登录名已存在'}}" /></td>
					<td class="fieldName"><span class="required">*</span>姓名：</td>
					<td class="fieldInput"><input name="name" class="required" type="text" size="30" value="${vm.name}" validate="{required:true,rangelength:[1,30]}" /></td>
				</tr>
				<c:if test="${action=='create'}">
					<tr>
						<td class="fieldName"><span class="required">*</span>密码：</td>
						<td class="fieldInput"><input id="plainPassword" class="required" name="plainPassword" type="password" size="30" value="" validate="{required:true,rangelength:[3,20]}" /></td>


						<td class="fieldName"><span class="required">*</span>密码确认：</td>
						<td class="fieldInput"><input id="confirmPassword" class="required" name="confirmPassword" type="password" size="30" value="" alt="请再次输入密码" validate="{equalTo:'#plainPassword'}" /></td>
					</tr>
				</c:if>
				<tr>
					<td class="fieldName"><span class="required">*</span>使用状态：</td>
					<td class="fieldInput" colspan="3"><label><form:radiobutton path="vm.isEnabled" value="true" validate="{required:true}" /> <s:message code="user.isenabled.true" /></label> <label><form:radiobutton path="vm.isEnabled" value="false" />
							<s:message code="user.isenabled.false" /></label> <span for="isEnabled" generated="true" class="error" style="display: none"></span></td>
				</tr>
				<tr>
					<td class="fieldName"><span class="required">*</span>手机：</td>
					<td class="fieldInput"><label> <input type="text" name="mobilePhone"  value="${vm.mobilePhone}" validate="{required:true}" />
					</label><span for="mobilePhone" generated="true" style="display: none" class="error"></span></td>
					<td class="fieldName"><span class="required">*</span> 性别：</td>
					<td class="fieldInput" width="35%"><label> <form:radiobutton path="vm.gender" value="0" validate="{required:true}" /> <s:message code="user.gender.0" />
					</label> <label> <form:radiobutton path="vm.gender" value="1" /> <s:message code="user.gender.1" />
					</label> <span style="display: none" class="error" generated="true" for="gender"></span></td>
				</tr>
				 
				<tr>
					<td class="fieldName">来源：</td>
					<td class="fieldInput" colspan="3"><label><input type="text" name="sourceType" value="${vm.sourceType}" /></label></td>

				</tr>
				<tr>
					<td class="fieldName">联系地址：</td>
					<td class="fieldInput" colspan="3"><label><textarea rows="3" cols="60" name="contactAddress">${vm.contactAddress}</textarea> </label></td>

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
