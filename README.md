#Software Development Process Management

本项目此版本的实现参考了禅道的许多功能，在此特表感谢。
#初次安装
初次运行请将application.xml中的databaseInstallerProcessor、initDataApplicationProcessor注释打开，以便进行数据库表结构安装及初始化。
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