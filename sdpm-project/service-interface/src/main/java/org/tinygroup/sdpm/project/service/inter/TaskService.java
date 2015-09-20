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
     * @param projectTask
     * @return
     */
    public ProjectTask save(ProjectTask projectTask);

    /**
     * 查询所有任务
     *
     * @return
     */
    public Pager<ProjectTask> findList();

    /**
     * 分组产看任务
     *
     * @param colum
     * @return
     */
    public Map<String, List<ProjectTask>> findByGroup(String colum);

    /**
     * 根据任务状态进行查询
     *
     * @param status
     * @return
     */
    public Pager<ProjectTask> findByStatus(String status);

    /**
     * 跟新任务，包括指派，开始，完成，关闭，编辑
     * 注意：1.状态不可逆转 2.有前置条件判断
     *
     * @param projectTask
     * @return
     */
    public ProjectTask update(ProjectTask projectTask);

    /**
     * 复杂查询，延期
     *
     * @return
     */
    public Pager<ProjectTask> findComplex();

    public void ouputFile();

    public void input();
}
