<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" import="org.apache.commons.lang3.StringUtils"%>
<%@ attribute name="text" type="java.lang.String" required="false"%>
<%@ attribute name="value" type="java.lang.String" required="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<option value="<%=StringUtils.isEmpty(value)?"":value%>"><%=StringUtils.isEmpty(text)?"不限":text%></option> 
 
 