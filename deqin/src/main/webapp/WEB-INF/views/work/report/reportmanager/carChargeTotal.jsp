<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>

<script type="text/javascript">

$(function () {
	
	// 基于准备好的dom，初始化echarts图表
    var myChart = echarts.init(document.getElementById('carChargeTotal_div')); 
    
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
        toolbox: {
            show: true,
            feature: {
                dataZoom: {
                    yAxisIndex: 'none'
                },
                magicType: {type: ['line', 'bar']},
                restore: {},
                saveAsImage: {}
            }
        },
        xAxis :{
                type : 'category',
                axisLabel:{  
                    interval:0,//横轴信息全部显示  
               	},
                data : [<c:forEach items="${lm}" var="vm" varStatus="vi">'${vm['name']}'<c:if test="${!vi.last}">,</c:if></c:forEach>]
        } ,
        yAxis : {
            type : 'value'
        },
        series : [
            {
                "name":"应收金额",
                "type":"bar",
                "data":[<c:forEach items="${lm}" var="vm" varStatus="vi">${vm['YS_PAY_AMOUNT']}<c:if test="${empty vm['YS_PAY_AMOUNT'] }">0</c:if><c:if test="${!vi.last}">,</c:if></c:forEach>]
            },{
                "name":"实收金额",
                "type":"bar",
                "data":[<c:forEach items="${lm}" var="vm" varStatus="vi">${vm['SS_PAY_AMOUNT']}<c:if test="${empty vm['SS_PAY_AMOUNT'] }">0</c:if><c:if test="${!vi.last}">,</c:if></c:forEach>]
            }
        ]
    };

    // 为echarts对象加载数据 
    myChart.setOption(option); 
	


});
</script>


<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);"
		action="${ctx}/work/report/reportmanager/carChargeTotal"
		method="post">
		<div class="searchBar">
			<ul class="searchContent">
				<li><label>日期:</label> <input type="text" class="date" size="9"
					value="${startTime}" dateFmt="yyyy-MM-dd"
					name="search_GTE_startTime" readonly="true" /> - <input
					type="text" class="date" size="9" value="${endTime}"
					dateFmt="yyyy-MM-dd" name="search_LTE_endTime" readonly="true" /></li>
			</ul>
			<div class="subBar">
				<ul>
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">统计</button>
							</div>
						</div></li>
				</ul>
			</div>
		</div>
	</form>
</div>
<div id="carChargeTotal_div" style="height:500px;"></div>
