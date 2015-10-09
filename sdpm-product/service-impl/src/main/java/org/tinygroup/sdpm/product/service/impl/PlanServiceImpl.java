package org.tinygroup.sdpm.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.product.biz.inter.PlanManager;
import org.tinygroup.sdpm.product.dao.pojo.ProductPlan;
import org.tinygroup.sdpm.product.service.PlanService;
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

	public int[] updateBatchPlan(List<ProductPlan> plan) {
		
		return planManager.updateBatch(plan);
	}

	public List<ProductPlan> findPlanList(ProductPlan productPlan,String order,String ordertype) {
	
		return planManager.findList(productPlan, order, ordertype);
	}

	public Pager<ProductPlan> findProductPlanPager(int page, int limit, ProductPlan productPlan, String order,
			String ordertype) {
		
		return planManager.findPager(page, limit, productPlan, order, ordertype);
	}

	public Integer deletePlan(Integer planId) {
		
		return planManager.delete(planId);
	}

	public List<ProductPlan> findPlanList(Integer... planId) {

		return planManager.findList(planId);
	}


}
