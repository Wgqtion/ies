<%@page import="org.apache.shiro.SecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>

<SCRIPT type="text/javascript">
	var ParkingLotzNodes =[
   	   <c:forEach items="${parkingLotTree}" var="entity" varStatus="index">
   	   		{ id:'${entity.id}', pId:'${entity.parent.id}', name:'${entity.name}'}<c:if test="${!index.last}">,</c:if>
   	   </c:forEach>
   	];
   	
	$(document).ready(function(){
   		GenerateSelectZTree("ParkingLot",ParkingLotzNodes,"parent","${parkingLot.id}",companyDisabled);
   		companyDisabled();
   		
   		$("#clearBtnId").bind("click",function(){
   			$("#companyCode").attr("disabled",false);
   			$("#companyCode").attr("validate","{required:true}");
   		});
   	});
	//隐藏公司
	function companyDisabled(){
		var parentId=$("#parentId").val();
		if(parentId!=''){
			$("#companyCode").attr("disabled",true);
			$("#companyCode").attr("validate","{required:false}");
		}
	}
	
</SCRIPT>
<div id="contentParkingLot" class="pageContent">
	<form method="post" action="${ctx}/work/parkinglot/${action}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" id="id" name="id" value="${id}">
		<vsc:token tokenName="work.parkinglot.create"></vsc:token>
		<vsc:callback></vsc:callback>
		<div class="pageFormContent" layoutH="56">
			<table class="formTable"> 
				<tr>
					<td class="fieldName"><span class="required">*</span>场区名称:</td>
					<td class="fieldInput"><label><input type="text" id="name" name="name" value="${vm.name}" validate="{required:true}" /></label><span for="name" generated="true" style="display: none" class="error"></span></td>
					<td rowspan="6"  class="fieldName" valign="top">场区图片:</td>
					<td rowspan="6"  class="fieldInput">
					<vsc:picUpload  height="180"  picPath="${vm.photoAttach.downloadPath}"  uploadifyFileId="uploadifyFileIdFileShare"  hiddenValue="${vm.photoAttach.id}"   hiddenName="photoAttachId" width="320"></vsc:picUpload>
					<span style="display: none" class="error" generated="true" for="uploadifyFileIdFileShare_hiddenId"></span>
					</td>
				</tr> 
				<tr>
					<td class="fieldName">上级场区:</td>
					<td class="fieldInput">
						<input type="hidden" name="parent.id" id="parentId" value="${parent.id}">
						<input  id="parentName" value="${parent.name }" readonly="readonly"/>
						<c:if test="${empty id}">
						<a class="btnLook" title="选择上级场区" href="#" onclick="showSelectZTreeMenu(this,'ParkingLot');"></a>
						<span class="info">选择</span>
						<input id="clearBtnId" type='button' style="margin-left: 5px;" value='清空' onclick='clearSelectZTreeBtn("parent");' />
						</c:if>
					</td>
				</tr> 
				<c:if test="${empty id}">
				<tr>
					<td class="fieldName"><span class="required">*</span>所属公司:</td>
					<td class="fieldInput">
						<select id="companyCode" name="companyCode" validate="{required:true}">
							<option value="">全部</option>
							<c:forEach items="${companyList}" var="company">
								<option value="${company.code}" <c:if test="${empty vm.companyCode }"><c:if test="${currentUser.company.code eq company.code }">selected='selected'</c:if></c:if><c:if test="${company.code eq vm.companyCode }">selected='selected'</c:if>>${company.name}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				</c:if>
				<tr>
					<td class="fieldName"><span class="required">*</span>纬度坐标:</td>
					<td class="fieldInput"><label><input type="text" id="itudeLong" name="itudeLong" value="${vm.itudeLong}" validate="{required:true}" /></label><span for="itudeLong" generated="true"
						style="display: none" class="error"></span></td> 
				</tr> 
				<tr>
					<td class="fieldName"><span class="required">*</span>经度坐标:</td>
					<td class="fieldInput"><label><input type="text" id="itudeLat" name="itudeLat" value="${vm.itudeLat}" validate="{required:true}" /></label><span for="itudeLat" generated="true"
						style="display: none" class="error"></span></td>
				</tr> 
				<tr>
					<td class="fieldName"><span class="required">*</span>状态:</td>
					<td class="fieldInput"><label> <form:radiobutton path="vm.isEnabled" value="false" validate="{required:true}" /> <s:message code="parkinglot.isenabled.false" />
					</label> <label> <form:radiobutton path="vm.isEnabled" value="true" /> <s:message code="parkinglot.isenabled.true" />
					</label> <span style="display: none" class="error" generated="true" for="isEnabled"></span></td>
				</tr>
				<tr>
					<td class="fieldName">地址:</td>
					<td class="fieldInput" colspan="3"><label><input type="text" size="50" id="address" name="address" value="${vm.address}" validate="{required:false}" /></label><span for="address" generated="true" style="display: none" class="error"></span></td>
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
<div id="selectParkingLotContent" class="menuContent" style="display: none;position: absolute;">
   <ul id="selectParkingLotTree" class="ztree editZtree" style="margin-top:0; width:200px;"></ul>
</div>


