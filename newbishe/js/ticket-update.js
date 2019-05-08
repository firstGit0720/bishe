$(function(){
		/**********************改签的查询*********************************/
		var trainId= GetQueryString("id");
		function GetQueryString(name){
		    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
		    var r = window.location.search.substr(1).match(reg);
		    if(r!=null)return  unescape(r[2]); return null;
		}
		var username =sessionStorage.getItem("uesrMessage");
	  	var userMessage;
	  	var strfrom,strarrive,datestr,trainCard;
		var obj = {
			"username" :username,
			
		}
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
	                },    
	                error : function(msg) {    
	                }    
	      });  
		$.ajax({
	 		type:"get",
	 		url:"http://localhost:8089/ticketother/getMessage",
	 		async:false,
	 		dataType : 'json',   
			contentType :"application/json; charset=UTF-8", 
	 		data : {
	 			"id" : trainId
	 		},
	 		success : function (resp){
	 			strfrom = resp.indentFrom;
	 			strarrive = resp.indentArrive;
	 			datestr = resp.trainStartTime.substring(0,10)
	 		},
	 		error : function(err){
	 			
	 		}
	 });
	 		
		getTable(strfrom,strarrive,datestr)
		
		function getTable(from,arrive,time){
		  $("#mytable").dataTable({  
				 "pagingType": "simple_numbers",//设置分页控件的模式      
                "bProcessing": false,                   // 是否显示取数据时的那个等待提示  
 				 "bLengthChange": true,//屏蔽tables的一页展示多少条记录的下拉列表    
					searching: false,//搜索
                "bServerSide": true,                    //这个用来指明是通过服务端来取数据    
                "sAjaxSource": "http://localhost:8089/ticketBuy/selectTrainTickets",      //这个是请求的地址    
                "fnServerData": retrieveData,            // 获取数据的处理函数    
			     "columns": [
			                {"data": "trainCard","bSortable": false},
			                {"data": "trainFrom"},
							{"data": "trainFromTime"},
			                {"data": "trainArrive"},
			                {"data": "trainArriveTime"},
							{"data": "trainWait"},
							{"data": "trainAfter"},
			                {"data": "seatBestNum"},
							{"data": "seatFirstNum"},
			                {"data": "seatSecondNum"},
			                {"data": "sleeperFirstSoftNum"},
							{"data": "sleeperBestNum"},
			                {"data": "sleeperSportNum"},
							{"data": "sleeperStiffNum"},
			                {"data": "seatSoftNum"},
			                {"data": "seatStiffNum"},
							 {"data": "seatNoNum"},
			                {"data": "seatOtherNum"},
			            ],
			            "columnDefs": [
			                {
			                    "targets": [18],
			                    "data": "id",
			                    "render": function(data, type, full) {
			                        return "<a class='smailButton' id='" + data + "' onClick = 'buyTicket("+data+")'>改签</a>";
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
					"from"	: from,
					"arrive" : arrive,
					"date" : time
				},   //这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数 ,分页,排序,查询等的值   
                type : 'get',    
                dataType : 'json',   
				contentType :"application/json; charset=UTF-8", 
                async : false,  
                success : function(result) {   
//					console.info(result)
                    fnCallback(result);
                     //把返回的数据传给这个方法就可以了,datatable会自动绑定数据的    
                },    
                error : function(msg) {    
                }    
            });    
        }   
	}
	var ticketBuyId;	
	buyTicket = function(id){
		$("#mytable").find("tr").each(function(){
			trainCard = $(this).children('td:eq(0)').text()
	        datestr += " " +  $(this).children('td:eq(2)').text()
	    });
		
			ticketBuyId = id;
		/*根据火车的id查找火车的座位信息*/
		$.ajax({
			type:"get",
			url:"http://localhost:8089/ticketother/getTrainSeats",
			contentType :"application/json;charset=UTF-8",
			dataType : 'json',
			async:false,
			data:{
				"trainId" : id,
				"from" :strfrom,
				"arrive" : strarrive
			},
			success : function(result){
				console.log(result)
				//生成座位信息
				var seat = "<table class='layui-table' ><thead><th align='center'>座位等级</th><th align='center'>价格</th><th align='center'>操作</th></thead><tbody>"
			
				for(var i = 0; i< result.length ; i++){
					seat +=
					"<tr align='center'>"+
                		"<td>"+ result[i].seatType +"</td>"+
                		"<td>" + result[i].seatPrice + "</td>"+
                		"<td><button class='smailButton' onclick=buyOK('" + result[i].seatType + "',"+ result[i].seatPrice +")>提交订单</button></td>"+
                	"</tr>"
				}
				seat += "</tbody></table>"
				var option = {
						title: "座位详情",
						btn: parseInt("0011",2),
						onOk: function(){
							console.log("确认啦");
						}
					}
				window.wxc.xcConfirm(seat,"custom",option);
			},
			error :function(msg){}
		});
		
	};
	
	//订单确定
	buyOK = function(seatType,price){
		//生成订单信息
		var str = "<div class='x-body'>"+
			"<fieldset class='layui-elem-field' style='width: 450px;'>" + 
			"<table class='layui-table' >"+
        	"<tr align='center'>"+
        		"<td>用户名：</td>"+
        		"<td>" + userMessage.userName + "</td>"+
        	"</tr>"+
        	"<tr align='center'>"+
        		"<td>用户姓名：</td>"+
        		"<td>" + userMessage.userPname + "</td>"+
        	"</tr>"+
        	"<tr align='center'>"+
        		"<td>联系方式：</td>"+
        		"<td>" + userMessage.userPhone + "</td>"+
        	"</tr>"+
        	"<tr align='center'>"+
        		"<td>车次：</td>"+
        		"<td>" + trainCard + "</td>"+
        	"</tr>"+
        	"<tr align='center'>"+
        		"<td>出发地点：</td>"+
        		"<td>" + strfrom + "</td>"+
        	"</tr>"+
        	"<tr align='center'>"+
        		"<td>到达地点：</td>"+
        		"<td>" + strarrive + "</td>"+
        	"</tr>"+
        	"<tr align='center'>"+
        		"<td>出发时间：</td>"+
        		"<td>" + datestr + "</td>"+
        	"</tr>"+
			"<tr align='center'>"+
        		"<td>座位等级：</td>"+
        		"<td>" + seatType + "</td>"+
        	"</tr>"+
			"<tr align='center'>"+
        		"<td>价格：</td>"+
        		"<td>" + price + "</td>"+
        	"</tr>"+
			"<tr align='center'>"+
				"<td style='margin-left : 50px;'><button class='smailButton' onclick=exitMessage()> 取消改签 </button></td>"+
        		"<td style='margin-left : 50px;'><button class='smailButton' onclick=buyTicketMessage('" + seatType + "',"+ price +")> 确定改签 </button></td>"+
        	"</tr>"
		
		"</fieldset>"
		"</div>"
		var option = {
				title: "座位详情",
				btn: parseInt("0011",2),
				onOk: function(){
					console.log("确认啦");
				}
			}
		window.wxc.xcConfirm(str,"custom",option);
		
	}
	
	//提交订单,购买
	buyTicketMessage = function(seatType,price){
		exitMessage();
		update(seatType,price);
		
		
		layer.msg('改签成功！',{icon:1,time:1000});
		setTimeout(function () {
	      x_admin_close();
			x_admin_father_reload();
	      }, 1000);
		
		
	
	}
	
	function update(seatType,price){
				//先修改订单状态
		$.ajax({
	 		type:"post",
	 		url:"http://localhost:8089/ticketother/updateTicketStatus",
	 		async:false,
	 		dataType : 'json',   
			contentType :"application/json; charset=UTF-8", 
	 		data : trainId,
	 		success : function (resp){
	 			//成功后,进行购买功能
	 			buy(seatType,price);
	 		},
	 		error : function(err){
	 			
	 		}
	 	
		});
	}
	function buy(seatType,price){
		obj.trainId = ticketBuyId
		obj.trainFrom = strfrom;
		obj.trainArrive = strarrive;
		obj.seatType = seatType; 
		obj.seatPrice = price;
		obj.datestr = datestr;
		console.info(JSON.stringify(obj))

		$.ajax({    
                url : "http://localhost:8089/ticketBuy/buyTicket",                             
                data : JSON.stringify(obj),  
                type : 'post',    
                dataType : 'json',   
				contentType :"application/json; charset=UTF-8", 
                async : false,  
                success : function(result) {    
                },    
                error : function(msg) {    
                }    
            });
	}
	
	exitMessage = function(){
//		window.history.go(-1);
//		getTable(strfrom,strarrive,datestr);
		$(".clsBtn").trigger('click')
	}
	
})
