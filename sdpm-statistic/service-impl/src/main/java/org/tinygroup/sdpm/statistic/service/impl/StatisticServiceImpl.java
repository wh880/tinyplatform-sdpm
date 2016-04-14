package org.tinygroup.sdpm.statistic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.statistic.biz.inter.StatisticManager;
import org.tinygroup.sdpm.statistic.dao.pojo.*;
import org.tinygroup.sdpm.statistic.service.inter.StatisticService;

import java.util.Date;
import java.util.List;

@Component
@Transactional
public class StatisticServiceImpl implements StatisticService {
    @Autowired
    private StatisticManager statisticManager;
    @Transactional(readOnly = true)
    public List<ProjectTaskSta> findProjectTaskStaList(ProjectTaskSta projectTaskSta, Date startDate, Date endDate, Integer roleId) {
        return statisticManager.findListProTask(projectTaskSta, startDate, endDate, roleId);
    }
    @Transactional(readOnly = true)
    public List<QualityBugSta> findQualityBugSta(QualityBugSta qualityBugSta, Date startDate, Date endDate, Integer cProject, Integer cProduct) {
        return statisticManager.findBugCreate(qualityBugSta, startDate, endDate, cProject, cProduct);
    }
    @Transactional(readOnly = true)
    public List<Assigned> findAssigned(Assigned assigned) {
        return statisticManager.findAssigned(assigned);
    }
    @Transactional(readOnly = true)
    public List<QualityBugCall> findBugCall(QualityBugCall qualityBugCall) {
        return statisticManager.findBugCall(qualityBugCall);
    }
    @Transactional(readOnly = true)
    public List<ProductProject> getProductInvest(ProductProject productProject, boolean deleted, String userId) {
        return statisticManager.productProjects(productProject, deleted, userId);
    }

}
