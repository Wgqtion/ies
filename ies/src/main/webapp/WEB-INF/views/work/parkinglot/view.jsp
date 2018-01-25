<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<div class="pageContent">
	<div class="pageFormContent" layoutH="56">
		<table class="viewTable">
			<tbody>
				<tr>
					<td width="15%" align="right">校区名:</td>
					<td width="30%" align="left">${vm.name}</td>
					<td width="15%" align="right"></td>
					<td align="left"></td>
				</tr> 
				<tr>
					<td class="fieldName">百度纬度坐标:</td>
					<td class="fieldInput">${vm.baiduLatitudeLng}</td>
						<td class="fieldName">百度经度坐标:</td>
					<td class="fieldInput">${vm.baiduLatitudeLat}</td>
				</tr>				 
				<tr>
					<td class="fieldName">地址:</td>
					<td class="fieldInput" colspan="3">${vm.address}</td>
				</tr>
				<tr>
					<td class="fieldName">车位数量:</td>
					<td class="fieldInput">${vm.carNumber}</td>
					<td class="fieldName">状态:</td>
					<td class="fieldInput"><s:message code="parkinglot.isenabled.${vm.isEnabled}" /></td>
			
				</tr>
				 <tr>
					<td class="fieldName" valign="top">创建时间:</td>
					<td class="fieldInput" colspan="3"><fmt:formatDate value='${vm.createTime}' pattern='yyyy-MM-dd HH:mm' /></td>
				</tr>
				<tr>
					<td class="fieldName" valign="top">说明:</td>
					<td class="fieldInput" colspan="3">${vm.description}</td>
				</tr>
				<tr>
					<td class="fieldName" valign="top">校区图片:</td>
					<td class="fieldInput" colspan="3"><img style="max-width:650px" src="${vm.photoAttach.downloadPath}"/> 
					</td>
				</tr>
				
			</tbody>
		</table>
	</div>
	<div class="formBar">
		<ul>
			<li>
				<div class="button">
					<div class="buttonContent">
						<button type="button" class="close">关闭</button>
					</div>
				</div>
			</li>
		</ul>
	</div>
</div>