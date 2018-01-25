<%@tag import="org.apache.commons.lang3.StringUtils"%>
<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<%@ attribute name="defaultStr" type="java.lang.String"%>
<%@ attribute name="str" type="java.lang.String"%>  
 <%=StringUtils.defaultIfBlank(str, defaultStr)%> 
  