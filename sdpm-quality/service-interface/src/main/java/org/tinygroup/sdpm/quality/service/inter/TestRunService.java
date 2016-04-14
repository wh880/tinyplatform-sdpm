package org.tinygroup.sdpm.quality.service.inter;

import org.tinygroup.sdpm.dao.condition.ConditionCarrier;
import org.tinygroup.sdpm.quality.dao.pojo.QualityTestRun;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

public interface TestRunService {

    /**
     * 条件查询
     *
     * @param testRun
     * @return
     */
    List<QualityTestRun> findTestRunList(QualityTestRun testRun);

    /**
     * 编辑
     *
     * @param testrun
     * @return
     */
    int updateTestRun(QualityTestRun testrun);

    /**
     * 新增测试运行
     *
     * @param run
     * @return
     */
    QualityTestRun addTestRun(QualityTestRun run);

    /**
     * 根据id获取运行
     *
     * @param id
     * @return
     */
    QualityTestRun findTestRunById(Integer id);

    /**
     * 根据id删除运行
     *
     * @param runId
     * @return
     */
    int deleteTestRun(Integer runId);

    /**
     * 复合条件-排序-分页查询运行
     *
     * @param start
     * @param limit
     * @param testRun
     * @param carrier
     * @param sortName
     * @param asc
     * @return
     */
    Pager<QualityTestRun> findTestRunPager(Integer start, Integer limit, QualityTestRun testRun, ConditionCarrier carrier, String sortName, boolean asc);

    /**
     * 根据测试标识获取QualityTestRun列表
     * @param testversionId
     * @return
     */
    List<QualityTestRun> findTestRunByTestVersionId(Integer testversionId);
}