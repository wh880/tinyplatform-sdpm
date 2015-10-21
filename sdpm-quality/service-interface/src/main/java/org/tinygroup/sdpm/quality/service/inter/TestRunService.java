package org.tinygroup.sdpm.quality.service.inter;

import java.util.List;

import org.tinygroup.sdpm.quality.dao.pojo.QualityTestRun;
import org.tinygroup.sdpm.quality.dao.pojo.QualityTestTask;
import org.tinygroup.tinysqldsl.Pager;

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

	Pager<QualityTestRun> findTestRunPager(Integer start, Integer limit, QualityTestRun testRun,String condition, String sortName, boolean asc);
}