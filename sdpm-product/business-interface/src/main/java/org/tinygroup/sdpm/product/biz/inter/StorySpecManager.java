package org.tinygroup.sdpm.product.biz.inter;

import org.tinygroup.sdpm.product.dao.pojo.ProductStorySpec;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

public interface StorySpecManager {

    ProductStorySpec add(ProductStorySpec storySpec);

    /**
     * 根据需求Id查找
     *
     * @param storyId
     * @return
     */
    ProductStorySpec find(Integer storyId, Integer version);

    /**
     * 根据多个id查找
     *
     * @param storyspecId
     * @return
     */
    List<ProductStorySpec> findList(Integer... storyspecId);

    /**
     * 根据对象查询
     *
     * @param storySpec
     * @return
     */
    List<ProductStorySpec> findList(ProductStorySpec storySpec);

    /**
     * 根据需求对象查找
     *
     * @param storySpec
     * @param orderBies
     * @return
     */
    List<ProductStorySpec> findList(ProductStorySpec storySpec, String order, String ordertype);

    /**
     * 分页查询
     *
     * @param start
     * @param limit
     * @param storySpec
     * @param orderBies
     * @return
     */
    Pager<ProductStorySpec> findPager(int start, int limit, ProductStorySpec storySpec, String order, String ordertype);

    /**
     * 查找最新版本
     *
     * @param storyId
     * @return
     */
    int getNewStoryVersion(Integer storyId);

    Integer getMaxVersion(Integer storyId);
}
