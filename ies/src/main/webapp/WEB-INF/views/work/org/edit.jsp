<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>

<div class="pageContent">
	<form method="post" action="${ctx}/work/org/${action}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="id" value="${id}">
		<vsc:token tokenName="work.org.create"></vsc:token>
		<vsc:callback></vsc:callback>
		<div class="pageFormContent" layoutH="56">
			<table class="formTable" style="width: 50%;margin:auto">
			
				<tr>
					<td class="fieldName"><label>名称:</label></td>
					<td class="fieldInput"><label><input type="text" id="name" name="name" value="${vm.name}" validate="{required:true}" /></label><span for="name" generated="true" style="display: none" class="error"></span></td>
					
				</tr>
				<tr>
					<td class="fieldName"><label>权限码:</label></td>
					<td class="fieldInput"><label><input type="text" readonly="readonly" id="code" name="code" value="${vm.code}" validate="{required:true}" /></label><span for="code" generated="true" style="display: none" class="error"></span></td>
				</tr>
				<tr>
					<td>分配停车场：</td>
					<td>
						<input name="parkingLots.code" value="<vsc:fetchElementPropertyToString propertyName="code" list="${vm.parkingLots}"/>" type="hidden"/> 
						<a class="btnLook" rel="parkinglot_orgAuthority" title="选择授权" href="${ctx}/work/parkinglot/orgAuthority" <c:if test="${not empty vm.parkingLots}">data="{orgAuthorityCodes:[<c:forEach items="${vm.parkingLots}" var="item" varStatus="index">'{code:\'${item.code}\', name:\'${item.name}\'}'<c:if test="${!index.last}">,</c:if></c:forEach>]}"</c:if> lookupGroup="parkingLots">查找带回</a> <span class="info">(点击选择授权)</span>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<textarea class="readonly" rows="3" cols="40" readonly="readonly" name="parkingLots.name" ><vsc:fetchElementPropertyToString propertyName="name" list="${vm.parkingLots}" /></textarea>
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