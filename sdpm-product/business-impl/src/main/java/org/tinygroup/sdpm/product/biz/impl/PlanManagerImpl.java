package org.tinygroup.sdpm.product.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.common.util.common.NameUtil;
import org.tinygroup.sdpm.product.biz.inter.PlanManager;
import org.tinygroup.sdpm.product.dao.ProductPlanDao;
import org.tinygroup.sdpm.product.dao.ProductStoryDao;
import org.tinygroup.sdpm.product.dao.pojo.ProductPlan;
import org.tinygroup.tinysqldsl.Pager;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlanManagerImpl implements PlanManager {

    @Autowired
    private ProductPlanDao productPlanDao;
    @Autowired
    private ProductStoryDao productStoryDao;

    public ProductPlan add(ProductPlan plan) {
        Integer no = productPlanDao.getMaxNo(plan.getProductId());
        plan.setNo(no == null ? 1 : no + 1);
        return productPlanDao.add(plan);
    }

    public int update(ProductPlan plan) {

        return productPlanDao.edit(plan);
    }

    public ProductPlan find(Integer planId) {

        return productPlanDao.getByKey(planId);
    }

    public int[] updateBatch(List<ProductPlan> productplan) {

        return productPlanDao.batchUpdate(productplan);
    }

    public Pager<ProductPlan> findPager(int start, int limit, ProductPlan productPlan, String order, String ordertype) {

        return productPlanDao.queryPager((start - 1) * limit, limit, productPlan, (order == null || "".equals(order)) ? null : new OrderBy(NameUtil.resolveNameDesc(order), !("desc".equals(ordertype)) ? true : false));
    }

    public List<ProductPlan> statisticFind(ProductPlan productPlan, boolean isOverdue) {
        return productPlanDao.statisticQuery(productPlan, isOverdue);
    }

    public List<ProductPlan> findList(ProductPlan productplan, String order, String ordertype) {

        return productPlanDao.query(productplan, (order == null || "".equals(order)) ? null : new OrderBy(NameUtil.resolveNameDesc(order), !("desc".equals(ordertype)) ? true : false));
    }

    public Integer delete(Integer planId) {
        //删需求
        productStoryDao.deleteStoryByPlan(planId);

        return productPlanDao.softDelete(planId);
    }

    public List<ProductPlan> findList(ProductPlan plan) {

        return productPlanDao.query(plan, null);
    }

    public List<ProductPlan> findList(Integer... planId) {
        if (planId == null || planId.length == 0) return new ArrayList<ProductPlan>();
        return productPlanDao.getByKeys(planId);
    }

    public int[] deleteBatch(List<ProductPlan> ids) {

        return productPlanDao.batchUpdateDel(ids);
    }


}
