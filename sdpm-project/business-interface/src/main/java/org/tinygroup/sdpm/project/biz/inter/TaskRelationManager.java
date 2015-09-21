package org.tinygroup.sdpm.project.biz.inter;


import org.tinygroup.sdpm.project.dao.pojo.ProjectTaskrelation;

import java.util.List;

/**
 * Created by shenly13343 on 2015-09-18.
 */
public interface TaskRelationManager {
    /**
     * 根据主键id查找用户
     *
     * @param id 主键
     * @return
     */
    ProjectTaskrelation find(int id);

    /**
     * 根据条件查询List
     *
     * @param taskrelation 用于查询条件
     * @return
     */
    List<ProjectTaskrelation> findList(ProjectTaskrelation taskrelation);

    /**
     * 新增有一个用户
     *
     * @param taskrelation 新增实体类
     * @return
     */
    ProjectTaskrelation add(ProjectTaskrelation taskrelation);

    /**
     * 更新用户
     *
     * @param taskrelation 需要更新的实体类
     * @return
     */
    ProjectTaskrelation update(ProjectTaskrelation taskrelation);

    /**
     * 根据id进行软删除用户
     *
     * @param id 主键
     * @return
     */
    Integer delete(String id);
}
