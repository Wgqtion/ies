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
	<title>上海宜事智能地锁管理平台</title>
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
	<script src="${ctx}/work/report/query/cwtj" type="text/javascript"></script>
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

			$("#index_system_dstj").loadUrl("${ctx}/work/report/query/dstj");

			setTimeout(function() {
				$("#random").show().skippr({
					autoPlay : false,
					autoPlayDuration : 2000,
					arrows : false,
					keyboardOnAlways : false
				});
				$("#random1").show().skippr({
					autoPlay : false,
					autoPlayDuration : 2000,
					arrows : false,
					keyboardOnAlways : false
				});

			}, 1100);

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
							<li><a href="${ctx}/work/passages" title="出入口管理" target="navTab" rel="work_passages">出入口管理</a></li>
							<li><a href="${ctx}/work/videodevice" title="视频设备" target="navTab" rel="work_videodevice">视频设备</a></li>
						</ul>
					</div>
					<div class="accordionHeader">
						<h2>
							<span>Folder</span>使用记录
						</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a title="停车记录" href="${ctx}/work/parkingorder" target="navTab" rel="work_parkingorder">停车记录</a></li>
							<li><a href="${ctx}/work/accessapply" title="出入申请记录" target="navTab" rel="work_accessapply">出入申请记录</a></li>
							<li><a title="车位使用记录" href="${ctx}/work/parkinggaragecarnolog" target="navTab" rel="work_parkinggaragecarnolog">车位使用记录</a></li>
							<li><a title="路径服务记录" href="${ctx}/work/routepathlog" target="navTab" rel="work_routepathlog">路径服务记录</a></li>

						</ul>
					</div>

					<div class="accordionHeader">
						<h2>
							<span>Folder</span>地锁管理
						</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a title="地锁信息" href="${ctx}/work/parkinglock" target="navTab" rel="work_parkinglock">地锁信息</a></li>
							<li><a title="地锁使用统计" href="${ctx}/work/parkinglockoperationevent" target="navTab" rel="work_parkinglockoperationevent">使用统计</a></li>
							<li><a title="地锁日志信息" href="${ctx}/work/parkinglockeventlog" target="navTab" rel="work_parkinglockeventlog">日志信息</a></li>

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
					<div class="page unitBox" style="height: 100%">
						<div class="index-portal-body panelContent" style="height: 100%">
							<table width="98%" height="100%">
								<tr height="50%">
									<td width="50%" align="center" valign="middle">
										<table>
											<tr>
												<td><div id="container_668662" style="width: 260px; height: 248px; margin: 0 auto"></div></td>
												<td><div id="container_450650" style="width: 260px; height: 248px; margin: 0 auto"></div></td>
											</tr>
										</table> 
									</td>
									<td width="50%" align="center" valign="middle">
										<!-- <img src="${ctx}/static/styles/vsc/images/3.png">
										-->
										<div id="random" style="display: none">
											<div>
												<a href="${ctx}/work/parkinglot/imageview/1" max="true" target="dialog" title="查看停车场平面图" rel="work_parkinglot_view_image"> <img src="${ctx}/static/images/1.png" title="上海宜事停车场" width="520" height="248" />
												</a>
											</div>
											<div>
												<a href="${ctx}/work/parkinglot/imageview/2" max="true" target="dialog" title="查看停车场平面图" rel="work_parkinglot_view_image"><img src="${ctx}/static/images/2.png" title="3号研发楼地下车库" width="520" height="248" /></a>
											</div>
										</div>
									</td>
								</tr>
								<tr height="50%">
									<td align="center" valign="middle"><img src="${ctx}/static/styles/vsc/images/2.png"></td>
									<td align="center" style="background: url(${ctx}/static/styles/vsc/images/4.png)  no-repeat center;">
										<div style="margin: 10px 10px 30px -220px;">
											<ul id="index_system_dstj">
												<li style="line-height: 36px; font-weight: bold; color: #a2a2a2; font-size: 18px;">地锁总数0个</li>
												<li style="line-height: 36px; font-weight: bold; color: #a2a2a2; font-size: 18px;">地锁正常0个</li>
												<li style="line-height: 36px; font-weight: bold; color: #a2a2a2; font-size: 18px;">地锁异常0个</li>
												<li style="line-height: 36px; font-weight: bold; color: #a2a2a2; font-size: 18px;">地锁已用0个</li>
												<li style="line-height: 36px; font-weight: bold; color: #a2a2a2; font-size: 18px;">地锁可用0个</li>
											</ul>
										</div>
									</td>
								</tr>

							</table>
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
