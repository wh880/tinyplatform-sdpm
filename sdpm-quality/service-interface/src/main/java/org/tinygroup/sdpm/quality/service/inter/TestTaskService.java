package org.tinygroup.sdpm.quality.service.inter;

import java.util.List;

import org.tinygroup.sdpm.quality.dao.pojo.QualityTestTask;
import org.tinygroup.tinysqldsl.Pager;

public interface TestTaskService {
	/**
	 * 通过条件查询
	 * @param testtask
	 * @return
	 */
	List<QualityTestTask> findTestTaskList(QualityTestTask testtask);
	/**
	 * 通过Id查询
	 * @param id
	 * @return
	 */
	QualityTestTask findById(Integer id);
	/**
	 * 提交测试
	 * @param testtask
	 * @return
	 */
	QualityTestTask addTestTask(QualityTestTask testtask);
	/**
	 * 编辑测试
	 * @param testtask
	 * @return
	 */
	int updateTestTask(QualityTestTask testtask);
	/**
	 * 删除测试
	 * @param id
	 * @return
	 */
	int deleteById(int id);
	Pager<QualityTestTask> findTestTaskPager(Integer start,Integer limit,QualityTestTask testtask, String condition, String sortName,boolean asc);
	Pager<QualityTestTask> findTestTaskPager(Integer start,Integer limit,QualityTestTask testtask, String sortName,boolean asc);
}