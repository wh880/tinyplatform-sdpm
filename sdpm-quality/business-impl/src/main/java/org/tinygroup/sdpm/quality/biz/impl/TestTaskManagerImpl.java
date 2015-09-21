package org.tinygroup.sdpm.quality.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.quality.biz.inter.TestTaskManager;
import org.tinygroup.sdpm.quality.dao.TestTaskDao;
import org.tinygroup.sdpm.quality.dao.pojo.TestTask;

@Service
@Transactional
public class TestTaskManagerImpl implements TestTaskManager {
	
	@Autowired
	private TestTaskDao testtaskdao;
	
	public List<TestTask> findList(TestTask testtask){
		return testtaskdao.query(testtask);
	}
	
	public TestTask add(TestTask testtask){
		return testtaskdao.add(testtask);
	}
	
	public int update(TestTask testtask){
		return testtaskdao.edit(testtask);
	}
	
	public int delete(int id){
		TestTask testtask = new TestTask();
		testtask.setTestversionId(id);
		testtask.setDeleted(TestTask.DELETE_YES);
		return testtaskdao.edit(testtask);
	}
}
