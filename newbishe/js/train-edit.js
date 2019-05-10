$(function(){
	var id= GetQueryString("id");
	function GetQueryString(name){
	    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	    var r = window.location.search.substr(1).match(reg);
	    if(r!=null)return  unescape(r[2]); return null;
	}
	
	//根据id获取火车信息
	var train;
	 $.ajax({    
                url :"http://localhost:8089/ticketother/getSelectById",                          
                data : {
					"trainId" : id
				},
                type : 'get',    
                dataType : 'json',   
				contentType :"application/json;charset=UTF-8", 
                async : false,  
                success : function(result) {   
					train = result;
					console.info(result) 
                },    
                error : function(msg) {    
                }    
            });

	//将数据填入到相应的位置上
	$("#id").val(train.id)
	$("#trainCard").val(train.trainCard)
	$("#from").val(train.trainFrom)
	$("#fromTime").val(train.trainFromTime)
	$("#arrive").val(train.trainArrive)
	$("#arriveTime").val(train.trainArriveTime)
	
	//修改按钮
	$("#editOK").on("click",function(){
		var trainMessage = {
			"id":$("#id").val(),
			"trainFrom" : $("#from").val(),
			"trainFromTime" : $("#fromTime").val(),
			"trainArrive" : $("#arrive").val(),
			"trainArriveTime" : $("#arriveTime").val()
		}
		console.info(trainMessage)
		$.ajax({    
                url :"http://localhost:8089/ticketother/updateTrain",                          
                data :JSON.stringify(trainMessage),
                type : 'post',    
                dataType : 'json',   
				contentType :"application/json;charset=UTF-8", 
                async : false,  
                success : function(result) {   
					console.info(result) 
					if(result){
						x_admin_close();
      					x_admin_father_reload();
      					
						layer.alert('修改成功!',{icon:1,time:2000});
						
						
					}else{
						layer.msg('修改失败!',{icon:2,time:2000});
//						location.replace(location.href);
						setTimeout(function(){
							x_admin_close();
          					x_admin_father_reload();
						},2000)
					}
                },    
                error : function(msg) {    
						layer.msg('信息获取失败', {icon: 1});
                }    
            });
		
		
	})
	
	
	$("#exit").on("click",function(){
		  x_admin_close();
          x_admin_father_reload();
	})
	
	
})