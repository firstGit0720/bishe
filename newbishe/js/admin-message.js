$(function(){
	//获取当前登录人的信息
  	var username =sessionStorage.getItem("uesrMessage");
  	console.info(username)
  	var userMessage;
  	$.ajax({    
            url :"http://localhost:8089/otherWays/getUser",                               
            data : {
				"username" : username
			}, 
            type : 'get',    
            dataType : 'json',  
			async : false, 
			contentType :"application/json;charset=UTF-8", 
            success : function(result) {  
				console.info(result) 
				//复制
				$("#name").append(result.userName);
				$("#pname").append(result.userPname);
				if(result.userSex == 0){
					$("#sex").append('男');
				}else{
					$("#sex").append('女');
				}
				$("#age").append(result.userAge);
				
				if(result.userType == 0){
					$("#type").append("高级管理员");
				}else if (result.userType == 1){
					$("#type").append("普通管理员");
				}else if (result.userType == 2){
					$("#type").append("普通会员");
				}
				$("#phone").append(result.userPhone);
				$("#birthday").append(format(parseInt(result.userBirthday)));
				$("#card").append(result.userCard);
				$("#email").append(result.userEmail);
				$("#address").append(result.userAddress);
            },    
            error : function(msg) {    
					layer.msg('获取消息失败！',{icon:2,time:2000});
            }    
      });   
	
	function format(shijianchuo){
		//shijianchuo是整数，否则要parseInt转换
		var time = new Date(shijianchuo);
		var y = time.getFullYear();
		var m = time.getMonth()+1;
		var d = time.getDate();
		return y+'-'+add0(m)+'-'+add0(d);
	}
	function add0(m){return m<10?'0'+m:m }
 
})