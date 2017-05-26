function fmt_image(val){
	return "<img src='"+val+"' height='120px' />";
}

function fmt_status(val){
	if(val=='1'){
		return "<span style='color:red;'>未显示</span>";
	}else{
		return "<span style='color:green;'>正常显示</span>";
	}
}

var url = '';

//增加
function add() {
	url = '../mipetimage/insert.do';
	$('#mgrForm').form('clear');
	$("#imageShow").attr("src","");
	$('#mgrDiv').window('open');
}

	
//修改
function upd() {
	var row = $("#datagrid").datagrid("getSelected");
	if (row) {
		url = '../mipetimage/update.do';
		$('#mgrForm').form('load', row);
		$("#imageShow").attr("src",row.imageUrl);
		$('#mgrDiv').window('open');
	} else {
		$.messager.alert("友情提示", "请选择需要操作的数据", "info");
	}
}

//删除
function del() {
	var row = $("#datagrid").datagrid("getSelected");
	if (row) {
		$.messager.confirm('提示', '确认操作?', function(r) {
			if (r) {
				$.ajax({
					url : '../mipetimage/delete.do',
					type : 'post',
					data : {
						'id' : row.id
					},
					dataType : 'json',
					error : function() {
						$.messager.alert("友情提示", "系统异常,请稍后尝试", "error");
					},
					success : function(r) {
						$.messager.alert("温馨提示", r.info, r.success,
								function() {
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

//取消
function cancel() {
	$('#mgrDiv').window('close');
}

//保存
function save() {
	$.ajax({
		url:url,
		type:'post',
		data:{
			'id':$("#id").val(),
			'imageUrl':$("#imageUrl").val(),
			'imageLink':$("#imageLink").textbox('getValue'),
			'status':$("#status").val()
		},
		error:function(){
			$.messager.alert("友情提示", "系统异常,请稍后尝试", "error");
		},
		success:function(arr){
			$.messager.alert("友情提示", "保存成功", "success");
			$('#mgrDiv').window('close');
			$("#datagrid").datagrid('reload');
		}
	})
}

//按钮选中时
function choose() {
	$('#upd').linkbutton('enable');
	$('#del').linkbutton('enable');
	var row = $("#datagrid").datagrid("getSelected");
	if (row.status == "1") {
		$('#start').linkbutton('enable');
		$('#stop').linkbutton('disable');
	} else {
		$('#start').linkbutton('disable');
		$('#stop').linkbutton('enable');
	}
}
function fmt_status(val){
	if (val == '0') {
		return "<span style='color:blue'>正常</span>";
	} else {
		return "<span style='color:red'>隐藏</span>";
	}
}

function change_status(status) {
	var row = $("#datagrid").datagrid("getSelected");
	if (row) {
		$.messager.confirm('提示','确认操作?',function(r) {
		if (r) {
			$.ajax({
				url : '../mipetimage/update.do',
				type : 'post',
				data : {
					'id' : row.id,
					'status' : status,
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


//文件上传
$(function(){
	$("#uploadButton").bind('click', function(){
        fn_fileUpload('model_file');
    });    
});
function fn_fileUpload(str){
	var url = '../muiUpload/fileUpload';
	var suffixStr = "jpg,JPG,gif,GIF,jpge,JPGE";//限制文件格式
	var savePath = ",upload,mipetImages";//目录设置
	var parameUrl = url + '?fileObjectId=' + str + '&MaxSize=' + 10240
					+ '&suffixStr=' + suffixStr + '&savePath=' + savePath 
					+ '&width='+960+'&height='+640;
	$.ajaxFileUpload({
		url : parameUrl, //用于文件上传的服务器端请求地址
		secureuri : false, //是否需要安全协议，一般设置为false
		fileElementId : str, //文件上传域的ID
		dataType : 'json', //返回值类型 一般设置为json
		success : function(data, status) {
			if(data.success=="true"){
				var images = "/mipet/upload/mipetImages/"+data.fileUrl;//与目录设置一至
				$("#imageUrl").val(images);
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