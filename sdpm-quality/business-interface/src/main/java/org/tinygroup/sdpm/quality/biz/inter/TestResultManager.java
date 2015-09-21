package org.tinygroup.sdpm.quality.biz.inter;

import java.util.List;

import org.tinygroup.sdpm.quality.dao.pojo.TestResult;

public interface TestResultManager {
	
	/**
	 * 测试结果对象查询	
	 * @param testresult
	 * @return
	 */
	List<TestResult> findList(TestResult testresult);
	/**
	 * 通过主键id查询
	 * @param id
	 * @return
	 */
	TestResult find(int id);
	/**
	 * 编辑
	 * @param testresult
	 * @return
	 */
	int update(TestResult testresult);

}
