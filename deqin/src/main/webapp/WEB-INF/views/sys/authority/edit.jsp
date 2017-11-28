<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<div class="pageContent">
	<form method="post" action="${ctx}/sys/authority/${action}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="id" value="${id}">
		<vsc:token tokenName="sys.authority.create"></vsc:token>
		<input type="hidden" name="callbackType" value="closeCurrent"> <input type="hidden" name="navTabId" value="sys_authority">
		<div class="pageFormContent" layoutH="56">
			<dl>
				<dt>授权名称：</dt>
				<dd>
					<input name="displayName" class="required" type="text" size="30" value="${vm.displayName}" validate="{required:true,rangelength:[1,60]}" />
				</dd>
			</dl>
			<dl>
				<dt>授权KEY：</dt>
				<dd>
					<input name="name" class="required" type="text" size="30" value="${vm.name}" validate="{required:true,rangelength:[1,60]}" />
				</dd>
			</dl>
			<dl class="nowrap">
				<dt>资源分配：</dt>
				<dd>
					<input name="resource.id" value="<vsc:fetchElementPropertyToString propertyName="id" list="${vm.resourceList}"/>" type="hidden"> <a class="btnLook"
						rel="resource_select" title="选择资源" href="${ctx}/sys/resource/select" lookupGroup="resource">查找带回</a> <span class="info">(点击选择资源)</span>
				</dd>
			</dl>
			<dl class="nowrap">
				<dt>&nbsp;</dt>
				<dd>
					<textarea class="readonly" rows="3" cols="40" readonly="readonly" name="resource.value"><vsc:fetchElementPropertyToString propertyName="value" list="${vm.resourceList}" /></textarea>
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
