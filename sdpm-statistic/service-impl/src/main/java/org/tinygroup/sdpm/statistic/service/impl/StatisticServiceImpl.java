package org.tinygroup.sdpm.statistic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.statistic.biz.inter.StatisticManager;
import org.tinygroup.sdpm.statistic.dao.pojo.*;
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

	public List<QualityBugSta> findBugCreate(QualityBugSta qualityBugSta) {
		return statisticManager.findBugCreate(qualityBugSta);
	}

	public List<Assigned> findAssigned(Assigned assigned) {
		return statisticManager.findAssigned(assigned);
	}

	public List<QualityBugCall> findBugCall(QualityBugCall qualityBugCall) {
		return statisticManager.findBugCall(qualityBugCall);
	}

	public List<ProductProject> productProjects(ProductProject productProject) {
		return statisticManager.productProjects(productProject);
	}

}
