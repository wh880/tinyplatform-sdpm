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
    private ModuleManager SystemModuleManager;
    
	public SystemModule edit(SystemModule systemModule) {
		// TODO Auto-generated method stub
		return SystemModuleManager.edit(systemModule);
	}

	public int deleteById(int id) {
		// TODO Auto-generated method stub
		return SystemModuleManager.deleteById(id);
	}

	public int delete(SystemModule systemModule) {
		// TODO Auto-generated method stub
		return  SystemModuleManager.delete(systemModule);
	}

	public SystemModule findById(int id) {
		// TODO Auto-generated method stub
		return SystemModuleManager.findById(id);
	}

	public List<SystemModule> findModules(SystemModule systemModule) {
		// TODO Auto-generated method stub
		
		return SystemModuleManager.findByModules(systemModule);
	}
	
	public List<SystemModule> findAllModules(SystemModule systemModule) {
		// TODO Auto-generated method stub
		
		return SystemModuleManager.findModules(systemModule,new ArrayList<SystemModule>());
	}

	public SystemModule add(SystemModule systemModule) {

		return SystemModuleManager.add(systemModule);
	}
    public SystemModule eidtNameAndTiele(SystemModule systemModule){
    return SystemModuleManager.editNameAndTitle(systemModule);
    }

	public int batchDelete(Integer...ids) {
		// TODO Auto-generated method stub
		return SystemModuleManager.batchDelete(ids);
	}

	public List<SystemModule> findModuleList(SystemModule systemModule){

		return SystemModuleManager.findList(systemModule);
	}

}
