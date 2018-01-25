<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>

<div class="pageContent">
	<form method="post" action="${ctx}/work/yuding/${action}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="id" value="${id}">
		<vsc:token tokenName="work.yuding.create"></vsc:token>
		<vsc:callback></vsc:callback>
		<div class="pageFormContent" layoutH="56">
			<table class="formTable">
				<tr>
					<td class="fieldName">会员姓名:</td>
					<td class="fieldInput"><input name="yudingUserGroup.id" value="${vm.user.id}" type="hidden" /> <label> <input type="text" id="yuding_user_name" name="yudingUserGroup.name" value="${vm.user.name}" /> <a class="btnLook"
							rel="yuding_user_select" title="选择会员" href="${ctx}/work/member/select?single=true" lookupGroup="yudingUserGroup">查找带回</a> <span class="info">(选择)</span>
					</label><span for="yuding_user_name" generated="true" style="display: none" class="error"></span></td>

				</tr>
				<tr>
					<td class="fieldName">车牌号:</td>
					<td class="fieldInput"><label><input type="text" name="carNo" value="${vm.carNo}" validate="{required:true}" /></label><span for="carNo" generated="true" style="display: none" class="error"></span></td>
				</tr>
				<tr>
					<td class="fieldName">预约片区:</td>
					<td class="fieldInput"><input id="yuding_parkinglotarea_id" validate="{required:true,remoteAsync:'${ctx}/work/yuding/checkParkingLotAreaIsMinNode',messages:{remoteAsync:'必须选择末端的(无子节点)区域'}}" name="parkingLotAreaGroup.id" value="${vm.parkingLotArea.id}" type="hidden" /> <label> 
					<input type="text" class="readonly" id="yuding_parkinglotarea_name" name="parkingLotAreaGroup.name"
							value="${vm.parkingLotArea.name}"  /> <a class="btnLook" rel="yuding_parkinglotarea_select" title="选择停车区" href="${ctx}/work/parkinglotarea/select" lookupGroup="parkingLotAreaGroup">查找带回</a> <span class="info">(选择)</span>
					</label><span for="yuding_parkinglotarea_id" generated="true" style="display: none" class="error"></span></td>
				</tr>
				<tr>
					<td class="fieldName">预约时间:</td>
					<td class="fieldInput"><label><input type="text" id="yuyueTime" name="yuyueTime" class="date" value="<fmt:formatDate value='${vm.yuyueTime}' pattern='yyyy-MM-dd HH:mm'/>" dateFmt="yyyy-MM-dd HH:mm" readonly="true"
							validate="{required:true}" /></label><span for="yuyueTime" generated="true" style="display: none" class="error"></span></td>
					<td class="fieldName">提前保留:</td>
					<td class="fieldInput"><label><input type="text" id="lockedMinutes" name="lockedMinutes" value="${vm.lockedMinutes}" validate="{required:true}" />(分钟)</label><span for="lockedMinutes" generated="true" style="display: none"
						class="error"></span></td>
				</tr>
				<tr>
					<td class="fieldName">保留费用:</td>
					<td class="fieldInput"><label><input type="text" id="lockedCost" name="lockedCost" value="${vm.lockedCost}" validate="{required:true}" />(元)</label><span for="lockedCost" generated="true" style="display: none" class="error"></span></td>
					<td class="fieldName">使用计费:</td>
					<td class="fieldInput"><label><input type="text" id="lockedHourCost" name="lockedHourCost" value="${vm.lockedHourCost}" validate="{required:true}" />(元/小时)</label><span for="lockedHourCost" generated="true" style="display: none"
						class="error"></span></td>
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