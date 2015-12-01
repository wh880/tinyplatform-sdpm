package org.tinygroup.sdpm.project.biz.inter;


import org.tinygroup.sdpm.project.dao.pojo.ProjectTeam;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

/**
 * Created by shenly13343 on 2015-09-18.
 */
public interface TeamManager {

    List<ProjectTeam> find(ProjectTeam team);

    /**
     * 批量添加
     *
     * @param list
     * @return
     */
    Integer batchAdd(List<ProjectTeam> list);

    /**
     * 批量更新
     *
     * @param list
     * @return
     */
    Integer batchUpdate(List<ProjectTeam> list);

    /**
     * 根据主键id查找
     *
     * @param id 主键
     * @return
     */
    ProjectTeam find(int id);

    /**
     * 根据项目id查团队成员
     *
     * @param projectId
     * @return
     */
    List<ProjectTeam> findByProjectId(Integer projectId);

    /**
     * 根据产品id查团队成员
     *
     * @param productId
     * @return
     */
    List<ProjectTeam> findByProductId(Integer productId);

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
    Pager<ProjectTeam> findPager(ProjectTeam team, Integer start, Integer limit, String order, boolean asc);

    /**
     * 根据用户和项目Id获取团队成员拥有的菜单
     *
     * @param projectId
     * @param userId
     * @return
     */
    List<String> getMenuIdListByProjectAndUser(Integer projectId, String userId);

    /**
     * 根据用户和产品Id获取团队成员拥有的菜单
     *
     * @param productId
     * @param userId
     * @return
     */
    List<String> getMenuIdListByProductAndUser(Integer productId, String userId);
}
