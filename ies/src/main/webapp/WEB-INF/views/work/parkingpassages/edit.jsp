<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>

<SCRIPT type="text/javascript">
	var zNodesParkingPassages =[
   	   <c:forEach items="${parkingLotTree}" var="entity" varStatus="index">
   	   		{ id:'${entity.id}', pId:'${entity.parent.id}', name:'${entity.name}'}<c:if test="${!index.last}">,</c:if>
   	   </c:forEach>
   	];
   	
   	$(document).ready(function(){
   		GenerateSelectZTree("ParkingPassages",zNodesParkingPassages,"parkingLot","${parkingLot.id}");
   	});
	
</SCRIPT>
<div id="contentParkingGarage" class="pageContent">
	<form method="post" action="${ctx}/work/parkingpassages/${action}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="id" value="${id}">
		<vsc:token tokenName="work.passages.create"></vsc:token>
		<vsc:callback></vsc:callback>
		<div class="pageFormContent" layoutH="56">
			<table class="formTable">
			
				<tr>
					<td class="fieldName"><label><span class="required">*</span>场区:</label></td>
					<td class="fieldInput" colspan="3">
						<input id="parkingLotId" name="parkingLot.id" value="${parkingLot.id}" type="hidden" />
						<label><input validate="{required:true}" id="parkingLotName" value="${parkingLot.name}" readonly="readonly"/></label>
						<c:if test="${empty id}">
						<a class="btnLook" title="选择场区" href="#" onclick="showMenu(this,'ParkingPassages');"></a>
						<span class="info">选择</span>
						<input id="claerBtn" type='button' style="margin-left: 5px;" value='清空' onclick='clearBtn("parkingLot");' />
						</c:if>
					</td>
				</tr>
				<tr>
					<td class="fieldName"><label><span class="required">*</span>出入口名称:</label></td>
					<td class="fieldInput"><label><input type="text" id="name" name="name" value="${vm.name}" validate="{required:true}" /></label><span for="name" generated="true" style="display: none" class="error"></span></td>
					<td class="fieldName"><label><span class="required">*</span>是否启用:</label></td>
					<td class="fieldInput">
						<label><form:radiobutton path="vm.isEnabled" value="1" validate="{required:true}" />启用</label> 
						<label><form:radiobutton path="vm.isEnabled" value="0" />停用</label>
						<span for="isEnabled" generated="true" class="error" style="display: none"></span>
					</td>
				</tr>
				<tr>
					<td class="fieldName"><label>纬度坐标:</label></td>
					<td class="fieldInput"><label><input type="text" id="itudeLong" name="itudeLong" value="${vm.itudeLong}" validate="{required:false}" /></label><span for="itudeLong" generated="true" style="display: none" class="error"></span></td>
					<td class="fieldName"><label>经度坐标:</label></td>
					<td class="fieldInput"><label><input type="text" id="itudeLat" name="itudeLat" value="${vm.itudeLat}" validate="{required:false}" /></label><span for="itudeLat" generated="true" style="display: none" class="error"></span></td>
				</tr>
				
				<tr>
					<td class="fieldName"><label>备注:</label></td>
					<td class="fieldInput" colspan="3"><label><input type="text" id="mark" name="mark" value="${vm.mark}" /></label><span for="mark" generated="true" style="display: none" class="error"></span></td>
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

<div id="selectParkingPassagesContent" class="menuContent" style="display: none;position: absolute;">
   <ul id="selectParkingPassagesTree" class="ztree editZtree" style="margin-top:0; width:200px;"></ul>
</div>