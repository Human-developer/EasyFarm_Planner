/**
 * ajax 공통모듈
 */

var ksmartAjax = function(){
	
	var inputData = {};
	var resultObject = null;
	
	this.ajaxInfo = {
			url: null,
		    type: null,
		    data: null,
    		dataType: null,
    		resultObject : null
	};
	this.setAjaxUrl = function(url){
		this.ajaxInfo.url = url;
	};
	this.setAjaxType = function(type){
		this.ajaxInfo.type = type;
	};
	this.put = function(key,value){
		inputData[key] = value;
		this.ajaxInfo.data = inputData;
	};
	this.putObj = function(value){
		this.ajaxInfo.data = value;
	};
	this.setResultType = function(type){
		this.ajaxInfo.dataType = type;
	};
	this.clear = function(){
		this.ajaxInfo.url =null;
		this.ajaxInfo.type = null;
		this.ajaxInfo.data = null;		
		this.ajaxInfo.dataType = null;
	};
	this.setResultObject = function(object){
		resultObject = object;
	};	
	this.getResultObject = function(){	
		return resultObject;	
	};	
}
ksmartAjax.prototype.action = function(fn){
		
	var getAjaxInfo = this.ajaxInfo;
	var ajaxFuntion = new ksmart_ajaxFuntion();
	
	if(fn != null && typeof fn === "function"){
		$.ajax({
	        url     : getAjaxInfo.url,
	        type    : getAjaxInfo.type,
	        data    : getAjaxInfo.data,
	        async	: true,
	        dataType: getAjaxInfo.resultType,
	        success : fn,
	        error : function(xhr,status,error) {
	        	console.log("xhr: " + xhr);
	        	console.log("status: " + status);
	        	console.log("error: " + error);
	        }
	    });
	}else{
				
		ajaxFuntion.ajaxAsyncFalseAction(getAjaxInfo.url, getAjaxInfo.type, getAjaxInfo.data, getAjaxInfo.resultType, this.setResultObject);
		
	}
}
var ksmart_ajaxFuntion = function(){};

ksmart_ajaxFuntion.prototype.ajaxAsyncFalseAction = function(getUrl, getType, getData, getResultType, callback){
	
	$.ajax({
        url     : getUrl,
        type    : getType,
        data    : getData,
        dataType: getResultType,
        success : function(data) {
        	var resultData = JSON.stringify(data);
        	resultData = resultData.replace(/[<][^>]*[>]/gi, "");
            callback(JSON.parse(resultData));
        },
        error : function(xhr,status,error) {
        	console.log("xhr: " + xhr);
        	console.log("status: " + status);
        	console.log("error: " + error);
        }
    });
	
}