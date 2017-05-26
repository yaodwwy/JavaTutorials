<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>login log</title>
<%@ include file="../common/common.jsp"%>
<script type="text/javascript">
$(function(){
	$('#rolelayout_search').panel({
		fit:true,
		collapsible:true,
		title: '查询条件',
		onCollapse:function(){
			$('#north_search').panel('resize',{ height: 40 });
			$("#rolelayout").layout('resize');
		},
		onBeforeExpand:function(){
			$('#north_search').panel('resize',{ height: 90 });
			$("#rolelayout").layout('resize');
		}
	});
});
function date2str(x, y) {
   var z = {
      y: x.getFullYear(),
      M: x.getMonth() + 1,
      d: x.getDate(),
      h: x.getHours(),
      m: x.getMinutes(),
      s: x.getSeconds()
   };
   return y.replace(/(y+|M+|d+|h+|m+|s+)/g, function(v) {
      return ((v.length > 1 ? "0" : "") + eval('z.' + v.slice(-1))).slice(-(v.length > 2 ? v.length : 2))
   });
}
function fmt_time(value){
	var unixTimestamp = new Date(value);	
	return date2str(unixTimestamp, "yyyy-MM-d h:m:s")
}
function search(){
	$("#datagrid").datagrid({
		url:'../loginlog/query',
		  queryParams: {
				source:$('#sel_source').combobox('getValue')
		  }
	});	
}
</script>

</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="border-bottom-width:1px;">
		<div style="padding:3px; border:0px;" class="panel-header">
				<a id="search" href="javascript:search();" onclick="javascript:search();" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">查询</a>
		</div>
	</div> 

	<div data-options="region:'center',border:false">
		 <div id="tabs" class="easyui-tabs" data-options="plain:true, fit:true, border:false" style="width:100%;height:100%;">
			<div title="登录日志查询" style="padding:0px;">
				<div id="rolelayout" class="easyui-layout" data-options=" fit:true, border:false">
				   
					<div id="north_search" data-options="region:'north',border:false" style="height:90px;padding:10px; padding-bottom: 0px;">
					 	<div id="rolelayout_search" style="padding:10px;" data-options="title:'asdf'"> 
							<table id="match">
								<tr>
									<td>
										<label>日志来源：</label>
										<input class="easyui-combobox" id="sel_source" name="sel_source" data-options="url:'../dictdetail/query?code=login_log_source',panelHeight:'auto',editable:false,width:'60',valueField:'type',textField:'typeName'"/>
									</td>
								</tr>
							</table>    
						</div>   
					</div>   
					
					<div id="center_role" data-options="region:'center',border:false" style="weight:100%;padding:10px;">
					 
						<table id="datagrid" class="easyui-datagrid" data-options="fit:true,striped:true,rownumbers:true,singleSelect:true,url:'../loginlog/query',method:'POST',pagination:true,pageSize:20">
							<thead>
								<tr>
									<th data-options="field:'userCode',width:80,align:'center'">用户工号</th>
									<th data-options="field:'userName',width:80,align:'center'">用户姓名</th>
									<th data-options="field:'sourceName',width:80,align:'center'">来源</th>
									<th data-options="field:'loginTime',width:160,align:'center',formatter:fmt_time">登录时间</th>
									<th data-options="field:'devicenum',width:120,align:'center'">设备号/IP</th>
								</tr>
							</thead>
						</table>
				    </div>    
				    
				</div>  
			</div>
		</div>
	</div>

</body>
</html>