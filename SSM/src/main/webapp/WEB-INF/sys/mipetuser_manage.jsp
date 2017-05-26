<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>job manage</title>
<%@ include file="../common/common.jsp"%>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="border-bottom-width: 1px;">
		<a id="del" href="javascript:void(0)" onclick="javascript:del();" class="easyui-linkbutton" 
		data-options="iconCls:'icon-remove',plain:true,disabled:true">删除</a>
		<a id="start" href="javascript:void(0)" onclick="javascript:change_status(0);" 
		class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true,disabled:true">启用</a>
		<a id="stop" href="javascript:void(0)" onclick="javascript:change_status(1);" 
		class="easyui-linkbutton" data-options="iconCls:'icon-tip',plain:true,disabled:true">禁用</a>
	</div>

	<div data-options="region:'center',border:false">
		<div id="tabs" class="easyui-tabs" data-options="plain:true, fit:true, border:false, title:false" style="width: 100%; height: 100%;">
			<div style="padding: 0px;" title="注册用户管理">
				<div id="rolelayout" class="easyui-layout" data-options=" fit:true, border:false">
					<div id="north_search" data-options="region:'north',border:false" style="height: 90px; padding: 10px; padding-bottom: 0px;">
						<div id="rolelayout_search" style="padding: 10px;" data-options="title:'注册用户管理'">
							<table id="match">
								<tr>
									<td><label>手机：</label> <input class="easyui-textbox" id="phone" name="phone" /></td>
									<td><label>昵称：</label> <input class="easyui-textbox" id="nickname" name="nickname" /></td>
									<td><a id="search" href="javascript:void(0)" onclick="javascript:search1();" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">查询</a></td>
								</tr>
							</table>
						</div>
					</div>

					<div id="center_role" data-options="region:'center',border:false" style="weight: 100%; padding: 10px;">

						<table id="datagrid" class="easyui-datagrid" data-options="fit:true,striped:true,singleSelect:true,url:'../mipet/queryall.do',
							method:'POST',onClickRow:choose,remoteSort:false,pagination:true,pageSize:20">
							<thead>
								<tr>
									<th data-options="field:'headPic',width:80,align:'center',formatter:fmt_head">头像</th>
									<th data-options="field:'nickname',width:160,align:'center'" sortable="true">昵称</th>
									<th data-options="field:'phone',width:160,align:'center'" sortable="true">手机</th>
									<th data-options="field:'gender',width:80,align:'center',formatter:fmt_gender" sortable="true">性别</th>
									<th data-options="field:'birthday',width:160,align:'center',formatter:fmt_date" sortable="true">生日</th>
									<th data-options="field:'family',width:160,align:'center'" sortable="true">家族</th>
									<th data-options="field:'loginIp',width:160,align:'center'" sortable="true">登录IP</th>
									<th data-options="field:'regTime',width:160,align:'center',formatter:fmt_date" sortable="true">注册时间</th>
									<th data-options="field:'updateTime',width:160,align:'center',formatter:fmt_time" sortable="true">最后修改时间</th>
									<th data-options="field:'enabled',width:80,align:'center',formatter:fn_fmt_status" sortable="true">状态</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<img alt="" src="" width="35">
	<script type="text/javascript">
		$(function() {
			$('#rolelayout_search').panel({
				fit : true,
				collapsible : true,
				title : '查询条件',
				onCollapse : function() {
					$('#north_search').panel('resize', {
						height : 40
					});
					$("#rolelayout").layout('resize');
				},
				onBeforeExpand : function() {
					$('#north_search').panel('resize', {
						height : 90
					});
					$("#rolelayout").layout('resize');
				}
			});
		});

		function date2str(x, y) {
			var z = {
				y : x.getFullYear(),
				M : x.getMonth() + 1,
				d : x.getDate(),
				h : x.getHours(),
				m : x.getMinutes(),
				s : x.getSeconds()
			};
			return y.replace(/(y+|M+|d+|h+|m+|s+)/g, function(v) {
				return ((v.length > 1 ? "0" : "") + eval('z.' + v.slice(-1)))
						.slice(-(v.length > 2 ? v.length : 2))
			});
		}
		function fmt_time(value) {
			var unixTimestamp = new Date(value);
			return date2str(unixTimestamp, "yyyy-MM-dd h:m:s")
		}
		function fmt_date(value) {
			if (value == null) {
				return "<span style='color:green;'>未设置</span>";
			} else {
				var unixTimestamp = new Date(value);
				return date2str(unixTimestamp, "yyyy-MM-dd")
			}
		}
		function choose() {
			$('#upd').linkbutton('enable');
			$('#del').linkbutton('enable');
			var row = $("#datagrid").datagrid("getSelected");
			if (row.enabled == "1") {
				$('#start').linkbutton('enable');
				$('#stop').linkbutton('disable');
			} else {
				$('#start').linkbutton('disable');
				$('#stop').linkbutton('enable');
			}
		}
		function fn_fmt_status(val, rec) {
			if (val == '0') {
				return "<span style='color:blue'>正常</span>";
			} else {
				return "<span style='color:red'>禁用</span>";
			}
		}
		function fmt_gender(val) {
			if (val == '1') {
				return "男";
			} else {
				return "女";
			}
		}
		function fmt_head(value) {
			if (value != null) {
				return "<img alt='头像' width='35' src='"+value+"'>";
			}
		}
		function search1() {
			$("#datagrid").datagrid({
				url : '../mipet/queryall.do',
				queryParams : {
					nickname : $('#nickname').textbox('getValue'),
					phone : $('#phone').textbox('getValue')
				},
				pagination : true,
				pageSize : '20'
			});
		}
		
		function del() {
			var row = $("#datagrid").datagrid("getSelected");
			if (row) {
				$.messager.confirm('提示','确认操作?',function (r) {
					if (r) {
						$.ajax({
							url : '../mipet/delete.do',
							type : 'post',
							data : {
								'id' : row.id
							},
							dataType : 'json',
							error : function () {
								$.messager.alert("友情提示","系统异常,请稍后尝试","error");
							},
							success : function (r) {
								$.messager.alert("温馨提示",r.info,r.success,function () {
									if (r.success) {
										$("#datagrid").datagrid('reload');
									}
								});
							}
						});
					}
				});
			} else {
				$.messager.alert("友情提示", "请选择需要操作的数据", "info");
			}
		}

		function change_status(enabled) {
			var row = $("#datagrid").datagrid("getSelected");
			if (row) {
				$.messager.confirm('提示','确认操作?',function(r) {
				if (r) {
					$.ajax({
						url : '../mipet/updateStatus.do',
						type : 'post',
						data : {
							'id' : row.id,
							'enabled' : enabled
						},
						dataType : 'json',
						error : function() {$.messager.alert("友情提示","系统异常,请稍后尝试","error");},
						success : function(r) {
							$.messager.alert("提醒",r.info,r.success,function() {
							if (r.success) {
								$("#datagrid").datagrid('reload');}
							});}
						});}});
						} else {
							$.messager.alert("友情提示", "请选择需要操作的数据", "info");
						}
		}
	</script>
</body>

</html>