package org.tinygroup.sdpm.system.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.common.util.common.NameUtil;
import org.tinygroup.sdpm.system.biz.inter.ActionManager;
import org.tinygroup.sdpm.system.dao.SystemActionDao;
import org.tinygroup.sdpm.system.dao.pojo.SystemAction;
import org.tinygroup.tinysqldsl.Pager;
import org.tinygroup.tinysqldsl.base.Condition;

import java.util.Date;
import java.util.List;

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
        int pk = systemAction.getActionId();
        return systemActionDao.deleteByKey(pk);
    }

    public List<SystemAction> find(SystemAction SystemAction) {
        List<SystemAction> actions = systemActionDao.query(SystemAction);
        if (actions.size() > 0) {
            for (SystemAction s : actions) {
                SystemAction action = systemActionDao.getActionAndObject(s);
                actions.remove(s);
                actions.add(action);
                //s.setUrl(ActionEnum.getUrl(s.getActionObjectType()));
            }
        }
        return actions;
    }

    public Pager<SystemAction> findByPage(int start, int limit, SystemAction systemAction, String order,
                                          String ordertype) {
        Pager<SystemAction> pager;
        if(order==null){
            pager = systemActionDao.queryPager(start, limit, systemAction);
        }else{
            pager = systemActionDao.queryPager(start, limit, systemAction,new OrderBy(NameUtil.resolveNameDesc(order), !("desc".equals(ordertype)) ? true : false));
        }
        if (pager.getRecords().size() > 0) {
            for (SystemAction s : pager.getRecords()) {
                SystemAction action = systemActionDao.getActionAndObject(s);
                pager.getRecords().remove(s);
                pager.getRecords().add(action);
                //s.setUrl(ActionEnum.getUrl(s.getActionObjectType()));
            }
        }
        return pager;
    }

    public Pager<SystemAction> queryPager(int start,int limit ,Condition condition,SystemAction systemAction , String order,String ordertype){
        Pager<SystemAction> pager;
        if(order==null){
            pager = systemActionDao.queryPager(start, limit,condition, systemAction);
        }else{
            pager = systemActionDao.queryPager(start, limit,condition, systemAction,new OrderBy(NameUtil.resolveNameDesc(order), !("desc".equals(ordertype)) ? true : false));
        }
        if(pager.getRecords().size()>0){
            for(SystemAction s : pager.getRecords()){
                SystemAction action = systemActionDao.getActionAndObject(s);
                pager.getRecords().remove(s);
                pager.getRecords().add(action);
                //s.setUrl(ActionEnum.getUrl(s.getActionObjectType()));
            }
        }
        return pager;
    }

	public Pager<SystemAction> queryBetweenDate(int start, int limit,
			SystemAction action, String startDate, String endDate, String sortName,
			boolean asc) {
		// TODO Auto-generated method stub
		if (StringUtil.isBlank(sortName)) {
			return systemActionDao.findByDate(start, limit, action, startDate, endDate);
		}
		OrderBy orderBy = new OrderBy(sortName, asc);
		return systemActionDao.findByDate(start, limit, action, startDate, endDate, orderBy);
	}

	

}
