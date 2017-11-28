<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>

<div class="pageContent">
	<form method="post" action="${ctx}/work/cardtype/${action}"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="id" value="${id}">
		<vsc:token tokenName="work.cardtype.create"></vsc:token>
		<vsc:callback></vsc:callback>
		<div class="pageFormContent" layoutH="56">
			<table class="formTable">
				<tr>
					<td class="fieldName"><span class="required">*</span>名称:</td>
					<td class="fieldInput"><label><input type="text"
							id="name" name="name" value="${vm.name}"
							validate="{required:true}" /></label><span for="name" generated="true"
						style="display: none" class="error"></span></td>
					<td class="fieldName"><span class="required">*</span>首字母编号:</td>
					<td class="fieldInput"><label><input type="text"
							id="cardNumber" name="cardNumber" value="${vm.cardNumber}"
							validate="{required:true}" /></label><span for="cardNumber"
						generated="true" style="display: none" class="error"></span></td>
				</tr>
				<tr>
					<td class="fieldName"><span class="required">*</span>适用年份:</td>
					<td class="fieldInput"><label><input type="text"
							id="year" name="year" value="${vm.year}"
							validate="{required:true}" /></label><span for="year" generated="true"
						style="display: none" class="error"></span></td>
					<td class="fieldName"><span class="required">*</span>年费标准(单位元):</td>
					<td class="fieldInput"><label><input type="text"
							id="fee" name="fee" value="${vm.fee}" validate="{required:true}" /></label><span
						for="fee" generated="true" style="display: none" class="error"></span>
					</td>
				</tr>
				<tr>
					<td class="fieldName"><span class="required">*</span>补办收费倍数:</td>
					<td class="fieldInput"><label><input type="text"
							id="buban" name="buban" value="${vm.buban}"
							validate="{required:true}" /></label><span for="buban" generated="true"
						style="display: none" class="error"></span></td>
					<td class="fieldName"><span class="required">*</span>变更收费倍数:</td>
					<td class="fieldInput"><label><input type="text"
							id="biangeng" name="biangeng" value="${vm.biangeng}"
							validate="{required:true}" /></label><span for="biangeng"
						generated="true" style="display: none" class="error"></span></td>
				</tr>
				<tr>
					<td class="fieldName"><span class="required">*</span>证件时效：</td>
					<td class="fieldInput" width="35%"><label> <form:radiobutton
								path="vm.cardType" value="1" validate="{required:true}" /> <s:message
								code="cardtype.cardtype.1" />
					</label> <label> <form:radiobutton path="vm.cardType" value="2" />
							<s:message code="cardtype.cardtype.2" />
					</label> <span style="display: none" class="error" generated="true"
						for="cardType"></span></td>

					<td class="fieldName"><span class="required">*</span>进校证人群：</td>
					<td class="fieldInput" width="35%"><label> <form:radiobutton
								path="vm.cmod" value="1" validate="{required:true}" /> <s:message
								code="cardtype.cmod.1" />
					</label> <label> <form:radiobutton path="vm.cmod" value="2" /> <s:message
								code="cardtype.cmod.2" />
					</label> <span style="display: none" class="error" generated="true"
						for="cmod"></span></td>
				</tr>
				<tr>
					<td class="fieldName"><span class="required">*</span>关系证明：</td>
					<td class="fieldInput"><label><form:radiobutton
								path="vm.needRel" value="true" validate="{required:true}" /> <s:message
								code="cardtype.needRel.1" /></label> <label><form:radiobutton
								path="vm.needRel" value="false" /> <s:message
								code="cardtype.needRel.0" /></label> <span for="needRel"
						generated="true" class="error" style="display: none"></span></td>
					<td class="fieldName"><span class="required">*</span>默认类型：</td>
					<td class="fieldInput"><label><form:radiobutton
								path="vm.status" value="true" validate="{required:true}" /> <s:message
								code="user.isenabled.true" /></label> <label><form:radiobutton
								path="vm.status" value="false" /> <s:message
								code="user.isenabled.false" /></label> <span for="status"
						generated="true" class="error" style="display: none"></span></td>
				</tr>
				<tr>
					<td class="fieldName"><span class="required">*</span>校门:</td>
					<td class="fieldInput"><input name="campusGroup.id"
						value="<vsc:fetchElementPropertyToString propertyName="id" list="${vm.campusList}"/>"
						type="hidden" /> <a class="btnLook" rel="cardtype_campus_select"
						title="校门"
						href="${ctx}/work/campus/select?single=true&search_EQ_isEnabled=1"
						<c:if test="${not empty vm.campusList}">data="{selectCampusIds:[<c:forEach items="${vm.campusList}" var="item" varStatus="index">'{id:\'${item.id}\', name:\'${item.name}\'}'<c:if test="${!index.last}">,</c:if></c:forEach>]}"</c:if>
							lookupGroup="campusGroup">查找带回</a> <span class="info">(点击选择校门)</span>
					</td>
				</tr>
				<tr>
					<td></td>
					<td><textarea class="readonly" readonly="readonly"
							name="campusGroup.name">
							<vsc:fetchElementPropertyToString propertyName="name"
								list="${vm.campusList}" />
						</textarea></td>
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