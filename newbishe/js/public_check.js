$(function(){
	 //获取当前登录人的信息
  	var username =sessionStorage.getItem("uesrMessage");
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
								userMessage = result;
								console.info(result)
                },    
                error : function(msg) {    
                }    
      });    
	var aaa = "test";


	
	updateImage = function(){
		//获取该行信息
		x_admin_show('修改头像','updateLogo.html')
	}
  
  	$('#show').empty()
	$("#show").append("菜单")
	if(userMessage ==null || userMessage == undefined){
		$('#show').empty()
		$("#show").append("菜单")
		$("#logo").attr("style","display:none");
	}else{
		$('#show').empty();
		$("#show").append(username);
		$("#logo").attr("src","http://localhost:8089/otherWays/getiamge?username="+username)
	}
	$("#goBack").on("click",function (){
		if(username == null){
				layer.confirm('您好没有登录,请先登录!',function(){
      		 	window.location.href = "login.html"
	        });
		}else if(userMessage == null || userMessage ==undefined){
			layer.confirm('您登录的时间已过期,请重新登录!',function(){
      		 	window.location.href = "login.html"
	        });
		}else{
			if(userMessage.userType < 2){
				window.location.href = "backstage.html"
			}else{
				layer.msg('您的权限不够，不能进入后台！',{icon:2,time:2000});
				
			}
		}
		
	})
	showUserMessage = function(){
		if(username == null || username.length ==0){
			layer.confirm('您好没有登录,请先登录!',function(){
  		 	window.location.href = "login.html"
	        });
		}else{
			window.location.href = 'admin.html'
		}
	}
	
	lgout = function(){
		//清空session
		sessionStorage.clear()
		layer.msg('退出成功,即将调至主页！',{icon:1,time:1000});
//		setTimeout(function() {window.location.href = "index.html";}, 1300);
		//清空缓存
		/*$.ajax({    
                url :"http://localhost:8089/otherWays/clearUserMessage",                               
                data : username, 
                type : 'post',    
                dataType : 'json',  
				async : false, 
				contentType :"application/json;charset=UTF-8", 
                success : function(result) {   
					if(result){
						layer.msg('退出成功,即将调至主页！',{icon:1,time:1000});
						setTimeout(function() {window.location.href = "index.html";}, 1300);
					}
					
                },    
                error : function(msg) {    
                }    
      });    */
  
		
	
	}
	
})