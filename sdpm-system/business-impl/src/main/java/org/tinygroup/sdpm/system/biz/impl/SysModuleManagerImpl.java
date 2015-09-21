package org.tinygroup.sdpm.system.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.system.biz.inter.SysModuleManager;
import org.tinygroup.sdpm.system.dao.SysModuleDao;
import org.tinygroup.sdpm.system.dao.pojo.SysModule;

@Service
@Transactional
public class SysModuleManagerImpl implements SysModuleManager{
	@Autowired
    private SysModuleDao sysModuleDao;
	public SysModule edit(SysModule sysModule) {
		// TODO Auto-generated method stub
		sysModuleDao.edit(sysModule);
		return sysModule;
		
	}

	public int deleteById(int id) {
		// TODO Auto-generated method stub
		return sysModuleDao.deleteByKey(id);
	}

	public List<SysModule> findByRoot(int root) {
		// TODO Auto-generated method stub
		
		return null;
	}

	public int delete(SysModule sysModule) {
		// TODO Auto-generated method stub
		int pk=sysModule.getSysModuleId();
		return sysModuleDao.deleteByKey(pk);
	}

}
