package org.tinygroup.sdpm.service.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.service.biz.inter.ClientUserManager;
import org.tinygroup.sdpm.service.dao.ClientUserDao;
import org.tinygroup.sdpm.service.dao.pojo.ClientUser;

import java.util.List;

/**
 * Created by Administrator on 2015-09-18.
 */
@Service
@Transactional
public class ClientUserManagerImpl implements ClientUserManager {
    @Autowired
    private ClientUserDao clientUserDao;

    public ClientUser find(Integer id) {return clientUserDao.getByKey(id);}

    public List<ClientUser> getUserList(ClientUser clientUser) {
        return clientUserDao.query(clientUser);
    }

    public ClientUser add(ClientUser clientUser) {
        return clientUserDao.add(clientUser);
    }

    public ClientUser update(ClientUser clientUser) {
        clientUserDao.edit(clientUser);
        return clientUser;
    }

    public ClientUser delete(Integer id) {
        clientUserDao.deleteByKey(id);
        return null;
    }
}
