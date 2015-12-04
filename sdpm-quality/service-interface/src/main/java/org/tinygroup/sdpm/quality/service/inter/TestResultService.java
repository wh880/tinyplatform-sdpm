package org.tinygroup.sdpm.quality.service.inter;

import org.tinygroup.sdpm.quality.dao.pojo.QualityTestResult;

import java.util.List;

public interface TestResultService {
    /**
     * 测试结果对象查询
     * @param qualityTestResult
     * @return
     */
    List<QualityTestResult> findTestResultList(QualityTestResult qualityTestResult);

    /**
     * 新增测试结果
     * @param qualityTestResult
     * @return
     */
    QualityTestResult addTestResult(QualityTestResult qualityTestResult);
}
