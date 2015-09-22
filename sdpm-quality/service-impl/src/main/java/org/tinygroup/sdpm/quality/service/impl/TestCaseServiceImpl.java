package org.tinygroup.sdpm.quality.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.quality.biz.inter.TestCaseManager;
import org.tinygroup.sdpm.quality.dao.pojo.TestCase;
import org.tinygroup.sdpm.quality.service.inter.TestCaseService;

@Component()
public class TestCaseServiceImpl implements TestCaseService {
	@Autowired
	private TestCaseManager testcasemanager;
	
	public List<TestCase> findTestCaseList(TestCase testcase){
		return testcasemanager.findList(testcase);
	}
	
	public TestCase findById(int id){
		return testcasemanager.find(id);
	}
	
	public TestCase addTestCase(TestCase testcase){
		return testcasemanager.add(testcase);
	}
	
	public int updateTestCase(TestCase testcase){
		return testcasemanager.update(testcase);
	}
	
	public int[] batchUpdateTestCase(List<TestCase> testcases){
		return testcasemanager.batchUpdate(testcases);
	}
	
	public int deleteById(int id){
		return testcasemanager.delete(id);
	}
	
	public int[] batchDeleteTestCase(List<TestCase> testcases){
		return testcasemanager.batchDelete(testcases);
	}
}
