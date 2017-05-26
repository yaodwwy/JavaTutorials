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

function fmt_status(val){
	if (val == '0') {
		return "<span style='color:blue'>正常</span>";
	} else {
		return "<span style='color:red'>隐藏</span>";
	}
}

//查询
function search1() {
	var name = $("#stitle").val();
	var services=$("#sservice").combobox("getValues").toString();
	 var city=$("#scity").combobox("getValues").toString();
	$("#datagrid").datagrid({
		url : '../fos/querylist.do',
		queryParams : {
			name:name,
			services:services,
			city:city
		},
		pagination : true,
		pageSize :'20'
	});
}
var url = '';
var userId = 0;

//增加
function add() {
	url = '../fos/insert.do';
	$('#mgrForm').form('clear');
	$('#mgrDiv').window('open');
}
//判断手机号长度
function setPhone(phone){
	var pattern=/(^[0-9]{3,4}\-[0-9]{3,8}$)|(^[0-9]{3,8}$)|(^\([0-9]{3,4}\)[0-9]{3,8}$)|(^0{0,1}13[0-9]{9}$)/;
	if(!pattern.test(phone)){
		alert("请正确输入联系方式！");
	return ;
	}
}
	
//修改
function upd() {
	var row = $("#datagrid").datagrid("getSelected");
	if (row) {
		url = '../fos/update.do';
		$('#mgrForm').form('load', row);
		$("#max").attr({"src":row.maxPic,"height":80});
	
		$("#min").attr("src",row.minPic);
		$("#min").attr("height","50");
		
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
					url : '../fos/delete.do',
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
      var services=$("#services").combobox("getValues");
	 var city=$("#city").combobox("getValues");
	  var opening=$("#openning").combobox("getValues");
	   var phone=$("#phone").numberbox('getValue');
	mydata = {
		'id':$("#id").val(),
		'name':$("#name").textbox('getValue'),
		'items':services.toString(),
//		'items':$("#items").textbox('getValue'),
		'phone':phone,
		'city':city.toString(),
		'address':$("#address").textbox('getValue'),
		'opening':opening.toString(),
		'serviceInfo':$("#serviceInfo").val(),
		'maxPic':$("#maxPic").val(),
		'status':$("#status").val()
	};
	$.ajax({
		url:url,
		type:'post',
		data:mydata,
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
				url : '../fos/update.do',
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

function fmt_mainImage(val){
	return "<img src='"+val+"'  height='80px' />";
}	


//文件上传
$(function(){
	$("#uploadMax").bind('click', function(){
        fn_fileUpload('model_file');
    });
});
function fn_fileUpload(str){
	var url = '../muiUpload/fileUpload';
	var suffixStr = "jpg,JPG,gif,GIF,jpge,JPGE";//限制文件格式
	var savePath = ",upload,fosterPic";//目录设置
	var parameUrl = url + '?fileObjectId=' + str + '&MaxSize=' + 10240
					+ '&suffixStr=' + suffixStr + '&savePath=' + savePath
					+ '&width='+960+'&height='+520;
	$.ajaxFileUpload({
		url : parameUrl, //用于文件上传的服务器端请求地址
		secureuri : false, //是否需要安全协议，一般设置为false
		fileElementId : str, //文件上传域的ID
		dataType : 'json', //返回值类型 一般设置为json
		success : function(data, status) {
			if(data.success=='true'){
				var pic = "/mipet/upload/fosterPic/"+data.fileUrl;//与目录设置一至
					$("#maxPic").val(pic);
					$("#max").attr("src",pic);
			}else{
			    alert("失败:"+data.msg);
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			 alert(textStatus+errorThrown);
		}
	})
}