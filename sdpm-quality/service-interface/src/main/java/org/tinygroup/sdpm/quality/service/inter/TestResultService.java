package org.tinygroup.sdpm.quality.service.inter;

import org.tinygroup.sdpm.quality.dao.pojo.QualityTestResult;

import java.util.List;

public interface TestResultService {
	/**
	 * 测试结果对象查询	
	 * @param testresult
	 * @return
	 */
	List<QualityTestResult> findTestResultList(QualityTestResult testresult);
	/**
	 * 通过主键id查询
	 * @param id
	 * @return
	 */
	QualityTestResult findByid(int id);
	/**
	 * 编辑
	 * @param testresult
	 * @return
	 */
	int updateTestResult(QualityTestResult testresult);

	QualityTestResult add(QualityTestResult qualityTestResult);
}
