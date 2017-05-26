  $(function(){
		//上级菜单
		$('#parentId').combotree({
				url: '../menu/queryParentMenu',
				panelHeight:'200',
				editable:false,
				width:'200'
		});
  });
  
  var toolbar = [{
	  			text:'新增',
	  			iconCls:'icon-add',
	  			handler:fn_add
	  		},'-',{
	  			text:'修改',
	  			iconCls:'icon-edit',
	  			handler:fn_upd
	  		},'-',{
	  			text:'删除',
	  			iconCls:'icon-remove',
	  			handler:fn_del
  			},'-',{
				text:'刷新',
				iconCls:'icon-reload',
				handler:function(){$('#menu_tg').treegrid('reload');}
		    },'-',{
				text:'上移',
				iconCls:'icon-up',
				handler:fn_up
		    },'-',{
				text:'下移',
				iconCls:'icon-down',
				handler:fn_down
		    }];
  
  function fn_down(){
	  var row = $("#menu_tg").treegrid("getSelected");
    if(row){
  	   var sortUrl='../menu/updateMenuSortDown';
         $.post(sortUrl,{id:row.id},function(result){
        		if(result.success=='error'){
					$.messager.alert(result.title,result.content);
				}
  	        	$('#menu_tg').treegrid('reload');
  	        	$('#ac').accordion('reload');
         },'json');  
    }else{
  	  $.messager.alert({title: '提示',msg: '请选择需要移动的菜单'},'info');
    }
}
function fn_up(){
	  var row = $("#menu_tg").treegrid("getSelected");
    if(row){
  	   var sortUrl='../menu/updateMenuSortUp';
         $.post(sortUrl,{id:row.id},function(result){
					if(result.success=='error'){
						$.messager.alert(result.title,result.content);
					}
	  	        	$('#menu_tg').treegrid('reload');
	  	        	$('#ac').accordion('reload');
         },'json');  
    }else{
  	  $.messager.alert({title: '提示',msg: '请选择需要移动的菜单'},'info');
    }
}
  
  function fn_add(){
	  $("#ff").form('clear');
	  $('#w').window('open');
	  url='../menu/insert.do';
  }
  function fn_upd(){
	  var row = $("#menu_tg").treegrid("getSelected");
      if(row){
    	  $('#ff').form('load',row);
    	  $('#w').window('open');
    	  url='../menu/update.do';
    	  $("#imageShow").attr("src", $("#imageUrl").textbox("getValue"));
      }else{
    	  $.messager.alert({title: '提示',msg: '请选择需要修改的菜单'},'info');
      }
  }
  function fn_del(){
	  var row = $("#menu_tg").treegrid("getSelected");
      if(row){
			$.ajax({  
				url:'../menu/delete.do',
				type:'post',
				data:{'id':row.id},
				dataType:'json',
				error:function(){
					$.messager.alert("友情提示","系统异常,请稍后尝试","error");
				}, 
				success:function(r){ 
					$.messager.alert(r.title,r.content,r.success,function(){
						if(r.success=='info'){
							$("#menu_tg").treegrid('reload');
							$('#parentId').combotree({url: '../menu/queryParentMenu',required: true});
						}
					});
				}  
			});
      }else{
    	  $.messager.alert({title: '提示',msg: '请选择需要删除的菜单'},'info');
      }
  }
  var url="";
  function fn_save(){
      $('#ff').form('submit',{
          url: url,
          onSubmit: function(){
              return $(this).form('validate');
          },
          success: function(result){;
          	var result = eval('('+result+')');
          	$.messager.alert(result.title,result.content,result.success,function(){
				if(result.success=='info'){
					$('#w').window('close');
					$("#menu_tg").treegrid('reload');
					$('#parentId').combotree({url: '../menu/queryParentMenu',required: true});
				}
			});
          }
      });
  }
  function fn_fmt_status(val,rec){
		if(val=='1'){
			return "<span style='color:red'>全部</span>";
		}else if(val=='0'){
			return "<span style='color:blue'>仅后台</span>";
		}else{
			return "<span style='color:green'>仅前台</span>";
		}
  }
    
  function fn_parentid_change(){
	  var row = $("#menu_tg").treegrid("getSelected");
	  if(url=='../menu/update.do'&&row){
		var parentId = $("#parentId").combotree("getValue");
		if(parentId==row.id){
			$.messager.alert('提示','不可以选择自己作为上级目录','info',function(){
				$("#parentId").combotree("setValue",row.parentId);
				return;
			});
		}
		var childStr='';
		var children = $("#menu_tg").treegrid('getChildren',row.id);
		$(children).each(function(i,cm){
			if(parentId==cm['id']){
				$.messager.alert('提示','不可以选择下级作为上级目录','info',function(){
					$("#parentId").combotree("setValue",row.parentId);
				});
				return;
			}
		});
	  }
  }
  
//文件上传
  $(function(){
  	$("#uploadButton").bind('click', function(){
          fn_fileUpload('model_file');
      });    
  });
  function fn_fileUpload(str){
  	var url = '../menu/fileUpload';
	var suffixStr = "png,PNG,jpg,JPG,gif,GIF,jpge,JPGE";//限制文件格式
  	var savePath = ",upload,images";//目录设置
  	var parameUrl = url + '?fileObjectId=' + str + '&MaxSize=' + 10240
  					+ '&suffixStr=' + suffixStr + '&savePath=' + savePath 
  					+ '&width='+32+'&height='+32;
  	$.ajaxFileUpload({
  		url : parameUrl, //用于文件上传的服务器端请求地址
  		secureuri : false, //是否需要安全协议，一般设置为false
  		fileElementId : str, //文件上传域的ID
  		dataType : 'json', //返回值类型 一般设置为json
  		success : function(data, status) {
  			if(data.success=="true"){
  				$.messager.alert('成功','上传完成,请保存','success');
  				var images = "../upload/images/"+data.fileUrl;//与目录设置一至
  				$("#imageUrl").textbox("setValue",images);
  				$("#imageShow").attr("src",images);
  			}else{
  			    alert(data.msg);
  			}
  		},
  		error : function(data, status){
  			alert(data.msg);
  		}
  	})
  }