package org.tinygroup.sdpm.quality.service.inter;

import org.tinygroup.sdpm.dao.condition.ConditionCarrier;
import org.tinygroup.sdpm.quality.dao.pojo.QualityTestTask;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

public interface TestTaskService {
    /**
     * 通过条件查询
     *
     * @param qualityTestTask
     * @return
     */
    List<QualityTestTask> findTestTaskList(QualityTestTask qualityTestTask);

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
     * @param qualityTestTask
     * @return
     */
    QualityTestTask addTestTask(QualityTestTask qualityTestTask);

    /**
     * 编辑测试
     *
     * @param qualityTestTask
     * @return
     */
    int updateTestTask(QualityTestTask qualityTestTask);


    /**
     * 复合条件-排序-分页查询测试版本
     *
     * @param start
     * @param limit
     * @param qualityTestTask
     * @param carrier
     * @param sortName
     * @param asc
     * @return
     */
    Pager<QualityTestTask> findTestTaskPagerWithConditionCarrier(Integer start, Integer limit, QualityTestTask qualityTestTask, ConditionCarrier carrier, String sortName, boolean asc);

    /**
     * 简单-排序-分页查询测试版本
     *
     * @param start
     * @param limit
     * @param qualityTestTask
     * @param sortName
     * @param asc
     * @return
     */
    Pager<QualityTestTask> findTestTaskPager(Integer start, Integer limit, QualityTestTask qualityTestTask, String sortName, boolean asc);
}