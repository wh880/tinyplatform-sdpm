package org.tinygroup.sdpm.system.biz.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.system.biz.inter.EffortManager;
import org.tinygroup.sdpm.system.dao.EffortDao;
import org.tinygroup.sdpm.system.dao.pojo.Effort;
import org.tinygroup.tinysqldsl.Pager;
import org.tinygroup.validate.annotation.AssertEnum;
@Service
@Transactional
public class EffortManagerImpl implements EffortManager {
	@Autowired
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

	public List<Effort> findBetweenDate(Date begindate, Date enddate) {
		// TODO Auto-generated method stub
		return null;
	}

}
