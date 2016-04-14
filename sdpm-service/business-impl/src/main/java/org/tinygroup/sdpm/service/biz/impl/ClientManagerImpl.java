package org.tinygroup.sdpm.service.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.common.util.common.NameUtil;
import org.tinygroup.sdpm.service.biz.inter.ClientManager;
import org.tinygroup.sdpm.service.dao.ServiceClientDao;
import org.tinygroup.sdpm.service.dao.pojo.ServiceClient;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

/**
 * Created by Administrator on 2015-09-18.
 */
@Service
public class ClientManagerImpl implements ClientManager {
    @Autowired
    private ServiceClientDao clientDao;

    public ServiceClient find(Integer id) {
        return clientDao.getByKey(id);
    }

    public Pager<ServiceClient> findPager(Integer start, Integer limit, ServiceClient serviceClient, String order, String orderType) {
        return clientDao.queryPager(start, limit, serviceClient, (order == null || "".equals(order)) ? null : new OrderBy(NameUtil.resolveNameDesc(order), !("desc".equals(orderType)) ? true : false));
    }

    public Pager<ServiceClient> findByProduct(Integer start, Integer limit, Integer treeId, String order, String orderType) {
        return clientDao.findByProduct(start, limit, treeId, (order == null || "".equals(order)) ? null : new OrderBy(NameUtil.resolveNameDesc(order), !("desc".equals(orderType)) ? true : false));
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

    public int[] deleteBatch(List<ServiceClient> list) {
        return clientDao.softDeleteBatch(list);
    }


    public ServiceClient judgeClient(String clientName) {
        return clientDao.judge(clientName);
    }
}
