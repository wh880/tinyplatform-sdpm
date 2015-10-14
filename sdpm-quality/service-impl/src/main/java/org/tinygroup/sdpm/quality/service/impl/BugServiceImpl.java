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
		bug.setBugStatus("1");
		return bugmanager.add(bug);
	}
	
	public QualityBug findById(Integer id){
		return bugmanager.find(id);
	}
	
	public int updateBug(QualityBug bug){
		return bugmanager.update(bug);
	}
	
	public int[] batchUpdateBug(List<QualityBug> bugs){
		return bugmanager.batchUpdate(bugs);
	}
	
	public Pager<QualityBug> findBugListPager(Integer start,Integer limit,String conditions,QualityBug bug,String sortName,boolean asc){
		return bugmanager.findPager(start, limit, conditions, bug, sortName, asc);
	}

	public Integer deleteBug(Integer bugId) {
		
		return bugmanager.delete(bugId);
	}

	public int[] batchDeleteBug(List<QualityBug> bugIds) {
		
		return bugmanager.batchDelete(bugIds);
	}
}