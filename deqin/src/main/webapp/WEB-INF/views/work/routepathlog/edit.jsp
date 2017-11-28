<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>

<div class="pageContent">
	<form method="post" action="${ctx}/work/routepathlog/${action}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="id" value="${id}">
		<vsc:token tokenName="work.routepathlog.create"></vsc:token>
		<vsc:callback></vsc:callback>
		<div class="pageFormContent" layoutH="56">
			<table class="formTable">
					<tr>
					<td class="fieldName">CREATE_TIME:</td>
						<td class="fieldInput"><label><input type="text" id="createTime" name="createTime" class="date" value="<fmt:formatDate value='${vm.createTime}' pattern='yyyy-MM-dd'/>" dateFmt="yyyy-MM-dd" readonly="true" validate="{required:true}"/></label><span for="createTime" generated="true" style="display: none" class="error"></span>
						</td>
					</tr>
					<tr>
					<td class="fieldName">USER_ID:</td>
						<td class="fieldInput"><label><input type="text" id="userId" name="userId" value="${vm.userId}" validate="{required:true}"/></label><span for="userId" generated="true" style="display: none" class="error"></span>
						</td>
					</tr>
					<tr>
					<td class="fieldName">SUBTYPE:</td>
						<td class="fieldInput"><label><input type="text" id="subtype" name="subtype" value="${vm.subtype}" validate="{required:true}"/></label><span for="subtype" generated="true" style="display: none" class="error"></span>
						</td>
					</tr>
					<tr>
					<td class="fieldName">LOG_CONTENT:</td>
						<td class="fieldInput"><label><input type="text" id="logContent" name="logContent" value="${vm.logContent}" validate="{required:true}"/></label><span for="logContent" generated="true" style="display: none" class="error"></span>
						</td>
					</tr>
			</table>
		</div>
		<div class="formBar">
			<ul> 
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>