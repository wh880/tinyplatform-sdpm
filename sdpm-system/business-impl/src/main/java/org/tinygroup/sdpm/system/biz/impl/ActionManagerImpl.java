package org.tinygroup.sdpm.system.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.system.biz.inter.ActionManager;
import org.tinygroup.sdpm.system.dao.SystemActionDao;
import org.tinygroup.sdpm.system.dao.pojo.SystemAction;
import org.tinygroup.tinysqldsl.Pager;
@Service
@Transactional
public class ActionManagerImpl implements ActionManager {
	@Autowired
    private SystemActionDao systemActionDao;
	public SystemAction add(SystemAction systemAction) {
		// TODO Auto-generated method stub
		return systemActionDao.add(systemAction);
	}

	public SystemAction updata(SystemAction systemAction) {
		// TODO Auto-generated method stub
		systemActionDao.edit(systemAction);
		return systemAction;
	}

	public Integer delete(SystemAction systemAction) {
		// TODO Auto-generated method stub
		int pk=systemAction.getActionId();
		return systemActionDao.deleteByKey(pk);
	}

	public List<SystemAction> find(SystemAction SystemAction) {
		// TODO Auto-generated method stub
		return systemActionDao.query(SystemAction);
	}

	public Pager<SystemAction> findByPage(int start, int limit,
			SystemAction systemAction, String sortName, boolean asc) {
		// TODO Auto-generated method stub
		if(StringUtil.isBlank(sortName)){
			return systemActionDao.queryPager(start, limit, systemAction);
		}
		OrderBy orderBy = new OrderBy(sortName, asc);
		
		return systemActionDao.queryPager(start, limit, systemAction, orderBy);
	}

}
