package org.tinygroup.sdpm.action.productLine;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.dao.condition.ConditionCarrier;
import org.tinygroup.sdpm.dao.condition.ConditionUtils;
import org.tinygroup.sdpm.org.dao.pojo.OrgRole;
import org.tinygroup.sdpm.org.service.inter.RoleService;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.dao.utils.FieldUtil;
import org.tinygroup.sdpm.product.service.inter.ProductService;
import org.tinygroup.sdpm.productLine.dao.pojo.ProductLine;
import org.tinygroup.sdpm.productLine.service.inter.ProductLineService;
import org.tinygroup.sdpm.project.dao.pojo.ProjectBuild;
import org.tinygroup.sdpm.project.service.inter.BuildService;
import org.tinygroup.sdpm.system.dao.pojo.SystemAction;
import org.tinygroup.sdpm.system.dao.pojo.SystemConfig;
import org.tinygroup.sdpm.util.LogUtil;
import org.tinygroup.sdpm.util.UserUtils;
import org.tinygroup.tinysqldsl.Pager;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 产品线控制器
 *
 * @author wangjie14929
 */
@Controller
@RequestMapping("/a/productLine")
public class ProductLineAction extends BaseController {

    @Autowired
    private ProductLineService productLineService;
    @Autowired
    private ProductService productService;
    @Autowired
    private BuildService buildService;
    @Autowired
    private RoleService roleService;

    @RequestMapping("/content/{type}")
    public String productLine(@PathVariable(value = "type") String type) {
        if ("add".equals(type)) {

            return "productLine/page/add/add.page";
        }
        if ("all".equals(type)) {
            return "productLine/page/list/productline/list.page";
        }
        return null;

    }

    @RequestMapping("/save")
    public String save(ProductLine productLine, SystemAction systemAction, String lastAddress) {
        productLine.setProductLineCreatedBy(userUtils.getUserId());
        productLine.setProductLineCreatedDate(new Date());
        productLine.setProductLineStatus(ProductLine.STATUS_ACTIVE);
        ProductLine productLine1 = productLineService.addProductLine(productLine);
        LogUtil.logWithComment(LogUtil.LogOperateObject.PRODUCTLINE
                , LogUtil.LogAction.OPENED
                , String.valueOf(productLine1.getProductLineId())
                , userUtils.getUserId()
                , null
                , null
                , null
                , null
                , systemAction.getActionComment());
        if (!StringUtil.isBlank(lastAddress)) {
            if (lastAddress.contains("?")) {
                return "redirect:" + lastAddress + "&backProductLine=" + productLine1.getProductLineId();
            }
            return "redirect:" + lastAddress + "?backProductLine=" + productLine1.getProductLineId();
        }
        return "redirect:" + "/a/productLine/to";
    }

    @RequestMapping("/update")
    public String update(ProductLine productLine) {
        ProductLine productLineOld = productLineService.findProductLine(productLine.getProductLineId());
        if (ProductLine.STATUS_DELETED.equals(productLine.getProductLineStatus())) {
            productLine.setDeleted(1);
        } else {
            if (FieldUtil.DELETE_YES == (productLineOld.getDeleted()) &&
                    !ProductLine.STATUS_DELETED.equals(productLine.getProductLineStatus())) {
                productLine.setDeleted(0);
            }
        }
        productLineService.updateProductLine(productLine);
        LogUtil.logWithComment(LogUtil.LogOperateObject.PRODUCTLINE,
                LogUtil.LogAction.EDITED,
                String.valueOf(productLine.getProductLineId()),
                userUtils.getUserId(),
                null,
                null,
                productLineOld,
                productLine,
                productLine.getProductLineSpec());
        return "redirect:" + "/a/productLine/to";
    }


    @RequestMapping("/edit")
    public String edit(ProductLine productLine) {
        productLineService.updateProductLine(productLine);
        return "/productLine/page/update/update.page";
    }

