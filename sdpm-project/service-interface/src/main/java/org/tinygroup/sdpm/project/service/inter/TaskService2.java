package org.tinygroup.sdpm.project.service.inter;

import org.tinygroup.sdpm.project.dao.pojo.Task;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;
import java.util.Map;

/**
 * Created by shenly13343 on 2015-09-17.
 */
public interface TaskService2 {

    /**
     * 新建任务
     *
     * @param task
     */
    public Task save(Task task);

    /**
     * 查询任务列表
     */
    public Pager<Task> findTasks();

    /**
     * 通过分组查询，关键字column
     * 用于分组查看
     */
    public Map<String, List<Task>> findByGroup(String colum);

    /**
     * 通过状态查询，关键字status
     */
    public Pager<Task> findByStatus(String status);

    /**
     * 修改任务
     */
    public Task update(Task task);
}
