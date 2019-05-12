$(function(){
	/*动态添加文本框*/
	 var j = 1,i = 1;
	addTextBox = function (form) {
	  // Create the label
	  var div = document.createElement("div");
	  div.setAttribute("class","layui-form-item");
	    div.setAttribute("id","text" + i);
	  //创建lable标签
	  var label1 = document.createElement("label");
	  label1.appendChild(document.createTextNode("*  站点名称:"));
	  label1.setAttribute("class","layui-form-label");
	  label1.setAttribute("for","trainArrive");
	  // 目的地
	  //创建放inout狂的div
	  var inputDiv1 = document.createElement("div");
	  inputDiv1.setAttribute("class","layui-input-inline");
	  var textField1 = document.createElement("input");
	  textField1.setAttribute("type","text");
	  textField1.setAttribute("name","trainArrive");
	  textField1.setAttribute("id","trainArrive");
	  textField1.setAttribute("class","layui-input");
	  textField1.setAttribute("placeholder","站点名称");
	  div.appendChild(label1)
	  inputDiv1.appendChild(textField1)
	  div.appendChild(inputDiv1);
	 
	  //到达时间
	   //创建lable标签
	  var label2 = document.createElement("label");
	  label2.appendChild(document.createTextNode("*  到达时间:"));
	  label2.setAttribute("class","layui-form-label");
	  label2.setAttribute("for","trainArriveTime");
	  // 到达时间
	  //创建放inout狂的div
	  var inputDiv2 = document.createElement("div");
	  inputDiv2.setAttribute("class","layui-input-inline");
	  var textField2 = document.createElement("input");
	  textField2.setAttribute("type","text");
	  textField2.setAttribute("class","layui-input");
	  textField2.setAttribute("name","trainArriveTime");
	  textField2.setAttribute("id","trainArriveTime");
	  textField2.setAttribute("placeholder","到达时间（9:30）");
	  div.appendChild(label2)
	  inputDiv2.appendChild(textField2)
	  div.appendChild(inputDiv2);
  	  //停留时间
	  //创建lable标签
	  var label3 = document.createElement("label");
	  label3.appendChild(document.createTextNode("*  停留时间"));
	  label3.setAttribute("class","layui-form-label");
	  label3 .setAttribute("for","trainArriveWite");
	  // 停留时间
	    //创建放inout狂的div
	  var inputDiv3 = document.createElement("div");
	  inputDiv3.setAttribute("class","layui-input-inline");
	  var textField3 = document.createElement("input");
	  textField3.setAttribute("type","text");
	  textField3.setAttribute("name","trainArriveWite");
	  textField3.setAttribute("id","trainArriveWite");
	  textField3.setAttribute("placeholder","停留时间");
	  textField3.setAttribute("class","layui-input");
	  div.appendChild(label3)
	  inputDiv3.appendChild(textField3)
	  div.appendChild(inputDiv3);
	  
	   //创建一个按钮，用于删除本格div
	  var button = document.createElement("button");
	  button.setAttribute("onclick","deleteSeat("+i+")");
	  button.setAttribute("class","delete");
	   button.appendChild(document.createTextNode("删除"))
	   div.appendChild(button);
	 
	  //添加到from表单中
	  form.append(div)
	  i++;
//	  form.insertBefore(div,afterElement);
	  return false;
	}

	
	addSeat = function(form){
//	var form = document.getElementById("trainSeat")
	 var div = document.createElement("div");
	  div.setAttribute("class","layui-form-item");
	   div.setAttribute("id","seat"+ j);
	  //创建lable标签
	  var label0 = document.createElement("label");
	  label0.appendChild(document.createTextNode("*  座位等级:"));
	  label0.setAttribute("class","layui-form-label");
	  label0.setAttribute("for","seatType");
   	  div.appendChild(label0)
	  // 座位等级选项
     //创建放inout狂的div
	  var inputDiv0 = document.createElement("div");
	  inputDiv0.setAttribute("class","layui-input-inline");
	  
	  var select = document.createElement("select");
	  select.setAttribute("name","seatType");
	  select.setAttribute("id","seatType");
	  select.setAttribute("class","selectClass")
	  //创建选项
	  var option0 = document.createElement("option")
	  option0.setAttribute("value",0);
	  option0.appendChild(document.createTextNode("特等座"));
	  select.appendChild(option0)
	  
	  var option1 = document.createElement("option")
	  option1.setAttribute("value",1);
	  option1.appendChild(document.createTextNode("一等座"));
	  select.appendChild(option1)
	  
	  var option2 = document.createElement("option")
	  option2.setAttribute("value",2);
	  option2.appendChild(document.createTextNode("二等座"));
	  select.appendChild(option2)
	  
	  var option3 = document.createElement("option")
	  option3.setAttribute("value",3);
	  option3.appendChild(document.createTextNode("软卧一等卧"));
	  select.appendChild(option3)
	  
	  var option4 = document.createElement("option")
	  option4.setAttribute("value",4);
	  option4.appendChild(document.createTextNode("高级软卧"));
	  select.appendChild(option4)
	  
	  var option5 = document.createElement("option")
	  option5.setAttribute("value",5);
	  option5.appendChild(document.createTextNode("动卧"));
	  select.appendChild(option5)
	  
	  var option6 = document.createElement("option")
	  option6.setAttribute("value",6);
	  option6.appendChild(document.createTextNode("硬卧"));
	  select.appendChild(option6)
	  
	  var option7 = document.createElement("option")
	  option7.setAttribute("value",7);
	  option7.appendChild(document.createTextNode("软座"));
	  select.appendChild(option7)
	  
	  var option8 = document.createElement("option")
	  option8.setAttribute("value",8);
	  option8.appendChild(document.createTextNode("硬座"));
	  select.appendChild(option8)
	  
	  var option9 = document.createElement("option")
	  option9.setAttribute("value",9);
	  option9.appendChild(document.createTextNode("无座"));
	  select.appendChild(option9)
	  
	  var option10 = document.createElement("option")
	  option10.setAttribute("value",10);
	  option10.appendChild(document.createTextNode("其他"));
	  select.appendChild(option10)
	 
	 
	  inputDiv0.appendChild(select)
	  div.appendChild(inputDiv0)
	  //添加座位数量
	   //创建lable标签
	  var label1 = document.createElement("label");
	  label1.appendChild(document.createTextNode("*  座位数量:"));
	  label1.setAttribute("class","layui-form-label");
	  label1.setAttribute("for","seatNum");
	  // 座位数量
	   //创建放inout狂的div
	  var inputDiv1 = document.createElement("div");
	  inputDiv1.setAttribute("class","layui-input-inline");
	  var textField1 = document.createElement("input");
	  textField1.setAttribute("type","number");
	  textField1.setAttribute("name","seatNum");
	  textField1.setAttribute("id","seatNum");
	  textField1.setAttribute("class","layui-input");
	  textField1.setAttribute("placeholder","座位数量");
	  div.appendChild(label1)
	  inputDiv1.appendChild(textField1)
	  div.appendChild(inputDiv1);
	  
 	  //添加车厢数量
   	  //创建lable标签
	  var label2 = document.createElement("label");
	  label2.appendChild(document.createTextNode("*  车厢数量:"));
	  label2.setAttribute("class","layui-form-label");
	  label2.setAttribute("for","seatCarriage");
	  // 车厢数量
	  //创建放inout狂的div
	  var inputDiv2 = document.createElement("div");
	  inputDiv2.setAttribute("class","layui-input-inline");
	  var textField2 = document.createElement("input");
	  textField2.setAttribute("type","number");
	  textField2.setAttribute("name","seatCarriage");
	  textField2.setAttribute("id","seatCarriage");
	  textField2.setAttribute("class","layui-input");
	  textField2.setAttribute("placeholder","车厢数量");
	  div.appendChild(label2)
	  inputDiv2.appendChild(textField2)
	  div.appendChild(inputDiv2);
	 
	  //添加座位数量
	  //创建lable标签
	  var label3 = document.createElement("label");
	  label3.appendChild(document.createTextNode("*  车座价格:"));
	  label3.setAttribute("class","layui-form-label");
	  label3.setAttribute("for","seatPrice");
	  // 座位数量
	  //创建放inout狂的div
	  var inputDiv3 = document.createElement("div");
	  inputDiv3.setAttribute("class","layui-input-inline");
	  var textField3 = document.createElement("input");
	  textField3.setAttribute("type","number");
	  textField3.setAttribute("name","seatPrice");
	  textField3.setAttribute("id","seatPrice");
	  textField3.setAttribute("class","layui-input");
	  textField3.setAttribute("placeholder","车座价格");
	  div.appendChild(label3)
	  inputDiv3.appendChild(textField3)
	  div.appendChild(inputDiv3);
	  
	  //创建一个按钮，用于删除本格div
	  var button = document.createElement("button");
	  button.setAttribute("onclick","deleteSeat("+j+")");
	  button.setAttribute("class","delete");
	   button.appendChild(document.createTextNode("删除"))
	   div.appendChild(button);

	  //添加到from表单中
	  form.append(div)
	}
	deleteText = function(id){
		$("#text" + id).remove()
	}
	deleteSeat = function(id){
		$("#seat" + id).remove()
	}
	
	//添加火车信息
	addTrainMessage = function (){
		//获取火车的基本信息
		var formdata = {
			"trainCard" : $("#trainCard").val(),
			"trainFrom" : $("#from").val(),
			"trainFromTime" : $("#fromTime").val(),
			"trainArrive" : $("#arrive").val(),
			"trainArriveTime" : $("#arriveTime").val()
		};
	    //获取所有的座位等级信息
//	    var seatType = document.getElementsByName("seatType").toString()
	    var seatType =[];
        $("select[name='seatType']").each(function(){
            seatType.push(parseInt($(this).val()));
        })
//	    console.log(seatType)
	    //获取所有的座位数量信息
//	    var seatNum = document.getElementsByName("seatNum").toString()
		 var seatNum =[];
        $("input[name='seatNum']").each(function(){
            seatNum.push(parseInt($(this).val()));
        })
//	    console.log(seatNum)
	    
	    //获取所有的车厢数量
//	    var seatCarriage = document.getElementsByName("seatCarriage").toString()
 		var seatCarriage =[];
        $("input[name='seatCarriage']").each(function(){
            seatCarriage.push(parseInt($(this).val()));
        })
//	    console.log(seatCarriage)
//		var seatPrice = document.getElementsByName("seatPrice").toString()
		var seatPrice =[];
        $("input[name='seatPrice']").each(function(){
            seatPrice.push(parseFloat($(this).val()));
        })
//		console.log(seatPrice)
		
		//获取站点信息
//		var trainArrive = document.getElementsByName("trainArrive").toString()
		var trainArrive =[];
        $("input[name='trainArrive']").each(function(){
            trainArrive.push($(this).val());
        })
//		console.log(trainArrive)
//		var trainArriveTime = document.getElementsByName("trainArriveTime")
		var trainArriveTime =[];
        $("input[name='trainArriveTime']").each(function(){
            trainArriveTime.push($(this).val());
        })
//		console.log(trainArriveTime)
//		var trainArriveWite = document.getElementsByName("trainArriveWite")
		var trainArriveWite =[];
        $("input[name='trainArriveWite']").each(function(){
            trainArriveWite.push($(this).val());
        })
//		console.log(trainArriveWite)
		
		 $.ajax({    
                url :"http://localhost:8089/ticketother/add",                               
                data : JSON.stringify({
					"train" : formdata,
					"seatType" : seatType,
					"seatNum" :seatNum,
					"seatCarriage" : seatCarriage,
					"seatPrice" : seatPrice,
					"trainArrive" : trainArrive,
					"trainArriveTime" :trainArriveTime,
					"trainArriveWite" : trainArriveWite}),
                type : 'post',    
                dataType : 'json',   
				contentType :"application/json;charset=UTF-8", 
                async : false,  
                success : function(result) {   
					if(result){
						layer.msg('火车信息添加成功！',{icon:1,time:1000});
		                x_admin_close();
		                x_admin_father_reload();
						location.replace(location.href);
					}else{
						layer.msg('火车信息添加失败！',{icon:2,time:1000});
		                x_admin_close();
		                x_admin_father_reload();
						location.replace(location.href);
					}
                },    
                error : function(msg) {  
						layer.msg('出现了未知错误,请稍后重试！',{icon:1,time:1000});
//		                x_admin_close();
//		                x_admin_father_reload();  
                }    
            });
		
	    
	}
	
	
})
