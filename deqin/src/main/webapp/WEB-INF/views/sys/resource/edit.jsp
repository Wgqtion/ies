<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<div class="pageContent">
	<form method="post" action="${ctx}/sys/resource/${action}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="id" value="${id}">
		<vsc:token tokenName="sys.resource.create"></vsc:token>
		<input type="hidden" name="callbackType" value="closeCurrent"> <input type="hidden" name="navTabId" value="sys_resource">
		<div class="pageFormContent" layoutH="56">
			<dl>
				<dt>资源类型：</dt>
				<dd>
					<select class="combox" name="resourceType" selectedValue="${vm.resourceType}">
						<option value="url">URL</option>
					</select>
				</dd>
			</dl>
			<dl>
				<dt>资源标识：</dt>
				<dd>
					<input name="value" class="required" type="text" size="30" value="${vm.value}" validate="{required:true,rangelength:[1,100]}" />
				</dd>
			</dl>
			<dl>
				<dt>排序：</dt>
				<dd>
					<input name="position" class="required" type="text" size="30" value="${vm.position}" validate="{required:true,number:true}" />
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
