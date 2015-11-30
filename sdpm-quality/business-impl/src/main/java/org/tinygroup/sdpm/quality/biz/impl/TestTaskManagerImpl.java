package org.tinygroup.sdpm.quality.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.quality.biz.inter.TestTaskManager;
import org.tinygroup.sdpm.quality.dao.QualityTestTaskDao;
import org.tinygroup.sdpm.quality.dao.pojo.QualityTestTask;
import org.tinygroup.tinysqldsl.Pager;
import org.tinygroup.tinysqldsl.base.Condition;
import org.tinygroup.tinysqldsl.base.FragmentSql;

import java.util.List;

@Service
@Transactional
public class TestTaskManagerImpl implements TestTaskManager {
	
	@Autowired
	private QualityTestTaskDao testtaskdao;
	
	public List<QualityTestTask> findList(QualityTestTask testtask){
		return testtaskdao.query(testtask);
	}
	
	public QualityTestTask add(QualityTestTask testtask){
		Integer no = testtaskdao.getMaxNo(testtask.getProductId());
		testtask.setNo(no==null?1:no+1);
		return testtaskdao.add(testtask);
	}
	
	public int update(QualityTestTask testtask){
		return testtaskdao.edit(testtask);
	}
	
	public int delete(int id){
		QualityTestTask testtask = new QualityTestTask();
		testtask.setTestversionId(id);
		testtask.setDeleted(QualityTestTask.DELETE_YES);
		return testtaskdao.edit(testtask);
	}
	
	public Pager<QualityTestTask> findPager(Integer start,Integer limit,QualityTestTask testtask,String condition,String sortName,boolean asc){
		Condition condition1 = StringUtil.isBlank(condition)?null: FragmentSql.fragmentCondition(condition);
		if(StringUtil.isBlank(sortName)){
			return testtaskdao.queryPager(start,limit,testtask,condition1);
		}
		OrderBy order = new OrderBy(sortName, asc);
		return testtaskdao.queryPager(start, limit, testtask,condition1, order);
	}

	public QualityTestTask find(Integer id) {
		if(id != null){
			return testtaskdao.getByKey(id);
		}
		return null;
	}
}
