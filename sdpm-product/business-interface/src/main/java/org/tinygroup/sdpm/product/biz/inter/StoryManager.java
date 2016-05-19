package org.tinygroup.sdpm.product.biz.inter;

import org.tinygroup.sdpm.dao.condition.ConditionCarrier;
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.product.dao.pojo.ProductStorySpec;
import org.tinygroup.sdpm.product.dao.pojo.StoryCount;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;
import java.util.Map;

public interface StoryManager {

    /**
     * 添加需求
     *
     * @param story
     * @return
     */
    ProductStory add(ProductStory story, ProductStorySpec storySpec);

    /**
     * 根据需求Id删除
     *
     * @param story
     * @return
     */
    Integer delete(ProductStory story);

    /**
     * 编辑
     *
     * @param story
     * @return
     */
    int update(ProductStory story);

    /**
     * 批量编辑
     *
     * @param stories
     * @return
     */
    int[] updateBatch(List<ProductStory> stories);

    /**
     * 根据id批量删除
     *
     * @param ids
     * @return
     */
    int[] deleteBatch(List<ProductStory> ids);

    /**
     * 根据需求ID查找
     *
     * @param storyId
     * @return
     */
    ProductStory find(Integer storyId);


    /**
     * 根据多个id查找
     *
     * @param storyId
     * @return
     */
    List<ProductStory> findList(boolean withSpec, Integer... storyId);

    /**
     * 根据多个id条件查找
     *
     * @param storyId
     * @return
     */
    List<ProductStory> findList(boolean withSpec, ProductStory story, Integer... storyId);

    /**
     * 根据对象查询
     *
     * @param story
     * @return
     */
    List<ProductStory> findList(ProductStory story);

    /**
     * 根据对象查找（排序）
     *
     * @param story
     * @return
     */
    List<ProductStory> findList(ProductStory story, String order, String ordertype);

    /**
     * 分页查询（排序）
     *
     * @param start
     * @param limit
     * @param story
     * @return
     */
    Pager<ProductStory> findPagerRel(int start, int limit, ProductStory story, ConditionCarrier carrier, String columnName, boolean asc);

    /**
     * 分页查询（排序）
     *
     * @param start
     * @param limit
     * @param story
     * @param carrier
     * @param ignoreDelete 忽略判断是否删除与否
     * @param columnName
     * @param asc
     * @return
     */
    Pager<ProductStory> findPager(int start, int limit, ProductStory story, ConditionCarrier carrier, Boolean ignoreDelete, String columnName, boolean asc);

    /**
     * 产品需求数量分类
     *
     * @param story
     * @return
     */
    List<StoryCount> productStoryCount(ProductStory story);

    /**
     * 模块需求数量分类
     *
     * @param story
     * @return
     */
    List<StoryCount> modelStoryCount(ProductStory story);

    /**
     * 计划需求数量分类
     *
     * @param story
     * @return
     */
    List<StoryCount> planStoryCount(ProductStory story);

    /**
     * 其他状态需求数量分类
     *
     * @param story
     * @return
     */
    Map<String, List<StoryCount>> report(String fields, ProductStory story);

    /**
     * 计算状态
     *
     * @param productId
     * @param status
     * @return
     */
    int countStatus(int productId, int status);

    List<ProductStory> findProductNameByStoryId(Integer storyId);

    Pager<ProductStory> findProjectLinkedStory(int start, int limit, ProductStory story, ConditionCarrier carrier, String columnName, boolean asc);

    Pager<ProductStory> findStoryByCondition(int start, int limit, ProductStory story, ConditionCarrier carrier, final String columnName, boolean asc);

    /**
     * 根据输入名称查询
     *
     * @param condition
     * @param productId
     * @return
     */
    List<ProductStory> storyInCondition(String condition, Integer limit, Integer productId, Integer... ids);

    ProductStory findStoryByStoryId(Integer storyId);
}
