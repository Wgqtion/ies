<%@page import="org.apache.shiro.SecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<div class="pageContent">
	<form method="post" action="${ctx}/work/parkinglot/${action}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="id" value="${id}">
		<vsc:token tokenName="work.parkinglot.create"></vsc:token>
		<vsc:callback></vsc:callback>
		<div class="pageFormContent" layoutH="56">
			<table class="formTable"> 
				<tr>
					<td class="fieldName">停车场名称:</td>
					<td class="fieldInput"><label><input type="text" id="name" name="name" value="${vm.name}" validate="{required:true}" /></label><span for="name" generated="true" style="display: none" class="error"></span></td>
					<td rowspan="6"  class="fieldName" valign="top">停车场图片:</td>
					<td rowspan="6"  class="fieldInput">					 
					<vsc:picUpload  height="180"  picPath="${vm.photoAttach.downloadPath}"  uploadifyFileId="uploadifyFileIdFileShare"  hiddenValue="${vm.photoAttach.id}"   hiddenName="photoAttachId" width="320"></vsc:picUpload>
					<span style="display: none" class="error" generated="true" for="uploadifyFileIdFileShare_hiddenId"></span>
					</td>
				</tr> 
				<tr>
					<td class="fieldName">编号:</td>
					<td class="fieldInput">
						<label><input type="text" id="code" name="code" value="${vm.code}" validate="{required:true}" /></label><span for="code" generated="true" style="display: none" class="error"></span>
					</td>
				</tr>
				
				<tr>
					<td class="fieldName">百度纬度坐标:</td>
					<td class="fieldInput"><label><input type="text" id="baiduLatitudeLng" name="baiduLatitudeLng" value="${vm.baiduLatitudeLng}" validate="{required:true}" /></label><span for="baiduLatitudeLng" generated="true"
						style="display: none" class="error"></span></td> 
				</tr> 
				<tr>
					<td class="fieldName">百度经度坐标:</td>
					<td class="fieldInput"><label><input type="text" id="baiduLatitudeLat" name="baiduLatitudeLat" value="${vm.baiduLatitudeLat}" validate="{required:true}" /></label><span for="baiduLatitudeLat" generated="true"
						style="display: none" class="error"></span></td>
				</tr> 
				<tr>
					<td class="fieldName">车位数量:</td>
					<td class="fieldInput"><label><input type="text" id="carNumber" name="carNumber" value="${vm.carNumber}" validate="{required:true}" /></label><span for="carNumber" generated="true" style="display: none" class="error"></span></td>
				</tr>
				
				<tr>
					<td class="fieldName">状态:</td>
					<td class="fieldInput"><label> <form:radiobutton path="vm.isEnabled" value="false" validate="{required:true}" /> <s:message code="parkinglot.isenabled.false" />
					</label> <label> <form:radiobutton path="vm.isEnabled" value="true" /> <s:message code="parkinglot.isenabled.true" />
					</label> <span style="display: none" class="error" generated="true" for="isEnabled"></span></td>
				</tr>
				<tr>
					<td class="fieldName">地址:</td>
					<td class="fieldInput" colspan="3"><label><input type="text" size="50" id="address" name="address" value="${vm.address}" validate="{required:true}" /></label><span for="address" generated="true" style="display: none" class="error"></span></td>
				</tr>
				<tr>
					<td class="fieldName" valign="top">说明:</td>
					<td class="fieldInput" colspan="3"><label>
					<textarea id="description" name="description"  rows="5" cols="80">${vm.description}</textarea>
					</label><span for="description" generated="true" style="display: none" class="error"></span>
					</td>
				</tr>
			</table>
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