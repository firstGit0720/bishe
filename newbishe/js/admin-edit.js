$(function(){
	//获取id
	var id= GetQueryString("id");
	function GetQueryString(name){
	    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	    var r = window.location.search.substr(1).match(reg);
	    if(r!=null)return  unescape(r[2]); return null;
	}
	
	//更具id获取权限信息
		$.ajax({    
                url :"http://localhost:8089/getUserType",                               
                data : {
						"userId" : id,
					}, 
                type : 'get',    
                dataType : 'json',  
				contentType :"application/json;charset=UTF-8", 
                success : function(result) {   
//					console.info(result)
					//将值复制到相应的位置
					$("#id").val(result.id)
					$("input[name='userType'][value='"+result.userType+"']").prop("checked",true);
					$("#username").val(result.userName)
					$("#userpname").val(result.userPname)
					
                },    
                error : function(msg) {    
                }    
     		 });    
	
	update = function(){
		alert("修改")
		console.info(JSON.stringify({"id" : id,	"userType" : $("input[name='userType']:checked").val()}))	
		var data = JSON.stringify({"id" : id,	"userType" : $("input[name='userType']:checked").val()});
		//获取信息
		$.ajax({    
                url :"http://localhost:8089/updateUserType",                               
                data : data, 
                type : 'post',
				async : false,    
                dataType : 'json',  
				contentType :"application/json;charset=UTF-8", 
                success : function(result) { 
					if(result){
						x_admin_close();
      					x_admin_father_reload();
      				}else{
						layer.msg('修改失败!',{icon:2,time:2000});
					}
                },    
                error : function(msg) {    
                }    
     		 });
	}
	
	$("#exit").on("click",function(){
//		  location.replace(location.href);
 		  x_admin_close();
          x_admin_father_reload();
	})
	
	
	
	
	
	
})