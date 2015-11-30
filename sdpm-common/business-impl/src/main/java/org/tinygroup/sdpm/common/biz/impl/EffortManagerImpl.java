package org.tinygroup.sdpm.common.biz.impl;

import org.tinygroup.sdpm.common.biz.inter.EffortManager;
import org.tinygroup.sdpm.common.dao.EffortDao;
import org.tinygroup.sdpm.common.dao.pojo.Effort;
import org.tinygroup.tinysqldsl.Pager;

import java.util.Date;
import java.util.List;

public class EffortManagerImpl implements EffortManager {
    private EffortDao effortDao;
	public Effort add(Effort effort) {
		// TODO Auto-generated method stub
		return effortDao.add(effort);
	}

	public Effort updata(Effort effort) {
		// TODO Auto-generated method stub
		effortDao.edit(effort);
		return effort;
	}

	public Integer delete(Effort effort) {
		// TODO Auto-generated method stub
		int pk=effort.getEffortId();
		return effortDao.deleteByKey(pk);
	}

	public List<Effort> findBetweenDate(Date begindate, Date enddate) {
		// TODO Auto-generated method stub
		return effortDao.findBetweenDate(begindate, enddate);
	}

	public List<Effort> findByAccount(String account) {
		// TODO Auto-generated method stub
		Effort effort= new Effort();
		effort.setEffortAccount(account);
		return effortDao.query(effort);
	}

	public List<Effort> findByProject(int projectId) {
		// TODO Auto-generated method stub
		Effort effort = new Effort();
		effort.setEffortProject(projectId);
		return effortDao.query(effort);
	}

	public Pager<Effort> findByPage(int start, int limit, Effort effort) {
		// TODO Auto-generated method stub
		return effortDao.queryPager(start, limit, effort);
	}

	public List<Effort> findByDate(Date date) {
		// TODO Auto-generated method stub
		Effort effort = new Effort();
		effort.setEffortDate(date);
		return effortDao.query(effort);
	}

	public List<Effort> find(Effort effort) {
		// TODO Auto-generated method stub
		return effortDao.query(effort);
	}

}
