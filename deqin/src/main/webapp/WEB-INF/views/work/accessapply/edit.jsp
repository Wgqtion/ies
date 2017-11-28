<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>

<div class="pageContent">
	<form method="post" action="${ctx}/work/accessapply/${action}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="id" value="${id}">
		<vsc:token tokenName="work.accessapply.create"></vsc:token>
		<vsc:callback></vsc:callback>
		<div class="pageFormContent" layoutH="56">
			<table class="formTable">
				<tr>
					<td class="fieldName">车辆编号:</td>
					<td class="fieldInput"><label> <input name="cardInfoGroup.id" value="${vm.cardInfo.id}" type="hidden" /> <input validate="{required:true}" readonly="readonly" id="cardInfoGroup_userNo" name="cardInfoGroup.userNo" value="${vm.cardInfo.userNo}" type="text" /> <a class="btnLook" rel="accessapply_cardinfo_select" title="选择车辆" href="${ctx}/work/cardinfo/select?single=true" lookupGroup="cardInfoGroup">查找带回</a> <span class="info">(选择车辆)</span>
					</label><span for="cardInfoGroup_userNo" generated="true" style="display: none" class="error"></span></td>

					<td class="fieldName">车牌号:</td>
					<td class="fieldInput"><label> <input type="text" readonly="readonly" id="cardInfoGroup_carNo" name="cardInfoGroup.carNo" value="${vm.cardInfo.carNo}" type="text" /></td>
				</tr>
				<tr>
					<td class="fieldName">出入口:</td>
					<td class="fieldInput"><label> <input name="passagesGroup.id" value="${vm.passages.id}" type="hidden" /> <input validate="{required:true}" readonly="readonly" id="passagesGroup_name" name="passagesGroup.name" value="${vm.passages.name}" type="text" /> <a class="btnLook" rel="accessApply_passages_select" title="选择出入口" href="${ctx}/work/passages/select?single=true&search_EQ_isEnabled=1" lookupGroup="passagesGroup">查找带回</a> <span class="info">(选择出入口)</span>
					</label><span for="passagesGroup_name" generated="true" style="display: none" class="error"></span></td>
				<td class="fieldName">出入状态:</td>
					<td class="fieldInput"><label><form:radiobutton path="vm.accessStatus" value="0" validate="{required:true}" />入</label> <label><form:radiobutton path="vm.accessStatus" value="1" />出</label> <span for="accessStatus" generated="true" class="error" style="display: none"></span></td>
					
				</tr>
				<tr>
					<td class="fieldName">申请时间:</td>
					<td class="fieldInput"  colspan="3"><label><input type="text" id="applyTime" name="applyTime" class="date" value="<fmt:formatDate value='${vm.applyTime}' pattern='yyyy-MM-dd HH:mm'/>" dateFmt="yyyy-MM-dd HH:mm" readonly="true" validate="{required:true}" /></label><span for="applyTime" generated="true" style="display: none" class="error"></span></td>
				</tr>

				<tr>
					<td class="fieldName">申请人:</td>
					<td class="fieldInput" colspan="3"><label> <input name="applyUserGroup.id" value="${vm.user.id}" type="hidden" /> <input validate="{required:true}"  readonly="readonly"  id="applyUserGroup_name" name="applyUserGroup.name" value="${vm.user.name}" type="text" /> <a class="btnLook" rel="accessapply_user_select" title="选择申请人" href="${ctx}/sys/user/select?single=true&search_EQ_isEnabled=1" lookupGroup="applyUserGroup">查找带回</a> <span class="info">(选择申请人)</span>
					</label><span for="applyUserGroup_name" generated="true" style="display: none" class="error"></span></td>
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