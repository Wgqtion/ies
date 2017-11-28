<%@page import="com.vsc.business.gerd.web.httpservices.ClientController"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<title>平台第三方客户端接口说明</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta HTTP-EQUIV="pragma" CONTENT="no-cache"/>
<meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate"/>
<meta HTTP-EQUIV="expires" CONTENT="0"/>

<%
    String servername=request.getServerName();
	String url = request.getContextPath()
			+ "/"
			+ ClientController.PATH;
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
						<li class=""><a href="javascript:return false;"><h3>客户端接口说明</h3></a></li>
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
					<li><a href="#进场接口"><i class="icon-chevron-right"></i>1.进场接口</a></li>
					<li><a href="#出场接口"><i class="icon-chevron-right"></i>2.出场接口</a></li>
					<li><a href="#车位上锁"><i class="icon-chevron-right"></i>3.车位上锁</a></li>
				   <li><a href="#单个车位状态上报"><i class="icon-chevron-right"></i>4.单个车位状态上报</a></li>
					<li><a href="#批量车位状态上报"><i class="icon-chevron-right"></i>5.批量车位状态上报</a></li>
					<li><a href="#车位解锁"><i class="icon-chevron-right"></i>6.车位解锁</a></li>
					<li><a href="#地锁事件上报"><i class="icon-chevron-right"></i>7.地锁事件上报服务</a></li>
				</ul>
			</div>
			<div class="span9">



				<!-- Download
        ================================================== -->
				<section id="进场接口">
					<div class="page-header">
						<h2>
							1. 进场接口
							</h1>
					</div>
					<h4>
						<a href="javascript:return false;">接口地址</a>
					</h4>
					<p><pre>http请求方式: POST<br/>http://<%=servername+url%>/order/inschool</pre></p>
					<h4>
						<a href="javascript:return false;">示例</a>
					</h4>
					<form class="bs-docs-example" action="<%=url%>/order/inschool" method="post">

					<label>相机IP</label> <input type="text" name="inCameraIp" value="127.0.0.1"	placeholder="请输入相机ip" /><br /> 
					<label>车牌号</label> <input	type="text" name="inPlateNo"  value="沪A12345" placeholder="请输入车牌号" /><br />
					<label>进场时间</label><input  type="text" name="inTime" value="2016-04-10 12:10:20"/> <br/>
					<label>图片URL</label><input  type="text" name="inPicName" value="https://www.baidu.com/img/bd_logo1.png"/> <br/>
					<label>进场入口ID</label><input  type="text" name="inDoorId" value=""/><br/>
						
							
						<button type="submit" class="btn">提交</button>
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
									<td>inCameraIp</td>
									<td>是</td>
									<td>相机IP</td>
								</tr>
								<tr>
									<td>inPlateNo</td>
									<td>是</td>
									<td>车牌号</td>
								</tr>
									<tr>
									<td>inTime</td>
									<td>是</td>
									<td>进场时间(yyyy-MM-dd HH:mm:ss)</td>
								</tr>
								<tr>
									<td>inPicName</td>
									<td>是</td>
									<td>进场照片URL</td>
								</tr>
								 <tr>
									<td>inDoorId</td>
									<td>是</td>
									<td>进场入口ID</td>
								</tr>
								 
							</tbody>
						</table>
					</div>

					<h4>
						<a href="javascript:return false;">返回说明</a>
					</h4>
				 			
					<p>正常情况下返回的JSON数据包：<pre>{ "statusCode":"200", "callbackData":"a0b25c32-4017-4e8a-bf7a-e2e4f83b92a7", "message":"接口调用成功" }</pre>
					</p>

				</section>
				
				<section id="出场接口">
					<div class="page-header">
						<h2>
							2. 出场接口
							</h1>
					</div>
					<h4>
						<a href="javascript:return false;">接口地址</a>
					</h4>
					<p><pre>http请求方式: POST<br/>http://<%=servername+url%>/order/outschool</pre></p>
					<h4>
						<a href="javascript:return false;">示例</a>
					</h4>
					<form class="bs-docs-example" action="<%=url%>/order/outschool" method="post">

					<label>相机IP</label> <input type="text" name="outCameraIp" value="127.0.0.1"	placeholder="请输入相机ip" /><br /> 
					<label>车牌号</label> <input	type="text" name="outPlateNo"  value="沪A12345" placeholder="请输入车牌号" /><br />
					<label>出场时间</label><input  type="text" name="outTime" value="2016-04-10 12:10:20"/> <br/>
					<label>图片URL</label><input  type="text" name="outPicName" value="https://www.baidu.com/img/bd_logo1.png"/> <br/>
					<label>出场出口</label><input  type="text" name="outDoorId" value="1970"/><br/>
						
							
						<button type="submit" class="btn">提交</button>
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
									<td>outCameraIp</td>
									<td>是</td>
									<td>相机IP</td>
								</tr>
								<tr>
									<td>outPlateNo</td>
									<td>是</td>
									<td>车牌号</td>
								</tr>
									<tr>
									<td>outTime</td>
									<td>是</td>
									<td>出场时间(yyyy-MM-dd HH:mm:ss)</td>
								</tr>
								<tr>
									<td>outPicName</td>
									<td>是</td>
									<td>出场照片URL</td>
								</tr>
								 <tr>
									<td>outDoorId</td>
									<td>是</td>
									<td>出场出口ID</td>
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
				
				<!-- //////////////////////////车位上锁/////////////////////////// START -->
				<section id="车位上锁">
					<div class="page-header">
						<h2>
							3. 车位上锁
							</h1>
					</div>
					<h4>
						<a href="javascript:return false;">接口地址</a>
					</h4>
					<p><pre>http请求方式: POST<br/>http://<%=servername+url%>/shangsuo/locked</pre></p>
					<h4>
						<a href="javascript:return false;">示例</a>
					</h4>
					<form class="bs-docs-example" action="<%=url%>/shangsuo/locked" method="post">

						<label>车位编码</label> 
						<input type="text" name="parkingGarageId" placeholder="请输入车位ID" /><br />
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
									<td>parkingGarageId</td>
									<td>是</td>
									<td>车位ID</td>
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
				<!-- //////////////////////////车位解锁/////////////////////////// END -->
				 
				
				
				
				<section id="单个车位状态上报">
					<div class="page-header">
						<h2>
							4. 单个车位状态上报
							</h1>
					</div>
					<h4>
						<a href="javascript:return false;">接口地址</a>
					</h4>
					<p><pre>http请求方式: POST<br/>http://<%=servername+url%>/garage/oneincar</pre></p>
					<h4>
						<a href="javascript:return false;">示例</a>
					</h4>
					<form class="bs-docs-example" action="<%=url%>/garage/oneincar" method="post">
					<label>车位编号</label><input  type="text" name="parkingName" value="1001"/> <br/>
					<label>相机IP</label><input  type="text" name="cameraIp" value="127.0.0.1"/> <br/>
					<label>车位状态</label><input  type="text" name="status" value="1"/> <br/>
					<label>上报时间</label><input  type="text" name="intime" value="2016-04-10 12:10:20"/> <br/>
					<label>车牌号</label><input  type="text" name="carNo" value="沪A12345"/>  <br/>	
							
						<button type="submit" class="btn">提交</button>
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
								<tr>
									<td>cameraIp</td>
									<td>是</td>
									<td>相机IP</td>
								</tr>
									<tr>
									<td>status</td>
									<td>是</td>
									<td>车位状态</td>
								</tr>
								<tr>
									<td>intime</td>
									<td>是</td>
									<td>上报时间(yyyy-MM-dd HH:mm:ss)</td>
								</tr>
								 <tr>
									<td>carNo</td>
									<td>是</td>
									<td>车牌号</td>
								</tr>
								 
							</tbody>
						</table>
					</div>

					<h4>
						<a href="javascript:return false;">返回说明</a>
					</h4>
				 			
					<p>正常情况下返回的JSON数据包：<pre>{ "statusCode":"200", "callbackData":"true", "message":"接口调用成功" }</pre>
					</p>

				</section>
				
				<section id="批量车位状态上报">
					<div class="page-header">
						<h2>
							5. 批量车位状态上报
							</h1>
					</div>
					<h4>
						<a href="javascript:return false;">接口地址</a>
					</h4>
					<p><pre>http请求方式: POST<br/>http://<%=servername+url%>/garage/incar</pre></p>
					<h4>
						<a href="javascript:return false;">示例</a>
					</h4>
					<form class="bs-docs-example" action="<%=url%>/garage/incar" method="post">
					<label>上报时间</label><input  type="text" name="intime" value="2016-04-10 12:10:20"/> <br/>
					<label>车位1:</label><br/>	
					<label>车位编号</label><input  type="text" name="carnologs[0].parkingName" value="1001"/> (carnologs[0].parkingName)<br/>
					<label>相机IP</label><input  type="text" name="carnologs[0].cameraIp" value="127.0.0.1"/> (carnologs[0].cameraIp)<br/>
					<label>车位状态</label><input  type="text" name="carnologs[0].status" value="0"/> (carnologs[0].status)<br/>
					<label>车牌号</label><input  type="text" name="carnologs[0].carNo" value="沪A12345"/>  (carnologs[0].carNo)<br/>	
					<label>车位2:</label><br/>					
					<label>车位编号</label><input  type="text" name="carnologs[1].parkingName" value="1002"/> (carnologs[1].parkingName)<br/>
					<label>相机IP</label><input  type="text" name="carnologs[1].cameraIp" value="127.0.0.2"/> (carnologs[1].cameraIp)<br/>
					<label>车位状态</label><input  type="text" name="carnologs[1].status" value="0"/> (carnologs[1].status)<br/>
					<label>车牌号</label><input  type="text" name="carnologs[1].carNo" value="沪B12345"/>  (carnologs[1].carNo)<br/>
					<label>车位X:依次类推<br/>					
					<label>车位编号</label><input  type="text" name="carnologs[2].parkingName" value="1003"/> (carnologs[X].parkingName)<br/>
					<label>相机IP</label><input  type="text" name="carnologs[2].cameraIp" value="127.0.0.3"/> (carnologs[X].cameraIp)<br/>
					<label>车位状态</label><input  type="text" name="carnologs[2].status" value="1"/> (carnologs[X].status)<br/>
					<label>车牌号</label><input  type="text" name="carnologs[2].carNo" value="沪C12345"/>  (carnologs[X].carNo)<br/>	
						<button type="submit" class="btn">提交</button>
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
									<td>carnologs[0].parkingName</td>
									<td>是</td>
									<td>车位编号[X]为数据索引</td>
								</tr>
								<tr>
									<td>carnologs[0].cameraIp</td>
									<td>是</td>
									<td>相机IP</td>
								</tr>
									<tr>
									<td>carnologs[0].status</td>
									<td>是</td>
									<td>车位状态</td>
								</tr>
								 <tr>
									<td>carnologs[0].carNo</td>
									<td>是</td>
									<td>车牌号</td>
								</tr>
								<tr>
									<td>intime</td>
									<td>是</td>
									<td>上报时间(yyyy-MM-dd HH:mm:ss)</td>
								</tr>
								
								 
							</tbody>
						</table>
					</div>

					<h4>
						<a href="javascript:return false;">返回说明</a>
					</h4>
				 			
					<p>正常情况下返回的JSON数据包：<pre>{ "statusCode":"200", "callbackData":"true", "message":"接口调用成功" }</pre>
					</p>

				</section>
				
				
				
							
				<!-- //////////////////////////车位解锁/////////////////////////// START -->
				<section id="车位解锁">
					<div class="page-header">
						<h2>
							6. 车位解锁
							</h1>
					</div>
					<h4>
						<a href="javascript:return false;">接口地址</a>
					</h4>
					<p><pre>http请求方式: POST<br/>http://<%=servername+url%>/jiesuo/locked</pre></p>
					<h4>
						<a href="javascript:return false;">示例</a>
					</h4>
					<form class="bs-docs-example" action="<%=url%>/jiesuo/locked" method="post">

						<label>车位编码</label> 
						<input type="text" name="parkingGarageId" placeholder="请输入车位ID" /><br />
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
									<td>parkingGarageId</td>
									<td>是</td>
									<td>车位ID</td>
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
				<!-- //////////////////////////车位解锁/////////////////////////// END -->
				
				
						
				<!-- //////////////////////////地锁事件上报服务/////////////////////////// START -->
				<section id="地锁事件上报">
					<div class="page-header">
						<h2>
							7.地锁事件上报服务
							</h1>
					</div>
					<h4>
						<a href="javascript:return false;">接口地址</a>
					</h4>
					<p><pre>http请求方式: POST<br/>http://<%=servername+url%>/locked/event/new</pre></p>
					<h4>
						<a href="javascript:return false;">示例</a>
					</h4>
					<form class="bs-docs-example" action="<%=url%>/locked/event/new" method="post">
 

						<label>地锁编号</label> <input type="text" name="lockNum"
							placeholder="请输入地锁编号" /><br /> 
						<label>地锁区域</label> 
						<input type="text" name="lockArea"
							placeholder="请输入地锁区域" /><br /> 	
							
						<label>设备状态和电量</label> <input type="text" name="state"
							placeholder="请输入设备状态和电量" /><br /> 
						<label>数据段控制</label> <input type="text" name="mcOpen"
							placeholder="请输入数据段控制" /><br /> 
						<label>事件类型</label> 
						<input type="text" name="eventType"
							placeholder="请输入事件类型" /><br /> 
                        
					  <label>发生时间</label> <input type="text" name="reportedTime"
							placeholder="yyyy-MM-dd HH:mm:ss" /><br /> 
						<button type="submit" class="btn">地锁事件上报</button>
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
									<td>lockNum</td>
									<td>是</td>
									<td>地锁编号（设备 ID Byte5-6）</td>
								</tr>
								<tr>
									<td>lockArea</td>
									<td>是</td>
									<td>地锁区域编号</td>
								</tr>
								<tr>
									<td>eventType</td>
									<td>是</td>
									<td>事件类型
									  0X33 上线
             						  0x44 下线									 
									  0X55 事件
            						  0x66 特殊情况
									
									</td>
								</tr>
								<tr>
									<td>state</td>
									<td>是</td>
									<td>设备状态和电量(报上来是int,转换为二进制分析意义)<br/>
									00xx x000                电量% 小于等于4.5V<br/>
