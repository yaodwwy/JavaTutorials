var statebar, toolbar, logbox, info, lastTalkId, totalMemoryDom, freeMemoryDom, maxMemoryDom, usedMemoryDom, 
connectorCountDom, startupDom, workStyleDom, maxLogCount = 100,loginDom;
var uId,uName,uHeadPic,tId,tName,tHeadPic,time;
function windowResize() {
	var offset = 2;
	var other = statebar.offsetHeight + toolbar.offsetHeight + offset + 100;
	logbox.style.height = document.documentElement.clientHeight - other + 'px';
}
function init() {
	statebar = document.getElementById("statebar");
	toolbar = document.getElementById("toolbar");
	logbox = document.getElementById("logbox");
	//发送的文本内容
	info = document.getElementById("info");
	uId = document.getElementById("uId");
	uName = document.getElementById("uName");
	uHeadPic = document.getElementById("uHeadPic");
	tId = document.getElementById("tId");
	tName = document.getElementById("tName");
	tHeadPic = document.getElementById("tHeadPic");
	//系统健康
	totalMemoryDom = document.getElementById("totalMemory");
	freeMemoryDom = document.getElementById("freeMemory");
	maxMemoryDom = document.getElementById("maxMemory");
	usedMemoryDom = document.getElementById("usedMemory");
	connectorCountDom = document.getElementById("connectorCount");
	workStyleDom = document.getElementById("workStyle");
	startupDom = document.getElementById("startup");
	loginDom = document.getElementById("login");
	windowResize();
	window.onresize = windowResize;
	//引擎事件绑定
	JS.Engine.on({
		start : function(cId, aml, engine) {
			var style = engine.getConnector().workStyle;
			style = style === 'stream'?'长连接':'长轮询';
			workStyleDom.innerHTML = style;
		},
		stop : function(cause, url, cId, engine) {
			setTimeout(start,2000);
		},
		t : function(data, timespan, engine) {
			switch (data.type) {
			case 'talk':
				onMessage(data, timespan);
				break;
			case 'health':
				onHealthMessage(data, timespan);
				break;
			default:
			}
		}
		
	});
	
	/* 需要修改变量名才能使用:*/
	var paramsUrl = window.location.search;
	if(paramsUrl){
		uId.value = decodeURI(paramsUrl.split("uId=")[1].split("&")[0]);//登录用户ID
		uName.value = decodeURI(paramsUrl.split("uName=")[1].split("&")[0]);//登录用户名
		uHeadPic.value= decodeURI(paramsUrl.split("uHeadPic=")[1].split("&")[0]);//头像
		uName.value = uName.value ? uName.value.trim() : '匿名用户';
	}
	setCookie('uId', uId.value, 365);
	setCookie('uName', uName.value, 365);
	setCookie('uHeadPic', uHeadPic.value, 365);
	start();
}
//开启连接
function start(){
	JS.Engine.start('conn');
}
//打印对象
function writeObj(obj){ 
	 var description = ""; 
	 for(var i in obj){ 
	  var property=obj[i]; 
	  description+="\""+i+"\":\""+property+"\",";
	 } 
	 var str = "{"+description.substring(0,description.length-1)+"}::~";
	 return str;
	}
