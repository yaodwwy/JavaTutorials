function fn_get_city(){
	var row = $("#province_dg").datagrid("getSelected");
	$("#city_dg").datagrid({
		url:'../city/query.do',
		queryParams: {
			provinceCode:row.code
		}
	})
	$('#region_dg').datagrid('loadData', { total: 0, rows: [] }); 
}
function fn_get_region(){
	var row = $("#city_dg").datagrid("getSelected");
	$("#region_dg").datagrid({
		url:'../region/query.do',
		queryParams: {
			cityCode:row.code
		}
	})
}
function fn_reload(flag){
	$("#"+flag+"_dg").datagrid('reload');
}
var url = "";
var parentCode = "";
var flag = '';
function fn_add(_flag){
	flag = _flag;
	if(_flag=="city"){
		var row = $("#province_dg").datagrid('getSelected');
		if(!row){
			$.messager.alert({title: '提示',msg: '请选择省'},'info');
			return;
		}else{
			parentCode=row.code;
		}
	}
	if(_flag=="region"){
		var row = $("#city_dg").datagrid('getSelected');
		if(!row){
			$.messager.alert({title: '提示',msg: '请选择市'},'info');
			return;
		}else{
			parentCode=row.code;
		}
	}
	$("#code").numberbox('readonly','');
	$("#ff").form('clear');
	url="../"+_flag+"/insert.do";
	$("#win").window('open');
}

function fn_upd(_flag){
	flag = _flag;
	var row = $("#"+_flag+"_dg").datagrid('getSelected');
    $("#ff").form('load',row);
    $("#code").numberbox('readonly','readonly');
	url="../"+_flag+"/update.do";
	$("#win").window('open');
}
function fn_save(){
	
	$.ajax({  
		url:url,
		type:'post',
		data:{
			'id':$("#id").val(),
			parentCode:parentCode,
			spell:$('#spell').textbox('getValue'),
			code:$("#code").numberbox('getValue'),
			name:$("#name").textbox('getValue'),
		},
		dataType:'json',
		error:function(){
			$.messager.alert("友情提示","系统异常,请稍后尝试","error");
		}, 
		success:function(r){ 
			$.messager.alert(r.title,r.content,r.success,function(){
				if(r.success=='info'){
					$("#win").window('close');				
					$("#"+flag+"_dg").datagrid('reload');
				}
			});
		}  
	});
}
function fn_del(flag){
	var row = $("#"+flag+"_dg").datagrid('getSelected');
	if(row){	
		$.ajax({  
			url:'../'+flag+'/delete.do',
			type:'post',
			data:{'id':row.id},
			dataType:'json',
			error:function(){
				$.messager.alert("友情提示","系统异常,请稍后尝试","error");
			}, 
			success:function(r){ 
				$.messager.alert(r.title,r.content,r.success,function(){
					if(r.success=='info'){
						$("#"+flag+"_dg").datagrid('reload');
					}
				});
			}  
		});
	}else{
	  $.messager.alert({title: '提示',msg: '请选择需要删除的数据'},'info');
	}
}