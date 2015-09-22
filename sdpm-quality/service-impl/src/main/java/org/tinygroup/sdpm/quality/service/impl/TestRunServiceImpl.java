package org.tinygroup.sdpm.quality.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.quality.biz.inter.TestRunManager;
import org.tinygroup.sdpm.quality.dao.pojo.TestRun;
import org.tinygroup.sdpm.quality.service.inter.TestRunService;

@Component
public class TestRunServiceImpl implements TestRunService {
	
	@Autowired
	private TestRunManager testrunmanager;
	
	public List<TestRun> findTestRunList(TestRun testrun){
		return testrunmanager.findList(testrun);
	}
	
	public int updateTestRun(TestRun testrun){
		return testrunmanager.update(testrun);
	}

}
