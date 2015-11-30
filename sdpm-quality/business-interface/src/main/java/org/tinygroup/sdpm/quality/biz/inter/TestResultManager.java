package org.tinygroup.sdpm.quality.biz.inter;

import org.tinygroup.sdpm.quality.dao.pojo.QualityTestResult;

import java.util.List;

public interface TestResultManager {

    /**
     * 测试结果对象查询
     *
     * @param testresult
     * @return
     */
    List<QualityTestResult> findList(QualityTestResult testresult);

    /**
     * 通过主键id查询
     *
     * @param id
     * @return
     */
    QualityTestResult find(int id);

    /**
     * 编辑
     *
     * @param testresult
     * @return
     */
    int update(QualityTestResult testresult);

    QualityTestResult add(QualityTestResult qualityTestResult);

}