00xx x001                电量% 4.8V<br/>
00xx x011                电量% 5.1V<br/>
00xx x010                电量% 5.4V<br/>
00xx x100                电量% 5.7V<br/>
00xx x101                电量% 6.0V<br/>
00xx x110                电量% 6.3V<br/>
00xx x111                电量% 大于等于6.6V<br/>
00xx 1xxx                地锁开关状态1：开0：关<br/>
00x1 xxxx                是否有车1：无0：有<br/>
001x xxxx                系统被锁住(锁位MC_Open)<br/>
------------------------------------	 
									</td>
								</tr>
								 <tr>
									<td>mcOpen</td>
									<td>否</td>
									<td> MC_Open数据段控制  <br/> 开锁为90度,关锁为0度<br/>
	 0x01   433请求开锁
	 0x02   ZIGBEE请求开锁
	 0x03   BLE请求开锁
0x04   BLE请求开锁(高级用户)
     0x05   倒下去3分钟未检测到车辆自动抬起
     0x06   车走后自动抬起
   

	 0x08   433请求关锁
	 0x09   ZIGBEE请求关锁
	 0x0A   BLE请求关锁
	 0x0B   BLE请求关锁(高级用户)
	 0x0C   上升过载重试中关锁,          
0x0D   上升过载重试三次也法完成,


