<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<div class="pageContent">

	<div class="pageFormContent" layoutH="56"> 
		<table class="viewTable">
			<tbody>
				<tr>
					<td width="10%" align="right">校门名称:</td>
					<td align="left">
${vm.name}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">校区(停车场):</td>
					<td align="left">
${vm.parkingLot.name}					</td>
				</tr>
				<tr>
					<td width="10%" align="right">创建时间:</td>
					<td align="left">
<fmt:formatDate value='${vm.createTime}' pattern='yyyy-MM-dd HH:mm:ss'/>					</td>
				</tr>
				<tr>
					<td align="right">使用状态：</td>
					<td align="left"><s:message code="user.isenabled.${vm.isEnabled}"></s:message></td>
					<td align="right"></td>
					<td align="left"></td>
				</tr>
				<tr>
					<td width="10%" align="right">备注:</td>
					<td align="left">
${vm.remark}					</td>
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