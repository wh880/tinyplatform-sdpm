package org.tinygroup.sdpm.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.project.biz.inter.TaskRelationManager;
import org.tinygroup.sdpm.project.dao.ProjectTaskrelationDao;
import org.tinygroup.sdpm.project.dao.pojo.ProjectTaskrelation;
import org.tinygroup.sdpm.project.service.inter.TaskRelationService;

import java.util.List;

/**
 * Created by wangying14938 on 2015-09-18.
 */
@Component
public class TaskRelationServiceImpl implements TaskRelationService {
    @Autowired
    private TaskRelationManager taskRelationManager;

    private ProjectTaskrelationDao taskrelationDao;

    public ProjectTaskrelation add(TaskRelationService taskRelationService) {
        return null;
    }

    public List<TaskRelationService> findByProjectId(int projectId) {
        return null;
    }

    public int deleteById(int id) {
        return 0;
    }
}
