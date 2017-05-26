$(function(){
	init();
})

function init(){
	$("#datagrid").datagrid({
		url : "../role/queryAll.do", //获取数据地址
		//iconCls : "icon-save", //图标
		nowrap:false, //是否关闭自动换行(true关闭，false开启)
		border : true, //边框
		width:212,
		height:'100%',
		singleSelect:true,
		onClickRow: function(index,row){//双击行事件
			$("#ytree").tree({
				url:'../menu/qyeryMenuY.do',
				queryParams:{roleId:row.id}
			});
		    $("#ntree").tree({
				url:'../menu/qyeryMenuN.do',
				queryParams:{
					roleId:row.id
				}
			});
		},
		toolbar:toolbar,//加载按钮功能
		columns : [[ 
		 {
			title : "<b>角色名</b>",
			field : "name",
			align : 'center',
			width : 80
		},{
			title : "<b>角色描述</b>",
			field : "desc",
			align : 'center',
			width : 120
		} ]] 
	});
}
var url = '';
var toolbar = [{
	text:'新增',
	iconCls:'icon-add',
	handler:function(){
		url='../role/insert.do';
		$('#ff').form('clear');
		$('#w').window('open');
	}
},'-',{
	text:'修改',
	iconCls:'icon-edit',
	handler:function(){
		var row = $("#datagrid").datagrid("getSelected");
		if(row){
			url='../role/update';
			$('#ff').form('load',row);
			$('#w').window('open');
		}
	}
},'-',{
	text:'删除',
	iconCls:'icon-cut',
	handler:function(){
		$.ajax({  
			url:'../role/delete.do',
			type:'post',
			data:{id:$('#datagrid').datagrid('getSelected').id},
			dataType:'json',
			error:function(){
				$.messager.alert("友情提示","系统异常,请稍后尝试","error");
			}, 
			success:function(r){ 
				$.messager.alert(r.title,r.content,"info",function(){
					if(r.success=='info'){
						$('#datagrid').datagrid('reload');   
					}
				});
			}  
		});
	}
}];
function fn_save(){
    $('#ff').form('submit',{
        url: url,
        onSubmit: function(){
            return $(this).form('validate');
        },
        success: function(result){;
	      	var result = eval('('+result+')');
      		$.messager.alert(result.title,result.content,result.success, function () {
      			if(result.success=='info'){
	      			$('#w').window('close');
	      			$('#datagrid').datagrid('reload');
      			}
  			});
        }
    });
}
function fn_del_rolemenu(){
	
    
        
    

	var menuidstr = '';
	var row=$("#ytree").tree('getSelected');
	if(row){
		$.messager.confirm('提示', '确定移出这些菜单?', function (r) {
			if (!r) {return;}
			var nodes  =$('#ntree').tree('getChildren');
			
			//if(!fn_check_menu_isexit(nodes,row.id)){
			menuidstr += row.id+',';
			//}
			
			var children = $('#ytree').tree('getChildren', row.target);
			$.each(children,function(i,cm){
				//if(!fn_check_menu_isexit(nodes,cm['id'])){
					menuidstr+=cm['id']+',';
				//}
			});
	
			if(menuidstr!=''){
				menuidstr = menuidstr.substring(0,menuidstr.length-1);
			  	$.ajax({  
					url:'../rolemenu/delete',
					type:'post',
					data:{roleId:$('#datagrid').datagrid('getSelected').id,menuId:menuidstr},
					dataType:'json',
					error:function(){
						$.messager.alert("友情提示","系统异常,请稍后尝试","error");
					}, 
					success:function(r){ 
						$.messager.alert(r.title,r.content,r.success,function(){
							if(r.success=='info'){
							    $("#ytree").tree('reload');
							    $("#ntree").tree('reload');
							}		
						});
					}  
				});
			}else{
				alert('没有可删除的菜单');
			}
		});	
	}else{
		alert('请选择需要移除的菜单')
	}
}

function fn_get_tree_parent(row){
	
	var parent = $('#ntree').tree('getParent', row.target);
	var str = row.id;
	if(parent){
		str += ','+fn_get_tree_parent(parent);
	}
	return str;
}

function fn_add_rolemenu(){
	var row=$("#ntree").tree('getSelected');
	var menuidstr = '';
	var row=$("#ntree").tree('getSelected');
	if(row){
		$.messager.confirm('提示', '确定添加这些菜单?', function (r) {
			if (!r) {return;}
			var nodes  =$('#ytree').tree('getChildren');
			
			var parent = fn_get_tree_parent(row);
			var parentIds=parent.split(',');
			for(i=0;i<parentIds.length;i++){
				if(!fn_check_menu_isexit(nodes,parentIds[i])){
					menuidstr += parentIds[i]+',';
				}    
			}
			
			var children = $('#ntree').tree('getChildren', row.target);
			$.each(children,function(i,cm){
				if(!fn_check_menu_isexit(nodes,cm['id'])){
					menuidstr+=cm['id']+',';
				}
			});
	
			if(menuidstr!=''){
				menuidstr = menuidstr.substring(0,menuidstr.length-1);
			  	$.ajax({  
					url:'../rolemenu/insert',
					type:'post',
					data:{roleId:$('#datagrid').datagrid('getSelected').id,menuId:menuidstr},
					dataType:'json',
					error:function(){
						$.messager.alert("友情提示","系统异常,请稍后尝试","error");
					}, 
					success:function(r){ 
						$.messager.alert(r.title,r.content,r.success,function(){
							if(r.success=='info'){
							    $("#ytree").tree('reload');
							    $("#ntree").tree('reload');
							}		
						});
					}  
				});
			}else{
				alert('没有可添加的菜单');
			}
		})	
	}else{
		alert('请选择需要移除的菜单')
	}

}
function fn_check_menu_isexit(nodes,menuId){
	var isexit = false;
	$.each(nodes,function(i,cm){
		if(!isexit){
		   	if(cm['id']==menuId){
		   		isexit=true;
		   		return true;
		   	}
		}else{
			return true;
		}
	});
	return isexit;
}