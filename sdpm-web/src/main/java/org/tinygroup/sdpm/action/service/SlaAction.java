package org.tinygroup.sdpm.action.service;

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
import org.tinygroup.sdpm.service.dao.pojo.ServiceSla;
import org.tinygroup.sdpm.service.service.inter.ClientService;
import org.tinygroup.sdpm.service.service.inter.SlaService;
import org.tinygroup.tinysqldsl.Pager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/a/service/sla")
public class SlaAction extends BaseController {
    @Autowired
    private SlaService slaService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductLineService productLineService;

    /*查询*/
    @RequestMapping("/form")
    public String form(Integer id, Model model) {
        if (id != null) {
            ServiceSla sla = slaService.findSla(id);
            model.addAttribute("sla", sla);
        }
        /*调用client的服务，查询出客户表的对象*/
        ServiceClient client = new ServiceClient();
        List<ServiceClient> list = clientService.getClientList(client);
        /*调用product的服务，查询出产品表的对象*/
        Product product = new Product();
        List<Product> slas = productService.findProductList(product);
        model.addAttribute("slas", slas);
        model.addAttribute("list", list);
        return "/service/sla/slaAdd.page";
    }

    /*新增和修改*/
    @RequestMapping("/save")
    public String save(ServiceSla sla, Model model) {
        if (sla.getSlaId() == null) {
            sla = slaService.addSla(sla);
        } else {
            slaService.updateSla(sla);
        }
        model.addAttribute("sla", sla);
        return "/service/sla/sla.page";
    }

    /*注解的意义是，想根据产品id找到产品名称。"和“/form”拼凑成查询语句，/list"和"/list/data"是实现sla开始 页面表中数据的显示*/
    @RequestMapping(value = "/list")
    public String list(ServiceSla sla, Model model) {
        return "/service/sla/sla.page";
    }

    @RequestMapping(value = "/list/data")
    public String listData(Integer limit, Integer start, ServiceSla sla, Integer treeId,
                           @RequestParam(required = false, defaultValue = "slaId") String order,
                           @RequestParam(required = false, defaultValue = "asc") String ordertype,
                           Model model) {
        Pager<ServiceSla> pager = slaService.findSlaPager(start, limit, sla, treeId, order, ordertype);
        model.addAttribute("pager", pager);
        return "service/sla/slaTableData.pagelet";
    }

    @ResponseBody
    @RequestMapping("/delete")
    public Map delete(Integer id) {
        slaService.deleteSla(id);
        Map<String, String> map = new HashMap<String, String>();
        map.put("status", "success");
        map.put("info", "删除成功");
        return map;
    }

    /* 协议里面，点击“详情”进入。注解的意义是，想根据产品id找到产品名称*/
    @RequestMapping(value = "/slaClient")
    public String showClient(Integer id, Integer limit, Integer start, ServiceSla sla,
                             @RequestParam(required = false, defaultValue = "1") int page,
                             @RequestParam(required = false, defaultValue = "10") int pageSize,
                             @RequestParam(required = false, defaultValue = "slaId") String order,
                             @RequestParam(required = false, defaultValue = "asc") String ordertype,
                             Model model) {
        start = (page - 1) * pageSize;
        limit = pageSize;
        /*在dao层，findSlaBySlaId调用的方法中，增加了根据产品id查找产品名称*/
        List<ServiceSla> slas = slaService.findSlaBySlaId(id);
        ServiceClient client = clientService.findClient(id);
        model.addAttribute("client", client);
        model.addAttribute("slas", slas);
        return "service/sla/clientsla.page";
    }

    /*下面是协议里面，点击“详情”里面“编辑”和“删除”*/
    @RequestMapping(value = "/slaEdit")
    public String slaEdit(Integer id, Model model) {
        if (id != null) {
            ServiceSla sla = slaService.findSla(id);
            model.addAttribute("sla", sla);
        }
        ServiceClient client = new ServiceClient();
        List<ServiceClient> list = clientService.getClientList(client);
        /*调用product的服务，查询出产品表的对象*/
        Product product = new Product();
        List<Product> slas = productService.findProductList(product);
        model.addAttribute("slas", slas);
        model.addAttribute("list", list);
        return "/service/sla/slaAdd.page";
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

    /*协议的“协议标题”页面，协议具体内容查找出来*/
    @RequestMapping(value = "/slaContent")
    public String slaContent(Integer id, Model model) {
        ServiceSla sla = slaService.findSla(id);
        model.addAttribute("sla", sla);
        return "service/sla/slaContent.page";
    }

    /*协议的“协议标题”页面，里面的编辑和删除*/
    @RequestMapping(value = "/slaContentEdit")
    public String slaContentEdit(Integer id, Model model) {
        if (id != null) {
            ServiceSla sla = slaService.findSla(id);
            model.addAttribute("sla", sla);
        }
        ServiceClient client = new ServiceClient();
        List<ServiceClient> list = clientService.getClientList(client);
        /*调用product的服务，查询出产品表的对象*/
        Product product = new Product();
        List<Product> slas = productService.findProductList(product);
        model.addAttribute("slas", slas);
        model.addAttribute("list", list);
        return "/service/sla/slaAdd.page";
    }
    @ResponseBody
    @RequestMapping(value = "/slaContentDelete")
    public Map slaTitleDelete(Integer id) {
        slaService.deleteSla(id);
        Map<String, String> map = new HashMap<String, String>();
        map.put("status", "y");
        map.put("info", "删除成功");
        return map;
    }

    @ResponseBody
    @RequestMapping("/treeData")
    public List data(String check) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        List<ProductAndLine> productLists = productService.getProductAndLine(new Product());
        List<ProductLine> productLines = productLineService.findlist(new ProductLine());

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
    /*sla必填项校验*/
    @ResponseBody
    @RequestMapping(value = "/judgeClient")
    public Map judgeClient(String name, String param) {
        Map<String, String> map = new HashMap<String, String>();
        if (param != null)
        {
            String productVersion = param;
            ServiceSla serviceSla = slaService.judgeClient(productVersion);
            map.put("status", "y");
            map.put("info", "");
            return map;
        }
        map.put("status", "n");
        map.put("info", "请输入产品版本");
        return map;
    }
    /*sla批量删除*/
    @ResponseBody
    @RequestMapping(value = "/batchDelete")
    public Map batchDelete(String ids) {
        Map<String, String> map = new HashMap<String, String>();
        if (ids == null) {
            map.put("status", "n");
            map.put("info", "删除失败");
            return map;
        }
        List<ServiceSla> list = new ArrayList<ServiceSla>();
        for (String s : ids.split(",")) {
            ServiceSla serviceSla = new ServiceSla();
            serviceSla.setSlaId(Integer.valueOf(s));
            serviceSla.setDeleted(serviceSla.DELETE_YES);
            list.add(serviceSla);
        }
        slaService.deleteBatchSla(list);
        map.put("status", "y");
        map.put("info", "删除成功");
        return map;
    }

}