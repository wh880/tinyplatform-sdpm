package org.tinygroup.sdpm.quality.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.quality.biz.inter.TestResultManager;
import org.tinygroup.sdpm.quality.dao.pojo.TestResult;
import org.tinygroup.sdpm.quality.service.inter.TestResultService;

@Component
public class TestResultServiceImpl implements TestResultService {

		@Autowired
		private TestResultManager testresultmanager;
		
		public List<TestResult> findTestResultList(TestResult testresult){
			return testresultmanager.findList(testresult);
		}
		
		public TestResult findByid(int id){
			return testresultmanager.find(id);
		}
		
		public int updateTestResult(TestResult testresult){
			return testresultmanager.update(testresult);
		}
		
}
