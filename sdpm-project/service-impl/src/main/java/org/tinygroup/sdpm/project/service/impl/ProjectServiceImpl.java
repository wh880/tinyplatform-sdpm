package org.tinygroup.sdpm.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.project.biz.inter.ProjectManager;
import org.tinygroup.sdpm.project.dao.pojo.Project;
import org.tinygroup.sdpm.project.service.inter.ProjectService;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;


/**
 * Created by shenly13343 on 2015-09-18.
 */
@Component
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectManager projectManager;

    public Project add(Project project) {
        return projectManager.add(project);
    }

    public List<Project> findList() {
        return projectManager.findList();
    }

    public Project findByName(String projectName) {
        return null;
    }

    public Project findById(int projectId) {
        return projectManager.find(projectId);
    }

    public List<Project> findByProjectList(List<Integer> list) {
        return null;
    }

    public Pager<Project> findProjects(Integer start, Integer limit, String order, String ordertype) {

        return projectManager.findPagerProjects(start, limit, order, "asc".equals(ordertype) ? true : false);
    }
}
