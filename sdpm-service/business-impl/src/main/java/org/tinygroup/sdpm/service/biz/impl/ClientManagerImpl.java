package org.tinygroup.sdpm.service.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.service.biz.inter.ClientManager;
import org.tinygroup.sdpm.service.dao.ServiceClientDao;
import org.tinygroup.sdpm.service.dao.pojo.ServiceClient;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

/**
 * Created by Administrator on 2015-09-18.
 */
@Service
@Transactional
public class ClientManagerImpl implements ClientManager {
    @Autowired
    private ServiceClientDao clientDao;

    public ServiceClient find(Integer id) {
        return clientDao.getByKey(id);
    }

    public Pager<ServiceClient> findPager(Integer start, Integer limit, ServiceClient serviceClient) {
        return clientDao.queryPager(start, limit, serviceClient);
    }

    public List<ServiceClient> getList(ServiceClient client) {
        return clientDao.query(client);
    }

    public ServiceClient add(ServiceClient client) {
        return clientDao.add(client);
    }

    public ServiceClient update(ServiceClient client) {
        clientDao.edit(client);
        return client;
    }

    public Integer delete(Integer id) {
        return clientDao.softDelete(id);
    }

    public Integer deleteBatch(Integer id) {
        return null;
    }

    public List<ServiceClient> findByProduct(Integer productId) {
        return null;
    }
}
