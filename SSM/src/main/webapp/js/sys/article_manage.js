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
	config.filebrowserImageUploadUrl = '../muiUpload/CKImgUpload';
	config.height=250;
};
//加载文章评论
function initComment(id){
	$('#commentDatagrid').datagrid({
    url:'../comment/queryList.do',
	data:{articleId:id,page:1,rows:20},
	pagination:true,
	pageSize:20,
	singleSelect:true,
	title:"评论管理",
    columns:[[
		{field:'headPic',title:'头像',width:60,formatter: function(value,row,index){
					return "<img src='"+value+"' width='30px'/>";}},
		{field:'nickname',title:'用户名',width:100,align:'center'},
		{field:'content',title:'评论内容',width:280,align:'left'},
		{field:'pubTime',title:'评论时间',width:180,align:'center',formatter:fmt_time},
		{field:'id',title:'操作',width:150,align:'center',formatter:function(value,row,index){
					return "<input onclick='delComment("+value+")' type='button' value='删除'/>";
		}}
    ]]
	});
	
	$('#commentDatagrid').datagrid('load', {
	    articleId: id
	});
}

function delComment(val){
	$.messager.confirm('提示', '确认操作?', function(r) {
		if (r) {
			$.ajax({
				type:"post",
				url:"../comment/delete.do",
				dataType:'json',
				data:{id:val},
				success:function(r){
					if(r.success){
						$.messager.alert("友情提示", "删除成功", "info");
						$('#commentDatagrid').datagrid('reload');
					}else{
						$.messager.alert("友情提示", "删除失败", "info");
					}
				},error:function(r){
					$.messager.alert("友情提示", "服务器未响应!", "error");
				}
			});
		}
	});
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

function getUserName(val){
	var name = val;
	$.ajax({
		url : '../mipet/queryById.do',
		type : 'post',
		async:false,
		data : {
			'id' : name
		},
		dataType : 'json',
		error : function() {
		},
		success : function(r) {
			name = r.nickname;
		}
	});
	return name;
}

function fmt_type(val){
	switch (val) {
	case 0:
		return "<span style='color:blue'>爱狗专区</span>";
	case 1:
		return "<span style='color:blue'>爱猫专区</span>";
	case 2:
		return "<span style='color:blue'>其它</span>";
	default:
		return "<span style='color:blue'>SOS救助站</span>";
	}
}


//查询
function search1(){
	var title = $("#stitle").val();
	var portion = $("#sPortion").val();
	$("#datagrid").datagrid({
		url : '../article/querylist.do',
		queryParams : {
			title:title,
			portion:portion
		},
		pagination : true,
		pageSize :'20'
	});
}
var url = '';
var userId = 0;

//增加
function add() {
	url = '../article/insert.do';
	$('#mgrForm').form('clear');
	CKEDITOR.instances.editor1.setData('');
	$('#mgrDiv').window('open');
}

	
//修改
function upd() {
	var row = $("#datagrid").datagrid("getSelected");
	if (row) {
		CKEDITOR.instances.editor1.setData(row.htmlContent);
		url = '../article/update.do';
		$('#mgrForm').form('load', row);
		$('#mgrDiv').window('open');
		userId = row.userId;
		initComment(row.id);
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
					url : '../article/delete.do',
					type : 'post',
					data : {
						'id' : row.id,
						'userId':-1
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
			'sysId':$("#id").val(),
			'userId':userId,
			'title':$("#title").textbox('getValue'),
			'portion':$("#portion").val(),
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
				url : '../article/updateshow.do',
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
