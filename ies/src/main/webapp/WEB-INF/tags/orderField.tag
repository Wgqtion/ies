<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ attribute name="name" type="java.lang.String" required="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%request.setAttribute("tagOrderField", name);%>
orderField="${tagOrderField}" class="needorder <c:if test="${tagOrderField eq param.orderField}">${param.orderDirection}</c:if>"