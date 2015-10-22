package org.tinygroup.sdpm.statistic.service.inter;

import org.tinygroup.sdpm.statistic.dao.pojo.ProjectTaskSta;
import org.tinygroup.sdpm.statistic.dao.pojo.StatisticOrg;

import java.util.List;

public interface StatisticService {
	/**
	 * 用户负载表
	 * @param statisticOrg
	 * @return
	 */
     List<StatisticOrg> findOrgList(StatisticOrg statisticOrg);

	/**
	 * 查询project的任务数，消耗
	 * @param projectTaskSta
	 * @return
     */
	List<ProjectTaskSta> findProTasks(ProjectTaskSta projectTaskSta);
}
