package org.tinygroup.sdpm.product.service;

import java.util.List;

import org.tinygroup.sdpm.product.dao.pojo.Plan;
import org.tinygroup.sdpm.product.dao.pojo.Product;


public interface PlanService {
	/**
	 * 创建计划
	 * @param plan
	 * @return
	 */
	Plan add(Plan plan);
	
	/**
	 * 编辑计划
	 * @param plan
	 * @return
	 */
	int eidt(Plan plan);
	
	/**
	 * 根据计划对象删除
	 * @param plan
	 * @return
	 */
	int deleteByPlan(Plan plan);

	/**
	 * 根据计划批量删除
	 * @param plan
	 * @return
	 */
	int batchDelete(List<Plan> plans);
	
	/**
	 * 根据计划对象删除
	 * @param plan
	 * @return
	 */
	int deleteById(Integer planId);
	
	/**
	 * 根据计划Id查找日志
	 * @param id
	 * @return
	 */
	List<Plan> findByPlanId(Integer planId);
	
	/**
	 * 根据计划ID查找
	 * @param id
	 * @return
	 */
	Plan findById(Integer planId);
	
	
	/**
	 * 根据计划对象查找需求
	 * @param plan
	 * @return
	 */
	List<Story> findByPlan(Plan plan);	
	/**
	 * 根据计划对象查找BUG
	 * @param plan
	 * @return
	 */
	List<Plan> findByPlan(Plan plan);
	
	
	
	
	
	
	
	
	
	
	
}
