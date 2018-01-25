/**
 * 处理json对象中的引用
 * 使用方法：
 * 		json = new FashJson().deal(json);
 * @returns
 */
var FashJson = function(){
	this._rootobj;
};
FashJson.prototype.deal = function(json){
	var _this = this;
	_this._rootobj = json
	return _this._deal(json,json,json);
}
FashJson.prototype._deal = function(json,sobj,pobj){
	var _this = this;
	if(json == undefined){
		return json;
	}
	if(typeof(json) == "object"){
		return _this._dealObject(json,sobj,pobj);
	}else{//如果不是对象，直接返回
		return json;
	}
}
FashJson.prototype._dealObject = function(obj,sobj,pobj){
	var _this = this;
	if(obj["$ref"] != undefined){
		obj = _this._dealRefObject(obj,sobj,pobj);
	}else{
		for(var i in obj){
			if(i == ""){
				continue;
			}
			obj[i] = _this._deal(obj[i],obj,sobj);
		}
	}
	return obj;
}
FashJson.prototype._dealRefObject = function(refobj,sobj,pobj){
	var _this = this;
	var refstr = refobj["$ref"];
	if(refstr == ".."){
		return pobj;
	}else if(refstr == "@"){
		return sobj;
	}else if(refstr == "$"){
		return _this._rootobj;
	}else if(refstr.indexOf("$") == 0){
		var tempobj;
		eval("tempobj = _this._rootobj"+refstr.substring(1));
		return tempobj;
	}else{
		return refobj;
	}
}