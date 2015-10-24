package org.tinygroup.sdpm.statistic.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.statistic.biz.inter.StatisticManager;
import org.tinygroup.sdpm.statistic.dao.StatisticDao;
import org.tinygroup.sdpm.statistic.dao.pojo.*;

import java.util.List;
@Service
@Transactional
public class StatisticManagerImpl implements StatisticManager{
	@Autowired
	private StatisticDao statisticDao;

	public List<StatisticOrg> findList(StatisticOrg statisticOrg) {
		// TODO Auto-generated method stub
		return statisticDao.findList(statisticOrg);
	}

	public List<ProjectTaskSta> findListProTask(ProjectTaskSta projectTaskSta) {
		return statisticDao.findProTasks(projectTaskSta);
	}

	public List<QualityBugSta> findBugCreate(QualityBugSta qualityBugSta) {
		return statisticDao.findBugCreate(qualityBugSta);
	}

	public List<Assigned> findAssigned(Assigned assigned) {
		return statisticDao.findAssigned(assigned);
	}

	public List<QualityBugCall> findBugCall(QualityBugCall qualityBugCall) {
		return statisticDao.findBugCall(qualityBugCall);
	}

}