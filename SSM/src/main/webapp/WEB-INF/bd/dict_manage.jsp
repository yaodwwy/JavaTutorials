<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>dict manage</title>
<%@ include file="../common/common.jsp"%>
<script type="text/javascript">
$(function(){
	var winWidth = $(window).width();
	$('#dict_win').window({left:(winWidth/10*4-310)/2});
	$('#dd_win').window({left:winWidth/10*4+((winWidth/10*6-310)/2)});
});
var dict_toolbar = [{
		text:'新增',
		iconCls:'icon-add',
		handler:fn_dict_add
	},'-',{
		text:'修改',
		iconCls:'icon-edit',
		handler:fn_dict_upd
	},'-',{
		text:'删除',
		iconCls:'icon-remove',
		handler:fn_dict_del
	},'-',{
	text:'刷新',
	iconCls:'icon-reload',
	handler:function(){$('#dict_dg').datagrid('reload');}
}];

var dd_toolbar = [{
		text:'新增',
		iconCls:'icon-add',handler:fn_dd_add
	},'-',{
		text:'修改',
		iconCls:'icon-edit',handler:fn_dd_upd
	},'-',{
		text:'删除',
		iconCls:'icon-remove',handler:fn_dd_del
	},'-',{
	text:'刷新',
	iconCls:'icon-reload',
	handler:function(){$('#dd_dg').datagrid('reload');}
}];
var dict_url='';
function fn_dict_add(){
	  $("#dict_form").form('clear');
	  $("#code").textbox('readonly','');
	  $('#dict_win').window('open');
	  dict_url='../dict/insert';
}
function fn_dict_upd(){
  var row = $("#dict_dg").datagrid("getSelected");
  if(row){
	  $('#dict_form').form('load',row);
	  $("#code").textbox('readonly','readonly');
	  $('#dict_win').window('open');
	  dict_url='../dict/update';
  }else{
	  $.messager.alert({title: '提示',msg: '请选择需要修改的类别'},'info');
  }
}
function fn_dict_del(){
  var row = $("#dict_dg").datagrid("getSelected");
  if(row){	
			$.ajax({  
				url:'../dict/delete',
				type:'post',
				data:{'id':row.id},
				dataType:'json',
				error:function(){
					$.messager.alert("友情提示","系统异常,请稍后尝试","error");
				}, 
				success:function(r){ 
					$.messager.alert(r.title,r.content,r.success,function(){
						if(r.success=='info'){
							$("#dict_dg").datagrid('reload');
							$("#dd_form #code").combobox('reload','../dict/query');
						}
					});
				}  
			});
  }else{
	  $.messager.alert({title: '提示',msg: '请选择需要删除的字典'},'info');
  }
}
var dd_url='';
function fn_dd_add(){
	  $("#dd_form").form('clear');
	  $("#type").textbox('readonly','');
	  $("#dd_form #code").combobox('readonly','');
	  $('#dd_win').window('open');
	  var row = $("#dict_dg").datagrid("getSelected");
	  if(row){
	    $("#dd_form #code").combobox('setValue',row.code);
	  }
	  dd_url='../dictdetail/insert';
}

function fn_dd_upd(){
  var row = $("#dd_dg").datagrid("getSelected");
  if(row){
	  $('#dd_form').form('load',row);
	  $("#type").textbox('readonly','readonly');
	  $("#dd_form #code").combobox('readonly','readonly');
	  $("#type").textbox('setValue',''+row.type);
	  $('#dd_win').window('open');
	  dd_url='../dictdetail/update';
  }else{
	  $.messager.alert({title: '提示',msg: '请选择需要修改的职位'},'info');
  }
}
function fn_dd_del(){
  var row = $("#dd_dg").datagrid("getSelected");
  if(row){	
			$.ajax({  
				url:'../dictdetail/delete',
				type:'post',
				data:{'id':row.id},
				dataType:'json',
				error:function(){
					$.messager.alert("友情提示","系统异常,请稍后尝试","error");
				}, 
				success:function(r){ 
					$.messager.alert(r.title,r.content,r.success,function(){
						if(r.success=='info'){
							$("#dd_dg").datagrid('reload');
						}
					});
				}  
			});
  }else{
	  $.messager.alert({title: '提示',msg: '请选择需要删除的字典数据'},'info');
  }
}
function fn_get_dd(){
	var row = $("#dict_dg").datagrid("getSelected");
    if(row){
  	  
  	  $("#dd_dg").datagrid({
  		url : "../dictdetail/query",
  		queryParams: {
			code:row.code
		}
  	  });
    }
}

