package org.tinygroup.sdpm.service.biz.inter;

import org.tinygroup.sdpm.service.dao.pojo.ServiceClientUser;

import java.util.List;

/**
 * Created by Administrator on 2015-09-18.
 */
public interface ClientUserManager {
    /**
     * 根据主键id查找客户联系人
     *
     * @param id 主键
     * @return
     */
    ServiceClientUser find(Integer id);

    /**
     * 根据条件查询List
     *
     * @param clientUser 用于查询条件
     * @return
     */
    List<ServiceClientUser> getUserList(ServiceClientUser clientUser);

    /**
     * 新增一个客户联系人
     *
     * @param clientUser 新增实体类
     * @return
     */
    ServiceClientUser add(ServiceClientUser clientUser);

    /**
     * 更新客户联系人
     *
     * @param clientUser 需要更新的实体类
     * @return
     */
    ServiceClientUser update(ServiceClientUser clientUser);

    /**
     * 根据id进行批量软删除客户
     *
     * @param id 主键
     * @return
     */
    ServiceClientUser delete(Integer id);
}