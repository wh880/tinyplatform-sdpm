package org.tinygroup.sdpm.statistic.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinygroup.sdpm.statistic.biz.inter.StatisticManager;
import org.tinygroup.sdpm.statistic.dao.StatisticDao;
import org.tinygroup.sdpm.statistic.dao.pojo.*;

import java.util.Date;
import java.util.List;

@Service
public class StatisticManagerImpl implements StatisticManager {
    @Autowired
    private StatisticDao statisticDao;

    public List<StatisticOrg> findList(StatisticOrg statisticOrg) {
        // TODO Auto-generated method stub
        return statisticDao.findList(statisticOrg);
    }

    public List<ProjectTaskSta> findListProTask(ProjectTaskSta projectTaskSta, Date startDate, Date endDate, Integer roleId) {
        return statisticDao.findProTasks(projectTaskSta, startDate, endDate, roleId);
    }

    public List<QualityBugSta> findBugCreate(QualityBugSta qualityBugSta, Date startDate, Date endDate, Integer cProject, Integer cProduct) {
        return statisticDao.findBugCreate(qualityBugSta, startDate, endDate, cProject, cProduct);
    }

    public List<Assigned> findAssigned(Assigned assigned) {
        return statisticDao.findAssigned(assigned);
    }

    public List<QualityBugCall> findBugCall(QualityBugCall qualityBugCall) {
        return statisticDao.findBugCall(qualityBugCall);
    }

    public List<ProductProject> productProjects(ProductProject productProject, boolean deleted, String userId) {
        return statisticDao.productProjects(productProject, deleted, userId);
    }

}
