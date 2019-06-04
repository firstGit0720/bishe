$(function(){
	  /********************************************验证码******************************************************/
	//存放当前验证码
	var nowstr;
	//存放验证码的字符
	var str = new Array()
	str.push('0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f','g','h','i','j','k','l','m','n',
	'o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O',
	'P','Q','R','S','T','U','V','W','X','Y','Z')
	//随机抽取四个，组成一个新的字符串,为方便刷新，将其写为一个方法
	newStr()
	function newStr(){
		var newstr = new String();
		//随机生成四个随机数
		for(var i= 0; i < 4; i++){
			newstr += str[Math.round(Math.random() * 60 + 1)]
		}
		nowstr = newstr;
		// 设置验证码的显示样式，并显示
		 document.getElementById("isok").style.fontFamily="Fixedsys"; //设置字体
		 document.getElementById("isok").style.fontSize="30px"; //设置字体
		 document.getElementById("isok").style.letterSpacing="10px"; //字体间距
		 document.getElementById("isok").style.color="#0ab000"; //字体颜色
		 document.getElementById("isok").innerHTML=newstr; // 显示
	}
	
	//点击事件，修改验证码
	$("#isok").on("click",function(){
		newStr()
	})
	//鼠标移开时间
	function isNumCheck(text){
//		$("#ischeck").blur(function(){
		//将输入的验证码和生成的验证码，都转换为小写
		 text= text.toLowerCase()
		nowstr = nowstr.toLowerCase()
		if(nowstr == text){
			return true;
		}
//	})

	}
	
/********************************************分割线*********************************************************/
	
	var userMessage = null;
	newStr()
	$("#ischeck").val("")
	$("#login").click(function(){
		//获取信息
		var username = $("#username").val();
		var password = $("#password").val();
//		先判断用户名和密码
		if(username.length == 0){
			layer.msg('用户名不能为空!',{icon:2,time:2000});
		}else if(password.length == 0){
				layer.msg('密码不能为空!',{icon:2,time:2000});
		}else if($("#ischeck").val().length == 0){
				layer.msg('验证码不能为空!',{icon:2,time:2000});
		}else{
			var text = $("#ischeck").val()
			if(isNumCheck(text)){
				 $.ajax({    
	                url :"http://localhost:8089/otherWays/login",                               
	                data : JSON.stringify({
											"username" : username,
											"password" : password
										}), 
	                type : 'post',    
									async : false,
	                dataType : 'json',   
									contentType :"application/json;charset=UTF-8", 
	                success : function(result) {
										if(result != null) {
												userMessage = result;
												/*将信息设置到session中,座位权限的认证*/
												sessionStorage.setItem("uesrMessage",result.userName)
												$('#show').empty()
												$("#show").append(result.userName)
												layer.msg('登陆成功！',{icon:1,time:1000});
		//										setTimeout(function() {window.history.go(-1);}, 1000);
												setTimeout(function() {self.location=document.referrer;}, 1000);
												console.info(JSON.stringify(sessionStorage.getItem("uesrMessage")))
										}else{
												layer.msg('用户名不存在,请先注册！',{icon:1,time:1000});
										}
								
											
	                },    
	                error : function(msg) {    
	                }    
            });    
			}else{
				layer.msg('验证码输入错误,请重新输入!',{icon:2,time:2000});
				newStr()
				$("#ischeck").val("")
			}
			
		}
		
	})

})