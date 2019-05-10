$(function(){
	//获取当前登录人的信息
  /*	var usernamestr =sessionStorage.getItem("uesrMessage");
	if(usernamestr !=null){
		$('#show').empty()
		$("#show").append(usernamestr)
	}else{
		$('#show').empty()
		$("#show").append("菜单")
	}
*/
	$("#isok").on("click",function(){
		var data = {
			"userName" : $("#username").val(),
			"password" : $("#password").val(),
			"userPname" : $("#pname").val(),
			"userSex" :  $('input[name="userSex"]:checked').val(),
			"userAddress" :$("#userAddress").val(),
			"userCard" : $("#userCard").val(),
			"userBirthday" : $("#userBirthday").val(),
			"userPhone" : $("#userPhone").val(),
			"userEmail" : $("#userEmail").val()
		}
		console.log(data)
		if(checkUsername($("#username").val())){
			layer.msg('该用户名已存在!',{icon:2,time:2000});
		}else{
			 $.ajax({    
                url :"http://localhost:8089/register",                               
                data :JSON.stringify(data), 
                type : 'post',    
                dataType : 'json',   
				contentType :"application/json;charset=UTF-8", 
                success : function(result) {  
					if(result){
							layer.msg('注册成功！',{icon:1,time:1000});
							setTimeout(function() {window.history.go(-1);}, 1000);
					} else{
						layer.msg('注册失败', {icon:2,time:2000});
					}
                },    
                error : function(msg) {    
					layer.msg('发生了一些问题,请稍后重试!', {icon: 1});
                }    
            });    
		}
		
	})
	
	//鼠标消失事件
	$("#username").blur(function(){
		//获取该字段的信息
		var user = $("#username").val()
		var result = checkUsername(user);
		if(result){
			layer.msg('该用户名已存在!',{icon:2,time:2000});
		} 
		
	});
	
	function checkUsername(name){
		var check = false;
		$.ajax({    
                url :"http://localhost:8089/isExist",                               
                data :name, 
                type : 'post',    
                dataType : 'json',   
				contentType :"application/json;charset=UTF-8", 
                success : function(result) {  
					check=result;
                },
				error : function(msg) {    
					layer.msg('发生了一些问题,请稍后重试!', {icon: 1});
                }        
                
            });  
		return check;  
		
	}
	
	/*$("#goBack").on("click",function (){
		if(usernamestr == null){
				layer.confirm('您好没有登录,请先登录!',function(){
      		 	window.location.href = "login.html"
	        });
		}else{
			if(userMessage.userType < 2){
				window.location.href = "backstage.html"
			}else{
				layer.msg('您的权限不够，不能进入后台！',{icon:2,time:2000});
				
			}
		}
		
	})*/
	
	
})
