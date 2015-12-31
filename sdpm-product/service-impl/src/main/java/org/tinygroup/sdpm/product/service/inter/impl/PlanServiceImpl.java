package org.tinygroup.sdpm.product.service.inter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.product.biz.inter.PlanManager;
import org.tinygroup.sdpm.product.dao.pojo.ProductPlan;
import org.tinygroup.sdpm.product.service.inter.PlanService;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

@Component
public class PlanServiceImpl implements PlanService {
    @Autowired
    private PlanManager planManager;

    public ProductPlan addPlan(ProductPlan plan) {
        return planManager.add(plan);
    }

    public int updatePlan(ProductPlan plan) {
        return planManager.update(plan);
    }

    public ProductPlan findPlan(Integer planId) {
        return planManager.find(planId);
    }

    public List<ProductPlan> findPlanList(ProductPlan productPlan) {
        return planManager.findList(productPlan);
    }

    public List<ProductPlan> findPlanListByOrder(ProductPlan productPlan, String order, String orderType) {

        return planManager.findList(productPlan, order, orderType);
    }

    public Pager<ProductPlan> findProductPlanPager(int page, int limit, ProductPlan productPlan, String order,
                                                   String orderType) {
        return planManager.findPager(page, limit, productPlan, order, orderType);
    }

    public List<ProductPlan> statisticProductPlan(ProductPlan productPlan, boolean isOverdue) {
        return planManager.statisticFind(productPlan, isOverdue);
    }

    public Integer deletePlan(Integer planId) {
        return planManager.delete(planId);
    }

    public int[] deleteBatchPlan(List<ProductPlan> ids) {
        return planManager.deleteBatch(ids);
    }

}
