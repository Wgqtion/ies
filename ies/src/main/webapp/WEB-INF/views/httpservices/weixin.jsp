<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<title>微信系统接口</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta HTTP-EQUIV="pragma" CONTENT="no-cache"/>
<meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate"/>
<meta HTTP-EQUIV="expires" CONTENT="0"/>

<%
    String servername=request.getServerName();
	String url = request.getContextPath()
			+ "/"
			+ com.vsc.business.gerd.web.httpservices.WeixinController.PATH;
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
						<li class=""><a href="javascript:return false;"><h3>微信系统接口</h3></a></li>
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
					<li><a href="#登录"><i class="icon-chevron-right"></i>1.登录</a></li>
					<li><a href="#注册"><i class="icon-chevron-right"></i>2.注册</a></li> 
					<li><a href="#预约查询"><i class="icon-chevron-right"></i>3.预约查询</a></li> 
                    <li><a href="#订单查询"><i class="icon-chevron-right"></i>4.订单查询</a></li> 
					<li><a href="#场库查询"><i class="icon-chevron-right"></i>5.场库查询</a></li>
					<li><a href="#片区查询"><i class="icon-chevron-right"></i>6.片区查询</a></li>
					<li><a href="#车位查询"><i class="icon-chevron-right"></i>7.车位查询</a></li>
					<li><a href="#预约车位"><i class="icon-chevron-right"></i>8.预约车位</a></li>
					<li><a href="#取消预约"><i class="icon-chevron-right"></i>9.取消预约</a></li>
					<li><a href="#车位解锁"><i class="icon-chevron-right"></i>10.车位解锁</a></li>
					<li><a href="#车位上锁"><i class="icon-chevron-right"></i>11.车位上锁</a></li>
					<li><a href="#取消订单"><i class="icon-chevron-right"></i>12.取消订单</a></li>
					<li><a href="#权限码查询"><i class="icon-chevron-right"></i>13.权限码查询</a></li>
					<li><a href="#添加权限码"><i class="icon-chevron-right"></i>14.添加权限码</a></li>
					<li><a href="#删除权限码"><i class="icon-chevron-right"></i>15.删除权限码</a></li>
				</ul>
			</div>
			<div class="span9">



				<!-- Download
        ================================================== -->
				<section id="登录">
					<div class="page-header">
						<h2>
							1. 登录
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

						<label>微信登陆标识</label>
                                                <input type="text" name="js_code" placeholder="微信登陆标识" /><br />
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
									<td>js_code</td>
									<td>是</td>
									<td>小程序随机登录码</td>
								</tr>
								 
							</tbody>
						</table>
					</div>

					<h4>
						<a href="javascript:return false;">返回说明</a>
					</h4>
				 			
					<p>正常情况下返回的JSON数据包：<pre>{ "statusCode":"200", "callbackData":{ "userId":用户ID, "carNumber":"车牌号", "tel":"手机号" }, "message":"登录成功" }</pre>
					</p>

				</section>
				
				
				<!-- //////////////////////////注册/////////////////////////// START -->
				<section id="注册">
					<div class="page-header">
						<h2>
							2. 注册
							</h1>
					</div>
					<h4>
						<a href="javascript:return false;">接口地址</a>
					</h4>
					<p><pre>http请求方式: POST<br/>http://<%=servername+url%>/register</pre></p>
					<h4>
						<a href="javascript:return false;">示例</a>
					</h4>
					<form class="bs-docs-example" action="<%=url%>/register" method="post">

						<label>微信ID</label>
                                                <input type="text" name="weixinId" placeholder="请输入微信ID" /><br />
                                                <label>微信昵称</label>
                                                <input type="text" name="name" placeholder="请输入微信昵称" /><br />
                                                <label>车牌号</label>
                                                <input type="text" name="carNumber" placeholder="请输入车牌" /><br />
                                                <label>用户手机号</label>
                                                <input type="text" name="tel" placeholder="请输入手机号" /><br />
                                                <label>性别（0男，1女）</label>
                                                <input type="text" name="sex" placeholder="请输入性别" /><br />
                                                <label>国家</label>
                                                <input type="text" name="country" placeholder="请输入国家" /><br />
                                                <label>省</label>
                                                <input type="text" name="province" placeholder="请输入省" /><br />
                                                <label>城市</label>
                                                <input type="text" name="city" placeholder="请输入城市" /><br />
						<button type="submit" class="btn">注册</button>
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
									<td>weixinId</td>
									<td>是</td>
									<td>微信ID</td>
								</tr>
								<tr>
									<td>name</td>
									<td>否</td>
									<td>微信昵称</td>
								</tr>
								<tr>
									<td>carNumber</td>
									<td>否</td>
									<td>车牌号</td>
								</tr>
								<tr>
									<td>tel</td>
									<td>否</td>
									<td>用户手机号</td>
								</tr>
								<tr>
									<td>sex</td>
									<td>否</td>
									<td>性别</td>
								</tr>
								<tr>
									<td>country</td>
									<td>否</td>
									<td>国家</td>
								</tr>
								<tr>
									<td>province</td>
									<td>否</td>
									<td>省</td>
								</tr>
								<tr>
									<td>city</td>
									<td>否</td>
									<td>城市</td>
								</tr>
								 
							</tbody>
						</table>
					</div>

					<h4>
						<a href="javascript:return false;">返回说明</a>
					</h4>
				 			
					<p>正常情况下返回的JSON数据包：<pre>{ "statusCode":"200", "callbackData":{"userId":用户ID, "carNumber":"车牌号", "tel":"手机号" }, "message":"登录成功" }</pre>
					</p>

				</section>
				<!-- //////////////////////////注册/////////////////////////// END -->
				<!-- //////////////////////////预约查询/////////////////////////// START -->
				<section id="预约查询">
					<div class="page-header">
						<h2>
							3. 预约查询
							</h1>
					</div>
					<h4>
						<a href="javascript:return false;">接口地址</a>
					</h4>
					<p><pre>http请求方式: POST<br/>http://<%=servername+url%>/yuyue/info</pre></p>
					<h4>
						<a href="javascript:return false;">示例</a>
					</h4>
					<form class="bs-docs-example" action="<%=url%>/yuyue/info" method="post">

						<label>用户ID</label>
                                                <input  type="text" name="userId" value=""/> <br/>
						<button type="submit" class="btn">获取预约信息</button>
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
									<td>userId</td>
									<td>是</td>
									<td>用户ID</td>
								</tr>								 
							</tbody>
						</table>
					</div>

					<h4>
						<a href="javascript:return false;">返回说明</a>
					</h4>
				 			
					<p>正常情况下返回的JSON数据包：<pre>{ "statusCode":"200", "callbackData":{ "userId":用户ID, "orerNumber":"订单号", "expireDate":到期时间, "parkingGarageId":车位ID }, "message":"接口调用成功" }</pre>
					</p>

				</section>
				<!-- //////////////////////////预约查询/////////////////////////// END -->
                                
				<!-- //////////////////////////订单查询/////////////////////////// START -->
				<section id="订单查询">
					<div class="page-header">
						<h2>
							4. 订单查询
							</h1>
					</div>
					<h4>
						<a href="javascript:return false;">接口地址</a>
					</h4>
					<p><pre>http请求方式: POST<br/>http://<%=servername+url%>/order/info</pre></p>
					<h4>
						<a href="javascript:return false;">示例</a>
					</h4>
					<form class="bs-docs-example" action="<%=url%>/order/info" method="post">

						<label>用户ID</label>
                                                <input  type="text" name="userId" value=""/> <br/>
                                                <button type="submit" class="btn">获取订单信息</button>
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
									<td>userId</td>
									<td>是</td>
									<td>用户ID</td>
								</tr>								 
							</tbody>
						</table>
					</div>

					<h4>
						<a href="javascript:return false;">返回说明</a>
					</h4>
				 			
					<p>正常情况下返回的JSON数据包：<pre>{ "statusCode":"200", "callbackData":{ "userId":用户ID, "orerNumber":"订单号", "expireDate":到期时间, "parkingGarageId":车位ID }, "message":"接口调用成功" }</pre>
					</p>

				</section>
				<!-- //////////////////////////订单查询/////////////////////////// END -->

				<!-- //////////////////////////场库查询/////////////////////////// START -->
				<section id="场库查询">
					<div class="page-header">
						<h2>
							5. 场库查询
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

						<label>用户ID</label>
                                                <input  type="text" name="userId" value="6562240"/> <br/>
						<button type="submit" class="btn">获取场库信息</button>
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
									<td>userId</td>
									<td>是</td>
									<td>用户id</td>
								</tr>
								 
							</tbody>
						</table>
					</div>

					<h4>
						<a href="javascript:return false;">返回说明</a>
					</h4>
				 			
					<p>正常情况下返回的JSON数据包：<pre>{ "statusCode":"200", "callbackData":[{ "id":"停车场ID", "name":"停车场姓名", "baiduLatitudeLat":经度, "baiduLatitudeLng":"维度", "canGetCard":"可否预约", "carNumber":车位总数, "remaining":"剩余车位" }], "message":"接口调用成功" }</pre>
					</p>

				</section>
				<!-- //////////////////////////厂库查询/////////////////////////// END -->
				
				<!-- //////////////////////////片区查询/////////////////////////// START -->
				<section id="片区查询">
					<div class="page-header">
						<h2>
							6. 片区查询
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

						<label>停车场ID</label>
                                                <input  type="text" name="parkingLotId" value=""/> <br/>
						<button type="submit" class="btn">获取片区信息</button>
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
									<td>parkingLotId</td>
									<td>是</td>
									<td>停车场ID</td>
								</tr>
								 
							</tbody>
						</table>
					</div>

					<h4>
						<a href="javascript:return false;">返回说明</a>
					</h4>
				 			
					<p>正常情况下返回的JSON数据包：<pre>{ "statusCode":"200", "callbackData":[{ "id":"片区ID", "pid":父片区, "parkingLotId";所属停车场, "name":"片区名", "baiduLatitudeLat":经度, "baiduLatitudeLng":"维度", "canGetCard":"可否预约", "carNumber":车位总数, "remaining":"剩余车位"  }], "message":"接口调用成功" }</pre>
					</p>

				</section>
				<!-- //////////////////////////片区查询/////////////////////////// END -->
				
				<!-- //////////////////////////车位查询/////////////////////////// START -->
				<section id="车位查询">
					<div class="page-header">
						<h2>
							7. 车位查询
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

						<label>片区ID</label>
                                                <input  type="text" name="parkingLotAreaId" value=""/> <br/>
						<button type="submit" class="btn">获取车位信息</button>
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
									<td>parkingLotAreaId</td>
									<td>是</td>
									<td>片区ID</td>
								</tr>
								 
							</tbody>
						</table>
					</div>

					<h4>
						<a href="javascript:return false;">返回说明</a>
					</h4>
				 			
					<p>正常情况下返回的JSON数据包：<pre>{ "statusCode":"200", "callbackData":[{ "id":车位ID, "garageType":"车位类型", "isOnline":有车无车, "canGetCard":可否预约 }], "message":"接口调用成功" }</pre>
					</p>

				</section>
				<!-- //////////////////////////车位查询/////////////////////////// END -->
				
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
					<p><pre>http请求方式: POST<br/>http://<%=servername+url%>/yuyue/new</pre></p>
					<h4>
						<a href="javascript:return false;">示例</a>
					</h4>
					<form class="bs-docs-example" action="<%=url%>/yuyue/new" method="post">

						<label>用户ID</label>
						<input type="text" name="userId" placeholder="输入用户ID"/><br />
                                                <label>预约时间(格式：yyyy-MM-dd HH:mm)</label>
						<input type="text" class="date" size="9" dateFmt="yyyy-MM-dd" name="inTime"/><br />
						<label>车位ID</label>
						<input type="text" name="parkingId" placeholder="请输入车位ID" /><br /> 
						<label>车牌号码</label> 
						<input type="text" name="plateNo" placeholder="请输入车牌号码" /><br />
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
									<td>userId</td>
									<td>是</td>
									<td>用户ID</td>
								</tr>
								<tr>
									<td>inTime</td>
									<td>否</td>
									<td>预约时间(格式：yyyy-MM-dd HH:mm)</td>
								</tr>
								<tr>
									<td>parkingId</td>
									<td>是</td>
									<td>车位ID</td>
								</tr>
								<tr>
									<td>plateNo</td>
									<td>否</td>
									<td>车牌号码</td>
								</tr>
								 
							</tbody>
						</table>
					</div>

					<h4>
						<a href="javascript:return false;">返回说明</a>
					</h4>
				 			
					<p>正常情况下返回的JSON数据包：<pre>{ "statusCode":"200", "callbackData":{"orderNumber":订单号}, "message":"预约成功" }</pre>
					</p>

				</section>
				<!-- //////////////////////////预约车位/////////////////////////// END -->
				
				
				 
				
				
				<!-- //////////////////////////取消预约/////////////////////////// START -->
				<section id="取消预约">
					<div class="page-header">
						<h2>
							9.取消预约
							</h1>
					</div>
					<h4>
						<a href="javascript:return false;">接口地址</a>
					</h4>
					<p><pre>http请求方式: POST<br/>http://<%=servername+url%>/yuyue/cancel</pre></p>
					<h4>
						<a href="javascript:return false;">示例</a>
					</h4>
					<form class="bs-docs-example" action="<%=url%>/yuyue/cancel" method="post">

						<label>用户ID</label> 
						<input type="text" name="userId" placeholder="请输入用户ID" /><br />
                                                <label>订单号</label> 
						<input type="text" name="orderNumber" placeholder="请输入订单号" /><br />
						<button type="submit" class="btn">取消预约</button>
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
									<td>userId</td>
									<td>是</td>
									<td>用户ID</td>
								</tr>
                                                                <tr>
									<td>orderNumber</td>
									<td>是</td>
									<td>订单号</td>
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
				
				
				<!-- //////////////////////////车位解锁/////////////////////////// START -->
				<section id="车位解锁">
					<div class="page-header">
						<h2>
							10.车位解锁
							</h1>
					</div>
					<h4>
						<a href="javascript:return false;">接口地址</a>
					</h4>
					<p><pre>http请求方式: POST<br/>http://<%=servername+url%>/parkinggarage/unlock</pre></p>
					<h4>
						<a href="javascript:return false;">示例</a>
					</h4>
					<form class="bs-docs-example" action="<%=url%>/parkinggarage/unlock" method="post">

						<label>用户ID</label> 
						<input type="text" name="userId" placeholder="请输入用户ID" /><br />
                                                <label>车位ID</label>
						<input type="text" name="parkingId" placeholder="请输入车位ID" /><br /> 
						<button type="submit" class="btn">车位解锁</button>
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
									<td>userId</td>
									<td>是</td>
									<td>用户ID</td>
								</tr>
								<tr>
									<td>parkingId</td>
									<td>是</td>
									<td>车位ID</td>
								</tr>
								 
								 
							</tbody>
						</table>
					</div>

					<h4>
						<a href="javascript:return false;">返回说明</a>
					</h4>
				 			
					<p>正常情况下返回的JSON数据包：<pre>{ "statusCode":"200", "callbackData":true, "message":"解锁成功" }</pre>
					</p>

				</section>
				<!-- //////////////////////////车位解锁/////////////////////////// END -->
				
				 
				<!-- //////////////////////////车位上锁/////////////////////////// START -->
				<section id="车位上锁">
					<div class="page-header">
						<h2>
							11. 车位上锁
							</h1>
					</div>
					<h4>
						<a href="javascript:return false;">接口地址</a>
					</h4>
					<p><pre>http请求方式: POST<br/>http://<%=servername+url%>/parkinggarage/lock</pre></p>
					<h4>
						<a href="javascript:return false;">示例</a>
					</h4>
					<form class="bs-docs-example" action="<%=url%>/parkinggarage/lock" method="post">
                                                <label>用户ID</label> 
						<input type="text" name="userId" placeholder="请输入用户ID" /><br />
                                                <label>车位ID</label>
						<input type="text" name="parkingId" placeholder="请输入车位ID" /><br />
						<button type="submit" class="btn">车位上锁</button>
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
									<td>userId</td>
									<td>是</td>
									<td>用户ID</td>
								</tr>
								<tr>
									<td>parkingId</td>
									<td>是</td>
									<td>车位ID</td>
								</tr>
								 
							</tbody>
						</table>
					</div>

					<h4>
						<a href="javascript:return false;">返回说明</a>
					</h4>
				 			
					<p>正常情况下返回的JSON数据包：<pre>{ "statusCode":"200", "callbackData":true, "message":"上锁成功" }</pre>
					</p>

				</section>
				<!-- //////////////////////////车位上锁/////////////////////////// END -->
                                
                                
				<!-- //////////////////////////取消订单/////////////////////////// START -->
				<section id="取消订单">
					<div class="page-header">
						<h2>
							12. 取消订单
							</h1>
					</div>
					<h4>
						<a href="javascript:return false;">接口地址</a>
					</h4>
					<p><pre>http请求方式: POST<br/>http://<%=servername+url%>/order/cancel</pre></p>
					<h4>
						<a href="javascript:return false;">示例</a>
					</h4>
					<form class="bs-docs-example" action="<%=url%>/order/cancel" method="post">
                                                <label>用户ID</label> 
						<input type="text" name="userId" placeholder="请输入用户ID" /><br />
                                                <label>订单号</label>
						<input type="text" name="orderId" placeholder="订单号" /><br />
						<button type="submit" class="btn">取消订单</button>
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
									<td>userId</td>
									<td>是</td>
									<td>用户ID</td>
								</tr>
								<tr>
									<td>orderId</td>
									<td>是</td>
									<td>订单号</td>
								</tr>
								 
							</tbody>
						</table>
					</div>

					<h4>
						<a href="javascript:return false;">返回说明</a>
					</h4>
				 			
					<p>正常情况下返回的JSON数据包：<pre>{ "statusCode":"200", "callbackData":true, "message":"上锁成功" }</pre>
					</p>

				</section>
				<!-- //////////////////////////取消订单/////////////////////////// END -->
                                
                
                <!-- //////////////////////////权限码查询/////////////////////////// START -->
				<section id="权限码查询">
					<div class="page-header">
						<h2>
							13. 权限码查询
							</h1>
					</div>
					<h4>
						<a href="javascript:return false;">接口地址</a>
					</h4>
					<p><pre>http请求方式: POST<br/>http://<%=servername+url%>/org/find</pre></p>
					<h4>
						<a href="javascript:return false;">示例</a>
					</h4>
					<form class="bs-docs-example" action="<%=url%>/org/find" method="post">
                                                <label>用户ID</label> 
						<input type="text" name="userId" placeholder="请输入用户ID" /><br />
						<button type="submit" class="btn">权限码查询</button>
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
									<td>userId</td>
									<td>是</td>
									<td>用户ID</td>
								</tr>
							</tbody>
						</table>
					</div>

					<h4>
						<a href="javascript:return false;">返回说明</a>
					</h4>
				 			
					<p>正常情况下返回的JSON数据包：<pre>{ "statusCode":"200", "callbackData":[ { "code":"a5819ImW", "id":17, "name":"万润权限" }, { "code":"WQegdidg", "id":16, "name":"同济权限" } ], "message":"接口调用成功" }</pre>
					</p>

				</section>
				<!-- //////////////////////////权限码查询/////////////////////////// END -->
                
                
                <!-- //////////////////////////权限码查询/////////////////////////// START -->
				<section id="添加权限码">
					<div class="page-header">
						<h2>
							14. 添加权限码
							</h1>
					</div>
					<h4>
						<a href="javascript:return false;">接口地址</a>
					</h4>
					<p><pre>http请求方式: POST<br/>http://<%=servername+url%>/org/add</pre></p>
					<h4>
						<a href="javascript:return false;">示例</a>
					</h4>
					<form class="bs-docs-example" action="<%=url%>/org/add" method="post">
                                                <label>用户ID</label> 
						<input type="text" name="userId" placeholder="请输入用户ID" /><br />
						<input type="text" name="code" placeholder="请输入权限码" /><br />
						<button type="submit" class="btn">添加权限码</button>
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
									<td>userId</td>
									<td>是</td>
									<td>用户ID</td>
								</tr>
								<tr>
									<td>code</td>
									<td>是</td>
									<td>权限码</td>
								</tr>
							</tbody>
						</table>
					</div>

					<h4>
						<a href="javascript:return false;">返回说明</a>
					</h4>
				 			
					<p>正常情况下返回的JSON数据包：<pre>{ "statusCode":"200", "message":"添加成功" }</pre>
					</p>

				</section>
				<!-- //////////////////////////添加权限码/////////////////////////// END -->
				
				
				<!-- //////////////////////////权限码查询/////////////////////////// START -->
				<section id="删除权限码">
					<div class="page-header">
						<h2>
							15. 删除权限码
							</h1>
					</div>
					<h4>
						<a href="javascript:return false;">接口地址</a>
					</h4>
					<p><pre>http请求方式: POST<br/>http://<%=servername+url%>/org/delete</pre></p>
					<h4>
						<a href="javascript:return false;">示例</a>
					</h4>
					<form class="bs-docs-example" action="<%=url%>/org/delete" method="post">
                                                <label>用户ID</label> 
						<input type="text" name="userId" placeholder="请输入用户ID" /><br />
						<input type="text" name="code" placeholder="请输入权限码" /><br />
						<button type="submit" class="btn">删除权限码</button>
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
									<td>userId</td>
									<td>是</td>
									<td>用户ID</td>
								</tr>
								<tr>
									<td>code</td>
									<td>是</td>
									<td>权限码</td>
								</tr>
							</tbody>
						</table>
					</div>

					<h4>
						<a href="javascript:return false;">返回说明</a>
					</h4>
				 			
					<p>正常情况下返回的JSON数据包：<pre>{ "statusCode":"200", "message":"删除成功" }</pre>
					</p>

				</section>
				<!-- //////////////////////////删除权限码/////////////////////////// END -->
                
                
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
