package org.tinygroup.sdpm.product.biz.inter;

import java.util.List;

import org.tinygroup.sdpm.product.dao.pojo.StorySpec;

public interface StorySpecManager {
    /**
     * 添加描述
     * @param storySpec
     * @return
     */
    StorySpec add(StorySpec storySpec);

    /**
     * 编辑需求
     * @param storySpec
     * @return
     */
    int edit(StorySpec storySpec);

    /**
     * 批量操作
     * @param storySpecs
     * @return
     */
    int[] batchUpdate(List<StorySpec> storySpecs);

    /**
     * 根据需求Id删除
     * @param storyId
     * @return
     */
    int deleteById(Integer storyId);

    /**
     * 根据对象删除
     * @param storySpec
     * @return
     */
    int deleteByStorySpec(StorySpec storySpec);

}
