var emails = [
	{ name: "Peter Pan", to: "上海上海" },
	{ name: "Molly", to: "广东广东" },
	{ name: "Forneria Marconi", to: "北京别具" },
	{ name: "Master <em>Sync</em>", to: "相应襄阳" },
	{ name: "Dr. <strong>Tech</strong> de Log", to: "武汉武汉" },
	{ name: "Don Corleone", to: "浦东普度" },
	{ name: "Mc Chick", to: "送检松江" },
	{ name: "Donnie Darko", to: "海南海宁" },
	{ name: "Quake The Net", to: "松花江" },
	{ name: "Dr. Write", to: "松赞干布" }
];
var testddd = [
	"上海上海" ,
	"广东广东"  
];



function autocompleteInit(ctx){
	$("#suggest1").autocomplete(ctx+"/sys/dict/typelist", {
		minChars: 0,
		width: 150,
		matchContains: true,
		autoFill: false,
		formatItem: function(row, i, max) {
			return i + "/" + max + ": \""+row["type"];
		},
		formatMatch: function(row, i, max) {
			return row["type"];
		},
		formatResult: function(row) {
			return row["type"];
		}
	});

	$("#suggest2").autocomplete(ctx+"/sys/dict/typealltest", {
		minChars: 0,
		width: 180,
		matchContains: true,	//允许对输入的值进行模糊匹配并过滤下拉框显示的数据，当设置为false每次输入都会从新查询数据库
		autoFill: false,
		max:100,				//设置下拉框显示的最大数据条数
		formatItem: function(row, i, max) {//设置下拉框中数据的显示格式
			return i + "/" + max + ": \"" + row["id"] + "\" [" + row["name"] + "]";
		},
		formatMatch: function(row, i, max) {//设置模糊匹配的值
			return row["id"]+ " " + row["name"];
		},
		formatResult: function(row) {//设置选择下拉框的返回值
			return row["name"];
		}
	});

	$("#suggest3").autocomplete(emails, {
		minChars: 0,
		width: 150,
		matchContains: true,
		autoFill: false,
		formatItem: formatItem,
		formatMatch: formatMatch,
		formatResult:formatResult 
	});
}
function formatItem(row, i, max) {
	return row[this.itemParam[0]];
}
function formatMatch(row, i, max) {
	return row[this.itemParam[0]];
}
function formatResult(row) {
	return row[this.itemParam[0]];
}

/**
 * /**
 *  
 * @param {} cacheElementId 缓存选中内容的dom位置
 * @param {} url  获取所有记录结果的url地址
 * @param {} groupIds 需要勾选的checkbox name
 * @param {} cacheName 需要想url地址中传递的缓存checkbox 使用名
 */
function pageSelectAll(cacheElementId,url,groupIds,cacheName){
 
	if (cacheElementId) {
		var $rel = $("#"+cacheElementId);
		$rel.loadUrl(url, {"cacheName":cacheName}, function(){
			var $checkboxLi = $rel.parents(".unitBox:first").find(":checkbox[name='"+groupIds+"']"); 
		    $checkboxLi.attr('checked', true);
		});
	}
}
function pageUnSelectAll(cacheElementId,groupIds){ 
	if (cacheElementId) {
		var $rel = $("#"+cacheElementId);
		$rel.empty();
		var $checkboxLi = $rel.parents(".unitBox:first").find(":checkbox[name='"+groupIds+"']"); 
		$checkboxLi.attr('checked', false);		 
	}
}

/**
 * 跨页选择参数的传递
 */
function pagePass(eventElement,cacheElementId,hiddenName){	 	
	var $cacheElement=$.pdialog.getCurrent().find("#"+cacheElementId);
	//多个节点
	if($(eventElement).length>1){
	    var _inputVal="";
	    var $nowVal=$cacheElement.find("input[type='checkbox'][name='"+hiddenName+"']");
	    $.each(eventElement, function(key, ee) {
           
		   $.each($nowVal, function(nkey, ne) {
		       if(ee.value==ne.value){
		          $(ne).remove();
		       }
		   });
		  if(ee.checked){
		    _inputVal+="<input type=\"checkbox\" checked=\"checked\" class=\"cachebox\" name=\""+hiddenName+"\" value=\""+ee.value+"\"/>";
		   
		  }   
		});
		$cacheElement.prepend(_inputVal);
	}else{
		//单个节点
		$cacheElement.find("input[type='checkbox'][name='"+hiddenName+"'][value='"+eventElement.value+"']").remove();
		if(eventElement.checked){
		 $cacheElement.prepend("<input type=\"checkbox\" checked=\"checked\" class=\"cachebox\" name=\""+hiddenName+"\" value=\""+eventElement.value+"\"/>");
		}
	}
}

