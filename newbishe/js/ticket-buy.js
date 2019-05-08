$(function(){
	
	var usermessage =  GetQueryString("userMessage");
	var trainSeat = GetQueryString("result");
	console.info(usermessage);
	console.info(trainSeat);
	
	
	
	
	
	
	
	 function GetQueryString(name){
	    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	    var r = window.location.search.substr(1).match(reg);
	    if(r!=null)return  unescape(r[2]); return null;
	}
})