package org.tinygroup.sdpm.project.service.inter;

import org.tinygroup.sdpm.project.dao.pojo.Task;
import org.tinygroup.tinysqldsl.Pager;

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
    public Pager<Task> findTasks();

    /**
     * 删除任务
     */
}