0XD0 系统被ZIGBEE锁住
0XD2 系统被433锁住

0XD5 系统被ZIGBEE解锁
0XD7 系统被433解锁

	 正常状态0x90<br/>
	 0x51 (0x91)	 433请求开锁                    正常完成, 上传数据后自动加0X40    
	 0x52 (0x92)	 ZIGBEE请求开锁      		    正常完成,				   
	 0x53 (0x93)	 BLE请求开锁          			正常完成,	
0x54(0X94)   BLE请求开锁(高级用户)			正常完成
	    
	 0x55 (0x95)  倒下去3分钟未检测到车辆自动抬起     			正常完成,
0x56(0X96)   车走后自动抬起	   			    正常完成
			  
	 0x58 (0x98)	  433请求关闭                   正常完成,				   
	 0x59(0x99)	 ZIGBEE请求关闭                正常完成,			    
	 0x5A (0x9A)	 BLE请求关闭                   正常完成,				   
	 0x5B(0x9B)	 BLE请求关锁(高级用户)          正常完成,	

	 0x5C(0x9C)	上升过载重试中关锁               正常完成,	
     0x5C(0x9D)	上升过载重试三次也法完成         正常完成,
		    
	 过电流状态0xA1<br/>
0x61 (0xA1)	 433请求开锁                    请求过程中过电流,    
	 0x62 (0xA2)	 ZIGBEE请求开锁      		    请求过程中过电流,			   
	 0x63 (0xA3)	 BLE请求开锁          			请求过程中过电流,,	
