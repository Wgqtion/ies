<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<div class="pageContent">
	<form method="post" action="${ctx}/sys/dict/${action}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="id" value="${id}">
		<vsc:token tokenName="sys.dict.create"></vsc:token>
		<vsc:callback></vsc:callback>
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>项目类型：</label>
				<input class="ac_input required" name="type" type="text" value="${vm.type}" options="{formatItem:formatItem,itemParam:['type']}" dataUrl="${ctx}/sys/dict/typelist" validate="{required:true}"/>
			</p>
			<p>
				<label>项目名称：</label>
				<input name="name" class="required" type="text" size="30" value="${vm.name}"   validate="{required:true,rangelength:[1,255]}"/>
			</p>
			<p>
				<label>项目代码：</label>
				<input name="code" class="required" type="text" size="30" value="${vm.code}"  validate="{required:true,rangelength:[1,255]}"/>
			</p>
			<p>
				<label>描述：</label>
				<input name="description" class="required" type="text" size="30" value="${vm.description}"  validate="{rangelength:[1,255]}"/>
			</p> 
			<p>
				<label>排序：</label>
				<input name="sort" type="text" size="30" value="${vm.sort}"  validate="{required:true,digits:true}"/>
			</p> 
		</div>
		<div class="formBar">
			<ul> 
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
