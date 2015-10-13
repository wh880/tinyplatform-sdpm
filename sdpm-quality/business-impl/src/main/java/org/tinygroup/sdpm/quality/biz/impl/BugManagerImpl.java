package org.tinygroup.sdpm.quality.biz.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.quality.biz.inter.BugManager;
import org.tinygroup.sdpm.quality.dao.QualityBugDao;
import org.tinygroup.sdpm.quality.dao.pojo.QualityBug;
import org.tinygroup.tinysqldsl.Pager;
import org.tinygroup.tinysqldsl.expression.FragmentExpressionSql;

@Service        
@Transactional  
public class BugManagerImpl implements BugManager {
	@Autowired
	private QualityBugDao bugdao;
	
	public List<QualityBug> findList(QualityBug bug){			
		return bugdao.query(bug);
	}
	
	public QualityBug add(QualityBug bug){
				
		return bugdao.add(bug);
	}
	
	public QualityBug find(Integer id){
		return bugdao.getByKey(id);
	}
	
	public Integer update(QualityBug bug){
		
		return bugdao.edit(bug);
	}
	
	public int[] batchUpdate(List<QualityBug> bugs){
		
		return bugdao.batchUpdate(bugs);
	}
	
	public Pager<QualityBug> findPager(Integer start,Integer limit,String conditions,QualityBug bug,String sortName,boolean asc){
		if(StringUtil.isBlank(sortName)){
			return bugdao.queryPager(start,limit,(((conditions!=null&&!"".equals(conditions))? FragmentExpressionSql.fragmentCondition(conditions):null)),bug);
		}else{
			OrderBy orderby = new OrderBy(sortName,asc);
			return bugdao.queryPager(start, limit, (((conditions!=null&&!"".equals(conditions))?FragmentExpressionSql.fragmentCondition(conditions):null)), bug, orderby);
		}		
	}

	public Integer delete(Integer id) {
		
		return bugdao.softDelete(id);
	}

	public int[] batchDelete(List<QualityBug> bugIds) {
		
		return bugdao.batchUpdateDel(bugIds);
	}

}
