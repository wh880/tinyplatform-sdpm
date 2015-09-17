package org.tinygroup.sdpm.product.service;

import java.util.List;

import org.tinygroup.sdpm.product.dao.pojo.Plan;
import org.tinygroup.sdpm.product.dao.pojo.Story;
import org.tinygroup.tinysqldsl.Pager;


public interface PlanService {
	/**
	 * 创建计划 
	 * @param Plan
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
	Plan findByPlanId(Integer PlanId);
	/**
	 * 根据计划名称查找需求
	 * @param Plan
	 * @return
	 */
	List<Story> findByPlanName(String planName);
	
	/**
	 * 根据计划ID编辑计划
	 * @param Plan
	 * @return
	 */
	int edit(Integer PlanId);
	
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
	List<Story> find(Integer planId);
/*	
	*//**
	 * 根据计划ID查找关联BUG
	 * @param BUGs
	 * @return
	 *//*
	List<Bug> findByPlanId(Integer planId);*/
	/**
	 * 根据计划对象分页查询
	 * @param currentPage
	 * @param limit
	 * @param plan
	 * @return
	 */
	Pager<Plan> findPager(int currentPage,int limit ,Plan plan);
	
	/**
	 * 根据计划Id批量删除
	 * @param PlanIds
	 * @return
	 */
	int deleteByPlanIds(Integer... planIds);
	
	
}