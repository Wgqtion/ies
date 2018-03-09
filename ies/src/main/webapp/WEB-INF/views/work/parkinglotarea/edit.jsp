<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>

<div class="pageContent">
	<form method="post" action="${ctx}/work/parkinglotarea/${action}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="id" value="${id}">
		<vsc:token tokenName="work.parkinglotarea.create"></vsc:token>
		<vsc:callback></vsc:callback>
		<div class="pageFormContent" layoutH="56">
			<table class="formTable">
				<tr>
					<td class="fieldName">停车片区名称:</td>
					<td class="fieldInput"><label><input type="text" id="name" name="name" value="${vm.name}" validate="{required:true}" /></label><span for="name" generated="true" style="display: none" class="error"></span></td>
					<td class="fieldName">编号:</td>
					<td class="fieldInput"><label><input type="text" id="code" name="code" value="${vm.code}" validate="{required:true}" /></label><span for="code" generated="true" style="display: none" class="error"></span></td>
				</tr>
				<tr>
					<td class="fieldName">所属停车场:</td>
					<td class="fieldInput">
					<label>
					<input name="parkinglotGroup.id" value="${vm.parkingLot.id}" type="hidden" />
					<input validate="{required:true}" readonly="readonly" id="parkinglotGroup_name" name="parkinglotGroup.name" value="${vm.parkingLot.name}" type="text"/> 
					<a class="btnLook" rel="parkinglotarea_parkinglot_select" title="选择所属停车场" href="${ctx}/work/parkinglot/select?single=true&search_EQ_isEnabled=true" lookupGroup="parkinglotGroup">查找带回</a> <span class="info">(选择停车场)</span>
					</label><span for="parkinglotGroup_name" generated="true" style="display: none" class="error"></span>
					</td>
					<td class="fieldName">车位数:</td>
					<td class="fieldInput"><label><input type="text" id="carNumber" name="carNumber" value="${vm.carNumber}" validate="{required:true}" /></label><span for="carNumber" generated="true" style="display: none" class="error"></span></td>
				</tr>
				<tr>
					<td class="fieldName">上级区域：</td>
					<td class="fieldInput"><label>
						<input id="par.id" name="parentId" type="hidden" value="${vm.parent.id}"  />
					    <input name="par.name" style="width:190px;" type="text" class="readonly changeValidate" readonly="readonly" value="${vm.parent.name}"  valName="parentId"
					    <c:if test="${not empty id}">validate="{remoteAsync:'${ctx}/work/parkinglotarea/checkSelParkinglotarea?oldid=${vm.parent.id}&pid=${vm.id}',messages:{remoteAsync:'上级区域关系冲突'}}" </c:if>
					    />
					    <a class="btnLook" rel="work_parkinglotarea_select" title="选择停车区" href="${ctx}/work/parkinglotarea/select" lookupGroup="par">查找带回</a> 
					    </label>
					    <span class="info">(点击选择区域)</span>
			         </td>
				</tr>
				<tr>
					<td class="fieldName" >百度X坐标:</td>
					<td class="fieldInput" ><label><input  name="baiduLatitudeLng" value="${vm.baiduLatitudeLng}"/></label><span for="baiduLatitudeLng" generated="true"
						style="display: none" class="error"></span></td>
						<td class="fieldName" >百度Y坐标:</td>
					<td class="fieldInput" ><label><input  name="baiduLatitudeLat" value="${vm.baiduLatitudeLat}"/></label><span for="baiduLatitudeLat" generated="true"
						style="display: none" class="error"></span></td>
				</tr>
				
				
				<tr>
					<td class="fieldName">状态:</td>
					<td class="fieldInput" colspan="3"><label> <form:radiobutton path="vm.isEnabled" value="false" validate="{required:true}" /> <s:message code="parkinglot.isenabled.false" />
					</label> <label> <form:radiobutton path="vm.isEnabled" value="true" /> <s:message code="parkinglot.isenabled.true" />
					</label> <span style="display: none" class="error" generated="true" for="isEnabled"></span></td>
				</tr>
				<tr>
					<td class="fieldName" valign="top">平面图:</td>
					<td class="fieldInput" colspan="3">
					<vsc:picUpload  height="240"  picPath="${vm.photoAttach.downloadPath}"  uploadifyFileId="uploadifyFileIdFileShare"  hiddenValue="${vm.photoAttach.id}"   hiddenName="photoAttachId" width="320"></vsc:picUpload>
					<span style="display: none" class="error" generated="true" for="uploadifyFileIdFileShare_hiddenId"></span>
					
					</td>
				</tr>
				<tr>
					<td class="fieldName" valign="top">说明:</td>
					<td class="fieldInput" colspan="3"><label><textarea cols="80" rows="5" id="description" name="description">${vm.description}</textarea></label><span for="description" generated="true"
						style="display: none" class="error"></span></td>
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