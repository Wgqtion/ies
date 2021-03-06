<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<script type="text/javascript">
var parkingChargeTotalChart;
$(function () {
	
	// 基于准备好的dom，初始化echarts图表
    parkingChargeTotalChart = echarts.init(document.getElementById('parkingChargeTotal_div')); 
    
    var option = {
        tooltip: {
            show: true,
            formatter: '{b}</br>{a}:{c}元'
        },
        title: {
            text: '进出口收费统计图表',
            x:'center',
            top:10
        },
        legend: {
            data:['应收金额','实收金额'],
            x: 'center',
			y: 'bottom'
        },
        color:['#90EE90','#FF3030'],
        toolbox: {
            show: true,
            feature: {
                saveAsImage: {}
            }
        },
        xAxis :{
                type : 'category',
                axisLabel:{  
                    interval:0,//横轴信息全部显示  
               	},
                data : []
        } ,
        yAxis : {
            type : 'value'
        },
        series : []
    };

    // 为echarts对象加载数据 
    parkingChargeTotalChart.setOption(option); 
	
    parkingChargeTotalData();
});

function parkingChargeTotalData(){
	var formData = $("#parkingChargeTotalForm").serialize();
	$.ajax({
		url : "${ctx}/work/chart/parkingChargeTotalData",
		type : "post",
		data : formData,
		success : function(data) {
			if (data.status == 'success') {
				var result = data.data;
				var names=[];
				var yspayamount=[];
				var sspayamount=[];
				for (var i = 0; i < result.length; i++) {
					names.push(result[i].name);
					yspayamount.push(result[i].yspayamount);
					sspayamount.push(result[i].sspayamount);
				}
				parkingChargeTotalChart.hideLoading(); //隐藏加载动画
				parkingChargeTotalChart.setOption({ //加载数据图表
					xAxis : {
						data : names
					},
					series : [ {
						"name":"应收金额",
		                "type":"bar",
		                "data":yspayamount
					}, {
						"name":"实收金额",
		                "type":"bar",
		                "data":sspayamount
					} ]
				});
			}else{
				alert("图表请求数据失败!");
			}
		},
		error : function(e) {
			alert("图表请求数据失败!");
		}
	});
}
</script>


<div style="width: 100%;">
<div class="pageHeader">
	<form id="parkingChargeTotalForm" action="#">
		<table>
			<tr>
				<td align="right" width="50">日期:</td>
				<td width="250"><input type="text" class="date" size="9"
					value="${reportView.startDate}" dateFmt="yyyy-MM-dd"
					name="startDate" readonly="true" /> - <input
					type="text" class="date" size="9" value="${reportView.endDate}"
					dateFmt="yyyy-MM-dd" name="endDate" readonly="true" /></td>
				<td align="right" width="50">停车场:</td>
				<td width="50"><select id="selectId" name="selectId">
					<option value="">全部</option>
					<c:forEach items="${lotAreaList}" var="lotArea">
						<option value="${lotArea.id}">${lotArea.name}</option>
					</c:forEach>
				</select></td>
				<td align="right" width="50">分组:</td>
				<td><input type="radio" id="passagesRadio" name="selectType" value="1" checked="checked" /><label for="passagesRadio">进出口</label><input type="radio" id="memberRadio" name="selectType" value="0" /><label for="memberRadio">收费员</label></td>
				<td width="50"><button type="button" onclick="parkingChargeTotalData();">统计</button></td>
			</tr>
		</table>
	</form>
</div>
<div id="parkingChargeTotal_div" class="pageContent" style="height:400px;"></div>
</div>