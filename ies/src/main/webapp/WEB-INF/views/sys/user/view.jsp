<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<div class="pageContent">
	<div class="pageFormContent" layoutH="56">
		<table class="viewTable">
			<tbody>
				<tr>
					<td width="15%" align="right">登录名：</td>
					<td width="30%" align="left">${vm.loginName}</td>
					<td width="15%" align="right">姓名：</td>
					<td align="left">${vm.name}</td>
				</tr>
				<tr>
					<td align="right">创建时间：</td>
					<td align="left"><fmt:formatDate value="${vm.createDate}" pattern="yyyy-MM-dd HH:mm" /></td>
					<td align="right">使用状态：</td>
					<td align="left"><s:message code="user.isenabled.${vm.isEnabled}"></s:message></td>
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