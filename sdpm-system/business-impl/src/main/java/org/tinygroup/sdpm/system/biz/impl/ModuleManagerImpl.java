package org.tinygroup.sdpm.system.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinygroup.sdpm.system.biz.inter.ModuleManager;
import org.tinygroup.sdpm.system.dao.SystemModuleDao;
import org.tinygroup.sdpm.system.dao.pojo.SystemModule;

import java.util.ArrayList;
import java.util.List;

@Service
public class ModuleManagerImpl implements ModuleManager {
    @Autowired
    private SystemModuleDao systemModuleDao;

    public SystemModule edit(SystemModule systemModule) {
        systemModuleDao.edit(systemModule);
        return systemModule;

    }

    public Integer deleteById(Integer id) {
        return systemModuleDao.deleteByKey(id);
    }


    public Integer delete(SystemModule systemModule) {
        return systemModuleDao.deleteByObject(systemModule);
    }

    public SystemModule findById(Integer id) {
        try {
            return systemModuleDao.getByKey(id);
        } catch (Exception e) {
            return new SystemModule();
        }

    }

    public List<SystemModule> findByModules(SystemModule systemModule) {

        return systemModuleDao.query(systemModule);
    }

    public List<SystemModule> findModules(SystemModule systemModule, List<SystemModule> list) {
        if (systemModule == null) {
            return null;
        }
        if (list == null) {
            list = new ArrayList<SystemModule>();
        }
        if (systemModule.getModuleId() != null && systemModule.getModuleParent() == null) {
            Integer c = systemModule.getModuleId();
            systemModule.setModuleParent(c);
            systemModule.setModuleId(null);
        }
        List<SystemModule> modules = systemModuleDao.query(systemModule);
        if (modules.size() > 0) {
            list.addAll(modules);
            for (SystemModule module : modules) {
                systemModule.setModuleParent(module.getModuleId());
                systemModule.setModuleId(null);
                findModules(systemModule, list);
            }
        }
        return list;
    }

    public SystemModule add(SystemModule systemModule) {
        return systemModuleDao.add(systemModule);
    }

    public SystemModule editNameAndTitle(SystemModule systemModule) {
        systemModuleDao.editNameAndTitle(systemModule);
        return systemModule;
    }

    public Integer batchDelete(Integer... ids) {
        return systemModuleDao.deleteByKeys(ids);
    }

    public List<SystemModule> findList(SystemModule systemModule) {

        return systemModuleDao.query(systemModule);
    }

    public Integer deleteAndEdit(Integer id) {
        return systemModuleDao.deletebyKeyAndedit(id);
    }

    public Integer deleteByType(String type) {
        return systemModuleDao.deleteByType(type);
    }

}
