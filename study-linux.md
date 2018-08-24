1 rhel7安装
boot 分区：引导分区 ，系统启动，grub（引导器）， 内核
swap 分区：内存扩展分区给多大， 一般最多8~16G
/ 根：所有文件的根
sda 第一块磁盘
RHEL7默认使用xfs文件系统，RHEL6默认使用ext4

2 vmware 快照

3 linux清屏ctrl+l

4 setup命令进行系统配置
  NetworkManager进行网络服务配置
  nmtui修改网络配置，前提NetworkManager.service启动了
  
  如果命令唯一，按tab可已补齐命令，按两个tab列出所有命令
  
  systemctrl status NetworkManager
  查看服务状态
  systemctrl status NetworkManager
  启动服务
  
5 防火墙
查看防火墙状态
 systemctl status firewalld.service
关闭防火墙状态
systemctl stop firewalld.service
禁止开机启动
systemctl disable firewalld.service

6 selinux
临时关闭
setenforce 0
永久关闭
vim /etc/sysconfig/selinux
改为
SELINUX=disabled

7 vim
撤销操作
ESC，u

8开机自动挂在光驱
永久挂载
vim /etc/fstab
添加
/dev/cdrom   /mnt    iso9660 defaults        0 0
mount -a 验证挂载点

df查看
*(确保虚拟机右下角光盘图标被点亮，否则系统无法启动）

9 yum源

配置文件地址在以下目录
/etc/yum.repos.d/

vim rhel7.repo
[rhel7-server] 源名称，如果多个yum源，yum名称必须唯一
name=rhel-server 描述信息
baseurl=file:///mnt （系统盘）文件路径
enabled=1  启动
gpgcheck=0 取消验证

清空所有yum
yum clean

列出所有yum
yum list

10 1U 指4.445cm
diy 1u服务器
华硕主板Z8NA-D6C 1块1850元
Intel CPU E5620 两颗 2170*2=4340元
希捷1TB ST31000524NS企业级硬盘/SATA/32M/3年 2块 1350*2=2700
现代DDR31333 REG 8G服务器内存 480*2=960元
华南工控1U机箱 320元
全汉FSP400-601UG400W 1U服务器电源 80PLUS认证 1U电源 1个530元
捷豹1U775涡轮风扇 100*2= 200元
合计：10900元
优惠价：10800元

11 终端
tty1-tty6 控制台终端
	tty1为图形界面（刚进入的为图形终端）
	tty2-tty6 为字符终端
	图形界面切换到字符界面终端：Ctrl+Alt+F2-6
	字符界面切换到字符界面终端：Alt+F2-6
	字符界面切换图形界面：Alt+F1
pts虚拟终端
   新建虚拟终端：Ctrl+Shift+T
   放大虚拟终端：ctrl+shift++
   缩小：ctrl+-
   切换：Alt+数字键
   清屏：ctrl+l
   关闭：Alt+F4
常看当前进程 w
查看进程
    ps -aux | grep pts
远程连接linux服务器 ssh
    ssh root@192.168.3.71
	退出
	  exit
ls 为列出目录
   ll 为list -l 的缩写
   ls -h 按人类可读的方式列出文件大小
   ls -a 列出隐藏文件
   -l 详细信息
   -d 只显示指定目录
   文件颜色含义：
      蓝色 目录
	  黑色 文件
	  浅蓝色 链接
	  红色 压缩包
	  黑底黄色 设备文件

date 时间命令
     -s 指定时间	
        date -s 21:03:44
hwclock bios时间
设置锁屏时间 设置->电源

12 关机 shutdown init reboot poweroff
LAN 局域网 VXLAN 用在openstack，跨物理网络 
shutdown
  关机，重启，定时关机
  -r 重启计算机
  -h 关机
  -h+时间 定时关机
init
   init 0-6
   linux7个运行级别：
	0 系统停机
	1 单用户模式
	2 多用户模式
	3 完整的多用户文本模式
	4系统未使用
	5图形化模式
	6重启模式
切换到3级别
	systemctl isolate multi-user.target
	或
	systemctl isolate runlevel3.target	
切换到5级别
	systemctl isolate graphical.target	  或者 systemctl isolate runlevel5.target
设置默认级别
	systemctl set-default graphical.target
获得默认级别
	systemctl get-default
查看运行级别
    runlevel

13 系统文件目录
/boot 存放Linux启动时需要加载的文件。Kernel、grub等文件
/var 是可增长的目录，包含很经常变的文件
/tmp 临时文件，会定期清除
/sbin 系统命令，
/usr 表示 unix software resource 
	/usr/src 源代码
/dev 包含设备文件
	linux中，一切都被看为文件，终端设备和磁盘都被看做文件如/dev/sda
/proc 这个目录下是一个虚拟目录，他是系统内存的映射，可通过直接访问目录获取系统信息，看看内存cpu信息
    cat /proc/meminfo | grep "Mem"
/lib 存放系统的库文件
		lib库包括动态和静态库，lib***.a是静态库，lib***.so是动态库
/bin 二进制命令
/sbin 系统命令，
/mnt 挂载目录
/home 普通用户数据
/root 系统管理员目录
*本地管理员大多数情况下将额外的软件安装在/usr/local目录下，并符号连接在/usr/local/bin下的主执行程序。
 系统的所有设置在/etc目录下。
 大多数工具和应用程序安装在目录：/bin,/usr/sbin,/sbin

相对路径
绝对路径

mkdir -p test/a/b 创建父级目录
cat 查看文件
more 分页查看，只能往后翻
less 分页查看，可以往前翻，按光标键
head 默认看文件前10行
   head -n 数字  显示多少行
tail 默认查看文件后10行
	tail -f  动态显示文件内容更新
	 tail -f /var/log/messages
	   service network restart实验看messages文件输出
cp -r 复制目录	   
rm -rf 递归强行删除

14 vim
which vim 查看vim绝对路径
rpm -qf /usr/bin/vi 查看版本
rpm -ivh /mnt/Packages/vim-enchanced-7.4.160-1.el7.x86_64.rpm

命令模式下常用：
	i 当前字符前插入（光标前）
	I 行首插入
	a 当前字符后插入
	A 行位插入
	o 下一行插入
	O 上一行插入
	x 向后删除一个字符
	X向前删除一个字符
	u撤销一步
	home键或^行首
	end或者$行尾
	dd删除一行 数字+dd删除多行
	yy复制一行 数字+yy复制多行
	p 将复制行粘贴
	d+Home（或者^) 删除到行首
	d+End(或$) 删除到行尾
	w 切换单词
	  dw 删除一个词
	  yw 复制一个词
	gg定位到行首
	G定位到最后一行，行首
	数字+gg 定位到某一行

	
	块操作，进入v模式 移动光标选择区域
	编程时要进行多行注释：
	1 注释：ctrl+v进入列编辑模式
	2 向下或向上移动光标
	3 把需要注释的行开头标记起来
	4然后按大写的I
	5再插入注释符号‘#’；
	6再按Esc就会全部注释
	
	删除多行注释
	ctr+v 进入列模式，上下移动光标，选中注释，按d，删除注释符号

 命令行模式
  set nu  设置行号
  set nonu
  /正向查找 
  :noh 取消高亮
  
