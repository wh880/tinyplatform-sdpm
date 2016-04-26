package org.tinygroup.sdpm.quality.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.common.util.common.NameUtil;
import org.tinygroup.sdpm.dao.condition.CallBackFunction;
import org.tinygroup.sdpm.dao.condition.ConditionCarrier;
import org.tinygroup.sdpm.dao.condition.ConditionUtils;
import org.tinygroup.sdpm.quality.biz.inter.TestCaseManager;
import org.tinygroup.sdpm.quality.dao.QualityTestCaseDao;
import org.tinygroup.sdpm.quality.dao.QualityTestRunDao;
import org.tinygroup.sdpm.quality.dao.pojo.QualityTestCase;
import org.tinygroup.sdpm.system.dao.impl.util.ModuleUtil;
import org.tinygroup.tinysqldsl.Pager;
import org.tinygroup.tinysqldsl.base.Condition;

import java.util.Date;
import java.util.List;

/**
 * Created by chenpeng15668 on 2015-9-24
 */
@Service
public class TestCaseManagerImpl implements TestCaseManager {

    @Autowired
    private QualityTestCaseDao testcasedao;
    @Autowired
    private QualityTestRunDao testRunDao;

    public List<QualityTestCase> findList(QualityTestCase testcase) {
        return testcasedao.query(testcase);
    }

    public QualityTestCase find(Integer id) {
        return testcasedao.getByKey(id);
    }

    public QualityTestCase add(QualityTestCase testcase) {
        Integer no = testcasedao.getMaxNo(testcase.getProductId());
        testcase.setNo(no == null ? 1 : no + 1);
        testcase.setCaseOpenedDate(new Date());
        return testcasedao.add(testcase);
    }

    public Integer update(QualityTestCase testcase) {
        testcase.setCaseLastEditedDate(new Date());
        return testcasedao.edit(testcase);
    }

    public int[] batchUpdate(List<QualityTestCase> testcases) {
        QualityTestCase testcase = new QualityTestCase();
        testcase.setCaseLastEditedDate(new Date());
        return testcasedao.batchUpdate(testcases);
    }

    public Integer delete(Integer id) {
        testRunDao.deleteByCase(id);
        return testcasedao.softDelete(id);

    }

    public int[] batchDelete(List<QualityTestCase> testcases) {
        for (QualityTestCase testCase : testcases) {
            testCase.setDeleted(QualityTestCase.DELETE_YES);
        }
        return testcasedao.batchUpdate(testcases);
    }

    public Pager<QualityTestCase> findPager(Integer start, Integer limit, QualityTestCase testcase, String sortName, boolean asc) {
        if (StringUtil.isBlank(sortName)) {
            return testcasedao.queryPager(start, limit, testcase);
        }
        OrderBy order = new OrderBy(NameUtil.resolveNameDesc(sortName), asc);
        return testcasedao.queryPager(start, limit, testcase, order);
    }

    public Pager<QualityTestCase> findPagerRel(Integer start, Integer limit, QualityTestCase testcase, ConditionCarrier carrier, String columnName, boolean asc) {

        Condition condition = mergeCondition(carrier);
        if (condition != null && !"".equals(condition.toString())) {
            if (!StringUtil.isBlank(columnName)) {
                return testcasedao.queryPagerRel(start, limit, testcase, condition, new OrderBy("quality_test_case." + NameUtil.resolveNameDesc(columnName), asc));
            }
        }
        if (StringUtil.isBlank(columnName)) {
            return testcasedao.queryPager(start, limit, testcase);
        }
        return testcasedao.queryPager(start, limit, testcase, new OrderBy("quality_test_case." + NameUtil.resolveNameDesc(columnName), asc));
    }

    public Pager<QualityTestCase> findStoryChangedCase(Integer start, Integer limit, QualityTestCase testcase, ConditionCarrier carrier, String columnName, boolean asc) {

        if (StringUtil.isBlank(columnName)) {
            testcasedao.queryStoryChangedCase(start, limit, testcase, mergeCondition(carrier));
        }
        return testcasedao.queryStoryChangedCase(start, limit, testcase, mergeCondition(carrier), new OrderBy(columnName, asc));
    }

    public List<Integer> getStoryIds(QualityTestCase t) {

        return testcasedao.getStoryIds(t);
    }

    private Condition mergeCondition(ConditionCarrier carrier) {
        return ConditionUtils.mergeCondition(carrier, new CallBackFunction() {
            public String moduleRoot(String moduleId) {
                return ModuleUtil.getConditionByRootWithoutOperate(Integer.parseInt(moduleId.substring(1)), "story");
            }

            public String module(String moduleId) {
                return ModuleUtil.getConditionWithoutOperate(Integer.parseInt(moduleId));
            }
        });
    }
}
