package org.tinygroup.sdpm.quality.biz.inter;

import org.tinygroup.sdpm.dao.condition.ConditionCarrier;
import org.tinygroup.sdpm.quality.dao.pojo.BugCount;
import org.tinygroup.sdpm.quality.dao.pojo.QualityBug;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;
import java.util.Map;

public interface BugManager {
    /**
     * 根据主键ID查询
     *
     * @param id
     * @return
     */
    QualityBug find(Integer id);

    /**
     * 根据条件查询d
     *
     * @param bug
     * @return
     */
    List<QualityBug> findList(QualityBug bug);

    /**
     * 分页查询
     *
     * @return
     */
    Pager<QualityBug> findPager(Integer start, Integer limit, String conditions, QualityBug bug, String sortName, boolean asc);

    /**
     * 提Bug
     *
     * @param bug
     * @return
     */
    QualityBug add(QualityBug bug);

    /**
     * 修改
     *
     * @param bug
     * @return
     */
    Integer update(QualityBug bug);

    /**
     * 批量编辑
     *
     * @param bugs
     * @return
     */
    int[] batchUpdate(List<QualityBug> bugs);

    /**
     * 批量删除
     *
     * @param bugIds
     * @return
     */
    int[] batchDelete(List<QualityBug> bugIds);

    /**
     * 删除
     *
     * @return
     */
    Integer delete(Integer id);

    Map<String, List<BugCount>> report(String code, Integer productId);

    Pager<QualityBug> queryStoryChangedBugs(Integer start, Integer limit, ConditionCarrier carrier, QualityBug bug, String sortName, boolean asc);

    Pager<QualityBug> findBugListPager(Integer start, Integer limit, ConditionCarrier carrier, QualityBug bug, String sortName, boolean asc);

    List<QualityBug> getBugsInReleaseDoc(QualityBug bug);

    List<QualityBug> bugInCondition(String condition, Integer limit, Integer productId);

    QualityBug findBugByBugId(Integer bugId);

    /**
     * 校验Bug名称的唯一性
     * @param bugTitle
     * @param productId
     * @param status
     * @return
     */
    List<QualityBug> findBugByProductIdAndBugTitle(String bugTitle, Integer productId,String status);
}
