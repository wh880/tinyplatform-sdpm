package org.tinygroup.sdpm.service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.service.biz.inter.ClientManager;
import org.tinygroup.sdpm.service.biz.inter.ClientUserManager;
import org.tinygroup.sdpm.service.dao.pojo.ClientUser;
import org.tinygroup.sdpm.service.service.inter.ClientUserService;

import java.util.List;

/**
 * Created by Administrator on 2015-09-18.
 */
@Component
public class ClientUserServiceImpl implements ClientUserService{
    @Autowired
    private ClientUserManager clientUserManager;
    public ClientUser findClientUser(Integer id) {
        return clientUserManager.find(id);
    }

    public List<ClientUser> getClientUserList(ClientUser clientUser) {
        return clientUserManager.getUserList(clientUser);
    }

    public ClientUser addClientUser(ClientUser clientUser) {
        return clientUserManager.add(clientUser);
    }

    public ClientUser updateClientUser(ClientUser clientUser) {
        return clientUserManager.update(clientUser);
    }

    public ClientUser deleteClientUser(Integer id) {
        return clientUserManager.delete(id);
    }
}
