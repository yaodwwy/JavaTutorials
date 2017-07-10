#使用Java实现面向对象编程
-------------

一、抽象和封装总结
----
class    object    static    final    private    public    protect    overloading    constructor    encapsulation

现实世界是“面向对象”的，面向对象就是采用“现象模拟”的方法设计和开发程序。
面向对象技术是目前计算机软件开发中最流行的技术。面向对象设计的过程就是抽象的过程。
类是对某一类事物的描述，是抽象的，概念上的定义。对象是实际存在的该事物的个体，是具体的，现实的。
如果同一个类中包含了两个或两个以上的方法，它们的方法名相同，方法参数个数或参数类型不同，则称该方法被重载了，这个过程称为方法重载。
构造方法用于创建类的对象。构造方法的作用主要就是在创建对象时执行一些初始化操作。可以通过构造方法重载来实现多种初始化行为。
封装就是将类的成员属性声明为私有的，同时提供公有的方法实现对该成员属性的存取操作。
封装的好处主要有：隐藏类的实现细节；让使用者只能通过程序员规定的方法来访问数据；可以方便地加入存取控制语句，限制不合理操作。

二、继承总结
----
inheritance    extend    super    override    constructor    public    abstract    final

继承是Java中实现代码重用的重要手段之一。Java中只支持单继承，即一个类只能有一个直接父类。java.lang.Object类是所有Java类的祖先。
在子类中可以根据实际需求对从父类继承的方法进行重新编写，称为方法的重写或覆盖。
子类中重写的方法和父类中被重写方法必须具有相同的方法名、参数列表，返回值类型必须和被重写方法的返回值类型相同或者是其子类。
如果子类的构造方法中没有通过super显式调用父类的有参构造方法，也没有通过this显示调用自身的其他构造方法，则系统会默认先调用父类的无参构造方法。
抽象类不能实例化。抽象类中可以没有，可以有一个或多个抽象的方法。子类必须重写所有的抽象方法才能实例化，否则子类还是一个抽象类。
用final修饰的类，不能再被继承。用final修饰的方法，不能被子类重写。用final修饰的变量将变成常量，只能赋值一次。

三、多态总结
----
polymorphism  

通过多态可以减少类中代码量，可以提高代码的可扩展性和可维护性。继承是多态的基础，没有继承就没有多态。
把子类转换为父类，称为向上转型，自动进行类型转换。把父类转换为子类，称为向下转型，必须进行强制类型转换。
向上转型后通过父类引用变量调用的方法是子类覆盖或继承父类的方法，通过父类引用变量无法调用子类特有的方法。
向下转型后可以访问子类特有的方法。必须转换为父类指向的真实子类类型，否则将出现类型转换异常ClassCastException。
instanceof运算符通常和强制类型转换结合使用，首先通过instanceof进行类型判断，然后进行相应的强制类型转换。
使用父类作为方法形参是使用多态的常用方法。

-------------------

##1、 JSP有哪几个内置对象，每个对象的作用是什么，并写出常用方法
JSP中一共预先定义了9个这样的对象，分别为：request、response、session、application、out、pagecontext、config、page、exception
request对象代表了客户端的请求信息，主要用于接受通过HTTP协议传送到服务器的数据。（包括头信息、系统信息、请求方式以及请求参数等）。request对象的作用域为一次请求。
Request常用的方法：getParameter(String strTextName) 获取表单提交的信息.
getProtocol() 获取客户使用的协议。
String strProtocol=request.getProtocol(); 
getServletPath() 获取客户提交信息的页面。
getMethod() 获取客户提交信息的方式 
getRermoteAddr() 获取客户的IP地址。
getRemoteHost() 获取客户机的名称。
getServerName() 获取服务器名称。 
getServerPort() 获取服务器的端口号。 
getParameterNames() 获取客户端提交的所有参数的名字。 
​
response 代表的是对客户端的响应，主要是将JSP容器处理过的对象传回到客户端。
response.setContentType(String s); 设置用什么内容类型对客户请求做出响应
response.sendRedirect(index.jsp); 页面重定向
 
