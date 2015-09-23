package org.tinygroup.sdpm.service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.service.biz.inter.ClientManager;
import org.tinygroup.sdpm.service.dao.pojo.ServiceClient;
import org.tinygroup.sdpm.service.service.inter.ClientService;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

/**
 * Created by Administrator on 2015-09-18.
 */
@Component
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientManager clientManager;

    public ServiceClient findClient(Integer id) {
        return clientManager.find(id);
    }

    public Pager<ServiceClient> findClientPager(Integer start, Integer limit, ServiceClient serviceClient) {
        return clientManager.findPager(start, limit, serviceClient);
    }

    public List<ServiceClient> getClientList(ServiceClient client) {
        return clientManager.getList(client);
    }

    public ServiceClient addClient(ServiceClient client) {
        return clientManager.add(client);
    }

    public ServiceClient updateClient(ServiceClient client) {
        return clientManager.update(client);
    }

    public Integer deleteClient(Integer id) {
        return clientManager.delete(id);
    }

    public Integer deleteBatchClient(Integer id) {
        return clientManager.deleteBatch(id);
    }

    public List<ServiceClient> findClientByProduct(Integer productId) {
        return clientManager.findByProduct(productId);
    }
}
