package org.tinygroup.sdpm.product.service.inter;

import org.tinygroup.sdpm.product.dao.pojo.ProductPlan;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

public interface PlanService {
    /**
     * 添加计划
     *
     * @param plan
     * @return
     */
    ProductPlan addPlan(ProductPlan plan);

    /**
     * 编辑计划
     *
     * @parm plan
     * @ruturn
     */
    int updatePlan(ProductPlan plan);

    /**
     * 根据id批量删除
     *
     * @param ids
     * @return
     */
    int[] deleteBatchPlan(List<ProductPlan> ids);

    /**
     * 根据id进行软删除用户
     *
     * @param planId 主键
     * @return
     */
    Integer deletePlan(Integer planId);

    /**
     * 根据Id查找
     *
     * @param planId
     * @return
     */
    ProductPlan findPlan(Integer planId);


    /**
     * 根据对象查找
     *
     * @param productPlan
     * @return
     */
    List<ProductPlan> findPlanList(ProductPlan productPlan);

    /**
     * 根据对象查找(排序)
     *
     * @param productPlan
     * @param order
     * @param orderType
     * @return
     */
    List<ProductPlan> findPlanListByOrder(ProductPlan productPlan, String order, String orderType);

    /**
     * 分页查询（排序）
     *
     * @param page
     * @param limit
     * @param productPlan
     * @param order
     * @param orderType
     * @return
     */
    Pager<ProductPlan> findProductPlanPager(int page, int limit, ProductPlan productPlan, String order, String orderType);

    /**
     * 统计plan
     *
     * @param productPlan
     * @return
     */
    List<ProductPlan> statisticProductPlan(ProductPlan productPlan, boolean isOverdue);

}