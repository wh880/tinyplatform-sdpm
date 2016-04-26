package org.tinygroup.sdpm.service.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinygroup.sdpm.service.biz.inter.ClientUserManager;
import org.tinygroup.sdpm.service.dao.ServiceClientUserDao;
import org.tinygroup.sdpm.service.dao.pojo.ServiceClientUser;

import java.util.List;

/**
 * Created by Administrator on 2015-09-18.
 */
@Service
public class ClientUserManagerImpl implements ClientUserManager {
    @Autowired
    private ServiceClientUserDao clientUserDao;

    public ServiceClientUser find(Integer id) {
        return clientUserDao.getByKey(id);
    }

    public List<ServiceClientUser> getUserList(ServiceClientUser clientUser) {
        return clientUserDao.query(clientUser);
    }

    public ServiceClientUser add(ServiceClientUser clientUser) {
        return clientUserDao.add(clientUser);
    }

    public ServiceClientUser update(ServiceClientUser clientUser) {
        clientUserDao.edit(clientUser);
        return clientUser;
    }

    public Integer delete(Integer id) {
        return clientUserDao.softDelete(id);
    }

    public Integer deleteAll(Integer id) {
        return clientUserDao.softAllDelete(id);
    }

}
