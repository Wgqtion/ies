<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<div class="pageContent">
	<form method="post" action="${ctx}/sys/user/reset" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="id" value="${id}"> 
		<input type="hidden" name="callbackType" value="closeCurrent">
		<input type="hidden" name="navTabId" value="sys_user">
		<div class="pageFormContent" layoutH="56">
			<dl>
				<dt>登录名：</dt>
				<dd>
					${vm.loginName} 
				</dd>
			</dl>
			<dl>
				<dt>姓名：</dt>
				<dd>
					${vm.name}
				</dd>
			</dl>
			<dl>
				<dt>新密码：</dt>
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
