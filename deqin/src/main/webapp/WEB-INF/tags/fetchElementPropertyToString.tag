<%@tag import="com.vsc.util.CoreUtils" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ attribute name="list" type="java.util.List" required="true"%>
<%@ attribute name="propertyName" type="java.lang.String" required="true"%>
<%@ attribute name="separator" type="java.lang.String" required="false"%>
<%=CoreUtils.fetchElementPropertyToString(list, propertyName, separator)%>

