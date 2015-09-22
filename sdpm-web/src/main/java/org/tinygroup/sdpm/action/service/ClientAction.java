package org.tinygroup.sdpm.action.service;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import com.sun.tools.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.service.dao.pojo.Client;
import org.tinygroup.sdpm.service.service.inter.ClientService;

import java.util.List;

/**
 * Created by Administrator on 2015-09-22.
 */
@Controller
@RequestMapping("/service/client/")
public class ClientAction extends BaseController {
    @Autowired
    private ClientService clientService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Client client, Model model) {
        if (client.getClientId() == null) {
            clientService.addClient(client);
        } else {
            clientService.updateClient(client);
        }
        model.addAttribute("client", client);
        return "service/client/clientAdd.page";
    }

}
