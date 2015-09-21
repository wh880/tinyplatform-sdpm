package org.tinygroup.sdpm.quality.biz.inter;

import java.util.List;

import org.tinygroup.sdpm.quality.dao.pojo.TestTask;

public interface TestTaskManager {

	/**
	 * 通过条件查询
	 * @param testtask
	 * @return
	 */
	List<TestTask> findList(TestTask testtask);
	/**
	 * 提交测试
	 * @param testtask
	 * @return
	 */
	TestTask add(TestTask testtask);
	/**
	 * 编辑测试
	 * @param testtask
	 * @return
	 */
	int update(TestTask testtask);
	/**
	 * 删除测试
	 * @param id
	 * @return
	 */
	int delete(int id);
}
