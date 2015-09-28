package org.tinygroup.sdpm.action.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.service.dao.pojo.ServiceClient;
import org.tinygroup.sdpm.service.dao.pojo.ServiceClientUser;
import org.tinygroup.sdpm.service.dao.pojo.ServiceSla;
import org.tinygroup.sdpm.service.service.inter.ClientService;
import org.tinygroup.sdpm.service.service.inter.SlaService;
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
    @Autowired
    private SlaService slaService;


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

    @RequestMapping("/edit")
    public String edit(Integer id, Model model) {
        if (id != null) {
            ServiceClient client = clientService.findClient(id);
            model.addAttribute("client", client);
        }
        return "service/client/clientEdit.page";
    }

    @RequestMapping(value = "/save")
    public String save(ServiceClient client, Model model) {
        ServiceClientUser serviceClientUser = new ServiceClientUser();
        if (client.getClientId() == null) {
            client = clientService.addClient(client);
            //新建用户联系人表
            serviceClientUser.setUserPhone(client.getUserPhone());
            serviceClientUser.setUserAccount(client.getUserAccount());
            serviceClientUser.setClientId(client.getClientId());
            serviceClientUser.setUserPost(client.getUserPost());
            clientService.addClientUser(serviceClientUser);
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
        ServiceClient client = clientService.findClient(id);
        model.addAttribute("client", client);
        model.addAttribute("slas", slas);
        return "service/client/clientProduct.page";
    }

    @RequestMapping("/slaEdit")
    public String slaEdit(Integer id, Model model) {
        if (id != null) {
            ServiceSla sla = slaService.findSla(id);
            model.addAttribute("sla", sla);//待定....根据明明页面上的来
        }
        return "service/sla/slaAdd.page";
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

    @RequestMapping("/slaDetail")
    public String slaDetail(Integer id, Model model) {
        if (id != null) {
            ServiceSla sla = slaService.findSla(id);
            model.addAttribute("sla", sla);
        }
        return "service/sla/slaContent.page";
    }

    @RequestMapping("/clientDetail")
    public String clientDetail(Integer id, Model model) {
        if (id != null) {
            ServiceClient client = clientService.findClient(id);
            model.addAttribute("client", client);
            ServiceClientUser clientUser = new ServiceClientUser();
            clientUser.setClientId(id);
            List<ServiceClientUser> clientUsers = clientService.getAllClientUser(clientUser);
            model.addAttribute("clientUsers", clientUsers);
        }
        return "service/client/clientInfo.page";
    }

    @ResponseBody
    @RequestMapping(value = "/deleteClientUser")
    public Map deleteClientUser(Integer id) {
        clientService.deleteClient(id);
        Map<String, String> map = new HashMap<String, String>();
        map.put("status", "y");
        map.put("info", "删除成功");
        return map;
    }
}
