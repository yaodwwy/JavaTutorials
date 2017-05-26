$(function(){
   $("#validateCode").keydown(function(e){
       if(e.keyCode==13){
          fn_login();
       }
   });
   setInterval(fn_bg_chage,3000);
   var i = 1;
   function fn_bg_chage(){
	  if(i==1){
		  $("#lg_bg1").fadeIn();
		  $("#lg_bg2").fadeOut();
		  i=2;		  
	  }else{
		  $("#lg_bg2").fadeIn();
		  $("#lg_bg1").fadeOut();
		  i=1;
	  }
   }
});
function fn_login(){
   var adminCode= $("#username").val();
   var adminPass=$("#pwd").val();
   var validateCode = $("#validateCode")
   if(adminCode==""){
      $("#username").css("border-color","#FF0000");
      $("#error").html("用户名为空");  
      return; 
   }
   if(adminPass==""){
   	  $("#pwd").css("border-color","#FF0000");
      $("#error").html("密码为空");  
      return; 
   }
   if(validateCode==""){
   	  $("#validateCode").css("border-color","#FF0000");
      $("#error").html("密码为空");  
      return; 
   }
   $("#form_login").submit();
}