/**
 * 扩展dialogAjaxDone方法，回调用户指定的方法
 * 服务器转回navTabId，可以重新载入指定的navTab. statusCode=DWZ.statusCode.ok表示操作成功, 自动关闭当前dialog
 * 
 * form提交后返回json数据结构,json格式和navTabAjaxDone一致
 */
function dialogAjaxDoneFun(json){
	DWZ.ajaxDone(json);
	if (json.statusCode == DWZ.statusCode.ok){
		if (json.navTabId){
			navTab.reload("", {navTabId: json.navTabId,data:undefined});
		}
//		else if (json.rel) {
//			var $pagerForm = $("#pagerForm", navTab.getCurrentPanel());
//			var args = $pagerForm.size()>0 ? $pagerForm.serializeArray() : {}
//			navTabPageBreak(args, json.rel);
//		}
		if ("" != json.callbackFun) {
			var callbackFuntion = eval(json.callbackFun);
			callbackFuntion(json);
		}
		
		if ("closeCurrent" == json.callbackType) {
			$.pdialog.closeCurrent();
		}
	}
}
/**
 * 刷新某个弹出窗体中的内容rel
 * @param {} form 查询表单
 * @param {} dialogRel 弹出窗ID
 * @return {Boolean}
 * 要更改为回调函数，参数只有一个 返回的 JSON 
 */
function dialogSearchReload(json){ 
	if (json.rel){
		var dialog = $("body").data(json.rel);
		if(dialog) {
		   var $form =dialog.find("#pagerForm"); 
		   if ($form[DWZ.pageInfo.pageNum]) $form[DWZ.pageInfo.pageNum].value = 1;
           $.pdialog.reload($form.attr('action'), {dialogId:json.rel,data: $form.serializeArray()});
 
		}
	}  
}



 
function upPrev(imgId) {
	var $img = $('#' + imgId);
	var _index = parseInt($img.attr('index'));
	if (_index > 0) {
		$img.attr('index', _index - 1);
		var imgurls = $img.parent().find("d[index='" + (_index - 1) + "']");
		$img.attr('src', imgurls.attr('v'));
	}
}
function upNext(imgId) {
	var $img = $('#' + imgId);
	var _index = parseInt($img.attr('index'));
	var imgurls = $img.parent().find("d");
	if ((_index + 1) < imgurls.length) {
		$img.attr('index', _index + 1);
		var imgurls = $img.parent().find("d[index='" + (_index + 1) + "']");
		$img.attr('src', imgurls.attr('v'));
	}
}


function paperQuestionReloadUrl(json){
    
		var dialog = $("body").data('work_paper_room_update');
		if(dialog) {		    
		   if (json.forwardUrl){		    
		   	 var op={"data":{"rel":json.rel,"forwardUrl":json.forwardUrl}};
             $.pdialog.open(json.forwardUrl,'work_paper_room_update','在线考试',op);
		   }
		}
	 
}

function paperQuestionTimeout(json){  
	if ( DWZ.statusCode.error == json.statusCode){		
		if(json.message && alertMsg) alertMsg.error(json.message);
		if ("closeCurrent" == json.callbackType) {
			var dialog = $("body").data('work_paper_room_update');
			if(dialog){$.pdialog.close(dialog)};
		}
	} 
}

function paperQuestionSubType(subType,sform,cpid){
    var $sform = $(sform)
	if(subType==0){
    	$sform.find('#subType').val(0);
    	$sform.find("input[name='callbackType']").val('');
    	if(!$sform.find("input[name='callbackFun']").length){
    	   $sform.append("<input type=\"hidden\" name=\"callbackFun\" value=\"\">");
    	}
    	$sform.find("input[name='callbackFun']").val('paperQuestionReloadUrl');
    } 
    
    if(subType==1){
         $sform.find('#subType').val(1);
         $sform.find('#answerCheck').remove();
    }
    
    if(subType==2){
        $sform.find('#subType').val(2);
    }
    
    paperQuestionTimeEndClear(sform,cpid);
}

function paperQuestionTimeEndClear(sform,cpid){  
	$("#"+cpid,sform).stop(); 
}
