<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<vsc:pagerForm action="#rel#" id="pagerForm"></vsc:pagerForm>

<div class="pageHeader">
	 
		<div class="searchBar">
			<ul class="searchContent">

				<li><label>视频开始时间:</label></li>
				<li><label>视频结束时间:</label></li>

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
	 
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="40" align="center">序号</th>				 
				<th>视频开始时间</th>
				<th>视频结束时间</th>
				<th>操作</th>

			</tr>
		</thead>
		<tbody>

			<tr>
				<td align="center">1</td>
				<td>2016-07-31 15:30:00</td>
				<td>2016-07-31 15:34:00</td>
				<td> 
				<a  title="历史视频播放" href="${ctx}/work/videodevice/onlineview/1?navTabId=work_videodevice" target="dialog"    rel="videodevice_ls_online_view">播放 </a>
				<a  title="历史视频播放" href="${ctx}/work/videodevice/kkkkk"   >下载 </a>
				</td>
			</tr>
			<tr>
				<td align="center">2</td>
				<td>2016-08-02 16:31:00</td>
				<td>2016-08-02 16:34:00</td>
				<td><a  title="历史视频播放" href="${ctx}/work/videodevice/onlineview/1?navTabId=work_videodevice" target="dialog"    rel="videodevice_ls_online_view">播放 </a>
				<a  title="历史视频播放" href="${ctx}/work/videodevice/kkkkk"   >下载 </a>
				</td>
			</tr>
			<tr>
				<td align="center">3</td>
				<td>2016-08-03 6:30:00</td>
				<td>2016-08-03 7:34:00</td>
				<td><a  title="历史视频播放" href="${ctx}/work/videodevice/onlineview/1?navTabId=work_videodevice" target="dialog"    rel="videodevice_ls_online_view">播放 </a>
				<a  title="历史视频播放" href="${ctx}/work/videodevice/kkkkk"   >下载 </a>
				</td>
			</tr>
			 
		</tbody>
	</table> 
</div>
