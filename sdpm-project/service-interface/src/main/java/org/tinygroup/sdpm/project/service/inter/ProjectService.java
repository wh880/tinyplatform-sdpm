package org.tinygroup.sdpm.project.service.inter;


import org.tinygroup.aopcache.annotation.CacheGet;
import org.tinygroup.aopcache.annotation.CachePut;
import org.tinygroup.aopcache.annotation.CacheRemove;
import org.tinygroup.sdpm.project.dao.pojo.Project;
import org.tinygroup.tinysqldsl.Pager;

import java.util.Date;
import java.util.List;

/**
 * Created by shenly13343 on 2015-09-17.
 */
public interface ProjectService {
    String CACHE_USER_PROJECT_LIST = "userProjectList";
    String CACHE_PROJECT_ID = "projectId";

    /**
     * 获得用户所能见的项目列表
     */
    @CacheGet(key = "${userId}", group = CACHE_USER_PROJECT_LIST)
    List<Project> getUserProjectList(String user111Id);

    /**
     * 新增项目，保证项目名称唯一
     *
     * @param project
     * @return
     */
    @CacheRemove(removeGroups = {CACHE_USER_PROJECT_LIST})
    Project addProject(Project project);


    /**
     * 查询项目，包括统计总消耗等数据
     *
     * @param project
     * @return
     */
    List<Project> findProjectBetween(Project project, Date startDate, Date endDate);

    /**
     * 批量删除项目
     *
     * @param projectIds
     * @return
     */
    @CacheRemove(removeGroups = {CACHE_USER_PROJECT_LIST, CACHE_PROJECT_ID})
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
    @CacheGet(group = CACHE_PROJECT_ID, key = "${projectId}")
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
     * @param orderType
     * @return
     */
    List<Project> findProjectList(Project project, String order, String orderType);


    /**
     * 分页查询(排序)
     *
     * @param page
     * @param pageSize
     * @param project
     * @param order
     * @param orderType
     * @return
     */
    Pager<Project> findProjectPager(int page, int pageSize, Project project, String order, String orderType);


    /**
     * 更新项目，包括指派，开始，完成，关闭，编辑
     * 注意：1.状态不可逆转 2.有前置条件判断
     *
     * @param project
     * @return
     */
    @CachePut(keys = "${project.projectId}", parameterNames = "project", group = CACHE_PROJECT_ID, removeGroups = CACHE_USER_PROJECT_LIST)
    Integer updateProject(Project project);

    List<Project> getProjectByStoryId(Integer storyId);

    /**
     * 根据输入查询
     *
     * @param condition
     * @param ids
     * @return
     */
    List<Project> projectInCondition(String condition, Integer... ids);
}
