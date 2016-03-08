package org.tinygroup.sdpm.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.common.util.Collections3;
import org.tinygroup.sdpm.project.dao.pojo.Project;
import org.tinygroup.sdpm.project.service.inter.ProjectService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 项目数据工具类
 * Created by Hulk on 2015/10/29.
 */
@Service
public class ProjectOperate {
    public static final String COOKIE_PROJECT_ID = "currentProjectId";

    @Autowired
    private ProjectService projectService;

    /**
     * 获得项目ID 存在Cookies中
     */
    public static Integer getCurrentProjectId(HttpServletRequest request) {
        Integer currentProjectId;
        String cookie = CookieUtils.getCookie(request, COOKIE_PROJECT_ID);
        if (!StringUtil.isBlank(cookie)) {
            currentProjectId = Integer.valueOf(cookie);
            return currentProjectId;
        }
        return null;
    }

    /**
     * 获得项目列表
     */
    public Project getDefaultProject() {
        List<Project> userProjectList = projectService.getUserProjectList(UserUtils.getUserId());
        if (CollectionUtil.isEmpty(userProjectList)) {
            return null;
        }
        return userProjectList.get(0);
    }

    /**
     * 获得项目ID 存在Cookies中
     */
    public Integer getCurrentProjectId(HttpServletRequest request, HttpServletResponse response) {
        Integer currentProjectId = null;
        String cookie = CookieUtils.getCookie(request, COOKIE_PROJECT_ID);
        if (!StringUtil.isBlank(cookie)) {
            currentProjectId = Integer.valueOf(cookie);
            return currentProjectId;
        }
        return initCurrentProjectId(currentProjectId, response);
    }

    /**
     * 获得项目ID 存在Cookies中
     */
    public Integer initCurrentProjectId(Integer currentProjectId, HttpServletResponse response) {
        if (currentProjectId == null) {
            Project defaultProject = getDefaultProject();
            if (defaultProject != null) {
                currentProjectId = defaultProject.getProjectId();
                CookieUtils.setCookie(response, COOKIE_PROJECT_ID, currentProjectId.toString());
            }
        }
        return currentProjectId;
    }

    /**
     * 获得当前用户项目列表
     */
    public List<Project> getUserProjectList() {
        return projectService.getUserProjectList(UserUtils.getUserId());
    }

    /**
     * 获得当前用户项目id列表
     */
    public Integer[] getUserProjectIdList() {
        List<Project> userProjectList = getUserProjectList();
        if (CollectionUtil.isEmpty(userProjectList)) {
            return null;
        }
        List<Integer> ids = Collections3.extractToList(userProjectList, "projectId");
        for (Project project : userProjectList) {
            ids.add(project.getProjectId());
        }
        return ids.toArray(new Integer[0]);
    }

    /**
     * 获得项目列表
     */
    public Project getProject(String projectId) {
        if (StringUtil.isBlank(projectId)) {
            return new Project();
        }
        Project project = projectService.findProjectById(Integer.valueOf(projectId));
        if (project == null) {
            project = new Project();
        }
        return project;
    }
}