session对象是一个JSP内置对象，它在第一个JSP页面被装载时自动创建，完成会话期管理。从一个客户打开浏览器并连接到服务器开始，到客户关闭 浏览器离开这个服务器结束，被称为一个会话。当一个客户访问一个服务器时，可能会在这个服务器的几个页面之间切换，服务器应当通过某种办法知道这是一个客 户，就需要Session对象。
public String getId()：获取Session对象编号。
public void setAttribute(String key,Object obj)：将参数Object指定的对象obj添加到Session对象中，并为添加的对象指定一个索引关键字。
public Object getAttribute(String key)：获取Session对象中含有关键字的对象。
 
application 对象可将信息保存在服务器中，直到服务器关闭，否则application对象中保存的信息会在整个应用中都有效。与session对象相比，application对象生命周期更长，类似于系统的“全局变量”。
setAttribute(String key,Object obj)：将参数Object指定的对象obj添加到Application对象中，并为添加的对象指定一个索引关键字。
getAttribute(String key)：获取Application对象中含有关键字的对象
 
out 对象用于在Web浏览器内输出信息，并且管理应用服务器上的输出缓冲区。在使用 out 对象输出数据时，可以对数据缓冲区进行操作，及时清除缓冲区中的残余数据，为其他的输出让出缓冲空间。
out.print()：输出各种类型数据。
 
pageContext 对象的作用是取得任何范围的参数，通过它可以获取 JSP页面的out、request、reponse、session、application 等对象。page 对象代表JSP本身，只有在JSP页面内才是合法的。 page隐含对象本质上包含当前 Servlet接口引用的变量，类似于Java编程中的 this 指针。
 
config 对象的主要作用是取得服务器的配置信息。通过 pageConext对象的 getServletConfig() 方法可以获取一个config对象。当一个Servlet 初始化时，容器把某些信息通过 config对象传递给这个 Servlet。开发者可以在web.xml 文件中为应用程序环境中的Servlet程序和JSP页面提供初始化参数。
 
 
exception 对象的作用是显示异常信息, 只有在包含 isErrorPage="true" 的页面中才有效
 
##2、 什么是Cookie，有哪些常用方法？ JSP中Session和Cookie的区别是什么，请详细阐述
Cookie是Web服务器保存在用户硬盘上的一段文本。Cookie允许一个Web站点在用户电脑上保存信息并且随后再取回它。Cookie对象的典型应用是用来统计网站的访问人数。由于代理服务器、缓存等的使用，唯一能帮助网站精确统计来访人数的方法就是为每个访问者建立一个唯一ID。使用Cookie，常用方法如下:
setMaxAge(long time); 设置Cookie的有效时间，单位是秒
response.addCookie(c); 将Cookie对象传送到客户端
 
1．Session对象是一个JSP内置对象，它由容器负责创建，Cookie是一个类，它由应用程序代码创建。
2．Session对象保存在服务器端，可以保存Object类型的内容，Cookie对象以文本类型保存在客户端，只能保存字符串内容。
3．Session对象的生命周期开始于客户端第一次请求，结束于客户端关闭页面。Cookie对象的生命周期，开始于Cookie对象的创建，并随着response响应回客户端，在没有使用setAge()方法设置保存时间的情况下，随着Session的结束而结束；在使用setAge()方法设置了保存时间的情况下，只有当时间达到指定时长后，才由客户端自动清理掉 Cookie的文本文件。
4．Sessoin一般存储较为重要的数据。Cookie一般存储不重要，但常用的数据，如简化登录操作，商品信息对比操作。
 
 
##3、 jQuery的选择器有哪几种类型？jQuery基本选择器分为哪几类，各自的特点是什么？
1、 基本选择器
2、 层次选择器
3、 过滤选择器
4、 表单选择器
5、 属性过滤选择器
6、 子元素过滤选择器
 
基本选择器包括：
ID选择器，根据给定的id匹配一个元素，例如：$(‘test’)选取id为test的元素
类选择器，根据给定的类名匹配元素，例如：$(‘.test’)选取所有class为test的元素
元素选择器，根据给定的元素名匹配元素，例如：$(‘p’)选取所有<p>元素
全部选择器，匹配所有元素，例如：$(‘*’)选择所有元素
并集选择器，将每一个选择器匹配到的元素合并后一起返回，例如：$(‘div,span,p.myclass’)选择所有<div>,<span>和拥有class为myclass的<p>元素的一组元素
 
