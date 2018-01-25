# 总结

*常用地址 <http://www.mybatis.org/mybatis-3/zh/>

*常用地址 <http://www.cnblogs.com/luoxn28/p/6417892.html>

## 架构设计

![包结构图](http://img.blog.csdn.net/20141028140852531?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbHVhbmxvdWlz/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

主要类

| 类名 | 配置文件 | 最佳作用域 |
| --- | --- | -- |
| SqlSessionFactoryBuilder | xml配置文件 | 法作用域（也就是局部方法变量） |
| SqlSessionFactory | xml配置文件 | 应用作用域，单例模式或者静态单例模式 |
| SqlSession | xml配置文件 | 请求或方法作用域。 每个线程都应该有它自己的 SqlSession 实例。也绝不能将 SqlSession 实例的引用放在任何类型的管理作用域中，比如 Servlet 架构中的 HttpSession。 |
| Mapper Instances | xml映射文件 | 映射器是创建用来绑定映射语句的接口。映射器接口的实例是从 SqlSession 中获得的。因此从技术层面讲，映射器实例的最大作用域是和 SqlSession 相同的，因为它们都是从 SqlSession 里被请求的。 |

xml配置文件文件中的元素

| 名称 | 说明 |
| -- | -- |
| property | 属性 |
| settings | 设置 |
| typeAliases | 类型别名 |
| typeHandlers | 类型处理器 |

## mysql日期查询是出错，配置url
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
