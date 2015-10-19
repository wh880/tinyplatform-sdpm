package org.tinygroup.sdpm.system.service.impl;

import java.util.Date;
import java.util.List;

import org.tinygroup.tinysqldsl.base.Condition;
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

	public List<SystemAction> find(SystemAction systemAction,String orderby, boolean asc) {
		return actionManager.find(systemAction,orderby,asc);
	}

	public Pager<SystemAction> findSystemActionPager(int page, int pagesize, SystemAction action, String order,
			String ordertype) {
		
		return actionManager.findByPage(page, pagesize, action, order, ordertype);
	}

	public Pager<SystemAction> queryPager(int start,int limit , Condition condition,SystemAction systemAction , String order,
			String ordertype){
		
		return actionManager.queryPager(start, limit, condition, systemAction, order, ordertype);
	}

	public Pager<SystemAction> queryBetweenDate(int start, int limit,
			SystemAction action, String startDate, String endDate, String sortName,
			boolean asc) {
		// TODO Auto-generated method stub
		return actionManager.queryBetweenDate(start, limit, action, startDate, endDate, sortName, asc);
	}

}