##4、 请详细描述Ajax的工作原理，是如何实现页面无刷新的
AJAX全称为(异步JavaScript和XML），是一种创建交互式网页应用的网页开发技术
Ajax的工作原理相当于在用户和服务器之间加了—个中间层(AJAX引擎),使用户操作与服务器响应异步化。并不​是所有的用户请求都提交给服务器,像—些数据验证和数据处理等都交给Ajax引擎自己来做, 只有确定需要从服​务器读取新数据时再由Ajax引擎代为向服务器提交请求。
 
创建XMLHttpRequest对象，该步骤是最为复杂的，需要针对不同的浏览器建立不同的对象；
注册回调函数，当请求状态改变时调用该函数；
设置与服务器的连接信息；
发送数据；
创建回调函数，处理服务器返回的数据，将返回的数据动态地显示在JSP页面上。
 
 
##5、 什么是盒子模型？盒子模型的属性有哪些？它们的作用分别是什么？
在网页中，一个元素占有空间的大小由几个部分构成，其中包括元素的内容，元素的内间距，元素的边框，元素的边界四个部分，四个部分一起构成了css中元素的盒模型
盒子模型的属性包括：
border，用来设置边框的样式，包括：粗细、样式和颜色
padding，用来设置内边距
margin:用来设置外边距
 
 
##6、 JSP中转发和重定向的区别是什么，请详细阐述
转发：request.getRequestDispatcher(url).forward(request,response);
重定向：response.sendRedirect(url);
区别1：转发后浏览器地址栏不变，仍然是发起跳转操作的资源地址，而重定向后改变为目标网页地址
区别2：转发可以共享request作用域中的数据，而重定向不可以
区别3：转发可以访问WEB-INF目录下的资源，而重定向不可以
区别4：重定向可以访问站外资源，比如链接到其他网站，而重定向只能访问本站内部的资源
 
##7、 请阐述MVC架构各个单词的含义，以及运行原理，同时说明在JSP开发中每层的常用实现技术
M：model模型，模型表示企业数据和业务规则
V：view 视图，视图是用户看到并与之交互的界面，负责数据采集和显示
C：controller控制器, 控制器接受用户的输入并调用模型和视图去完成用户的需求，负责整个应用程序的调度
 
首先控制器接收到用户请求，
然后将请求数据发送给模型进行处理，
模型处理后，将处理结果反馈给控制器，
控制器再根据结果选择视图显示
 
在JSP开发中，模型通常由JavaBean充当，视图由JSP充当，控制器由Servlet充当
 
##8、 请描述Servlet的生命周期
web容器加载servlet，生命周期开始。通过调用servlet的init()方法进行servlet的初始化。通过调用service()方法实现，根据请求的不同调用doGet()或doPost()方法。结束服务，web容器调用servlet的destroy()方法
 
##9、 jQuery对象和dom对象是怎样转换的？
两种转换方式将一个jQuery对象转换成DOM对象：[index]和.get(index); 
(1)jQuery对象是一个数据对象，可以通过[index]的方法，来得到相应的DOM对象。
如：
var $v=$("#v") ; //jQuery对象 
var v=$v[0]; //DOM对象 
alert(v.checked) //检测这个checkbox是否被选中
(2)jQuery本身提供，通过.get(index)方法，得到相应的DOM对象
如：
var $v=$("#v"); //jQuery对象 
var v=$v.get(0); //DOM对象 
alert(v.checked) //检测这个checkbox是否被选中
 
DOM对象转成jQuery对象: 
对于已经是一个DOM对象，只需要用$()把DOM对象包装起来，就可以获得一个jQuery对象了。$(DOM对象)
如：var v=document.getElementById("v"); //DOM对象 
var $v=$(v); //jQuery对象 
转换后，就可以任意使用jQuery的方法了。
 
##10、 列出解析XML的常用技术，并阐述各自特点
Java通过DOM解析XML
DOM是基于树形结构的的节点或信息片段的集合，允许开发人员使用DOM API遍历XML树、检索所需数据。分析该结构通常需要加载整个文档和构造树形结构，然后才可以检索和更新节点信息。
使用DOM对XML文件进行操作时，首先要解析文件，将文件分为独立的元素、属性和注释等，然后以节点树的形式在内存中对XML文件进行表示，就可以通过节点树访问文档的内容，并根据需要修改文档。因为整个DOM树已经被载入内存，所以可以随机访问任意节点信息。
 
Java通过SAX解析XML
SAX解析器是一种基于事件的解析器，事件驱动的流式解析方式是，从文件的开始顺序解析到文档的结束，不可暂停或倒退。它的核心是事件处理模式，SAX解析器的优点是解析速度快，占用内存少。

--------------------------------

##一、Spring依赖注入的3种实现方式是什么？
依赖注入的3种实现方式分别是：接口注入（interface injection）、Set注入（setter injection）和构造注入（constructor injection）。接口注入指的就是在接口中定义要注入的信息，并通过接口完成注入。其具体步骤包括（1）编写一个接口,各种数据的注入将通过这个接口进行。（2）任何想要使用数据的类都必须实现这个接口 。Set注入指的就是在接受注入的类中定义一个Set方法，并在参数中定义需要注入的元素。构造注入指的就是在接受注入的类中定义一个构造方法，并在参数中定义需要注入的元素。
 
##二、SSH 框架配置流程是什么？
1.创建java web项目：添加struts包：freemarker，ognl，struts2-core，struts2-spring-plugin，xwork-core，commons-fileupload、commons-io、 commons-lang等 ；添加spring包：spring-aop、spring-aspects、spring-beans、spring-context、spring-core、spring-jdbc、spring-orm、spring-web等；添加hibernate包：hibernate-core等；添加其他包：ojdbc等
2.配置struts（1）配置struts核心过滤器。打开web.xml，配置struts核心过滤器和过滤器映射。（2）建立Action，继承ActionSupport类。
（3）配置action。在struts.xml文件中，添加对Action的配置。包含<package><action><result>等元素的配置（4）新建视图页面
3.配置hibernate：（1）建立数据库与数据表（2）创建持久层实体类及映射文件（3）编写数据访问类，集成HibernateDaoSupport父类
4.配置spring：（1）配置applicationContext.xml文件，配置spring会话工厂bean，数据访问bean ,业务bean和action bean等。（2）引入aop和tx命名空间，配置声明式事务（3）配置web.xml，添加Spring监听器和context-param初始化参数，告知监听器applicationContext.xml的位置。
 
##三、Struts的运行机制是什么
1、客户端初始化一个指向Servlet容器（例如Tomcat）的请求；
2、这个请求经过一系列的过滤器（Filter）（这些过滤器中有一个叫做ActionContextCleanUp的可选过滤器，这个过滤器对于Struts2和其他框架的集成很有帮助，例如：SiteMesh Plugin）；
3、接着StrutsPrepareAndExecuteFilter被调用，StrutsPrepareAndExecuteFilter询问ActionMapper来决定这个请求是否需要调用某个Action
4、如果ActionMapper决定需要调用某个Action，StrutsPrepareAndExecuteFilter把请求的处理交给ActionProxy；
5、ActionProxy通过Configuration Manager询问框架的配置文件，找到需要调用的Action类；
6、ActionProxy创建一个ActionInvocation的实例。
7、ActionInvocation实例使用命名模式来调用，在调用Action的过程前后，涉及到相关拦截器（Intercepter）的调用。
8、一旦Action执行完毕，ActionInvocation负责根据struts.xml中的配置找到对应的返回结果。返回结果通常是 （但不总是，也可能是另外的一个Action链）一个需要被表示的JSP或者FreeMarker的模版。在表示的过程中可以使用Struts2 框架中继承的标签。在这个过程中需要涉及到ActionMapper。
 
##四、说说IOC和AOP的概念以及在Spring中是如何应用的
1.控制反转（Inversion of Control，英文缩写为IoC）是一个重要的面向对象编程的法则来削减计算机程序的耦合问题，也是轻量级的Spring框架的核心。 控制反转一般分为两种类型，依赖注入（Dependency Injection，简称DI）和依赖查找（Dependency Lookup）。依赖注入应用比较广泛。2.AOP为Aspect Oriented Programming的缩写，意为：面向切面编程，通过预编译方式和运行期动态代理实现程序功能的统一维护的一种技术。AOP是OOP的延续。利用AOP可以对业务逻辑的各个部分进行隔离，从而使得业务逻辑各部分之间的耦合度降低，提高程序的可重用性，同时提高了开发的效率。3.IOC是典型的工厂模式，而AOP则是典型的代理模式的体现。spring的IoC容器是spring的核心，spring AOP是spring框架的重要组成部分4.在spring中ioc最典型的应用就是整合并管理各个组件，如整合ssh时，spring可以管理datasource,sessionfactory,dao,service,action等bean,这些传统开发方式中均是由硬编码的方式实现；而aop则在spring中表现为各种通知，spring提供了诸如MethodBeforeAdvice,AfterReturningAdvice,AfterThrowingAdvice等API，并在spring提供的声明式事务中也提供了相应的tx命名空间，利用aop简化事务配置
 
##五、Struts中mvc中分别使用的什么技术？
1.在Action的实现方面，而Struts2中可以是一个POJO。Struts2支持通过拦截器堆栈为每一个Action创建不同的生命周期，其中拦截器在AOP（Aspect-Oriented Programming）中用于在某个方法或字段被访问之前或之后加入某些操作
2.线程模型方面，Struts2的Action是一个请求对应一个实例（每次请求时都新new出一个对象）,没有线程安全方面的问题。
3.Servlet依赖方面，Struts2中的Action不再依赖于Servlet API，有利于测试，并且实现TDD。
4.封装请求参数，Struts2可以选择使用POJO类来封装请求的参数，或者直接使用Action的属性。
5.表达式语言方面，Struts2整合了OGNL（Object Graph NavigationLanguage）。
6.绑定值到视图技术，Struts2使用“ValueStack”技术。
7.类型转换，Struts2中使用OGNL进行转换，可以更方便的使用。
8.Struts2支持重写validate方法或者使用XWork的验证框架。同时也很好的使用了Java的国际化机制
 
##六、Spring是怎么实现事务处理的？
Spring 提供两种事务处理方式，一种是编程式事务处理；一种是声明式事务处理。其中编程式事务处理使用TransactionTemplate进行事务处理（Spring进行commit和rollback）。而Spring 的声明式事务处理，可以说是 Spring AOP的一种实现。因为事务处理是所谓方面（ Aspect ）的一个子集。因此默认情况下，事务处理是利用 Java动态代理机制实现的，这样就必须先定义一个接口，然后再编写实现；而对于没有接口的Javabean ，则通过 CGLIB 实现。这部分是SpringAOP 部分的内容。  其实就是在执行某个业务方法之前添加了tx.beginTran()事务开启，若没有异常，业务方法执行完毕后添加了tx.commitTran()事务提交。若有异常抛出，则添加了tx.rollbackTran();事务回滚。使用Spring?AOP进行事务控制需要具备一定的规则，若是开发人员的业务Service或者说业务Service中的业务方法命名不按照事前定义好的规则进行命名的话，AOP是拦截不到的。
 
##七、谈谈Hibernate的缓存机制
Hibernate的缓存包括Session的缓存和SessionFactory的缓存，其中SessionFactory的缓存又可以分为两类：内置缓存和外置缓存。Session的缓存是内置的，不能被卸载，也被称为Hibernate的第一级缓存。SessionFactory的内置缓存和Session的缓存在实现方式上比较相似，前者是SessionFactory对象的一些集合属性包含的数据，后者是指Session的一些集合属性包含的数据。SessionFactory的内置缓存中存放了映射元数据和预定义SQL语句，映射元数据是映射文件中数据的拷贝，而预定义SQL语句是在Hibernate初始化阶段根据映射元数据推导出来，SessionFactory的内置缓存是只读的，应用程序不能修改缓存中的映射元数据和预定义SQL语句，因此SessionFactory不需要进行内置缓存与映射文件的同步。SessionFactory的外置缓存是一个可配置的插件。在默认情况下，SessionFactory不会启用这个插件。外置缓存的数据是数据库数据的拷贝，外置缓存的介质可以是内存或者硬盘。SessionFactory的外置缓存也被称为Hibernate的第二级缓存。
 
##八、MyBatis和Hibernate有什么区别
1)MyBATIS非常简单易学，Hibernate相对较复杂，门槛较高。2) 二者都是比较优秀的开源产品。但Hibernate现在已经是主流O/R Mapping框架，从文档的丰富性，产品的完善性，版本的开发速度都要强于MyBatis。3) 当系统属于二次开发，无法对数据库结构做到控制和修改，那MyBatis的灵活性将比Hibernate更适合。4) 系统数据处理量巨大，性能要求极为苛刻，在这种情况下MyBatis会有更好的可控性和表现。因为MyBatis比Hibernate更容易进行sql的优化。5) MyBatis 可以进行细粒度的优化，减少与数据库的交互，对于性能的提升是非常重要。6) MyBatis需要手写sql语句，也可以生成一部分，Hibernate则基本上可以自动生成，偶尔会写一些Hql。如果涉及到数据库字段的修改，Hibernate修改的地方很少。7) 开发方面：开发效率上，两者差不多 ；可维护性方面，MyBatis更好一些。8)  运行效率：在不考虑cache的情况下，MyBatis应该会比hibernate快一些或者很多（根据实际情况会有所不同）。 9) 对不同数据库类型的支持。MyBatis对不同数据库类型的支持不够好，如果你要开发的系统是要在对中数据间移植，那可能用hibernate比较好。10)对缺省的cache支持。MyBatis对缺省的cache支持不够好，但是hibernate的cache支持其实也不是很好，而且很复杂。尤其是对于大并发量的应用。
 
