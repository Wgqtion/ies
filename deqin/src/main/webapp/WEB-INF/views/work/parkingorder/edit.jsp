<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>

<div class="pageContent">
	<form method="post" action="${ctx}/work/parkingorder/${action}"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="id" value="${id}">
		<vsc:token tokenName="work.parkingorder.create"></vsc:token>
		<vsc:callback></vsc:callback>
		<div class="pageFormContent" layoutH="56">
			<table class="formTable">
				<td class="fieldName">车牌号:</td>
				<td class="fieldInput"><label><input type="text"
						id="inPlateNo" name="inPlateNo" value="${vm.inPlateNo}"
						validate="{required:true}" /></label><span for="inPlateNo"
					generated="true" style="display: none" class="error"></span></td>
					<td class="fieldName">车辆编号:</td>
					<td class="fieldInput"><label><input type="text"
							id="inChargeUser" name="inChargeUser" value="${vm.inChargeUser}"
							validate="{required:true}" /></label><span for="inChargeUser"
						generated="true" style="display: none" class="error"></span></td>
				</tr>
			 
				<tr>
					<td class="fieldName">停车位:</td>
					<td class="fieldInput"><label>
					<input name="parkingGarageGroup.id" value="${vm.parkingGarage.id}" type="hidden" />
					<input  id="parkingGarageGroup_name" name="parkingGarageGroup.name" value="${vm.parkingGarage.name}" type="text" readonly="readonly"/> 
					<a class="btnLook" rel="parkingOrder_parkingGarage_select" title="选择停车位" href="${ctx}/work/parkinggarage/select?single=true&search_EQ_isEnabled=1" lookupGroup="parkingGarageGroup">查找带回</a> <span class="info">(选择停车位)</span>
					</label><span for="parkingGarageGroup_name" generated="true" style="display: none" class="error"></span></td>
					</td>
				</tr>
					<tr>
					<td class="fieldName">进口:</td>
					<td class="fieldInput">
					<label>
					<input name="inDoorGroup.id" value="${vm.inDoor.id}" type="hidden" />
					<input validate="{required:true}"   readonly="readonly"  id="inDoorGroup_name" name="inDoorGroup.name" value="${vm.inDoor.name}" type="text"/> 
					<a class="btnLook" rel="parkingOrder_inDoor_select" title="选择出入口" href="${ctx}/work/passages/select?single=true&search_EQ_isEnabled=1" lookupGroup="inDoorGroup">查找带回</a> <span class="info">(选择出入口)</span>
					</label><span for="inDoorGroup_name" generated="true" style="display: none" class="error"></span></td>
					<td class="fieldName">进场时间:</td>
					<td class="fieldInput"><label><input type="text"
							id="inTime" name="inTime" class="date"
							value="<fmt:formatDate value='${vm.inTime}' pattern='yyyy-MM-dd HH:mm'/>"
							dateFmt="yyyy-MM-dd HH:mm" readonly="true" validate="{required:true}" /></label><span
						for="inTime" generated="true" style="display: none" class="error"></span>
					</td>
					</tr>
				 
					<tr>
					<td class="fieldName">出口:</td>
					<td class="fieldInput">
					<label>
					<input name="outDoorGroup.id" value="${vm.outDoor.id}" type="hidden" />
					<input   readonly="readonly" id="outDoorGroup_name" name="outDoorGroup.name" value="${vm.outDoor.name}" type="text"/> 
					<a class="btnLook" rel="parkingOrder_outDoor_select" title="选择出入口" href="${ctx}/work/passages/select?single=true&search_EQ_isEnabled=1" lookupGroup="outDoorGroup">查找带回</a> <span class="info">(选择出入口)</span>
					</label><span for="outDoorGroup_name" generated="true" style="display: none" class="error"></span></td>
						<td class="fieldName">出场时间:</td>
					<td class="fieldInput"><label><input type="text"
							id="outTime" name="outTime" class="date"
							value="<fmt:formatDate value='${vm.outTime}' pattern='yyyy-MM-dd HH:mm'/>"
							dateFmt="yyyy-MM-dd HH:mm" readonly="true"  /></label><span
						for="outTime" generated="true" style="display: none" class="error"></span>
					</td>
					</tr>
			 
				<tr>
					<td class="fieldName">车位图片查看:</td>
					<td class="fieldInput"></td>
				</tr>
				<tr>
					<td class="fieldName">视屏记录:</td>
					<td class="fieldInput"></td>
				</tr>
				<tr>
					<td class="fieldName">路径日志:</td>
					<td class="fieldInput"></td>
				</tr>

			</table>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">保存</button>
						</div>
					</div></li>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" class="close">取消</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>