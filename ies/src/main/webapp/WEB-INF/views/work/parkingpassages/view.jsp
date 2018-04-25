<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<div class="pageContent">

	<div class="pageFormContent" layoutH="56">
		<table class="viewTable">
			<tbody>
				<tr>
					<td align="right">场区名称:</td>
					<td align="left">${vm.parkingLot.name}</td>
				</tr>
				<tr>
					<td  align="right">出入口名称:</td>
					<td align="left">${vm.name}</td>
				</tr>
				<tr>
					<td  align="right">纬度坐标:</td>
					<td align="left">${vm.itudeLong}</td>
				</tr>
				<tr>
					<td  align="right">经度坐标:</td>
					<td align="left">${vm.itudeLat}</td>
				</tr>
				<tr>
					<td  align="right">状态:</td>
					<td align="left"><s:message code="parkingpassages.isenabled.${vm.isEnabled}"/></td>
				</tr>
				<tr>
					<td  align="right">备注:</td>
					<td align="left">${vm.mark}</td>
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