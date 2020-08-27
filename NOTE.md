### Thmeleaf 模板+Bootstrap

~~~
地址：https://www.thymeleaf.org/
~~~

### 数据校验

~~~
JQuery  Validate 客户端验证  https://jqueryvalidation.org/
JSR303服务端验证   https://developer.ibm.com/zh/articles/j-lo-jsr303/
~~~

### 安全设计
~~~
前端使用JQUERY MD5 加盐加密传输
后端二次加密   加盐加密存储数据库
~~~

### 提升springboot应用性能
~~~
1.更改加载的配置方式

//下面的优化springboot的加载：只要引入Positive matches下面的类   Negative matches 不要匹配  只能启动更快
//@Configuration
//@Import({
	
//})


2.使用更小的Jetty 
~~~