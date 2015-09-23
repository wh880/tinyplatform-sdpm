package org.tinygroup.sdpm.action.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.service.dao.pojo.ServiceSla;
import org.tinygroup.sdpm.service.service.inter.SlaService;
import java.util.List;
@Controller
@RequestMapping("/service/sla")
public class SlaAction extends BaseController{
    @Autowired
    private SlaService slaService;

    @RequestMapping("form")
    public String form(ServiceSla sla,Model model)
    {
        if (sla!=null) {
            model.addAttribute("sla", sla);
        }
        return "/service/sla/slaAdd.page";
    }










}