#Software Development Process Management

本项目此版本的实现参考了禅道的许多功能，在此特表感谢。
#数据库设置
数据库的配置文件在sdpm-web工程中resource中的sdpm.properties
#首次运行
首先在工程根路径使用命令install
```
mvn clean install
```
再进入工程sdpm-web，使用jetty即可运行工程，命令如下
```
mvn jetty:run
```

#如何安装
初次运行请在application.xml中/application/application-processors下添加<application-processor bean="databaseInstallerProcessor"/>进行数据库建表操作。同时请添加 <application-processor bean="initDataApplicationProcessor"/>进行系统数据初始化操作。
此时系统的用户名为admin,密码为123
此外还需将web.beans.xml中bean[sdpmDictLoader]的property[isInitFromFile]置为true，以便进行数据字典初始化。

#相关工程包名约束

biz接口类包名：
org.tinygroup.sdpm.${模块}.biz.inter

biz实现类包名：
org.tinygroup.sdpm.${模块}.biz.impl

tiny服务接口类包名：
org.tinygroup.sdpm.${模块}.service.inter;

tiny服务实现类包名：
org.tinygroup.sdpm.${模块}.service.impl;

table表不需要写包名，元数据生成Java类工具填写包名
org.tinygroup.sdpm.${模块}.dao

生成tiny服务xml中id为 方法名