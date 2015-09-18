package org.tinygroup.sdpm.service.service.inter;

import org.tinygroup.sdpm.service.dao.pojo.ClientUser;

import java.util.List;

/**
 * Created by Administrator on 2015-09-17.
 */
public interface ClientUserService {
    /**
     * 根据主键id查找用户联系人
     *
     * @param id 主键
     * @return
     */
    ClientUser find(String id);

    /**
     * 根据条件查询List
     *
     * @param clientUser 用于查询条件
     * @return
     */
    List<ClientUser> getList(ClientUser clientUser);

    /**
     * 新增有一个用户联系人
     *
     * @param clientUser 新增实体类
     * @return
     */
    ClientUser add(ClientUser clientUser);

    /**
     * 更新用户联系人
     *
     * @param clientUser 需要更新的实体类
     * @return
     */
    ClientUser update(ClientUser clientUser);
}