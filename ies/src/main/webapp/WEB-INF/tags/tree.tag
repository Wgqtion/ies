<%@tag import="org.apache.commons.lang3.StringUtils"%>
<%@tag import="java.util.List"%>
<%@tag import="com.google.common.collect.Lists"%>
<%@tag import="org.apache.commons.beanutils.PropertyUtils"%>
<%@ tag pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<%@ attribute name="root" type="java.lang.Object" required="true"%>
<%@ attribute name="idFieldName" type="java.lang.String"%>
<%@ attribute name="pidFieldName" type="java.lang.String"%>
<%@ attribute name="childFieldName" type="java.lang.String"%>
<%@ attribute name="nameFieldName" type="java.lang.String"%>
<%@ attribute name="noteTemplet" type="java.lang.String" required="false"%>
<%@ attribute name="onClickTemplet" type="java.lang.String" required="false"%>
<%@ attribute name="isRoot" type="java.lang.Boolean" required="true"%>
<%@ attribute name="className" type="java.lang.String"%>
<%@ attribute name="curSelectid" type="java.lang.String"%>
<%
	if (StringUtils.isEmpty(noteTemplet)) {
		request.setAttribute("noteTemplet", "<a onclick=\"{onclick}\" href=\"javascript:void(0)\">{name}</a>");
	}
	if (StringUtils.isEmpty(onClickTemplet)) {
		request.setAttribute("onClickTemplet", "");
	}
	if (StringUtils.isEmpty(className)) {
		request.setAttribute("className", "tree treeFolder");
	}
	if (StringUtils.isEmpty(curSelectid)) {
		request.setAttribute("curSelectid", "");
	}
%>

<c:if test="${not empty root}">
	<%
		if (!(root instanceof List)) {
			List l=Lists.newArrayList();
			l.add(root);
			request.setAttribute("root",l);
		}
	%>
	<ul <c:if test="${isRoot}">class="${className}"</c:if>>
		<c:forEach items="${root}" var="item" varStatus="index">
			<c:set  var="onClick" value="${fn:replace(fn:replace(fn:replace(onClickTemplet,'{id}',item[idFieldName]),'{name}',item[nameFieldName]),'{pname}',item.parent[nameFieldName])}"></c:set>
			<li <c:if test="${curSelectid==item[idFieldName]}">class="selected"</c:if>>
			${fn:replace(fn:replace(fn:replace(noteTemplet,'{name}',item[nameFieldName]),'{onclick}',onClick),'{id}',item[idFieldName])}
			 <vsc:tree root="${item[childFieldName]}"
				isRoot="false" childFieldName="${childFieldName}"
				idFieldName="${idFieldName}" nameFieldName="${nameFieldName}"
				noteTemplet="${noteTemplet}" pidFieldName="${pidFieldName}" 
				onClickTemplet="${onClickTemplet}" className="${className}" curSelectid="${curSelectid}"></vsc:tree>
			</li>
		</c:forEach>
	</ul> 
</c:if>