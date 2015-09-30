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

	

	public int delete(SystemModule systemModule) {
		// TODO Auto-generated method stub
		int pk=systemModule.getModuleId();
		return systemModuleDao.deleteByKey(pk);
	}

	public SystemModule findById(int id) {
		// TODO Auto-generated method stub
		return systemModuleDao.getByKey(id);
	}

	public List<SystemModule> findByModules(SystemModule systemModule) {
		// TODO Auto-generated method stub
		return systemModuleDao.query(systemModule);
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

	public int batchDelete(String ids) {
		// TODO Auto-generated method stub
		return systemModuleDao.batchdelete(ids);
	}

}
