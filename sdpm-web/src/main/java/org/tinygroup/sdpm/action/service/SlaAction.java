package org.tinygroup.sdpm.action.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.service.dao.pojo.ServiceSla;
import org.tinygroup.sdpm.service.service.inter.SlaService;
import org.tinygroup.tinysqldsl.Pager;

import java.util.Map;

@Controller
@RequestMapping("/service/sla")
public class SlaAction extends BaseController {
    @Autowired
    private SlaService slaService;

    @RequestMapping("form")
    public String form(ServiceSla sla, Model model) {
        if (sla != null) {
            model.addAttribute("sla", sla);
        }
        return "/service/sla/slaAdd.page";
    }

    @RequestMapping("/save")
    public String save(ServiceSla sla, Model model) {
        if (sla.getSlaId() == null) {
            slaService.addSla(sla);
        } else {
            slaService.updateSla(sla);
        }
        model.addAttribute("sla", sla);
        return "/service/sla/sla.page";
    }

    @RequestMapping("/delete")
    public Map delete(Integer id) {
        /*slaService.deleteSla(id);
        Map<String, String> map = new HashMap<String, String>();
        map.put("status","y");
        map.put("info","删除成功");
        return map;*/
        return null;
    }

    @RequestMapping(value = "/list")
    public String list(ServiceSla sla, Model model) {
        return "/service/sla/sla.page";
    }

    @RequestMapping(value = "/list/data")
    public String listData(Integer limit, Integer start, ServiceSla sla, Model model) {
        Pager<ServiceSla> pager = slaService.findSlaPager(start, limit, sla);
        model.addAttribute("pager", pager);
        return "service/sla/slaTableData.pagelet";
    }


}