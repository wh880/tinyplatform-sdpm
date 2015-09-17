package org.tinygroup.sdpm.project.service.inter;

import org.tinygroup.sdpm.project.dao.pojo.Task;

import java.util.List;

/**
 * Created by shenly13343 on 2015-09-17.
 */
public interface taskService {

    /**
     * 新建任务
     *
     * @param task
     */
    public int save(Task task);

    /**
     * 查询任务列表
     */
    public List<Task> findTasks();

    /**
     *
     */
}
