package org.tinygroup.sdpm.system.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.common.util.common.NameUtil;
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
		return systemActionDao.add(systemAction);
	}

	public SystemAction updata(SystemAction systemAction) {
		systemActionDao.edit(systemAction);
		return systemAction;
	}

	public Integer delete(SystemAction systemAction) {
		int pk=systemAction.getActionId();
		return systemActionDao.deleteByKey(pk);
	}

	public List<SystemAction> find(SystemAction SystemAction) {
		return systemActionDao.query(SystemAction);
	}

	public Pager<SystemAction> findByPage(int start, int limit, SystemAction systemAction, String order,
			String ordertype) {
	
		return systemActionDao.queryPager((start-1)*limit, limit, systemAction, (order==null||"".equals(order))?null:new OrderBy(NameUtil.resolveNameDesc(order), !("desc".equals(ordertype))?true:false));
	}

	

}
