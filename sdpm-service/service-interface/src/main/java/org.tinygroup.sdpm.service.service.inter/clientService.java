package org.tinygroup.sdpm.service.service.inter;

import org.tinygroup.sdpm.service.dao.pojo.Client;

import java.util.List;


public interface ClientService {
    /**
     * 根据主键id查找用户
     *
     * @param id 主键
     * @return
     */
    Client find(Client id);

    /**
     * 根据条件查询List
     *
     * @param client 用于查询条件
     * @return
     */
    List<Client> getList(Client client);

    /**
     * 新增有一个用户
     *
     * @param client 新增实体类
     * @return
     */
    Client add(Client client);

    /**
     * 更新用户
     *
     * @param client 需要更新的实体类
     * @return
     */
    Client update(Client client);

    /**
     * 根据id进行软删除用户
     *
     * @param id 主键
     * @return
     */
    Integer delete(String id);

    /**
     * 根据id进行批量软删除用户
     *
     * @param id 主键
     * @return
     */
    Integer deleteBatch(Integer id);


    /**
     * 根据id进行批量软删除用户
     *
     * @param productId 主键
     * @return
     */
    List<Client> findbyProduct(Integer productId);
}