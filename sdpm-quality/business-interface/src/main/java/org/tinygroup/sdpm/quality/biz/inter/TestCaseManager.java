package org.tinygroup.sdpm.quality.biz.inter;

import java.util.List;
import org.tinygroup.sdpm.quality.dao.pojo.TestCase;

public interface TestCaseManager {
	
	/**
	 * 通过对象查询
	 * @param festcase
	 * @return
	 */
	List<TestCase> findList(TestCase festcase);
	/**
	 * 通过主键id查询
	 * @param story
	 * @return
	 */
	TestCase find(int id);
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
	int delete(int id);

}
