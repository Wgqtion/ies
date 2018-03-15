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
				$("#homeDiv").load("${ctx}/homeView");
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
							<span>Folder</span>场库管理
						</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a href="${ctx}/work/parkinglot" title="停车场信息" target="navTab" rel="work_parkinglot">停车场信息</a></li>
							<li><a href="${ctx}/work/parkinglotarea" title="片区信息" target="navTab" rel="work_parkinglotarea">片区信息</a></li>
							<li><a href="${ctx}/work/parkinggarage" title="车位信息" target="navTab" rel="work_parkinggarage">车位信息</a></li>
							<li><a href="${ctx}/work/passages" title="通道信息" target="navTab" rel="work_passages">通道信息</a></li>
						</ul>
					</div>
					<div class="accordionHeader">
						<h2>
							<span>Folder</span>用户管理
						</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a href="${ctx}/sys/user" target="navTab" rel="sys_user">后台用户</a></li>
							<li><a href="${ctx}/work/wxUser" title="微信用户" target="navTab" rel="work_wxUser">微信用户</a></li>
						</ul>
					</div>
					<div class="accordionHeader">
						<h2>
							<span>Folder</span>设备管理
						</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a>地锁管理</a>
							<ul>
								<li><a title="地锁信息" href="${ctx}/work/parkinglock" target="navTab" rel="work_parkinglock">地锁信息</a></li>
								<li><a title="地锁使用统计" href="${ctx}/work/parkinglockoperationevent" target="navTab" rel="work_parkinglockoperationevent">使用统计</a></li>
							</ul>
							</li>
						</ul>
						<ul class="tree treeFolder">
							<li><a>充电桩管理</a>
							<ul>
								<li><a title="充电桩平台" href="https://www.teld.cn/" target="navTab" rel="telaidian">充电桩平台</a></li>
							</ul>
							</li>
						</ul>
					</div> 
					
					
					<div class="accordionHeader">
						<h2>
							<span>Folder</span>车辆管理
						</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a title="车辆信息" href="${ctx}/work/car" target="navTab" rel="work_car">车辆信息</a></li>
						</ul>
					</div>
					
					<div class="accordionHeader">
						<h2>
							<span>Folder</span>订单管理
						</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a title="停车记录单" href="${ctx}/work/parkingorder?isHome=true" target="navTab" rel="work_parkingorder">停车记录单</a></li>
							<li><a title="预约订单" href="${ctx}/work/yuding" target="navTab" rel="work_yuding">预约订单</a></li>
						</ul>
					</div>
					
					<div class="accordionHeader">
						<h2>
							<span>Folder</span>数据报表
						</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a title="车位状态查询" href="${ctx}/work/report/parkingGarageStatus" target="navTab" rel="work_report_parkingGarageStatus">车位状态查询</a></li>
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
								<li><a title="余位统计" href="${ctx}/work/chart/parkingSurplusTotalView" target="navTab" rel="work_chart_parkingSurplusTotalView">余位统计</a></li>
								<li><a title="进出次数" href="${ctx}/work/chart/parkingInOutTotalView" target="navTab" rel="work_chart_parkingInOutTotalView">进出次数</a></li>
								<li><a title="收费统计" href="${ctx}/work/chart/parkingChargeTotalView" target="navTab" rel="work_chart_parkingChargeTotalView">收费统计</a></li>
							</ul>
							</li>
						</ul>
					</div> 
					<div class="accordionHeader">
						<h2>
							<span>Folder</span>基础设置
						</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a href="${ctx}/work/org" target="navTab" rel="work_org">小程序权限</a></li>
						</ul>
					</div>
					<div class="accordionHeader">
						<h2>
							<span>Folder</span>系统管理
						</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a href="${ctx}/work/company" target="navTab" rel="work_company">公司管理</a></li>
							<li><a>权限管理</a>
								<ul>
									<li><a href="${ctx}/sys/role" target="navTab" rel="sys_role">角色管理</a></li>
									<li><a href="${ctx}/sys/authority" target="navTab" rel="sys_authority">授权管理</a></li>
									<li><a href="${ctx}/sys/resource" target="navTab" rel="sys_resource">资源管理</a></li>
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
