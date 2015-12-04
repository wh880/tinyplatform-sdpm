package org.tinygroup.sdpm.quality.service.inter;

import org.tinygroup.sdpm.dao.condition.ConditionCarrier;
import org.tinygroup.sdpm.quality.dao.pojo.QualityTestCase;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

public interface TestCaseService {
    /**
     * 通过对象查询
     *
     * @param festcase
     * @return
     */
    List<QualityTestCase> findTestCaseList(QualityTestCase festcase);

    /**
     * 通过主键id查询
     *
     * @param story
     * @return
     */
    QualityTestCase testCase(int id);

    /**
     * 建用例
     *
     * @param testCase
     * @return
     */
    QualityTestCase addTestCase(QualityTestCase testCase);

    /**
     * 用例修改
     *
     * @param testCase
     * @return
     */
    int updateTestCase(QualityTestCase testCase);

    /**
     * 批量修改
     *
     * @param testCases
     * @return
     */
    int[] batchUpdateTestCase(List<QualityTestCase> testCases);

    /**
     * 删除用例
     *
     * @param id
     * @return
     */
    int deleteById(int id);

    /**
     * 批量删除
     *
     * @param testCases
     * @return
     */
    int[] batchDeleteTestCase(List<QualityTestCase> testCases);

    /**
     * 分页查询
     *
     * @param start
     * @param limit
     * @param testCase
     * @param sortName
     * @param asc
     * @return
     */
    Pager<QualityTestCase> findTestCasePager(Integer start, Integer limit, QualityTestCase testCase, String sortName, boolean asc);

    Pager<QualityTestCase> findTestCasePager(Integer start, Integer limit, QualityTestCase testCase, ConditionCarrier carrier, String columnName, boolean asc);


    Pager<QualityTestCase> findStoryChangedCase(Integer start, Integer limit, QualityTestCase testCase, ConditionCarrier carrier, String columnName, boolean asc);

    List<Integer> getStoryIds(QualityTestCase t);
}