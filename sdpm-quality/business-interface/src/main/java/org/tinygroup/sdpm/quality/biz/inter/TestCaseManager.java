package org.tinygroup.sdpm.quality.biz.inter;

import org.tinygroup.sdpm.dao.condition.ConditionCarrier;
import org.tinygroup.sdpm.quality.dao.pojo.QualityTestCase;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

/**
 * Created by chenpeng15668 on 2015-9-24
 */
public interface TestCaseManager {

    /**
     * 通过对象查询
     *
     * @param festcase
     * @return
     */
    List<QualityTestCase> findList(QualityTestCase festcase);

    /**
     * 通过主键id查询
     *
     * @param story
     * @return
     */
    QualityTestCase find(Integer id);

    /**
     * 建用例
     *
     * @param testcase
     * @return
     */
    QualityTestCase add(QualityTestCase testcase);

    /**
     * 用例修改
     *
     * @param testcase
     * @return
     */
    Integer update(QualityTestCase testcase);

    /**
     * 批量修改
     *
     * @param testcases
     * @return
     */
    int[] batchUpdate(List<QualityTestCase> testcases);

    /**
     * 删除用例
     *
     * @param id
     * @return
     */
    Integer delete(Integer id);

    /**
     * 批量删除
     *
     * @param id
     * @return
     */
    int[] batchDelete(List<QualityTestCase> testcases);

    /**
     * 分页查询
     *
     * @param start
     * @param limit
     * @param testsase
     * @param sortName
     * @param asc
     * @return
     */
    Pager<QualityTestCase> findPager(Integer start, Integer limit, QualityTestCase testcase, String sortName, boolean asc);

    Pager<QualityTestCase> findPagerRel(Integer start, Integer limit, QualityTestCase testcase, ConditionCarrier carrier, String columnName, boolean asc);

    Pager<QualityTestCase> findStoryChangedCase(Integer start, Integer limit, QualityTestCase testcase, ConditionCarrier carrier, String columnName, boolean asc);


    List<Integer> getStoryIds(QualityTestCase t);
}
