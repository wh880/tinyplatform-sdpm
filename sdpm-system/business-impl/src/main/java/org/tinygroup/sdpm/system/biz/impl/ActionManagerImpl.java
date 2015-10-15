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
import org.tinygroup.sdpm.system.dao.impl.ActionEnum;
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
		List<SystemAction> actions = systemActionDao.query(SystemAction);
		if(actions.size()>0){
			for(SystemAction s : actions){
				s = systemActionDao.getActionAndObject(s);
				s.setUrl(s.getActionObjectType());
			}
		}
		return actions;
	}

	public Pager<SystemAction> findByPage(int start, int limit, SystemAction systemAction, String order,
			String ordertype) {

		Pager<SystemAction> pager = systemActionDao.queryPager((start-1)*limit, limit, systemAction, (order==null||"".equals(order))?null:new OrderBy(NameUtil.resolveNameDesc(order), !("desc".equals(ordertype))?true:false));
		if(pager.getRecords().size()>0){
			for(SystemAction s : pager.getRecords()){
				s = systemActionDao.getActionAndObject(s);
				s.setUrl(ActionEnum.getUrl(s.getActionObjectType()));
			}
		}
		return pager;
	}

	

}
