$(function(){
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


		 $("#journeyFuture").dataTable({      
                "bProcessing": false,                   // 是否显示取数据时的那个等待提示    
				searching: false,//搜索
                "bServerSide": true,                    //这个用来指明是通过服务端来取数据    
                "sAjaxSource": "http://localhost:8089/ticketother/getUserFuture",      //这个是请求的地址    
                "fnServerData": retrieveData,            // 获取数据的处理函数    
			     "columns": [
							{"data": "trainCrad","bSortable": false},
							{"data": "indentFrom"},
							{"data": "indentArrive"},
			               	{"data": "trainStartTime"},
							{"data": "seatType"},
							{"data": "seatMessage"},
							{"data": "isPayment"},
							{"data": "isStatus"},
							{"data": "indentTime"}
			            ],
			            "columnDefs": [ 
			                {
			                    "targets": [9],
			                    "data": "id",
			                    "render": function(data, type, full) {
			                        return "<button class='smailButton' id='" + data + "' onClick = 'updateTicket("+data+")'>改签</button>" + 
											"<button class='smailButton' id='" + data + "' onClick = 'exitTicket("+data+")'>退票</button>";
			                    }
			                }
			            ],
					language: {
		                    lengthMenu: '显示<select style="width:80px;height:30px">' + '<option value="1">1</option>' + '<option value="10">10</option>' + '<option value="20">20</option>' + '<option value="30">30</option>' + '<option value="40">40</option>' + '<option value="50">50</option>' + '</select>条记录',//左上角的分页大小显示。
		                    paginate: {//分页的样式内容。
		                        previous: "上一页",
		                        next: "下一页",
		                        first: "第一页",
		                        last: "最后"
		                    },
		
		                    zeroRecords: "没有查询到内容",//table tbody内容为空时，tbody的内容。
		                    //下面三者构成了总体的左下角的内容。
		                    info: "总共_PAGES_ 页，显示第_START_ 条到第 _END_条",//左下角的信息显示，大写的词为关键字。
		                    infoEmpty: "0条记录",//筛选为空时左下角的显示。
		                    infoFiltered: ""//筛选之后的左下角筛选提示，
		                },
		                paging: true,
		                pagingType: "full_numbers",//分页样式的类型

            });    
        // 3个参数的名字可以随便命名,但必须是3个参数,少一个都不行    
        function retrieveData(sSource,aoData, fnCallback) {    
            $.ajax({    
                url : sSource,                              //这个就是请求地址对应sAjaxSource    
                data : {
					"aoData":JSON.stringify(aoData),
					"userId": userMessage.id,
				},   //这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数 ,分页,排序,查询等的值   
                type : 'get',    
                dataType : 'json',   
				contentType :"application/json; charset=UTF-8", 
                async : false,  
                success : function(result) {   
					console.info(result)
                    fnCallback(result);
                     //把返回的数据传给这个方法就可以了,datatable会自动绑定数据的    
                },    
                error : function(msg) {    
                }    
            });    
        }   

		updateTicket = function(id){
	        x_admin_show('车票改签','ticket_update.html?id='+id)
			
		}
		
		exitTicket = function(id){
			layer.confirm('确定退票吗!',function(){
      		 	$.ajax({
			 		type:"post",
			 		url:"http://localhost:8089/ticketother/exitTicket",
			 		async:false,
			 		dataType : 'json',   
					contentType :"application/json; charset=UTF-8", 
			 		data : {
			 			"id" : id
			 		},
			 		success : function (resp){
			 			if(resp){
			 				layer.msg('退票成功！',{icon:1,time:2000});
			 			}else{
			 				layer.msg('退票失败,请到服务台进行退票！',{icon:2,time:2000});
			 			}
			 		},
			 		error : function(err){
			 			
			 		}
			 });
	        });
			
		}
	
	
})