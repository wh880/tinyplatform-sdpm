package org.tinygroup.sdpm.quality.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.dao.condition.CallBackFunction;
import org.tinygroup.sdpm.dao.condition.ConditionCarrier;
import org.tinygroup.sdpm.dao.condition.ConditionUtils;
import org.tinygroup.sdpm.quality.biz.inter.TestRunManager;
import org.tinygroup.sdpm.quality.dao.QualityTestRunDao;
import org.tinygroup.sdpm.quality.dao.pojo.QualityTestRun;
import org.tinygroup.sdpm.system.dao.impl.util.ModuleUtil;
import org.tinygroup.tinysqldsl.Pager;
import org.tinygroup.tinysqldsl.base.Condition;

import java.util.Date;
import java.util.List;

@Service
public class TestRunManagerImpl implements TestRunManager {

    @Autowired
    private QualityTestRunDao testrundao;

    public List<QualityTestRun> findList(QualityTestRun testrun) {
        return testrundao.query(testrun);
    }

    public int update(QualityTestRun testrun) {
        return testrundao.edit(testrun);
    }

    public int[] batchUpdate(List<QualityTestRun> testruns) {
        QualityTestRun testrun = new QualityTestRun();
        testrun.setTestRunLastRunDate(new Date());
        return testrundao.batchUpdate(testruns);
    }

    public QualityTestRun add(QualityTestRun run) {
        return testrundao.add(run);
    }

    public QualityTestRun findRunById(Integer id) {
        return testrundao.getByKey(id);
    }

    public int delete(Integer runId) {
        return testrundao.deleteByKey(runId);
    }

    public Pager<QualityTestRun> findPager(Integer start, Integer limit, QualityTestRun testRun, ConditionCarrier carrier, String sortName, boolean asc) {
        if (StringUtil.isBlank(sortName)) {
            return testrundao.queryPager(start, limit, testRun, mergeCondition(carrier));
        }
        return testrundao.queryPager(start, limit, testRun, mergeCondition(carrier), new OrderBy(sortName, asc));
    }

    @Override
    public List<QualityTestRun> findTestRunByTestVersionId(Integer testversionId) {
        return testrundao.findTestRunByTestVersionId(testversionId);
    }

    private Condition mergeCondition(ConditionCarrier carrier) {
        return ConditionUtils.mergeCondition(carrier, new CallBackFunction() {
            public String moduleRoot(String moduleId) {
                return ModuleUtil.getConditionByRootWithoutOperate(Integer.parseInt(moduleId.substring(1)), "bug");
            }

            public String module(String moduleId) {
                return ModuleUtil.getConditionWithoutOperate(Integer.parseInt(moduleId));
            }
        });
    }
}
