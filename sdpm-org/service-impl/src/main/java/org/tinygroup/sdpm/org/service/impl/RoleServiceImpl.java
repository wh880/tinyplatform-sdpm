
package org.tinygroup.sdpm.org.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.org.biz.inter.RoleManager;
import org.tinygroup.sdpm.org.dao.pojo.OrgRole;
import org.tinygroup.sdpm.org.service.inter.RoleService;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

@Component
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleManager roleManager;

    public OrgRole findRole(Integer id) {
        return roleManager.find(id);
    }

    public Pager<OrgRole> findRolePager(Integer start, Integer limit, OrgRole orgRole) {
        return roleManager.findPager(start, limit, orgRole);
    }

    public List<OrgRole> findRoleList(OrgRole orgRole) {
        return roleManager.findList(orgRole);
    }

    public OrgRole addRole(OrgRole orgRole) {
        return roleManager.add(orgRole);
    }

    public OrgRole updateRole(OrgRole orgRole) {
        return roleManager.update(orgRole);
    }

    public Integer deleteRole(Integer id) {
        return roleManager.delete(id);
    }
}