//发送聊天信息动作
function send(text,tId) {
	if (!JS.Engine.running){
		alert("发送失败,请检查网络连接!");
		return;
	}
	var myId = uId.value;
	if(myId == tId){
		alert("不能与自己对话!");
		return;
	}
	text = text.trim();
	if (!text){
		alert("请输入消息内容!");
		return;
	}
	var id = JS.Engine.getId();
	var param = "id=" + id + '&text=' + encodeURIComponent(text)+'&tId='+tId;
	//判断是否在线
	JS.AJAX.post('talk.do?cmd=online', param, function(data) {
		//如果在线,直接发送
		if(data.responseText=="true"){
			JS.AJAX.post('talk.do?cmd=talk', param);
		//如果不在线,则发离线
		}else{
			param = "id=" + uId.value + '&text=' + encodeURIComponent(text)+'&tId='+tId;
			JS.AJAX.post('msg/insert.do?', param);
			document.getElementById("isOnline").innerHTML = "<span style='color:red;'>对方不在线,已转至留言!</span>";
		}
		//加载到页面上
		var li = document.createElement("li");
		li.className = "pet-chatbox-right";
		li.innerHTML = '<div class="pet-chat-left"><span>' +uId.value+": "+ text + '</span></div>';
		logbox.appendChild(li);
		info.value = '';
	});
}
// 用户聊天通知
function onMessage(data, timespan) {
	//储存聊天信息
	var msgInfo = new Object();
	msgInfo.uId = data.userId;//消息发送者
	msgInfo.tId = getCookie('uId');//接收消息时,当前用户为目标ID
	msgInfo.info = data.text;
	msgInfo.time = data.transtime;
	var uidTemp = getCookie('uId');
	//加载到消息列队中,供父级操作
	document.getElementById("msgInfo").innerHTML += writeObj(msgInfo);
		//加载到页面上
		var li = document.createElement("li");
		li.className = "pet-chatbox-right";
		li.innerHTML = '<div class="pet-chat-left"><span>' +data.userId+": "+ data.text + '</span></div>';
		logbox.appendChild(li);
		info.value = '';
		
	var str = msgInfo.info;
	var id = data.id;
	var name = data.name || '';
	name = name.HTMLEncode();
	var text = data.text || '';
	text = text.HTMLEncode();
	var t = data.transtime;
	checkLogCount();
	lastTalkId = id;
	moveScroll();
}
// 系统健康信息
function onHealthMessage(data, timespan) {
	var totalMemory = data.totalMemory;
	var freeMemory = data.freeMemory;
	var maxMemory = data.maxMemory;
	var usedMemory = data.usedMemory;
	var startup = data.startup;
	var connectorCount = data.connectorCount + '个';
	totalMemoryDom.innerHTML = totalMemory + 'M';
	freeMemoryDom.innerHTML = freeMemory + 'M';
	maxMemoryDom.innerHTML = maxMemory + 'M';
	usedMemoryDom.innerHTML = usedMemory + 'M';
	connectorCountDom.innerHTML = connectorCount;
	startupDom.innerHTML = startup;
}

// 检测输出长度
function checkLogCount() {
	var count = logbox.childNodes.length;
	if (count > maxLogCount) {
		var c = count - maxLogCount;
		for ( var i = 0; i < c; i++) {
			logbox.removeChild(logbox.firstChild);
		}
	}
}
// 移动滚动条
function moveScroll() {
	logbox.scrollTop = logbox.scrollHeight;
	info.focus();
}
// 回车事件
function onSendBoxEnter(event) {
	if (event.keyCode == 13) {
		var text = info.value;
		send(text, tId.value);
		return false;
	}
}


// 设置Cookie
function setCookie(name, value, expireDay) {
	var exp = new Date();
	exp.setTime(exp.getTime() + expireDay * 24 * 60 * 60 * 1000);
	document.cookie = name + "=" + encodeURIComponent(value) + ";expires="
			+ exp.toGMTString();
}
// 获得Cookie
function getCookie(name) {
	var arr = document.cookie
			.match(new RegExp("(^| )" + name + "=([^;]*)(;|$)"));
	if (arr != null)
		return decodeURIComponent(arr[2]);
	return null;
}
// 删除Cookie
function delCookie(name) {
	var exp = new Date();
	exp.setTime(exp.getTime() - 1);
	var cval = getCookie(name);
	if (cval != null)
		document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
}
// HTML编码
String.prototype.HTMLEncode = function() {
	var temp = document.createElement("div");
	(temp.textContent != null) ? (temp.textContent = this)
			: (temp.innerText = this);
	var output = temp.innerHTML;
	temp = null;
	return output;
};
// HTML解码
String.prototype.HTMLDecode = function() {
	var temp = document.createElement("div");
	temp.innerHTML = this;
	var output = temp.innerText || temp.textContent;
	temp = null;
	return output;
};
String.prototype.trim = function() {
	return this.replace(/^\s+|\s+$/g, '');
};