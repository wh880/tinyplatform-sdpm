package org.tinygroup.sdpm.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.commons.tools.ArrayUtil;
import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.common.util.Collections3;
import org.tinygroup.sdpm.org.biz.inter.RoleManager;
import org.tinygroup.sdpm.org.dao.pojo.OrgRole;
import org.tinygroup.sdpm.project.biz.inter.ProjectManager;
import org.tinygroup.sdpm.project.dao.pojo.Project;
import org.tinygroup.sdpm.project.service.inter.ProjectService;
import org.tinygroup.sdpm.quality.dao.pojo.QualityBug;
import org.tinygroup.tinysqldsl.Pager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by shenly13343 on 2015-09-18.
 */
@Component
@Transactional
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectManager projectManager;
    @Autowired
    private RoleManager roleManager;

    /**
     * 判断当前用户是否属于项目白名单成员
     *
     * @param whiteList
     * @return
     */
    @Transactional(readOnly = true)
    protected Boolean hasProjectByRole(String userId, String whiteList) {
        if (StringUtil.isBlank(whiteList)) {
            return false;
        }
        String[] split = whiteList.split(",");
        if (ArrayUtil.isEmptyArray(split)) {
            return false;
        }
        List<OrgRole> userRoleList = roleManager.findRoleByUserId(userId);
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
     * 查找所有项目
     *
     * @return
     */
    @Transactional(readOnly = true)
    private List<Project> findAllProjectList() {
        return projectManager.findList();
    }
    @Transactional(readOnly = true)
    public Project findProjectById(Integer projectId) {
        if (projectId == null) {
            return null;
        }
        return projectManager.find(projectId);
    }
    @Transactional(readOnly = true)
    public List<Project> findByProjectList(List<Integer> list) {
        return projectManager.findListByIds(list);
    }
    @Transactional(readOnly = true)
    public List<Project> findProjectBetween(Project project, Date startDate, Date endDate) {
        return projectManager.findListProjects(project, startDate, endDate);
    }

    public Integer batchDeleteProject(Integer[] projectIds) {
        if (projectIds == null || projectIds.length == 0) {
            return 0;
        }
        return projectManager.batchDelete(projectIds);
    }
    @Transactional(readOnly = true)
    public Pager<Project> findProjects(Integer start, Integer limit, String order, String orderType, Integer... ids) {
        return projectManager.findPagerProjects(start, limit, order, "asc".equals(orderType) ? true : false, ids);
    }

    public List<Project> getUserProjectList(String userId) {
        List<Project> userProjectList = new ArrayList<Project>();
        List<Project> projectList = findAllProjectList();
        for (Project project : projectList) {
            String projectAcl = project.getProjectAcl();
            if (Project.ACL_OPEN.equals(projectAcl)) {
                // 开放项目
                userProjectList.add(project);
            } else if (Project.ACL_CUSTOM.equals(projectAcl) && hasProjectByRole(userId, project.getProjectWhiteList())) {
                // 自定义白名单
                userProjectList.add(project);
            }
        }
        List<Project> listByTeamUserId = findListByTeamUserId(userId, Project.ACL_PRIVATE);
        if (listByTeamUserId != null) {
            // 私有项目，根据角色定义
            userProjectList.addAll(listByTeamUserId);
        }
        List projectIdList = Collections3.extractToList(userProjectList, "projectId");
        List<Project> relatedProject = findListByRelatedUser(userId);
        for (Project project : relatedProject) {
            if (!projectIdList.contains(project.getProjectId())) {
                userProjectList.add(project);
            }
        }
        return userProjectList;
    }
    @Transactional(readOnly = true)
    private List<Project> findListByTeamUserId(String userId, String acl) {
        return projectManager.findListByTeamUserId(userId, acl);
    }

    /**
     * 查找相关干系人的项目
     * 查询条件OR
     *
     * @param userId
     * @return
     */
    @Transactional(readOnly = true)
    private List<Project> findListByRelatedUser(String userId) {
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
    @Transactional(readOnly = true)
    public List<Project> findProjectList(Project project, String order, String orderType) {
        return projectManager.findList(project, order, orderType);
    }
    @Transactional(readOnly = true)
    public Pager<Project> findProjectPager(int page, int pageSize, Project project, String order, String orderType) {
        return projectManager.findPager(page, pageSize, project, order, orderType);
    }

    public Integer updateProject(Project project) {
        return projectManager.update(project);
    }
    @Transactional(readOnly = true)
    public List<Project> getProjectByStoryId(Integer storyId) {
        return projectManager.getProjectByStoryId(storyId);
    }
    @Transactional(readOnly = true)
    public List<Project> projectInCondition(String condition, Integer limit, Integer... ids) {
        return projectManager.projectInCondition(condition, limit, ids);
    }

    /**
     * 根据产品id查询待关联的bug list
     *
     * @param projectId
     * @return List<QualityBug>
     */
    @Override
    @Transactional(readOnly = true)
    public List<QualityBug> findRelationBugByProjectID(Integer projectId) {
        return projectManager.findList(projectId);
    }

}
