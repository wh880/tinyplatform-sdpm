package org.tinygroup.sdpm.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.common.util.DateUtils;
import org.tinygroup.sdpm.project.biz.inter.BurnManager;
import org.tinygroup.sdpm.project.biz.inter.ProjectManager;
import org.tinygroup.sdpm.project.biz.inter.TaskManager;
import org.tinygroup.sdpm.project.dao.pojo.Project;
import org.tinygroup.sdpm.project.dao.pojo.ProjectBurn;
import org.tinygroup.sdpm.project.service.dto.BurnDTO;
import org.tinygroup.sdpm.project.service.inter.BurnService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wangying14938 on 2015-09-18.
 */
@Component
@Transactional
public class BurnServiceImpl implements BurnService {
    @Autowired
    private BurnManager burnManager;
    @Autowired
    private ProjectManager projectManager;
    @Autowired
    private TaskManager taskManager;

    public void updateBurnByProjectId(Integer projectId) {
        Project project = new Project();
        project.setProjectId(projectId);
        project = projectManager.findListProjects(project, null, null).get(0);
        ProjectBurn burn = new ProjectBurn();
        burn.setProjectId(project.getProjectId());
        burn.setBurnDate(DateUtils.getDateStart(new Date()));
        List<ProjectBurn> burnList = burnManager.findList(burn);//查询本项目当天的燃尽情况

        burn.setBurnConsumed(project.getConsumed());
        burn.setBurnLeft(project.getAllLeft());
        if (burnList.isEmpty()) {
            burnManager.add(burn);
        } else {
            burn.setId(burnList.get(0).getId());
            burnManager.update(burn);
        }
    }
    @Transactional(readOnly = true)
    public BurnDTO initBurn(Integer projectId, Integer interval) {
        if (interval == null) {
            interval = 1;
        }
        DateFormat format = new SimpleDateFormat("\"M/d\"");//日期格式"M/d"
        Project project = projectManager.find(projectId);
        Date startData = project.getProjectBegin();
        Date endData = project.getProjectEnd();
        //项目周期
        int period = (int) DateUtils.getDistanceOfTwoDate(startData, endData);

        ProjectBurn projectBurn = new ProjectBurn();
        projectBurn.setProjectId(projectId);
        List<ProjectBurn> projectBurnList = burnManager.findList(projectBurn);

        //均线起始值
        float topLeft = getMaxLeftTime(projectBurnList);
        //均线斜率
        Float rake = topLeft / period;

        Date nextDay = startData;
        Float tLeft = 0f;

        Date today = new Date();
        List<Float> leftList = new ArrayList<Float>();
        List<Float> averageList = new ArrayList<Float>();
        List<String> dateList = new ArrayList<String>();
        int i = 0;
        while (i <= period) {
            projectBurn = getProjectBurnByDate(projectBurnList, nextDay);
            if (projectBurn != null) {
                tLeft = projectBurn.getBurnLeft();
            }
            if (0 == i % interval || i == period) {
                averageList.add(topLeft - rake * i);
                dateList.add(format.format(nextDay));
                if (projectBurn != null || today.after(nextDay)) {
                    leftList.add(tLeft);
                }
            }
            i++;
            nextDay = DateUtils.addDays(startData, i);
        }
        BurnDTO burnDTO = new BurnDTO();
        burnDTO.setAverageValues(StringUtil.join(averageList, ","));
        burnDTO.setDayValues(StringUtil.join(dateList, ","));
        burnDTO.setLeftValues(StringUtil.join(leftList, ","));
        return burnDTO;
    }


    /**
     * 获取列表中最大剩余工作时长
     *
     * @param list
     * @return
     */
    protected Float getMaxLeftTime(List<ProjectBurn> list) {
        //均线起始值
        float topLeft = 0f;
        if (list != null && !list.isEmpty()) {
            for (ProjectBurn burn : list) {
                if (topLeft < burn.getBurnLeft()) {
                    topLeft = burn.getBurnLeft();
                }
            }
        }
        return topLeft;
    }

    /**
     * 获取列表中最大剩余工作时长
     *
     * @param projectBurnList
     * @param date
     * @return
     */
    protected ProjectBurn getProjectBurnByDate(List<ProjectBurn> projectBurnList, Date date) {
        for (ProjectBurn burn : projectBurnList) {
            if (burn.getBurnDate().getTime() == date.getTime()) {
                return burn;
            }
        }
        return null;
    }

}
