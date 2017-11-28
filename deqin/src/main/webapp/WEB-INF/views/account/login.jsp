<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta HTTP-EQUIV="pragma" CONTENT="no-cache"/>
<meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate"/>
<meta HTTP-EQUIV="expires" CONTENT="0"/>
<title>上海宜事智能地锁管理平台</title>
<link href="${ctx}/static/styles/themes/css/login.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/static/images/favicon.ico?222" rel="shortcut icon" type="image/x-icon" />
<script src="${ctx}/static/js/jquery/1.7.2/jquery.js" type="text/javascript"></script>
<script src="${ctx}/static/js/jquery-validation/1.10.0/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctx}/static/js/jquery/jquery.metadata.js" type="text/javascript"></script>
<script src="${ctx}/static/js/dwz/dwz.regional.zh.js" type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#loginForm").validate({
			groups : {
				userpass : "username password vcaptcha"
			}
		});
	});
</script>
</head>
<body>
	<div class="verticalAlign"></div>
	<!--定位元素，页面不显示，只为页面容器整体垂直对齐作参照-->
	<div class="divAll">
		<!--父级垂直居中-->
		<!--子级上中下排列-->
		<div id='divTop' class="divTop"></div>
		<div id='divBody' class="divBody">
			<div id="login">
				<div id="login_content">
					<div class="loginForm">
						<form id="loginForm" action="${ctx}/login" method="post">
							<p style="height: 25px;margin-left: 150px;">
								<label for="userpass" generated="true" class="error"> <%
						 	String error = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
						 	if (error != null) {
						 %> 登录失败，请重试. <%
						 	}
						 %>
								</label>
							</p>
							<p>
								<label>用户名：</label> <input type="text" id="username" name="username" value="${username}" class="login_input" validate="{required:true,messages:{required:'请输入用户名'}}" />
							</p>
							<p>
								<label>密&nbsp;&nbsp;码：</label> <input type="password" id="password" name="password" class="login_input" validate="{required:true,messages:{required:'请输入密码'}}" />
							</p> 
							<!-- 
							<p> 
								<label>验证码：</label> <input class="code" type="text" size="9" name="vcaptcha" validate="{required:true,messages:{required:'请输入验证码'}}">
							<span>
						<img src="${ctx}/vcaptcha.jpg" width="60" height="22" onclick="this.src='${ctx}/vcaptcha.jpg?d='+new Date()*1"> </span>
							</p> -->
							<div class="login_bar" style="padding-left: 175px;">	
							    <label style="width: 140px;margin-right: 5px"><a href="#"> </a></label>						    
								<input class="sub" type="submit" value=" " />
							</div>
						</form>
					</div>
				</div>
			</div>
			</div>			 
		</div>
</body>
</html>
