package org.tinygroup.sdpm.project.biz.inter;


import org.tinygroup.sdpm.project.dao.pojo.ProjectBuild;

import java.util.List;

/**
 * Created by shenly13343 on 2015-09-18.
 */
public interface BuildManager {
    /**
     * 根据主键id查找用户
     *
     * @param id 主键
     * @return
     */
    ProcessBuilder find(String id);

    /**
     * 根据条件查询List
     *
     * @param projectId 用于查询条件
     * @return
     */
    List<ProcessBuilder> findList(int projectId);

    /**
     * 新增有一个版本
     *
     * @param build 新增实体类
     * @return
     */
    ProjectBuild add(ProjectBuild build);

    /**
     * 更新用户
     *
     * @param build 需要更新的实体类
     * @return
     */
    ProjectBuild update(ProjectBuild build);

    /**
     * 根据id进行软删除
     *
     * @param id 主键
     * @return
     */
    Integer delete(String id);
}
