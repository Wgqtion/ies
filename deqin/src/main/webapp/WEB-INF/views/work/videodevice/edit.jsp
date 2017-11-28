<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>

<div class="pageContent">
	<form method="post" action="${ctx}/work/videodevice/${action}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="id" value="${id}">
		<vsc:token tokenName="work.videodevice.create"></vsc:token>
		<vsc:callback></vsc:callback>
		<div class="pageFormContent" layoutH="56">
			<table class="formTable">
					<tr>
					<td class="fieldName">视频设备编号:</td>
						<td class="fieldInput"><label><input type="text" id="deviceNo" name="deviceNo" value="${vm.deviceNo}" validate="{required:true}"/></label><span for="deviceNo" generated="true" style="display: none" class="error"></span>
						</td>
					</tr>
					<tr>
					<td class="fieldName">视频设备名称:</td>
						<td class="fieldInput"><label><input type="text" id="name" name="name" value="${vm.name}" validate="{required:true}"/></label><span for="name" generated="true" style="display: none" class="error"></span>
						</td>
					</tr>
					<tr>
					<td class="fieldName">设备网络地址:</td>
						<td class="fieldInput"><label><input type="text" id="deviceIp" name="deviceIp" value="${vm.deviceIp}" validate="{required:true}"/></label><span for="deviceIp" generated="true" style="display: none" class="error"></span>
						</td>
					</tr>
					<tr>
					<td class="fieldName">状态:</td>
						<td class="fieldInput"><label><input type="text" id="isEnabled" name="isEnabled" value="${vm.isEnabled}" validate="{required:true}"/></label><span for="isEnabled" generated="true" style="display: none" class="error"></span>
						</td>
					</tr>
					 
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