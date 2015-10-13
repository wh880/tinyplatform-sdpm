package org.tinygroup.sdpm.project.service.inter;

import org.tinygroup.sdpm.project.dao.pojo.ProjectTask;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;
import java.util.Map;

/**
 * Created by shenly13343 on 2015-09-20.
 */
public interface TaskService {

    public Integer batchAdd(List<ProjectTask> taskList, Integer projectId);

    /**
     * 新建任务
     *
     * @param Task
     * @return
     */
    public ProjectTask addTask(ProjectTask Task);

    /**
     * 分组产看任务
     *
     * @param colum
     * @param projectId
     * @return
     */
    public Map<String, List<ProjectTask>> findTaskByGroup(int projectId, String colum);

    /**
     * 根据任务状态进行查询
     *
     * @param task
     * @return
     */
    public List<ProjectTask> findListTask(ProjectTask task);

    /**
     * 分页查询
     * @param start
     * @param limit
     * @param task
     * @param sortName
     * @return
     */
    public Pager<ProjectTask> findPagerTask(Integer start, Integer limit, ProjectTask task, String sortName, boolean asc, String condition, String group);

    /**
     * 根据taskId主键查询
     * @param taskId
     * @return
     */
    public ProjectTask findTask(Integer taskId);

    /**
     * 跟新任务，包括指派，开始，完成，关闭，编辑
     * 注意：1.状态不可逆转 2.有前置条件判断
     *
     * @param projectTask
     * @return
     */
    public Integer updateTask(ProjectTask projectTask);
    /**
     * 跟新任务，包括指派，开始，完成，关闭，编辑
     * 注意：1.状态不可逆转 2.有前置条件判断
     *
     * @param projectTask
     * @return
     */
    public Integer updateEditTask(ProjectTask projectTask);
    /**
     * 跟新任务，包括指派，开始，完成，关闭，编辑
     * 注意：1.状态不可逆转 2.有前置条件判断
     *
     * @param projectTask
     * @return
     */
    public Integer updateCallTask(ProjectTask projectTask);
    /**
     * 跟新任务，包括指派，开始，完成，关闭，编辑
     * 注意：1.状态不可逆转 2.有前置条件判断
     *
     * @param projectTask
     * @return
     */
    public Integer updateCloseTask(ProjectTask projectTask);
    /**
     * 跟新任务，包括指派，开始，完成，关闭，编辑
     * 注意：1.状态不可逆转 2.有前置条件判断
     *
     * @param projectTask
     * @return
     */
    public Integer updateStartTask(ProjectTask projectTask);
    /**
     * 跟新任务，包括指派，开始，完成，关闭，编辑
     * 注意：1.状态不可逆转 2.有前置条件判断
     *
     * @param projectTask
     * @return
     */
    public Integer updateFinishTask(ProjectTask projectTask);

    /**
     * 复杂查询
     *
     * @return
     */
    public Pager<ProjectTask> findComplexTask();
}
