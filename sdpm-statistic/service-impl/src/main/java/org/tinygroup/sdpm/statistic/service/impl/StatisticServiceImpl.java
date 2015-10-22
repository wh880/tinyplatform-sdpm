package org.tinygroup.sdpm.statistic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.statistic.biz.inter.StatisticManager;
import org.tinygroup.sdpm.statistic.dao.pojo.ProjectTaskSta;
import org.tinygroup.sdpm.statistic.dao.pojo.StatisticOrg;
import org.tinygroup.sdpm.statistic.service.inter.StatisticService;

import java.util.List;
@Component
public class StatisticServiceImpl implements StatisticService{
   @Autowired
   private StatisticManager statisticManager;
	public List<StatisticOrg> findOrgList(StatisticOrg statisticOrg) {
		// TODO Auto-generated method stub
		return statisticManager.findList(statisticOrg);
	}

	public List<ProjectTaskSta> findProTasks(ProjectTaskSta projectTaskSta) {
		return statisticManager.findListProTask(projectTaskSta);
	}

}
