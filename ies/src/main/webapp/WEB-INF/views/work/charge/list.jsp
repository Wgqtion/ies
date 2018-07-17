<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/inc/include.inc.jsp" %>
<vsc:pagerForm action="#rel#" id="pagerForm"></vsc:pagerForm>

<div class="pageHeader">
    <form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/work/charge" method="post">
    </form>
    <div class="searchBar">
        <ul class="searchContent">
            <li>名称:${chargesSettings.name}</li>
            <li>计费类型:<s:message code="chargesSettings.chargeStandard.${chargesSettings.chargeStandard}"/></li>
            <li>按次收费单价:${chargesSettings.unitPrice}</li>
            <li>按时收费时间单位:${chargesSettings.priceTimeString}</li>
            <li>优惠减免费用:${chargesSettings.privilegeFee}</li>
            <li>最高费用:${chargesSettings.maxFee}</li>
            <li>描述:${chargesSettings.description}</li>
            <li><input id="chargesSettingsId" type="hidden" value="${chargesSettings.id}"></li>
        </ul>
        <div class="subBar">
            <ul>
                <div class="buttonContent">
                    <a class="button" title="编辑"
                       href="${ctx}/work/charge/settings/${chargesSettings.id}?navTabId=basic_charge"
                       target="dialog" rel="charges_update"><span>编辑</span></a>
                </div>
                </li>
            </ul>
        </div>
    </div>

</div>
<div class="pageContent">
    <div class="panelBar">
        <ul class="toolBar">
            <li><a class="add" title="添加" href="${ctx}/work/charge/new/${chargesSettings.id}?navTabId=basic_charge"
                   target="dialog" rel="charge_new"><span>添加</span></a></li>
            <li><a class="edit" title="编辑" href="${ctx}/work/charge/update/{sid}?navTabId=basic_charge"
                   target="dialog" rel="charge_update"
                   warn="请选择一个记录"><span>编辑</span></a></li>
            <li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="${ctx}/work/charge/delete"
                   class="delete"><span>删除</span></a></li>
            <li class="line">line</li>
        </ul>
    </div>
    <table class="table" width="100%" layoutH="175">
        <thead>
        <tr>
            <th width="40" align="center">序号</th>
            <th width="30"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
            <th <vsc:orderField name="week"/>>周</th>
            <th <vsc:orderField name="startTime"/>>开始时间</th>
            <th <vsc:orderField name="endTime"/>>结束时间</th>
            <th <vsc:orderField name="fee"/>>单价/元</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${page.content}" var="varitem" varStatus="varindex">
            <tr target="sid" rel="${varitem.id}">
                <td align="center">${varindex.count+(page.number * page.size)}</td>
                <td><input name="ids" value="${varitem.id}" type="checkbox"></td>
                <td><s:message code="charge.week.${varitem.week}"/></td>
                <td>${varitem.startTimeString}</td>
                <td>${varitem.endTimeString}</td>
                <td>${varitem.fee}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="panelBar">
        <vsc:pagination page="${page}"
                        numPerPageOnchange="navTabPageBreak({numPerPage:this.value})"></vsc:pagination>
    </div>
</div>

