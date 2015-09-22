package org.tinygroup.sdpm.action.org;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.org.dao.pojo.OrgDept;
import org.tinygroup.sdpm.org.service.inter.DeptService;

@Controller
@RequestMapping("/org/dept")
public class DeptAction extends BaseController {
    @Autowired
    private DeptService deptService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(OrgDept dept, Model model) {
        if (dept.getOrgDeptId() == null) {
            deptService.addDept(dept);
        } else {
            deptService.updateDept(dept);
        }
        model.addAttribute("dept", dept);
        return "organization/department/department.page";
    }

}
