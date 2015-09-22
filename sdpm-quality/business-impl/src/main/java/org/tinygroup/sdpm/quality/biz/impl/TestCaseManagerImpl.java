package org.tinygroup.sdpm.quality.biz.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.quality.biz.inter.TestCaseManager;
import org.tinygroup.sdpm.quality.dao.TestCaseDao;
import org.tinygroup.sdpm.quality.dao.pojo.TestCase;

@Service
@Transactional
public class TestCaseManagerImpl implements TestCaseManager {
	
	@Autowired
	private TestCaseDao testcasedao;
	
	public List<TestCase> findList(TestCase testcase){
		return testcasedao.query(testcase);
	}
	
	public TestCase find(int id){
		return testcasedao.getByKey(id);
	}
	
	public TestCase add(TestCase testcase){
		testcase.setCaseScriptedDate(new Date());
		return testcasedao.add(testcase);
	}
	
	public int update(TestCase testcase){
		testcase.setCaseLastEditedDate(new Date());
		return testcasedao.edit(testcase);
	}
	
	public int[] batchUpdate(List<TestCase> testcases){
		TestCase testcase = new TestCase();
		testcase.setCaseLastEditedDate(new Date());
		return testcasedao.batchUpdate(testcases);
	}
	
	public int delete(int id){
		TestCase testcase = new TestCase();
		testcase.setCaseId(id);
		testcase.setDeleted(TestCase.DELETE_YES);
		return testcasedao.edit(testcase);
	}
	
	public int[] batchDelete(List<TestCase> testcases){
		for(TestCase testCase:testcases){
			testCase.setDeleted(TestCase.DELETE_YES);
		}
		return testcasedao.batchUpdate(testcases);
	}
}
