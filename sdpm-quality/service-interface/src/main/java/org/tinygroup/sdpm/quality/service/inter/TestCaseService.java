package org.tinygroup.sdpm.quality.service.inter;

import org.tinygroup.sdpm.dao.condition.ConditionCarrier;
import org.tinygroup.sdpm.quality.dao.pojo.QualityTestCase;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

public interface TestCaseService {
    /**
     * 通过对象查询
     * @param festcase
     * @return
     */
    List<QualityTestCase> findTestCaseList(QualityTestCase festcase);

    /**
     * 通过主键id查询
     * @param id
     * @return
     */
    QualityTestCase testCase(int id);

    /**
     * 建用例
     * @param testcase
     * @return
     */
    QualityTestCase addTestCase(QualityTestCase testcase);

    /**
     * 用例修改
     * @param testcase
     * @return
     */
    int updateTestCase(QualityTestCase testcase);

    /**
     * 批量修改
     * @param testcases
     * @return
     */
    int[] batchUpdateTestCase(List<QualityTestCase> testcases);

    /**
     * 删除用例
     * @param id
     * @return
     */
    int deleteById(int id);

    /**
     * 批量删除
     * @param testcases
     * @return
     */
    int[] batchDeleteTestCase(List<QualityTestCase> testcases);

    /**
     * 简单-排序-分页查询
     * @param start
     * @param limit
     * @param testcase
     * @param sortName
     * @param asc
     * @return
     */
    Pager<QualityTestCase> findTestCasePager(Integer start, Integer limit, QualityTestCase testcase, String sortName, boolean asc);

    /**
     * 复合条件-排序-分页查询
     * @param start
     * @param limit
     * @param testcase
     * @param carrier
     * @param columnName
     * @param asc
     * @return
     */
    Pager<QualityTestCase> findTestCasePager(Integer start, Integer limit, QualityTestCase testcase, ConditionCarrier carrier, String columnName, boolean asc);

    /**
     * 复合条件-排序-分页查询需求变更用例
     * @param start
     * @param limit
     * @param testcase
     * @param carrier
     * @param columnName
     * @param asc
     * @return
     */
    Pager<QualityTestCase> findStoryChangedCase(Integer start, Integer limit, QualityTestCase testcase, ConditionCarrier carrier, String columnName, boolean asc);

    /**
     * 获取存在用例的需求ids
     * @param t
     * @return
     */
    List<Integer> getStoryIds(QualityTestCase t);
}