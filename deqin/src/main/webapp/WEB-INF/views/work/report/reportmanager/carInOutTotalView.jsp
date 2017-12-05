<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>

<script type="text/javascript">
var myChart;
$(function() {
	// 基于准备好的dom，初始化echarts图表
	myChart = echarts
			.init(document.getElementById('carInOutTotal_div'));
	var option = {
		tooltip : {
			show : true,
			formatter : '{b}</br>{a}:{c}次'
		},
		title : {
			text : '停车场进出次数统计图表',
			x : 'center',
			top : 10
		},
		legend : {
			data : [ '进', '出' ],
			x : 'center',
			y : 'bottom'
		},
		toolbox : {
			show : true,
			feature : {
				dataZoom : {
					yAxisIndex : 'none'
				},
				magicType : {
					type : [ 'line', 'bar' ]
				},
				restore : {},
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
	myChart.setOption(option);
	
	carInOutTotalData();
});
function carInOutTotalData() {
	//${ctx}/work/report/reportmanager/carInOutTotalData
	var formData = $("#carInOutTotalForm").serialize();
	$.ajax({
		url : "${ctx}/work/report/reportmanager/carInOutTotalData",
		type : "post",
		data : formData,
		success : function(data) {
			if (data.status == 'success') {
				var result = data.data;
				var names=[];
				var innums=[];
				var outnums=[];
				for (var i = 0; i < result.length; i++) {
					names.push(result[i].name);
					innums.push(result[i].innum);
					outnums.push(result[i].outnum);
				}
				myChart.hideLoading(); //隐藏加载动画
				myChart.setOption({ //加载数据图表
					xAxis : {
						data : names
					},
					series : [ {
						"name" : "进",
						"type" : "bar",
						data : innums
					}, {
						"name" : "出",
						"type" : "bar",
						data : outnums
					} ]
				});
			}
		},
		error : function(e) {
			alert("图表请求数据失败!");
		}
	});
}
</script>


<div class="pageHeader" >
	<form id="carInOutTotalForm" action="#">
		<table style="width: 35%;">
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
				<td width="10%"><button type="button" onclick="carInOutTotalData();">统计</button></td>
			</tr>
		</table>
	</form>
</div>
<div id="carInOutTotal_div" style="height: 500px;"></div>
