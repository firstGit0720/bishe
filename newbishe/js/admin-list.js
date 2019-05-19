$(function(){
	//从session中获取用户信息
	var userMessage;
	$.ajax({    
                url :"http://localhost:8089/otherWays/getUser",                               
                data : {
						"username" : sessionStorage.getItem("uesrMessage")
					}, 
                type : 'get',    
                dataType : 'json',  
				async : false, 
				contentType :"application/json;charset=UTF-8", 
                success : function(result) {   
				userMessage = result;
                },    
                error : function(msg) {  
//	 				layer.msg('信息获取失败', {icon: 1});
                }    
      });    

	search = function(){
		var user = $("#userpname").val()
	 	$("#userTables").dataTable().fnDestroy();
		getDateTable(user);
	}
	//显示table表格
	getDateTable(null);
	function getDateTable(userpanme){
		//获取所有会员信息,并封装到datatable
		$("#userTables").dataTable({      
                "bProcessing": false,                   // 是否显示取数据时的那个等待提示    
				searching: false,//搜索
                "bServerSide": true,                    //这个用来指明是通过服务端来取数据    
                "sAjaxSource": "http://localhost:8089/allusers",      //这个是请求的地址    
                "fnServerData": retrieveData,            // 获取数据的处理函数    
			     "columns": [
			                {"data": "id","bSortable": false},
			                {"data": "userName"},
							{"data": "userPname"},
			                {"data": "userSex"},
							{"data": "userAge"},
							{"data": "userPhone"},
							{"data": "userEmail"},
			                {"data": "userType"},
							{"data": "createTime"}
			            ],
			            "columnDefs": [
			                {
			                    "targets": [9],
			                    "data": "id",
			                    "render": function(data, type, full) {
			                        return "<button class='smailButton' id='" + data + "' onClick =member_del(this,"+data+")>删除</button>"+
									"<button class='smailButton' style='width:120px;' id='" + data + "' onClick = 'updateType("+data+")'>修改权限等级</button>";
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
		
		                    zeroRecords: "没有内容",//table tbody内容为空时，tbody的内容。
		                    //下面三者构成了总体的左下角的内容。
		                    info: "总共_PAGES_ 页，显示第_START_ 条到第 _END_ 条 ",//左下角的信息显示，大写的词为关键字。
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
					"userpname" : userpanme
				},   //这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数 ,分页,排序,查询等的值   
                type : 'get',    
                dataType : 'json',   
				contentType :"application/json;charset=UTF-8", 
                async : false,  
                success : function(result) {  
                    fnCallback(result);                     //把返回的数据传给这个方法就可以了,datatable会自动绑定数据的    
                },    
                error : function(msg) {    
				/*layer.msg('数据获取失败', {icon: 1});*/
                }    
            });    
        } 
	}

	 updateType = function(id){
	 	if(userMessage == null){
	 		 layer.confirm('您的登录时间已过期,请重新登录？',function(){
      		 	window.location.href = "login.html"
	        });
	 	}else{
	 		if(userMessage.userType == 0){
	 			setTimeout(function(){
		 			x_admin_show('会员权限修改','admin-edit.html?id='+id)
		 		},100)
	 		}else{
	 			  layer.msg('您的权限不够,不能完成此功能!', {icon: 2});
	 		}
	 	}
	 	
	 }  
	
	/*"fnDrawCallback": function() {
                                    var api = this.api();
                                    var startIndex= api.context[0]._iDisplayStart;// 获取到本页开始的条数
                                    api.column(1).nodes().each(function(cell, i) {
                                        cell.innerHTML = startIndex + i + 1;
                                    });
                                }*/
	
	 member_del = function(obj,id){
		if(userMessage == null){
      		 layer.confirm('您的登录时间已过期,请重新登录？',function(){
      		 	window.location.href = "login.html"
	        });
      	}else{
      		if(userMessage.userType == 0){
      			 layer.confirm('确认要删除吗？',function(){
		      		$.ajax({    
		                url :"http://localhost:8089/deleteUser",                               
		                data : JSON.stringify( {
								"id" : id,
							}), 
		                type : 'post',    
		                dataType : 'json',  
						contentType :"application/json;charset=UTF-8", 
		                success : function(result) {   
							if(result){
								layer.msg('已删除!',{icon:1,time:1000});
				                location.replace(location.href)
							}else{
								layer.msg('已删除!',{icon:2,time:1000});
				              location.replace(location.href)
							}
		                },    
		                error : function(msg) {    
		                }    
		     		 });    
		      	
		         
		      });
      		}else{
      			layer.msg('您的权限不够,不能进行删除!',{icon:2,time:2000});
      		}
      		
    }
		
         
      }

	
})