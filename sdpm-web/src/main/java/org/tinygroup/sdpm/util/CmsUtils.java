package org.tinygroup.sdpm.util;

import org.tinygroup.sdpm.project.dao.pojo.Project;
import org.tinygroup.sdpm.project.service.impl.ProjectServiceImpl;
import org.tinygroup.sdpm.project.service.inter.ProjectService;

import java.util.List;

/**
 * 常用数据获取工具集
 * Created by Hulk on 2015/10/21.
 */
public class CmsUtils {
    private static final String CMS_CACHE = "cmsCache";
    private static ProjectService projectService = SpringContextHolder.getBean(ProjectServiceImpl.class);

    /**
     * 获得项目列表
     */
    public static List<Project> getProjectList() {
        List<Project> projectList = (List<Project>) CacheUtils.get(CMS_CACHE, "projectList");
        if (projectList == null) {
            projectList = projectService.findList();
            CacheUtils.put(CMS_CACHE, "projectList", projectList);
        }
        return projectList;
    }

    /**
     * 清楚项目列表
     */
    public static void removetList() {
        CacheUtils.remove(CMS_CACHE, "projectList");
    }

    /**
     * 获得项目列表
     */
    public static Project getProject(Integer projectId) {
        List<Project> projectList = getProjectList();
        if (projectId == null && projectList != null && !projectList.isEmpty()) {
            return projectList.get(0);
        }
        for (Project project : projectList) {
            if (project.getProjectId().equals(projectId)) {
                return project;
            }
        }
        return new Project();
    }

}
