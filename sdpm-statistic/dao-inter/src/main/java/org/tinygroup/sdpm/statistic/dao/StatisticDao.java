package org.tinygroup.sdpm.statistic.dao;

import org.tinygroup.sdpm.statistic.dao.pojo.ProjectTaskSta;
import org.tinygroup.sdpm.statistic.dao.pojo.StatisticOrg;

import java.util.List;

public interface StatisticDao {
	 List<StatisticOrg> findList(StatisticOrg statisticOrg);

	/**
	 * 获取项目的任务数
	 * @param projectTaskSta
	 * @return
     */
	 List<ProjectTaskSta> findProTasks(ProjectTaskSta projectTaskSta);
}
