<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<title>智能地锁平台系统接口</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta HTTP-EQUIV="pragma" CONTENT="no-cache"/>
<meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate"/>
<meta HTTP-EQUIV="expires" CONTENT="0"/>

<%
    String servername=request.getServerName();
	String url = request.getContextPath()
			+ "/"
			+ com.vsc.business.gerd.web.httpservices.PhoneController.PATH;
%>
	<!-- Le styles -->
<link rel="shortcut icon" href="<%=request.getContextPath()%>/static/images/favicon.ico?1111" />
<link href="<%=request.getContextPath()%>/static/bootstrap/2.1.1/css/bootstrap.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/static/bootstrap/2.1.1/css/bootstrap-responsive.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/static/bootstrap/2.1.1/css/docs.css" rel="stylesheet">
</head>

<body data-spy="scroll" data-target=".bs-docs-sidebar">

	<!-- Navbar
    ================================================== -->
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<button type="button" class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>

				<div class="nav-collapse collapse">
					<ul class="nav">
						<li class=""><a href="javascript:return false;"><h3>智能地锁平台系统接口</h3></a></li>
						<li class="active"><a href="javascript:return false;"> </a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<p></p>
	<div class="container">
		<p></p>
		<!-- Docs nav
    ================================================== -->
		<div class="row">
			<div class="span3 bs-docs-sidebar">
				<ul class="nav nav-list bs-docs-sidenav">
					<li><a href="#登录认证接口"><i class="icon-chevron-right"></i>1.登录认证接口</a></li>
					<li><a href="#获取停车场信息"><i class="icon-chevron-right"></i>2.获取停车场信息</a></li> 
					<li><a href="#获取关联车位锁信息"><i class="icon-chevron-right"></i>3.获取关联车位锁信息</a></li> 
					<li><a href="#获取停车片区信息"><i class="icon-chevron-right"></i>4.获取停车区信息</a></li>
					<li><a href="#获取车位信息"><i class="icon-chevron-right"></i>5.获取车位信息</a></li>
					<li><a href="#获取所有余车信息"><i class="icon-chevron-right"></i>6.获取停车区余车</a></li>
					<li><a href="#获取当前可预约时间"><i class="icon-chevron-right"></i>7.获取当前可预约时间</a></li>
					<li><a href="#预约车位"><i class="icon-chevron-right"></i>8.预约车位</a></li>
		 
					<li><a href="#开始路径服务"><i class="icon-chevron-right"></i>10.开始路径服务</a></li>
					<li><a href="#结束路径服务"><i class="icon-chevron-right"></i>11.结束路径服务</a></li>
				 
					<li><a href="#出入口下发"><i class="icon-chevron-right"></i>13.获取出入口信息</a></li>
					
					<li><a href="#请求路径接口"><i class="icon-chevron-right"></i>14.请求路径接口</a></li>
					<li><a href="#请求开始导航"><i class="icon-chevron-right"></i>15.请求开始导航接口</a></li>
					<li><a href="#请求结束导航"><i class="icon-chevron-right"></i>16.请求结束导航接口</a></li>
				</ul>
			</div>
			<div class="span9">



				<!-- Download
        ================================================== -->
				<section id="登录认证接口">
					<div class="page-header">
						<h2>
							1. 登录认证接口
							</h1>
					</div>
					<h4>
						<a href="javascript:return false;">接口地址</a>
					</h4>
					<p><pre>http请求方式: POST<br/>http://<%=servername+url%>/login</pre></p>
					<h4>
						<a href="javascript:return false;">示例</a>
					</h4>
					<form class="bs-docs-example" action="<%=url%>/login" method="post">

						<label>登录名</label> <input type="text" name="username"
							placeholder="请输入登录名" /><br /> <label>密码</label> <input
							type="password" name="password" placeholder="请输入密码" /><br />
						<button type="submit" class="btn">登录</button>
					</form>
					<h4>
						<a href="javascript:return false;">参数说明</a>
					</h4>
					<div class="bs-docs-example">
						<table border="1" cellspacing="0" cellpadding="4" align="center" width="95%">
							<tbody>
								<tr>
									<th style="width: 120px">参数</th>
									<th style="width: 120px">是否必须</th>
									<th >说明</th>
								</tr>
								<tr>
									<td>username</td>
									<td>是</td>
									<td>系统提供的登录用户名</td>
								</tr>
								<tr>
									<td>password</td>
									<td>是</td>
									<td>系统提供的登录密码</td>
								</tr>
								 
							</tbody>
						</table>
					</div>

					<h4>
						<a href="javascript:return false;">返回说明</a>
					</h4>
				 			
					<p>正常情况下返回的JSON数据包：<pre>{ "statusCode":"200", "callbackData":{ "isEnabled":true, "loginName":"user", "name":"普通会员" }, "message":"登录成功" }</pre>
					</p>

				</section>
				
				
				<!-- //////////////////////////获取停车场信息/////////////////////////// START -->
				<section id="获取停车场信息">
					<div class="page-header">
						<h2>
							2. 获取所有停车场信息
							</h1>
					</div>
					<h4>
						<a href="javascript:return false;">接口地址</a>
					</h4>
					<p><pre>http请求方式: POST<br/>http://<%=servername+url%>/parkinglot/find</pre></p>
					<h4>
						<a href="javascript:return false;">示例</a>
					</h4>
					<form class="bs-docs-example" action="<%=url%>/parkinglot/find" method="post">

						
						<button type="submit" class="btn">获取所有停车场信息</button>
					</form>
					<h4>
						<a href="javascript:return false;">参数说明</a>
					</h4>
					<div class="bs-docs-example">
						<table border="1" cellspacing="0" cellpadding="4" align="center" width="95%">
							<tbody>
								<tr>
									<th style="width: 120px">参数</th>
									<th style="width: 120px">是否必须</th>
									<th >说明</th>
								</tr>
								<tr>
									<td>无</td>
									<td>无</td>
									<td>无</td>
								</tr>
								 
							</tbody>
						</table>
					</div>

					<h4>
						<a href="javascript:return false;">返回说明</a>
					</h4>
				 			
					<p>正常情况下返回的JSON数据包：<pre>{ "statusCode":"200", "callbackData":[{ "baiduLatitudeLat":"11", "baiduLatitudeLng":"1", "canGetCard":false, "carNumber":0, "id":1941, "name":"嘉定校区" }], "message":"接口调用成功" }</pre>
					</p>

				</section>
				<!-- //////////////////////////获取停车场信息/////////////////////////// END -->
				<!-- //////////////////////////获取关联车位锁信息/////////////////////////// START -->
				<section id="获取关联车位锁信息">
					<div class="page-header">
						<h2>
							3. 获取关联车位锁信息
							</h1>
					</div>
					<h4>
						<a href="javascript:return false;">接口地址</a>
					</h4>
					<p><pre>http请求方式: POST<br/>http://<%=servername+url%>/parkinggaragelock</pre></p>
					<h4>
						<a href="javascript:return false;">示例</a>
					</h4>
					<form class="bs-docs-example" action="<%=url%>/parkinggaragelock" method="post">

						<label>车位编号</label><input  type="text" name="parkingName" value="30028"/> <br/>
						<button type="submit" class="btn">获取关联车位锁信息</button>
					</form>
					<h4>
						<a href="javascript:return false;">参数说明</a>
					</h4>
					<div class="bs-docs-example">
						<table border="1" cellspacing="0" cellpadding="4" align="center" width="95%">
							<tbody>
								<tr>
									<th style="width: 120px">参数</th>
									<th style="width: 120px">是否必须</th>
									<th >说明</th>
								</tr>
								<tr>
									<td>parkingName</td>
									<td>是</td>
									<td>车位编号</td>
								</tr>								 
							</tbody>
						</table>
					</div>

					<h4>
						<a href="javascript:return false;">返回说明</a>
					</h4>
				 			
					<p>正常情况下返回的JSON数据包：<pre>{ "statusCode":"200", "callbackData":{ "id":451270, "ipAddress":"0001", "isCarOn":false, "isOk":true, "isOnline":false, "isOpen":false, "lockNum":"123", "parkingGarage":{ "id":20001, "ipAddress":"F01", "isOnline":true, "name":"20001" } }, "message":"接口调用成功" }</pre>
					isOpen:地锁开关状态  lockNum:地锁编号 ipAddress：区域编号 isOk:正常异常 isOnline:是否在线
					</p>

				</section>
				<!-- //////////////////////////获取关联车位锁信息/////////////////////////// END -->

				<!-- //////////////////////////获取校门信息/////////////////////////// START 
				<section id="获取校门信息">
					<div class="page-header">
						<h2>
							3. 获取所有校门信息
							</h1>
					</div>
					<h4>
						<a href="javascript:return false;">接口地址</a>
					</h4>
					<p><pre>http请求方式: POST<br/>http://<%=servername+url%>/campus/find</pre></p>
					<h4>
						<a href="javascript:return false;">示例</a>
					</h4>
					<form class="bs-docs-example" action="<%=url%>/campus/find" method="post">

						
						<button type="submit" class="btn">获取所有校门信息</button>
					</form>
					<h4>
						<a href="javascript:return false;">参数说明</a>
					</h4>
					<div class="bs-docs-example">
						<table border="1" cellspacing="0" cellpadding="4" align="center" width="95%">
							<tbody>
								<tr>
									<th style="width: 120px">参数</th>
									<th style="width: 120px">是否必须</th>
									<th >说明</th>
								</tr>
								<tr>
									<td>无</td>
									<td>无</td>
									<td>无</td>
								</tr>
								 
							</tbody>
						</table>
					</div>

					<h4>
						<a href="javascript:return false;">返回说明</a>
					</h4>
				 			
					<p>正常情况下返回的JSON数据包：<pre>{ "statusCode":"200", "callbackData":[ { "id":1975, "name":"3号门", "parkingLot":{ "id":1941, "name":"嘉定校区" }, "remark":"1" }, { "id":1976, "name":"2号门", "parkingLot":{ "id":1941, "name":"嘉定校区" }, "remark":"1" }, { "id":1977, "name":"嘉定区曹安公路4800号门 ", "parkingLot":{ "id":1941, "name":"嘉定校区" }, "remark":"1" } ], "message":"接口调用成功" }</pre>
					</p>

				</section>-->
				<!-- //////////////////////////获取校门信息/////////////////////////// END -->
				
				<!-- //////////////////////////获取停车片区信息/////////////////////////// START -->
				<section id="获取停车片区信息">
					<div class="page-header">
						<h2>
							4. 获取停车片区信息
							</h1>
					</div>
					<h4>
						<a href="javascript:return false;">接口地址</a>
					</h4>
					<p><pre>http请求方式: POST<br/>http://<%=servername+url%>/parkinglotarea/find</pre></p>
					<h4>
						<a href="javascript:return false;">示例</a>
					</h4>
					<form class="bs-docs-example" action="<%=url%>/parkinglotarea/find" method="post">

						
						<button type="submit" class="btn">获取停车片区信息</button>
					</form>
					<h4>
						<a href="javascript:return false;">参数说明</a>
					</h4>
					<div class="bs-docs-example">
						<table border="1" cellspacing="0" cellpadding="4" align="center" width="95%">
							<tbody>
								<tr>
									<th style="width: 120px">参数</th>
									<th style="width: 120px">是否必须</th>
									<th >说明</th>
								</tr>
								<tr>
									<td>无</td>
									<td>无</td>
									<td>无</td>
								</tr>
								 
							</tbody>
						</table>
					</div>

					<h4>
						<a href="javascript:return false;">返回说明</a>
					</h4>
				 			
					<p>正常情况下返回的JSON数据包：<pre>{ "statusCode":"200", "callbackData":[{ "baiduLatitudeLat":"", "baiduLatitudeLng":"", "carNumber":36, "code":"T001", "description":"东东", "id":111640, "name":"通达馆地下车库" }], "message":"接口调用成功" }</pre>
					</p>

				</section>
				<!-- //////////////////////////获取停车片区信息/////////////////////////// END -->
				
				<!-- //////////////////////////获取车位信息/////////////////////////// START -->
				<section id="获取车位信息">
					<div class="page-header">
						<h2>
							5. 获取车位信息
							</h1>
					</div>
					<h4>
						<a href="javascript:return false;">接口地址</a>
					</h4>
					<p><pre>http请求方式: POST<br/>http://<%=servername+url%>/parkinggarage/find</pre></p>
					<h4>
						<a href="javascript:return false;">示例</a>
					</h4>
					<form class="bs-docs-example" action="<%=url%>/parkinggarage/find" method="post">

						
						<button type="submit" class="btn">获取所有车位信息</button>
					</form>
					<h4>
						<a href="javascript:return false;">参数说明</a>
					</h4>
					<div class="bs-docs-example">
						<table border="1" cellspacing="0" cellpadding="4" align="center" width="95%">
							<tbody>
								<tr>
									<th style="width: 120px">参数</th>
									<th style="width: 120px">是否必须</th>
									<th >说明</th>
								</tr>
								<tr>
									<td>无</td>
									<td>无</td>
									<td>无</td>
								</tr>
								 
							</tbody>
						</table>
					</div>

					<h4>
						<a href="javascript:return false;">返回说明</a>
					</h4>
				 			
					<p>正常情况下返回的JSON数据包：<pre>{ "statusCode":"200", "callbackData":[{ "description":"", "garageType":0, "id":414560, "name":"C001", "parkingLotArea":{ "description":"东东", "id":111640, "name":"通达馆地下车库" } }], "message":"接口调用成功" }</pre>
					</p>

				</section>
				<!-- //////////////////////////获取车位信息/////////////////////////// END -->
				
				<!-- //////////////////////////获取所有余车信息/////////////////////////// START -->
				<section id="获取所有余车信息">
					<div class="page-header">
						<h2>
							6. 获取所有余车信息
							</h1>
					</div>
					<h4>
						<a href="javascript:return false;">接口地址</a>
					</h4>
					<p><pre>http请求方式: POST<br/>http://<%=servername+url%>/remainingcar/find</pre></p>
					<h4>
						<a href="javascript:return false;">示例</a>
					</h4>
					<form class="bs-docs-example" action="<%=url%>/remainingcar/find" method="post">

						
						<button type="submit" class="btn">获取所有余车信息</button>
					</form>
					<h4>
						<a href="javascript:return false;">参数说明</a>
					</h4>
					<div class="bs-docs-example">
						<table border="1" cellspacing="0" cellpadding="4" align="center" width="95%">
							<tbody>
								<tr>
									<th style="width: 120px">参数</th>
									<th style="width: 120px">是否必须</th>
									<th >说明</th>
								</tr>
								<tr>
									<td>无</td>
									<td>无</td>
									<td>无</td>
								</tr>
								 
							</tbody>
						</table>
					</div>

					<h4>
						<a href="javascript:return false;">返回说明</a>
					</h4>
				 			
					<p>正常情况下返回的JSON数据包：<pre>{ "statusCode":"200", "callbackData":[{ "carNumber":36, "code":"T001", "freeCarNum":0, "id":111640, "name":"通达馆地下车库" }], "message":"接口调用成功" }</pre>
					</p>

				</section>
				<!-- //////////////////////////获取所有余车信息/////////////////////////// END -->
				
				<!-- //////////////////////////获取所有余车信息/////////////////////////// START -->
				<section id="获取当前可预约时间">
					<div class="page-header">
						<h2>
							7. 获取当前可预约时间
							</h1>
					</div>
					<h4>
						<a href="javascript:return false;">接口地址</a>
					</h4>
					<p><pre>http请求方式: POST<br/>http://<%=servername+url%>/yuyue/nowtime</pre></p>
					<h4>
						<a href="javascript:return false;">示例</a>
					</h4>
					<form class="bs-docs-example" action="<%=url%>/yuyue/nowtime" method="post">

						
						<button type="submit" class="btn">获取当前可预约时间</button>
					</form>
					<h4>
						<a href="javascript:return false;">参数说明</a>
					</h4>
					<div class="bs-docs-example">
						<table border="1" cellspacing="0" cellpadding="4" align="center" width="95%">
							<tbody>
								<tr>
									<th style="width: 120px">参数</th>
									<th style="width: 120px">是否必须</th>
									<th >说明</th>
								</tr>
								<tr>
									<td>无</td>
									<td>无</td>
									<td>无</td>
								</tr>
								 
							</tbody>
						</table>
					</div>

					<h4>
						<a href="javascript:return false;">返回说明</a>
					</h4>
				 			
					<p>正常情况下返回的JSON数据包：<pre>{ "statusCode":"200", "callbackData":{"canLocked ": true,"starttime":"2016-08-15 16:01","endTime":"2016-08-15 16:21"}, "message":"接口调用成功" }</pre>
					</p>

				</section>
				<!-- //////////////////////////获取当前可预约时间/////////////////////////// END -->
				
				
				
				<!-- //////////////////////////预约车位/////////////////////////// START -->
				<section id="预约车位">
					<div class="page-header">
						<h2>
							8. 预约车位
							</h1>
					</div>
					<h4>
						<a href="javascript:return false;">接口地址</a>
					</h4>
					<p><pre>http请求方式: POST<br/>http://<%=servername+url%>/yuyue/locked</pre></p>
					<h4>
						<a href="javascript:return false;">示例</a>
					</h4>
					<form class="bs-docs-example" action="<%=url%>/yuyue/locked" method="post">

						<label>预约时间(格式：yyyy-MM-dd HH:mm)</label> 
						<input type="text" class="date" size="9" dateFmt="yyyy-MM-dd" name="yuyueTime"/><br />
						<label>停车区域ID</label>
						<input type="text" name="parkingLotAreaId" placeholder="请输入停车位" /><br /> 
						<label>车牌号码</label> 
						<input type="text" name="carNo" placeholder="请输入车牌号码" /><br />
						<button type="submit" class="btn">预约车位</button>
					</form>
					<h4>
						<a href="javascript:return false;">参数说明</a>
					</h4>
					<div class="bs-docs-example">
						<table border="1" cellspacing="0" cellpadding="4" align="center" width="95%">
							<tbody>
								<tr>
									<th style="width: 120px">参数</th>
									<th style="width: 120px">是否必须</th>
									<th >说明</th>
								</tr>
								<tr>
									<td>yuyueTime</td>
									<td>是</td>
									<td>预约时间(格式：yyyy-MM-dd HH:mm)</td>
								</tr>
								<tr>
									<td>parkingLotAreaId</td>
									<td>是</td>
									<td>停车区域ID</td>
								</tr>
								<tr>
									<td>carNo</td>
									<td>是</td>
									<td>车牌号码</td>
								</tr>
								 
							</tbody>
						</table>
					</div>

					<h4>
						<a href="javascript:return false;">返回说明</a>
					</h4>
				 			
					<p>正常情况下返回的JSON数据包：<pre>{ "statusCode":"200", "callbackData":true, "message":"接口调用成功" }</pre>
					</p>

				</section>
				<!-- //////////////////////////预约车位/////////////////////////// END -->
				
				
				 
				
				
				<!-- //////////////////////////开始路径服务/////////////////////////// START -->
				<section id="开始路径服务">
					<div class="page-header">
						<h2>
							10.开始路径服务
							</h1>
					</div>
					<h4>
						<a href="javascript:return false;">接口地址</a>
					</h4>
					<p><pre>http请求方式: POST<br/>http://<%=servername+url%>/start/pathService</pre></p>
					<h4>
						<a href="javascript:return false;">示例</a>
					</h4>
					<form class="bs-docs-example" action="<%=url%>/start/pathService" method="post">

						<label>车辆id或者车牌号</label> 
						<input type="text" name="paramInput" placeholder="请输入车辆id或者车牌号" /><br />
						<button type="submit" class="btn">开始路径服务</button>
					</form>
					<h4>
						<a href="javascript:return false;">参数说明</a>
					</h4>
					<div class="bs-docs-example">
						<table border="1" cellspacing="0" cellpadding="4" align="center" width="95%">
							<tbody>
								<tr>
									<th style="width: 120px">参数</th>
									<th style="width: 120px">是否必须</th>
									<th >说明</th>
								</tr>
								<tr>
									<td>paramInput</td>
									<td>是</td>
									<td>车辆id或者车牌号</td>
								</tr>
								 
							</tbody>
						</table>
					</div>

					<h4>
						<a href="javascript:return false;">返回说明</a>
					</h4>
				 			
					<p>正常情况下返回的JSON数据包：<pre>{ "statusCode":"200", "callbackData":true, "message":"接口调用成功" }</pre>
					</p>

				</section>
				<!-- //////////////////////////开始路径服务/////////////////////////// END -->
				
				
				<!-- //////////////////////////结束路径服务/////////////////////////// START -->
				<section id="结束路径服务">
					<div class="page-header">
						<h2>
							11.结束路径服务
							</h1>
					</div>
					<h4>
						<a href="javascript:return false;">接口地址</a>
					</h4>
					<p><pre>http请求方式: POST<br/>http://<%=servername+url%>/stop/pathService</pre></p>
					<h4>
						<a href="javascript:return false;">示例</a>
					</h4>
					<form class="bs-docs-example" action="<%=url%>/stop/pathService" method="post">

						<label>车辆id或者车牌号</label> 
						<input type="text" name="paramInput" placeholder="请输入车辆id或者车牌号" /><br />
						<button type="submit" class="btn">结束路径服务</button>
					</form>
					<h4>
						<a href="javascript:return false;">参数说明</a>
					</h4>
					<div class="bs-docs-example">
						<table border="1" cellspacing="0" cellpadding="4" align="center" width="95%">
							<tbody>
								<tr>
									<th style="width: 120px">参数</th>
									<th style="width: 120px">是否必须</th>
									<th >说明</th>
								</tr>
								<tr>
									<td>paramInput</td>
									<td>是</td>
									<td>车辆id或者车牌号</td>
								</tr>
								 
								 
							</tbody>
						</table>
					</div>

					<h4>
						<a href="javascript:return false;">返回说明</a>
					</h4>
				 			
					<p>正常情况下返回的JSON数据包：<pre>{ "statusCode":"200", "callbackData":true, "message":"接口调用成功" }</pre>
					</p>

				</section>
				<!-- //////////////////////////结束路径服务/////////////////////////// END -->
				
				 
				<!-- //////////////////////////出入口下发/////////////////////////// START -->
				<section id="出入口下发">
					<div class="page-header">
						<h2>
							13. 获取出入口信息
							</h1>
					</div>
					<h4>
						<a href="javascript:return false;">接口地址</a>
					</h4>
					<p><pre>http请求方式: POST<br/>http://<%=servername+url%>/passages/find</pre></p>
					<h4>
						<a href="javascript:return false;">示例</a>
					</h4>
					<form class="bs-docs-example" action="<%=url%>/passages/find" method="post">

						
						<button type="submit" class="btn">获取出入口信息</button>
					</form>
					<h4>
						<a href="javascript:return false;">参数说明</a>
					</h4>
					<div class="bs-docs-example">
						<table border="1" cellspacing="0" cellpadding="4" align="center" width="95%">
							<tbody>
								<tr>
									<th style="width: 120px">参数</th>
									<th style="width: 120px">是否必须</th>
									<th >说明</th>
								</tr>
								<tr>
									<td>无</td>
									<td>无</td>
									<td>无</td>
								</tr>
								 
							</tbody>
						</table>
					</div>

					<h4>
						<a href="javascript:return false;">返回说明</a>
					</h4>
				 			
					<p>正常情况下返回的JSON数据包：<pre>{ "statusCode":"200", "callbackData":[ { "id":449470, "name":"进口", "xcoordinate":"21.4", "ycoordinate":"17" }, { "id":449471, "passagesName":"出口", "xcoordinate":"21.4", "ycoordinate":"17" } ], "message":"接口调用成功" }</pre>
					</p>

				</section>
				<!-- //////////////////////////出入口下发/////////////////////////// END -->
				

				<!-- //////////////////////////请求路径接口/////////////////////////// START -->
				<section id="请求路径接口">
					<div class="page-header">
						<h2>
							14.请求路径接口
							</h1>
					</div>
					<h4>
						<a href="javascript:return false;">接口地址</a>
					</h4>
					<p><pre>http请求方式: POST<br/>http://<%=servername+url%>/pathService/route</pre></p>
					<h4>
						<a href="javascript:return false;">示例</a>
					</h4>
					<form class="bs-docs-example" action="<%=url%>/pathService/route" method="post">

						<label>请求路径的起点的ID</label> 
						<input type="text" name="startpoint" placeholder="请输入请求路径的起点的ID" /><br />
						<label>请求路径的终点的ID</label> 
						<input type="text" name="endpoint" placeholder="请输入请求路径的终点的ID" /><br />
						<button type="submit" class="btn">调用请求路径</button>
					</form>
					<h4>
						<a href="javascript:return false;">参数说明</a>
					</h4>
					<div class="bs-docs-example">
						<table border="1" cellspacing="0" cellpadding="4" align="center" width="95%">
							<tbody>
								<tr>
									<th style="width: 120px">参数</th>
									<th style="width: 120px">是否必须</th>
									<th >说明</th>
								</tr>
								<tr>
									<td>startpoint</td>
									<td>是</td>
									<td>请求路径的起点的ID</td>
								</tr>
								<tr>
									<td>endpoint</td>
									<td>是</td>
									<td>请求路径的终点的ID</td>
								</tr>
								 
							</tbody>
						</table>
					</div>

					<h4>
						<a href="javascript:return false;">返回说明</a>
					</h4>
				 			
					<p>正常情况下返回的JSON数据包：<pre>{"statusCode":"200","callbackData":{"RoutePoint":[{"X":0.2,"Y":10.8},{"X":19.0,"Y":10.8},{"X":15.0,"Y":10.8},{"X":10.4,"Y":10.8},{"X":10.4,"Y":16.4}],"PIN":"20161220170059548"},"message":"接口调用成功"}</pre>
					</p>

				</section>
				<!-- //////////////////////////请求路径接口/////////////////////////// END -->
				
				<!-- //////////////////////////请求开始导航接口/////////////////////////// START -->
				<section id="请求开始导航">
					<div class="page-header">
						<h2>
							15.请求开始导航接口
							</h1>
					</div>
					<h4>
						<a href="javascript:return false;">接口地址</a>
					</h4>
					<p><pre>http请求方式: POST<br/>http://<%=servername+url%>/pathService/startfix</pre></p>
					<h4>
						<a href="javascript:return false;">示例</a>
					</h4>
					<form class="bs-docs-example" action="<%=url%>/pathService/startfix" method="post">

						<label>定位终端ID</label> 
						<input type="text" name="deviceid" placeholder="请输入定位终端ID" /><br />
						<label>PIN码</label> 
						<input type="text" name="pin" placeholder="请输入PIN码" /><br />
						<label>车牌号</label> 
						<input type="text" name="plateno" placeholder="请输入车牌号" /><br />
						<button type="submit" class="btn">调用请求开始导航</button>
					</form>
					<h4>
						<a href="javascript:return false;">参数说明</a>
					</h4>
					<div class="bs-docs-example">
						<table border="1" cellspacing="0" cellpadding="4" align="center" width="95%">
							<tbody>
								<tr>
									<th style="width: 120px">参数</th>
									<th style="width: 120px">是否必须</th>
									<th >说明</th>
								</tr>
								<tr>
									<td>deviceid</td>
									<td>是</td>
									<td>定位终端ID</td>
								</tr>
								<tr>
									<td>pin</td>
									<td>否</td>
									<td>PIN码</td>
								</tr>
								<tr>
									<td>plateno</td>
									<td>否</td>
									<td>车牌号</td>
								</tr>
								 
							</tbody>
						</table>
					</div>

					<h4>
						<a href="javascript:return false;">返回说明</a>
					</h4>
				 			
					<p>正常情况下返回的JSON数据包：<pre>{"statusCode":"200","callbackData":"OK","message":"接口调用成功"}</pre>
					</p>

				</section>
				<!-- //////////////////////////请求开始导航接口/////////////////////////// END -->
				
				<!-- //////////////////////////请求开始导航接口/////////////////////////// START -->
				<section id="请求结束导航">
					<div class="page-header">
						<h2>
							16.请求结束导航接口
							</h1>
					</div>
					<h4>
						<a href="javascript:return false;">接口地址</a>
					</h4>
					<p><pre>http请求方式: POST<br/>http://<%=servername+url%>/pathService/endfix</pre></p>
					<h4>
						<a href="javascript:return false;">示例</a>
					</h4>
					<form class="bs-docs-example" action="<%=url%>/pathService/endfix" method="post">

						<label>PIN码</label> 
						<input type="text" name="Pin" placeholder="请输入PIN码" /><br />
						<button type="submit" class="btn">调用请求开始导航</button>
					</form>
					<h4>
						<a href="javascript:return false;">参数说明</a>
					</h4>
					<div class="bs-docs-example">
						<table border="1" cellspacing="0" cellpadding="4" align="center" width="95%">
							<tbody>
								<tr>
									<th style="width: 120px">参数</th>
									<th style="width: 120px">是否必须</th>
									<th >说明</th>
								</tr>
								<tr>
									<td>pin</td>
									<td>是</td>
									<td>PIN码</td>
								</tr>
								 
							</tbody>
						</table>
					</div>

					<h4>
						<a href="javascript:return false;">返回说明</a>
					</h4>
				 			
					<p>正常情况下返回的JSON数据包：<pre>{"statusCode":"200","callbackData":"OK","message":"接口调用成功"}</pre>
					</p>

				</section>
				<!-- //////////////////////////请求结束导航接口/////////////////////////// END -->
			</div>
		</div>

	</div>



	<!-- Footer
    ================================================== -->
	<footer class="footer">
		<div class="container">
			<p class="pull-right">
				<a href="javascript:return false;">返回顶部</a>
			</p>
		</div>
	</footer>



	<!-- Le javascript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->




</body>
</html>
