<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>

<SCRIPT type="text/javascript">
	var zNodesParkingOvertime =[
   	   <c:forEach items="${parkingLotTree}" var="entity" varStatus="index">
   	   		{ id:'${entity.code}', pId:'${entity.parent.code}', name:'${entity.name}'}<c:if test="${!index.last}">,</c:if>
   	   </c:forEach>
   	];
   	
   	$(document).ready(function(){
   		GenerateSelectZTree("ParkingOvertime",zNodesParkingOvertime,"parkingLotParkingOvertime","${parkingLot.code}");
   		
   	});
</SCRIPT>
<div id="contentParkingOvertime" class="pageContent">
	<form method="post" action="${ctx}/work/parkingOvertime/${action}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="id" value="${id}">
		<vsc:token tokenName="work.passages.create"></vsc:token>
		<vsc:callback></vsc:callback>
		<div class="pageFormContent" layoutH="56">
			<table class="formTable">
				<tr>
					<td class="fieldName"><label><span class="required">*</span>场区:</label></td>
					<td class="fieldInput" colspan="3">
						<input id="parkingLotParkingOvertimeId" name="parkingLotCode" value="${parkingLot.code}" type="hidden" />
						<label><input validate="{required:true}" id="parkingLotParkingOvertimeName" value="${parkingLot.name}" readonly="readonly"/></label>
						<a class="btnLook" title="选择场区" href="#" onclick="showMenu(this,'ParkingOvertime');"></a>
						<span class="info">选择</span>
						<input id="claerBtn" type='button' style="margin-left: 5px;" value='清空' onclick='clearBtn("parkingLotParkingOvertime");' />
					</td>
				</tr>
				<tr>
					<td class="fieldName"><label><span class="required">*</span>超时分钟:</label></td>
					<td class="fieldInput">
						<label>
						<input type="text" id="overtimeMin" value="${vm.overtimeMin}" name="overtimeMin" validate="{required:true,digits:true}" />
						</label>
						<span for="overtimeMin" generated="true" style="display: none" class="error"></span>
					</td>
					<td class="fieldName"><label><span class="required">*</span>追加费:</label></td>
					<td class="fieldInput">
						<label>
							<input type="text" name="appendFee" id="appendFee" value="${vm.appendFee }" validate="{required:true,number:true}"/>
						</label>
						<span for="appendFee" generated="true" style="display: none" class="error"></span>
					</td>
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

<div id="selectParkingOvertimeContent" class="menuContent" style="display: none;position: absolute;">
   <ul id="selectParkingOvertimeTree" class="ztree editZtree" style="margin-top:0; width:200px;"></ul>
</div>