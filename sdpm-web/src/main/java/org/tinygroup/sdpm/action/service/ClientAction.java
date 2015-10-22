package org.tinygroup.sdpm.action.service;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.dao.pojo.ProductAndLine;
import org.tinygroup.sdpm.product.service.ProductService;
import org.tinygroup.sdpm.productLine.dao.pojo.ProductLine;
import org.tinygroup.sdpm.productLine.service.ProductLineService;
import org.tinygroup.sdpm.service.dao.pojo.ServiceClient;
import org.tinygroup.sdpm.service.dao.pojo.ServiceClientUser;
import org.tinygroup.sdpm.service.dao.pojo.ServiceSla;
import org.tinygroup.sdpm.service.service.inter.ClientService;
import org.tinygroup.sdpm.service.service.inter.ClientUserService;
import org.tinygroup.sdpm.service.service.inter.SlaService;
import org.tinygroup.tinysqldsl.Pager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015-09-22.
 */
@Controller
@RequestMapping("/a/service/client")
public class ClientAction extends BaseController {
    @Autowired
    private ClientService clientService;
    @Autowired
    private SlaService slaService;
    @Autowired
    private ClientUserService clientUserService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductLineService productLineService;

    @RequiresPermissions("service")
    @RequestMapping(value = "/list")
    public String list(ServiceClient client, Model model) {
        return "service/client/clientUser.page";
    }

    @RequestMapping(value = "/list/data")
    public String listData(Integer limit, Integer start, ServiceClient client, Model model, Integer treeId,
                           @RequestParam(required = false, defaultValue = "serviceClient.clientId") String order,
                           @RequestParam(required = false, defaultValue = "desc") String ordertype) {

        if (treeId != null) {
            Pager<ServiceClient> pager = clientService.findClientPagerByPid(start, limit, treeId, order, ordertype);
            model.addAttribute("pager", pager);
            return "service/client/clientTableData.pagelet";
        }
        Pager<ServiceClient> pager = clientService.findClientPager(start, limit, client, order, ordertype);
        model.addAttribute("pager", pager);
        return "service/client/clientTableData.pagelet";
    }

    @RequiresPermissions("client-add")
    @RequestMapping("/form")
    public String form(Integer id, Model model) {
        if (id != null) {
            ServiceClient client = clientService.findClient(id);
            model.addAttribute("client", client);
        }
        return "service/client/clientAdd.page";
    }

