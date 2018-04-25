<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>

<SCRIPT type="text/javascript">
   	var nodesParkingGarage=[
				<c:forEach items="${parkingLotTree}" var="entity" varStatus="index">
						{ id:'${entity.id}', pId:'${entity.parent.id}', name:'${entity.name}'}<c:if test="${!index.last}">,</c:if>
				</c:forEach>
   	];
   	$(document).ready(function(){
   		GenerateSelectZTree("ParkingGarage",nodesParkingGarage,"parkingLot","${parkingLot.id}");
   	});
	
</SCRIPT>
<div id="contentParkingGarage" class="pageContent">
	<form method="post" action="${ctx}/work/parkinggarage/${action}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="id" value="${id}">
		<vsc:token tokenName="work.parkinggarage.create"></vsc:token>
		<vsc:callback></vsc:callback>
		<div class="pageFormContent" layoutH="56">
			<table class="formTable">
				<tr>
					<td class="fieldName"><span class="required">*</span>场区:</td>
					<td class="fieldInput" colspan="3">
						<input id="parkingLotId" name="parkingLot.id" value="${parkingLot.id}" type="hidden" />
						<label><input validate="{required:true}" id="parkingLotName" value="${parkingLot.name}" readonly="readonly"/></label>
						<c:if test="${empty id}">
						<a class="btnLook" title="选择场区" href="#" onclick="showMenu(this,'ParkingGarage');"></a>
						<span class="info">选择</span>
						<input id="claerBtn" type='button' style="margin-left: 5px;" value='清空' onclick='clearBtn("parkingLot");' />
						</c:if>
					</td>
				</tr>
				<tr>
					<td class="fieldName"><span class="required">*</span>车位名称:</td>
					<td class="fieldInput"><label><input type="text" id="name" name="name" value="${vm.name}" validate="{required:true}" /></label><span for="name" generated="true" style="display: none" class="error"></span></td>
					<td class="fieldName"><span class="required">*</span>状态:</td>
					<td class="fieldInput"><label><form:radiobutton path="vm.isEnabled" value="false" validate="{required:true}" /> <s:message code="parkinggarage.isenabled.false" /> </label> <label> <form:radiobutton path="vm.isEnabled" value="true" />
							<s:message code="parkinggarage.isenabled.true" />
					</label> <span style="display: none" class="error" generated="true" for="isEnabled"></span></td>
				</tr>
				<tr>
					<td class="fieldName"><span class="required">*</span>纬度坐标:</td>
					<td class="fieldInput"><label><input type="text" id="name" name="itudeLong" value="${vm.itudeLong}" validate="{required:true}" /></label><span for="itudeLong" generated="true" style="display: none" class="error"></span></td>
					<td class="fieldName"><span class="required">*</span>经度坐标:</td>
					<td class="fieldInput"><label><input type="text" id="name" name="itudeLat" value="${vm.itudeLat}" validate="{required:true}" /></label><span for="itudeLat" generated="true" style="display: none" class="error"></span></td>
				</tr>
				<tr>
					<td class="fieldName">备注:</td>
					<td class="fieldInput" colspan="3"><label><textarea name="description" rows="5" cols="80">${vm.description}</textarea> </label><span for="description" generated="true" style="display: none" class="error"></span></td>

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
<div id="selectParkingGarageContent" class="menuContent" style="display: none;position: absolute;">
   <ul id="selectParkingGarageTree" class="ztree editZtree" style="margin-top:0; width:200px;"></ul>
</div>