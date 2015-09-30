package org.tinygroup.sdpm.service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.service.biz.inter.ClientUserManager;
import org.tinygroup.sdpm.service.dao.pojo.ServiceClientUser;
import org.tinygroup.sdpm.service.service.inter.ClientUserService;

import java.util.List;

/**
 * Created by Administrator on 2015-09-18.
 */
@Component
public class ClientUserServiceImpl implements ClientUserService{
    @Autowired
    private ClientUserManager clientUserManager;

    public ServiceClientUser findClientUser(Integer id) {
        return clientUserManager.find(id);
    }

    public List<ServiceClientUser> getClientUserList(ServiceClientUser clientUser) {
        return clientUserManager.getUserList(clientUser);
    }

    public ServiceClientUser addClientUser(ServiceClientUser clientUser) {
        return clientUserManager.add(clientUser);
    }

    public ServiceClientUser updateClientUser(ServiceClientUser clientUser) {
        return clientUserManager.update(clientUser);
    }

    public Integer deleteClientUser(Integer id) {
        return clientUserManager.delete(id);
    }

    public Integer deleteAllClientUser(Integer id) {
        return clientUserManager.deleteAll(id);
    }
}
