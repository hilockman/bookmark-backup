1修改远程访问权限
登陆：
mysql -h localhost -u root -p
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY 'root' WITH GRANT OPTION;

2，innodb只有一个聚集索引

3，不适合建索引的情况
where条件中用不到的字段
频繁更新的字段
数据值发布比较均匀的不适合建索引
表的数据可以确定行数的，而且

4什么情况下索引失效：
order by

age是索引的情况下
select * from user order by age 索引生效

index(name, age) 复合索引
select * from user order by age 索引失效
select * from user where age=18 and name='zhangfei'索引失效，要按顺序提供查询条件

index(age, name) 复合索引
select * from user where age > 18 and name='zhangfei' 部分失效

### 5 navicat连接mysql8失败
```
ALTER USER 'root'@'localhost' IDENTIFIED BY 'root' PASSWORDEXPIRE NEVER;
ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'root';
```
### 6 [centos安装mysql]<https://www.digitalocean.com/community/tutorials/how-to-install-mysql-on-centos-7>


### 7 mysql远程访问的问题
Your root account, and this statement applies to any account, may only have been added with localhost access (which is recommended).

You can check this with:
```
SELECT host FROM mysql.user WHERE User = 'root';
```
If you only see results with localhost and 127.0.0.1, you cannot connect from an external source. If you see other IP addresses, but not the one you're connecting from - that's also an indication.

You will need to add the IP address of each system that you want to grant access to, and then grant privileges:
```
CREATE USER 'root'@'ip_address' IDENTIFIED BY 'some_pass';
GRANT ALL PRIVILEGES ON *.* TO 'root'@'ip_address';
```
If you see %, well then, there's another problem altogether as that is "any remote source". If however you do want any/all systems to connect via root, use the % wildcard to grant access:
```
CREATE USER 'root'@'%' IDENTIFIED BY 'some_pass';
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%';
```
Finally, reload the permissions, and you should be able to have remote access:
```
FLUSH PRIVILEGES;
```
