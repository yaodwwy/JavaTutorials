#该仓库主要记录学习JavaTutorials中一些Demo
----------------------------------------
执行 

gradle projects 命令会为你列出子项目名称列表.

gradle -q tasks --all 获取任务信息 【--all】获得更多的任务信息

gradle help --task someTask 可以显示指定任务的详细信息

gradle dependencies 命令会列出项目的依赖列表

gradle -q SpringAction:dependencies --configuration testCompile 
//指定项目“SpringAction”
//--configuration 参数来查看 指定构建任务的依赖情况

gradle properties 可以获取项目所有属性列表
//gradle -q api:properties

--profile 参数可以收集一些构建期间的信息并保存到 build/reports/profile 目录下

标准项目属性
Name	Type	Default Value
project	Project	Project 实例对象
name	String	项目目录的名称
path	String	项目的绝对路径
description	String	项目描述
projectDir	File	包含构建脚本的目录
build	File	projectDir/build
group	Object	未具体说明
version	Object	未具体说明
ant	AntBuilder	Ant实例对象