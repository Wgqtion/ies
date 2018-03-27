<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/include.inc.jsp"%>

	
<SCRIPT type="text/javascript">
	var setting = {
		check: {
			enable: true
		},
		data: {
			simpleData: {
				enable: true
			}
		}
	};

	var zNodes =[
	   <c:forEach items="${list}" var="entity" varStatus="index">
	   		{ id:'${entity.code}', pId:'${entity.parentCode}', name:'${entity.name}'<c:forEach items="${authoritys}" var="authority"><c:if test="${authority.id eq entity.id}">,checked:true</c:if></c:forEach>}<c:if test="${!index.last}">,</c:if>
	   </c:forEach>
	];
	
	$(document).ready(function(){
		$.fn.zTree.init($("#authorityTree"), setting, zNodes);
	});
	//保存
	function save(){
		var treeObj=$.fn.zTree.getZTreeObj("authorityTree"),  
        nodes=treeObj.getCheckedNodes(true),  
        codes="";  
        for(var i=0;i<nodes.length;i++){  
        	codes+=nodes[i].id + ",";
        }
        var roleId='${roleId}';
        $.ajax({
       	  type: 'POST',
       	  url: "${ctx}/sys/authority/save",
       	  data: {"roleId":roleId,"codes":codes},
     	  success : function(result) {
     		  alert(result.message);
	       	  if (result.statusCode == "200") {
	       	  	  $("#cancel").click();
	       	  }
    	  },
    	  error:function(msg){
        	 alert(msg);
      	  },
      	dataType:"json"
       	});
	}
</SCRIPT>
<div class="content_wrap">
	<div class="zTreeDemoBackground">
		<ul id="authorityTree" class="ztree"></ul>
	</div>
	<div class="buttonActive" style="margin-left: 20px;">
		<div class="buttonContent">
			<button type="button" onclick="save();">保存</button>
			<button id="cancel" type="button" style="display: none;" class="close">取消</button>
		</div>
	</div>
</div>
