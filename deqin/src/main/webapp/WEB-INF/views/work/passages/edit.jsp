<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>

<div class="pageContent">
	<form method="post" action="${ctx}/work/passages/${action}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="id" value="${id}">
		<vsc:token tokenName="work.passages.create"></vsc:token>
		<vsc:callback></vsc:callback>
		<div class="pageFormContent" layoutH="56">
			<table class="formTable">
			
				<tr>
					<td class="fieldName"><label>所属停车场:</label></td>
					<td class="fieldInput">
					<label>
					<input name="parkinglotGroup.id" value="${vm.parkinglot.id}" type="hidden" />
					<input validate="{required:true}" id="parkinglotGroup_name" name="parkinglotGroup.name" value="${vm.parkinglot.name}" type="text"/> 
					<a class="btnLook" rel="passages_parkinglot_select" title="选择车位所属停车场" href="${ctx}/work/parkinglot/select?single=true&search_EQ_isEnabled=1" lookupGroup="parkinglotGroup">查找带回</a> <span class="info">(选择停车场)</span>
					</label><span for="parkinglotGroup_name" generated="true" style="display: none" class="error"></span></td>
					
					<td class="fieldName"><label>出入口名称:</label></td>
					<td class="fieldInput"><label><input type="text" id="name" name="name" value="${vm.name}" validate="{required:true}" /></label><span for="name" generated="true" style="display: none" class="error"></span></td>
				</tr>
				
				<tr>
					<td class="fieldName"><label>X坐标:</label></td>
					<td class="fieldInput"><label><input type="text" id="xcoordinate" name="xcoordinate" value="${vm.xcoordinate}" validate="{required:true}" /></label><span for="xcoordinate" generated="true" style="display: none" class="error"></span></td>
					<td class="fieldName"><label>Y坐标:</label></td>
					<td class="fieldInput"><label><input type="text" id="ycoordinate" name="ycoordinate" value="${vm.ycoordinate}" validate="{required:true}" /></label><span for="ycoordinate" generated="true" style="display: none" class="error"></span></td>
				</tr>
				
				<tr>
					<td class="fieldName"><label>是否启用:</label></td>
					<td class="fieldInput">
						<label><form:radiobutton path="vm.isEnabled" value="1" validate="{required:true}" />启用</label> 
						<label><form:radiobutton path="vm.isEnabled" value="0" />停用</label>
						<span for="isEnabled" generated="true" class="error" style="display: none"></span>
					</td>
					<td class="fieldName"><label>备注:</label></td>
					<td class="fieldInput"><label><input type="text" id="mark" name="mark" value="${vm.mark}" validate="{required:true}" /></label><span for="mark" generated="true" style="display: none" class="error"></span></td>
				</tr>
				
			</table>
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