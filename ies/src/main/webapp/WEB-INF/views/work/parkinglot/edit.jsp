<%@page import="org.apache.shiro.SecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>

<SCRIPT type="text/javascript">
	//选择树
	var setting = {
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			onClick: onClick	//点击时触发
		}
	};
	var zNodes =[
   	   <c:forEach items="${parkingLotTree}" var="entity" varStatus="index">
   	   		{ id:'${entity.id}', pId:'${entity.parent.id}', name:'${entity.name}'}<c:if test="${!index.last}">,</c:if>
   	   </c:forEach>
   	];
   	
   	$(document).ready(function(){
   		$.fn.zTree.init($("#selectParkingLotTree"), setting, zNodes);
   		var zTree = $.fn.zTree.getZTreeObj("selectParkingLotTree");
   		var node = zTree.getNodeByParam("id", "${parent.id}");
   		zTree.selectNode(node);
   	});
	
	//显示树菜单
	function showMenu() {
		var cityObj = $("#parentName");
		var cityOffset = $("#parentName").position();
		$("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
		
		$(".pageContent").bind("mousedown", onBodyDown);	//body绑定鼠标按下事件
	}
	
	//隐藏树菜单
	function hideMenu() {
		$("#menuContent").fadeOut("fast");	//隐藏树菜单
		$(".pageContent").unbind("mousedown", onBodyDown);	//body解除鼠标按下事件
	}
	
	//body绑定鼠标按下事件
	function onBodyDown(event) {
		if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
			hideMenu();
		}
	}
	
	//点击时触发
	function onClick(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("selectParkingLotTree"),
		nodes = zTree.getSelectedNodes();
		$("#parentId").val(nodes[0].id);	//
		$("#parentName").val(nodes[0].name);
		$("#menuContent").fadeOut("fast");	//隐藏树菜单
		
		
		companyHide();
	}
	//清空树
	function clearBtn(){
		$("#parentId").val('');
		$("#parentName").val('');
		$("#companyTr").show();
	}
	
	$(function(){
		companyHide();
	});
	//隐藏公司
	function companyHide(){
		var parentId=$("#parentId").val();
		if(parentId!=''){
			$("#companyTr").hide();
		}
	}
	
</SCRIPT>
<style>
ul.editZtree{
    background: none repeat scroll 0 0 #f0f6e4;
    border: 1px solid #617775;
    height: 200px;
    margin-top: 10px;
    overflow-x: auto;
    overflow-y: scroll;
    width: 300px;
}
</style>
<div class="pageContent">
	<form method="post" action="${ctx}/work/parkinglot/${action}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" id="id" name="id" value="${id}">
		<vsc:token tokenName="work.parkinglot.create"></vsc:token>
		<vsc:callback></vsc:callback>
		<div class="pageFormContent" layoutH="56">
			<table class="formTable"> 
				<tr>
					<td class="fieldName"><span class="required">*</span>场区名称:</td>
					<td class="fieldInput"><label><input type="text" id="name" name="name" value="${vm.name}" validate="{required:true}" /></label><span for="name" generated="true" style="display: none" class="error"></span></td>
					<td rowspan="6"  class="fieldName" valign="top">场区图片:</td>
					<td rowspan="6"  class="fieldInput">
					<vsc:picUpload  height="180"  picPath="${vm.photoAttach.downloadPath}"  uploadifyFileId="uploadifyFileIdFileShare"  hiddenValue="${vm.photoAttach.id}"   hiddenName="photoAttachId" width="320"></vsc:picUpload>
					<span style="display: none" class="error" generated="true" for="uploadifyFileIdFileShare_hiddenId"></span>
					</td>
				</tr> 
				<tr>
					<td class="fieldName">上级场区:</td>
					<td class="fieldInput">
						<input type="hidden" name="parent.id" id="parentId" value="${parent.id}">
						<input  id="parentName" value="${parent.name }" readonly="readonly"/>
						<c:if test="${empty id}">
						<a class="btnLook" title="选择上级场区" href="#" onclick="showMenu();"></a>
						<span class="info">选择</span>
						<input id="claerBtn" type='button' style="margin-left: 5px;" value='清空' onclick='clearBtn();' />
						</c:if>
					</td>
				</tr> 
				<c:if test="${empty id}">
				<tr id="companyTr">
					<td class="fieldName"><span class="required">*</span>所属公司:</td>
					<td class="fieldInput">
						<select id="companyCode" name="companyCode" validate="{required:true}">
							<option value="">全部</option>
							<c:forEach items="${companyList}" var="company">
								<option value="${company.code}" <c:if test="${empty vm.companyCode }"><c:if test="${currentUser.company.code eq company.code }">selected='selected'</c:if></c:if><c:if test="${company.code eq vm.companyCode }">selected='selected'</c:if>>${company.name}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				</c:if>
				<tr>
					<td class="fieldName"><span class="required">*</span>纬度坐标:</td>
					<td class="fieldInput"><label><input type="text" id="itudeLong" name="itudeLong" value="${vm.itudeLong}" validate="{required:true}" /></label><span for="baiduLatitudeLng" generated="true"
						style="display: none" class="error"></span></td> 
				</tr> 
				<tr>
					<td class="fieldName"><span class="required">*</span>经度坐标:</td>
					<td class="fieldInput"><label><input type="text" id="itudeLat" name="itudeLat" value="${vm.itudeLat}" validate="{required:true}" /></label><span for="baiduLatitudeLat" generated="true"
						style="display: none" class="error"></span></td>
				</tr> 
				<tr>
					<td class="fieldName"><span class="required">*</span>状态:</td>
					<td class="fieldInput"><label> <form:radiobutton path="vm.isEnabled" value="false" validate="{required:true}" /> <s:message code="parkinglot.isenabled.false" />
					</label> <label> <form:radiobutton path="vm.isEnabled" value="true" /> <s:message code="parkinglot.isenabled.true" />
					</label> <span style="display: none" class="error" generated="true" for="isEnabled"></span></td>
				</tr>
				<tr>
					<td class="fieldName">地址:</td>
					<td class="fieldInput" colspan="3"><label><input type="text" size="50" id="address" name="address" value="${vm.address}" validate="{required:false}" /></label><span for="address" generated="true" style="display: none" class="error"></span></td>
				</tr>
				<tr>
					<td class="fieldName" valign="top">说明:</td>
					<td class="fieldInput" colspan="3"><label>
					<textarea id="description" name="description"  rows="5" cols="80">${vm.description}</textarea>
					</label><span for="description" generated="true" style="display: none" class="error"></span>
					</td>
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
<div id="menuContent" class="menuContent" style="display: none;position: absolute;">
   <ul id="selectParkingLotTree" class="ztree editZtree" style="margin-top:0; width:200px;"></ul>
</div>


