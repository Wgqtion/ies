<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<div class="pageContent">
	<div class="pageFormContent" layoutH="10">
		<dl>
			<dt>资源类型：</dt>
			<dd>${vm.resourceType}</dd>
		</dl>

		<dl>
			<dt>资源标识：</dt>
			<dd>${vm.value}</dd>
		</dl>

		<dl>
			<dt>排序：</dt>
			<dd>${vm.position}</dd>
		</dl>

	</div>
</div>
