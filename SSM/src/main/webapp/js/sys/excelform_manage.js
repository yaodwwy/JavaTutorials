/**
 * 文件上传
 */
$(function(){
	init();
	$("#uploadButton").bind('click', function(){
		if($("#model_file").val()!=""){
			fn_ExcelUpload('model_file');
		}else{
			fn_update();
		}
    }); 
});
function init(){
	$.ajax({
		type:'GET',
		url:'../excel/query',
		dataType : 'json',
		success:function(data){
			$("#name").textbox('setValue',data.name);
			$("#url").textbox("setValue",data.url);
			$("#email").textbox("setValue",data.email);
			$("#download").attr('href','../excel/download');
			$("#address").html(data.url);
			$("#download").html("点击下载");
		}
	});
}
function fn_ExcelUpload(str){
	var url = '../excel/fileUpload';
	var suffixStr = "xls,XLS,xlsx,XLSX";//限制文件格式
	var savePath = ",upload,excel";//目录设置
	var parameUrl = url + '?fileObjectId=' + str + '&MaxSize=' + 10240
					+ '&suffixStr=' + suffixStr + '&savePath=' + savePath
	$.ajaxFileUpload({
		url : parameUrl, //用于文件上传的服务器端请求地址
		secureuri : false, //是否需要安全协议，一般设置为false
		fileElementId : str, //文件上传域的ID
		dataType : 'json', //返回值类型 一般设置为json
		success : function(data, status) {
			if(data.success=="true"){
				var downloadLink = window.location.protocol+"//"+window.location.host+"/adam/upload/excel/"+data.fileUrl;//与目录设置一至
				$("#url").textbox("setValue",downloadLink);
				//上传完成之后进行更新操作
				fn_update();
			}else{
			    alert(data.msg);
			}
		},
		error : function(data, status){//服务器响应失败处理函数
			alert(data.msg);
		}
	})
}
function fn_update(){
	$.ajax({
		type:'post',
		url:'../excel/update',
		data:{
			name:$("#name").textbox("getValue"),
			url:$("#url").textbox("getValue"),
			email:$("#email").textbox("getValue")
		},
		dataType : 'json',
		success:function(r){
			$.messager.alert(r.title,r.content,r.success);
			init();
		},error:function(r){
			$.messager.alert(r.title,r.content,r.success);
		}
		
	});
}