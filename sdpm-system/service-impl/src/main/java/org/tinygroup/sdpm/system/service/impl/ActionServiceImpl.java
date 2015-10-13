package org.tinygroup.sdpm.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.system.biz.inter.ActionManager;
import org.tinygroup.sdpm.system.dao.pojo.SystemAction;
import org.tinygroup.sdpm.system.service.inter.ActionService;
import org.tinygroup.tinysqldsl.Pager;
@Component
public class ActionServiceImpl implements ActionService {
	@Autowired
    private ActionManager actionManager;
	public SystemAction add(SystemAction systemAction) {
		return actionManager.add(systemAction);
	}

	public SystemAction updata(SystemAction systemAction) {

		return actionManager.updata(systemAction);
	}

	public Integer delete(SystemAction systemAction) {
		return actionManager.delete(systemAction);
	}

	public List<SystemAction> find(SystemAction systemAction) {
		return actionManager.find(systemAction);
	}

	public Pager<SystemAction> findSystemActionPager(int page, int pagesize, SystemAction action, String order,
			String ordertype) {
		
		return actionManager.findByPage(page, pagesize, action, order, ordertype);
	}



}
