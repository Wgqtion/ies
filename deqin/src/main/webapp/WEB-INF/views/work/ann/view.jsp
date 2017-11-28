<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<div class="pageContent">
	<div class="pageFormContent" layoutH="56">
		<table class="viewTable">
			<tbody>
				<tr>
					<td width="15%" align="right">标题:</td>
					<td align="left" colspan="3">${vm.title}</td>
				</tr>
				<tr>
					<td align="right" valign="top">详细内容:</td>
					<td align="left" colspan="3"><textarea rows="5" cols="50" readonly="readonly">${vm.context}</textarea></td>
				</tr>
				<tr>
					<td align="right" valign="top">发送人员:</td>
					<td align="left" colspan="3"><vsc:fetchElementPropertyToString propertyName="sendUser.name" list="${vm.sends}" /></td>
				</tr>
				<tr>
					<td align="right">创建人:</td>
					<td align="left">${vm.createUser.name}</td>
					<td align="right">创建时间:</td>
					<td align="left"><fmt:formatDate value='${vm.createTime}' pattern='yyyy-MM-dd HH:mm:ss' /></td>
				</tr>
				<tr>
					<td align="right">状态:</td>
					<td align="left" colspan="3"><s:message code="ann.state.${vm.state}" /></td>
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