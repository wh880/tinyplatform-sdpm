package org.tinygroup.sdpm.quality.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.dao.condition.ConditionCarrier;
import org.tinygroup.sdpm.quality.biz.inter.TestRunManager;
import org.tinygroup.sdpm.quality.dao.pojo.QualityTestRun;
import org.tinygroup.sdpm.quality.service.inter.TestRunService;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

@Component
public class TestRunServiceImpl implements TestRunService {

    @Autowired
    private TestRunManager testRunManager;

    public List<QualityTestRun> findTestRunList(QualityTestRun testRun) {
        return testRunManager.findList(testRun);
    }

    public int updateTestRun(QualityTestRun testRun) {
        return testRunManager.update(testRun);
    }

    public QualityTestRun addTestRun(QualityTestRun run) {
        return testRunManager.add(run);
    }

    public QualityTestRun findTestRunById(Integer id) {
        return testRunManager.findRunById(id);
    }

    public int deleteTestRun(Integer runId) {
        return testRunManager.delete(runId);
    }

    public Pager<QualityTestRun> findTestRunPager(Integer start, Integer limit, QualityTestRun testRun, ConditionCarrier carrier, String sortName, boolean asc) {
        return testRunManager.findPager(start, limit, testRun, carrier, sortName, asc);
    }

}
