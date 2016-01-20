package org.tinygroup.sdpm.project.biz.inter;

import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.sdpm.project.dao.pojo.ProjectTask;
import org.tinygroup.sdpm.project.dao.pojo.TaskChartBean;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

/**
 * Created by shenly13343 on 2015-09-18.
 */
public interface TaskManager {

    /**
     * 统计项目相关的任务工时信息
     * @param projectId
     * @return
     */
    ProjectTask getProjectTaskTimeInfo(Integer projectId);
    /**
     * 获取项目最大编号
     *
     * @param projectId
     * @return
     */
    Integer getMaxNo(Integer projectId);

    int[] batchAdd(List<ProjectTask> taskList);

    /**
     * 根据主键id查找用户
     *
     * @param taskId 主键
     * @return
     */
    ProjectTask find(Integer taskId);

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
    Integer getTaskSumByStory(Integer storyId);

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
     *
     * @param start
     * @param limit
     * @param task
     * @param sortName
     * @param asc
     * @param condition
     * @return
     */
    Pager<ProjectTask> findPagerByStatus(Integer start, Integer limit, ProjectTask task, String sortName, boolean asc, String condition);

    /**
     * 由我完成
     *
     * @param start
     * @param limit
     * @param task
     * @param sortName
     * @param asc
     * @param user
     * @return
     */
    Pager<ProjectTask> findPagerByMe(Integer start, Integer limit, ProjectTask task, String sortName, boolean asc, OrgUser user);

    ProjectTask add(ProjectTask task);

    Integer update(ProjectTask task);

    /**
     * 根据id进行软删除
     *
     * @param id 主键
     * @return
     */
    Integer delete(Integer id);

    List<TaskChartBean> findByGroup(String id);

    ProjectTask findTaskByTaskId(Integer taskId);

}