##九、Hashmap和Hashtable的区别
1 HashMap不是线程安全的，hastmap是将键映射到值的对象，其中键和值都是对象，并且不能包含重复键，但可以包含重复值。HashMap允许null key和null value，而hashtable不允许。2 .HashTable是线程安全的一个Collection。HashMap是Hashtable的轻量级实现（非线程安全的实现），他们都完成了Map接口，主要区别在于HashMap允许空（null）键值（key）,由于非线程安全，效率上可能高于Hashtable。 HashMap允许将null作为一个entry的key或者value，而Hashtable不允许。 HashMap把Hashtable的contains方法去掉了，改成containsvalue和containsKey。Hashtable继承自Dictionary类，而HashMap是Java1.2引进的Map interface的一个实现。最大的不同是，Hashtable的方法是Synchronize的，而HashMap不是，在多个线程访问Hashtable时，不需要自己为它的方法实现同步，而HashMap 就必须为之提供外同步。 Hashtable和HashMap采用的hash/rehash算法都大概一样，所以性能不会有很大的差异
 
##十、JDBC与Hibernate的优缺点比较
JDBC与Hibernate在性能上相比，JDBC灵活性有优势。而Hibernate在易学性，易用性上有些优势。当用到很多复杂的多表联查和复杂的数据库操作时，JDBC有优势。
相同点：
◆两者都是JAVA的数据库操作中间件。
◆两者对于数据库进行直接操作的对象都不是线程安全的，都需要及时关闭。
◆两者都可以对数据库的更新操作进行显式的事务处理。
 
不同点：
◆使用的SQL语言不同：JDBC使用的是基于关系型数据库的标准SQL语言，Hibernate使用的是HQL(Hibernate query language)语言
◆操作的对象不同：JDBC操作的是数据，将数据通过SQL语句直接传送到数据库中执行，Hibernate操作的是持久化对象，由底层持久化对象的数据更新到数据库中。
◆数据状态不同：JDBC操作的数据是“瞬时”的，变量的值无法与数据库中的值保持一致，而Hibernate操作的数据是可持久的，即持久化对象的数据属性的值是可以跟数据库中的值保持一致的。