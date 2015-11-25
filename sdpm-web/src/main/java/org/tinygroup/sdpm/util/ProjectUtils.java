package org.tinygroup.sdpm.util;

import org.tinygroup.commons.tools.ArrayUtil;
import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.common.util.Collections3;
import org.tinygroup.sdpm.org.dao.pojo.OrgRole;
import org.tinygroup.sdpm.project.dao.pojo.Project;
import org.tinygroup.sdpm.project.service.impl.ProjectServiceImpl;
import org.tinygroup.sdpm.project.service.inter.ProjectService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 项目数据工具类
 * Created by Hulk on 2015/10/29.
 */
public class ProjectUtils {
    public static final String COOKIE_PROJECT_ID = "currentProjectId";

    private static final String CMS_CACHE = "cmsCache";
    private static final String CMS_CACHE_PROJECT_LIST = "projectList";
    private static final String CMS_CACHE_PROJECT_ID_ = "project_id_";
    private static final String USER_CACHE_PROJECT_LIST = "userProjectList";
    private static ProjectService projectService = SpringContextHolder.getBean(ProjectServiceImpl.class);

    /**
     * 获得当前用户项目列表
     */
    public static List<Project> getUserProjectList() {
        List<Project> userProjectList = (List<Project>) UserUtils.getCache(USER_CACHE_PROJECT_LIST);
        if (userProjectList == null) {
            userProjectList = new ArrayList<Project>();
            List<Project> projectList = getProjectList();
            for (Project project : projectList) {
                String projectAcl = project.getProjectAcl();
                if (Project.ACL_OPEN.equals(projectAcl)) {
                    // 开放项目
                    userProjectList.add(project);
                } else if (Project.ACL_CUSTOM.equals(projectAcl) && hasProjectByRole(project.getProjectWhiteList())) {
                    // 自定义白名单
                    userProjectList.add(project);
                }
            }
            List<Project> listByTeamUserId = projectService.findListByTeamUserId(UserUtils.getUserId(),Project.ACL_PRIVATE);
            if (listByTeamUserId != null) {
                // 私有项目，根据角色定义
                userProjectList.addAll(listByTeamUserId);
            }
            UserUtils.putCache(USER_CACHE_PROJECT_LIST, userProjectList);
        }
        return userProjectList;
    }

    public static void removeUserProjectList() {
        UserUtils.removeCache(USER_CACHE_PROJECT_LIST);
    }

    /**
     * 获得当前用户项目id列表
     */
    public static Integer[] getUserProjectIdList() {
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
     * 判断当前用户是否属于项目白名单成员
     *
     * @param whiteList
     * @return
     */
    protected static Boolean hasProjectByRole(String whiteList) {
        if (StringUtil.isBlank(whiteList)) {
            return false;
        }
        String[] split = whiteList.split(",");
        if (ArrayUtil.isEmptyArray(split)) {
            return false;
        }
        List<OrgRole> userRoleList = UserUtils.getUserRoleList();
        if (CollectionUtil.isEmpty(userRoleList)) {
            return false;
        }
        List<String> orgRoleId = Collections3.extractToList(userRoleList, "orgRoleId");
        for (String s : split) {
            return orgRoleId.contains(s);
        }
        return false;
    }

    /**
     * 获得项目列表
     */
    public static List<Project> getProjectList() {
        List<Project> projectList = (List<Project>) CacheUtils.get(CMS_CACHE, CMS_CACHE_PROJECT_LIST);
        if (projectList == null) {
            projectList = projectService.findList();
            CacheUtils.put(CMS_CACHE, CMS_CACHE_PROJECT_LIST, projectList);
        }
        return projectList;
    }

    /**
     * 清楚项目列表
     */
    public static void removeProjectList() {
        CacheUtils.remove(CMS_CACHE, CMS_CACHE_PROJECT_LIST);
        UserUtils.removeCache(USER_CACHE_PROJECT_LIST);
    }

    /**
     * 获得项目列表
     */
    public static Project getProject(String projectId) {
        if (StringUtil.isBlank(projectId)) {
            return new Project();
        }
        Project project = (Project) CacheUtils.get(CMS_CACHE, CMS_CACHE_PROJECT_ID_ + projectId);
        if (project == null) {
            project = projectService.findProjectById(Integer.valueOf(projectId));
        }
        if (project == null) {
            project = new Project();
        }
        return project;
    }

    /**
     * 获得项目列表
     */
    public static Project getDefaultProject() {
        List<Project> userProjectList = getUserProjectList();
        if (CollectionUtil.isEmpty(userProjectList)) {
            return null;
        }
        return userProjectList.get(0);
    }

    /**
     * 获得项目ID 存在Cookies中
     */
    public static Integer getCurrentProjectId(HttpServletRequest request ) {
        Integer currentProjectId = null;
        String cookie = CookieUtils.getCookie(request, COOKIE_PROJECT_ID);
        if (!StringUtil.isBlank(cookie)) {
            currentProjectId = Integer.valueOf(cookie);
            return currentProjectId;
        }
        return null;
    }
    /**
     * 获得项目ID 存在Cookies中
     */
    public static Integer getCurrentProjectId(HttpServletRequest request, HttpServletResponse response) {
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
    public static Integer initCurrentProjectId(Integer currentProjectId, HttpServletResponse response) {
        if (currentProjectId == null) {
            Project defaultProject = getDefaultProject();
            if (defaultProject != null) {
                currentProjectId = defaultProject.getProjectId();
                CookieUtils.setCookie(response, COOKIE_PROJECT_ID, currentProjectId.toString());
            }
        }
        return currentProjectId;
    }
}
