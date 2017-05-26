   function fn_logout(){
	   window.location.href="../user/logout";
   }
   function fn_updpass(){
	   //alert(adminCode);
	   $('#w').window('open');
   }
   function fn_save(){
	   var adminPassold = $("#adminPassold").textbox('getValue');
	   var adminPass = $("#adminPass").textbox('getValue');
	   var adminPassagain = $("#adminPassagain").textbox('getValue');
	  
	   if(!$("#addForm").form('validate')){
		   $.messager.alert("提示","输入有误，请核实","info");
		   return;
	   }

	   if(adminPass!=adminPassagain){
		   $.messager.alert("提示","两次输入密码不一致","info");
		   return;
	   }
	   $.ajax({
	        type: "POST",
	        url: "../user/updatepwd.do",
	        data: {pwdold:adminPassold,pwdnew:adminPass},
	        dataType: "json",
	        success: function(data){
	        	$.messager.alert(data.title,data.content,data.success,function(){
	        		if(data.success=='info'){
	        			$('#w').window('close');
	        		}
	        	});
	        }
	   });
   }