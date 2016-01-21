package org.tinygroup.sdpm.product.service.inter;

import org.tinygroup.sdpm.dao.condition.ConditionCarrier;
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.product.dao.pojo.ProductStorySpec;
import org.tinygroup.sdpm.product.dao.pojo.StoryCount;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;
import java.util.Map;

public interface StoryService {

    /**
     * 添加需求
     *
     * @param story
     * @return
     */
    ProductStory addStory(ProductStory story, ProductStorySpec storySpec, String userId);

    /**
     * 删除需求及相关
     * @param story
     * @return
     */
    Integer deleteStory(ProductStory story);

    /**
     * 编辑
     *
     * @param story
     * @return
     */
    int updateStory(ProductStory story);

    /**
     * 批量编辑
     *
     * @param stories
     * @return
     */
    int[] updateBatchStory(List<ProductStory> stories);

    /**
     * 根据id批量删除
     *
     * @param ids
     * @return
     */
    int[] deleteBatchStory(List<ProductStory> ids);

    /**
     * 根据需求ID查找
     *
     * @param storyId
     * @return
     */
    ProductStory findStory(Integer storyId);

    /**
     * 根据多个ID查找
     *
     * @param storyId
     * @return
     */
    List<ProductStory> findStoryListByIds(Integer... storyId);

    /**
     * 根据对象查找
     *
     * @param story
     * @return
     */
    List<ProductStory> findStoryList(ProductStory story);

    /**
     * 根据产品对象查找
     *
     * @return
     */
    List<ProductStory> findStoryListByOrder(ProductStory story, String order, String orderType);

    /**
     * 分页查询
     *
     * @param start
     * @param limit
     * @param story
     * @return
     */
    Pager<ProductStory> findStoryPagerRel(int start, int limit, ProductStory story, ConditionCarrier carrier, String columnName, boolean asc);

    /**
     * 其他状态需求数量分类
     *
     * @param story
     * @return
     */
    Map<String, List<StoryCount>> StoryCountReport(String fields, ProductStory story);

    /**
     * 获取需求所在product名称
     *
     * @param storyId
     * @return
     */
    List<ProductStory> findProductName(Integer storyId);

    /**
     * 复合条件-排序-分页查询项目关联需求
     * @param start
     * @param limit
     * @param story
     * @param carrier
     * @param columnName
     * @param asc
     * @return
     */
    Pager<ProductStory> findProjectLinkedStory(int start, int limit, ProductStory story, ConditionCarrier carrier, String columnName, boolean asc);

    /**
     * 复合条件-排序-分页查询需求
     * @param start
     * @param limit
     * @param story
     * @param carrier
     * @param columnName
     * @param asc
     * @return
     */
    Pager<ProductStory> findStoryByCondition(int start, int limit, ProductStory story, ConditionCarrier carrier, String columnName, boolean asc);

    /**
     * 根据输入名称查询
     * @param condition
     * @param productId
     * @return
     */
    List<ProductStory> storyInCondition(String condition, Integer limit, Integer productId);

    /**
     * 根据ids查询需求-可选是否携带描述
     * @param isWithSpec
     * @param ids
     * @return
     */
    List<ProductStory> getStoryWithSpecInIds(boolean isWithSpec, Integer... ids);

    /**
     * 根据Story Id查找相应的Story
     * @param storyId
     * @return
     */
    ProductStory findStoryByStoryId(Integer storyId);
}
