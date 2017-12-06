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
	<title>上海德勤智能停车管理平台</title>
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
			
			
			//homeDiv
			$("#homeDiv").load("${ctx}/work/chart/homeView");
		});
	</script>
</head>
<body scroll="no">
	<div id="layout">
		<div id="header">
			<div class="headerNav">
				<a class="logo" href="#">标志</a>
				<ul class="nav">
					<li><a href="javascript:void(0)">您好,<shiro:principal property="name"/></a></li>
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
				<div class="accordion" fillSpace="sidebar">
					<div class="accordionHeader">
						<h2>
							<span>Folder</span>车辆管理
						</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a href="${ctx}/work/member" title="会员管理" target="navTab" rel="work_member">会员管理</a></li>
							<li><a title="vip车辆管理" href="${ctx}/work/cardinfo" target="navTab" rel="work_cardinfo">vip车辆管理</a></li>
							<li><a title="预约管理" href="${ctx}/work/yuding" target="navTab" rel="work_yuding">预约管理</a></li>
							<li><a title="车辆反寻" href="${ctx}/work/parkingordernotfinished" target="navTab" rel="work_parkingorder_notfinished">车辆反寻</a></li>
						</ul>
					</div>
					<div class="accordionHeader">
						<h2>
							<span>Folder</span>设施管理
						</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a href="${ctx}/work/parkinglot" title="停车场管理" target="navTab" rel="work_parkinglot">停车场管理</a></li>
							<li><a href="${ctx}/work/parkinglotarea" title="停车片区管理" target="navTab" rel="work_parkinglotarea">停车片区管理</a></li>
							<li><a href="${ctx}/work/parkinggarage" title="停车位管理" target="navTab" rel="work_parkinggarage">停车位管理</a></li>
							<li><a href="${ctx}/work/passages" title="进出口管理" target="navTab" rel="work_passages">进出口管理</a></li>
						</ul>
					</div>
					<div class="accordionHeader">
						<h2>
							<span>Folder</span>子系统管理
						</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a>地锁管理</a>
							<ul>
								<li><a title="地锁信息" href="${ctx}/work/parkinglock" target="navTab" rel="work_parkinglock">地锁信息</a></li>
								<li><a title="地锁使用统计" href="${ctx}/work/parkinglockoperationevent" target="navTab" rel="work_parkinglockoperationevent">使用统计</a></li>
								<li><a title="地锁日志信息" href="${ctx}/work/parkinglockeventlog" target="navTab" rel="work_parkinglockeventlog">日志信息</a></li>
							</ul>
							</li>
						</ul>
						<ul class="tree treeFolder">
							<li><a>全视频管理</a>
							<ul>
								<li><a title="全视频日志" href="${ctx}/work/parkingvideo" target="navTab" rel="work_parkingvideo">全视频日志</a></li>
							</ul>
							</li>
						</ul>
					</div> 
					<div class="accordionHeader">
						<h2>
							<span>Folder</span>数据报表
						</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a title="停车记录" href="${ctx}/work/parkingorder?isHome=true" target="navTab" rel="work_parkingorder">停车记录</a></li>
						</ul>
					</div> 
					
					
					<div class="accordionHeader">
						<h2>
							<span>Folder</span>图形报表
						</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a>停车场图表</a>
							<ul>
								<li><a title="余位统计图表" href="${ctx}/work/chart/parkingSurplusTotalView" target="navTab" rel="work_chart_parkingSurplusTotalView">收费统计图表</a></li>
								<li><a title="进出次数图表" href="${ctx}/work/chart/parkingInOutTotalView" target="navTab" rel="work_chart_parkingInOutTotalView">进出次数图表</a></li>
								<li><a title="收费统计图表" href="${ctx}/work/chart/parkingChargeTotalView" target="navTab" rel="work_chart_parkingChargeTotalView">收费统计图表</a></li>
							</ul>
							</li>
						</ul>
					</div> 
					<div class="accordionHeader">
						<h2>
							<span>Folder</span>系统管理
						</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a href="${ctx}/sys/user" target="navTab" rel="sys_user">账号管理</a></li>

							<li><a>权限管理</a>
								<ul>
									<li><a href="${ctx}/sys/role" target="navTab" rel="sys_role">角色管理</a></li>
									<li><a href="${ctx}/sys/authority" target="navTab" rel="sys_authority">授权管理</a></li>
									<li><a href="${ctx}/sys/resource" target="navTab" rel="sys_resource">资源管理</a></li>
								</ul></li>
							<li><a>日志管理</a>
								<ul>
									<li><a href="${ctx}/sys/loginlog" target="navTab" rel="sys_loginlog">登录日志</a></li>
									<!-- 
									<li><a href="${ctx}/sys/syslog" target="navTab" rel="sys_syslog">系统日志</a></li> -->
								</ul></li>
						</ul>
					</div>

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
					<div class="page unitBox" style="height: 100%;width: 99%;">
						<div class="index-portal-body panelContent" style="height: 100%;width: 99%;">
							<div id="homeDiv"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="footer">
		Copyright &copy; 2017 <a href="#" target="dialog">版权所有</a>
	</div>
</body>
</html>
