<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>

<div class="pageContent">
	<form method="post" action="${ctx}/work/parkingcharge/${action}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<vsc:token tokenName="work.parkingcharge.create"></vsc:token>
		<vsc:callback></vsc:callback>
		<div class="pageFormContent" layoutH="200">
			<table class="formTable">
				<tr>
					<td class="fieldName">停车片区名称:${vm.parkingLot.name}</td>
						<input type="hidden" name="parkingLotId" id="parkingLotId" value="${vm.parkingLot.id}">
				</tr>
				<tr>
					<td class="fieldName"><span class="required">*</span>收费规则:</td>
					<td class="fieldInput">
						<select id="chargesSettingsId" name="chargesSettingsId" validate="{required:true}">
							<option value=""></option>
							<c:forEach items="${chargesSettingsList}" var="chargesSettings">
								<option value="${chargesSettings.id}" <c:if test="${empty vm.chargesSettings.id }"><c:if test="${ vm.chargesSettings.id eq chargesSettings.id }">selected='selected'</c:if></c:if><c:if test="${chargesSettings.id eq vm.chargesSettings.id }">selected='selected'</c:if>>${chargesSettings.name}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<%--<tr>
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
					<td class="fieldName">片区类型:
					</td>
					<td class="fieldInput">
						<label><select class="combox" name="lotType" selectedValue="${vm.lotType}"  dataUrl="${ctx}/static/js/data/parkinglotarea_lotType.json">
						<vsc:headoption></vsc:headoption>
					</select></label>
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
					<td class="fieldInput" colspan="3"><label><textarea cols="80" rows="5" id="description" name="description" validate="{required:true}">${vm.description}</textarea></label><span for="description" generated="true"
						style="display: none" class="error"></span></td>
				</tr>--%>
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