package org.tinygroup.sdpm.product.biz.inter;

import java.util.List;

import org.tinygroup.sdpm.product.dao.pojo.ProductPlan;

public interface PlanManager {
	/**
	 * 添加计划
	 * @param plan
	 * @return
	 */
	ProductPlan add(ProductPlan plan);
	/**
	 * 编辑计划
	 * @parm plan
	 * @ruturn
	 */
	int update(ProductPlan plan);
	
	/**
	 * 根据计划ID删除计划
	 * @parm plan
	 * @ruturn
	 */
	int delete(Integer planId);
	
	/**
	 * 根据Id查找
	 * @param planId
	 * @return
	 */
	ProductPlan find(Integer planId);
	
	/**
	 * 根据对象查找
	 * @param plan
	 * @return
	 */
	List<ProductPlan> findList(ProductPlan plan);
	
	

}
