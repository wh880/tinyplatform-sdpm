package org.tinygroup.sdpm.project.biz.inter;

import org.tinygroup.sdpm.project.dao.pojo.ProjectTaskestimate;

import java.util.List;

/**
 * Created by shenly13343 on 2015-09-18.
 */
public interface TaskEsTimateManager {
    /**
     * 根据主键id查找用户
     *
     * @param id 主键
     * @return
     */
    ProjectTaskestimate find(String id);

    /**
     * 根据条件查询List
     *
     * @param taskestimate 用于查询条件
     * @return
     */
    List<ProjectTaskestimate> findList(ProjectTaskestimate taskestimate);

    /**
     * 新增有一个用户
     *
     * @param taskestimate 新增实体类
     * @return
     */
    ProjectTaskestimate add(ProjectTaskestimate taskestimate);

    /**
     * 更新用户
     *
     * @param taskestimate 需要更新的实体类
     * @return
     */
    ProjectTaskestimate update(ProjectTaskestimate taskestimate);

    /**
     * 根据id进行软删除用户
     *
     * @param id 主键
     * @return
     */
    Integer delete(String id);
}