    @ResponseBody
    @RequestMapping("/delete")
    public Map delete(Integer productLineId, SystemAction systemAction) {
        Product product = new Product();
        product.setProductLineId(productLineId);
        product.setDeleted(0);
        List<Product> products = productService.findProductList(product);
        for (Product product1 : products) {
            productService.deleteProduct(product1.getProductId());
            LogUtil.logWithComment(LogUtil.LogOperateObject.PRODUCT,
                    LogUtil.LogAction.DELETED,
                    String.valueOf(product1.getProductId()),
                    userUtils.getUserId(),
                    null,
                    null,
                    null,
                    null,
                    systemAction.getActionComment());
        }
        productLineService.deleteProductLine(productLineId);
        Map<String, String> map = new HashMap<String, String>();
        LogUtil.logWithComment(LogUtil.LogOperateObject.PRODUCTLINE,
                LogUtil.LogAction.DELETED,
                String.valueOf(productLineId),
                userUtils.getUserId(),
                null,
                null,
                null,
                null,
                systemAction.getActionComment());
        map.put("status", "success");
        map.put("info", "删除成功");
        return map;
    }

    @RequestMapping("/find")
    public String find(Integer productLineId, Model model) {
        ProductLine productLine = productLineService.findProductLine(productLineId);
        model.addAttribute("productLine", productLine);
        return "/productLine/page/update/update.page";
    }


    @RequestMapping("/shut")
    public String shut(SystemAction systemAction, ProductLine productLine) {
        ProductLine productLine1 = productLineService.findProductLine(productLine.getProductLineId());
        productLine.setDeleted(FieldUtil.DELETE_YES);
        productLineService.updateProductLine(productLine);
        LogUtil.logWithComment(LogUtil.LogOperateObject.PRODUCTLINE,
                LogUtil.LogAction.CLOSED,
                String.valueOf(productLine.getProductLineId()),
                userUtils.getUserId(),
                String.valueOf(productLine.getProductLineId()),
                null,
                productLine1,
                productLine,
                systemAction.getActionComment());
        return "redirect:" + "/productLine/page/list/productline/list.page";
    }

    @RequestMapping("/list")
    public String list(ProductLine productLine, Integer start, Integer pagesize,
                       @RequestParam(required = false, defaultValue = "productLineId") String order,
                       @RequestParam(required = false, defaultValue = "asc") String ordertype, Integer status, Model model) {
        ConditionCarrier carrier = new ConditionCarrier();
        Pager<ProductLine> pagerProductLine = null;
        if (status != null && status != 5) {
            mergeStatusCondition(carrier, status);
            Integer[] ids = productLineService.getUserProductLineIds(userUtils.getUserId());
            pagerProductLine = productLineService.findProductLinePagerInIds(start, pagesize, carrier, productLine, ids, order, ordertype);
        } else {
            ProductLine productLine1 = new ProductLine();
            productLine1.setDeleted(1);
            pagerProductLine = productLineService.findProductLineInPage(start, pagesize, productLine1, order, ordertype);
        }

        model.addAttribute("productLine", pagerProductLine);
        return "/productLine/data/productline/productLinedata.pagelet";
    }

    @RequestMapping("/find/{forword}")
    public String find(@CookieValue(value = "cookieProductId") String cookieProductId,
                       @PathVariable(value = "forword") String forword,Integer productLineId, Model model) {
        if(productLineId==null)
        {
            Product product = productService.findProductById(Integer.parseInt(cookieProductId));
            productLineId = product.getProductLineId();
        }
        ProductLine productLine = productLineService.findProductLine(productLineId);
        List<String> lineNameList = productService.getProductNameByLineId(productLineId);
        model.addAttribute("productLine", productLine);
        model.addAttribute("lineNameList", lineNameList);

        if ("overview".equals(forword)) {
            return "/productLine/page/view/overview.page";
        } else if ("productLineDetail".equals(forword)) {
            if (!StringUtil.isBlank(productLine.getProductLineWhiteList())) {
                String[] ids = productLine.getProductLineWhiteList().split(",");
                List<OrgRole> roles = roleService.getRoleByIds(ids);
                model.addAttribute("whiteLists", roles);
            }
            return "/productLine/page/view/other-information.pagelet";
        }
        return "";
    }

    @RequestMapping("/toProduct")
    public String productLineAction() {
        return "redirect:" + adminPath + "/product";
    }

    @RequestMapping("/to")
    public String to(HttpServletRequest request, Model model) {
        String query = request.getQueryString();
        if (StringUtil.isBlank(query) || !query.contains("status")) {
            return "redirect:" + adminPath + "/productLine/to?status=1";
        }
        return "/productLine/page/list/productline/list.page";
    }

