package org.tinygroup.sdpm.statistic.dao;

import org.tinygroup.sdpm.statistic.dao.pojo.*;

import java.util.List;

public interface StatisticDao {
	 List<StatisticOrg> findList(StatisticOrg statisticOrg);

	/**
	 * 获取项目的任务数
	 * @param projectTaskSta
	 * @return
     */
	 List<ProjectTaskSta> findProTasks(ProjectTaskSta projectTaskSta);

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
}