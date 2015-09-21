package org.tinygroup.sdpm.quality.biz.inter;

import java.util.List;

import org.tinygroup.sdpm.quality.dao.pojo.TestRun;

public interface TestRunManager {

	
	/**
	 * 条件查询
	 * @param testrun
	 * @return
	 */
	List<TestRun> findList(TestRun testrun);
	/**
	 * 编辑
	 * @param testrun
	 * @return
	 */
	int update(TestRun testrun);
}
