package org.tinygroup.sdpm.statistic.biz.inter;

import org.tinygroup.sdpm.statistic.dao.pojo.ProjectTaskSta;
import org.tinygroup.sdpm.statistic.dao.pojo.StatisticOrg;

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
}
