package org.tinygroup.sdpm.statistic.biz.inter;

import org.tinygroup.sdpm.statistic.dao.pojo.*;

import java.util.List;

public interface StatisticManager {
	/**
	 * 查询员工负载
	 * @param statisticOrg
	 * @return
	 */
     List<StatisticOrg> findList(StatisticOrg statisticOrg);

	/**
	 * 查询项目的任务数，消耗
	 * @param projectTaskSta
	 * @return
     */
	List<ProjectTaskSta> findListProTask(ProjectTaskSta projectTaskSta);
	/**
	 * Bug创建
	 * @param qualityBugSta
	 * @return
	 */
	List<QualityBugSta> findBugCreate(QualityBugSta qualityBugSta);

	/**
	 * 获取被指派人和其bug数
	 * @param assigned
	 * @return
	 */
	List<Assigned> findAssigned(Assigned assigned);

	/**
	 * 产品Bug数
	 * @param qualityBugCall
	 * @return
	 */
	List<QualityBugCall> findBugCall(QualityBugCall qualityBugCall);
	/**
	 * 产品投入表
	 * @param productProject
	 * @return
	 */
	List<ProductProject> productProjects(ProductProject productProject);
}
