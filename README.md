#Software Development Process Management
在线演示地址[http://sdpm.tinygroup.org](http://sdpm.tinygroup.org)

本项目经过禅道作者王春生同意后，参考禅道现有功能模块并进行优化原有功能，提升用户体验，打造以Java为技术体系的项目管理工具，在此对禅道表示衷心感谢！
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
初次运行请在```application.xml```中```/application/application-processors```下添加```<application-processor bean="databaseInstallerProcessor"/>```进行数据库建表操作。同时请添加 ```<application-processor bean="initDataApplicationProcessor"/>```进行系统数据初始化操作。
此时系统的用户名为admin,密码为123
此外还需将```web.beans.xml```中```bean[sdpmDictLoader]```的```property[isInitFromFile]```置为```true```，以便进行数据字典初始化。

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