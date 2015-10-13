package org.tinygroup.sdpm.org.service.inter;

import org.tinygroup.sdpm.org.dao.pojo.OrgRoleUser;

import java.util.List;

/**
 * Created by Administrator on 2015-10-12.
 */
public interface RoleUserService {
    /**
     * 根据Id查找角色用户
     *
     * @param id
     * @return
     */
    OrgRoleUser findRoleUser(Integer id);

    /**
     * 根据角色id查找用户列表
     *
     * @param roleId
     * @return
     */
    List<OrgRoleUser> findUserByRoleId(Integer roleId);

    /**
     * 添加角色用户
     *
     * @param
     * @return
     */
    void addRoleUser(String[] array, Integer roleId);

    /**
     * 批量添加用色用户
     *
     * @param orgRoleUserList
     */
    void batchAddRoleUser(List<OrgRoleUser> orgRoleUserList);

    /**
     * 更新角色用户
     *
     * @param orgRoleUser
     * @return
     */
    OrgRoleUser updateRoleUser(OrgRoleUser orgRoleUser);


    /***
     * 根据id删除用户角色
     *
     * @param id
     * @return
     */
    Integer deleteRoleUser(Integer id);

    /**
     * 角色复制下面的用户复制
     *
     * @param orgRoleIdNew
     * @param orgRoleId
     */
    void copyRoleUser(Integer orgRoleIdNew, Integer orgRoleId);
}
