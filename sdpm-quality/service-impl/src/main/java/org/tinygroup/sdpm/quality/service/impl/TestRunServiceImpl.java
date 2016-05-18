package org.tinygroup.sdpm.quality.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.dao.condition.ConditionCarrier;
import org.tinygroup.sdpm.quality.biz.inter.TestRunManager;
import org.tinygroup.sdpm.quality.dao.pojo.QualityTestRun;
import org.tinygroup.sdpm.quality.service.inter.TestRunService;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

@Component
@Transactional
public class TestRunServiceImpl implements TestRunService {

    @Autowired
    private TestRunManager testRunManager;
    @Transactional(readOnly = true)
    public List<QualityTestRun> findTestRunList(QualityTestRun testRun) {
        return testRunManager.findList(testRun);
    }

    public int updateTestRun(QualityTestRun testRun) {
        return testRunManager.update(testRun);
    }

    public QualityTestRun addTestRun(QualityTestRun run) {
        return testRunManager.add(run);
    }
    @Transactional(readOnly = true)
    public QualityTestRun findTestRunById(Integer id) {
        return testRunManager.findRunById(id);
    }

    public int deleteTestRun(Integer runId) {
        return testRunManager.delete(runId);
    }
    @Transactional(readOnly = true)
    public Pager<QualityTestRun> findTestRunPager(Integer start, Integer limit, QualityTestRun testRun, ConditionCarrier carrier, String sortName, boolean asc) {
        return testRunManager.findPager(start, limit, testRun, carrier, sortName, asc);
    }

    @Override
    public List<QualityTestRun> findTestRunByTestVersionId(Integer testversionId) {
        return testRunManager.findTestRunByTestVersionId(testversionId);
    }

}
