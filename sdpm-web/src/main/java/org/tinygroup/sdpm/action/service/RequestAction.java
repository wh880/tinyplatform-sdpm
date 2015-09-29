package org.tinygroup.sdpm.action.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.service.dao.pojo.ServiceRequest;
import org.tinygroup.sdpm.service.service.inter.RequestService;
import org.tinygroup.tinysqldsl.Pager;

/**
 * Created by Administrator on 2015-09-28.
 */
@Controller
@RequestMapping("/service/request")
public class RequestAction extends BaseController {
    @Autowired
    private RequestService requestService;

    @RequestMapping(value = "/list")
    public String list(ServiceRequest clientRequest) {
        return "service/serviceReq/request.page";
    }

    @RequestMapping(value = "/list/data")
    public String listData(Integer limit, Integer start, ServiceRequest clientRequest, Model model) {
        Pager<ServiceRequest> pager = requestService.findRequestPager(start, limit, clientRequest);
        model.addAttribute("pager", pager);
        return "service/serviceReq/requestTableData.pagelet";
    }


    @RequestMapping("/form")
    public String form(Integer id, Model model) {
        if (id != null) {
            ServiceRequest clientRequest = requestService.findRequest(id);
            model.addAttribute("request", clientRequest);
        }
        return "service/serviceReq/add.page";
    }

    @RequestMapping(value = "/save")
    public String save(ServiceRequest clientRequest, Model model) {
        if (clientRequest.getClientId() == null) {
            requestService.addRequest(clientRequest);
        } else {
            requestService.updateRequest(clientRequest);
        }
        model.addAttribute("request", clientRequest);
        return "service/serviceReq/request.page";
    }
}
