# JavaMail Listener  开发文档

## 1.开发环境

* IntelliJ IDEA Ultimate 2020.3
* JDK 15.0.2
* SQL Server
* JavaMail 1.6.2
* SQL JDBC 8.4.1.0
* QQ邮箱或OutLook

## 2.实现

### 2.1 实现思路

* 邮箱会收到入职和离职的邮件，邮件中保存了员工的相关信息，因此我们只需要通过监听邮件并且提取邮件内的信息，即可得到所需的全部信息
* 为了对邮件进行操作，我们将收到的入职邮件放在“ Onboarding ” 文件下，离职邮件放在“ Termination ” 文件夹下，因此只需监听邮箱下的这两个文件夹即可
* 邮箱需开启IAMP服务，这样我们可以通过只读取未读邮件并在读取后将其标记为已读，即可得到尚未归档的员工信息
* 程序相当于一个守护进程，在后台每隔固定时间则进行一次监听操作，更新信息
* 当进程发现邮箱内目标文件夹下有未读邮件时，则进行正则表达式匹配得出所需要的员工信息，加入List中
* 将读取后的员工信息存到本地的Excel表格中，并将其同步到数据库中

### 2.2 项目文件结构

#### 2.2.1 文件结构

* .\Setting.ini：配置文件，设置监听的时间间隔，单位：秒，邮箱账号和登录密码
* .\Onboarding.xls：入职员工共信息表
* .\Termination.xls：离职员工信息表
* .\lib：jar包
* .\src：源码

#### 2.2.2 源代码

* JDBCTools.java：用于建立数据库连接，如需后续更改数据库，需更改该文件下的URL，USER，PASSWORD等

```java
//建立连接
public static Connection getConnection(){}

//释放连接
public static void release{}
```

* re.java：正则表达式匹配，用于提取邮件中的相关信息，如果以后收到的邮件有改变，需更改正则表达式

```java
//离职邮件信息提取
public ArrayList<String> TerminationRe(String text){}

//入职邮件信息提取
public ArrayList<String> OnboardingRe(String text) {}
```

* SQL.java：数据库相关操作

```java
//新增入职信息
public static void AddOnboarding(ArrayList<ArrayList<String>> ans){}

//新增离职信息
public static void AddTermination(ArrayList<ArrayList<String>> ans){}
```

* WriteExcel.java：将信息写入本地Excel表格

```java
//写入Excel
public static void toExcel(String FilePath,ArrayList<ArrayList<String>> ans)
```

* test.java：邮箱监听以及程序入口

```java
//周期运行对象
public static void period(String user, String password){}

//程序入口
public static void main( String[] args ){}
```

## 3.注意事项及问题

* 现在用的是QQ邮箱作为测试，更改邮箱需更改test.java中的参数HOST、USER、PASSWORD
* 更改数据库需修稿JDBCTools.java以及SQL.java
* JDBC获取邮箱数据好像有较高的延迟，滞后明显
* QQ邮箱的IAMP暂不支持许多Term进行邮件检索，需注意，可能无法完成匹配
* 使用的邮箱需开启IMAP服务
* 使用前请确定配置文件完整，Time为监听间隔，User为邮箱账号，Password为登录密码

