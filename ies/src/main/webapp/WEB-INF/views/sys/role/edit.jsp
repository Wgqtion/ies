<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<div class="pageContent">
	<form method="post" action="${ctx}/sys/role/${action}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="id" value="${id}">
		<vsc:token tokenName="sys.role.create"></vsc:token>
		<input type="hidden" name="callbackType" value="closeCurrent"> <input type="hidden" name="navTabId" value="sys_shiro_role">
		<div class="pageFormContent" layoutH="56">
			<dl>
				<dt>角色编码：</dt>
				<dd>
					<input name="code" <c:if test="${action eq 'update'}">readOnly=true</c:if>class="required" type="text" size="30" value="${vm.code}" validate="{required:true,rangelength:[1,50]}" />
				</dd>
				<dt>角色名称：</dt>
				<dd>
					<input name="name" class="required" type="text" size="30" value="${vm.name}" validate="{required:true,rangelength:[1,60]}" />
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
