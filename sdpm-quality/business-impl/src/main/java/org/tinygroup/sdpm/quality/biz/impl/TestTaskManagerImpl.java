package org.tinygroup.sdpm.quality.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.quality.biz.inter.TestTaskManager;
import org.tinygroup.sdpm.quality.dao.QualityTestTaskDao;
import org.tinygroup.sdpm.quality.dao.pojo.QualityTestTask;

@Service
@Transactional
public class TestTaskManagerImpl implements TestTaskManager {
	
	@Autowired
	private QualityTestTaskDao testtaskdao;
	
	public List<QualityTestTask> findList(QualityTestTask testtask){
		return testtaskdao.query(testtask);
	}
	
	public QualityTestTask add(QualityTestTask testtask){
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
}
