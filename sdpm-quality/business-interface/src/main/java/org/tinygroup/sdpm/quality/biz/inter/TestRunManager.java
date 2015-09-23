package org.tinygroup.sdpm.quality.biz.inter;

import java.util.List;

import org.tinygroup.sdpm.quality.dao.pojo.QualityTestRun;

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
}
