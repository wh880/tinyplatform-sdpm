package org.tinygroup.sdpm.quality.service.inter;

import java.util.List;

import org.tinygroup.sdpm.quality.dao.pojo.QualityTestRun;

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
}