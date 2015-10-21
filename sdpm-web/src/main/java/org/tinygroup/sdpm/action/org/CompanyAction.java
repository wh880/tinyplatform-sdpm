package org.tinygroup.sdpm.action.org;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.org.dao.pojo.OrgCompany;
import org.tinygroup.sdpm.org.service.inter.CompanyService;

@Controller
@RequestMapping("/a/org/company")
public class CompanyAction extends BaseController {
    @Autowired
    private CompanyService companyService;


    @RequiresPermissions("org-company-edit")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(OrgCompany company, Model model) {
        companyService.updateCompany(company);
        model.addAttribute("company", company);
        return "redirect:" + adminPath + "/org/company/show/";
    }

    @RequiresPermissions("organizationCompany")
    @RequestMapping("/show")
    public String show(Model model) {
        OrgCompany company = companyService.findCompany(1);
        model.addAttribute("company", company);
        return "organization/company/company.page";
    }

    @RequiresPermissions("org-company-edit")
    @RequestMapping("/edit")
    public String edit(Model model) {
        OrgCompany company = companyService.findCompany(1);
        model.addAttribute("company", company);
        return "organization/company/updateCompany.pagelet";
    }

}
