package org.tinygroup.sdpm.product.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.product.biz.inter.PlanManager;
import org.tinygroup.sdpm.product.dao.ProductPlanDao;
import org.tinygroup.sdpm.product.dao.pojo.ProductPlan;
import org.tinygroup.tinysqldsl.Pager;

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
		plan.setDeleted(FieldUtil.DELETE_YES);
		return productPlanDao.edit(plan);
	}

	public ProductPlan find(Integer planId) {

		return productPlanDao.getByKey(planId);
	}

	public int[] updateBatch(List<ProductPlan> productplan) {
		
		return productPlanDao.batchUpdate(productplan);
	}

	public List<ProductPlan> findList(ProductPlan productplan,String columnName,boolean asc) {
		
		return productPlanDao.query(productplan,  new OrderBy(columnName, asc));
	}

	public Pager<ProductPlan> findPager(int start, int limit, ProductPlan productplan,String columnName,boolean asc) {
		
		return productPlanDao.queryPager(start, limit, productplan,  new OrderBy(columnName, asc));
	}



}
