package org.tinygroup.sdpm.statistic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.statistic.biz.inter.StatisticManager;
import org.tinygroup.sdpm.statistic.dao.pojo.*;
import org.tinygroup.sdpm.statistic.service.inter.StatisticService;

import java.util.Date;
import java.util.List;

@Component
public class StatisticServiceImpl implements StatisticService {
    @Autowired
    private StatisticManager statisticManager;

    public List<StatisticOrg> findOrgList(StatisticOrg statisticOrg) {
        // TODO Auto-generated method stub
        return statisticManager.findList(statisticOrg);
    }

    public List<ProjectTaskSta> findProTasks(ProjectTaskSta projectTaskSta, Date startDate, Date endDate, Integer roleId) {
        return statisticManager.findListProTask(projectTaskSta, startDate, endDate, roleId);
    }

    public List<QualityBugSta> findBugCreate(QualityBugSta qualityBugSta, Date startDate, Date endDate, Integer cProject, Integer cProduct) {
        return statisticManager.findBugCreate(qualityBugSta, startDate, endDate, cProject, cProduct);
    }

    public List<Assigned> findAssigned(Assigned assigned) {
        return statisticManager.findAssigned(assigned);
    }

    public List<QualityBugCall> findBugCall(QualityBugCall qualityBugCall) {
        return statisticManager.findBugCall(qualityBugCall);
    }

    public List<ProductProject> productProjects(ProductProject productProject, boolean deleted, String userId) {
        return statisticManager.productProjects(productProject, deleted, userId);
    }

}
