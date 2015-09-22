package org.tinygroup.sdpm.quality.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.quality.biz.inter.TestTaskManager;
import org.tinygroup.sdpm.quality.dao.pojo.TestTask;
import org.tinygroup.sdpm.quality.service.inter.TestTaskService;

@Component
public class TestTaskServiceImpl implements TestTaskService {
	
	@Autowired
	private TestTaskManager testtaskmanager;
	
	public List<TestTask> findTestTaskList(TestTask testtask){
		return testtaskmanager.findList(testtask);
	}
	
	public TestTask addTestTask(TestTask testtask){
		return testtaskmanager.add(testtask);
	}
	
	public int updateTestTask(TestTask testtask){
		return testtaskmanager.update(testtask);
	}
	
	public int deleteById(int id){
		return testtaskmanager.delete(id);
	}
}
