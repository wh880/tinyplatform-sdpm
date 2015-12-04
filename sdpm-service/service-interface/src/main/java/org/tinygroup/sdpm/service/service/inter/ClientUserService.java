package org.tinygroup.sdpm.service.service.inter;

import org.tinygroup.sdpm.service.dao.pojo.ServiceClientUser;

/**
 * Created by Administrator on 2015-09-17.
 */
public interface ClientUserService {
    /**
     * 根据主键id查找客户联系人
     *
     * @param id 主键
     * @return
     */
    ServiceClientUser findClientUser(Integer id);

    /**
     * 新增一个客户联系人
     *
     * @param clientUser 新增实体类
     * @return
     */
    ServiceClientUser addServiceClientUser(ServiceClientUser clientUser);

    /**
     * 更新客户联系人
     *
     * @param clientUser 需要更新的实体类
     * @return
     */
    ServiceClientUser updateClientUser(ServiceClientUser clientUser);

    /**
     * 根据客户id进行全部客户联系人的删除
     *
     * @param id 客户id
     * @return
     */
    Integer deleteAllClientUser(Integer id);
}