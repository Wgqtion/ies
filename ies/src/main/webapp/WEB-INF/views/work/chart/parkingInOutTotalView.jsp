<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<script type="text/javascript">
var parkingInOutTotalChart;
$(function() {
	// 基于准备好的dom，初始化echarts图表
	parkingInOutTotalChart = echarts
			.init(document.getElementById('parkingInOutTotal_div'));
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
		color:['#90EE90','#FF3030'],
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
	parkingInOutTotalChart.setOption(option);
	
	parkingInOutTotalData();
});
function parkingInOutTotalData() {
	var formData = $("#parkingInOutTotalForm").serialize();
	$.ajax({
		url : "${ctx}/work/chart/parkingInOutTotalData",
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
				parkingInOutTotalChart.hideLoading(); //隐藏加载动画
				parkingInOutTotalChart.setOption({ //加载数据图表
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
	<form id="parkingInOutTotalForm" action="#">
		<table>
			<tr >
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
				<td width="50"><button type="button" onclick="parkingInOutTotalData();">统计</button></td>
			</tr>
		</table>
	</form>
</div>
<div id="parkingInOutTotal_div" class="pageContent" style="height:400px;"></div>
</div>