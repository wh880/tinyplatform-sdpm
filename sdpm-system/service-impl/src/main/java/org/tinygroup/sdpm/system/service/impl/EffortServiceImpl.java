package org.tinygroup.sdpm.system.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.system.biz.inter.EffortManager;
import org.tinygroup.sdpm.system.dao.pojo.SystemEffort;
import org.tinygroup.sdpm.system.service.inter.EffortService;
import org.tinygroup.tinysqldsl.Pager;
@Component
public class EffortServiceImpl implements EffortService {
	@Autowired
    private EffortManager  effortManager;
	public List<SystemEffort> findByDate(Date date) {
		// TODO Auto-generated method stub 
		
		return effortManager.findByDate(date);
	}

	public SystemEffort save(SystemEffort systemEffort) {
		// TODO Auto-generated method stub
		if(systemEffort.getEffortId()==null){
		return effortManager.add(systemEffort);
		}
		else{
		
			return effortManager.updata(systemEffort);
		}
	}

	public List<SystemEffort> findByAccount(String account) {
		// TODO Auto-generated method stub
		return effortManager.findByAccount(account);
	}

	public List<SystemEffort> find(SystemEffort systemEffort) {
		// TODO Auto-generated method stub
		return effortManager.find(systemEffort);
	}

	public List<SystemEffort> findBetweenDate(Date begindate, Date enddate) {
		// TODO Auto-generated method stub
		return effortManager.findBetweenDate(begindate, enddate);
	}

	public List<SystemEffort> findByProject(int projectId) {
		// TODO Auto-generated method stub
		return effortManager.findByProject(projectId);
	}

	public Pager<SystemEffort> findByPage(int start, int limit, SystemEffort SystemEffort) {
		// TODO Auto-generated method stub
		return effortManager.findByPage(start, limit, SystemEffort);
	}

}
