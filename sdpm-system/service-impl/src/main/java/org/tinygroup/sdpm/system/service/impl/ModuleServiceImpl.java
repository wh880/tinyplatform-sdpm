package org.tinygroup.sdpm.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.system.biz.inter.ModuleManager;
import org.tinygroup.sdpm.system.dao.pojo.SystemModule;
import org.tinygroup.sdpm.system.service.inter.ModuleService;

import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class ModuleServiceImpl implements ModuleService {
    @Autowired
    private ModuleManager systemModuleManager;

    public SystemModule editModule(SystemModule systemModule) {
        return systemModuleManager.edit(systemModule);
    }

    public Integer deleteModuleById(Integer id) {
        return systemModuleManager.deleteById(id);
    }

    public Integer delete(SystemModule systemModule) {
        return systemModuleManager.delete(systemModule);
    }
    @Transactional(readOnly = true)
    public SystemModule findById(Integer id) {
        return systemModuleManager.findById(id);
    }
    @Transactional(readOnly = true)
    public List<SystemModule> findAllModules(SystemModule systemModule) {
        return systemModuleManager.findModules(systemModule, new ArrayList<SystemModule>());
    }

    public SystemModule addSystemModule(SystemModule systemModule) {
        return systemModuleManager.add(systemModule);
    }

    public SystemModule editNameAndTitle(SystemModule systemModule) {
        return systemModuleManager.editNameAndTitle(systemModule);
    }

    public Integer batchDeleteSystemModule(Integer... ids) {
        return systemModuleManager.batchDelete(ids);
    }
    @Transactional(readOnly = true)
    public List<SystemModule> findModuleList(SystemModule systemModule) {
        return systemModuleManager.findList(systemModule);
    }

    public Integer deleteSystemModuleByType(String type) {
        return systemModuleManager.deleteByType(type);
    }

}
