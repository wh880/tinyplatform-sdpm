package org.tinygroup.sdpm.quality.service.inter;

import java.util.List;

import org.tinygroup.sdpm.quality.dao.pojo.TestTask;

public interface TestTaskService {
	/**
	 * 通过状态查询
	 * @param testtask
	 * @return
	 */
	List<TestTask> findByStatus(String testtask);
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
	int deleteById(Integer id);
	/**
	 * 需求关联查询
	 * @param testtask
	 * @return
	 */
	List<TestTask> findByLinkStory(TestTask testtask);
	/**
	 * Bug关联查询
	 * @param testtask
	 * @return
	 */
	List<TestTask> findByLinkBug(TestTask testtask);
}
