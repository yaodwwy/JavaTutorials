
$(function(){
	init();
});
//显示方式//展开或折叠
var isCollapse = false;
function displayType(){
	if(isCollapse){
		$('.easyui-panel').panel('expand',true);
		$('#expandAll').linkbutton({text:"全部折叠"});
		isCollapse = false;
	}else{
		$('.easyui-panel').panel('collapse',true);
		$('#expandAll').linkbutton({text:"全部展开"});
		isCollapse = true;
	}
}
/* CK Editer */
CKEDITOR.editorConfig = function( config ) {
	config.toolbar =
	[ { name: 'document', items: ['Source', '-', 'Preview'] },
		{ name: 'insert', items: ['Image', 'Flash', 'Table', 'HorizontalRule', 'SpecialChar', 'PageBreak',] },
		{ name: 'clipboard', items: ['Cut', 'Copy', 'Paste', 'PasteText', 'PasteFromWord', '-', 'Undo', 'Redo'] },
		{ name: 'styles', items: ['Styles', 'Format', 'Font', 'FontSize'] },
		{ name: 'links', items: ['Link', 'Unlink', 'Anchor'] },
		{ name: 'colors', items: ['TextColor', 'BGColor'] },
		"/",
		{ name: 'basicstyles', items: ['Bold', 'Italic', 'Underline', 'Strike', 'Subscript', 'Superscript', '-', 'RemoveFormat'] },
		{ name: 'paragraph', items: ['NumberedList', 'BulletedList', '-', 'Outdent', 'Indent', '-', 'Blockquote', 'CreateDiv', '-', 'JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock', '-', 'BidiLtr', 'BidiRtl'] }
	];
	config.filebrowserImageUploadUrl = '../upload/CKImgUpload';
};
function init(){
	$.ajax({
		type:'GET',
		url:'../sdk/query',
		dataType : 'json',
		success:function(data){
			$("#sdkName").textbox('setValue',data.sdkName);
			$("#version").textbox("setValue",data.version);
			$("#sdkDownload").attr('href','../sdk/download');
			$("#url").textbox("setValue",data.url);
			CKEDITOR.instances.sdkContent.setData(data.content);
			CKEDITOR.instances.sdkinfo.setData(data.sdkinfo);
			CKEDITOR.instances.sdkinfoEx.setData(data.sdkinfoEx);
		}
	});
}

//保存按钮触发
function fn_appSave() {
	var sdkName = $("#sdkName").textbox('getValue');
	var version = $("#version").textbox('getValue');
	var appUrl = $("#url").textbox('getValue');
	if($("#appForm").form('validate')){
		$.ajax({  
			url:'../sdk/update',
			type:'post',
			data:{'sdkName':sdkName,
				'version':version,
				'url':appUrl,
				'content':CKEDITOR.instances.sdkContent.getData(),
				'sdkinfo':CKEDITOR.instances.sdkinfo.getData(),
				'sdkinfoEx':CKEDITOR.instances.sdkinfoEx.getData()},
			dataType:'json',
			error:function(){
				$.messager.alert("友情提示","系统异常,请稍后尝试","error");
			}, 
			success:function(r){ 
				$.messager.alert(r.title,r.content,r.success);
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
	var url = '../sdk/fileUpload';
	var suffixStr = "rar,RAR,zip,ZIP,jar,JAR";//限制文件格式
	var savePath = ",upload,sdk";//目录设置
	var parameUrl = url + '?fileObjectId=' + str + '&MaxSize=' + 10240
					+ '&suffixStr=' + suffixStr + '&savePath=' + savePath
	$.ajaxFileUpload({
		url : parameUrl, //用于文件上传的服务器端请求地址
		secureuri : false, //是否需要安全协议，一般设置为false
		fileElementId : str, //文件上传域的ID
		dataType : 'json', //返回值类型 一般设置为json
		success : function(data, status) {
			if(data.success=="true"){
				$.messager.alert('成功','上传完成,请保存','success');
				var newSdkUrl = getRootPath()+"/upload/sdk/"+data.fileUrl;
				$("#url").textbox("setValue",newSdkUrl);
				//给sdk链接赋值
				$("#sdkDownload").css('display','inline');
				var sdkLink = $("#url").textbox('getValue');
				var start = sdkLink.lastIndexOf('.');
				var sdkExtendName = sdkLink.substring(start,sdkLink.length);
				var nameStart = $("#sdkName").textbox("getValue");
				var nameMiddle = $("#version").textbox("getValue");
				var linkName = (nameStart!=''?nameStart:'sdk')+sdkExtendName;
			}else{
			    alert(data.msg);
			}
		},
		error : function(data, status){//服务器响应失败处理函数
			alert(data.msg);
		}
	})
}
function getRootPath(){
    var curWwwPath=window.document.location.href;
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    var localhostPaht=curWwwPath.substring(0,pos);
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return(localhostPaht+projectName);
}
