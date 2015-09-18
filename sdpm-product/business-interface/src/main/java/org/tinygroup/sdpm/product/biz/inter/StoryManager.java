package org.tinygroup.sdpm.product.biz.inter;

import java.util.List;

import org.tinygroup.sdpm.product.dao.pojo.Story;

public interface StoryManager {
    /**
     * 添加需求
     * @param story
     * @return
     */
    Story add(Story story);

    /**
     * 编辑需求
     * @param story
     * @return
     */
    int update(Story story);

    /**
     * 批量操作
     * @param stories
     * @return
     */
    int[] updateBatch(List<Story> stories);

    /**
     * 报表生成
     * @param stories
     * @return
     */
    List<Story> GenerateRreports(List<Story> stories);

    /**
     * 根据需求ID批量关闭
     * @param storyIds
     * @return
     */
    int updateBatchClose(Integer...storyIds);

    /**
     * 根据需求Id删除
     * @param storyId
     * @return
     */
    int deleteById(Integer storyId);

    /**
     * 根据需求对象删除
     * @param story
     * @return
     */
    int deleteByStory(Story story);

    /**
     * 根据需求Id批量删除
     * @param storyIds
     * @return
     */
    int deleteByStoryIds(Integer... storyIds);

    /**
     * 根据需求批量删除
     * @param stories
     * @return
     */
    int[] deleteBatch(List<Story> stories);

    /**
     * 根据需求ID查找
     * @param storyId
     * @return
     */
    Story findById(Integer storyId);

    /**
     * 根据产品ID查找
     * @param productId
     * @return
     */
    List<Story> findByProductId(Integer productId);

    /**
     * 根据模块ID查找
     * @param moduleId
     * @return
     */
    List<Story> findByModuleId(Integer moduleId);

    /**
     * 根据计划ID查找
     * @param planId
     * @return
     */
    List<Story> findByPlanId(Integer planId);
    /**
     * 根据对象查找
     * @param story
     * @return
     */
    List<Story> findStory(Story story);


}
