package org.tinygroup.sdpm.service.service.inter;

import org.tinygroup.sdpm.service.dao.pojo.Client;

import java.util.List;


public interface ClientService {
    /**
     * 根据主键id查找客户
     *
     * @param id 主键
     * @return
     */
    Client findClient(Integer id);

    /**
     * 根据条件查询List
     *
     * @param client 用于查询条件
     * @return
     */
    List<Client> getClientList(Client client);

    /**
     * 新增一个客户
     *
     * @param client 新增实体类
     * @return
     */
    Client addClient(Client client);

    /**
     * 更新客户
     *
     * @param client 需要更新的实体类
     * @return
     */
    Client updateClient(Client client);

    /**
     * 根据id进行软删除客户
     *
     * @param id 主键
     * @return
     */
    Integer deleteClient(Integer id);

    /**
     * 根据id进行批量软删除客户
     *
     * @param id 主键
     * @return
     */
    Integer deleteBatchClient(Integer id);


    /**
     * 根据id进行批量软删除客户
     *
     * @param productId 主键
     * @return
     */
    List<Client> findClientByProduct(Integer productId);
}