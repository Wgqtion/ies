<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<%
	response.setStatus(200);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>验证码错误</title>
<link rel="stylesheet" type="text/css" media="screen" href="${ctx}/static/styles/vsc/captchaerror.css" />
</head>
<body>
<div id="wrapper">
<a class="logo" href="#"></a>
  <div id="main">
    <div id="header">
      <h1><img class="icon" src="${ctx}/static/styles/vsc/images/error_icon.png" width="64" />验证码错误</h1>
    </div>
    <div id="content">
      <h2>请检查您的输入是否有误.</h2>
      <p></p>
      <div class="utilities"> 
        <div class="button-container right"><a class="button" href="<c:url value="/"/>">返回登录</a></div>
        <div style="clear: both"></div>
      </div>
    </div>    
  </div>
</div> 
</body>
</html>