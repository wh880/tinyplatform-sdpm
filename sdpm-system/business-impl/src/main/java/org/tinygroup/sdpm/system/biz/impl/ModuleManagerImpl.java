package org.tinygroup.sdpm.system.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.system.biz.inter.ModuleManager;
import org.tinygroup.sdpm.system.dao.SystemModuleDao;
import org.tinygroup.sdpm.system.dao.pojo.SystemModule;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ModuleManagerImpl implements ModuleManager {
    @Autowired
    private SystemModuleDao systemModuleDao;

    public SystemModule edit(SystemModule systemModule) {
        // TODO Auto-generated method stub
        systemModuleDao.edit(systemModule);
        return systemModule;

    }

    public int deleteById(int id) {
        // TODO Auto-generated method stub
        return systemModuleDao.deleteByKey(id);
    }


    public int delete(SystemModule systemModule) {
        // TODO Auto-generated method stub
        int pk = systemModule.getModuleId();
        return systemModuleDao.deleteByKey(pk);
    }

    public SystemModule findById(int id) {
        // TODO Auto-generated method stub
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
        // TODO Auto-generated method stub
        return systemModuleDao.add(systemModule);
    }

    public SystemModule editNameAndTitle(SystemModule systemModule) {
        // TODO Auto-generated method stub
        systemModuleDao.editNameAndTitle(systemModule);
        return systemModule;
    }

    public int batchDelete(Integer... ids) {
        // TODO Auto-generated method stub
        return systemModuleDao.deleteByKeys(ids);
    }

    public List<SystemModule> findList(SystemModule systemModule) {

        return systemModuleDao.query(systemModule);
    }

    public int deleteAndedit(Integer id) {
        // TODO Auto-generated method stub
        return systemModuleDao.deletebyKeyAndedit(id);
    }

    public int deleteByType(String type) {
        return systemModuleDao.deleteByType(type);
    }

}
