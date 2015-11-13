package org.tinygroup.sdpm.project.service.inter;

import org.tinygroup.sdpm.project.dao.pojo.ProjectTeam;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

/**
 * Created by shenly13343 on 2015-09-17.
 */
public interface TeamService {

    List<ProjectTeam> findTeamList(ProjectTeam team);

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
     * 移除项目下的成员,根据逻辑id
     */
    Integer delete(int id);

    /**
     * 查询项目下的所有成员
     *
     * @param projectId
     * @return
     */
    List<ProjectTeam> findTeamByProjectId(Integer projectId);
    List<ProjectTeam> findTeamByProductId(Integer productId);
    /**
     * 查询
     *
     * @param team
     * @param start
     * @param limit
     * @param order
     * @param ordertype
     * @return
     */
    public Pager<ProjectTeam> findPager(ProjectTeam team, Integer start, Integer limit, String order, String ordertype);

}
