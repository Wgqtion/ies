<%@tag import="org.apache.commons.lang3.StringUtils"%>
<%@tag import="org.apache.commons.lang3.ArrayUtils"%>
<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<%@ attribute name="cacheName" type="java.lang.String" required="true"%>
<%@ attribute name="cacheId" type="java.lang.String" required="false"%>
<%
    if(StringUtils.isNotBlank(cacheName)){
	Object[] cacheValues = request.getParameterValues(cacheName);
	if (ArrayUtils.isEmpty(cacheValues)) {
		cacheValues = request.getParameterValues(cacheName + "[]");
	}
	if (ArrayUtils.isEmpty(cacheValues)) {
		Object obj = request.getAttribute(cacheName);
		if (obj != null) {
			if (obj.getClass().isArray()) {
				cacheValues=(Object[])obj;
			}
		}
	}
    if(StringUtils.isNotBlank(cacheId)){%>
    	<%="<div id=\"" + cacheId+ "\">"%>
   <% } 
	if (ArrayUtils.isNotEmpty(cacheValues)) {
		for (int i = 0; i < cacheValues.length; i++) {
			%>
			<%="<input type=\"checkbox\" checked=\"checked\" name=\"" + cacheName
										+ "\" class=\"cachebox\" value=\"" + cacheValues[i] + "\"/>"%>
			<%
		}
	}
	 if(StringUtils.isNotBlank(cacheId)){%>
	    <%="</div>"%><%
	    } 
    }
%>




