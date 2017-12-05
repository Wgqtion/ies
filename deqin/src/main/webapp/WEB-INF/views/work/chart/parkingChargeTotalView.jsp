<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>

<script type="text/javascript">
var myChart;
$(function () {
	
	// 基于准备好的dom，初始化echarts图表
    myChart = echarts.init(document.getElementById('parkingChargeTotal_div')); 
    
    var option = {
        tooltip: {
            show: true,
            formatter: '{b}</br>{a}:{c}元'
        },
        title: {
            text: '停车场收费统计图表',
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
    myChart.setOption(option); 
	
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
				myChart.hideLoading(); //隐藏加载动画
				myChart.setOption({ //加载数据图表
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


<div class="pageHeader">
	<form id="parkingChargeTotalForm" action="#">
		<table style="width: 40%;">
			<tr >
				<td align="right" width="10%">日期:</td>
				<td width="40%"><input type="text" class="date" size="9"
					value="${reportView.startDate}" dateFmt="yyyy-MM-dd"
					name="startDate" readonly="true" /> - <input
					type="text" class="date" size="9" value="${reportView.endDate}"
					dateFmt="yyyy-MM-dd" name="endDate" readonly="true" /></td>
				<td align="right" width="15%">停车场:</td>
				<td width="25%"><select id="selectId" name="selectId">
					<option value="">全部</option>
					<c:forEach items="${lotAreaList}" var="lotArea">
						<option value="${lotArea.id}">${lotArea.name}</option>
					</c:forEach>
				</select></td>
				<td width="10%"><button type="button" onclick="parkingChargeTotalData();">统计</button></td>
			</tr>
		</table>
	</form>
</div>
<div id="parkingChargeTotal_div" style="height:400px;"></div>
