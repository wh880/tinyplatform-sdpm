package org.tinygroup.sdpm.quality.service.inter;

import org.tinygroup.sdpm.dao.condition.ConditionCarrier;
import org.tinygroup.sdpm.quality.dao.pojo.BugCount;
import org.tinygroup.sdpm.quality.dao.pojo.QualityBug;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;
import java.util.Map;

public interface BugService {

    /**
     * 根据主键ID查询
     *
     * @param id
     * @return
     */
    QualityBug findQualityBugById(Integer id);

    /**
     * 根据条件查询d
     *
     * @param qualityBug
     * @return
     */
    List<QualityBug> findBugList(QualityBug qualityBug);

    /**
     * 提Bug
     *
     * @param bug
     * @return
     */
    QualityBug addBug(QualityBug bug);

    /**
     * 修改
     *
     * @param bug
     * @return
     */
    int updateBug(QualityBug bug);

    /**
     * 批量删除
     *
     * @param bugIds
     * @return
     */
    int[] batchDeleteBug(List<QualityBug> bugIds);

    /**
     * 删除
     *
     * @param bugId
     * @return
     */
    Integer deleteBug(Integer bugId);

    /**
     * bug报表
     *
     * @param code
     * @param productId
     * @return
     */
    Map<String, List<BugCount>> bugReport(String code, Integer productId);

    /**
     * 获取需求变更的bug
     *
     * @param start
     * @param limit
     * @param carrier
     * @param bug
     * @param sortName
     * @param asc
     * @return
     */
    Pager<QualityBug> findStoryChangedBugs(Integer start, Integer limit, ConditionCarrier carrier, QualityBug bug, String sortName, boolean asc);

    /**
     * 复合条件-排序-分页查询bug
     *
     * @param start
     * @param limit
     * @param carrier
     * @param bug
     * @param sortName
     * @param asc
     * @return
     */
    Pager<QualityBug> findBugListPager(Integer start, Integer limit, ConditionCarrier carrier, QualityBug bug, String sortName, boolean asc);

    /**
     * 发布文档中，获取已解决且关联的bug
     *
     * @param bug
     * @return
     */
    List<QualityBug> getBugsInReleaseDoc(QualityBug bug);

    /**
     * 根据输入名称获取bug
     *
     * @param condition
     * @param productId
     * @return
     */
    List<QualityBug> bugInCondition(String condition, Integer limit, Integer productId);

    /**
     * 根据bug Id查找对应的bug
     *
     * @param bugId
     * @return
     */
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