0x64(0XA4)   BLE请求开锁(高级用户)			请求过程中过电流,
	    
	 0x65 (0xA5)  倒下去3分钟未检测到车辆自动抬起   请求过程中过电流
0x66(0XA6)   车走后自动抬起	   			    请求过程中过电流,
			  
	 0x68 (0xA8)	  433请求关闭                   请求过程中过电流,			   
	 0x69(0xA9)	 ZIGBEE请求关闭                请求过程中过电流,,			    
	 0x6A (0xAA)	 BLE请求关闭                请求过程中过电流,		   
	 0x6B(0xAB)	 BLE请求关锁(高级用户)          请求过程中过电流,,
	
	 0x6C(0xAC)	上升过载重试中关锁               请求过程中过电流,,	
     0x6C(0xAD)	上升过载重试三次也法完成         请求过程中过电流,,
   
	 超时状态0xB1<br/>
0x71 (0xB1)	 433请求开锁                    请求过程中开合超时,    
	 0x72 (0xB2)	 ZIGBEE请求开锁      		    请求过程中开合超时,			   
	 0x73 (0xB3)	 BLE请求开锁          			请求过程中开合超时,,	
0x74(0XB4)   BLE请求开锁(高级用户)			请求过程中开合超时,	    
	 0x75 (0xB5)  倒下去3分钟未检测到车辆自动抬起   请求过程中开合超时
