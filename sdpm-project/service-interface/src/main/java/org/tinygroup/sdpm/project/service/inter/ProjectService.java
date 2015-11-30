package org.tinygroup.sdpm.project.service.inter;


import org.tinygroup.sdpm.project.dao.pojo.Project;
import org.tinygroup.tinysqldsl.Pager;

import java.util.Date;
import java.util.List;

/**
 * Created by shenly13343 on 2015-09-17.
 */
public interface ProjectService {

    /**
     * 查找用户所在团队所拥有的项目
     *
     * @param userId OrgUser.Id
     * @param acl 权限控制
     * @return
     */
    List<Project> findListByTeamUserId(String userId, String acl);

    /**
     * 新增项目，保证项目名称唯一
     *
     * @param project
     * @return
     */
    Project addProject(Project project);

    /**
     * 查找所有项目
     *
     * @return
     */
    List<Project> findList();

    /**
     * 查询项目，包括统计总消耗等数据
     *
     * @param project
     * @return
     */
    List<Project> findProjects(Project project, Date startDate, Date endDate);

    /**
     * 批量删除项目
     *
     * @param projectIds
     * @return
     */
    Integer batchDeleteProject(Integer... projectIds);

    /**
     * 查询所有项目
     *
     * @param start
     * @param limit
     * @param orderType
     * @param order
     * @return
     */
    Pager<Project> findProjects(Integer start, Integer limit, String order, String orderType, Integer... ids);

    /**
     * 根据项目id查找
     *
     * @param projectId
     * @return
     */
    Project findProjectById(Integer projectId);

    /**
     * 产品id查询所有关联的项目id，放入list
     * 根据项目id查询数据
     *
     * @param list
     * @return
     */
    List<Project> findByProjectList(List<Integer> list);

    /**
     * 对象查询(排序)
     *
     * @param project
     * @param order
     * @param ordertype
     * @return
     */
    List<Project> findProjectList(Project project, String order, String ordertype);


    /**
     * 分页查询(排序)
     *
     * @param page
     * @param pagesize
     * @param project
     * @param order
     * @param ordertype
     * @return
     */
    Pager<Project> findProjectPager(int page, int pagesize, Project project, String order, String ordertype);


    /**
     * 更新项目，包括指派，开始，完成，关闭，编辑
     * 注意：1.状态不可逆转 2.有前置条件判断
     *
     * @param project
     * @return
     */
    Integer updateProject(Project project);

    List<Project> getProjectByStoryId(Integer storyId);

    /**
     * 根据输入查询
     * @param condition
     * @param ids
     * @return
     */
    List<Project> projectInCondition(String condition,Integer ...ids);
}
