package org.tinygroup.sdpm.project.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.project.biz.inter.ProjectManager;
import org.tinygroup.sdpm.project.dao.ProjectDao;
import org.tinygroup.sdpm.project.dao.impl.FieldUtil;
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
//        Pager<Project> projectPager = null;
//        if (StringUtil.isBlank(sortName)) {
//            projectPager = projectDao.queryPager(start, limit, project);
//        } else {
//            OrderBy orderBy = new OrderBy(sortName, asc);
//            projectPager = projectDao.queryPager(start, limit, project, orderBy);
//        }
//
//        for (Project p : projectPager.getRecords()) {
//            p.setPercent(projectDao.getTime(p).getPercent());
//        }
//        return projectPager;

        if (StringUtil.isBlank(sortName)) {
            return projectDao.tquerytAll(start, limit, new Project());
        } else {
            OrderBy orderBy = null;
            if ("project_left".equals(sortName)) {
                orderBy = new OrderBy("estimate", asc);
            } else if ("project_consume".equals(sortName)) {
                orderBy = new OrderBy("consumed", asc);
            } else {
                orderBy = new OrderBy(sortName, asc);
            }
            return projectDao.tquerytAll(start, limit, new Project(), orderBy);
        }
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

    public List<Project> findList(List<Integer> ids) {
        if (ids.isEmpty()) {
            return null;
        } else {
            String condition = "projectId in ( ";
            for (Integer id : ids) {
                condition = condition + id + " ";
            }
            condition = condition + ")";
            return projectDao.findByCondition(condition);
        }
    }

    public Project find(Integer projectId) {

        return projectDao.getByKey(projectId);
    }

    public List<Project> findList(Project project, String order, String ordertype) {
        if (order != null) {
            return projectDao.query(project, new OrderBy(FieldUtil.stringFormat(order), !("desc".equals(ordertype)) ? true : false));
        } else {
            return projectDao.query(project);
        }

    }

    public Pager<Project> findPager(int start, int limit, Project project, String order, String ordertype) {

        return projectDao.queryPager((start - 1) * limit, limit, project, new OrderBy(FieldUtil.stringFormat(order), !("desc".equals(ordertype)) ? true : false));
    }

    public List<Project> getProjectByStoryId(Integer storyId) {

        return projectDao.getProjectByStoryId(storyId);
    }
}
