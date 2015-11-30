package org.tinygroup.sdpm.quality.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.common.util.ComplexSearch.SearchInfos;
import org.tinygroup.sdpm.common.util.ComplexSearch.SqlUtil;
import org.tinygroup.sdpm.common.util.common.NameUtil;
import org.tinygroup.sdpm.quality.biz.inter.TestCaseManager;
import org.tinygroup.sdpm.quality.dao.QualityTestCaseDao;
import org.tinygroup.sdpm.quality.dao.QualityTestRunDao;
import org.tinygroup.sdpm.quality.dao.pojo.QualityTestCase;
import org.tinygroup.tinysqldsl.Pager;
import org.tinygroup.tinysqldsl.base.Condition;
import org.tinygroup.tinysqldsl.base.FragmentSql;

import java.util.Date;
import java.util.List;

/**
 * Created by chenpeng15668 on 2015-9-24
 */
@Service
@Transactional
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

    public Pager<QualityTestCase> findPagerRel(Integer start, Integer limit, QualityTestCase testcase, String statusCondition, SearchInfos conditions,
                                               String groupOperate, String columnName, boolean asc) {

        String condition = conditions != null ? SqlUtil.toSql(conditions.getInfos(), groupOperate) : "";
        condition = condition != null && !"".equals(condition) ? (statusCondition != null && !"".equals(statusCondition) ? condition + " and "
                + statusCondition : condition)
                : statusCondition;
        Condition condition1 = null;
        OrderBy orderBy = null;
        if (columnName != null && !"".equals(columnName)) {
            orderBy = new OrderBy("quality_test_case." + NameUtil.resolveNameDesc(columnName), asc);
        }
        if (condition != null && !"".equals(condition)) {
            return testcasedao.queryPagerRel(start, limit, testcase, condition, orderBy);
        }
        return testcasedao.queryPager(start, limit, testcase, orderBy);
    }

    public Pager<QualityTestCase> findStoryChangedCase(Integer start, Integer limit, QualityTestCase testcase, String condition, String columnName, boolean asc) {
        Condition condition1 = null;
        if (!StringUtil.isBlank(condition)) {
            condition1 = FragmentSql.fragmentCondition(condition);
        }
        if (StringUtil.isBlank(columnName)) {
            testcasedao.queryStoryChangedCase(start, limit, testcase, condition1);
        }
        return testcasedao.queryStoryChangedCase(start, limit, testcase, condition1, new OrderBy(columnName, asc));
    }

    public List<Integer> getStoryIds(QualityTestCase t) {

        return testcasedao.getStoryIds(t);
    }
}
