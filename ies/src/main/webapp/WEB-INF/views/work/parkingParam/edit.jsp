<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>

<SCRIPT type="text/javascript">
	var zNodesParkingParam =[
   	   <c:forEach items="${parkingLotTree}" var="entity" varStatus="index">
   	   		{ id:'${entity.code}', pId:'${entity.parent.code}', name:'${entity.name}'}<c:if test="${!index.last}">,</c:if>
   	   </c:forEach>
   	];
   	
   	$(document).ready(function(){
   		GenerateSelectZTree("ParkingParam",zNodesParkingParam,"parkingLotParkingParam","${parkingLot.code}");
   		
   	});
   	
</SCRIPT>
<div id="contentParkingParam" class="pageContent">
	<form method="post" action="${ctx}/work/parkingParam/${action}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="id" value="${id}">
		<vsc:token tokenName="work.passages.create"></vsc:token>
		<vsc:callback></vsc:callback>
		<div class="pageFormContent" layoutH="56">
			<table class="formTable">
				<tr>
					<td class="fieldName"><label><span class="required">*</span>场区:</label></td>
					<td class="fieldInput" colspan="3">
						<input id="parkingLotParkingParamId" name="parkingLotCode" value="${parkingLot.code}" type="hidden" />
						<label><input validate="{required:true}" id="parkingLotParkingParamName" value="${parkingLot.name}" readonly="readonly"/></label>
						<a class="btnLook" title="选择场区" href="#" onclick="showSelectZTreeMenu(this,'ParkingParam');"></a>
						<span class="info">选择</span>
						<input id="claerBtn" type='button' style="margin-left: 5px;" value='清空' onclick='clearSelectZTreeBtn("parkingLotParkingParam");' />
					</td>
				</tr>
				<tr>
					<td class="fieldName"><label><span class="required">*</span>预约保留分钟:</label></td>
					<td class="fieldInput">
						<label>
						<input type="text" id="reserveMin" value="${vm.reserveMin}" name="reserveMin" validate="{required:true,digits:true}" />
						</label>
						<span for="reserveMin" generated="true" style="display: none" class="error"></span>
					</td>
					<td class="fieldName"><label><span class="required">*</span>预约取消次数上限:</label></td>
					<td class="fieldInput">
						<label>
						<input type="text" id="cancelNum" value="${vm.cancelNum}" name="cancelNum" validate="{required:true,digits:true}" />
						</label>
						<span for="cancelNum" generated="true" style="display: none" class="error"></span>
					</td>
				</tr>
				<tr>
					<td class="fieldName"><label><span class="required">*</span>免费预约分钟:</label></td>
					<td class="fieldInput">
						<label>
						<input type="text" id="freeReserveMin" value="${vm.freeReserveMin}" name="freeReserveMin" validate="{required:true,digits:true}" />
						</label>
						<span for="freeReserveMin" generated="true" style="display: none" class="error"></span>
					</td>
					<td class="fieldName"><label><span class="required">*</span>免费停车分钟:</label></td>
					<td class="fieldInput">
						<label>
						<input type="text" id="freeParkingMin" value="${vm.freeParkingMin}" name="freeParkingMin" validate="{required:true,digits:true}" />
						</label>
						<span for="freeParkingMin" generated="true" style="display: none" class="error"></span>
					</td>
				</tr>
				<tr>
					<td class="fieldName"><label><span class="required">*</span>预约优惠分钟:</label></td>
					<td class="fieldInput">
						<label>
						<input type="text" id="privilegeReserveMin" value="${vm.privilegeReserveMin}" name="privilegeReserveMin" validate="{required:true,digits:true}" />
						</label>
						<span for="privilegeReserveMin" generated="true" style="display: none" class="error"></span>
					</td>
					<td class="fieldName"><label><span class="required">*</span>停车优惠分钟:</label></td>
					<td class="fieldInput">
						<label>
						<input type="text" id="privilegeParkingMin" value="${vm.privilegeParkingMin}" name="privilegeParkingMin" validate="{required:true,digits:true}" />
						</label>
						<span for="privilegeParkingMin" generated="true" style="display: none" class="error"></span>
					</td>
				</tr>
				<tr>
					<td class="fieldName"><label><span class="required">*</span>预约上限费用:</label></td>
					<td class="fieldInput">
						<label>
						<input type="text" id="maxReserveFee" value="${vm.maxReserveFee}" name="maxReserveFee" validate="{required:true,number:true}" />
						</label>
						<span for="maxReserveFee" generated="true" style="display: none" class="error"></span>
					</td>
					<td class="fieldName"><label><span class="required">*</span>停车上限费用:</label></td>
					<td class="fieldInput">
						<label>
						<input type="text" id="maxParkingFee" value="${vm.maxParkingFee}" name="maxParkingFee" validate="{required:true,number:true}" />
						</label>
						<span for="maxParkingFee" generated="true" style="display: none" class="error"></span>
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

<div id="selectParkingParamContent" class="menuContent" style="display: none;position: absolute;">
   <ul id="selectParkingParamTree" class="ztree editZtree" style="margin-top:0; width:200px;"></ul>
</div>