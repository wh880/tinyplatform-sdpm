package org.tinygroup.sdpm.project.biz.inter;

import org.tinygroup.sdpm.project.dao.pojo.Project;
import org.tinygroup.tinysqldsl.Pager;

import java.util.Date;
import java.util.List;

/**
 * Created by shenly13343 on 2015-09-18.
 */
public interface ProjectManager {
    /**
     * 查找用户所在团队所拥有的项目
     *
     * @param userId OrgUser.Id
     * @param acl    权限控制
     * @return
     */
    List<Project> findListByTeamUserId(String userId, String acl);

    /**
     * 查找相关干系人的项目
     * 查询条件OR
     *
     * @param project
     * @return
     */
    List<Project> findListByRelatedUser(Project project);

    /**
     * 根据项目查，关联查询了总消耗等信息
     *
     * @param project
     * @return
     */
    List<Project> findListProjects(Project project, Date startDate, Date endDate);

    /**
     * 根据ids查询项目
     *
     * @param ids
     * @return
     */
    List<Project> findListByIds(List<Integer> ids);

    /**
     * 根据主键id查找
     *
     * @param projectId 主键
     * @return
     */
    Project find(Integer projectId);

    /**
     * 查询所有项目
     *
     * @return
     */
    List<Project> findList();

    /**
     * 查询所有的project
     *
     * @param start
     * @param limit
     * @param sortName
     * @param asc
     * @return
     */
    Pager<Project> findPagerProjects(Integer start, Integer limit, String sortName, boolean asc, Integer... ids);

    /**
     * 新增项目
     *
     * @param project 新增实体类
     * @return
     */
    Project add(Project project);

    /**
     * 更新项目
     *
     * @param project 需要更新的实体类
     * @return
     */
    Integer update(Project project);

    /**
     * 删除
     *
     * @param projectId
     * @return
     */
    Integer delete(int projectId);

    /**
     * 批量删除
     *
     * @param projectIds
     * @return
     */
    Integer batchDelete(Integer[] projectIds);

    /**
     * 根据对象查找(排序)
     *
     * @param project
     * @param order
     * @param ordertype
     * @return
     */
    List<Project> findList(Project project, String order, String ordertype);

    /**
     * 根据对象查找(分页、排序)
     *
     * @param start
     * @param limit
     * @param project
     * @param order
     * @param ordertype
     * @return
     */
    Pager<Project> findPager(int start, int limit, Project project, String order, String ordertype);

    List<Project> getProjectByStoryId(Integer storyId);
    /**
     * 根据输入查询
     * @param condition
     * @param ids
     * @return
     */
    List<Project> projectInCondition(String condition,Integer ...ids);
}
