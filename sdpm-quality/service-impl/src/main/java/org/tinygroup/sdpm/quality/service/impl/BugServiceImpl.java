package org.tinygroup.sdpm.quality.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.tinygroup.sdpm.quality.dao.BugDao;
import org.tinygroup.sdpm.quality.dao.pojo.Bug;
import org.tinygroup.sdpm.quality.service.inter.BugService;

public class BugServiceImpl implements BugService {
	@Autowired
	private BugDao bugdao;
	
	public List<Bug> findBugList(Bug bug){				
		return bugdao.query(bug);
	}
	
	public Bug addBug(Bug bug){
		return bugdao.add(bug);
	}
	
	public Bug findById(int id){
		return bugdao.getByKey(id);
	}
	
	public int updateBug(Bug bug){
		return bugdao.edit(bug);
	}
}
