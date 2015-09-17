package org.tinygroup.sdpm.quality.service.inter;

import java.util.List;

import org.tinygroup.sdpm.quality.dao.pojo.TestCase;

public interface TestCaseService {
	/**
	 * 通过对象查询
	 * @param festcase
	 * @return
	 */
	List<TestCase> findByTestCase(TestCase festcase);
	/**
	 * 通过需求查询
	 * @param story
	 * @return
	 */
	List<TestCase> findByStory(Integer story);
	/**
	 * 建用例
	 * @param testcase
	 * @return
	 */
	TestCase add(TestCase testcase);
	/**
	 * 用例修改
	 * @param testcase
	 * @return
	 */
	int update(TestCase testcase);
	/**
	 * 删除用例
	 * @param id
	 * @return
	 */
	int deleteById(Integer id);
	/**
	 * 转Bug
	 */
	
}
