$(function(){
	//获取当前用户名进行密码的修改
	var username =sessionStorage.getItem("uesrMessage");
	
	$("#updatePassword").on("click",function(){
		var oldPwd = $("#oldPassword").val();
		var newPwd = $("#newPassword").val();
		var repPwd = $("#newPassword1").val();
		if(repPwd == newPwd){
			$.ajax({
				type:"post",
				url:"http://localhost:8089/otherWays/updatePassword",
				async:true,
				dataType : 'json',  
				contentType :"application/json;charset=UTF-8",
				data:{
					"username" : username,
					"oldPassword" :oldPwd,
					"newPassword" :newPwd
				},
				success: function(result){},
				error: function(e){
					
				}
			});
		}else{
			alert("两次密码不一致,请重新输入")
			 $("#oldPassword").val();
			 $("#newPassword").val();
			 $("#newPassword1").val();
		}
		
	})


})