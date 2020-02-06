 
### 为什么使用线程池
    1. jdbc底层没有用线程池.操作数据库需要频繁的创建和关闭连接，消耗很大的资源
    2. 不利于维护
    3. 使用PreparedStatement预编译的话对变量进行设置123数字，序号不利于维护
    4. 返回的result结果集也需要硬编码
     
### mybatis 介绍：
    Mybatyis:Object relation mapping 对象关系映射

官方地址：
    https://mybatis.org/mybatis-3/getting-started.html
Getting started（入门）：    

### 快速开始 mybatis（xml 方式）：
    Every MyBatis application centers around an instance of SqlSessionFactory. 
    A SqlSessionFactory instance can be acquired by using the SqlSessionFactoryBuilder. 
    SqlSessionFactoryBuilder can build a SqlSessionFactory instance from an XML 
    configuration file, or from a custom prepared instance of the Configuration class.
    Building a SqlSessionFactory instance from an XML file is very simple. 
    It is recommended that you use a classpath resource for this configuration, 
    but you could use any InputStream instance, including one created from a literal file path or a file:// URL. 
    MyBatis includes a utility class, called Resources, 
    that contains a number of methods that make it simpler to load resources from the classpath and other locations.
    
    每个MyBatis应用程序都围绕一个SqlSessionFactory实例。可以使用SqlSessionFactoryBuilder获取SqlSessionFactory实例。
    SqlSessionFactoryBuilder可以从XML配置文件或配置类的自定义准备实例生成SqlSessionFactory实例。
    从XML文件构建SqlSessionFactory实例非常简单。建议对此配置使用类路径资源，但可以使用任何InputStream实例，
    包括从文本文件路径或file://URL创建的实例。MyBatis包含一个名为Resources的实用程序类，
    它包含许多方法，这些方法使从类路径和其他位置加载资源更加简单。
 ```java   
    String resource = "org/mybatis/example/mybatis-config.xml";
    InputStream inputStream = Resources.getResourceAsStream(resource);
    SqlSessionFactory sqlSessionFactory =
      new SqlSessionFactoryBuilder().build(inputStream);
```
#### 1. maven
```java  
<dependency>
  <groupId>org.mybatis</groupId>
  <artifactId>mybatis</artifactId>
  <version>x.x.x</version>
</dependency>
```  
#### 2. mybatis-config.xml

#### 3. Mapper.xml
##### Mybatis 全局配置详解： 
    mybatis-config.xml 中全局变量配置 ，可以通过properties中设置
|  属性名 |   作用|
| ------------ | ------------ |
|  属性（properties）  | 系统属性占用配置  |
|  设置（settings）  |  用于修改mybatis的运行时行为 |
|  类型别名（typeAliases）   | 为类型建立别名，一般使用更短的名称替代  |
|  类型处理器（typeHenders） |  用于将预编译语句（PreparedStatement）或结果集（ResultSet）中的JDBC类型转换成Java类型|
|  对象工厂（objectFactory）  |  提供默认构造器或者执行构造参数初始化目标类型的对象 |
|  插件（plugins）  |  mybatis 提供插件的方式来拦截映射 |
|  环境（environments） |   mybatis 容许配置多个环境 |
|  数据库标识提供商（databaseIdProvider） | 数据库  |
|  SQL映射文件（mappers）  |   sql映射文件 |
        

#### Mybatis 之 annotation： 
```java

public interface UserMapper {   
    @Select("select  from user where id=#{id}")   
    public User selectUser(Integer id); } 
 
<mapper class="com.jiagouedu.mybatis.mapper.UserMapper"></mapper> 

```

#### Mybatis 之注解和 xml 优缺点:

    Xml：增加 xml 文件. 麻烦. 条件不确定. 容易出错，特殊字符转义 
    注释：不适合复杂 sql，收集 sql 不方便，重新编译 
    
#### Mybatis 之#与$区别： 
    参数标记符号 
    1. #预编译，防止 sql 注入(推荐) 
    2. $可以 sql 注入，代替作用 
#### Mybatis 之 parameterType 与 parameterMap 区别： 
    通过 parameterType 指定输入参数的类型，类型可以是简单类型、hashmap、pojo 的包装 类型 
 
#### Mybatis 之 resultType 与 resultMap 区别： 
    使用 resultType 进行输出映射，只有查询出来的列名和 pojo 中的属性名一致，该列才可以 映射成功。 
    mybatis 中使用 resultMap 完成高级输出结果映射。 
    
#### Mybatis 之 plugin：    
    com.mybatis.plugin.SqlPrintIntercetor
      
       