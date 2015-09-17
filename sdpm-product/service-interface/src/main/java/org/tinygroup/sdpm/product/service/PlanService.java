package org.tinygroup.sdpm.product.service;

import java.util.List;

import org.tinygroup.sdpm.product.dao.pojo.Plan;
import org.tinygroup.sdpm.product.dao.pojo.Release;
import org.tinygroup.sdpm.product.dao.pojo.Story;


public interface PlanService {
	/**
	 * 创建计划
	 * @param plan
	 * @return
	 */
	Plan add(Plan plan);
	
	/**
	 * 保存计划
	 * @param Plan
	 * @return
	 */
	Plan save(Plan plan);
	
	/**
	 * 根据计划ID查找计划
	 * @param Plan
	 * @return
	 */
	Plan findByPlanId(Integer planId);
	/**
	 * 根据计划名称查找需求
	 * @param Stories
	 * @return
	 */
	List<Story> findByplanId(String PlanName);
	
	/**
	 * 根据计划ID编辑计划
	 * @param Plan
	 * @return
	 */
	int edit(Integer planId);
	
	/**
	 * 根据计划ID删除计划
	 * @param Plan
	 * @return
	 */
	int deleteByPlanId(Integer planId);
	
	/**
	 * 根据计划ID查找关联需求
	 * @param Stories
	 * @return
	 */
	List<Story> find(Integer releaseId);
/*	
	*//**
	 * 根据发布ID查找关联BUG
	 * @param Stories
	 * @return
	 *//*
	List<Bug> findByReleaseid(Integer releaseId);*/


}
