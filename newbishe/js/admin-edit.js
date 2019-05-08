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
					console.info(result)
					//将值复制到相应的位置
					$("#id").val(result.id)
					$("#username").val(result.userName)
					$("#userpname").val(result.userPname)
					$("input[name='userType'][value='"+result.userType+"']").prop("checked",true);
                },    
                error : function(msg) {    
                }    
     		 });    
	
	$("#update").on("click",function(){
						
		//获取信息
		$.ajax({    
                url :"http://localhost:8089/updateUserType",                               
                data : JSON.stringify({
						"id" : $("#id").val(),
						"userType" : $("input[name='userType']:checked").val()
					}), 
                type : 'post',    
                dataType : 'json',  
				contentType :"application/json;charset=UTF-8", 
                success : function(result) { 
					alert(result) 
					if(result){
						layer.msg('删除成功!',{icon:1,time:2000});
						location.replace(location.href);
					}else{
						layer.msg('删除失败!',{icon:2,time:2000});
						location.replace(location.href);
					}
					
                },    
                error : function(msg) {    
                }    
     		 });
	})
	
	$("#exit").on("click",function(){
//		  location.replace(location.href);
 		  x_admin_close();
          x_admin_father_reload();
	})
	
	
	
	
	
	
})