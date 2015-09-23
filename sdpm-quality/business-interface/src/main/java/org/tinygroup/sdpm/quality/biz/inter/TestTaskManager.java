package org.tinygroup.sdpm.quality.biz.inter;

import java.util.List;

import org.tinygroup.sdpm.quality.dao.pojo.QualityTestTask;

public interface TestTaskManager {

	/**
	 * 通过条件查询
	 * @param testtask
	 * @return
	 */
	List<QualityTestTask> findList(QualityTestTask testtask);
	/**
	 * 提交测试
	 * @param testtask
	 * @return
	 */
	QualityTestTask add(QualityTestTask testtask);
	/**
	 * 编辑测试
	 * @param testtask
	 * @return
	 */
	int update(QualityTestTask testtask);
	/**
	 * 删除测试
	 * @param id
	 * @return
	 */
	int delete(int id);
}