function fn_dict_save(){
    $('#dict_form').form('submit',{
        url: dict_url,
        onSubmit: function(){
            return $(this).form('validate');
        },
        success: function(result){;
        	var result = eval('('+result+')');
        	$.messager.alert(result.title,result.content,result.success,function(){
				if(result.success=='info'){
					$('#dict_win').window('close');
					$("#dict_dg").datagrid('reload');
					$("#dd_form #code").combobox('reload','../dict/query');
				}
			});
        }
    });
}
function fn_dd_save(){
    $('#dd_form').form('submit',{
        url: dd_url,
        onSubmit: function(){
            return $(this).form('validate');
        },
        success: function(result){;
        	var result = eval('('+result+')');
        	$.messager.alert(result.title,result.content,result.success,function(){
				if(result.success=='info'){
					$('#dd_win').window('close');
					$("#dd_dg").datagrid('reload');
				}
			});
        }
    });
}
</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'west',split:true" title="字典管理" style="width:50%;">
		<table id="dict_dg" class="easyui-datagrid" data-options="url: '../dict/query',fit:true,fitColumns:true,singleSelect:true,method: 'post',rownumbers: true,toolbar:dict_toolbar,onClickRow:fn_get_dd">
		<thead>
			<tr>
				<th data-options="field:'code',width:220">字典编码</th>
				<th data-options="field:'name',width:200">字典名称</th>
				<th data-options="field:'description',width:200">字典描述</th>
			</tr>
		</thead>
	  </table>
	</div>
	<div data-options="region:'center',title:'字典数据管理'">
	  <table id="dd_dg" class="easyui-datagrid" data-options="fit:true,rownumbers:true,fitColumns : true,singleSelect:true,toolbar:dd_toolbar,onDblClickRow:fn_dd_upd">
		<thead>
			<tr>
				<th data-options="field:'codeName',width:150">字典类</th>
				<th data-options="field:'type',width:100">数据编码</th>
				<th data-options="field:'typeName',width:100">数据名称</th>
				<th data-options="field:'description',width:200">描述</th>
			</tr>
		</thead>
	  </table>
	</div>

	<div id="dict_win" class="easyui-window" title="字典管理" data-options="modal:true,closed:true,iconCls:'icon-edit'" style="height:200px;padding:10px;">
		<form id="dict_form" method="post">  	
		  	<table align="center">
		    	<tr>
	    			<td width="70">字典编码:</td>
	    			<td>
	    			<input class="easyui-textbox" type="text" id="code" name="code" data-options="required:true" style="width:200px"></input>
	    			<input type="hidden" id="id" name="id">
	    			</td>
	    		</tr>
	    		<tr>
	    			<td width="70">字典名称:</td>
	    			<td>
	    			<input class="easyui-textbox" type="text" id="name" name="name" data-options="required:true" style="width:200px"></input>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td width="70">类别描述:</td>
	    			<td>
	    			<input class="easyui-textbox" type="text" id="description" name="description" data-options="required:true,multiline:true" style="width:200px;height:40px"></input>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td colspan="2" align="center">
					  <a href="javascript:void(0)" data-options="iconCls:'icon-save'" class="easyui-linkbutton" onclick="fn_dict_save()">保存</a>
	    			  <a href="javascript:void(0)" data-options="iconCls:'icon-cancel'" class="easyui-linkbutton" onclick="$('#dict_win').window('close');">取消</a>
                    </td>
	    		</tr>
		   	</table>
	   </form>
	</div>
	
	<div id="dd_win" class="easyui-window" title="字典数据管理" data-options="modal:true,closed:true,iconCls:'icon-edit'" style="height:240px;padding:10px;">
		<form id="dd_form" method="post">  	
		  	<table align="center">
		    	
	    		<tr>
	    			<td>字典分类:</td>
	    			<td><input class="easyui-combobox" id="code" name="code" data-options="url:'../dict/query',required:true,panelHeight:'200',editable:false,width:'200',valueField:'code',textField:'name'"/></td>
	    		</tr>
	    		<tr>
	    			<td width="70">编码:</td>
	    			<td>
	    			<input class="easyui-numberbox" type="text" id="type" name="type" data-options="required:true" style="width:200px"></input>
	    			</td>
	    		</tr>
				<tr>
	    			<td width="70">名称:</td>
	    			<td>
	    			<input class="easyui-textbox" type="text" id="typeName" name="typeName" data-options="required:true" style="width:200px"></input>
	    			<input type="hidden" id="id" name="id">
	    			</td>
	    		</tr>
	    		<tr>
	    			<td width="70">描述:</td>
	    			<td>
	    			<input class="easyui-textbox" type="text" id="description" name="description" data-options="required:true,multiline:true" style="width:200px;height:40px"></input>
	    			</td>
	    		</tr>
	    		
	    		<tr>
	    			<td colspan="2" align="center">
					  <a href="javascript:void(0)" data-options="iconCls:'icon-save'" class="easyui-linkbutton" onclick="fn_dd_save()">保存</a>
	    			  <a href="javascript:void(0)" data-options="iconCls:'icon-cancel'" class="easyui-linkbutton" onclick="$('#dd_win').window('close');">取消</a>
                    </td>
	    		</tr>
		   	</table>
	   </form>
	</div>
</body>
</html>