<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<div class="pageContent">
	<form method="post" action="${ctx}/login/ajax" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone)">
		<vsc:callback></vsc:callback>
		<div class="pageFormContent" layoutH="56">
			<dl>
				<dt>
					<label>用户名：</label>
				</dt>
				<dd>
					<input type="text" name="username" size="30" class="required"  validate="{required:true}"/> 
				</dd>
			</dl>
			<dl>
				<dt>
					<label>密码：</label>
				</dt>
				<dd>
					<input type="password" name="password" size="30" class="required" validate="{required:true}"/>
				</dd>
			</dl>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">登录</button>
						</div>
					</div></li>
				<li><div class="button">
						<div class="buttonContent">
							<button type="button" class="close">关闭</button>
						</div>
					</div></li>
			</ul>
		</div>
	</form>
</div>

