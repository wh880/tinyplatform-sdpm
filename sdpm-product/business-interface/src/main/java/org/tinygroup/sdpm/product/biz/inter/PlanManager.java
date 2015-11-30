package org.tinygroup.sdpm.product.biz.inter;

import org.tinygroup.sdpm.product.dao.pojo.ProductPlan;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

public interface PlanManager {
    /**
     * 添加计划
     *
     * @param plan
     * @return
     */
    ProductPlan add(ProductPlan plan);

    /**
     * 编辑计划
     *
     * @parm plan
     * @ruturn
     */
    int update(ProductPlan plan);

    /**
     * 批量编辑
     *
     * @param productplan
     * @return
     */
    int[] updateBatch(List<ProductPlan> productplan);


    /**
     * 根据ID批量删除
     *
     * @param ids
     * @return
     */
    int[] deleteBatch(List<ProductPlan> ids);

    /**
     * 根据ID进行软删除
     *
     * @param planId
     * @return
     */
    Integer delete(Integer planId);

    /**
     * 根据Id查找
     *
     * @param planId
     * @param oderby
     * @return
     */
    ProductPlan find(Integer planId);

    /**
     * 根据多个id查找
     *
     * @param planId
     * @return
     */
    List<ProductPlan> findList(Integer... planId);

    /**
     * 根据对象查询
     *
     * @param plan
     * @return
     */
    List<ProductPlan> findList(ProductPlan plan);

    /**
     * 根据对象查找(排序)
     *
     * @param productplan
     * @param order
     * @param ordertype
     * @return
     */
    List<ProductPlan> findList(ProductPlan productPlan, String order, String ordertype);

    /**
     * 根据对象查找(分页、排序)
     *
     * @param start
     * @param limit
     * @param productplan
     * @param orderBies
     * @return
     */
    Pager<ProductPlan> findPager(int start, int limit, ProductPlan productPlan, String order, String ordertype);

    /**
     * 统计查询
     *
     * @param productPlan
     * @return
     */
    List<ProductPlan> statisticFind(ProductPlan productPlan, boolean isOverdue);


}
