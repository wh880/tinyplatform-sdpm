package org.tinygroup.sdpm.quality.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.quality.biz.inter.TestRunManager;
import org.tinygroup.sdpm.quality.dao.pojo.QualityTestRun;
import org.tinygroup.sdpm.quality.service.inter.TestRunService;
import org.tinygroup.tinysqldsl.Pager;

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

	public QualityTestRun add(QualityTestRun run) {
		return testrunmanager.add(run);
	}

	public Pager<QualityTestRun> findTestRunPager(Integer start, Integer limit, QualityTestRun testRun,String condition, String sortName, boolean asc) {
		return testrunmanager.findPager(start,limit,testRun,condition,sortName,asc);
	}

}
