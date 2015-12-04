package org.tinygroup.sdpm.product.service;

import org.tinygroup.sdpm.product.dao.pojo.ProductStorySpec;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

public interface StorySpecService {

    ProductStorySpec addProductStorySpec(ProductStorySpec storySpec);

    /**
     * 根据需求Id查找
     *
     * @param storyId
     * @return
     */
    ProductStorySpec findStorySpec(Integer storyId, Integer version);


    /**
     * 根据需求对象查找
     *
     * @param storySpec
     * @param order
     * @param orderType
     * @return
     */
    List<ProductStorySpec> findStorySpecList(ProductStorySpec storySpec, String order, String orderType);

    /**
     * 分页查询（排序）
     *
     * @param page
     * @param limit
     * @param storySpec
     * @param order
     * @param orderType
     * @return
     */
    Pager<ProductStorySpec> findStorySpecPager(int page, int limit, ProductStorySpec storySpec, String order, String orderType);

    /**
     * 获取需求的最大版本号
     * @param storyId
     * @return
     */
    Integer getMaxVersion(Integer storyId);
}
