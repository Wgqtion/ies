<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/inc/include.inc.jsp" %>
<div class="pageContent" style="padding: 5px">
    <div>
        <%--显示树形结构栏目--%>
        <div layoutH="10"
             style="float: left; display: block; overflow: auto; width: 17%; border: solid 1px #CCC; line-height: 21px; background: #fff">
            <ul class="tree treeFolder expand">
                <c:forEach items="${vl}" var="root">
                    <li><a title="${root.description}" href='${ctx}/work/charge/list?chargesSettingsId=${root.id}'
                           target='ajax' rel='timeSectionBox'>${root.name}</a>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <div id="timeSectionBox" class="unitBox" style="margin-left: 220px;">
            <%@ include file="/WEB-INF/views/work/charge/list.jsp" %>
        </div>
    </div>
</div>