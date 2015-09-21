package org.tinygroup.sdpm.product.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.product.biz.inter.PlanManager;
import org.tinygroup.sdpm.product.dao.ProductPlanDao;
import org.tinygroup.sdpm.product.dao.pojo.ProductPlan;

@Service
@Transactional
public class PlanManagerImpl implements PlanManager{
	
	@Autowired
	private ProductPlanDao productPlanDao;
	
	public ProductPlan add(ProductPlan plan) {

		return productPlanDao.add(plan);
	}

	public int update(ProductPlan plan) {

		return productPlanDao.edit(plan);
	}

	public int delete(Integer planId) {
		
		ProductPlan plan = new ProductPlan();
		plan.setPlanId(planId);
		plan.setDeleted(ProductPlan.DELETE_YES);
		return productPlanDao.edit(plan);
	}

	public ProductPlan find(Integer planId) {

		return productPlanDao.getByKey(planId);
	}

	public List<ProductPlan> findList(ProductPlan plan) {

		return productPlanDao.query(plan);
	}

}
