---
title: 随机图片API接口
date: 2018-06-08 13:17:00
tag: API接口
---
![测试图片](https://picsum.photos/610/320)

百度上有很多获取一张背景图片的URL接口，替换 URL 即可获取不同服务商提供的精美图片，以下是推荐接口。

MD文档中修改图片大小的方法：
`<div style="width:10px"></div>`

### picsum.photos

> 基本用法: 
`https://picsum.photos/320/180`
宽度和高度放在网址后面
> ![基本用法](https://picsum.photos/320/180)

> 方形图像: 
`https://picsum.photos/180`
> ![方形图像](https://picsum.photos/180)

> 随机图像: 
`https://picsum.photos/300/180/?random`
> ![随机图像](https://picsum.photos/300/180?random)

> 灰度图像:
`https://picsum.photos/g/300/180`
`/g/`路径灰度图像
> ![灰度图像](https://picsum.photos/g/300/180)

> 特定图像: 
`https://picsum.photos/300/180?image=0`
> ![特定图像](https://picsum.photos/300/180?image=0)
> >[点击这里](https://picsum.photos/images)找到所有图像的列表。

> 模糊的图像:
`https://picsum.photos/300/180/?blur`
> ![模糊的图像](https://picsum.photos/300/180/?blur)
>> 通过追加`?blur`到url的末尾来获取模糊的图像。

> 图像修剪:
`https://picsum.photos/300/180/?gravity=east`
> ![图像修剪](https://picsum.photos/300/180/?gravity=west)
>>支持的参数：north, east, south, west, center



### source.unsplash.com

---

> 随机图像 
`https://source.unsplash.com/random`
> ![随机图像](https://source.unsplash.com/random/300x180)

> 每日/每周的照片: 
`https://source.unsplash.com/daily | weekly`
> ![每日/每周的照片](https://source.unsplash.com/daily)

> 随机搜索:
`https://source.unsplash.com/featured/?{KEYWORD},{KEYWORD}`
> ![随机搜索](https://source.unsplash.com/1600x900/?nature,water)

### uploadbeta.com

> 随机搜索词:
`https://uploadbeta.com/api/pictures/random?key={KEYWORD}`
> ![随机搜索词](https://uploadbeta.com/api/pictures/random?key=BingEverydayWallpaperPicture)
>> 支持的key：`BingEverydayWallpaperPicture`    `Tumblr`    `其它`