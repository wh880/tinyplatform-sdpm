package org.tinygroup.sdpm.product.service;

import java.util.List;

import org.tinygroup.sdpm.product.dao.pojo.ProductPlan;


public interface ProductPlanService{
	/**
	 * 添加计划
	 * @param plan
	 * @return
	 */
	ProductPlan addPlan(ProductPlan plan);
	/**
	 * 编辑计划
	 * @parm plan
	 * @ruturn
	 */
	int updatePlan(ProductPlan plan);
	
	/**
	 * 根据计划ID删除计划
	 * @parm plan
	 * @ruturn
	 */
	int deletePlan(Integer planId);
	
	/**
	 * 根据Id查找
	 * @param planId
	 * @return
	 */
	ProductPlan findPlan(Integer planId);
	
	/**
	 * 根据对象查找
	 * @param plan
	 * @return
	 */
	List<ProductPlan> findPlanList(ProductPlan plan);
	
	
	/*List<ProductPlan> findPlan(PagerPojo pagerPojo, ProductPlan plan);

	*//**
	 * 排序查询
	 * @param field
	 * @param sorting
	 * @param plan
	 * @return
	 *//*
	List<ProductPlan> findPlansort(String field,String sorting,ProductPlan plan);
	*//**
	 * 分页查询
	 * @param currentPage
	 * @param limit
	 * @param plan
	 * @return
	 *//*
	List<Plan> findStoryPager(int currentPage,int limit,Plan plan);

	*//**
	 * 分页查找
	 * @param currentPage
	 * @param limit
	 * @param plan
	 * @return
	 *//*
	Pager<Plan> findPlan(int currentPage,int limit ,Plan plan);*/
}