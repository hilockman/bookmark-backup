# MyBatis学习笔记

---

***目录***


+ [MyBatis架构设计](#MyBatis架构设计)
  - [主要类](#主要类)
  - [配置文件](#配置文件)
+ [程序技巧](#程序技巧)
  - [批量插入](#批量插入)
  - [SQLLIKE](#SQLLIKE)
  - [使用多参数](#使用多参数)


***参考***


* [MyBatis中文](http://www.mybatis.org/mybatis-3/zh/getting-started.html)
* [MyBatis英文](http://www.mybatis.org/mybatis-3/index.html) 
* [GitHub/mybatis/mybatis-3](https://github.com/mybatis/mybatis-3)
+ 博客
   - [MyBatis框架及原理分析](http://www.cnblogs.com/luoxn28/p/6417892.html)
   - [深入理解mybatis原理](http://blog.csdn.net/luanlouis/article/details/40422941)
   - [Mybatis框架整体设计](http://chenjc-it.iteye.com/blog/1460990)
   - [终结篇：MyBatis原理深入解析](https://www.jianshu.com/p/ec40a82cae28)
+ 源码分析
   - [mybatis源码中文注释](https://github.com/tuguangquan/mybatis)
   - [mybatis中文注释并附带图解架构](https://github.com/nero520/mybatis)
---

## MyBatis架构设计

| 功能架构设计 | 类结构图 |
| --- | --- |
| ![功能架构设计][function-graph] | ![类结构图][package-graph] |


[function-graph]:http://img.blog.csdn.net/20141028232313593?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbHVhbmxvdWlz/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast
[package-graph]:http://img.blog.csdn.net/20141028140852531?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbHVhbmxvdWlz/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast


### 主要类

| 类名 | 配置文件 | 最佳作用域 |
| --- | --- | -- |
| SqlSessionFactoryBuilder | xml配置文件 | 方法作用域（也就是局部方法变量） |
| SqlSessionFactory | xml配置文件 | 应用作用域，单例模式或者静态单例模式 |
| SqlSession | xml配置文件 | 请求或方法作用域。 每个线程都应该有它自己的 SqlSession 实例。也绝不能将 SqlSession 实例的引用放在任何类型的管理作用域中，比如 Servlet 架构中的 HttpSession。 |
| Mapper Instances | xml映射文件 | 映射器是创建用来绑定映射语句的接口。映射器接口的实例是从 SqlSession 中获得的。因此从技术层面讲，映射器实例的最大作用域是和 SqlSession 相同的，因为它们都是从 SqlSession 里被请求的。 |

### 主要包
|名称|结构|参考|类图|


### 配置文件

| 名称 | 说明 |
| -- | -- |
| property | 属性 |
| settings | 设置 |
| typeAliases | 类型别名 |
| typeHandlers | 类型处理器 |

### mysql日期查询是出错，配置url
SqlMapConfig.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <!-- 和spring整合后 environments配置将废除-->
    <environments default="development">
        <environment id="development">
            <!-- 使用jdbc事务管理，事务控制由mybatis-->
            <transactionManager type="JDBC" />
            <!-- 数据库连接池,由mybatis管理-->
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.jdbc.Driver" />
                <property name="url" value="jdbc:mysql://192.168.1.70:3306/testmybatis?characterEncoding=utf-8&amp;serverTimezone=UTC" />
                <property name="username" value="root" />
                <property name="password" value="root" />
            </dataSource>
        </environment>
    </environments>

</configuration>
```

## 程序技巧

### 批量插入
sql映射文件
```
<insert id="insertName">
  insert into names (name) values (#{value})
</insert>
```
java代码
```
List<String> names = new ArrayList<String>();
names.add("Fred");
names.add("Barney");
names.add("Betty");
names.add("Wilma");

SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
try {
  NameMapper mapper = sqlSession.getMapper(NameMapper.class);
  for (String name : names) {
    mapper.insertName(name);
  }
  sqlSession.commit();
} finally {
  sqlSession.close();
}
```

### SQLLIKE
java Code
```
String wildcardName = "%Smi%";
List<Name> names = mapper.selectLike(wildcardName);
```
xml
```
String wildcardName = "%Smi%";
List<Name> names = mapper.selectLike(wildcardName);
```

### 使用多参数
java code
```
import org.apache.ibatis.annotations.Param;
public interface UserMapper {
   User selectUser(@Param("username") String username, @Param("hashedPassword") String hashedPassword);
}
```
xml
```
<select id=”selectUser” resultType=”User”>
  select id, username, hashedPassword
  from some_table
  where username = #{username}
  and hashedPassword = #{hashedPassword}
</select>
```


