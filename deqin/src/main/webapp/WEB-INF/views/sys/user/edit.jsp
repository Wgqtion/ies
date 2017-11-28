<%@page import="org.apache.shiro.SecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<script src="${ctx}/static/js/business/sys/user/edit.js" type="text/javascript"></script>
<div class="pageContent">
	<form method="post" action="${ctx}/sys/user/${action}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="id" value="${id}">
		<input type="hidden" name="userType" value="0">
		<vsc:token tokenName="sys.user.create"></vsc:token>
		<vsc:callback navTabId="sys_user"></vsc:callback>
		<div class="pageFormContent" layoutH="56">
			<dl>
				<dt>登录名：</dt>
				<dd>
					<input name="loginName" alt="请输入用户名" class="required ignoreKeyUp" type="text" size="30" value="${vm.loginName}"
						validate="{required:true,rangelength:[1,24],remote:'${ctx}/sys/user/checkloginname?oldLoginName=${vm.loginName}',messages:{remote:'登录名已存在'}}" />
				</dd>
			</dl>
			<dl>
				<dt>姓名：</dt>
				<dd>
					<input name="name" class="required" type="text" size="30" value="${vm.name}" validate="{required:true,rangelength:[1,30]}" />
				</dd>
			</dl>
			<c:if test="${action=='create'}">
				<dl>
					<dt>密码：</dt>
					<dd>
						<input id="plainPassword" class="required" name="plainPassword" type="password" size="30" value="" validate="{required:true,rangelength:[3,20]}" />
					</dd>
				</dl>

				<dl>
					<dt>密码确认：</dt>
					<dd>
						<input id="confirmPassword" class="required" name="confirmPassword" type="password" size="30" value="" alt="请再次输入密码" validate="{equalTo:'#plainPassword'}" />
					</dd>
				</dl>
			</c:if>
					
			<dl class="nowrap">
				<dt>使用状态：</dt>
				<dd>
					<label><form:radiobutton path="vm.isEnabled" value="true" validate="{required:true}" />
					 <s:message code="user.isenabled.true" /></label> 
					 <label><form:radiobutton path="vm.isEnabled" value="false" /> 
					 <s:message code="user.isenabled.false" /></label> 
					 <span for="isEnabled" generated="true" class="error" style="display: none"></span>
				</dd>
			</dl>   
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
