package org.tinygroup.sdpm.project.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.project.biz.inter.ProjectManager;
import org.tinygroup.sdpm.project.dao.ProjectDao;
import org.tinygroup.sdpm.project.dao.pojo.Project;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

/**
 * Created by shenly13343 on 2015-09-18.
 */
@Service
@Transactional
public class ProjectManagerImpl implements ProjectManager {
    @Autowired
    private ProjectDao projectDao;

    public Project find(int projectId) {
        return projectDao.getByKey(projectId);
    }

    public List<Project> findList() {
        Project project = new Project();
        return projectDao.query(project);
    }

    public Pager<Project> findPagerProjects(Integer start, Integer limit, String sortName, boolean asc) {
        Project project = new Project();
        Pager<Project> projectPager = null;
        if (StringUtil.isBlank(sortName)) {
            projectPager = projectDao.queryPager(start, limit, project);
        } else {
            OrderBy orderBy = new OrderBy(sortName, asc);
            projectPager = projectDao.queryPager(start, limit, project, orderBy);
        }

        for (Project p : projectPager.getRecords()) {
            p.setPercent(projectDao.getTime(p).getPercent());
        }
        return projectPager;
    }

    public Project add(Project project) {
        return projectDao.add(project);
    }

    public Integer update(Project project) {
        return projectDao.edit(project);
    }

    public Integer delete(int projectId) {
        Project project = new Project();
        project.setProjectId(projectId);
        project.setProjectDeleted(project.DELETE_YES);
        return projectDao.edit(project);
    }
}
