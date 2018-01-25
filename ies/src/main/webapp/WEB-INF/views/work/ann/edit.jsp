<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<div class="pageContent">
	<form method="post" action="${ctx}/work/ann/${action}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="id" value="${id}">
		<vsc:token tokenName="work.ann.create"></vsc:token>
		<vsc:callback></vsc:callback>
		<div class="pageFormContent" layoutH="56">
			<dl>
				<dt>
					<label>标题:</label>
				</dt>
				<dd>
					<input type="text" id="title" name="title" class="required" size="30" value="${vm.title}" validate="{required:true}" />
				</dd>
			</dl>
			<dl class="nowrap">
				<dt>
					<label>详细内容:</label>
				</dt>
				<dd><textarea validate="{required:true}" class="required" rows="5" cols="60"  name="context">${vm.context}</textarea>
			 				
				</dd>
			</dl>  
			<dl class="nowrap">
				<dt>
					<label>发送所有人:</label>
				</dt>
				<dd><form:checkbox path="vm.toAllUser" value="true" />			 
				</dd>
			</dl>
			<dl class="nowrap">
				<dt>
					<label>发送人员:</label>
				</dt>
				<dd>
					<input name="user.id" value="<vsc:fetchElementPropertyToString propertyName="sendUser.id" list="${vm.sends}"/>" type="hidden" > 
					<a class="btnLook" rel="work_ann_user_select" title="发送人员" href="${ctx}/work/minenurse/select" lookupGroup="user">查找带回</a> <span class="info">(点击选择发送人员)</span>					 	
				 
				</dd>
			</dl>
			<dl class="nowrap">
				<dt>&nbsp;</dt>
				<dd>
					<textarea class="readonly" rows="3" cols="40"  readonly="readonly" name="user.name"><vsc:fetchElementPropertyToString propertyName="sendUser.name" list="${vm.sends}" /></textarea>
				</dd>
			</dl> 
			<dl class="nowrap">
				<dt>
					<label>状态:</label>
				</dt>
				<dd>					
				   <label><form:radiobutton path="vm.state" value="0" validate="{required:true}" /> <s:message code="ann.state.0" /></label> 
				   <label><form:radiobutton	path="vm.state" value="1" /> <s:message code="ann.state.1" /></label>  
				   <span for="state" generated="true" class="error"	style="display: none"></span>
				</dd>
			</dl>
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