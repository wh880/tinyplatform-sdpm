package org.tinygroup.sdpm.project.service.inter;

import org.tinygroup.sdpm.project.dao.pojo.ProjectTaskrelation;

import java.util.List;

/**
 * Created by shenly13343 on 2015-09-18.
 */
public interface TaskRelationService {
    /**
     * 增加任务关系
     *
     * @param taskRelationService
     * @return
     */
    public ProjectTaskrelation add(TaskRelationService taskRelationService);

    /**
     * 根据项目id查找相关任务关系
     *
     * @param projectId
     * @return
     */
    public List<TaskRelationService> findByProjectId(int projectId);

    /**
     * 根据逻辑id删除关系
     *
     * @param id
     * @return
     */
    public int deleteById(int id);
}
