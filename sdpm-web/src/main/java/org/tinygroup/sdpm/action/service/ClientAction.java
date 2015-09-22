package org.tinygroup.sdpm.action.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.service.service.inter.ClientService;

/**
 * Created by Administrator on 2015-09-22.
 */
@Controller
@RequestMapping("/service/client/")
public class ClientAction extends BaseController {
    @Autowired
    private ClientService clientService;
}
