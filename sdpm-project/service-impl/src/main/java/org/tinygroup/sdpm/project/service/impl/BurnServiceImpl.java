package org.tinygroup.sdpm.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.common.util.DateUtils;
import org.tinygroup.sdpm.project.biz.inter.BurnManager;
import org.tinygroup.sdpm.project.biz.inter.ProjectManager;
import org.tinygroup.sdpm.project.biz.inter.TaskManager;
import org.tinygroup.sdpm.project.dao.pojo.Project;
import org.tinygroup.sdpm.project.dao.pojo.ProjectBurn;
import org.tinygroup.sdpm.project.dao.pojo.ProjectTask;
import org.tinygroup.sdpm.project.service.inter.BurnService;

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

    public List<ProjectBurn> findById(int projectId) {
        return null;
    }

    public void updateDate(Integer taskId) {
        //当任务修改以后 根据跟新的任务刷新燃尽图，统计项目
        ProjectBurn burn = new ProjectBurn();
        if (taskId == null) {
            //更新全部
        } else {
            ProjectTask task = taskManager.find(taskId);
            Project project = projectManager.find(task.getTaskProject());
            List<Project> projectList = projectManager.findListProjects(project);
            burn.setProjectId(project.getProjectId());
            burn.setBurnConsumed(project.getConsume());

            burn.setBurnDate(DateUtils.getDateStart(new Date()));
            List<ProjectBurn> res = burnManager.findList(burn);
        }

        burnManager.findList(burn);
    }

    public int addBurn(ProjectBurn burn) {
        return 0;
    }

    public List<ProjectBurn> findBurnByProjectId(int projectId) {
        ProjectBurn projectBurn = new ProjectBurn();
        projectBurn.setProjectId(projectId);
        return burnManager.findList(projectBurn);
    }

    public Integer deleteBurnByProjectDate(int projectId, String date) {
        return null;
    }
}
