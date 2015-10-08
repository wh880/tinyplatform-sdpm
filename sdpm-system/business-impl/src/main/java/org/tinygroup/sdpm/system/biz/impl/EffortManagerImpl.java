package org.tinygroup.sdpm.system.biz.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.system.biz.inter.EffortManager;
import org.tinygroup.sdpm.system.dao.SystemEffortDao;
import org.tinygroup.sdpm.system.dao.pojo.SystemEffort;
import org.tinygroup.tinysqldsl.Pager;
@Service
@Transactional
public class EffortManagerImpl implements EffortManager {
	@Autowired
    private SystemEffortDao systemEffortDao;
	
	
	public SystemEffort add(SystemEffort systemEffort) {
		// TODO Auto-generated method stub
		return systemEffortDao.add(systemEffort);
	}

	public SystemEffort updata(SystemEffort systemEffort) {
		// TODO Auto-generated method stub
		systemEffortDao.edit(systemEffort);
		return systemEffort;
	}

	public Integer delete(SystemEffort SystemEffort) {
		// TODO Auto-generated method stub
		int pk=SystemEffort.getEffortId();
		return systemEffortDao.deleteByKey(pk);
	}

	public List<SystemEffort> findByAccount(String effortAccount) {
		// TODO Auto-generated method stub
		SystemEffort systemEffort= new SystemEffort();
		systemEffort.setEffortAccount(effortAccount);
		return systemEffortDao.query(systemEffort);
	}

	public List<SystemEffort> findByProject(int project) {
		// TODO Auto-generated method stub
		SystemEffort systemEffort = new SystemEffort();
		systemEffort.setEffortProject(project);
		return systemEffortDao.query(systemEffort);
	}

	public Pager<SystemEffort> findByPage(int start, int limit, SystemEffort SystemEffort,String sortName, boolean asc) {
		// TODO Auto-generated method stub
		if (StringUtil.isBlank(sortName)) {
		return systemEffortDao.queryPager(start, limit, SystemEffort);
		 }
		OrderBy orderBy = new OrderBy(sortName, asc);
		return systemEffortDao.queryPager(start, limit, SystemEffort, orderBy);
	}

	public List<SystemEffort> findByDate(Date date) {
		// TODO Auto-generated method stub
		SystemEffort systemEffort = new SystemEffort();
		systemEffort.setEffortDate(date);
		return systemEffortDao.query(systemEffort);
	}

	public List<SystemEffort> find(SystemEffort systemEffort) {
		// TODO Auto-generated method stub
		return systemEffortDao.query(systemEffort);
	}

	public List<SystemEffort> findBetweenDate(Date begindate, Date enddate) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<SystemEffort> findList(SystemEffort systemEffort, String order,
			String orderType) {
		// TODO Auto-generated method stub
		return systemEffortDao.query(systemEffort, new OrderBy(order, !("desc".equals(orderType))?true:false));
	}

	public int batchDelete(Integer... ids) {
		// TODO Auto-generated method stub
		return systemEffortDao.deleteByKeys(ids);
	}

//public List<SystemEffort> findBetweenDate(Date begindate, Date enddate) {
//		// TODO Auto-generated method stub
////		return SystemEffortDao.findBetweenDate(begindate, enddate);
//	}

}
