<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<meta HTTP-EQUIV="pragma" CONTENT="no-cache" />
<meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate" />
<meta HTTP-EQUIV="expires" CONTENT="0">
	<title>${sysName}</title>
	<link rel="shortcut icon" href="${ctx}/static/images/favicon.ico?222" />
	<link href="${ctx}/static/styles/themes/silver/style.css" rel="stylesheet" type="text/css" media="screen" />
	<link href="${ctx}/static/styles/themes/css/core.css" rel="stylesheet" type="text/css" media="screen" />
	<link href="${ctx}/static/styles/themes/css/print.css" rel="stylesheet" type="text/css" media="print" />
	<link href="${ctx}/static/js/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen" />
	<link href="${ctx}/static/js/jquery-autocomplete/css/thickbox.css" rel="stylesheet" type="text/css" media="screen" />
	<link href="${ctx}/static/js/jquery-autocomplete/css/jquery.autocomplete.css" rel="stylesheet" type="text/css" media="screen" />
	<link rel="stylesheet" href="${ctx}/static/js/jquery-validation/poshytip/tip-yellow/tip-yellow.css" type="text/css" />
	<link rel="stylesheet" href="${ctx}/static/js/jquery-validation/poshytip/tip-violet/tip-violet.css" type="text/css" />
	<link rel="stylesheet" href="${ctx}/static/js/jquery-validation/poshytip/tip-darkgray/tip-darkgray.css" type="text/css" />
	<link rel="stylesheet" href="${ctx}/static/js/jquery-validation/poshytip/tip-skyblue/tip-skyblue.css" type="text/css" />
	<link rel="stylesheet" href="${ctx}/static/js/jquery-validation/poshytip/tip-yellowsimple/tip-yellowsimple.css" type="text/css" />
	<link rel="stylesheet" href="${ctx}/static/js/jquery-validation/poshytip/tip-twitter/tip-twitter.css" type="text/css" />
	<link rel="stylesheet" href="${ctx}/static/js/jquery-validation/poshytip/tip-green/tip-green.css" type="text/css" />
	<link rel="stylesheet" href="${ctx}/static/styles/vsc/main.css" type="text/css" />
	<link rel="stylesheet" href="${ctx}/static/styles/vsc/upload.css" type="text/css" />
	<link rel="stylesheet" href="${ctx}/static/js/skippr/css/jquery.skippr.css" type="text/css" />
	<!--[if IE]>
<link href="${ctx}/static/styles/themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
<![endif]-->


	<%@ include file="/WEB-INF/inc/include.js.jsp"%>
	<script type="text/javascript">
		$(function() {
			DWZ.init("${ctx}/static/js/dwz/dwz.frag.xml", {
				loginUrl : "${ctx}/login/dialog",
				loginTitle : "登录",
				statusCode : {
					ok : 200,
					error : 300,
					timeout : 301
				},
				pageInfo : {
					pageNum : "pageNum",
					numPerPage : "numPerPage",
					orderField : "orderField",
					orderDirection : "orderDirection"
				},
				debug : false,
				callback : function() {
					initEnv();
					$("#themeList").theme({
						themeBase : "${ctx}/static/styles/themes"
					});
				},
				sysPara : {
					ctx : "${ctx}"
				}
			});
			

			setTimeout(function (){
				<c:forEach items="${homePage}" var="hp">
					$("#homeDiv").load("${ctx}${hp.value}");
				</c:forEach>
			},1000);
		});
		
	</script>
</head>
<body scroll="no">
	<div id="layout">
		<div id="header">
			<div class="headerNav">
				<a class="logo" href="#">标志</a>
				<ul class="nav">
					<li>v.2.0.3.1</li>
					<li>您好,<shiro:principal property="name"/></li>
					<li><a title="重置密码" href="${ctx}/sys/user/reset/<shiro:principal property="id" />" target="dialog" rel="sys_user_reset">重置密码</a></li>
					<li><a href="${ctx}/logout">安全退出</a></li>
				</ul>
			</div>
		</div>
		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse">
						<div></div>
					</div>
				</div>
			</div>
			<div id="sidebar">
				<div class="toggleCollapse">
					<h2>主菜单</h2>
					<div>收缩</div>
				</div>
				
				<!-- 菜单 -->
				<div class="accordion">
					<!-- 1级菜单 -->
					<c:forEach items="${resources1}" var="resource1">
						<div class="accordionHeader">
							<h2>
								<span>Folder</span>${resource1.name}
							</h2>
						</div>
						<div class="accordionContent">
							<ul class="tree treeFolder">
								<c:forEach items="${resources2}" var="resource2">
									<c:if test="${resource2.parent.code eq resource1.code}">
										<li><a>${resource2.name}</a>
											<ul>
												<c:forEach items="${resources3}" var="resource3">
													<c:if test="${resource3.parent.code eq resource2.code}">
														<li><a href="<c:if test="${resource3.isLocal}">${ctx}</c:if><c:if test="${!resource3.isLocal}">http://</c:if>${resource3.value}" title="${resource3.name}" target="${resource3.urlTarget}" rel="${resource3.code}">${resource3.name}</a></li>
													</c:if>
												</c:forEach>
											</ul>
										</li>
									</c:if>
								</c:forEach>
								<c:forEach items="${resources3}" var="resource3">
									<c:if test="${resource3.parent.code eq resource1.code}">
										<li><a href="<c:if test="${resource3.isLocal}">${ctx}</c:if><c:if test="${!resource3.isLocal}">http://</c:if>${resource3.value}" title="${resource3.name}" target="${resource3.urlTarget}" rel="${resource3.code}">${resource3.name}</a></li>
									</c:if>
								</c:forEach>
							</ul>
						</div>
					</c:forEach>
				</div>
				
				
			</div>
		</div>
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent">
						<!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main"><a href="javascript:;"><span><span class="home_icon">我的主页</span></span></a></li>
						</ul>
					</div>
					<div class="tabsLeft">left</div>
					<!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div>
					<!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:;">我的主页</a></li>
				</ul>
				<div class="navTab-panel tabsPageContent layoutBox">
					<div class="page unitBox">
						<div class="index-portal-body panelContent">
							<div id="homeDiv"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="footer">
		Copyright &copy; 2017-2018 <a href="#" target="dialog">版权所有</a>
	</div>
</body>
</html>
