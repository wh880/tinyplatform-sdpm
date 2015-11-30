package org.tinygroup.sdpm.quality.service.inter;

import org.tinygroup.sdpm.common.util.ComplexSearch.SearchInfos;
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
    QualityTestCase findById(int id);

    /**
     * 建用例
     *
     * @param testcase
     * @return
     */
    QualityTestCase addTestCase(QualityTestCase testcase);

    /**
     * 用例修改
     *
     * @param testcase
     * @return
     */
    int updateTestCase(QualityTestCase testcase);

    /**
     * 批量修改
     *
     * @param testcases
     * @return
     */
    int[] batchUpdateTestCase(List<QualityTestCase> testcases);

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
     * @param id
     * @return
     */
    int[] batchDeleteTestCase(List<QualityTestCase> testcases);

    /**
     * 分页查询
     *
     * @param start
     * @param limit
     * @param testcase
     * @param sortName
     * @param asc
     * @return
     */
    Pager<QualityTestCase> findTestCasePager(Integer start, Integer limit, QualityTestCase testcase, String sortName, boolean asc);

    Pager<QualityTestCase> findTestCasePager(Integer start, Integer limit, QualityTestCase testcase, String statusCondition, SearchInfos conditions,
                                             String groupOperate, String columnName, boolean asc);


    Pager<QualityTestCase> findStoryChangedCase(Integer start, Integer limit, QualityTestCase testcase, String condition, String columnName, boolean asc);

    List<Integer> getStoryIds(QualityTestCase t);
}