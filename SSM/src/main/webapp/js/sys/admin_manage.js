function fn_fmt_status(val,rec){
	if(val=='1'){
		return "<span style='color:blue'>可用</span>";
	}else{
		return "<span style='color:red'>禁用</span>";
	}
}
var url = '';
var toolbar = [{
	text:'添加',
	iconCls:'icon-add',
	handler:function(){
		url='../user/insert.do';
		$("#tr_pwd").show();
		$("#dg_role").datagrid('reload');
		$("#username").textbox('readonly','');
		$('#ff').form('clear');
		$('#w').window('open');
	}
},'-',{
	text:'删除',
	iconCls:'icon-remove',
	handler:function(){
		var row = $("#dg_user").datagrid("getSelected");
		if(row){
			$.ajax({  
				url:'../user/delete.do',
				type:'post',
				data:{'id':row.id},
				dataType:'json',
				error:function(){
					$.messager.alert("友情提示","系统异常,请稍后尝试","error");
				}, 
				success:function(r){ 
					$.messager.alert(r.title,r.content,r.success,function(){
						if(r.success=='info'){
							$("#dg_user").datagrid('reload');
						}
					});
				}  
			});
		}else{
			$.messager.alert("友情提示","请选择需要操作的数据","info");
		}
	}
},'-',{
	text:'修改',
	iconCls:'icon-edit',
	handler:function(){
		fn_update();
	}
}];
function fn_update(){
	var row = $("#dg_user").datagrid("getSelected");
	if(row){
		$("#tr_pwd").hide();
//		$("#dg_role").datagrid('reload');
		$("#dg_role").datagrid('clearSelections');
		$("#username").textbox('readonly','readonly');
		url='../user/update.do';
		$('#ff').form('load',row);
		$('#w').window('open');
		$.ajax({  
			url:'../userrole/query.do',
			type:'post',
			data:{userId:row.username},
			dataType:'json',
			error:function(){
				$.messager.alert("友情提示","系统异常,请稍后尝试","error");
			}, 
			success:function(r){ 
				$(r).each(function(i,cm){
					$($("#dg_role").datagrid('getRows')).each(function(i,cmm){
						if(cm['roleId']==cmm['id']){
							$("#dg_role").datagrid('selectRow',i);
						}
					});
				});
			}  
		});
	}else{
		$.messager.alert("友情提示","请选择需要操作的数据","info");
	}
}
function fn_save(){
	var username = $("#username").textbox('getValue');
	var pwd = $("#pwd").textbox('getValue');
	var fullname = $("#fullname").textbox('getValue');
	var id = $("#id").val();
	var status = $("#status").combobox('getValue');
	var rows = $("#dg_role").datagrid('getSelections');
	var roles = '';
	$(rows).each(function(i,cm){
		roles+=cm['id']+',';
	});
	roles = roles.substring(0,roles.length-1);
	if($("#ff").form('validate')){
		$.ajax({  
			url:url,
			type:'post',
			data:{'username':username,'pwd':pwd,'fullname':fullname,'id':id,'roles':roles,'status':status},
			dataType:'json',
			error:function(){
				$.messager.alert("友情提示","系统异常,请稍后尝试","error");
			}, 
			success:function(r){ 
				
				$.messager.alert(r.title,r.content,r.success,function(){
					if(r.success=='info'){
						$("#dg_user").datagrid('reload');
						$('#w').window('close');
					}
				});
			}  
		});
	}
}