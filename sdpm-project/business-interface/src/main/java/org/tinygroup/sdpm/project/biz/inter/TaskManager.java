package org.tinygroup.sdpm.project.biz.inter;

import org.tinygroup.sdpm.project.dao.pojo.ProjectTask;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

/**
 * Created by shenly13343 on 2015-09-18.
 */
public interface TaskManager {

    public int[] batchAdd(List<ProjectTask> taskList);

    /**
     * 根据主键id查找用户
     *
     * @param taskId 主键
     * @return
     */
    ProjectTask find(int taskId);

    /**
     * 根据条件查询List
     *
     * @param task 用于查询条件
     * @return
     */
    List<ProjectTask> findList(ProjectTask task);

    /**
     * 根据需求查询任务数量
     *
     * @param storyId
     * @return
     */
    public Integer getTaskSumByStory(Integer storyId);

    /**
     * 无状态查询
     *
     * @param start
     * @param limit
     * @param task
     * @param sortName
     * @param asc
     * @return
     */
    Pager<ProjectTask> findPager(Integer start, Integer limit, ProjectTask task, String sortName, boolean asc);

    /**
     * 加入状态
     * @param start
     * @param limit
     * @param task
     * @param sortName
     * @param asc
     * @param conditon
     * @return
     */
    public Pager<ProjectTask> findPagerByStatu(Integer start, Integer limit, ProjectTask task, String sortName, boolean asc, String conditon);

    /**
     * 新增有一个用户
     *
     * @param task 新增实体类
     * @return
     */
    ProjectTask add(ProjectTask task);

    /**
     * 更新用户
     *
     * @param task 需要更新的实体类
     * @return
     */
    Integer update(ProjectTask task);

    /**
     * 更新用户
     *
     * @param task 需要更新的实体类
     * @return
     */
    Integer updateTask(ProjectTask task);
    /**
     * 更新用户
     *
     * @param task 需要更新的实体类
     * @return
     */
    Integer updateEditTask(ProjectTask task);
    /**
     * 更新用户
     *
     * @param task 需要更新的实体类
     * @return
     */
    Integer updateCallTask(ProjectTask task);
    /**
     * 更新用户
     *
     * @param task 需要更新的实体类
     * @return
     */
    Integer updateFinishTask(ProjectTask task);
    /**
     * 更新用户
     *
     * @param task 需要更新的实体类
     * @return
     */
    Integer updateStartTask(ProjectTask task);


    /**
     * 根据id进行软删除用户
     *
     * @param id 主键
     * @return
     */
    Integer delete(int id);

    Integer updateCloseTask(ProjectTask task);
}
