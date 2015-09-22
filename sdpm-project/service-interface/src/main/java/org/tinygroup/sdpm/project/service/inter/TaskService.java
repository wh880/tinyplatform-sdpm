package org.tinygroup.sdpm.project.service.inter;

import org.tinygroup.sdpm.project.dao.pojo.ProjectTask;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;
import java.util.Map;

/**
 * Created by shenly13343 on 2015-09-20.
 */
public interface TaskService {

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
     * 跟新任务，包括指派，开始，完成，关闭，编辑
     * 注意：1.状态不可逆转 2.有前置条件判断
     *
     * @param projectTask
     * @return
     */
    public Integer updateTask(ProjectTask projectTask);

    /**
     * 复杂查询
     *
     * @return
     */
    public Pager<ProjectTask> findComplexTask();
}
