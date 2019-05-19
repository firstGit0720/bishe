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
				$("#username").val(result.userName);
				$("#userpname").val(result.userPname);
				$("input[name='userSex'][value='"+result.userSex+"']").prop("checked",true);
				$("#userPhone").val(result.userPhone);
				$("#userBirthday").val(format(parseInt(result.userBirthday)));
				$("#userCard").val(result.userCard);
				$("#userEmail").val(result.userEmail);
				$("#userAddress").val(result.userAddress);
            },    
            error : function(msg) {    
				alert("信息获取失败")
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
 
	$("#exit").on("click",function(){
		 // 获得frame索引
        var index = parent.layer.getFrameIndex(window.name);
        //关闭当前frame
        parent.layer.close(index);
	})
	
	
	
	$("#updateMessage").on("click",function(){
//		alert("修改用户信息")
		var data = {
			"userName" : $("#username").val(),
			"userPname" : $("#userpname").val(),
			"userSex" :  $('input[name="userSex"]:checked').val(),
			"userAddress" :$("#userAddress").val(),
			"userCard" : $("#userCard").val(),
			"userBirthday" : $("#userBirthday").val(),
			"userPhone" : $("#userPhone").val(),
			"userEmail" : $("#userEmail").val()
		}
		console.log(data)
		 $.ajax({    
                url :"http://localhost:8089/user/updateMessage",                               
                data :JSON.stringify(data), 
                type : 'post',    
                dataType : 'json',   
				contentType :"application/json;charset=UTF-8", 
                success : function(result) {   
					if(result){
						layer.msg('修改成功', {icon: 1});
						x_admin_close();
		                x_admin_father_reload();
					} else{
						layer.msg('修改失败', {icon: 1});
					}
                },    
                error : function(msg) {    
                }    
            }); 
		
	})


})