<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<div class="pageContent">
	<div class="pageFormContent" layoutH="56">
		<table class="viewTable">
			<tbody>
				<tr>
					<td width="15%" align="right">标题:</td>
					<td align="left" colspan="3">${vm.announcement.title}</td>
				</tr>
				<tr>
					<td align="right" valign="top">详细内容:</td>
					<td align="left" colspan="3">
					<vsc:textareaHTML input="${vm.announcement.context}"></vsc:textareaHTML>
					</td>
				</tr>
				<tr>
					<td align="right">接受人:</td>
					<td align="left">${vm.sendUser.name}</td>
					<td align="right">发送时间:</td>
					<td align="left"><fmt:formatDate value='${vm.sendDate}' pattern='yyyy-MM-dd HH:mm:ss' /></td>

				</tr>

				<tr>
					<td align="right">已阅读:</td>
					<td align="left"><s:message code="annsend.isread.${vm.isRead}"></s:message></td>
					<td align="right">阅读时间:</td>
					<td align="left"><fmt:formatDate value='${vm.readTime}' pattern='yyyy-MM-dd HH:mm:ss' /></td>

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