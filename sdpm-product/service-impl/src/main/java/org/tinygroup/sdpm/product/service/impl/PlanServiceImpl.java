package org.tinygroup.sdpm.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.product.biz.inter.PlanManager;
import org.tinygroup.sdpm.product.dao.pojo.ProductPlan;
import org.tinygroup.sdpm.product.service.PlanService;

@Component("planService")
public class PlanServiceImpl implements PlanService {
	
	@Autowired
	private PlanManager planManager;
	
	public ProductPlan addPlan(ProductPlan plan) {

		return planManager.add(plan);
	}

	public int updatePlan(ProductPlan plan) {

		return planManager.update(plan);
	}

	public int deletePlan(Integer planId) {

		return planManager.delete(planId);
	}

	public ProductPlan findPlan(Integer planId) {

		return planManager.find(planId);
	}

	public List<ProductPlan> findPlanList(ProductPlan plan) {

		return planManager.findList(plan);
	}

}