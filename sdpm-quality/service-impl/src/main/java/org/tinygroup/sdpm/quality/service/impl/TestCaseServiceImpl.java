package org.tinygroup.sdpm.quality.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.common.util.ComplexSearch.SearchInfos;
import org.tinygroup.sdpm.quality.biz.inter.TestCaseManager;
import org.tinygroup.sdpm.quality.dao.pojo.QualityTestCase;
import org.tinygroup.sdpm.quality.service.inter.TestCaseService;
import org.tinygroup.tinysqldsl.Pager;

@Component
public class TestCaseServiceImpl implements TestCaseService {
	@Autowired
	private TestCaseManager testcasemanager;
	
	public List<QualityTestCase> findTestCaseList(QualityTestCase testcase){
		return testcasemanager.findList(testcase);
	}
	
	public QualityTestCase findById(int id){
		return testcasemanager.find(id);
	}
	
	public QualityTestCase addTestCase(QualityTestCase testcase){
		return testcasemanager.add(testcase);
	}
	
	public int updateTestCase(QualityTestCase testcase){
		return testcasemanager.update(testcase);
	}
	
	public int[] batchUpdateTestCase(List<QualityTestCase> testcases){
		return testcasemanager.batchUpdate(testcases);
	}
	
	public int deleteById(int id){
		return testcasemanager.delete(id);
	}
	
	public int[] batchDeleteTestCase(List<QualityTestCase> testcases){
		return testcasemanager.batchDelete(testcases);
	}
	
	public Pager<QualityTestCase> findTestCasePager(Integer start,
			Integer limit, QualityTestCase testcase, String sortName,
			boolean asc) {

		return testcasemanager.findPager(start, limit, testcase, sortName, asc);
	}
	
	public Pager<QualityTestCase> findTestCasePager(Integer start,Integer limit,QualityTestCase testcase, String statusCondition, SearchInfos conditions,
            String groupOperate,String columnName,boolean asc){
		return testcasemanager.findPagerRel(start, limit, testcase, statusCondition, conditions, groupOperate, columnName, asc);
	}

	
}
