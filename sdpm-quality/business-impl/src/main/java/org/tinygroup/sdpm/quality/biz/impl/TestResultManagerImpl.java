package org.tinygroup.sdpm.quality.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.quality.biz.inter.TestResultManager;
import org.tinygroup.sdpm.quality.dao.TestResultDao;
import org.tinygroup.sdpm.quality.dao.pojo.TestResult;

@Service
@Transactional
public class TestResultManagerImpl implements TestResultManager {
	
	@Autowired
	private TestResultDao testresultdao;
	
	public List<TestResult> findList(TestResult testresult){
		return testresultdao.query(testresult);
	}
	
	public TestResult find(int id){
		return testresultdao.getByKey(id);
	}
	
	public int update(TestResult testresult){
		return testresultdao.edit(testresult);
	}
}
