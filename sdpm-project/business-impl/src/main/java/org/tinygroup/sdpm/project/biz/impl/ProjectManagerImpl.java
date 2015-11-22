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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by shenly13343 on 2015-09-18.
 */
@Service
@Transactional
public class ProjectManagerImpl implements ProjectManager {
    @Autowired
    private ProjectDao projectDao;

    public List<Project> findList() {
        Project project = new Project();
        project.setProjectDeleted(Project.DELETE_NO);
        return projectDao.query(project);
    }

    public List<Project> findListByTeamUserId(String userId) {
        return projectDao.findListByTeamUserId(userId);
    }

    public List<Project> findListProjects(Project project,Date startDate,Date endDate) {
        return projectDao.findListWithStatistics(project,startDate,endDate);
    }

    public Pager<Project> findPagerProjects(Integer start, Integer limit, String sortName, boolean asc, Integer... ids) {
        if(ids==null)return new Pager<Project>(0,0,new ArrayList<Project>());
        if (StringUtil.isBlank(sortName)) {
            return projectDao.findPageWithStatistics(start, limit, new Project(), ids);
        } else {
            OrderBy orderBy;
            if ("project_left".equals(sortName)) {
                orderBy = new OrderBy("estimate", asc);
            } else if ("project_consume".equals(sortName)) {
                orderBy = new OrderBy("consumed", asc);
            } else {
                orderBy = new OrderBy(sortName, asc);
            }
            return projectDao.findPageWithStatistics(start, limit, new Project(), ids, orderBy);
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

    public Integer batchDelete(Integer[] projectIds) {
        ArrayList<Project> list = new ArrayList<Project>();
        for (Integer projectId : projectIds) {
            Project project = new Project();
            project.setProjectId(projectId);
            project.setProjectDeleted(Project.DELETE_YES);
            list.add(project);
        }
        int[] effective = projectDao.batchUpdate(list);
        if (effective == null) {
            return 0;
        }
        return effective.length;
    }

    public List<Project> findListByIds(List<Integer> ids) {
        if (ids.isEmpty()) {
            return null;
        } else {
            return projectDao.findByIds(ids.toArray(new Integer[0]));
        }
    }

    public Project find(Integer projectId) {
        try {
            return projectDao.getByKey(projectId);
        } catch (Exception e) {
            return null;
        }
    }

    public List<Project> findList(Project project, String order, String orderType) {
        if (order != null) {
            return projectDao.query(project, new OrderBy(FieldUtil.stringFormat(order), !("desc".equals(orderType)) ? true : false));
        } else {
            return projectDao.query(project);
        }

    }

    public Pager<Project> findPager(int start, int limit, Project project, String order, String orderType) {
        return projectDao.queryPager((start - 1) * limit, limit, project, new OrderBy(FieldUtil.stringFormat(order), !("desc".equals(orderType)) ? true : false));
    }

    public List<Project> getProjectByStoryId(Integer storyId) {
        return projectDao.getProjectByStoryId(storyId);
    }
}
