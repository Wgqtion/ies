<%@tag import="org.apache.commons.lang3.ArrayUtils"%>
<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<%@ attribute name="cacheName" type="java.lang.String" required="true"%>
<%@ attribute name="objectToFind" type="java.lang.Object" required="true"%>
<%@ attribute name="yesStr" type="java.lang.String" %>
<%@ attribute name="noStr" type="java.lang.String" %>  
<% 
   Object[] cacheValues=request.getParameterValues(cacheName);  
   if(ArrayUtils.isEmpty(cacheValues)){
	   cacheValues=request.getParameterValues(cacheName+"[]");
   } 
   if(ArrayUtils.isEmpty(cacheValues)){ 
	   Object obj = request.getAttribute(cacheName);
		if (obj != null) {
			if (obj.getClass().isArray()) {
				cacheValues=(Object [])obj;
			}
		}
   } 
   request.setAttribute("cacheValues", cacheValues);
%>
<vsc:defaultIfIndexOfArray objectToFind="${objectToFind}" array="${cacheValues}" yesStr="${yesStr}" noStr="${noStr}"></vsc:defaultIfIndexOfArray>
 

 
  
