package org.tinygroup.sdpm.system.dao.impl.util;

import org.tinygroup.beancontainer.BeanContainerFactory;
import org.tinygroup.sdpm.system.dao.SystemModuleDao;
import org.tinygroup.sdpm.system.dao.impl.SystemModuleDaoImpl;
import org.tinygroup.sdpm.system.dao.pojo.SystemModule;

import java.util.List;

/**
 * Created by wangll13383 on 2015/10/13.
 */
public class ModuleUtil{

    private static SystemModuleDao systemModuleDao = BeanContainerFactory.getBeanContainer(ModuleUtil.class.getClassLoader()).getBean(SystemModuleDaoImpl.class);

    private static ModuleListCallBackFunction moduleList;

    private static ModuleCallBackFunction singleModule;

    static {
        moduleList = new ModuleListCallBackFunction() {
            public List<SystemModule> getModuleList(SystemModule systemModule) {
                return systemModuleDao.query(systemModule);
            }
        };
        singleModule = new ModuleCallBackFunction() {
            public SystemModule getModule(Integer moduleId) {
                return systemModuleDao.getByKey(moduleId);
            }
        };
    }

    private static void mergeModuleCondition(StringBuffer condition, int moduleId) {
        DefaultModuleUtils.mergeModuleCondition(condition,moduleId,moduleList);
    }

    public static StringBuffer getConditionByModule(StringBuffer condition,SystemModule systemModule){
        return DefaultModuleUtils.getConditionByModule(condition,systemModule,moduleList,singleModule);
    }

    public static String getCondition(Integer moduleId){
        return  DefaultModuleUtils.getCondition(moduleId,moduleList);
    }

    public static String getConditionWithoutOperate(Integer moduleId){
        return  DefaultModuleUtils.getConditionWithoutOperate(moduleId, moduleList);
    }

    public static String getConditionByRoot(Integer rootId,String moduleType ){
        return DefaultModuleUtils.getConditionByRoot(rootId,moduleType,moduleList);
    }

    public static String getConditionByRootWithoutOperate(Integer rootId,String moduleType){
        return DefaultModuleUtils.getConditionByRootWithoutOperate(rootId,moduleType,moduleList);
    }

    public static String getPath(Integer moduleId, String division, String root, boolean openRoot){
        return DefaultModuleUtils.getPath(moduleId,division,singleModule,root,openRoot);
    }

    private static String mergePath(String division, String paths ){
        return DefaultModuleUtils.mergePath(division,paths,singleModule);
    }
}
