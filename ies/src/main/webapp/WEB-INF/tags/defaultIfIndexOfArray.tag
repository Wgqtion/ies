<%@tag import="org.apache.commons.lang3.StringUtils"%>
<%@tag import="org.apache.commons.lang3.ArrayUtils"%>
<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<%@ attribute name="array" type="java.lang.Object[]" required="true"%>
<%@ attribute name="objectToFind" type="java.lang.Object" required="true"%>
<%@ attribute name="yesStr" type="java.lang.String" %>
<%@ attribute name="noStr" type="java.lang.String" %> 
<%     
   if(ArrayUtils.INDEX_NOT_FOUND==ArrayUtils.indexOf(array, objectToFind)){ 
	   if(StringUtils.isNotEmpty(noStr)){
	   %><%=noStr%> <% }
   }else{
	   if(StringUtils.isNotEmpty(yesStr)){
        %><%=yesStr%><%  }
   }
%>
 

 
  
