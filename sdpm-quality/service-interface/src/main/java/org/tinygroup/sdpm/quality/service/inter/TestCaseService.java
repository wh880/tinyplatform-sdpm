package org.tinygroup.sdpm.quality.service.inter;

import java.util.List;

import org.tinygroup.sdpm.quality.dao.pojo.TestCase;

public interface TestCaseService {
	/**
	 * 通过对象查询
	 * @param festcase
	 * @return
	 */
	List<TestCase> findTestCaseList(TestCase festcase);
	/**
	 * 通过主键id查询
	 * @param story
	 * @return
	 */
	List<TestCase> findById(Integer id);
	/**
	 * 建用例
	 * @param testcase
	 * @return
	 */
	TestCase addTestCase(TestCase testcase);
	/**
	 * 用例修改
	 * @param testcase
	 * @return
	 */
	int updateTestCase(TestCase testcase);
	/**
	 * 删除用例
	 * @param id
	 * @return
	 */
	int deleteById(Integer id);
	
}