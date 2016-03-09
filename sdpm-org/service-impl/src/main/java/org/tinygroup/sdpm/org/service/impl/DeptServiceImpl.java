package org.tinygroup.sdpm.org.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.org.biz.inter.DeptManager;
import org.tinygroup.sdpm.org.dao.pojo.OrgDept;
import org.tinygroup.sdpm.org.service.inter.DeptService;

import java.util.List;

@Component
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptManager deptManager;

    public OrgDept findDept(Integer id) {
        return deptManager.find(id);
    }

    public List<OrgDept> findDeptList(OrgDept orgDept) {
        return deptManager.findList(orgDept);
    }

    public OrgDept addDept(OrgDept orgDept) {
        return deptManager.add(orgDept);
    }

    public OrgDept updateDept(OrgDept orgDept) {
        return deptManager.update(orgDept);
    }

    public Integer deleteDept(Integer id) {
        return deptManager.delete(id);
    }
}