package org.tinygroup.sdpm.quality.service.inter;

import org.tinygroup.sdpm.quality.dao.pojo.QualityTestRun;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

public interface TestRunService {
	
	/**
	 * 条件查询
	 * @param testrun
	 * @return
	 */
	List<QualityTestRun> findTestRunList(QualityTestRun testrun);
	/**
	 * 编辑
	 * @param testrun
	 * @return
	 */
	int updateTestRun(QualityTestRun testrun);
	/**
	 * 批量编辑
	 * @param testruns
	 * @return
	 */
	int[] batchUpdateTestRun(List<QualityTestRun> testruns);

	QualityTestRun add(QualityTestRun run);

	QualityTestRun findRunById(Integer id);

	int delete(Integer runId);

	Pager<QualityTestRun> findTestRunPager(Integer start, Integer limit, QualityTestRun testRun,String condition, String sortName, boolean asc);
}