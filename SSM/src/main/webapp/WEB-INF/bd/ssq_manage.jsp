<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>ssq manage</title>
<%@ include file="../common/common.jsp"%>
<script type="text/javascript" src="<%=basePath%>js/bd/ssq_manage.js"></script>
</head>
<body class="easyui-layout">
	<div data-options="region:'west',split:true" title="省" style="width:33%;">
		<table id="province_dg" class="easyui-datagrid" data-options="url: '../province/query',toolbar:tb_prov,fit:true,fitColumns:true,singleSelect:true,method: 'post',rownumbers: true,onClickRow:fn_get_city,onDblClickRow:function(){fn_upd('province');}">
		<thead>
			<tr>
			    <th data-options="field:'code',width:100">省份编码</th>
			    <th data-options="field:'spell',width:100">拼音缩写</th>
				<th data-options="field:'name',width:100">省份名称</th>
			</tr>
		</thead>
	  </table>
	</div>
	<div data-options="region:'center',title:'市'" style="width:33%;">
	  <table id="city_dg" class="easyui-datagrid" data-options="fit:true,rownumbers:true,toolbar:tb_city,fitColumns:true,singleSelect:true,onClickRow:fn_get_region,onDblClickRow:function(){fn_upd('city');}">
		<thead>
			<tr>
				<th data-options="field:'code',width:100">城市编码</th>
				<th data-options="field:'spell',width:100">拼音缩写</th>
				<th data-options="field:'name',width:100">城市名称</th>
			</tr>
		</thead>
	  </table>
	</div>
	<div data-options="region:'east',title:'区县'" style="width:33%;">
	  <table id="region_dg" class="easyui-datagrid" data-options="fit:true,rownumbers:true,toolbar:tb_region,fitColumns : true,singleSelect:true,onDblClickRow:function(){fn_upd('region');}">
		<thead>
			<tr>
				<th data-options="field:'code',width:100">区县编码</th>
				<th data-options="field:'spell',width:100">拼音缩写</th>
				<th data-options="field:'name',width:100">区县名称</th>
			</tr>
		</thead>
	  </table>
	</div>
	<div id="tb_prov" style="height:auto"> 
		<table>
			    <tr>
				    <td>
				        <a class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="fn_add('province')">新增</a>
				    </td>
				    <td>    
				        <div class="datagrid-btn-separator"></div>
				    </td>
				    <td>
				        <a class="easyui-linkbutton" iconCls="icon-detail" plain="true" onclick="fn_del('province')">删除</a>
				    </td>
				    <td>    
				        <div class="datagrid-btn-separator"></div>
				    </td>
				    <td>
				        <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" onclick="fn_reload('province')">刷新</a>
				    </td>
				    <td>    
				        <div class="datagrid-btn-separator"></div>
				    </td>
		        </tr>
		</table> 
	</div>
	<div id="tb_city" style="height:auto"> 
		<table>
			    <tr>
				    <td>
				        <a class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="fn_add('city')">新增</a>
				    </td>
				    <td>    
				        <div class="datagrid-btn-separator"></div>
				    </td>
				    <td>
				        <a class="easyui-linkbutton" iconCls="icon-detail" plain="true" onclick="fn_del('city')">删除</a>
				    </td>
				    <td>    
				        <div class="datagrid-btn-separator"></div>
				    </td>
				    <td>
				        <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" onclick="fn_reload('city')">刷新</a>
				    </td>
				    <td>    
				        <div class="datagrid-btn-separator"></div>
				    </td>
		        </tr>
		</table> 
	</div>
	<div id="tb_region" style="height:auto"> 
		<table>
			    <tr>
				    <td>
				        <a class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="fn_add('region')">新增</a>
				    </td>
				    <td>    
				        <div class="datagrid-btn-separator"></div>
				    </td>
				    <td>
				        <a class="easyui-linkbutton" iconCls="icon-detail" plain="true" onclick="fn_del('region')">删除</a>
				    </td>
				    <td>    
				        <div class="datagrid-btn-separator"></div>
				    </td>
				    <td>
				        <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" onclick="fn_reload('region')">刷新</a>
				    </td>
				    <td>    
				        <div class="datagrid-btn-separator"></div>
				    </td>
		        </tr>
		</table> 
	</div>
	<div id="win" class="easyui-window" title="省市区县编辑" data-options="modal:true,minimizable:false,closed:true,iconCls:'icon-detail'" style="height:250px;width:260px;padding:10px">
 		<form id="ff" method="post">
   			<table cellpadding="5">
	  			<tr><td>编码:</td><td><input class="easyui-numberbox" id="code" name="code" style="width:150px" /></td></tr>
	  			<tr><td>名称:</td><td><input class="easyui-textbox" id="name" name="name" style="width:150px"/></td></tr>
	  			<tr><td>编码:</td><td><input class="easyui-textbox" id="spell" name="spell" style="width:150px" /></td></tr>
	  			<tr><td colspan="2" align="center">
	  				<input type="hidden" id="id" name="id"/>
	  				<a onclick="fn_save()" class="easyui-linkbutton" iconCls="icon-save" plain="false">保存</a>
			 		<a onclick="$('#win').window('close')" class="easyui-linkbutton" iconCls="icon-cancel" plain="false">关闭</a>
	  			</td></tr>
	  		</table>
	  	</form>
	</div>
</body>
</html>