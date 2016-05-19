package org.tinygroup.sdpm.service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.service.biz.inter.ClientManager;
import org.tinygroup.sdpm.service.biz.inter.ClientUserManager;
import org.tinygroup.sdpm.service.biz.inter.SlaManager;
import org.tinygroup.sdpm.service.dao.pojo.ServiceClient;
import org.tinygroup.sdpm.service.dao.pojo.ServiceClientUser;
import org.tinygroup.sdpm.service.dao.pojo.ServiceSla;
import org.tinygroup.sdpm.service.service.inter.ClientService;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

/**
 * Created by Administrator on 2015-09-18.
 */
@Component
@Transactional
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientManager clientManager;
    @Autowired
    private ClientUserManager clientUserManager;
    @Autowired
    private SlaManager slaManager;
    @Transactional(readOnly = true)
    public ServiceClient findClient(Integer id) {
        return clientManager.find(id);
    }
    @Transactional(readOnly = true)
    public Pager<ServiceClient> findClientPager(Integer start, Integer limit, ServiceClient client, String order, String orderType) {
        return clientManager.findPager(start, limit, client, order, orderType);
    }
    @Transactional(readOnly = true)
    public Pager<ServiceClient> findClientPagerByPid(Integer start, Integer limit, Integer treeId, String order, String orderType) {
        return clientManager.findByProduct(start, limit, treeId, order, orderType);
    }
    @Transactional(readOnly = true)
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

    public int[] deleteBatchClient(List<ServiceClient> list) {
        return clientManager.deleteBatch(list);
    }
    @Transactional(readOnly = true)
    public List<ServiceSla> findSlaByClientId(Integer id) {
        return slaManager.getListByClientId(id);
    }

    public ServiceClientUser addServiceClientUser(ServiceClientUser clientUser) {
        return clientUserManager.add(clientUser);
    }
    @Transactional(readOnly = true)
    public List<ServiceClientUser> getAllClientUser(ServiceClientUser clientUser) {
        return clientUserManager.getUserList(clientUser);
    }

    public Integer deleteClientUser(Integer id) {
        return clientUserManager.delete(id);
    }
    @Transactional(readOnly = true)
    public ServiceClient judgeClient(String clientName) {
        return clientManager.judgeClient(clientName);
    }

}
