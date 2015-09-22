package org.tinygroup.sdpm.system.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.system.biz.inter.ActionManager;
import org.tinygroup.sdpm.system.dao.ActionDao;
import org.tinygroup.sdpm.system.dao.ProfileDao;
import org.tinygroup.sdpm.system.dao.pojo.Action;
@Service
@Transactional
public class ActionManagerImpl implements ActionManager {
	@Autowired
    private ActionDao actionDao;
	public Action add(Action action) {
		// TODO Auto-generated method stub
		return actionDao.add(action);
	}

	public Action updata(Action action) {
		// TODO Auto-generated method stub
		actionDao.edit(action);
		return action;
	}

	public Integer delete(Action action) {
		// TODO Auto-generated method stub
		int pk=action.getActionId();
		return actionDao.deleteByKey(pk);
	}

	public List<Action> find(Action action) {
		// TODO Auto-generated method stub
		return actionDao.query(action);
	}

}
