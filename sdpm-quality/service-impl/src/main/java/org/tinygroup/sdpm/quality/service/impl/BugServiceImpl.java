package org.tinygroup.sdpm.quality.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.quality.biz.inter.BugManager;
import org.tinygroup.sdpm.quality.dao.pojo.Bug;
import org.tinygroup.sdpm.quality.service.inter.BugService;

@Component("bugService")
public class BugServiceImpl implements BugService {
	@Autowired
	private BugManager bugmanager;
	
	public List<Bug> findBugList(Bug bug){				
		return bugmanager.findList(bug);
	}
	
	public Bug addBug(Bug bug){
		return bugmanager.add(bug);
	}
	
	public Bug findById(int id){
		return bugmanager.find(id);
	}
	
	public int updateBug(Bug bug){
		return bugmanager.update(bug);
	}
	
	public int[] batchUpdateBug(List<Bug> bugs){
		return bugmanager.batchUpdate(bugs);
	}
}