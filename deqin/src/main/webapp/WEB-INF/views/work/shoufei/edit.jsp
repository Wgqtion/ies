<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>

<div class="pageContent">
	<form method="post" action="${ctx}/work/shoufei/${action}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="id" value="${id}">
		<vsc:token tokenName="work.shoufei.create"></vsc:token>
		<vsc:callback></vsc:callback>
		<div class="pageFormContent" layoutH="56">
			<table class="formTable">
					<tr>
					<td class="fieldName"><span class="required">*</span>进校证类型:</td>
						<td class="fieldInput">
						<label>
						<input type="text" id="cardTypeGroup_name" name="cardTypeGroup.name" value="${vm.cardType.name}" validate="{required:true}"/>
						<a class="btnLook" rel="shoufei_cardType_select" title="进校证类型" href="${ctx}/work/cardtype/select?single=true" lookupGroup="cardTypeGroup">查找带回</a> <span class="info">(选择进校证类型)</span>
						</label><span for="cardTypeGroup_name" generated="true" style="display: none" class="error"></span>
						</td>
					</tr>
					<tr>
					<td class="fieldName"><span class="required">*</span>校门:</td>
						<td class="fieldInput">
						<label>
						<input type="text" id="parkingLotGroup_name" name="parkingLotGroup.name" value="${vm.parkingLot.name}" validate="{required:true}"/>
						<a class="btnLook" rel="shoufei_parkingLot_select" title="校门" href="${ctx}/work/parkinglot/select?single=true&search_EQ_isEnabled=1" lookupGroup="parkingLotGroup">查找带回</a> <span class="info">(选择校门)</span>
						</label>
						<span for="parkingLotGroup_name" generated="true" style="display: none" class="error">
						</span>
						</td>
					</tr>
					<tr>
					<td class="fieldName"><span class="required">*</span>白天免费停车(小时):</td>
						<td class="fieldInput"><label><input type="text" id="dayTime" name="dayTime" value="${vm.dayTime}" validate="{required:true}"/></label><span for="dayTime" generated="true" style="display: none" class="error"></span>
						</td>
					<td class="fieldName"><span class="required">*</span>晚上免费停车(小时):</td>
						<td class="fieldInput"><label><input type="text" id="nightTime" name="nightTime" value="${vm.nightTime}" validate="{required:true}"/></label><span for="nightTime" generated="true" style="display: none" class="error"></span>
						</td>
					</tr>
					<tr>
					<td class="fieldName"><span class="required">*</span>白天停车费(元/小时):</td>
						<td class="fieldInput"><label><input type="text" id="dayHourMoney" name="dayHourMoney" value="${vm.dayHourMoney}" validate="{required:true}"/></label><span for="dayHourMoney" generated="true" style="display: none" class="error"></span>
						</td>
					<td class="fieldName"><span class="required">*</span>晚上停车费(元/小时):</td>
						<td class="fieldInput"><label><input type="text" id="nightHourMoney" name="nightHourMoney" value="${vm.nightHourMoney}" validate="{required:true}"/></label><span for="nightHourMoney" generated="true" style="display: none" class="error"></span>
						</td>
					</tr>
					<tr>
					<td class="fieldName"><span class="required">*</span>白天封顶停车费:</td>
						<td class="fieldInput"><label><input type="text" id="dayMaxMoney" name="dayMaxMoney" value="${vm.dayMaxMoney}" validate="{required:true}"/></label><span for="dayMaxMoney" generated="true" style="display: none" class="error"></span>
						</td>
					<td class="fieldName"><span class="required">*</span>晚上封顶停车费:</td>
						<td class="fieldInput"><label><input type="text" id="nightMaxMoney" name="nightMaxMoney" value="${vm.nightMaxMoney}" validate="{required:true}"/></label><span for="nightMaxMoney" generated="true" style="display: none" class="error"></span>
						</td>
					</tr>
					<tr>
					<td class="fieldName"><span class="required">*</span>白天免费停车时长:</td>
						<td class="fieldInput"><label><input type="text" id="everydayMianfeiTime" name="everydayMianfeiTime" value="${vm.everydayMianfeiTime}" validate="{required:true}"/></label><span for="everydayMianfeiTime" generated="true" style="display: none" class="error"></span>
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