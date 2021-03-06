<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<script type="text/javascript">
var parkingSurplusTotalChart;
$(function() {
	// 基于准备好的dom，初始化echarts图表
	parkingSurplusTotalChart = echarts
			.init(document.getElementById('parkingSurplusTotal_div'));
	var option = {
		tooltip : {
			show : true,
			formatter : '{b}</br>{a}:{c}位'
		},
		title : {
			text : '全视频余位统计图表',
			x : 'center',
			top : 10
		},
		legend : {
			data : [ '余位'],
			x : 'center',
			y : 'bottom'
		},
		color:['#90EE90'],
		toolbox : {
			show : true,
			feature : {
				saveAsImage : {}
			}
		},
		xAxis : {
			type : 'category',
			axisLabel : {
				interval : 0,//横轴信息全部显示  
			},
			data : []
		},
		yAxis : {
			type : 'value'
		},
		series : []
	};
	// 为echarts对象加载数据 
	parkingSurplusTotalChart.setOption(option);
	
	parkingSurplusTotalData();
});
function parkingSurplusTotalData() {
	var formData = $("#parkingSurplusTotalForm").serialize();
	$.ajax({
		url : "${ctx}/work/chart/parkingSurplusTotalData",
		type : "post",
		data : formData,
		success : function(data) {
			if (data.status == 'success') {
				var result = data.data;
				var names=[];
				var surplustotal=[];
				for (var i = 0; i < result.length; i++) {
					names.push(result[i].name);
					surplustotal.push(result[i].surplustotal);
				}
				parkingSurplusTotalChart.hideLoading(); //隐藏加载动画
				parkingSurplusTotalChart.setOption({ //加载数据图表
					xAxis : {
						data : names
					},
					series : [ {
						"name" : "余位",
						"type" : "bar",
						data : surplustotal
					}]
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
<div class="pageHeader" >
	<form id="parkingSurplusTotalForm" action="#">
		<table>
			<tr >
				<td width="50"><button type="button" onclick="parkingSurplusTotalData();">统计</button></td>
			</tr>
		</table>
	</form>
</div>
<div id="parkingSurplusTotal_div" class="pageContent" style="height:400px;"></div>
</div>