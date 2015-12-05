package org.tinygroup.sdpm.quality.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.dao.condition.ConditionCarrier;
import org.tinygroup.sdpm.quality.biz.inter.TestCaseManager;
import org.tinygroup.sdpm.quality.dao.pojo.QualityTestCase;
import org.tinygroup.sdpm.quality.service.inter.TestCaseService;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

@Component
public class TestCaseServiceImpl implements TestCaseService {
    @Autowired
    private TestCaseManager testCaseManager;

    public List<QualityTestCase> findTestCaseList(QualityTestCase testCase) {
        return testCaseManager.findList(testCase);
    }

    public QualityTestCase testCase(int id) {
        return testCaseManager.find(id);
    }

    public QualityTestCase addTestCase(QualityTestCase testCase) {
        return testCaseManager.add(testCase);
    }

    public int updateTestCase(QualityTestCase testCase) {
        return testCaseManager.update(testCase);
    }

    public int[] batchUpdateTestCase(List<QualityTestCase> testCases) {
        return testCaseManager.batchUpdate(testCases);
    }

    public int deleteById(int id) {
        return testCaseManager.delete(id);
    }

    public int[] batchDeleteTestCase(List<QualityTestCase> testCases) {
        return testCaseManager.batchDelete(testCases);
    }

    public Pager<QualityTestCase> findTestCasePager(Integer start,
                                                    Integer limit, QualityTestCase testCase, String sortName,
                                                    boolean asc) {
        return testCaseManager.findPager(start, limit, testCase, sortName, asc);
    }

    public Pager<QualityTestCase> findTestCasePagerByConditionCarrier(Integer start, Integer limit, QualityTestCase testCase, ConditionCarrier carrier, String columnName, boolean asc) {
        return testCaseManager.findPagerRel(start, limit, testCase, carrier,columnName, asc);
    }

    public Pager<QualityTestCase> findStoryChangedCase(Integer start, Integer limit, QualityTestCase testCase, ConditionCarrier carrier, String columnName, boolean asc) {
        return testCaseManager.findStoryChangedCase(start, limit, testCase, carrier, columnName, asc);
    }

    public List<Integer> getStoryIds(QualityTestCase qualityTestCase) {
        return testCaseManager.getStoryIds(qualityTestCase);
    }


}
