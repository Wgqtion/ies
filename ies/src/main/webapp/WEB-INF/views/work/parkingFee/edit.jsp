<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>

<SCRIPT type="text/javascript">
	var zNodesParkingFee =[
   	   <c:forEach items="${parkingLotTree}" var="entity" varStatus="index">
   	   		{ id:'${entity.code}', pId:'${entity.parent.code}', name:'${entity.name}'}<c:if test="${!index.last}">,</c:if>
   	   </c:forEach>
   	];
   	
   	$(document).ready(function(){
   		GenerateSelectZTree("ParkingFee",zNodesParkingFee,"parkingLotParkingFee","${parkingLot.code}",changeParkingFeeLot);
   		
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
   	
   	//检查费用的开始时间与结束时间是否重复
   	function checkTimeParkingFee(e){
   		var id=$("#id").val();
   		var parkingLotParkingFeeId=$("#parkingLotParkingFeeId").val();
   		var type=$("#type").val();
   		var week=$("#week").val();
   		var startTime=$("#startTime").val();
   		var endTime=$("#endTime").val();
   		if(parkingLotParkingFeeId.length>0&&week.length>0&&startTime.length>0&&endTime.length>0){
   		   	$.ajax({
   		        type: "GET",
   		        url: "${ctx}/work/parkingFee/checkTime",
   		        data:{id:id,parkingLotCode:parkingLotParkingFeeId,week:week,type:type,startTime:startTime,endTime:endTime},
   		        dataType: "json",
   		        success: function(res){
   		         	if(res){
   		         		alert("【开始时间】与【结束时间】，已存在重复时间段记录！");
   		         		$(e).val('');
   		         		$(e).focus();
   		         	}
   		        }
   		    });
   		}
   	}
   	
   	//选择周前检查类型与场区
   	function checkRequiredParkingFee(e){
   		var type=$("#type").val();
   		var parkingLotParkingFeeId=$("#parkingLotParkingFeeId").val();
   		if(parkingLotParkingFeeId.length==0){
   			alert("选择周前，场区不能为空");
   			$(e).val('');
   			$(e).prev().html($(e).find("option:selected").text());
   			$("#parkingLotParkingFeeId").focus();
   		}else if(type.length==0){
   			alert("选择周前，类型不能为空");
   			$(e).val('');
   			$(e).prev().html($(e).find("option:selected").text());
   			$("#type").focus();
   		}
   	}
   	
   	//改变场区时
   	function changeParkingFeeLot(){
   		$("#type").val('');
   		$("#type").prev().html($("#type").find("option:selected").text());
   		$("#week").val('');
   		$("#week").prev().html($("#week").find("option:selected").text());
   	}
   	
  	//改变type时
   	function changeParkingFeeType(){
   		$("#week").val('');
   		$("#week").prev().html($("#week").find("option:selected").text());
   	}
   	
</SCRIPT>
<div id="contentParkingFee" class="pageContent">
	<form method="post" action="${ctx}/work/parkingFee/${action}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" id="id" name="id" value="${id}">
		<vsc:token tokenName="work.passages.create"></vsc:token>
		<vsc:callback></vsc:callback>
		<div class="pageFormContent" layoutH="56">
			<table class="formTable">
				<tr>
					<td class="fieldName"><label><span class="required">*</span>场区:</label></td>
					<td class="fieldInput" colspan="3">
						<input id="parkingLotParkingFeeId" name="parkingLotCode" value="${parkingLot.code}" type="hidden" />
						<label><input validate="{required:true}" id="parkingLotParkingFeeName" value="${parkingLot.name}" readonly="readonly"/></label>
						<a class="btnLook" title="选择场区" href="#" onclick="showSelectZTreeMenu(this,'ParkingFee');"></a>
						<span class="info">选择</span>
						<input id="claerBtn" type='button' style="margin-left: 5px;" value='清空' onclick='clearSelectZTreeBtn("parkingLotParkingFee");changeParkingFeeLot();' />
					</td>
				</tr>
				<tr>
					<td class="fieldName"><label><span class="required">*</span>类型:</label></td>
					<td class="fieldInput">
						<label>
							<select class="combox" onchange="changeParkingFeeType();" id="type" name="type" validate="{required:true}" selectedValue="${vm.type}"  dataUrl="${ctx}/static/js/data/parkingFee_type.json">
							<vsc:headoption text="请选择"></vsc:headoption>
							</select>
						</label>
						<span for="type" generated="true" style="display: none" class="error"></span>
					</td>
					<td class="fieldName"><label><span class="required">*</span>周:</label></td>
					<td class="fieldInput">
						<label>
							<select onchange="checkRequiredParkingFee(this);" class="combox" id="week" name="week" validate="{required:true}" selectedValue="${vm.week}"  dataUrl="${ctx}/static/js/data/week.json">
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
						<input type="text" class="dateIco" id="startTime" value="${vm.startTime}" name="startTime" readonly="true" onchange="checkTimeParkingFee(this);" onclick="WdatePicker({readOnly:true,dateFmt:'HH:mm',maxDate:'#F{$dp.$D(\'endTime\')}'});" validate="{required:true}" />
						<input type="text" style="display: none;" class="dateIco" id="dateStartTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',onpicked:pickedFuncStartTime,maxDate:'#F{$dp.$D(\'dateEndTime\')}'})" value="${vm.startTime}" readonly="true" />
						</label>
						<span for="startTime" generated="true" style="display: none" class="error"></span>
					</td>
					<td class="fieldName"><label><span class="required">*</span>结束时间:</label></td>
					<td class="fieldInput">
						<label>
						<input type="text" class="dateIco" id="endTime" value="${vm.endTime}" name="endTime" readonly="true" onchange="checkTimeParkingFee(this);" onclick="WdatePicker({readOnly:true,dateFmt:'HH:mm',minDate:'#F{$dp.$D(\'startTime\')}'});" validate="{required:true}" />
						<input type="text" style="display: none;" class="dateIco" id="dateEndTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',onpicked:pickedFuncEndTime,minDate:'#F{$dp.$D(\'dateStartTime\')}'})" value="${vm.endTime}" readonly="true" />
						</label>
						<span for="endTime" generated="true" style="display: none" class="error"></span>
					</td>
				</tr>
				<tr>
					<td class="fieldName"><label><span class="required">*</span>费率（分钟）:</label></td>
					<td class="fieldInput">
						<label>
							<input type="text" readonly="readonly" value="60"/>
						</label>
					</td>
					<td class="fieldName"><label><span class="required">*</span>费用:</label></td>
					<td class="fieldInput">
						<label>
							<input type="text" name="fee" id="fee" value="${vm.fee }" validate="{required:true,number:true,min:0}"/>
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