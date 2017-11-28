 <%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<vsc:pagerForm action="#rel#" id="pagerForm"></vsc:pagerForm>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/work/videodevice" method="post">
		<div class="searchBar">
			<ul class="searchContent">
				   
				<li><label>视频设备编号:</label> 
					<input type="text" value="${param.search_LIKE_deviceNo}" name="search_LIKE_deviceNo" /> </li>   
				<li><label>视频设备名称:</label> 
					<input type="text" value="${param.search_LIKE_name}" name="search_LIKE_name" /> </li>      
			 
			</ul>
			<div class="subBar">
				<ul>
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">检索</button>
							</div>
						</div></li> 
				 
				</ul>
			</div>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" title="添加视频设备" href="${ctx}/work/videodevice/new?navTabId=work_videodevice" target="dialog" rel="videodevice_new"><span>添加</span></a></li>
			<li><a class="edit" title="编辑视频设备" href="${ctx}/work/videodevice/update/{sid}?navTabId=work_videodevice" target="dialog" rel="videodevice_update"
				warn="请选择一个记录"><span>编辑</span></a></li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="${ctx}/work/videodevice/delete" class="delete"><span>删除</span></a></li>
			<li class="line">line</li>
			 
			<li><a class="reload "    title="在线预览" href="${ctx}/work/videodevice/onlineview/{sid}?navTabId=work_videodevice" target="dialog"    rel="videodevice_online_view"
				warn="请选择一个记录"><span>在线预览</span></a></li>	
			<li><a class="icon"  title="历史视频" href="${ctx}/work/videodevice/ls/{sid}?navTabId=work_videodevice" target="dialog" rel="videodevice_detail_list"
				warn="请选择一个记录"><span>历史视频</span></a></li>		
		 </ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="40" align="center">序号</th>
				<th width="30"><input type="checkbox" group="ids" class="checkboxCtrl"></th>    
				<th <vsc:orderField name="deviceNo"/>>视频设备编号</th>   
				<th <vsc:orderField name="name"/>>视频设备名称</th>   
				<th <vsc:orderField name="deviceIp"/>>设备网络地址</th>   
				<th <vsc:orderField name="isEnabled"/>>状态</th>  			 
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.content}" var="varitem" varStatus="varindex">
				<tr target="sid" rel="${varitem.id}">
					<td align="center">${varindex.count+(page.number * page.size)}</td>
					<td><input name="ids" value="${varitem.id }" type="checkbox"></td>    
					<td> <a href="${ctx}/work/videodevice/view/${varitem.id}" target="dialog" title="查看视频设备信息" rel="videodevice_view"> ${varitem.deviceNo} </a> 
					</td>   
					<td>  ${varitem.name} 
					</td>   
					<td> ${varitem.deviceIp} 
					</td>   
					<td>  ${varitem.isEnabled} 
					</td>   
					 
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<vsc:pagination page="${page}" numPerPageOnchange="navTabPageBreak({numPerPage:this.value})"></vsc:pagination>
	</div>
</div>
