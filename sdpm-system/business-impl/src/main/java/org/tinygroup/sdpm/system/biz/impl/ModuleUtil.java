package org.tinygroup.sdpm.system.biz.impl;

import org.tinygroup.beancontainer.BeanContainerFactory;
import org.tinygroup.sdpm.system.biz.inter.ModuleManager;
import org.tinygroup.sdpm.system.dao.pojo.SystemModule;
import org.tinygroup.sdpm.system.dao.utils.DefaultModuleUtils;
import org.tinygroup.sdpm.system.dao.utils.ModuleCallBackFunction;
import org.tinygroup.sdpm.system.dao.utils.ModuleListCallBackFunction;

import java.util.List;

/**
 * Created by wangll13383 on 2015/10/13.
 */
public class ModuleUtil {

    private static ModuleManager moduleManager = BeanContainerFactory.getBeanContainer(ModuleUtil.class.getClassLoader()).getBean(ModuleManagerImpl.class);

    private static ModuleListCallBackFunction moduleList;

    private static ModuleCallBackFunction singleModule;

    static {
        moduleList = new ModuleListCallBackFunction() {
            public List<SystemModule> getModuleList(SystemModule systemModule) {
                return moduleManager.findByModules(systemModule);
            }
        };
        singleModule = new ModuleCallBackFunction() {
            public SystemModule getModule(Integer moduleId) {
                return moduleManager.findById(moduleId);
            }
        };
    }


    public static StringBuffer getConditionByModule(StringBuffer condition, SystemModule systemModule) {
        return DefaultModuleUtils.getConditionByModule(condition, systemModule, moduleList, singleModule);
    }

    public static String getCondition(Integer moduleId) {
        return DefaultModuleUtils.getCondition(moduleId, moduleList);
    }

    public static String getConditionByRoot(Integer rootId, String moduleType) {
        return DefaultModuleUtils.getConditionByRoot(rootId, moduleType, moduleList);
    }

    public static String getPath(Integer moduleId, String division, String root, boolean openRoot) {
        return DefaultModuleUtils.getPath(moduleId, division, singleModule, root, openRoot);
    }

    private static String mergePath(String division, String paths) {
        return DefaultModuleUtils.mergePath(division, paths, singleModule);
    }
}
