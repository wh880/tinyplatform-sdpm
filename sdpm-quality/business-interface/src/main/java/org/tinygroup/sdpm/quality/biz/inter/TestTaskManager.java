package org.tinygroup.sdpm.quality.biz.inter;

import org.tinygroup.sdpm.dao.condition.ConditionCarrier;
import org.tinygroup.sdpm.quality.dao.pojo.QualityTestTask;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

public interface TestTaskManager {

    /**
     * 通过条件查询
     *
     * @param testtask
     * @return
     */
    List<QualityTestTask> findList(QualityTestTask testtask);

    /**
     * 通过Id查询
     *
     * @param id
     * @return
     */
    QualityTestTask find(Integer id);

    /**
     * 提交测试
     *
     * @param testtask
     * @return
     */
    QualityTestTask add(QualityTestTask testtask);

    /**
     * 编辑测试
     *
     * @param testtask
     * @return
     */
    int update(QualityTestTask testtask);

    /**
     * 删除测试
     *
     * @param id
     * @return
     */
    int delete(int id);

    /**
     * 分页查询
     *
     * @param start
     * @param limit
     * @param testtask
     * @param sortName
     * @param asc
     * @return
     */
    Pager<QualityTestTask> findPager(Integer start, Integer limit, QualityTestTask testtask, ConditionCarrier carrier, String sortName, boolean asc);

}
