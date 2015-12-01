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

//    /**
//     * 判断当前用户是否属于项目白名单成员
//     *
//     * @param whiteList
//     * @return
//     */
//    protected static Boolean hasProjectByRole(String whiteList) {
//        if (StringUtil.isBlank(whiteList)) {
//            return false;
//        }
//        String[] split = whiteList.split(",");
//        if (ArrayUtil.isEmptyArray(split)) {
//            return false;
//        }
//        List<OrgRole> userRoleList = UserUtils.getUserRoleList();
//        if (CollectionUtil.isEmpty(userRoleList)) {
//            return false;
//        }
//        List<String> orgRoleId = Collections3.extractToList(userRoleList, "orgRoleId");
//        for (String s : split) {
//            return orgRoleId.contains(s);
//        }
//        return false;
//    }

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

    public List<Project> findProjects(Project project, Date startDate, Date endDate) {
        return projectManager.findListProjects(project, startDate, endDate);
    }

    public Integer batchDeleteProject(Integer[] projectIds) {
        if (projectIds == null || projectIds.length == 0) {
            return 0;
        }
        return projectManager.batchDelete(projectIds);
    }

    public Pager<Project> findProjects(Integer start, Integer limit, String order, String orderType, Integer... ids) {
        return projectManager.findPagerProjects(start, limit, order, "asc".equals(orderType) ? true : false, ids);
    }

//    public List<Project> getUserProjectList(String userId) {
//        List<Project> userProjectList = new ArrayList<Project>();
//        List<Project> projectList = findList();
//        for (Project project : projectList) {
//            String projectAcl = project.getProjectAcl();
//            if (Project.ACL_OPEN.equals(projectAcl)) {
//                // 开放项目
//                userProjectList.add(project);
//            } else if (Project.ACL_CUSTOM.equals(projectAcl) && hasProjectByRole(project.getProjectWhiteList())) {
//                // 自定义白名单
//                userProjectList.add(project);
//            }
//        }
//        List<Project> listByTeamUserId = findListByTeamUserId(userId, Project.ACL_PRIVATE);
//        if (listByTeamUserId != null) {
//            // 私有项目，根据角色定义
//            userProjectList.addAll(listByTeamUserId);
//        }
//        List projectIdList = Collections3.extractToList(userProjectList, "projectId");
//        List<Project> relatedProject = findListByRelatedUser(userId);
//        for (Project project : relatedProject) {
//            if (!projectIdList.contains(project.getProjectId())) {
//                userProjectList.add(project);
//            }
//        }
//        return userProjectList;
//    }

    public List<Project> findListByTeamUserId(String userId, String acl) {
        return projectManager.findListByTeamUserId(userId, acl);
    }

    public List<Project> findListByRelatedUser(String userId) {
        Project project = new Project();
        project.setProjectOpenedBy(userId);
        project.setProjectPo(userId);
        project.setProjectPm(userId);
        project.setProjectQd(userId);
        project.setProjectRd(userId);
        return projectManager.findListByRelatedUser(project);
    }

    public Project addProject(Project project) {
        project.setProjectStatus(Project.WAIT);
        project.setProjectDeleted(Project.DELETE_NO);
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

    public List<Project> projectInCondition(String condition, Integer... ids) {
        return projectManager.projectInCondition(condition, ids);
    }

}
