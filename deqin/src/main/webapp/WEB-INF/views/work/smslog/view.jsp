<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<div class="pageContent">
	<div class="pageFormContent" layoutH="56">
		<table class="viewTable">
			<tbody>
				<tr>
					<td width="25%" align="right">姓名:</td>
					<td align="left">
					<a href="${ctx}/work/patient/view/${varitem.patient.id}" target="dialog" title="查看患者" rel="work_patient_view">
					${vm.patient.name}</a></td>
				</tr>
				<tr>
					<td align="right">手机号:</td>
					<td align="left">${vm.patient.mobile}</td>
				</tr>
				<tr>
					<td align="right">发送时间:</td>
					<td align="left"><fmt:formatDate value='${vm.sendTime}' pattern='yyyy-MM-dd HH:mm:ss' /></td>
				</tr>
			 
				<tr>
					<td align="right">发送内容:</td>
					<td align="left">${vm.content}</td>
				</tr>
				<tr>
					<td align="right">发送结果:</td>
					<td align="left">${vm.results}</td>
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