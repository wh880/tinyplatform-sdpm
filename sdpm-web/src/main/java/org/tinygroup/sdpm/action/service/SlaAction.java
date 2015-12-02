package org.tinygroup.sdpm.action.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.dao.complexsearch.SearchInfos;
import org.tinygroup.sdpm.product.dao.impl.FieldUtil;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.service.ProductService;
import org.tinygroup.sdpm.service.dao.pojo.ServiceClient;
import org.tinygroup.sdpm.service.dao.pojo.ServiceSla;
import org.tinygroup.sdpm.service.service.inter.ClientService;
import org.tinygroup.sdpm.service.service.inter.SlaService;
import org.tinygroup.sdpm.util.LogUtil;
import org.tinygroup.tinysqldsl.Pager;

import java.util.ArrayList;
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

    /**
     * 查询
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/form")
    public String form(Integer id, Model model) {
        if (id != null) {
            ServiceSla sla = slaService.findSla(id);
            model.addAttribute("sla", sla);
        }
        /**
         * 调用client的服务，查询出客户表的对象
         */
        ServiceClient client = new ServiceClient();
        List<ServiceClient> list = clientService.getClientList(client);
        /**
         * 调用product的服务，查询出产品表的对象
         */
        Product product = new Product();
        List<Product> slas = productService.findProductList(product);
        model.addAttribute("slas", slas);
        model.addAttribute("list", list);
        return "/service/sla/slaAdd.page";
    }

    /**
     * 新增和修改
     *
     * @param clientId
     * @param clientName
     * @param sla
     * @param model
     * @return
     */
    @RequestMapping("/save")
    public String save(Integer clientId, String clientName, ServiceSla sla, Model model) {
        if (sla.getSlaId() == null) {
            sla = slaService.addSla(sla);
            LogUtil.logWithComment(LogUtil.LogOperateObject.SLA, LogUtil.LogAction.OPENED, String.valueOf(sla.getSlaId()), userUtils.getUserId(),
                    null, null, null, sla, null);

        } else {
            ServiceSla qualitySla = slaService.findSla(sla.getSlaId());
            slaService.updateSla(sla);
            LogUtil.logWithComment(LogUtil.LogOperateObject.SLA, LogUtil.LogAction.EDITED, String.valueOf(sla.getSlaId()), userUtils.getUserId(),
                    null, null, qualitySla, sla, null);
        }
        model.addAttribute("sla", sla);
        if (clientName.isEmpty()) {
            return "redirect:" + adminPath + "/service/sla/list";
        }
        return "redirect:" + adminPath + "/service/client/clientSla?id=" + clientId;
    }

    /**
     * 注解的意义是，想根据产品id找到产品名称。"/list"和"/list/data"显示页面表中数据
     *
     * @return
     */
    @RequestMapping(value = "/list")
    public String list(Model model) {
        ServiceClient client = new ServiceClient();
        List<ServiceClient> list = clientService.getClientList(client);
        model.addAttribute("list", list);
        return "/service/sla/sla.page";
    }

    @RequestMapping(value = "/list/data")
    public String listData(Integer limit, Integer start, ServiceSla sla, Integer treeId,
                           String groupOperate, SearchInfos searchInfos,
                           @RequestParam(required = false, defaultValue = "serviceSla.slaId") String order,
                           @RequestParam(required = false, defaultValue = "asc") String ordertype,
                           Model model) {
        /**
         * sla表格里面的productId与左侧树里面的product表里面的productId即treeId匹配，在findSlaPager方法匹配
         */
        Pager<ServiceSla> pager = slaService.findSlaPager(start, limit, sla, treeId, groupOperate, searchInfos, order, ordertype);
        model.addAttribute("pager", pager);
        return "service/sla/slaTableData.pagelet";
    }

    @ResponseBody
    @RequestMapping("/delete")
    public Map delete(Integer id) {
        slaService.deleteSla(id);
        return resultMap(true, "删除成功");
    }

    /**
     * 协议里面，点击“详情”进入。注解的意义是，想根据产品id找到产品名称
     *
     * @param id
     * @param limit
     * @param start
     * @param sla
     * @param page
     * @param pageSize
     * @param model
     * @return
     */
    @RequestMapping(value = "/slaClient")
    public String showClient(Integer id, Integer limit, Integer start, ServiceSla sla,
                             @RequestParam(required = false, defaultValue = "1") int page,
                             @RequestParam(required = false, defaultValue = "10") int pageSize,
                             Model model) {
        /**
         * 在dao层，findSlaBySlaId调用的方法中，增加了根据产品id查找产品名称
         */
        List<ServiceSla> slas = slaService.findSlaBySlaId(id);
        ServiceClient client = clientService.findClient(id);
        model.addAttribute("client", client);
        model.addAttribute("slas", slas);
        return "service/sla/clientsla.page";
    }

    /**
     * 协议的“协议标题”页面，协议具体内容查找出来。将sla表中数据查询出来，放在对象sla中
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/slaContent")
    public String slaContent(Integer id, Model model) {
        ServiceSla sla = slaService.findSla(id);
        model.addAttribute("sla", sla);
        return "service/sla/slaContent.page";
    }

    /**
     * sla批量删除
     *
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/batchDelete")
    public Map batchDelete(String ids) {
        if (ids == null) {
            return resultMap(false, "删除失败");
        }
        List<ServiceSla> list = new ArrayList<ServiceSla>();
        for (String s : ids.split(",")) {
            ServiceSla serviceSla = new ServiceSla();
            serviceSla.setSlaId(Integer.valueOf(s));
            serviceSla.setDeleted(FieldUtil.DELETE_YES);
            list.add(serviceSla);
        }
        slaService.deleteBatchSla(list);
        return resultMap(true, "删除成功");
    }
}