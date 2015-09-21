package org.tinygroup.sdpm.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.system.biz.inter.SysModuleManager;
import org.tinygroup.sdpm.system.dao.pojo.SysModule;
import org.tinygroup.sdpm.system.service.inter.SysModuleService;
@Component()
public class SysModuleServiceImpl implements SysModuleService {
	@Autowired
    private SysModuleManager sysModuleManager;
	public SysModule edit(SysModule sysModule) {
		// TODO Auto-generated method stub
		return sysModuleManager.edit(sysModule);
	}

	public int deleteById(int id) {
		// TODO Auto-generated method stub
		return sysModuleManager.deleteById(id);
	}

	public List<SysModule> findByRoot(int root) {
		// TODO Auto-generated method stub
		return sysModuleManager.findByRoot(root);
	}

	public int delete(SysModule sysModule) {
		// TODO Auto-generated method stub
		return  sysModuleManager.delete(sysModule);
	}

}
