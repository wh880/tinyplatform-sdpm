#Software Development Process Management
在线演示地址[http://sdpm.tinygroup.org](http://sdpm.tinygroup.org)

本项目参考市面上各类项目管理软件，对各类产品中现有功能模块进行优化，提升用户体验，打造以Java为技术体系的项目管理工具！
#数据库设置
数据库的配置文件在\sdpm-web\src\main\resources中的jdbc.properties
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
首先在mysql数据库中创建数据库tinysdpm，并设置数据库编码UTF-8。

初次运行请在```application.xml```中```/application/application-processors```下添加```<application-processor bean="databaseInstallerProcessor"/>```进行数据库建表操作。同时请添加 ```<application-processor bean="initDataApplicationProcessor"/>```进行系统数据初始化操作。
此时系统的用户名为admin,密码为123
此外还需将```\sdpm-common\base-controller\src\main\resources\web.beans.xml```中```bean[sdpmDictLoader]```的```property[isInitFromFile]```置为```true```，以便进行数据字典初始化。

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


sc 192.168.48.111 9191
ar 192.168.48.111

as1 192.168.48.112 9021
as2 192.168.48.113 9022
as3 192.168.48.114 9023
as4 192.168.48.114 9024