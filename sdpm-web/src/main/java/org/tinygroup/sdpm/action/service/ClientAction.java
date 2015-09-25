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
import org.tinygroup.tinysqldsl.Pager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015-09-22.
 */
@Controller
@RequestMapping("/service/client")
public class ClientAction extends BaseController {
    @Autowired
    private ClientService clientService;

    @RequestMapping(value = "/list")
    public String list(ServiceClient client, Model model) {
        return "service/client/clientUser.page";
    }

    @RequestMapping(value = "/list/data")
    public String listData(Integer limit, Integer start, ServiceClient client, Model model) {
        Pager<ServiceClient> pager = clientService.findClientPager(start, limit, client);
        model.addAttribute("pager", pager);
        return "service/client/clientTableData.pagelet";
    }


    @RequestMapping("/form")
    public String form(Integer id, Model model) {
        if (id != null) {
            ServiceClient client = clientService.findClient(id);
            model.addAttribute("client", client);
        }
        return "service/client/clientAdd.page";
    }

    @RequestMapping(value = "/save")
    public String save(ServiceClient client, Model model) {
        if (client.getClientId() == null) {
            clientService.addClient(client);
        } else {
            clientService.updateClient(client);
        }
        model.addAttribute("client", client);
        return "service/client/clientUser.page";
    }

    @ResponseBody
    @RequestMapping(value = "/delete")
    public Map delete(Integer id) {
        clientService.deleteClient(id);
        Map<String, String> map = new HashMap<String, String>();
        map.put("status", "y");
        map.put("info", "删除成功");
        return map;
    }

    @RequestMapping(value = "/clientSla")
    public String showSla(Integer id, Model model) {
        List<ServiceSla> slas = clientService.findSlaByClientId(id);
        model.addAttribute("slas", slas);
        return "service/client/clientProduct.page";
    }


}
