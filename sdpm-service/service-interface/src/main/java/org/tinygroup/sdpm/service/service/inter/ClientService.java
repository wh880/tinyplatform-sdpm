package org.tinygroup.sdpm.service.service.inter;

import org.tinygroup.sdpm.service.dao.pojo.ServiceClient;
import org.tinygroup.sdpm.service.dao.pojo.ServiceClientUser;
import org.tinygroup.sdpm.service.dao.pojo.ServiceSla;
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
    Pager<ServiceClient> findClientPager(Integer start, Integer limit, ServiceClient client, String order,
                                         String orderType);

    /**
     * 通过左树的产品ID查找数据库的所有记录
     *
     * @return
     */
    Pager<ServiceClient> findClientPagerByPid(Integer start, Integer limit, Integer treeId, String order,
                                              String orderType);

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
     * 根据list中id进行批量软删除客户
     *
     * @param
     * @return
     */
    int[] deleteBatchClient(List<ServiceClient> list);


    /**
     * 根据客户id查找客户签订的协议
     *
     * @param id 主键
     * @return
     */
    List<ServiceSla> findSlaByClientId(Integer id);

    /**
     * 查找一个客户的所有联系人
     *
     * @param clientUser 编辑实体类
     * @return
     */
    List<ServiceClientUser> getAllClientUser(ServiceClientUser clientUser);

    /**
     * 删除一个客户联系人
     *
     * @param id 客户联系人表的主键
     * @return
     */
    Integer deleteClientUser(Integer id);

    /**
     * 新建或编辑客户时验证客户名是否存在数据库中
     *
     * @param
     * @return
     */
    ServiceClient judgeClient(String clientName);

}