15 xmanager使用
xhell 下 rz上传文件  
远程打开图形程序
  gnome-terminal
  firefox
 
16 用户
useradd 用户名
-u UID
-d 宿主目录
-g 启示组
-G 附加组
-s 登录shell
/etc/passwd每个字段的作用
	例如：root:x:0:0:root:/root:/bin/bash
	用户名:密码占位符:UID:GID:用户描述:用户目录(bash中de '-'代表那个）:登录后使用的shell
	
17 开源网盘owncloud

18 删除程序
yum remove docker*

19 nmtui 修改主机名称
使修改生效，通过hostnamectl
systemctl restart systemd-hostnamed
查看修改后的状态
hostnamectl status

20 使用xshell执行图形程序
编辑修改 /etc/ssh/ssh_config
    ForwardX11 yes
安装xauth	
	yum install xauth  	
远程服务器 
 ssh -X root@cm01 	

21 三步实现SSH无密码登录
到 /root/.ssh目录
ssh-keygen -t rsa
id_rsa 私钥
id_rsa.pub 公钥
拷贝
ssh-copy-id 目标机器
第一次提示输入目标机器的账号密码 

22 scp拷贝文件
scp test.tar host:/usr/local
scp file hostname:`pwd`
 
### 23 安装图形系统
yum groupinstall “X Window System” 
yum groupinstall “GNOME Desktop” 
(卸载yum groupremove +组包名即可）


不行的话系统升级下：
yum upgrade
init 5

最靠谱步骤
1. Install CentOS-7 - Minimal (First entry point in list)
2. yum groupinstall "X Window System"
3. yum install gnome-classic-session gnome-terminal nautilus-open-terminal control-center liberation-mono-fonts
4. unlink /etc/systemd/system/default.target
5. ln -sf /lib/systemd/system/graphical.target /etc/systemd/system/default.target
6. reboot


### 24 增加root权限
修改 /etc/sudoers 文件，找到下面一行，把前面的注释（#）去掉
## Allows people in group wheel to run all commands
wheel    ALL=(ALL)    ALL


### 25 后台运行java程序

执行命令
```
nohup java -jar aaa.jar &
```
然后回车

也可以指定日志文件文件名随意（如：aaa.log）
```
nohup java -jar aaa.jar > aaa.log 2>&1 &
```
查看进程可以使用 

``` ps -ef|grep 'java -jar'

运行后，在当前路径下会生成nohup.out文件，会记录服务器的日志。
