<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<div class="pageContent">
	<div class="pageFormContent" layoutH="10">
		<dl>
			<dt>授权名称：</dt>
			<dd>${vm.displayName}</dd>
		</dl>
		<dl>
			<dt>授权KEY：</dt>
			<dd>${vm.name}</dd>
		</dl>
		<dl>
			<dt>资源分配：</dt>
			<dd>
				<vsc:fetchElementPropertyToString propertyName="value" list="${vm.resourceList}" />
			</dd>
		</dl>
	</div>
</div>
