$(function(){
//	var eventType = window.wxc.xcConfirm.eventEnum;
	$("#showMessage1").hide()
	//给时间复制
	$("#fromTime").val(format(new Date()))
	
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
								console.info(result)
                },    
                error : function(msg) {    
                }    
      });    
	
	   /**
	    * 火车票根据地点查询
	    */
  	$("#selectBySpace").click(function(){
  		var from = $("#from").val()
  		var arrive = $("#arrive").val()
  		var time = $("#fromTime").val()
  		if(from.length ==0 && arrive.length ==0){
  			layer.msg('出发地和目的地不能为空！',{icon:2,time:2000});
  		}else if(from.length !=0 && arrive.length ==0){
  			layer.msg('目的地不能为空！',{icon:2,time:2000});
  		}else if(from.length ==0 && arrive.length !=0){
  			layer.msg('出发地不能为空！',{icon:2,time:2000});
  		}else{
  			strfrom =from;
  			strarrive = arrive;
  			datestr = time;
  			$("#fromDate").append(time);
			$("#fromShow").append(from);
			$("#arriveShow").append(arrive)
	  		getTable(from,arrive,time)
  		}
		
  	
  	})
	var trainId;
	buyTicket = function(id){
		if(username == null){
			layer.msg('您还没有登录,请先登录！',{icon:1,time:1000});
			setTimeout(function() {window.location.href = "login.html"}, 1000);
		}else{
			$("#mytable").find("tr").each(function(){
				trainCard = $(this).children('td:eq(0)').text()
		        datestr += " " +  $(this).children('td:eq(2)').text()
		    });
			
			trainId = id;
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
//				x_admin_show('买票','ticket-buy.html?result='+strfrom+"&userMessage=" + strarrive)
				
				
			},
			error :function(msg){}
		});
		}
		
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
				"<td style='margin-left : 50px;'><button class='smailButton' onclick=exitMessage()> 取消订单 </button></td>"+
        		"<td style='margin-left : 50px;'><button class='smailButton' onclick=buyTicketMessage('" + seatType + "',"+ price +")> 提交订单 </button></td>"+
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
		obj.trainId = trainId
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
					if(result){
						$(".clsBtn").trigger('click')
						layer.msg('购买成功！',{icon:2,time:1000});
//						setTimeout(function() {window.location.href = "admin.html"}, 1000);
					}else{
						layer.msg('购买失败！',{icon:2,time:1000});
					}             
                },    
                error : function(msg) {    
                }    
            });
		
	
	}

	
	function getTable(from,arrive,time){
		  $("#mytable").dataTable({      
                "bProcessing": false,                   // 是否显示取数据时的那个等待提示    
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
			                        return "<a class='smailButton' id='" + data + "' onClick = 'buyTicket("+data+")'>购买</a>";
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
		$("#select").hide()
 		$("#showMessage1").show()
	}
	

	/*$("#goBack").on("click",function (){
		if(username == null){
				layer.confirm('您好没有登录,请先登录!',function(){
      		 	window.location.href = "login.html"
	        });
		}else{
			if(userMessage.userType < 2){
				window.location.href = "backstage.html"
			}else{
				layer.msg('您的权限不够，不能进入后台！',{icon:2,time:2000});
				
			}
		}
		
	})*/
	
	exitMessage = function(){
//		window.history.go(-1);
//		getTable(strfrom,strarrive,datestr);
		$(".clsBtn").trigger('click')
	}
	
	
	
	
	/**************************************时间格式化处理************************************/
	function format(shijianchuo){
		//shijianchuo是整数，否则要parseInt转换
		var time = new Date(shijianchuo);
		var y = time.getFullYear();
		var m = time.getMonth()+1;
		var d = time.getDate();
		return y+'-'+add0(m)+'-'+add0(d);
	}
	function add0(m){return m<10?'0'+m:m }
	
	/**************************************控制日期不能选择今天之前的日期************************/
	$("#fromTime").change(function(){

         var feeConfirmDate = $("#fromTime").val();

        var d1 = new Date(feeConfirmDate);

         //时间格式验证：必须为非空的时间格式

        if(d1 == "Invalid Date"){ 

           alert("费用确认日期必填，同时不能大于现在时间");

        }else{

            //取当前年月日，舍去时分秒

             var d = new Date();

              d = new Date(d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate());

              d1 = new Date(d1.getFullYear() + "-" + (d1.getMonth() + 1) + "-" + d1.getDate());

             var n = d.getTime() - d1.getTime();

              if(n > 0){
					$("#fromTime").val(format(new Date()))
                  layer.msg('日期不能选择之前的日期！',{icon:2,time:2000});

              }

      }

   });
  
})
