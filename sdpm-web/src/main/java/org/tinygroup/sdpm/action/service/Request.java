package org.tinygroup.sdpm.action.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tinygroup.sdpm.service.service.inter.RequestService;

/**
 * Created by Administrator on 2015-09-28.
 */
@Controller
@RequestMapping("/service/request")
public class Request {
    @Autowired
    private RequestService requestService;
}
