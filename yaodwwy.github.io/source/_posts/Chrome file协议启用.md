---
title: Chrome file协议启用
date: 2016-12-20 00:00:00
---

Cross origin requests are only supported for protocol schemes: http, data, chrome, chrome-extension, https, chrome-extension-resource. 

跨域请求仅支持协议：http, data, chrome, chrome-extension, https, chrome-extension-resource 
在某些浏览器中是允许这种操作的，比如Firefox浏览器，也就是说Filefox支持file协议下的AJAX请求。 

* 设置Chrome的快捷方式属性，在“目标”后面加上–allow-file-access-from-files，注意前面有个空格，重新打开Chrome即可。

* chrome通过file协议浏览

```
"C:\省略\chrome.exe" --enable-file-cookies --allow-file-access-from-files
```

	
