package org.tinygroup.sdpm.system.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.system.biz.inter.ActionManager;
import org.tinygroup.sdpm.system.dao.SystemActionDao;
import org.tinygroup.sdpm.system.dao.pojo.SystemAction;
@Service
@Transactional
public class ActionManagerImpl implements ActionManager {
	@Autowired
    private SystemActionDao systemActionDao;
	public SystemAction add(SystemAction SystemAction) {
		// TODO Auto-generated method stub
		return systemActionDao.add(SystemAction);
	}

	public SystemAction updata(SystemAction SystemAction) {
		// TODO Auto-generated method stub
		systemActionDao.edit(SystemAction);
		return SystemAction;
	}

	public Integer delete(SystemAction SystemAction) {
		// TODO Auto-generated method stub
		int pk=SystemAction.getActionId();
		return systemActionDao.deleteByKey(pk);
	}

	public List<SystemAction> find(SystemAction SystemAction) {
		// TODO Auto-generated method stub
		return systemActionDao.query(SystemAction);
	}

}
