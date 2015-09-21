package org.tinygroup.sdpm.quality.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.quality.dao.BugDao;
import org.tinygroup.sdpm.quality.dao.pojo.Bug;
import org.tinygroup.sdpm.quality.service.inter.BugService;

@Component("bugService")
public class BugServiceImpl implements BugService {
	@Autowired
	private BugDao bugdao;
	
	public List<Bug> findBugList(Bug bug){				
		return bugdao.query(bug);
	}
	
	public Bug addBug(Bug bug){
		bug.setBugOpenedDate(new Date());
		bug.setBugOpenedBy(bug.getBugOpenedBy());
		return bugdao.add(bug);
	}
	
	public Bug findById(int id){
		return bugdao.getByKey(id);
	}
	
	public int updateBug(Bug bug){
		bug.setBugLastEditedDate(new Date());
		bug.setBugLastEditedBy(bug.getBugLastEditedBy());
		return bugdao.edit(bug);
	}
}