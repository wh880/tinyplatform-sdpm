package org.tinygroup.sdpm.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.system.biz.inter.ModuleManager;
import org.tinygroup.sdpm.system.dao.pojo.SystemModule;
import org.tinygroup.sdpm.system.service.inter.ModuleService;

import java.util.ArrayList;
import java.util.List;

@Component
public class ModuleServiceImpl implements ModuleService {
    @Autowired
    private ModuleManager systemModuleManager;

    public SystemModule edit(SystemModule systemModule) {
        return systemModuleManager.edit(systemModule);
    }

    public int deleteById(int id) {
        return systemModuleManager.deleteById(id);
    }

    public int delete(SystemModule systemModule) {
        return systemModuleManager.delete(systemModule);
    }

    public SystemModule findById(int id) {
        return systemModuleManager.findById(id);
    }

    public List<SystemModule> findModules(SystemModule systemModule) {
        return systemModuleManager.findByModules(systemModule);
    }

    public List<SystemModule> findAllModules(SystemModule systemModule) {
        return systemModuleManager.findModules(systemModule, new ArrayList<SystemModule>());
    }

    public SystemModule add(SystemModule systemModule) {
        return systemModuleManager.add(systemModule);
    }

    public SystemModule editNameAndTitle(SystemModule systemModule) {
        return systemModuleManager.editNameAndTitle(systemModule);
    }

    public int batchDelete(Integer... ids) {
        return systemModuleManager.batchDelete(ids);
    }

    public List<SystemModule> findModuleList(SystemModule systemModule) {
        return systemModuleManager.findList(systemModule);
    }

    public int deleteAndEdit(Integer id) {
        return systemModuleManager.deleteAndEdit(id);
    }

    public int deleteByType(String type) {
        return systemModuleManager.deleteByType(type);
    }

}