    @RequiresPermissions("client-edit")
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
            if (!client.getUserAccount().isEmpty()) {
                serviceClientUser.setUserPhone(client.getUserPhone());
                serviceClientUser.setUserAccount(client.getUserAccount());
                serviceClientUser.setClientId(client.getClientId());
                serviceClientUser.setUserPost(client.getUserPost());
                clientService.addClientUser(serviceClientUser);
            }
        } else {
            clientService.updateClient(client);
            model.addAttribute("client", client);
        }
        return "redirect:" + adminPath + "/service/client/list";
    }

    @RequiresPermissions("client-del")
    @ResponseBody
    @RequestMapping(value = "/delete")
    public Map delete(Integer id) {
        clientService.deleteClient(id);
        clientUserService.deleteAllClientUser(id);
        Map<String, String> map = new HashMap<String, String>();
        map.put("status", "y");
        map.put("info", "删除成功");
        return map;
    }

    @RequiresPermissions("client-batchdel")
    @ResponseBody
    @RequestMapping(value = "/batchDelete")
    public Map batchDelete(String ids) {
        Map<String, String> map = new HashMap<String, String>();
        if (ids == null) {
            map.put("status", "n");
            map.put("info", "删除失败");
            return map;
        }
        List<ServiceClient> list = new ArrayList<ServiceClient>();
        for (String s : ids.split(",")) {
            ServiceClient serviceClient = new ServiceClient();
            serviceClient.setClientId(Integer.valueOf(s));
            serviceClient.setDeleted(serviceClient.DELETE_YES);
            list.add(serviceClient);
        }
        clientService.deleteBatchClient(list);
        map.put("status", "y");
        map.put("info", "删除成功");
        return map;
    }

    @RequiresPermissions("client-sla")
    @RequestMapping(value = "/clientSla")
    public String showSla(Integer id, Model model) {
        if (id != null) {
            ServiceClientUser clientUser = new ServiceClientUser();
            clientUser.setClientId(id);
            List<ServiceClientUser> clientUsers = clientService.getAllClientUser(clientUser);
            model.addAttribute("clientUsers", clientUsers);

            List<ServiceSla> slas = clientService.findSlaByClientId(id);
            ServiceClient client = clientService.findClient(id);
            model.addAttribute("client", client);
            model.addAttribute("slas", slas);
        }
        return "service/client/clientProduct.page";
    }

    @RequiresPermissions("clientSla_delete")
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


    @RequiresPermissions("linkman-del")
    @ResponseBody
    @RequestMapping(value = "/deleteClientUser")
    public Map deleteClientUser(Integer id) {
        clientService.deleteClientUser(id);
        Map<String, String> map = new HashMap<String, String>();
        map.put("status", "y");
        map.put("info", "删除成功");
        return map;
    }

    @RequestMapping(value = "/clientUserUpdate/date")
    public String clientUserUpdate(Integer id, Integer clientId, Model model) {
        if (id != null) {
            ServiceClientUser clientUser = clientUserService.findClientUser(id);
            model.addAttribute("clientUser", clientUser);
        }
        if (id == null && clientId != null) {
            ServiceClientUser clientUser = new ServiceClientUser();
            clientUser.setClientId(clientId);
            model.addAttribute("clientUser", clientUser);
        }
        return "service/client/companyInfoEdit.pagelet";
    }

    @RequiresPermissions("linkman-new")
    @RequestMapping(value = "/clientUserUpdate")
    public String updateClientUser(ServiceClientUser clientUser) {
        if (clientUser.getId() != null) {
            clientUserService.updateClientUser(clientUser);
        } else
            clientUser = clientUserService.addClientUser(clientUser);
        return "redirect:" + adminPath + "/service/client/clientSla?id=" + clientUser.getClientId();
    }

    @RequestMapping("/slaAdd")
    public String slaAdd(Integer id, Model model) {
        if (id != null) {
            ServiceClient client = new ServiceClient();
            client = clientService.findClient(id);
            model.addAttribute("client", client);
        /*调用product的服务，查询出产品表的对象*/
            Product product = new Product();
            List<Product> slas = productService.findProductList(product);
            model.addAttribute("slas", slas);
        }
        return "/service/client/clientSlaAdd.page";
    }

    @RequestMapping("/slaSave")
    public String slaSave(ServiceSla sla, Model model) {
        if (sla.getSlaId() == null) {
            sla = slaService.addSla(sla);
        }
        return "redirect:" + adminPath + "/service/client/clientSla?id=" + sla.getClientId();
    }

    @ResponseBody
    @RequestMapping(value = "/judgeClient")
    public Map judgeClient(String name, String param) {
        Map<String, String> map = new HashMap<String, String>();
        if (param != null) {
            String clientName = param;
            ServiceClient serviceClient = clientService.judgeClient(clientName);
            if (serviceClient != null) {
                map.put("status", "n");
                map.put("info", "该客户已存在");
                return map;
            } else {
                map.put("status", "y");
                map.put("info", "");
                return map;
            }
        }
        map.put("status", "n");
        map.put("info", "请输入客户名称");
        return map;
    }

    @ResponseBody
    @RequestMapping("/treeData")
    public List data(String check) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        List<ProductAndLine> productLists = productService.getProductAndLine(new Product());
        List<ProductLine> productLines = productLineService.findList(new ProductLine());

        for (ProductLine d : productLines) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", "p" + d.getProductLineId());
            map.put("pId", 0);
            map.put("name", d.getProductLineName());
            map.put("open", true);
            map.put("clickAble", false);
            list.add(map);
        }
        for (ProductAndLine d : productLists) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", d.getProductId());
            map.put("pId", "p" + d.getProductLineId());
            map.put("name", d.getProductName());
            map.put("open", true);
            list.add(map);
        }
        return list;
    }
}
