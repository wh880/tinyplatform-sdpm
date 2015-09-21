package org.tinygroup.sdpm.quality.biz.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.quality.biz.inter.BugManager;
import org.tinygroup.sdpm.quality.dao.BugDao;
import org.tinygroup.sdpm.quality.dao.pojo.Bug;

@Service        //注解
@Transactional  //开启事务
public class BugManagerImpl implements BugManager {
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
