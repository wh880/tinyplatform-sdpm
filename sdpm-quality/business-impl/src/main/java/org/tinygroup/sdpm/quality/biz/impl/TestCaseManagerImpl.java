package org.tinygroup.sdpm.quality.biz.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.quality.biz.inter.TestCaseManager;
import org.tinygroup.sdpm.quality.dao.QualityTestCaseDao;
import org.tinygroup.sdpm.quality.dao.pojo.QualityTestCase;

@Service
@Transactional
public class TestCaseManagerImpl implements TestCaseManager {
	
	@Autowired
	private QualityTestCaseDao testcasedao;
	
	public List<QualityTestCase> findList(QualityTestCase testcase){
		return testcasedao.query(testcase);
	}
	
	public QualityTestCase find(Integer id){
		return testcasedao.getByKey(id);
	}
	
	public QualityTestCase add(QualityTestCase testcase){
		testcase.setCaseScriptedDate(new Date());
		return testcasedao.add(testcase);
	}
	
	public Integer update(QualityTestCase testcase){
		testcase.setCaseLastEditedDate(new Date());
		return testcasedao.edit(testcase);
	}
	
	public int[] batchUpdate(List<QualityTestCase> testcases){
		QualityTestCase testcase = new QualityTestCase();
		testcase.setCaseLastEditedDate(new Date());
		return testcasedao.batchUpdate(testcases);
	}
	
	public Integer delete(Integer id){
		QualityTestCase testcase = new QualityTestCase();
		testcase.setCaseId(id);
		testcase.setDeleted(QualityTestCase.DELETE_YES);
		return testcasedao.edit(testcase);
	}
	
	public int[] batchDelete(List<QualityTestCase> testcases){
		for(QualityTestCase testCase:testcases){
			testCase.setDeleted(QualityTestCase.DELETE_YES);
		}
		return testcasedao.batchUpdate(testcases);
	}
}
