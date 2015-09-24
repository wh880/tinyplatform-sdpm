package org.tinygroup.sdpm.quality.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.quality.biz.inter.TestRunManager;
import org.tinygroup.sdpm.quality.dao.pojo.QualityTestRun;
import org.tinygroup.sdpm.quality.service.inter.TestRunService;

@Component
public class TestRunServiceImpl implements TestRunService {
	
	@Autowired
	private TestRunManager testrunmanager;
	
	public List<QualityTestRun> findTestRunList(QualityTestRun testrun){
		return testrunmanager.findList(testrun);
	}
	
	public int updateTestRun(QualityTestRun testrun){
		return testrunmanager.update(testrun);
	}
	
	public int[] batchUpdateTestRun(List<QualityTestRun> testruns){
		return testrunmanager.batchUpdate(testruns);
	}

}
