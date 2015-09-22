package org.tinygroup.sdpm.service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.service.biz.inter.ClientManager;
import org.tinygroup.sdpm.service.dao.pojo.Client;
import org.tinygroup.sdpm.service.service.inter.ClientService;

import java.util.List;

/**
 * Created by Administrator on 2015-09-18.
 */
@Component
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientManager clientManager;

    public Client findClient(Integer id) {
        return clientManager.find(id);
    }

    public List<Client> getClientList(Client client) {
        return clientManager.getList(client);
    }

    public Client addClient(Client client) {
        return clientManager.add(client);
    }

    public Client updateClient(Client client) {
        return clientManager.update(client);
    }

    public Integer deleteClient(Integer id) {
        return clientManager.delete(id);
    }

    public Integer deleteBatchClient(Integer id) {
        return clientManager.deleteBatch(id);
    }

    public List<Client> findClientByProduct(Integer productId) {
        return clientManager.findByProduct(productId);
    }
}
