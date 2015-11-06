package org.tinygroup.sdpm.org.biz.inter;

import org.tinygroup.sdpm.org.dao.pojo.OrgRole;
import org.tinygroup.sdpm.org.dao.pojo.OrgRoleUser;

import java.util.List;

/**
 * Created by Administrator on 2015-10-12.
 */
public interface RoleUserManager {
    /**
     * 根据主键id查找角色用户
     *
     * @param id 主键
     * @return
     */
    OrgRoleUser find(Integer id);

    /**
     * 根据用户id，查找先关角色
     * @param userId
     * @return
     */
    public List<OrgRoleUser> findListByUserIds(String userId);

    /**
     * 根据角色Id查询用户list
     *
     * @param id
     * @return
     */
    List<OrgRoleUser> findUserIds(Integer id);

    /**
     * 新增有一个角色用户
     *
     * @param orgRoleUser 新增实体类
     * @return
     */
    OrgRoleUser add(OrgRoleUser orgRoleUser);

    void addRoleUser(String[] array, Integer roleId);

    /**
     * 更新角色菜单
     *
     * @param orgRoleUser 需要更新的实体类
     * @return
     */
    OrgRoleUser update(OrgRoleUser orgRoleUser);

    /**
     * 根据id进行软删除角色菜单
     *
     * @param id 主键
     * @return
     */
    Integer delete(Integer id);


    void batchAdd(List<OrgRoleUser> orgRoleUserList);

    List<OrgRoleUser> getRolesByIds(String...ids);
}
