package org.tinygroup.sdpm.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.system.biz.inter.ModuleManager;
import org.tinygroup.sdpm.system.dao.pojo.SystemModule;
import org.tinygroup.sdpm.system.service.inter.ModuleService;
@Component
public class ModuleServiceImpl implements ModuleService {
	@Autowired
    private ModuleManager systemModuleManager;
    
	public SystemModule edit(SystemModule systemModule) {
		// TODO Auto-generated method stub
		return systemModuleManager.edit(systemModule);
	}

	public int deleteById(int id) {
		// TODO Auto-generated method stub
		return systemModuleManager.deleteById(id);
	}

	public int delete(SystemModule systemModule) {
		// TODO Auto-generated method stub
		return  systemModuleManager.delete(systemModule);
	}

	public SystemModule findById(int id) {
		// TODO Auto-generated method stub
		return systemModuleManager.findById(id);
	}

	public List<SystemModule> findModules(SystemModule systemModule) {
		// TODO Auto-generated method stub
		
		return systemModuleManager.findByModules(systemModule);
	}
	
	public List<SystemModule> findAllModules(SystemModule systemModule) {
		// TODO Auto-generated method stub
		
		return systemModuleManager.findModules(systemModule,new ArrayList<SystemModule>());
	}

	public SystemModule add(SystemModule systemModule) {

		return systemModuleManager.add(systemModule);
	}
    public SystemModule eidtNameAndTiele(SystemModule systemModule){
    return systemModuleManager.editNameAndTitle(systemModule);
    }

	public int batchDelete(Integer...ids) {
		// TODO Auto-generated method stub
		return systemModuleManager.batchDelete(ids);
	}

	public List<SystemModule> findModuleList(SystemModule systemModule){

		return systemModuleManager.findList(systemModule);
	}

	public int deleteAndedit(Integer id) {
		// TODO Auto-generated method stub
		return systemModuleManager.deleteAndedit(id);
	}

}
