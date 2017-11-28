<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<div class="pageContent">

	<div class="pageFormContent" layoutH="56">  
		<dl>
			<dt>角色名称：</dt>
			<dd>${vm.name}</dd>
		</dl> 
		<dl>
			<dt>授权分配：</dt>
			<dd>
				<vsc:fetchElementPropertyToString propertyName="displayName" list="${vm.authorityList}" />
			</dd>
		</dl>
	</div>
	<div class="formBar">
		<ul>
			<li>
				<div class="button">
					<div class="buttonContent">
						<button type="button" class="close">关闭</button>
					</div>
				</div>
			</li>
		</ul>
	</div>

</div>
