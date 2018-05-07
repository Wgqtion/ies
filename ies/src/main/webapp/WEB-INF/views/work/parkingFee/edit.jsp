<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>

<SCRIPT type="text/javascript">
	var zNodesParkingFee =[
   	   <c:forEach items="${parkingLotTree}" var="entity" varStatus="index">
   	   		{ id:'${entity.code}', pId:'${entity.parent.code}', name:'${entity.name}'}<c:if test="${!index.last}">,</c:if>
   	   </c:forEach>
   	];
   	
   	$(document).ready(function(){
   		GenerateSelectZTree("ParkingFee",zNodesParkingFee,"parkingLotParkingFee","${parkingLot.code}");
   		
   		$(".dateHH").attr("onclick","WdatePicker({readOnly:true,dateFmt:'HH:mm'});");
   		  
   		
   		$("select[name='week']").change(function(){  
   			weekChange($(this).val(),true);
   	    }); 
   		var week='${vm.week}';
   		weekChange(week,false);
   	});
   	
   	function pickedFuncStartTime(){
   		$dp.$('startTime').value=$dp.cal.getP('y')+"-"+$dp.cal.getP('M')+"-"+$dp.cal.getP('d')+" "+$dp.cal.getP('H')+":"+$dp.cal.getP('m');
   	}
   	function pickedFuncEndTime(){
   		$dp.$('endTime').value=$dp.cal.getP('y')+"-"+$dp.cal.getP('M')+"-"+$dp.cal.getP('d')+" "+$dp.cal.getP('H')+":"+$dp.cal.getP('m');
   	}
   	
   	function weekChange(week,flag){
   		if(week=='0'){
        	$("#dateStartTime").show();
        	$("#startTime").hide();
        	
        	$("#dateEndTime").show();
        	$("#endTime").hide();
        	
        	if(flag){
            	$("#dateStartTime").val('');
            	$("#dateEndTime").val('');
        	}
        }else{
        	$("#dateStartTime").hide();
        	$("#startTime").show();
        	
        	$("#dateEndTime").hide();
        	$("#endTime").show();
        	
        	if(flag){
            	$("#startTime").val('');
            	$("#endTime").val('');
        	}
        }
   	}
</SCRIPT>
<div id="contentParkingFee" class="pageContent">
	<form method="post" action="${ctx}/work/parkingFee/${action}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="id" value="${id}">
		<vsc:token tokenName="work.passages.create"></vsc:token>
		<vsc:callback></vsc:callback>
		<div class="pageFormContent" layoutH="56">
			<table class="formTable">
				<tr>
					<td class="fieldName"><label><span class="required">*</span>类型:</label></td>
					<td class="fieldInput" colspan="3">
						<label>
							<select class="combox" id="type" name="type" validate="{required:true}" selectedValue="${vm.type}"  dataUrl="${ctx}/static/js/data/parkingFee_type.json">
							<vsc:headoption text="请选择"></vsc:headoption>
							</select>
						</label>
						<span for="type" generated="true" style="display: none" class="error"></span>
					</td>
				</tr>
				<tr>
					<td class="fieldName"><label><span class="required">*</span>场区:</label></td>
					<td class="fieldInput" colspan="3">
						<input id="parkingLotParkingFeeId" name="parkingLotCode" value="${parkingLot.code}" type="hidden" />
						<label><input validate="{required:true}" id="parkingLotParkingFeeName" value="${parkingLot.name}" readonly="readonly"/></label>
						<a class="btnLook" title="选择场区" href="#" onclick="showMenu(this,'ParkingFee');"></a>
						<span class="info">选择</span>
						<input id="claerBtn" type='button' style="margin-left: 5px;" value='清空' onclick='clearBtn("parkingLotParkingFee");' />
					</td>
				</tr>
				<tr>
					<td class="fieldName"><label><span class="required">*</span>周:</label></td>
					<td class="fieldInput" colspan="3">
						<label>
							<select class="combox" id="week" name="week" validate="{required:true}" selectedValue="${vm.week}"  dataUrl="${ctx}/static/js/data/week.json">
							<vsc:headoption text="请选择"></vsc:headoption>
							</select>
						</label>
						<span for="week" generated="true" style="display: none" class="error"></span>
					</td>
				</tr>
				<tr>
					<td class="fieldName"><label><span class="required">*</span>开始时间:</label></td>
					<td class="fieldInput">
						<label>
						<input type="text" class="dateIco dateHH" id="startTime" value="${vm.startTime}" name="startTime" readonly="true" validate="{required:true}" />
						<input type="text" style="display: none;" class="dateIco" id="dateStartTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',onpicked:pickedFuncStartTime})" value="${vm.startTime}" readonly="true" />
						</label>
						<span for="startTime" generated="true" style="display: none" class="error"></span>
					</td>
					<td class="fieldName"><label><span class="required">*</span>结束时间:</label></td>
					<td class="fieldInput">
						<label>
						<input type="text" class="dateIco dateHH" id="endTime" value="${vm.endTime}" name="endTime" readonly="true" validate="{required:true}" />
						<input type="text" style="display: none;" class="dateIco" id="dateEndTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',onpicked:pickedFuncEndTime})" value="${vm.endTime}" readonly="true" />
						</label>
						<span for="endTime" generated="true" style="display: none" class="error"></span>
					</td>
				</tr>
				<tr>
					<td class="fieldName"><label><span class="required">*</span>费用:</label></td>
					<td class="fieldInput" colspan="3">
						<label>
							<input type="text" name="fee" id="fee" value="${vm.fee }" validate="{required:true,number:true}"/>
						</label>
						<span for="fee" generated="true" style="display: none" class="error"></span>
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

<div id="selectParkingFeeContent" class="menuContent" style="display: none;position: absolute;">
   <ul id="selectParkingFeeTree" class="ztree editZtree" style="margin-top:0; width:200px;"></ul>
</div>