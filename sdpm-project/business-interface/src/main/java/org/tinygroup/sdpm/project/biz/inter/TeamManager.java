package org.tinygroup.sdpm.project.biz.inter;


import org.tinygroup.sdpm.project.dao.pojo.ProjectTeam;
import org.tinygroup.tinysqldsl.Pager;

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
    ProjectTeam find(int id);

    /**
     * 根据条件查询List
     *
     * @param projectId 用于查询条件
     * @return
     */
    List<ProjectTeam> findListAccount(int projectId);

    /**
     * 根据项目id查团队成员
     * @param projectId
     * @return
     */
    public List<ProjectTeam> findByProjectId(Integer projectId);

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
    Integer update(ProjectTeam team);

    /**
     * 根据id进行删除
     *
     * @param id 逻辑id 主键
     * @return
     */
    Integer delete(int id);

    /**
     * 查询
     *
     * @param team
     * @param start
     * @param limit
     * @param order
     * @param asc
     * @return
     */
    public Pager<ProjectTeam> findPager(ProjectTeam team, Integer start, Integer limit, String order, boolean asc);
}
