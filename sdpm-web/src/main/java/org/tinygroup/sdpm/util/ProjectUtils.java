package org.tinygroup.sdpm.util;

import org.tinygroup.sdpm.common.util.Collections3;
import org.tinygroup.sdpm.org.dao.pojo.OrgRole;
import org.tinygroup.sdpm.project.dao.pojo.Project;
import org.tinygroup.sdpm.project.service.impl.ProjectServiceImpl;
import org.tinygroup.sdpm.project.service.inter.ProjectService;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目数据工具类
 * Created by Hulk on 2015/10/29.
 */
public class ProjectUtils {
    private static final String CMS_CACHE = "cmsCache";
    private static final String CMS_CACHE_PROJECT_LIST = "projectList";
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
            List<Project> listByTeamUserId = projectService.findListByTeamUserId(UserUtils.getUserId());
            if (listByTeamUserId != null) {
                // 私有项目，根据角色定义
                userProjectList.addAll(listByTeamUserId);
            }
            projectList = projectService.findList();
            UserUtils.putCache(USER_CACHE_PROJECT_LIST, projectList);
        }
        return userProjectList;
    }

    /**
     * 判断当前用户是否属于项目白名单成员
     *
     * @param whiteList
     * @return
     */
    protected static Boolean hasProjectByRole(String whiteList) {
        String[] split = whiteList.split(",");
        if (split == null || split.length == 0) {
            return false;
        }
        List<OrgRole> userRoleList = UserUtils.getUserRoleList();
        if (userRoleList == null || userRoleList.isEmpty()) {
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
    }

    /**
     * 获得项目列表
     */
    public static Project getProject(String projectId) {
        List<Project> projectList = getProjectList();
        if (projectId == null && projectList != null && !projectList.isEmpty()) {
            return projectList.get(0);
        }
        for (Project project : projectList) {
            if (project.getProjectId().toString().equals(projectId)) {
                return project;
            }
        }
        return new Project();
    }

}
