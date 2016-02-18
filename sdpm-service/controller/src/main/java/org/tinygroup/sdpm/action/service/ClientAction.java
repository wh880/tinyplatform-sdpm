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
import org.tinygroup.sdpm.product.service.inter.ProductService;
import org.tinygroup.sdpm.productLine.dao.pojo.ProductLine;
import org.tinygroup.sdpm.productLine.service.inter.ProductLineService;
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
 * Created by xudy on 2015-09-22.
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

    /**
     * 显示请求表格内容
     *
     * @return
     */
    @RequiresPermissions("service")
    @RequestMapping(value = "/list")
    public String list() {
        return "service/client/clientUser.page";
    }

    /**
     * @param limit     分页的记录限制
     * @param start     分页的当前页
     * @param client
     * @param model
     * @param treeId    左侧树对应产品的
     * @param order     排序
     * @param ordertype
     * @return
     */
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

    /**
     * 从表中读取数据跳到新建或编辑页面
     *
     * @param id
     * @param model
     * @return
     */
    @RequiresPermissions("client-add")
    @RequestMapping("/form")
    public String form(Integer id, Model model) {
        if (id != null) {
            ServiceClient client = clientService.findClient(id);
            model.addAttribute("client", client);
        }
        return "service/client/clientAdd.page";
    }

    /**
     * 客户信息编辑
     *
     * @param id
     * @param model
     * @return
     */
    @RequiresPermissions("client-edit")
    @RequestMapping("/edit")
    public String edit(Integer id, Model model) {
        if (id != null) {
            ServiceClient client = clientService.findClient(id);
            model.addAttribute("client", client);
        }
        return "service/client/clientEdit.page";
    }

    /**
     * 客户信息保存
     *
     * @param client
     * @param model
     * @return
     */
    @RequestMapping(value = "/save")
    public String save(ServiceClient client, Model model) {
        ServiceClientUser serviceClientUser = new ServiceClientUser();
        if (client.getClientId() == null) {
            client = clientService.addClient(client);
            if (!client.getUserAccount().isEmpty()) {
                serviceClientUser.setUserPhone(client.getUserPhone());
                serviceClientUser.setUserAccount(client.getUserAccount());
                serviceClientUser.setClientId(client.getClientId());
                serviceClientUser.setUserPost(client.getUserPost());
                clientUserService.addServiceClientUser(serviceClientUser);
            }
        } else {
            clientService.updateClient(client);
            model.addAttribute("client", client);
        }
        return "redirect:" + adminPath + "/service/client/list";
    }

    /**
     * 删除客户
     *
     * @param id
     * @return
     */
    @RequiresPermissions("client-del")
    @ResponseBody
    @RequestMapping(value = "/delete")
    public Map delete(Integer id) {
        clientService.deleteClient(id);
        clientUserService.deleteAllClientUser(id);
        return resultMap(true, "删除成功");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @RequiresPermissions("client-batchdel")
    @ResponseBody
    @RequestMapping(value = "/batchDelete")
    public Map batchDelete(String ids) {
        if (ids == null) {
            return resultMap(false, "删除失败");
        }
        List<ServiceClient> list = new ArrayList<ServiceClient>();
        for (String s : ids.split(",")) {
            ServiceClient serviceClient = new ServiceClient();
            serviceClient.setClientId(Integer.valueOf(s));
            serviceClient.setDeleted(ServiceClient.DELETE_YES);
            list.add(serviceClient);
        }
        clientService.deleteBatchClient(list);
        return resultMap(true, "删除成功");
    }

    /**
     * 显示客户信息和客户绑定协议的信息
     *
     * @param id
     * @param model
     * @return
     */
    @RequiresPermissions("client")
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
        return "service/client/clientProduct";
    }

    /**
     * 删除客户绑定的协议
     *
     * @param id
     * @return
     */
    @RequiresPermissions("clientSla_delete")
    @ResponseBody
    @RequestMapping(value = "/slaDelete")
    public Map slaDelete(Integer id) {
        slaService.deleteSla(id);
        return resultMap(true, "删除成功");
    }

    /**
     * 协议具体内容
     * 显示
     *
     * @param id
     * @param model
     * @return
     */
    @RequiresPermissions("client")
    @RequestMapping("/slaDetail")
    public String slaDetail(Integer id, Model model) {
        if (id != null) {
            ServiceSla sla = slaService.findSla(id);
            model.addAttribute("sla", sla);
        }
        return "service/sla/slaContent.page";
    }

    /**
     * 删除客户联系人
     *
     * @param id
     * @return
     */
    @RequiresPermissions("linkman-del")
    @ResponseBody
    @RequestMapping(value = "/deleteClientUser")
    public Map deleteClientUser(Integer id) {
        clientService.deleteClientUser(id);
        return resultMap(true, "删除成功");
    }

    /**
     * 客户联系人编辑
     *
     * @param id
     * @param clientId
     * @param model
     * @return
     */
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

    /**
     * 客户联系人编辑
     *
     * @param clientUser
     * @return
     */
    @RequiresPermissions("linkman-new")
    @RequestMapping(value = "/clientUserUpdate")
    public String updateClientUser(ServiceClientUser clientUser) {
        if (clientUser.getId() != null) {
            clientUserService.updateClientUser(clientUser);
        } else
            clientUser = clientUserService.addServiceClientUser(clientUser);
        return "redirect:" + adminPath + "/service/client/clientSla?id=" + clientUser.getClientId();
    }

    /**
     * 客户绑定协议
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/slaAdd")
    public String slaAdd(Integer id, Model model) {
        if (id != null) {
            ServiceClient client = clientService.findClient(id);
            model.addAttribute("client", client);
            Product product = new Product();
            List<Product> slas = productService.findProductList(product);
            model.addAttribute("slas", slas);
        }
        return "/service/client/clientSlaAdd.page";
    }

    /**
     * 协议保存
     *
     * @param sla
     * @return
     */
    @RequestMapping("/slaSave")
    public String slaSave(ServiceSla sla) {
        if (sla.getSlaId() == null) {
            sla = slaService.addSla(sla);
        }
        return "redirect:" + adminPath + "/service/client/clientSla?id=" + sla.getClientId();
    }

    /**
     * 新建客户时判断客户是否存在
     *
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/judgeClient")
    public Map judgeClient(String param) {
        if (param != null) {
            String clientName = param;
            ServiceClient serviceClient = clientService.judgeClient(clientName);
            if (serviceClient != null) {
                return resultMap(false, "该客户已存在");
            } else {
                return resultMap(true, "");
            }
        }
        return resultMap(false, "请输入客户名称");
    }

    /**
     * 实现左树
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/treeData")
    public List data() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Product product = new Product();
        product.setDeleted(0);
        List<ProductAndLine> productLists = productService.getProductAndLine(product);

        List<ProductLine> productLines = productLineService.findProductLineList(new ProductLine());

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
