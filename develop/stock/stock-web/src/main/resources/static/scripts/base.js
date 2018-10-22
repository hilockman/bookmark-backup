var AjaxFunction =  function (url, callBack, failCallBack) {
     var result;
     
	 jQuery.ajax({
		 dataType:'jsonp',
	        url: this.baseUrl+url,
	        success: callBack != undefined ? callBack : function(record) { result = record; },
	        async: callBack == undefined ? false : true,
	        error: failCallBack	== undefined ? function(e){console.log(e);} : failCallBack		        
	    });
	 
	 
	 if (callBack == undefined) {
		 return result;
	 }
};	

function StockFunction() {
	this.baseUrl = 'http://localhost:5001/stock/';
	this.getBasics = function(callBack, failCallBack) { return this.query('basics', callBack, failCallBack);};
	this.histData =  function(stock_id, callBack, failCallBack) { return this.query('histData/'+stock_id, callBack, failCallBack);};
}  

StockFunction.prototype.query =  AjaxFunction;

stockApi = new StockFunction();