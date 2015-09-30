package org.tinygroup.sdpm.system.dictinit;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.tinygroup.beancontainer.BeanContainerFactory;
import org.tinygroup.sdpm.system.biz.impl.DictManagerImpl;
import org.tinygroup.sdpm.system.biz.impl.ModuleManagerImpl;
import org.tinygroup.sdpm.system.biz.inter.DictManager;
import org.tinygroup.sdpm.system.biz.inter.ModuleManager;
import org.tinygroup.sdpm.system.dao.pojo.SystemDict;
import org.tinygroup.sdpm.system.dao.pojo.SystemModule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangll13383 on 2015/9/28.
 */
//@Component
public class DictUtil{
    private static   Map<String,List<SystemDict>> dictMap = new HashMap<String, List<SystemDict>>();
    private static boolean isStarted = false;
    private static DictManager dictManager = BeanContainerFactory.getBeanContainer(DictUtil.class.getClassLoader()).getBean(DictManagerImpl.class);
    private static ModuleManager moduleManager = BeanContainerFactory.getBeanContainer(DictUtil.class.getClassLoader()).getBean(ModuleManagerImpl.class);
    public static void init(){
        if(!isStarted) {
            SystemModule systemModule = new SystemModule();
            systemModule.setModuleType("dict");
            List<SystemModule> moduleList = moduleManager.findByModules(systemModule);
            mergeDict(moduleList);
            isStarted = true;
        }
    }
    public static  List<SystemDict> getDict(String dictType){
        return dictMap.get(dictType);
    }

//    public void afterPropertiesSet() throws Exception {
//        init();
//    }

    private static void mergeDict(List<SystemModule> systemModules){
        for(SystemModule systemModule : systemModules){
           {
               SystemDict dict = new SystemDict();
               dict.setModuleId(systemModule.getModuleId());
               List<SystemDict> dicts = dictManager.findList(dict,"dict_sort",true);
               if(dicts != null&&dicts.size()>0){
                   dictMap.put(systemModule.getModuleName(),dicts);
               }
            }
        }
    }

}
