package org.tinygroup.sdpm.action.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.service.dao.pojo.ServiceClient;
import org.tinygroup.sdpm.service.dao.pojo.ServiceSla;
import org.tinygroup.sdpm.service.service.inter.ClientService;
import org.tinygroup.sdpm.service.service.inter.SlaService;
import org.tinygroup.tinysqldsl.Pager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/service/sla")
public class SlaAction extends BaseController {
    @Autowired
    private SlaService slaService;
    @Autowired
    private ClientService clientService;

    /*查询*/
    @RequestMapping("/form")
    public String form(Integer id, Model model) {
        if (id != null) {
            ServiceSla sla = slaService.findSla(id);
            model.addAttribute("sla", sla);
        }
        ServiceClient client = new ServiceClient();
        List<ServiceClient> list = clientService.getClientList(client);
        model.addAttribute("list", list);
        return "/service/sla/slaAdd.page";
    }

    /*新增和修改*/
    @RequestMapping("/save")
    public String save(ServiceSla sla, Model model) {
        if (sla.getSlaId() == null) {
            sla = slaService.addSla(sla);
        } else {
            slaService.updateSla(sla);
        }
        model.addAttribute("sla", sla);
        return "/service/sla/sla.page";
    }

    /*"和“/form”拼凑成查询语句，/list"和"/list/data"是实现sla开始 页面表中数据的显示*/
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

    @ResponseBody
    @RequestMapping("/delete")
    public Map delete(Integer id) {
        slaService.deleteSla(id);
        Map<String, String> map = new HashMap<String, String>();
        map.put("status", "success");
        map.put("info", "删除成功");
        return map;
    }

    /* 协议里面，点击“详情”进入*/
    @RequestMapping(value = "/slaClient")
    public String showClient(Integer id, Model model) {
        List<ServiceSla> slas = slaService.findSlaBySlaId(id);
        ServiceClient client = clientService.findClient(id);
        model.addAttribute("client", client);
        model.addAttribute("slas", slas);
        return "service/sla/clientsla.page";
    }

    /*下面是协议里面，点击“详情”里面“编辑”和“删除”*/
    @RequestMapping(value = "/slaEdit")
    public String slaEdit(Integer id, Model model) {
        if (id != null) {
            ServiceSla sla = slaService.findSla(id);
            model.addAttribute("sla", sla);
        }
        return "/service/sla/slaAdd.page";
    }

    @ResponseBody
    @RequestMapping(value = "/slaDelete")
    public Map slaDelete(Integer id) {
        slaService.deleteSla(id);
        Map<String, String> map = new HashMap<String, String>();
        map.put("status", "y");
        map.put("info", "删除成功");
        return map;
    }

    /*协议的“协议标题”页面，协议具体内容查找出来*/
    @RequestMapping(value = "/slaContent")
    public String slaContent(Integer id, Model model) {
        /*if (id != null) {
            ServiceSla sla = slaService.findSla(id);
            model.addAttribute("sla", sla);
        }*/
        ServiceSla sla = slaService.findSla(id);
        model.addAttribute("sla", sla);
        return "service/sla/slaContent.page";
    }

    /*协议的“协议标题”页面，里面的编辑和删除*/
    @RequestMapping(value = "/slaContentEdit")
    public String slaContentEdit(Integer id, Model model) {
        if (id != null) {
            ServiceSla sla = slaService.findSla(id);
            model.addAttribute("sla", sla);
        }
        return "/service/sla/slaAdd.page";
    }

    @ResponseBody
    @RequestMapping(value = "/slaContentDelete")
    public Map slaTitleDelete(Integer id) {
        slaService.deleteSla(id);
        Map<String, String> map = new HashMap<String, String>();
        map.put("status", "y");
        map.put("info", "删除成功");
        return map;
    }
    /*协议的“客户名称”跳转到客户，根据徐丹阳的页面来的*/
}