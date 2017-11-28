<#include "/macro.include"/>
<#include "/custom.include"/>  
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
<#assign classNameFullLower = className?lower_case>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>
<div class="pageContent">

	<div class="pageFormContent" layoutH="56"> 
		<table class="viewTable">
			<tbody>
				<#list table.columns as column>
				<#if !column.htmlHidden>
				<tr>
					<td width="10%" align="right">${column.constantName}:</td>
					<td align="left">
						<#rt>
						<#compress>
							<#if column.isDateTimeColumn>
		                      <fmt:formatDate value='<@jspEl "vm."+column.columnNameLower />' pattern='yyyy-MM-dd HH:mm:ss'/> 
							<#else>					
								<@jspEl 'vm.'+column.columnNameLower/> 
							</#if>
						</#compress>
						<#lt>
					</td>
				</tr>
				</#if>
				</#list>
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