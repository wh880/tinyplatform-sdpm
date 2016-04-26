package org.tinygroup.sdpm.org.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.org.biz.inter.CompanyManager;
import org.tinygroup.sdpm.org.dao.pojo.OrgCompany;
import org.tinygroup.sdpm.org.service.inter.CompanyService;

import java.util.List;

@Component
@Transactional
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyManager companyManager;
    @Transactional(readOnly = true)
    public OrgCompany findCompany(Integer id) {
        return companyManager.find(id);
    }
    @Transactional(readOnly = true)
    public List<OrgCompany> findCompanyList() {
        return companyManager.findList();
    }

    public OrgCompany addCompany(OrgCompany orgCompany) {
        return companyManager.add(orgCompany);
    }

    public OrgCompany updateCompany(OrgCompany orgCompany) {
        return companyManager.update(orgCompany);
    }

}