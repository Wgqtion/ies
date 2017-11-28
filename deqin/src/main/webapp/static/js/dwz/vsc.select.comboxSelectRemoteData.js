/*
 * Plugin developed by: 付翀 使用远程数据填充select 下的 option 从dataUrl属性获取数据，并且转变为option加入
 * select中 数据集合 参照 [ ["all", "所有城市"], ["sh", "上海市",true] 数值意义:["value",
 * "text",selected] ] selectedValue 设置默认选中的值
 * 
 * 
 * 
 * 
 * <select class="combox" name="province" selectedValue="sh" selectedIndex="0"
 * dataUrl="/data.json"> </select>
 */

(function($) {
	$.fn.comboxSelectRemoteData = function(options) {
		var defaults = {
			selectedValue : null,
			selectedIndex : 0,
			dataUrl : null
		};
		var options = $.extend(defaults, options);
		return this.each(function(i) {
			var $this = $(this)
			var selectedValue = $this.attr("selectedValue") || ""; 
			var dataUrl = $this.attr("dataUrl");
			if (dataUrl) {
				$.ajax({
					type : 'POST',dataType : "json",url : dataUrl,
					cache : false,data : {},async:false,
					success : function(json) {
						if (!json)return;
						var html = '';

						$.each(json, function(i) {
							if (json[i] && json[i].length > 1) {
								var _selected = json[i][2]? true: (selectedValue == json[i][0]);
								html += '<option value="'+ json[i][0]+ '" '
										+ (_selected? 'selected="selected"': '') + '>'
										+ json[i][1] + '</option>';
							}
						});
						 
						$this.append(html);  
					},
					error : DWZ.ajaxError
				});
			}
 			

		});
	}
	
	$.fn.comboxRadioRemoteData = function(options) {
		var defaults = {
			selectedValue : null,
			selectedIndex : 0,
			dataUrl : null
		};
		var options = $.extend(defaults, options);
		return this.each(function(i) {
			var $this = $(this)
			var selectedValue = $this.attr("selectedValue") || ""; 
			var dataUrl = $this.attr("dataUrl");
			if (dataUrl) {
				$.ajax({
					type : 'POST',dataType : "json",url : dataUrl,
					cache : false,data : {},async:true,
					success : function(json) {
						if (!json)return;
						var html = '';

						$.each(json, function(i) {
							if (json[i] && json[i].length > 1) {
								var _selected = json[i][2]? true: (selectedValue == json[i][0]);
								html += '<label><input type="radio" class="'+$this.attr("class")+'" name="'+$this.attr("name")+'" value="'+ json[i][0]+ '" '
										+ (_selected? 'checked="checked"': '')  
										 + ' /> '+ json[i][1]+'</label>';
								if(_selected){$this.attr("checked",false);}		
							}
						});
						 
						$(html).insertAfter($this.parent());
						
					},
					error : DWZ.ajaxError
				});
			}
 			

		});
	}
	//手动触发表单项验证 
	$.fn.changeValidate = function(options) {
		var defaults = { 
		};
		var options = $.extend(defaults, options);
		return this.each(function(i) {
			 var $this = $(this)
			 $this.bind("changeValidate",function(){ 
				var form = $(this).parents("form:first");
				if(form && form.length > 0){
					$(form).validate().element(this);
				}
			}); 

		});
	}
	

   //自动匹配的可输入下拉菜单 
	$.fn.autocompleteCreate = function() {	 
		
		return this.each(function(i) {
			 var $this = $(this),
			   url=$this.attr("dataUrl"),
			   options=$this.attr("options");
			   
			   if(url.startsWith("javascript:")){
					url=url.replace("javascript:",'');
					url=eval(url); 
			   }
			   options=DWZ.jsonEval(options); 
			   var defaults = { 
				    matchContains: true,
		            minChars: 0,
				   	fully:true 
				   	/*,
				   	dataType:"json",
				   	parse : function(data){
						var parsed = [];
						var rows = data;
						rows = DWZ.jsonEval(data);
						var options = this;
						options.formatResult = options.formatResult || options.formatItem;
						for (var row in rows) {
							if (rows[row]) {
								parsed[parsed.length] = {
									data: rows[row],
									value: options.formatMatch && options.formatMatch(rows[row],rows[row],rows.length) || rows[row],
									result: options.formatResult && options.formatResult(rows[row]) || rows[row]
								};
							}
						}
						return parsed;
				   	}*/
				};
			   options = $.extend(defaults, options);
			   if(options.fully){
			   	  $.ajax({
							type: 'GET',
							url: url, 
							cache: false,
							success: function(response){
								var json = DWZ.jsonEval(response); 
								$this.autocomplete(json,options);
							}  
					});
			   }else{ 
			  	$this.autocomplete(url,options);
			   }
		});
	}	
    
	//Gerd总分计算 
	$.fn.autoGerdqScores = function() {	 
		
		return this.each(function(i) {
			 var $this = $(this),
			     _sourceId=$this.attr("sourceId");
			$("input:radio",$this).click(function(){
				var _total=0;
			    $('input:radio:checked',$this).each(function(){
			       var _score=$(this).parent().attr("score")*1;
			       _total+=_score;
			    });
			    $("#"+_sourceId).val(_total);
			});   
			  
		});
	}
	
	//药品选中
	$.fn.autoDrugCheckbox = function() {	 
		
		return this.each(function(i) {
			 var $this = $(this),
			 _source=$this.parent().find("input.isDrugUseDrugId");
			 $this.click(function(){
				if($this.is(":checked")){
				   _source.val($this.val());
				 }else{
				 	_source.val('');
				 } 
			}); 
		});
	} 
	
})(jQuery);