package org.tinygroup.sdpm.project.service.inter;

import org.tinygroup.sdpm.project.dao.pojo.ProjectTask;
import org.tinygroup.sdpm.project.dao.pojo.TaskChartBean;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;
import java.util.Map;

/**
 * 项目任务
 * Created by shenly13343 on 2015-09-20.
 */
public interface TaskService {

    /**
     * 激活项目
     *
     * @return
     */
    Integer updateDoingTask(ProjectTask task);

    /**
     * 批量软删除
     *
     * @param condition
     * @return
     */
    Integer batchSoftDel(String condition);

    Integer batchAdd(List<ProjectTask> taskList, Integer projectId);

    /**
     * 新建任务
     *
     * @param Task
     * @return
     */
    ProjectTask addTask(ProjectTask Task);

    /**
     * 分组产看任务
     *
     * @param colum
     * @param projectId
     * @return
     */
    Map<String, List<ProjectTask>> findTaskByGroup(int projectId, String colum);

    /**
     * 根据任务状态进行查询
     *
     * @param task
     * @return
     */
    List<ProjectTask> findListTask(ProjectTask task);

    /**
     * 分页查询
     *
     * @param start
     * @param limit
     * @param task
     * @param sortName
     * @return
     */
    Pager<ProjectTask> findTaskPager(Integer start, Integer limit, ProjectTask task, String sortName, boolean asc, String condition);

    /**
     * 根据taskId主键查询
     *
     * @param taskId
     * @return
     */
    ProjectTask findTask(Integer taskId);

    /**
     * 跟新任务，包括指派，开始，完成，关闭，编辑
     * 注意：1.状态不可逆转 2.有前置条件判断
     *
     * @param projectTask
     * @return
     */
    Integer updateTask(ProjectTask projectTask);

    /**
     * 跟新任务，包括指派，开始，完成，关闭，编辑
     * 注意：1.状态不可逆转 2.有前置条件判断
     *
     * @param projectTask
     * @return
     */
    Integer updateEditTask(ProjectTask projectTask);

    /**
     * 跟新任务，包括指派，开始，完成，关闭，编辑
     * 注意：1.状态不可逆转 2.有前置条件判断
     *
     * @param projectTask
     * @return
     */
    Integer updateCallTask(ProjectTask projectTask);

    /**
     * 跟新任务，包括指派，开始，完成，关闭，编辑
     * 注意：1.状态不可逆转 2.有前置条件判断
     *
     * @param projectTask
     * @return
     */
    Integer updateCloseTask(ProjectTask projectTask);

    /**
     * 跟新任务，包括指派，开始，完成，关闭，编辑
     * 注意：1.状态不可逆转 2.有前置条件判断
     *
     * @param projectTask
     * @return
     */
    Integer updateStartTask(ProjectTask projectTask);

    /**
     * 跟新任务，包括指派，开始，完成，关闭，编辑
     * 注意：1.状态不可逆转 2.有前置条件判断
     *
     * @param projectTask
     * @return
     */
    Integer updateFinishTask(ProjectTask projectTask);

    Integer updateCancelTask(ProjectTask task);

    /**
     * 复杂查询
     *
     * @return
     */
    Pager<ProjectTask> findComplexTask();

    /**
     * 分组查看
     *
     * @param type
     * @return
     */
    Map<String, List<ProjectTask>> findGroup(String type, Integer projectId);

    /**
     * 根据不同类型生成图表所需list
     *
     * @param id
     * @return
     */
    List<TaskChartBean> buildChart(String id);
}
