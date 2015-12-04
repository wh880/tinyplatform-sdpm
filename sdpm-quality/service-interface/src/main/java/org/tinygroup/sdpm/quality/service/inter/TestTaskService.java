package org.tinygroup.sdpm.quality.service.inter;

import org.tinygroup.sdpm.dao.condition.ConditionCarrier;
import org.tinygroup.sdpm.quality.dao.pojo.QualityTestTask;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

public interface TestTaskService {
    /**
     * 通过条件查询
     *
     * @param testtask
     * @return
     */
    List<QualityTestTask> findTestTaskList(QualityTestTask testtask);

    /**
     * 通过Id查询
     *
     * @param id
     * @return
     */
    QualityTestTask findTestTaskById(Integer id);

    /**
     * 提交测试
     *
     * @param testtask
     * @return
     */
    QualityTestTask addTestTask(QualityTestTask testtask);

    /**
     * 编辑测试
     *
     * @param testtask
     * @return
     */
    int updateTestTask(QualityTestTask testtask);

    /**
     * 删除测试
     *
     * @param id
     * @return
     */
    int deleteTestTaskById(int id);

    /**
     * 复合条件-排序-分页查询测试版本
     * @param start
     * @param limit
     * @param testtask
     * @param carrier
     * @param sortName
     * @param asc
     * @return
     */
    Pager<QualityTestTask> findTestTaskPagerWithConditionCarrier(Integer start, Integer limit, QualityTestTask testtask, ConditionCarrier carrier, String sortName, boolean asc);

    /**
     * 简单-排序-分页查询测试版本
     * @param start
     * @param limit
     * @param testtask
     * @param sortName
     * @param asc
     * @return
     */
    Pager<QualityTestTask> findTestTaskPager(Integer start, Integer limit, QualityTestTask testtask, String sortName, boolean asc);
}