<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>后台管理系统</title>
<%@ include file="../common/common.jsp"%>
<link rel="shortcut icon" href="/icon.ico"/>
<script type="text/javascript" src="<%=basePath%>js/sys/main.js"></script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:55px;width:100%; margin: 0">
	   <table style="width:100%; height:100%" cellpadding="0" cellspacing="0">
	      <tr>
	      <td>
		      <img src="<%=basePath%>image/logo1.jpg" height="30">
          </td>
          <td width="60" align="right" style="color: #999!important;">登录员工：</td>
          <td width="80" align="left" style="color: #F60!important;">${sessionScope.admin.fullname}</td>
          <td width="60" style="cursor: pointer;color: #999!important;" onclick="fn_updpass()">修改密码</td>
          <td width="10">|</td>
          <td width="40" style="cursor: pointer;color: #999!important;" onclick="fn_logout()">退出</td>
          </tr>
	      <tr><td colspan="6" bgcolor="#0470BC" height="8"></td></tr>
	   </table>
	</div>
	<div data-options="region:'west',split:true,title:'导航菜单'" style="width:200px;" >
		<div class="easyui-accordion" data-options="fit:true,border:false" id="ac">
			<c:forEach items="${menuList}" var="menu">
		    	<div title='${menu.name}'>
					<ul class="easyui-tree" data-options="url:'<%=basePath %>menu/queryMainMenu?parentId=${menu.id}&status=2',method:'post',animate:true,dnd:true"></ul>
				</div>
			</c:forEach>
		</div>
    </div>
	<script type="text/javascript">
		$(function(){
			//$("#ac").accordion('getSelected').panel('collapse');
		})
        $('.easyui-tree').tree({ 
             onClick: function(node){
                 if(node.leaf){
                	    if ($('#tabs-content').tabs('exists', node.text)){  
                	        $('#tabs-content').tabs('select', node.text);  
                	    } else {  
                	        var content = '<iframe scrolling="auto" frameborder="0"  src="../'+node.url+'" style="width:100%;height:100%;"></iframe>';  
                	        $('#tabs-content').tabs('add',{  
                	            title:node.text,
                	            //href:node.url,
                	            content:content,  
                	            closable:true
                                //iconCls: node.iconCls
                	        });  
                	    }  
                 }
             } 
        });
    </script>
		<div data-options="region:'south',split:true,border:false" style="height: 30px;overflow:hidden;">
		<div class="panel-header" style="border: none;text-align: center;" >CopyRight &copy; 版权所有. &nbsp;&nbsp;</div>
	</div>
	<div data-options="region:'center'">
		<div id="tabs-content" class="easyui-tabs" data-options="fit:true,border:false,plain:true">
		   <div title="Welcome" style="padding:10px">
		     <!-- <table width="100%" height="100%"><tr><td align="center" valign="middle"><img alt="welcome" src="<%=basePath%>images/main/qc.png"/></td></tr></table> -->
		   </div>
		</div>
	</div>
	<div id="w" class="easyui-window" title="修改密码" data-options="modal:true,closed:true,iconCls:'icon-edit'" style="width:400px;height:220px;padding:10px;">
		<form id="addForm" method="post">
	  		<table align="center" style="margin-top:20px">
	    		<tr style="height: 30px">
	    			<td>旧密码：</td>
	    			<td><input name="adminPassold" id="adminPassold"  type="password" class="easyui-textbox" data-options="width:150,required:true"/></td>
	    		</tr>
	    		<tr style="height: 30px">
	    			<td>新密码：</td>
	    			<td><input name="adminPass" id="adminPass" type="password" class="easyui-textbox" data-options="width:150,required:true"/></td>
	    		</tr>
	    		<tr style="height: 30px">
	    			<td>确认新密码：</td>
	    			<td><input name="adminPassagain" id="adminPassagain" type="password" class="easyui-textbox" data-options="width:150,required:true"/></td>
	    		</tr>
	    		<tr>
	    			<td colspan="2" align="middle" style="padding-left:20px">
	    			  <a href="javascript:void(0)" data-options="iconCls:'icon-save'" class="easyui-linkbutton" onclick="fn_save()">保存</a>
	    			  <a href="javascript:void(0)" data-options="iconCls:'icon-cancel'" class="easyui-linkbutton" onclick="$('#w').window('close');">取消</a>
	    			</td>
	    		</tr>
	   		</table>
		</form>
	</div>
	
</body>
</html>