    @RequestMapping("/listProduct")
    public String listProduct() {
        return "/productLine/page/list/productline/list.page";

    }

    @ResponseBody
    @RequestMapping("/treeData")
    public List treeData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Product product = new Product();
        product.setDeleted(FieldUtil.DELETE_NO);
        List<Product> productLists = productService.getProductByUser(userUtils.getUserId(), 0, null, Product.CHOOSE_OPENED);
        ProductLine productLine = new ProductLine();
        productLine.setDeleted(FieldUtil.DELETE_NO);
        List<ProductLine> productLines = productLineService.getUserProductLine(userUtils.getUserId());
        Integer[] ids = new Integer[productLists.size()];
        for (int i = 0; i < ids.length; i++) {
            ids[i] = productLists.get(i).getProductId();
        }
        List<ProjectBuild> projectBuilds = buildService.getBuildByProducts(ids);

        for (ProductLine d : productLines) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", "p" + d.getProductLineId());
            map.put("pId", 0);
            map.put("name", d.getProductLineName());
            map.put("open", true);
            map.put("clickAble", false);
            list.add(map);
        }
        for (Product d : productLists) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", "v" + d.getProductId());
            map.put("pId", "p" + d.getProductLineId());
            map.put("name", d.getProductName());
            map.put("open", true);
            list.add(map);
        }
        for (ProjectBuild d : projectBuilds) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", d.getBuildId());
            map.put("pId", "v" + d.getBuildProduct());
            map.put("name", d.getBuildName());
            map.put("open", true);
            map.put("clickAble", false);
            list.add(map);
        }
        return list;
    }


    @RequestMapping("/totree")
    public String totree(String treeId, Model model) {

        if (!StringUtil.isBlank(treeId)) {
            String prefix = treeId.substring(0, 1);
            if ("p".equals(prefix)) {
                model.addAttribute("productLineId", treeId.substring(1));
                return "/productLine/page/list/product/productlist.page";
            }
            if ("v".equals(prefix)) {
                model.addAttribute("buildProduct", treeId.substring(1));
                return "/productLine/page/list/build/buildlist.page";
            }
        }
        return "redirect:" + adminPath + "/productLine/to";
    }


    @ResponseBody
    @RequestMapping("/overviewTree")
    public List overview(Integer productLineId) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        ProductLine productLine = null;
        if (productLineId != null && productLineId > 0) {
            productLine = productLineService.findProductLine(productLineId);
        }
        List<Product> productList = null;
        if (productLine != null) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", "p" + productLine.getProductLineId());
            map.put("pId", 0);
            map.put("name", productLine.getProductLineName());
            map.put("open", true);
            map.put("clickAble", false);
            list.add(map);
            Product product = new Product();
            product.setProductLineId(productLine.getProductLineId());
            productList = productService.getProductByUser(UserUtils.getUserId(), 0, productLine.getProductLineId(), "");
        }
        if(productList != null){
            for (Product d : productList) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id", "v" + d.getProductId());
                map.put("pId", "p" + productLine.getProductLineId());
                map.put("name", d.getProductName());
                map.put("open", true);
                map.put("clickAble", false);
                list.add(map);
            }
        }

        return list;
    }

    private void mergeStatusCondition(ConditionCarrier carrier, Integer status) {
        if (status == null || status < 1) return;
        switch (status) {
            case 1:
                return;
            case 2:
                carrier.put("productLine.productLineOwner",
                        ConditionUtils.Operate.EQ.getOperate(),
                        ConditionUtils.CommonFieldType.FIELD_OPERATE.getCommonField(),
                        userUtils.getUserId());
                break;
            case 3:
                carrier.put("productLine.productLineQualityManager",
                        ConditionUtils.Operate.EQ.getOperate(),
                        ConditionUtils.CommonFieldType.FIELD_OPERATE.getCommonField(),
                        userUtils.getUserId());
                break;
            case 4:
                carrier.put("productLine.productLineDeliveryManager",
                        ConditionUtils.Operate.EQ.getOperate(),
                        ConditionUtils.CommonFieldType.FIELD_OPERATE.getCommonField(),
                        userUtils.getUserId());
                break;
        }
        return;
    }

    @ResponseBody
    @RequestMapping(value = "/judgeProductLineName")
    public Map judgeProductLineName(String param, Integer productLineId) {
        if (param != null) {
            String productLineName = param;
            ProductLine productLine = new ProductLine();
            productLine.setProductLineName(productLineName);
            productLine.setProductLineId(productLineId);
            productLine.setDeleted(FieldUtil.DELETE_NO);
            List<ProductLine> productLines = productLineService.findProductLineList(productLine);
            if (productLines.size() != 0) {
                if (productLineId == null) {
                    return resultMap(false, "该产品线已存在");
                } else if (!productLineId.equals(productLines.get(0).getProductLineId())) {
                    return resultMap(false, "该产品线已存在");
                } else {
                    return resultMap(true, "");
                }
            } else {
                return resultMap(true, "");
            }
        }
        return resultMap(false, "请输入产品线名称");
    }

    @ResponseBody
    @RequestMapping(value = "/judgeProductLineCode")
    public Map judgeProductLineCode(String param, Integer productLineId) {
        if (param != null) {
            String productLineCode = param;
            ProductLine productLine = new ProductLine();
            productLine.setProductLineCode(productLineCode);
            productLine.setDeleted(FieldUtil.DELETE_NO);
            List<ProductLine> productLines = productLineService.findProductLineList(productLine);
            if (productLines.size() != 0) {
                if (productLineId == null) {
                    return resultMap(false, "该产品线编号已存在");
                } else if (!productLineId.equals(productLines.get(0).getProductLineId())) {
                    return resultMap(false, "该产品线编号已存在");
                } else {
                    return resultMap(true, "");
                }
            } else {
                return resultMap(true, "");
            }
        }
        return resultMap(false, "请输入产品线编号");
    }

    @ResponseBody
    @RequestMapping(value = "/userProductTree")
    public List<Map<String, Object>> getUserProductTree() {
        List<Product> products = productService.getProductByUser(userUtils.getUserId(), 0, null, Product.CHOOSE_OPENED);
        List<Map<String, Object>> mapList = Lists.newArrayList();
        List<Integer> productLineIds = new ArrayList<Integer>();
        for (Product p : products) {
            productLineIds.add(p.getProductLineId());
            Map<String, Object> mapTop = Maps.newHashMap();
            mapTop.put("id", p.getProductId());
            mapTop.put("pId", "p" + p.getProductLineId());
            mapTop.put("open", false);
            mapTop.put("add", true);
            mapTop.put("edit", false);
            mapTop.put("name", p.getProductName());
            mapList.add(mapTop);
        }
        Integer[] ids = new Integer[productLineIds.size()];
        ids = productLineIds.toArray(ids);
        List<ProductLine> productLines = productLineService.getProductLineByIds(ids);
        for (ProductLine p : productLines) {
            productLineIds.add(p.getProductLineId());
            Map<String, Object> mapTop = Maps.newHashMap();
            mapTop.put("id", "p" + p.getProductLineId());
            mapTop.put("pId", 0);
            mapTop.put("open", true);
            mapTop.put("isParent", true);
            mapTop.put("clickAble", false);
            mapTop.put("add", true);
            mapTop.put("edit", false);
            mapTop.put("name", p.getProductLineName());
            mapList.add(mapTop);
        }
        return mapList;
    }

    @RequestMapping(value = "/productLineProducts")
    public String productLineProducts(String choose, Integer productLineId, Model model) {
        List<Product> products = productService.getProductByUserAndProductLineWithCount(UserUtils.getUserId(), productLineId, 0, choose);
        model.addAttribute("productList", products);
        return "/productLine/data/product/productListData.pagelet";
    }

    @ResponseBody
    @RequestMapping("ajax/lineInCondition")
    public List<ProductLine> lineInCondition(String key, Integer initKey) {
        if (initKey != null) {
            List<ProductLine> result = new ArrayList<ProductLine>();
            result.add(productLineService.findProductLine(initKey));
            return result;
        }
        List<ProductLine> lineList = productLineService.getUserProductLine(userUtils.getUserId());
        Integer[] ids = new Integer[lineList.size()];
        for (int i = 0; i < ids.length; i++) {
            ids[i] = lineList.get(i).getProductLineId();
        }
        return productLineService.lineInCondition(key, Integer.parseInt(configService.getConfigBySection(SystemConfig.SEARCH_CONFIG).getConfigKey()), ids);
    }
}

