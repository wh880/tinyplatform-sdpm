package org.tinygroup.sdpm.project.biz.inter;


import org.tinygroup.sdpm.project.dao.pojo.ProjectTeam;

import java.util.List;

/**
 * Created by shenly13343 on 2015-09-18.
 */
public interface TeamManager {
    /**
     * 根据主键id查找
     *
     * @param id 主键
     * @return
     */
    ProjectTeam find(String id);

    /**
     * 根据项目及帐号进行查询
     * @param projectId
     * @param account
     * @return
     */
    ProjectTeam find(String projectId, String account);

    /**
     * 根据条件查询List
     *
     * @param projectId 用于查询条件
     * @return
     */
    List<ProjectTeam> findList(int projectId);

    /**
     * 新增有一个成员
     *
     * @param team 新增实体类
     * @return
     */
    ProjectTeam add(ProjectTeam team);

    /**
     * 更新用户
     *
     * @param team 需要更新的实体类
     * @return
     */
    ProjectTeam update(ProjectTeam team);

    /**
     * 根据id进行删除
     *
     * @param id 逻辑id 主键
     * @return
     */
    Integer delete(String id);
}
