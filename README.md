#sdpm

warpper工程的包名：
org.tinygroup.sdpm.${模块}.wrapper

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

生成tiny服务xml中id为 模块名+方法名