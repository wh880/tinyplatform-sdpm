package org.tinygroup.sdpm.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.common.util.DateUtils;
import org.tinygroup.sdpm.project.biz.inter.BurnManager;
import org.tinygroup.sdpm.project.biz.inter.ProjectManager;
import org.tinygroup.sdpm.project.biz.inter.TaskManager;
import org.tinygroup.sdpm.project.dao.pojo.Project;
import org.tinygroup.sdpm.project.dao.pojo.ProjectBurn;
import org.tinygroup.sdpm.project.dao.pojo.ProjectTask;
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
public class BurnServiceImpl implements BurnService {
    @Autowired
    private BurnManager burnManager;
    @Autowired
    private ProjectManager projectManager;
    @Autowired
    private TaskManager taskManager;

    public void updateDate(Integer taskId) {
        //当任务修改以后 根据跟新的任务刷新燃尽图，统计项目

        if (taskId == null) {
            List<Project> projectList = projectManager.findListProjects(new Project());
            if (!projectList.isEmpty()) {
                for (Project p : projectList) {
                    List<Project> tList = projectManager.findListProjects(p);
                    ProjectBurn burn = new ProjectBurn();
                    burn.setProjectId(p.getProjectId());
                    burn.setBurnDate(DateUtils.getDateStart(new Date()));
                    List<ProjectBurn> res = burnManager.findList(burn);
                    burn.setBurnConsumed(tList.get(0).getConsume());
                    burn.setBurnLeft(tList.get(0).getAllLeft());
                    if (res.isEmpty()) {
                        burnManager.add(burn);
                    } else {
                        burn.setId(res.get(0).getId());
                        burnManager.update(burn);
                    }
                }
            }
        } else {
            ProjectTask task = taskManager.find(taskId);
            Project project = projectManager.find(task.getTaskProject());
            List<Project> projectList = projectManager.findListProjects(project);
            ProjectBurn burn = new ProjectBurn();
            burn.setProjectId(project.getProjectId());
            burn.setBurnDate(DateUtils.getDateStart(new Date()));
            List<ProjectBurn> res = burnManager.findList(burn);
            burn.setBurnConsumed(projectList.get(0).getConsume());
            burn.setBurnLeft(projectList.get(0).getAllLeft());
            if (res.isEmpty()) {
                burnManager.add(burn);
            } else {
                burn.setId(res.get(0).getId());
                burnManager.update(burn);
            }
        }
    }

    public BurnDTO initBurn(Integer projectId, Integer interval) {
        if (interval == null) {
            interval = 3;
        }
        DateFormat format = new SimpleDateFormat("\"M/d\"");//日期格式"M/d"
        Project project = projectManager.find(projectId);
        Date startData = project.getProjectBegin();
        Date endData = project.getProjectEnd();
        //项目周期
        float period = (float) DateUtils.getDistanceOfTwoDate(startData, endData);

        ProjectBurn projectBurn = new ProjectBurn();
        projectBurn.setProjectId(projectId);
        List<ProjectBurn> projectBurnList = burnManager.findList(projectBurn);

        //均线起始值
        float topLeft = getMaxLeftTime(projectBurnList);
        //均线斜率
        Float rake = topLeft / period;

        Date nextDay = startData;
        Float tLeft = 0f;

        List<Float> leftList = new ArrayList<Float>();
        List<Float> averageList = new ArrayList<Float>();
        List<String> dateList = new ArrayList<String>();
        for (int i = 0; i < period; i++, nextDay = DateUtils.addDays(startData, i)) {
            projectBurn = getProjectBurnByDate(projectBurnList, nextDay);
            if (projectBurn != null) {
                tLeft = projectBurn.getBurnLeft();
            }
            if (0 == i % interval) {
                averageList.add(topLeft - rake * i);
                dateList.add(format.format(nextDay));
                leftList.add(tLeft);
            }
        }
        BurnDTO burnDTO = new BurnDTO();
        burnDTO.setAverageValues(StringUtil.join(averageList, ","));
        burnDTO.setDayValues(StringUtil.join(dateList, ","));
        burnDTO.setLeftValues(StringUtil.join(leftList, ","));
        return burnDTO;
    }


    public List<ProjectBurn> findBurnByProjectId(int projectId) {
        ProjectBurn projectBurn = new ProjectBurn();
        projectBurn.setProjectId(projectId);
        return burnManager.findList(projectBurn);
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
