package org.tinygroup.sdpm.project.service.inter;

import org.tinygroup.sdpm.project.dao.pojo.Task;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;
import java.util.Map;

/**
 * Created by shenly13343 on 2015-09-17.
 */
public interface TaskService {

    /**
     * 新建任务
     * @param task
     * @return
     */
    public Task save(Task task);

    /**
     * 查询所有任务
     * @return
     */
    public Pager<Task> findList();

    /**
     * 分组产看任务
     * @param colum
     * @return
     */
    public Map<String, List<Task>> findByGroup(String colum);

    /**
     * 根据任务状态进行查询
     * @param status
     * @return
     */
    public Pager<Task> findByStatus(String status);

    /**
     * 跟新任务，包括指派，开始，完成，关闭，编辑
     * 注意：1.状态不可逆转 2.有前置条件判断
     * @param task
     * @return
     */
    public Task update(Task task);

    /**
     * 复杂查询，延期
     *
     * @return
     */
    public Pager<Task> findComplex();

    public void ouputFile();

    public void input();
}
