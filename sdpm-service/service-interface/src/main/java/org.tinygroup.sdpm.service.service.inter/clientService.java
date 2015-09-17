package org.tinygroup.sdpm.service.service.inter;

import org.tinygroup.sdpm.service.dao.pojo.Client;

import java.util.List;


public interface clientService {
    List<Client> findCli(Client client);
    /* 查找客户的记录到表单*/

    Client add(Client client);
    /* 新增客户*/

    Client edit(Client client);
    /* 编辑客户 */

    Client delete(Client client);
    /* 删除客户 */

    Client find(Client client);
    /*显示客户详细信息*/

    List<Client> findbyProduct(Integer productId);
    /*通过查找客户的记录到表单*/

    List<Client> deleteBatch(Integer productId);
    /*批量删除*/

}