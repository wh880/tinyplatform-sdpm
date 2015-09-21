package org.tinygroup.sdpm.service.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.service.biz.inter.ClientManager;
import org.tinygroup.sdpm.service.dao.ClientDao;
import org.tinygroup.sdpm.service.dao.pojo.Client;

import java.util.List;

/**
 * Created by Administrator on 2015-09-18.
 */
@Service
@Transactional
public class ClientManagerImpl implements ClientManager{
    @Autowired
    private ClientDao clientDao;
    public Client find(Integer id) { return clientDao.getByKey(id);
    }

    public List<Client> getList(Client client) {
        return clientDao.query(client);
    }

    public Client add(Client client) {
        return null;
    }

    public Client update(Client client) {
        return null;
    }

    public Integer delete(String id) {
        return null;
    }

    public Integer deleteBatch(Integer id) {
        return null;
    }

    public List<Client> findByProduct(Integer productId) {
        return null;
    }
}
