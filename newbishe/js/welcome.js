$(function(){
	 //获取当前登录人的信息
  	var username =sessionStorage.getItem("uesrMessage");
	$("#show").append(username)
	function currentTime(){
	  var d=new Date(),str='';
	  str+=d.getFullYear()+'-';
	  if((d.getMonth() + 1) < 10){
	  	str+= "0" + (d.getMonth() + 1)
	  }else{
	  	str+= (d.getMonth() + 1)
	  }
	  str+='-';
	  if(d.getDate() < 10){
	  	str+= "0" + d.getDate()
	  }else{
	  	str+= d.getDate()
	  }
	  str+='  ';
	  if(d.getHours() < 10){
	  	str+= "0" + d.getHours()
	  }else{
	  	str+= d.getHours()
	  }
	  str += ":";
	   if(d.getMinutes() < 10){
	  	str+= "0" + d.getMinutes()
	  }else{
	  	str+= d.getMinutes()
	  }
	  str+=':';
	  if(d.getSeconds() < 10){
	  	str+= "0" + d.getSeconds()
	  }else{
	  	str+=d.getSeconds()
	  }
	  return str;
	}
	
	setInterval(function(){
		$('#time').empty()
		$('#time').append(currentTime());
		},1000);
	
})