<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<div class="pageContent">
	<form action="${ctx}/attach/list" method="post" class="pageForm required-validate">
	<input type="hidden" name="id" value="${id}">
	<div class="pageFormContent" layoutH="56">
		<dl class="nowrap">
			<dt>附件上传：</dt>
			<dd>
				<vsc:upload name="openFileObject" id="openFileInput" fileHiddenName="attachids" fileList="${vm}" isPreview="false" previewWidth="100" previewHeight="100" advancedValue="true"></vsc:upload>
			</dd>
		</dl>
	</div>
	<div class="formBar">
		<ul>
			<li><div class="buttonActive"><div class="buttonContent">
				<button type="button" multLookupByName="attachids" warn="请上传附件">确定</button>
			</div></div></li>
			<li><div class="button"><div class="buttonContent">
			<button class="close" type="button" onclick="javascript:$.bringBack({id:'', name:''})">清空</button>
			</div></div></li>
		</ul>
	</div>
	</form>
</div>
<script type="text/javascript">
function attachSelectedBringBack(){
	
}
</script>
