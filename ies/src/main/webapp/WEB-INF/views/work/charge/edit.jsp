<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>

<div class="pageContent">
	<form method="post" action="${ctx}/work/charge/${action}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="id" value="${vm.id}">
		<input type="hidden" name="chargesSettings.id" value="${vm.chargesSettings.id}">
		<vsc:token tokenName="work.company.create"></vsc:token>
		<vsc:callback></vsc:callback>
		<div class="pageFormContent" layoutH="56">
			<table class="formTable" style="width: 50%;margin:auto">
				<tr>
					<td class="fieldName"><label>周:</label></td>
					<td class="fieldInput">
					<label><select class="combox" name="week" selectedValue="${vm.week}"  dataUrl="${ctx}/static/js/data/week.json">
						<vsc:headoption></vsc:headoption>
					</select></label></td>
				</tr>
				<tr>
					<td class="fieldName"><label>开始时间:</label></td>
					<td class="fieldInput"><label><input type="text" id="startTimeString" name="startTimeString" value="${vm.startTimeString}" validate="{required:false}" /></label><span for="startTimeString" generated="true" style="display: none" class="error"></span></td>
				</tr>
				<tr>
					<td class="fieldName"><label>结束时间:</label></td>
					<td class="fieldInput"><label><input type="text" id="endTimeString" name="endTimeString" value="${vm.endTimeString}" validate="{required:false}" /></label><span for="endTimeString" generated="true" style="display: none" class="error"></span></td>
				</tr>
				<tr>
					<td class="fieldName"><label>单价:</label></td>
					<td class="fieldInput"><label><input type="text" id="fee" name="fee" value="${vm.fee}" validate="{required:false}" /></label><span for="fee" generated="true" style="display: none" class="error"></span></td>
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