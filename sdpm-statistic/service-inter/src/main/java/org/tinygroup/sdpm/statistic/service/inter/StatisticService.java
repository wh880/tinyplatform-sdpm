package org.tinygroup.sdpm.statistic.service.inter;

import org.tinygroup.sdpm.statistic.dao.pojo.*;

import java.util.Date;
import java.util.List;

public interface StatisticService {

    /**
     * 查询project的任务数，消耗
     *
     * @param projectTaskSta
     * @return
     */
    List<ProjectTaskSta> findProjectTaskStaList(ProjectTaskSta projectTaskSta, Date startDate, Date endDate, Integer roleId);

    /**
     * Bug创建
     *
     * @param qualityBugSta
     * @return
     */
    List<QualityBugSta> findQualityBugSta(QualityBugSta qualityBugSta, Date startDate, Date endDate, Integer cProject, Integer cProduct);

    /**
     * 获取被指派人和其bug数
     *
     * @param assigned
     * @return
     */
    List<Assigned> findAssigned(Assigned assigned);

    /**
     * 产品Bug数
     *
     * @param qualityBugCall
     * @return
     */
    List<QualityBugCall> findBugCall(QualityBugCall qualityBugCall);

    /**
     * 产品投入表
     *
     * @param productProject
     * @return
     */
    List<ProductProject> getProductInvest(ProductProject productProject, boolean deleted, String usrId);
}
