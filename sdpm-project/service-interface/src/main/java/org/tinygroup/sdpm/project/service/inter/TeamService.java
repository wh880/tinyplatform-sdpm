package org.tinygroup.sdpm.project.service.inter;

import org.tinygroup.aopcache.annotation.CacheGet;
import org.tinygroup.aopcache.annotation.CacheRemove;
import org.tinygroup.sdpm.project.dao.pojo.ProjectTeam;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

/**
 * Created by shenly13343 on 2015-09-17.
 */
public interface TeamService {

    String CACHE_PRODUCT_TEAM_MENU_LIST = "productTamMenuList";
    String CACHE_PROJECT_TEAM_MENU_LIST = "projectTeamMenuList";
    String CACHE_PROJECT_TEAM_USER_LIST = "projectTeamUserList";
    String CACHE_PRODUCT_TEAM_USER_LIST = "productTeamUserList";

    /**
     * 统计团队总共可用工时信息
     *
     * @param projectId
     * @return
     */
    Integer getProjectTeamTimeInfo(Integer projectId);

    List<ProjectTeam> findTeamList(ProjectTeam team);

    /**
     * 批量添加
     *
     * @param list
     * @return
     */
    @CacheRemove(removeGroups = {CACHE_PRODUCT_TEAM_MENU_LIST, CACHE_PROJECT_TEAM_MENU_LIST, CACHE_PROJECT_TEAM_USER_LIST, CACHE_PRODUCT_TEAM_USER_LIST})
    Integer batchAddTeam(List<ProjectTeam> list);

    /**
     * 批量更新
     *
     * @param list
     * @return
     */
    @CacheRemove(removeGroups = {CACHE_PRODUCT_TEAM_MENU_LIST, CACHE_PROJECT_TEAM_MENU_LIST, CACHE_PROJECT_TEAM_USER_LIST, CACHE_PRODUCT_TEAM_USER_LIST})
    Integer batchUpdateTeam(List<ProjectTeam> list);

    /**
     * 移除项目下的成员,根据逻辑id
     *
     * @param id
     * @return
     */
    @CacheRemove(removeGroups = {CACHE_PRODUCT_TEAM_MENU_LIST, CACHE_PROJECT_TEAM_MENU_LIST, CACHE_PROJECT_TEAM_USER_LIST, CACHE_PRODUCT_TEAM_USER_LIST})
    Integer deleteTeam(int id);

    /**
     * 查询项目下的所有成员
     *
     * @param projectId
     * @return
     */
    @CacheGet(group = CACHE_PROJECT_TEAM_USER_LIST, key = "${projectId}")
    List<ProjectTeam> findTeamByProjectId(Integer projectId);

    @CacheGet(group = CACHE_PRODUCT_TEAM_USER_LIST, key = "${productId}")
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
    Pager<ProjectTeam> findProjectTeamPager(ProjectTeam team, Integer start, Integer limit, String order, String ordertype);

    /**
     * 根据用户和项目Id获取团队成员拥有的菜单
     *
     * @param projectId
     * @param userId
     * @return
     */
    @CacheGet(group = CACHE_PROJECT_TEAM_MENU_LIST, key = "${projectId}-${userId}")
    List<String> getMenuIdListByProjectAndUser(Integer projectId, String userId);

    /**
     * 根据用户和产品Id获取团队成员拥有的菜单
     *
     * @param productId
     * @param userId
     * @return
     */
    @CacheGet(group = CACHE_PRODUCT_TEAM_MENU_LIST, key = "${productId}-${userId}")
    List<String> getMenuIdListByProductAndUser(Integer productId, String userId);

}
