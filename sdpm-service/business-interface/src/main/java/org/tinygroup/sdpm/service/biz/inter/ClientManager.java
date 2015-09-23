package org.tinygroup.sdpm.service.biz.inter;

import org.tinygroup.sdpm.service.dao.pojo.ServiceClient;

import java.util.List;

/**
 * Created by Administrator on 2015-09-18.
 */
public interface ClientManager {
    /**
     * 根据主键id查找客户
     *
     * @param id 主键
     * @return
     */
    ServiceClient find(Integer id);

    /**
     * 根据条件查询List
     *
     * @param client 用于查询条件
     * @return
     */
    List<ServiceClient> getList(ServiceClient client);

    /**
     * 新增一个客户
     *
     * @param client 新增实体类
     * @return
     */
    ServiceClient add(ServiceClient client);

    /**
     * 更新客户
     *
     * @param client 需要更新的实体类
     * @return
     */
    ServiceClient update(ServiceClient client);

    /**
     * 根据id进行软删除客户
     *
     * @param id 主键
     * @return
     */
    Integer delete(Integer id);

    /**
     * 根据id进行批量软删除客户
     *
     * @param id 主键
     * @return
     */
    Integer deleteBatch(Integer id);


    /**
     * 根据id进行批量软删除客户
     *
     * @param productId 主键
     * @return
     */
    List<ServiceClient> findByProduct(Integer productId);
}
