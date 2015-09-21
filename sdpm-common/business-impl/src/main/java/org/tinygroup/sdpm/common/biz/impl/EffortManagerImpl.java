package org.tinygroup.sdpm.common.biz.impl;

import java.util.Date;
import java.util.List;

import org.tinygroup.sdpm.common.biz.inter.EffortManager;
import org.tinygroup.sdpm.common.dao.EffortDao;
import org.tinygroup.sdpm.common.dao.pojo.Effort;
import org.tinygroup.tinysqldsl.Pager;

public class EffortManagerImpl implements EffortManager {
    private EffortDao effortDao;
	public Effort add(Effort effort) {
		// TODO Auto-generated method stub
		return effortDao.add(effort);
	}

	public Integer updata(Effort effort) {
		// TODO Auto-generated method stub
		return effortDao.edit(effort);
	}

	public Integer delete(Effort effort) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Effort> findBetweenDate(Date begindate, Date enddate) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Effort> findByAccount(String account) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Effort> findByProject(int projectId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Pager<Effort> findByPage(int start, int limit, Effort effort) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Effort> findByDate(Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Effort> find(Effort effort) {
		// TODO Auto-generated method stub
		return null;
	}

}
