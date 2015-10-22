package org.tinygroup.sdpm.quality.biz.inter;

import java.util.List;

import org.tinygroup.sdpm.quality.dao.pojo.QualityTestRun;
import org.tinygroup.sdpm.quality.dao.pojo.QualityTestTask;
import org.tinygroup.tinysqldsl.Pager;

public interface TestRunManager {

	
	/**
	 * 条件查询
	 * @param testrun
	 * @return
	 */
	List<QualityTestRun> findList(QualityTestRun testrun);
	/**
	 * 编辑
	 * @param testrun
	 * @return
	 */
	int update(QualityTestRun testrun);
	/**
	 * 批量编辑
	 * @param testruns
	 * @return
	 */
	int[] batchUpdate(List<QualityTestRun> testruns);

	QualityTestRun add(QualityTestRun run);

	Pager<QualityTestRun> findPager(Integer start, Integer limit, QualityTestRun testRun,String condition, String sortName, boolean asc);
}
