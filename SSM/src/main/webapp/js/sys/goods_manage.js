CKEDITOR.editorConfig = function( config ) {
	config.toolbar =
		[
		{ name: 'document', items: ['Source', '-', 'Preview'] },
		{ name: 'clipboard', items: ['Cut', 'Copy', 'Paste', 'PasteText', 'PasteFromWord', '-', 'Undo', 'Redo'] },
		{ name: 'styles', items: ['Styles', 'Format', 'Font', 'FontSize'] },
		{ name: 'colors', items: ['TextColor', 'BGColor'] },
		{ name: 'tools', items: ['About'] },
		'/',
		{ name: 'basicstyles', items: ['Bold', 'Italic', 'Underline', 'Strike', 'Subscript', 'Superscript', '-', 'RemoveFormat'] },
		{ name: 'paragraph', items: ['NumberedList', 'BulletedList', '-', 'Outdent', 'Indent', '-', 'Blockquote', 'CreateDiv', '-', 'JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock', '-', 'BidiLtr', 'BidiRtl'] },
		{ name: 'links', items: ['Link', 'Unlink', 'Anchor'] },
		{ name: 'insert', items: ['Image', 'Flash', 'Table', 'HorizontalRule', 'SpecialChar', 'PageBreak',] }
		
	];
	config.filebrowserImageUploadUrl = '../upload/CKImgUpload';
	config.height=250;
};

function fmt_type(val){
	if(val=='0'){
		return "<span style='color:red;'>淘宝</span>";
	}else{
		return "<span style='color:green;'>微商</span>";
	}
}

function fmt_price(val){
	return "<span style='color:red;font-weight: bold;'>"+val+"</span>";
}

function fmt_mainImage(val){
	return "<img src='"+val+"'  height='120px' />";
}

function fmt_link(val){
	return "<a href='"+val+"'>"+val+"</a>";
}

function fmt_status(val){
	if(val==1){
		return "<span style='color:red;'>未显示</span>";
	}else{
		return "<span style='color:green;'>正常显示</span>";
	}
}

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
	return date2str(unixTimestamp, "yyyy-MM-d h:m:s")
}
function fmt_status(val,rec){
	if(val=='1'){
		return "<span style='color:blue'>可用</span>";
	}else{
		return "<span style='color:red'>禁用</span>";
	}
}

//查询
function search1() {
	var title = $("#stitle").val();
	var city=$("#scity").combobox("getValues").toString();
	var type=$("#stype").val();
	$("#datagrid").datagrid({
		url : '../goods/querylist.do',
		queryParams : {
			title:title,
			city:city,
			type:type
		},
		pagination : true,
		pageSize :'20'
	});
}
var url = '';
var userId = 0;

//增加
function add() {
	url = '../goods/insert.do';
	$('#mgrForm').form('clear');
	$("#imageShow").attr("src","");
	CKEDITOR.instances.editor1.setData('');
	$('#mgrDiv').window('open');
}

	
//修改
function upd() {
	var row = $("#datagrid").datagrid("getSelected");
	if (row) {
		CKEDITOR.instances.editor1.setData(row.htmlContent);
		url = '../goods/update.do';
		$('#mgrForm').form('load', row);
		$("#imageShow").attr("src",row.mainImage);
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
					url : '../goods/delete.do',
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
			'title':$("#title").textbox('getValue'),
			'types':$("#types").val(),
			'postPrice':$("#postPrice").textbox('getValue'),
			'goodsPrice':$("#goodsPrice").textbox('getValue'),
			'position':$("#position").textbox('getValue'),
			'mainImage':$("#mainImage").textbox('getValue'),
			'goodsLink':$("#goodsLink").textbox('getValue'),
			'enabled':$("#enabled").val(),
			'htmlContent':CKEDITOR.instances.editor1.getData(),
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
	if (row.enabled == "1") {
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

function change_status(enabled) {
	var row = $("#datagrid").datagrid("getSelected");
	if (row) {
		$.messager.confirm('提示','确认操作?',function(r) {
		if (r) {
			$.ajax({
				url : '../goods/update.do',
				type : 'post',
				data : {
					'id' : row.id,
					'enabled' : enabled,
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
	var url = '../menu/fileUpload';
	var suffixStr = "jpg,JPG,gif,GIF,jpge,JPGE";//限制文件格式
	var savePath = ",upload,goodImages";//目录设置
	var parameUrl = url + '?fileObjectId=' + str + '&MaxSize=' + 10240
					+ '&suffixStr=' + suffixStr + '&savePath=' + savePath 
					+ '&width='+800+'&height='+800;
	$.ajaxFileUpload({
		url : parameUrl, //用于文件上传的服务器端请求地址
		secureuri : false, //是否需要安全协议，一般设置为false
		fileElementId : str, //文件上传域的ID
		dataType : 'json', //返回值类型 一般设置为json
		success : function(data, status) {
			if(data.success=="true"){
				var images = "/mipet/upload/goodImages/"+data.fileUrl;//与目录设置一至
				$("#mainImage").textbox("setValue",images);
				$("#imageShow").attr("src",images);
				//window.location.protocol+"//"+window.location.host+
			}else{
			    alert(data.msg);
			}
		},
		error : function(data, status){
			alert(data.msg);
		}
	})
}