<#include "/macro.include"/>
<#include "/custom.include"/>  
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
<#assign classNameFullLower = className?lower_case>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>

<div class="pageContent">
	<form method="post" action="<@jspEl 'ctx'/>/${funcname}/${classNameFullLower}/<@jspEl 'action'/>" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="id" value="<@jspEl 'id'/>">
		<vsc:token tokenName="${funcname}.${classNameFullLower}.create"></vsc:token>
		<vsc:callback></vsc:callback>
		<div class="pageFormContent" layoutH="56">
			<table class="formTable">
			<#list table.columns as column>
				<#if !column.htmlHidden>
					<tr>
					<td class="fieldName">${column.constantName}:</td>
						<td class="fieldInput"><label><#rt>
						<#compress>
							<#if column.isDateTimeColumn> 
							  <input type="text" id="${column.columnNameLower}" name="${column.columnNameLower}" class="date" value="<fmt:formatDate value='<@jspEl "vm."+column.columnNameLower />' pattern='yyyy-MM-dd'/>" dateFmt="yyyy-MM-dd" readonly="true" validate="{required:true}"/>
							  
							<#else>					
							  <input type="text" id="${column.columnNameLower}" name="${column.columnNameLower}"  value="<@jspEl 'vm.'+column.columnNameLower/>" validate="{required:true}"/> 
							</#if>
						</#compress>
						<#lt></label><span for="${column.columnNameLower}" generated="true" style="display: none" class="error"></span>
						</td>
					</tr>
				</#if>
			</#list>
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