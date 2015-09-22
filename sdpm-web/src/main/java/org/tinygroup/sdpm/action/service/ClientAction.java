package org.tinygroup.sdpm.action.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.service.dao.pojo.Client;
import org.tinygroup.sdpm.service.service.inter.ClientService;

/**
 * Created by Administrator on 2015-09-22.
 */
@Controller
@RequestMapping("/service/client")
public class ClientAction extends BaseController {
    @Autowired
    private ClientService clientService;

    @RequestMapping(value = "/list")
    public String list(Model model) {
//        model.addAttribute("client", client);
        return "service/client/clientUser.page";
    }


    @RequestMapping("/form")
    public String form(Client client, Model model) {
        if (client != null) {
            model.addAttribute("client", client);
        }
        return "service/client/clientAdd.page";
    }

    @RequestMapping(value = "/save")
    public String save(Client client, Model model) {
        if (client.getClientId() == null) {
            clientService.addClient(client);
        } else {
            clientService.updateClient(client);
    }
        model.addAttribute("client", client);
        return "service/client/clientUser.page";
    }

    @RequestMapping(value = "/delete")
    public String delete(Integer id) {
        clientService.deleteClient(id);
        return "/service/client/clientUser.page";
    }

}
