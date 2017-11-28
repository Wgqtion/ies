<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	response.setStatus(200);
%>

<!DOCTYPE html>
<html>
<head>
<title>不允许重复提交数据</title>
</head>

<body>
	<h2>不允许重复提交数据.请确认:</h2>
	<p>1.是否多个窗体同时打开相同的表单页。</p>
	<p>2.多次提交同一个表单页。</p>
	<p>
		<a href="<c:url value="/"/>">返回首页</a>
	</p>
</body>
</html>