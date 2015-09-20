package org.tinygroup.sdpm.project.biz.inter;


import org.tinygroup.sdpm.project.dao.pojo.ProjectBurn;

import java.util.List;

/**
 * Created by shenly13343 on 2015-09-18.
 */
public interface BurnManager {
    /**
     * 根据主键id查找用户
     *
     * @param id 主键
     * @return
     */
    ProjectBurn find(String id);

    /**
     * 根据条件查询List
     *
     * @param projectId 用于查询条件
     * @return
     */
    List<ProjectBurn> findList(int projectId);

    /**
     * 新增有一个数据
     *
     * @param burn 新增实体类
     * @return
     */
    ProjectBurn add(ProjectBurn burn);

    /**
     * 更新用户
     *
     * @param burn 需要更新的实体类
     * @return
     */
    ProjectBurn update(ProjectBurn burn);

    /**
     * 根据id进行删除
     *
     * @param id 主键
     * @return
     */
    Integer delete(String id);
}
