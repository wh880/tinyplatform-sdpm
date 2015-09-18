package org.tinygroup.sdpm.product.biz.inter;

import java.util.List;

import org.tinygroup.sdpm.product.dao.pojo.Plan;
import org.tinygroup.sdpm.product.service.pojo.PagerPojo;
import org.tinygroup.tinysqldsl.Pager;

public interface PlanManager {
	/**
	 * 添加计划
	 * @param plan
	 * @return
	 */
	Plan add(Plan plan);
	/**
	 * 编辑计划
	 * @parm plan
	 * @ruturn
	 */
	int update(Plan plan);
	/**
	 * 删除计划
	 * @parm plan
	 * @ruturn
	 */
	int deleteByPlan(Plan plan);
	/**
	 * 根据计划ID删除计划
	 * @parm plan
	 * @ruturn
	 */
	int deleteByPlanId(Plan plan);

	/**
	 * 根据Id查找
	 * @param planId
	 * @return
	 */
	Plan findById(Integer planId);

	/**
	 * 根据对象查找
	 * @param plan
	 * @return
	 */
	List<Plan> findPlan( Plan plan);

}
