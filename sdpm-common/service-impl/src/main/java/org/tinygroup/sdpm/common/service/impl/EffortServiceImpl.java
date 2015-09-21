package org.tinygroup.sdpm.common.service.impl;

import java.util.Date;
import java.util.List;

import org.tinygroup.sdpm.common.biz.inter.EffortManager;
import org.tinygroup.sdpm.common.dao.pojo.Effort;
import org.tinygroup.sdpm.common.service.inter.EffortService;
import org.tinygroup.tinysqldsl.Pager;

public class EffortServiceImpl implements EffortService {
    private EffortManager  effortManager;
	public List<Effort> findByDate(Date date) {
		// TODO Auto-generated method stub 
		
		return effortManager.findByDate(date);
	}

	public Effort save(Effort effort) {
		// TODO Auto-generated method stub
		if(effort.getEffortId()==null){
		return effortManager.add(effort);
		}
		else{
		
			return effortManager.updata(effort);
		}
	}

	public List<Effort> findByAccount(String account) {
		// TODO Auto-generated method stub
		return effortManager.findByAccount(account);
	}

	public List<Effort> find(Effort effort) {
		// TODO Auto-generated method stub
		return effortManager.find(effort);
	}

	public List<Effort> findBetweenDate(Date begindate, Date enddate) {
		// TODO Auto-generated method stub
		return effortManager.findBetweenDate(begindate, enddate);
	}

	public List<Effort> findByProject(int projectId) {
		// TODO Auto-generated method stub
		return effortManager.findByProject(projectId);
	}

	public Pager<Effort> findByPage(int start, int limit, Effort effort) {
		// TODO Auto-generated method stub
		return effortManager.findByPage(start, limit, effort);
	}

}
