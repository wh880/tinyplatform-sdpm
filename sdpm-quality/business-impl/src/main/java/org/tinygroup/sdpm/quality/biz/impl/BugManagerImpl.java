package org.tinygroup.sdpm.quality.biz.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.quality.biz.inter.BugManager;
import org.tinygroup.sdpm.quality.dao.BugDao;
import org.tinygroup.sdpm.quality.dao.pojo.Bug;

@Service        //注解，告诉spring创建一个实现类的实例
@Transactional  //开启事务
public class BugManagerImpl implements BugManager {
	@Autowired
	private BugDao bugdao;
	
	public List<Bug> findList(Bug bug){			
		return bugdao.query(bug);
	}
	
	public Bug add(Bug bug){
		bug.setBugOpenedDate(new Date());		
		return bugdao.add(bug);
	}
	
	public Bug find(int id){
		return bugdao.getByKey(id);
	}
	
	public int update(Bug bug){
		bug.setBugLastEditedDate(new Date());
		bug.setBugLastEditedBy(bug.getBugLastEditedBy());
		return bugdao.edit(bug);
	}
}
