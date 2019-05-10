$(function(){
	var id= GetQueryString("id");
	function GetQueryString(name){
	    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	    var r = window.location.search.substr(1).match(reg);
	    if(r!=null)return  unescape(r[2]); return null;
	}
	
	//获取数据并放到相应的位置
		$.ajax({    
                url :"http://localhost:8089/ticketother/getTrainArrive",                          
                data : {
					"id" : id
				},
                type : 'get',    
                dataType : 'json',   
				contentType :"application/json;charset=UTF-8", 
                async : false,  
                success : function(result) {   
					$("#id").val(id)
					$("#trainId").val(result.trainId)
					$("#trainArrive").val(result.trainArrive)
					$("#trainArriveTime").val(result.trainArriveTime)
					$("#trainArriveWite").val(result.trainArriveWite)
                },    
                error : function(msg) {    
                }    
            });

	$("#editSpaceOK").on("click",function(){
		var obj = {
			"id" : id,
			"trainId" : $("#trainId").val(),
			"trainArrive" : $("#trainArrive").val(),
			"trainArriveTime" : $("#trainArriveTime").val(),
			"trainArriveWite" : $("#trainArriveWite").val()
		}
		$.ajax({    
                url :"http://localhost:8089/ticketother/updateSpace",                          
                data : JSON.stringify(obj),
                type : 'post',    
                dataType : 'json',   
				contentType :"application/json;charset=UTF-8", 
                async : false,  
                success : function(result) {   
						x_admin_close();
         				x_admin_father_reload();
                },    
                error : function(msg) {    
					layer.msg('出现了未知错误,请稍后重试!!',{icon:2,time:2000});
                }    
            });
		
		
	})
	
	
})
