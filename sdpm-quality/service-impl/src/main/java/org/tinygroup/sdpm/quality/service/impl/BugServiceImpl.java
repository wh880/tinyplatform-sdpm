package org.tinygroup.sdpm.quality.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.quality.biz.inter.BugManager;
import org.tinygroup.sdpm.quality.dao.pojo.QualityBug;
import org.tinygroup.sdpm.quality.service.inter.BugService;
import org.tinygroup.tinysqldsl.Pager;

@Component
public class BugServiceImpl implements BugService {
	@Autowired
	private BugManager bugmanager;
	
	public List<QualityBug> findBugList(QualityBug bug){				
		return bugmanager.findList(bug);
	}
	
	public QualityBug addBug(QualityBug bug){
		return bugmanager.add(bug);
	}
	
	public QualityBug findById(int id){
		return bugmanager.find(id);
	}
	
	public int updateBug(QualityBug bug){
		return bugmanager.update(bug);
	}
	
	public int[] batchUpdateBug(List<QualityBug> bugs){
		return bugmanager.batchUpdate(bugs);
	}
	
	public Pager<QualityBug> findBugListPager(Integer start,Integer limit,QualityBug bug,String sortName,boolean asc){
		return bugmanager.findPager(start, limit, bug, sortName, asc);	
	}
}