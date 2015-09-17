package org.tinygroup.sdpm.service.service.inter;

import org.tinygroup.sdpm.service.dao.pojo.ClientUser;

import java.util.List;

/**
 * Created by Administrator on 2015-09-17.
 */
public interface clientUserService {
    List<ClientUser> find(ClientUser clientUser);

    /*显示客户联系人的列表*/
    ClientUser edit(ClientUser clientUser);

    /*编辑联系人*/
    ClientUser add(ClientUser clientUser);

    /*添加联系人*/
    ClientUser delete(ClientUser clientUser);
    /*删除联系人*/


}
