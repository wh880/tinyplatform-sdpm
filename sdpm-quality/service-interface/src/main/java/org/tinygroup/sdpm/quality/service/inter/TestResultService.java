package org.tinygroup.sdpm.quality.service.inter;

import java.util.List;

import org.tinygroup.sdpm.quality.dao.pojo.TestResult;

public interface TestResultService {
	/**
	 * 测试结果对象查询	
	 * @param testresult
	 * @return
	 */
	List<TestResult> findTestResultList(TestResult testresult);
	/**
	 * 通过主键id查询
	 * @param id
	 * @return
	 */
	TestResult findByid(int id);
}
