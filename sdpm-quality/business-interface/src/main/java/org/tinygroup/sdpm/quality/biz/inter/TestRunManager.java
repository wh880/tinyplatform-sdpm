package org.tinygroup.sdpm.quality.biz.inter;

import org.tinygroup.sdpm.dao.condition.ConditionCarrier;
import org.tinygroup.sdpm.quality.dao.pojo.QualityTestRun;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

public interface TestRunManager {


    /**
     * 条件查询
     *
     * @param testrun
     * @return
     */
    List<QualityTestRun> findList(QualityTestRun testrun);

    /**
     * 编辑
     *
     * @param testrun
     * @return
     */
    int update(QualityTestRun testrun);

    /**
     * 批量编辑
     *
     * @param testruns
     * @return
     */
    int[] batchUpdate(List<QualityTestRun> testruns);

    QualityTestRun add(QualityTestRun run);

    QualityTestRun findRunById(Integer id);

    int delete(Integer runId);

    Pager<QualityTestRun> findPager(Integer start, Integer limit, QualityTestRun testRun, ConditionCarrier carrier, String sortName, boolean asc);

    List<QualityTestRun> findTestRunByTestVersionId(Integer testversionId);
}
