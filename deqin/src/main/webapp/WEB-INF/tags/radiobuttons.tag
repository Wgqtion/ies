<%@tag import="org.apache.commons.lang3.ObjectUtils"%>
<%@tag import="org.apache.commons.beanutils.PropertyUtils"%>
<%@tag import="org.springframework.util.CollectionUtils"%>
<%@tag import="java.util.Collections"%>
<%@tag import="java.util.Collection"%>
<%@tag import="com.google.common.collect.Lists"%>
<%@tag import="java.util.List"%>
<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" import="org.apache.commons.lang3.StringUtils"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<%@ attribute name="name" type="java.lang.String" required="true"%>
<%@ attribute name="value" type="java.lang.Object" required="false"%> 
<%@ attribute name="headValue" type="java.lang.String" required="false"%>
<%@ attribute name="headText" type="java.lang.String" required="false"%>
<%@ attribute name="items" type="java.lang.Object" required="true"%>
<%@ attribute name="itemLabel" type="java.lang.String" required="false"%>
<%@ attribute name="itemValue" type="java.lang.String" required="false"%> 
<% 
List radioValue=Lists.newArrayList();
if(items instanceof Collection){ 
	 Collections.addAll(radioValue, items);
}else if(org.springframework.util.ObjectUtils.isArray(items)){ 
	Collections.addAll(radioValue,CollectionUtils.arrayToList(items));
}else {
	radioValue.add(items);
} 
if(StringUtils.isNotEmpty(headText)){
%><input type="radio" name="${name}" value="<%=StringUtils.isEmpty(headValue)?"":headValue%>" <%=ObjectUtils.equals(headValue, value)? " checked='checked'":""%>}/><%=headText%>
<%	
}
for (Object item : radioValue) {
Object v=StringUtils.isNotBlank(itemValue)?PropertyUtils.getProperty(item, itemValue):item;
Object l=StringUtils.isNotBlank(itemLabel)?PropertyUtils.getProperty(item, itemLabel):item;
%><input type="radio" name="${name}" value="<%=v%>" <%=ObjectUtils.equals(v, value)? " checked='checked'":""%>}/><%=l%>
<%		
} 
%>

 



