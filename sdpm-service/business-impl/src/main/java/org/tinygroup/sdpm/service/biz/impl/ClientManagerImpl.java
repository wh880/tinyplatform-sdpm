package org.tinygroup.sdpm.service.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.service.biz.inter.ClientManager;
import org.tinygroup.sdpm.service.dao.ServiceClientDao;
import org.tinygroup.sdpm.service.dao.pojo.ServiceClient;

import java.util.List;

/**
 * Created by Administrator on 2015-09-18.
 */
@Service
@Transactional
public class ClientManagerImpl implements ClientManager{
    @Autowired
    private ServiceClientDao clientDao;

    public ServiceClient find(Integer id) {
        return clientDao.getByKey(id);
    }

    public List<ServiceClient> getList(ServiceClient client) {
        return clientDao.query(client);
    }

    public ServiceClient add(ServiceClient client) {
        return clientDao.add(client);
    }

    public ServiceClient update(ServiceClient client) {
        return clientDao.add(client);
    }

    public Integer delete(Integer id) {
        ServiceClient client = new ServiceClient();
        client.setClientId(id);
        client.setDeleted(id);
        return clientDao.edit(client);
    }

    public Integer deleteBatch(Integer id) {
        return null;
    }

    public List<ServiceClient> findByProduct(Integer productId) {
        return null;
    }
}
