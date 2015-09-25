package org.tinygroup.sdpm.product.biz.impl;

import java.util.Date;
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
		
		plan.setPlanBeginDate(new Date());
		plan.setPlanEndDate(new Date());
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

	public List<ProductPlan> findList(ProductPlan productplan,String columnName,boolean asc) {
		
		return productPlanDao.query(productplan,  new OrderBy(columnName, asc));
	}

	public Pager<ProductPlan> findPager(int start, int limit, ProductPlan productplan,String columnName,boolean asc) {
		
		return productPlanDao.queryPager(start, limit, productplan,  new OrderBy(columnName, asc));
	}

	public Pager<ProductPlan> findPager(int start, int limit, ProductPlan productplan, String order, String ordertype) {
		
		return productPlanDao.queryPager((start-1)*limit, limit, productplan, new OrderBy(FieldUtil.stringFormat(order), !("desc".equals(ordertype))?true:false));
	}

	public Integer delete(Integer planId) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
