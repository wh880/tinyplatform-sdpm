package org.tinygroup.sdpm.action.service;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.sdpm.common.util.ComplexSearch.SearchInfos;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.sdpm.org.service.inter.UserService;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.service.ProductService;
import org.tinygroup.sdpm.service.dao.pojo.ServiceClient;
import org.tinygroup.sdpm.service.dao.pojo.ServiceRequest;
import org.tinygroup.sdpm.service.dao.pojo.ServiceReview;
import org.tinygroup.sdpm.service.service.inter.ClientService;
import org.tinygroup.sdpm.service.service.inter.RequestService;
import org.tinygroup.sdpm.service.service.inter.ReviewService;
import org.tinygroup.sdpm.util.UserUtils;
import org.tinygroup.tinysqldsl.Pager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015-09-28.
 */
@Controller
@RequestMapping("/a/service/request")
public class RequestAction extends BaseController {
    @Autowired
    private RequestService requestService;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    @RequiresPermissions("request")
    @RequestMapping(value = "/list")
    public String list(Integer operation, Integer status, Model model) {
        model.addAttribute("status", status);
        model.addAttribute("operation", operation);
        List<OrgUser> userList = userService.findUserList(new OrgUser());
        List<ServiceClient> serviceClients = clientService.getClientList(new ServiceClient());
        model.addAttribute("serviceClients", serviceClients);
        model.addAttribute("userList", userList);
        return "service/serviceReq/request.page";
    }

    @RequestMapping(value = "/list/data")
    public String listData(Integer limit, Integer start, ServiceRequest clientRequest, Integer status, Integer operation, Integer treeId, Model model,
                           String groupOperate, SearchInfos searchInfos,
                           @RequestParam(required = false, defaultValue = "clientRequestId") String order,
                           @RequestParam(required = false, defaultValue = "desc") String orderType) {

        if (operation != null) {
            OrgUser user = UserUtils.getUser();
            Pager<ServiceRequest> pager = requestService.findOperationByMe(start, limit, user, clientRequest, treeId, operation, order, orderType);
            model.addAttribute("pager", pager);
            return "service/serviceReq/requestTableData.pagelet";
        }
        Pager<ServiceRequest> pager = requestService.findRequestPager(start, limit, status, clientRequest, treeId, groupOperate, searchInfos, order, orderType);
        model.addAttribute("pager", pager);
        return "service/serviceReq/requestTableData.pagelet";
    }

    @RequiresPermissions("request-add")
    @RequestMapping("/form")
    public String form(Integer id, Model model) {
        if (id != null) {
            ServiceRequest clientRequest = requestService.findRequest(id);
            model.addAttribute("request", clientRequest);
        }
        ServiceClient client = new ServiceClient();
        List<ServiceClient> clientList = clientService.getClientList(client);
        List<Product> productList = productService.findProductList(new Product());
        model.addAttribute("clientList", clientList);
        model.addAttribute("productList", productList);
        return "service/serviceReq/add.page";
    }

    @RequestMapping(value = "/save")
    public String save(ServiceRequest clientRequest, Model model) {
        if (clientRequest.getClientRequestId() == null) {
            requestService.addRequest(clientRequest);
        } else {
            requestService.updateRequest(clientRequest);
        }
        model.addAttribute("request", clientRequest);
        return "redirect:" + adminPath + "/service/request/list";
    }

    @RequiresPermissions("request-close")
    @RequestMapping(value = "/close")//关闭请求
    public String close(Integer id, Model model) {
        model.addAttribute("clientRequestId", id);
        ServiceRequest requests = requestService.findRequest(id);
        model.addAttribute("requests", requests);
        return "service/serviceReq/closeRequest.pagelet";
    }
    @RequestMapping(value = "/close/date")
    public String closed(ServiceRequest clientRequest, Model model) {
        if (clientRequest.getClientRequestId() != null)
            requestService.closeRequest(clientRequest);
        return "redirect:" + adminPath + "/service/request/list";
    }

    @RequiresPermissions("request-delete")
    @ResponseBody
    @RequestMapping(value = "/delete")
    public Map delete(Integer id) {
        requestService.deleteRequest(id);
        return resultMap(true, "删除成功");
    }

    @RequiresPermissions("request-solve")
    @RequestMapping(value = "/reply")
    public String reply(Integer id, Model model) {
        if (id != null) {
            ServiceRequest clientRequest = requestService.findRequest(id);
            model.addAttribute("request", clientRequest);
        }
        return "/service/serviceReq/requestsolve.page";
    }

    @RequestMapping(value = "/replySave")
    public String replySave(ServiceRequest clientRequest) {
        if (clientRequest != null) {
            requestService.saveReply(clientRequest);
        }
        return "redirect:" + adminPath + "/service/request/list";
    }

    @RequiresPermissions("request-back")
    @RequestMapping(value = "/review")
    public String review(Integer id, Model model) {
        if (id != null) {
            OrgUser user = UserUtils.getUser();
            ServiceRequest clientRequest = requestService.findRequest(id);
            model.addAttribute("request", clientRequest);
            model.addAttribute("currentReviewer", user.getOrgUserAccount());
            if (clientRequest.getRequestStatus() == ServiceRequest.RETURNVISIT) {
                ServiceReview review = reviewService.findReviewByRequestId(id);
                model.addAttribute("review", review);
            }
        }
        return "service/serviceReq/requestViewList.page";
    }

    @RequestMapping(value = "/reviewSave")
    public String reviewSave(ServiceReview review, Model model) {
        if (review.getReviewId() == null) {
            reviewService.addReview(review);
        } else {
            reviewService.updateReview(review);
        }
        reviewService.changeStatus(review.getClientRequestId());
        return "redirect:" + adminPath + "/service/request/list";
    }

    @RequiresPermissions("request-tome")
    @ResponseBody
    @RequestMapping(value = "/solveBy")
    public Map solveBy(Integer[] ids, String name) {
        List<ServiceRequest> list = new ArrayList<ServiceRequest>();
        for (Integer id : ids) {
            ServiceRequest serviceRequest = new ServiceRequest();
            serviceRequest.setClientRequestId(id);
            serviceRequest.setReplier(name);
            list.add(serviceRequest);
        }
        requestService.updateReply(list);
        return resultMap(true, "操作成功");
    }

    @RequiresPermissions("request-review-byme")
    @ResponseBody
    @RequestMapping(value = "/reviewBy")
    public Map reviewBy(Integer[] ids, String name) {
        List<ServiceRequest> list = new ArrayList<ServiceRequest>();
        for (Integer id : ids) {
            ServiceRequest serviceRequest = new ServiceRequest();
            serviceRequest.setClientRequestId(id);
            serviceRequest.setRequestReviewer(name);
            list.add(serviceRequest);
        }
        requestService.updateReview(list);
        return resultMap(true, "操作成功");
    }

    @RequiresPermissions("request-batchdel")
    @ResponseBody
    @RequestMapping(value = "/batchDelete")
    public Map batchDelete(String ids) {
        if (ids == null) {
            return resultMap(false, "删除失败");
        }
        List<ServiceRequest> list = new ArrayList<ServiceRequest>();
        for (String s : ids.split(",")) {
            ServiceRequest serviceRequest = new ServiceRequest();
            serviceRequest.setClientRequestId(Integer.valueOf(s));
            serviceRequest.setDeleted(serviceRequest.DELETE_YES);
            list.add(serviceRequest);
        }
        requestService.deleteBatchRequest(list);
        return resultMap(true, "删除成功");
    }
}
