package org.tinygroup.sdpm.quality.service.inter;

import java.util.List;

import org.tinygroup.sdpm.quality.dao.pojo.TestRun;

public interface TestRunService {
	
	/**
	 * 条件查询
	 * @param testrun
	 * @return
	 */
	List<TestRun> findTestRunList(TestRun testrun);
	/**
	 * 编辑
	 * @param testrun
	 * @return
	 */
	int updateTestRun(TestRun testrun);
	/**
	 * 批量编辑
	 * @param testruns
	 * @return
	 */
	int[] batchUpdateTestRun(List<TestRun> testruns);
}