<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
$(function () {

<c:forEach items="${vl}"  var="item" >
    try{
        if(${item['id']}){
            $('#container_${item['id']}').highcharts({
                chart: {
                    plotBackgroundColor: null,
                    plotBorderWidth: null,
                    plotShadow: false
                },
                title: {
                    text: '${item['name']}'
                },
                tooltip: {
                    pointFormat: '{series.name}: <b>{point.percentage:.0f}%</b>'
                },
                plotOptions: {
                    pie: {
                        allowPointSelect: false,
                        cursor: 'pointer',
                        dataLabels: {
                            enabled: true,
                            color: '#000000',
                            connectorColor: '#000000',
                            format: '<b>{point.name}</b>: {point.percentage:.0f} %'
                        }
                    }
                },
                series: [{
                    type: 'pie',
                    name: '数量',
                    data: [
                        ['已停车', ${item['onlinenum']}  ],
                        ['空闲',    ${item['total']-item['onlinenum']}] 
                    ]
                }]
            });
        }
    }cath(er){
    }
    
   </c:forEach> 
});
