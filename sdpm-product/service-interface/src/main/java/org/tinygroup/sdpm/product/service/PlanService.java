package org.tinygroup.sdpm.product.service;

import java.util.List;

import org.tinygroup.sdpm.product.dao.pojo.ProductPlan;
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.tinysqldsl.Pager;

public interface PlanService{
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
	 * 批量编辑
	 * @param plan
	 * @return
	 */
	int[] updateBatchPlan(List<ProductPlan> plan);
	

    /**
     * 根据id进行软删除用户
     *
     * @param id 主键
     * @return
     */
    Integer deletePlan(Integer planId);
	
	/**
	 * 根据Id查找
	 * @param planId
	 * @return
	 */
	ProductPlan findPlan(Integer planId);
	
	/**
	 * 根据多个ID查找
	 * @param planId
	 * @return
	 */
	List<ProductPlan> findPlanList(Integer... planId);
	
	/**
	 * 根据对象查找
	 * @param plan
	 * @param columnName
	 * @param asc
	 * @return
	 */
	List<ProductPlan> findPlanList(ProductPlan productPlan,String order,String ordertype);
	/**
	 * 分页查询（排序）
	 * @param start
	 * @param limit
	 * @param plan
	 * @param ordertype
	 * @return
	 */
	Pager<ProductPlan> findProductPlanPager(int page,int limit,ProductPlan productPlan,String order,String ordertype);
	
}