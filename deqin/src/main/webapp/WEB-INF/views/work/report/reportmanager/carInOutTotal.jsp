<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>

<script type="text/javascript">

$(function () {
	var chart;
	
    $(document).ready(function() {
    	chart = new Highcharts.Chart({
    		chart: {
                renderTo: 'carInOutTotal_div',
                type: 'column'
            },
            title: {
                text: '${startTime} 至 ${endTime}车辆进出次数统计报表'
            },
            subtitle: {
                text: ''
            },
            xAxis: {
            	//指定x轴分组
            	categories: [<c:forEach items="${lm}" var="vm" varStatus="vi">'${vm['name']}'<c:if test="${!vi.last}">,</c:if></c:forEach>]  
            },
            yAxis: {
                min: 0,
                title: {
                    text: '次数'
                }
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                    '<td style="padding:0"><b>{point.y:.0f} 次</b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                useHTML: true
            },
            series: [
					{name:'进',data:[
						<c:forEach items="${lm}" var="vm" varStatus="vi">${vm['innum']}<c:if test="${empty vm['innum'] }">0</c:if><c:if test="${!vi.last}">,</c:if></c:forEach> 
					]},
	            	{name:'出',data:[
						<c:forEach items="${lm}" var="vm" varStatus="vi">${vm['outnum']}<c:if test="${empty vm['outnum'] }">0</c:if><c:if test="${!vi.last}">,</c:if></c:forEach> 
					]}
            ]
    	});
    });
});
</script>


<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);"
		action="${ctx}/work/report/reportmanager/carInOutTotal"
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
<div class="pageContent">
	<table width="98%" layoutH="60">
		<tr>
			<td colspan="3" align="center">
				<div id="carInOutTotal_div"
					style="min-width: 310px; height: 400px; margin: 0 auto"></div>
			</td>
		</tr>
	</table>
</div>
