package org.tinygroup.sdpm.project.biz.inter;

import org.tinygroup.sdpm.project.dao.pojo.Team;

import java.util.List;

/**
 * Created by shenly13343 on 2015-09-18.
 */
public interface TeamManager {
    /**
     * 根据主键id查找用户
     *
     * @param id 主键
     * @return
     */
    Team find(String id);

    /**
     * 根据条件查询List
     *
     * @param team 用于查询条件
     * @return
     */
    List<Team> findList(Team team);

    /**
     * 新增有一个用户
     *
     * @param team 新增实体类
     * @return
     */
    Team add(Team team);

    /**
     * 更新用户
     *
     * @param team 需要更新的实体类
     * @return
     */
    Team update(Team team);

    /**
     * 根据id进行软删除用户
     *
     * @param id 主键
     * @return
     */
    Integer delete(String id);
}
