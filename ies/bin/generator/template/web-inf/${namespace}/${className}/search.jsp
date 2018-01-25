<#include "/macro.include"/>
<#include "/custom.include"/>  
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
<#assign classNameFullLower = className?lower_case>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>

<div class="pageContent">
	<form method="post" action="<@jspEl 'ctx'/>/${funcname}/${classNameFullLower}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="58">
			<div class="unit">
				<label>请输入检索条件：</label>
				<input type="text" size="25" name="search_LIKE_fieldName_OR_LIKE_fieldName2"/>
			</div>
			<div class="divider">divider</div>
			
			<div class="unit">
				<label>simpleInput:</label>
				<input type="text" size="25" name="search_LIKE_fieldName" />
				<span class="inputInfo">LIKE关键字或全称</span>
			</div>
			<div class="unit">
				<label>DateInput:</label>
				<input type="text" class="date" size="11" dateFmt="yyyy-MM-dd" name="search_GTE_fieldName" readonly="true"/> 
				<input type="text" class="date" size="11" dateFmt="yyyy-MM-dd" name="search_LTE_fieldName" readonly="true" style="margin-left:5px;"/>
				<span class="inputInfo">关键字或全称</span>
			</div>
			<div class="unit">
				<label>Radio：</label>
				<label class="radioButton"><input name="search_EQ_fieldName" type="radio" value="1"/>企业</label>
				<label class="radioButton"><input name="search_EQ_fieldName" type="radio" value="2"/>商户</label>
				<label class="radioButton"><input name="search_EQ_fieldName" type="radio" value="3"/>农户</label>
				<label class="radioButton"><input name="search_EQ_fieldName" type="radio" value="4"/>个人</label>
			</div>
			<div class="unit">
				<label>CheckBox：</label>
				<label><input type="checkbox" name="search_EQ_fieldName" value="1" />选择1</label>
				<label><input type="checkbox" name="search_EQ_fieldName" value="2" />选择2</label>
				<label><input type="checkbox" name="search_EQ_fieldName" value="3" />选择3</label>
				<label><input type="checkbox" name="search_EQ_fieldName" value="4" />选择4</label>
			</div>
			<div class="unit">
				<label>SelectInput：</label>
				<input name="search_LIKE_fieldName" id="MyObj.fieldName" type="text" postField="keyword" suggestFields="fileName" 
					suggestUrl="<@jspEl 'ctx'/>/${funcname}/${classNameFullLower}/funcList" lookupGroup="MyObj"/>
				<span class="inputInfo">带提示的文本框</span>
			</div>
			<div class="unit">
				<label>Select：</label>
				<select class="combox" name="search_EQ_fieldName" id="fieldName" dataUrl="<@jspEl 'ctx'/>/${funcname}/${classNameFullLower}/selectList">
					<vsc:headoption></vsc:headoption>
				</select>
			</div>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">开始检索</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="reset">清空重输</button></div></div></li>
			</ul>
		</div>
	</form>
</div>