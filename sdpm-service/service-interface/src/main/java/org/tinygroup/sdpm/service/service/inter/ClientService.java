package org.tinygroup.sdpm.service.service.inter;

import org.tinygroup.sdpm.service.dao.pojo.ServiceClient;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;


public interface ClientService {
    /**
     * 根据主键id查找客户
     *
     * @param id 主键
     * @return
     */
    ServiceClient findClient(Integer id);

    /**
     * 查找数据库的所有记录
     *
     * @return
     */
    Pager<ServiceClient> findClientPager(Integer start, Integer limit, ServiceClient client);

    /**
     * 根据条件查询List
     *
     * @param client 用于查询条件
     * @return
     */
    List<ServiceClient> getClientList(ServiceClient client);

    /**
     * 新增一个客户
     *
     * @param client 新增实体类
     * @return
     */
    ServiceClient addClient(ServiceClient client);

    /**
     * 更新客户
     *
     * @param client 需要更新的实体类
     * @return
     */
    ServiceClient updateClient(ServiceClient client);

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
    List<ServiceClient> findClientByProduct(Integer productId);
}