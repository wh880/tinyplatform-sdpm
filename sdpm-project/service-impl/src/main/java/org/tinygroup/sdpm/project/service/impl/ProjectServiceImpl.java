package org.tinygroup.sdpm.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.project.biz.inter.ProjectManager;
import org.tinygroup.sdpm.project.dao.pojo.Project;
import org.tinygroup.sdpm.project.service.inter.ProjectService;
import org.tinygroup.tinysqldsl.Pager;

import java.util.Date;
import java.util.List;


/**
 * Created by shenly13343 on 2015-09-18.
 */
@Component
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectManager projectManager;

    public List<Project> findList() {
        return projectManager.findList();
    }

    public Project findProjectById(Integer projectId) {
        if (projectId == null) {
            return null;
        }
        return projectManager.find(projectId);
    }

    public List<Project> findByProjectList(List<Integer> list) {
        return projectManager.findListByIds(list);
    }

    public List<Project> findProjects(Project project,Date startDate,Date endDate) {
        return projectManager.findListProjects(project,startDate,endDate);
    }

    public Integer batchDeleteProject(Integer[] projectIds) {
        if (projectIds == null || projectIds.length == 0) {
            return 0;
        }
        return projectManager.batchDelete(projectIds);
    }

    public Pager<Project> findProjects(Integer start, Integer limit, String order, String orderType, Integer... ids) {
        return projectManager.findPagerProjects(start, limit, order, "asc".equals(orderType) ? true : false,ids);
    }

    public List<Project> findListByTeamUserId(String userId, String acl) {
        return projectManager.findListByTeamUserId(userId,acl);
    }

    public Project addProject(Project project) {
        project.setProjectStatus(project.WAIT);
        project.setProjectDeleted(project.DELETE_NO);
        return projectManager.add(project);
    }

    public List<Project> findProjectList(Project project, String order, String orderType) {
        return projectManager.findList(project, order, orderType);
    }

    public Pager<Project> findProjectPager(int page, int pageSize, Project project, String order, String orderType) {
        return projectManager.findPager(page, pageSize, project, order, orderType);
    }

    public Integer updateProject(Project project) {
        return projectManager.update(project);
    }

    public List<Project> getProjectByStoryId(Integer storyId) {
        return projectManager.getProjectByStoryId(storyId);
    }

}
