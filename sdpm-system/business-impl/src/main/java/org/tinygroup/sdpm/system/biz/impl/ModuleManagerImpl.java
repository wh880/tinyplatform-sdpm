package org.tinygroup.sdpm.system.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.system.biz.inter.ModuleManager;
import org.tinygroup.sdpm.system.dao.SystemModuleDao;
import org.tinygroup.sdpm.system.dao.pojo.SystemModule;

@Service
@Transactional
public class ModuleManagerImpl implements ModuleManager{
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

	public List<SystemModule> findByRoot(int moduleRoot) {
		// TODO Auto-generated method stub
		SystemModule systemModule = new SystemModule();
		systemModule.setModuleRoot(moduleRoot);
		
		return systemModuleDao.query(systemModule);
	}

	public int delete(SystemModule systemModule) {
		// TODO Auto-generated method stub
		int pk=systemModule.getModuleId();
		return systemModuleDao.deleteByKey(pk);
	}

	public SystemModule findById(int id) {
		// TODO Auto-generated method stub
		return systemModuleDao.getByKey(id);
	}

}