0x76(0XB6)   车走后自动抬起	   			    请求过程中开合超时,
			  
	 0x78 (0xB8)	  433请求关闭                   请求过程中开合超时,			   
	 0x79(0xB9)	 ZIGBEE请求关闭                请求过程中开合超时,,			    
	 0x7A (0xBA)	 BLE请求关闭                请求过程中开合超时,		   
	 0x7B(0xBB)	 BLE请求关锁(高级用户)          请求过程中开合超时,,	
	 0x7C(0xBC)	上升过载重试中关锁               请求过程中开合超时,,	
     0x7C(0xBD)	上升过载重试三次也法完成         请求过程中开合超时,,
	 系统异常复位0xC0<br/>
	 0x80(0xC0) 系统开机代码（复位或者重启）,但未上传数据,上传数据后自动加0X40 

     0xD0系统被ZIGBEE锁住
     0XD2系统被433锁住
     0XD5系统由ZIGBEE解锁
     0xD7系统由433解锁 

									</td>
								</tr>
								 <tr>
									<td>reportedTime</td>
									<td>否</td>
									<td>发生时间(yyyy-MM-dd HH:mm:ss)
									留空为当前系统时间
									</td>
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
				<!-- //////////////////////////地锁事件上报服务/////////////////////////// END -->
				
				


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