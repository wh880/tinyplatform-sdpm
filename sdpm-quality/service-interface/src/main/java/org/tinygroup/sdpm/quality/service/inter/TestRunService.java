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

    QualityTestRun addTestRun(QualityTestRun run);

    QualityTestRun findTestRunById(Integer id);

    int deleteTestRun(Integer runId);

    Pager<QualityTestRun> findTestRunPager(Integer start, Integer limit, QualityTestRun testRun, ConditionCarrier carrier, String sortName, boolean asc);
}