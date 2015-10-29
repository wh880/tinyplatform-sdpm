package org.tinygroup.sdpm.action.productLine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.product.dao.impl.FieldUtil;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.dao.pojo.ProductAndLine;
import org.tinygroup.sdpm.product.service.ProductService;
import org.tinygroup.sdpm.productLine.dao.pojo.ProductLine;
import org.tinygroup.sdpm.productLine.service.ProductLineService;
import org.tinygroup.sdpm.project.dao.pojo.ProjectBuild;
import org.tinygroup.sdpm.project.service.inter.BuildService;
import org.tinygroup.sdpm.system.dao.pojo.SystemAction;
import org.tinygroup.sdpm.util.LogUtil;
import org.tinygroup.sdpm.util.ProductUtils;
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

    @RequestMapping("/content/{type}")
    public String productline(@PathVariable(value = "type") String type) {
        if ("add".equals(type)) {

            return "productLine/page/tabledemo/add.page";
        }
        if ("all".equals(type)) {
            return "productLine/page/tabledemo/list.page";
        }
        return null;

    }

    @RequestMapping("/save")
    public String save(ProductLine productLine, SystemAction systemAction,HttpServletRequest request) {
        productLine.setProductLineCreatedBy(UserUtils.getUserId());
        productLine.setProductLineCreatedDate(new Date());
        productLine.setProductLineStatus("0");
        ProductLine productLine1 = productLineService.addProductLine(productLine);
        ProductUtils.removeProductLineList();
        
        LogUtil.logWithComment(LogUtil.LogOperateObject.PRODUCTLINE
                , LogUtil.LogAction.OPENED
                ,String.valueOf(productLine1.getProductLineId())
                ,UserUtils.getUserId()
                ,null
                ,null
                ,null
                ,null
                , systemAction.getActionComment());

        return "redirect:" + "/a/productLine/content/all";
    }

    @RequestMapping("/update")
    public String update(ProductLine productLine) {
        ProductLine productLineOld = productLineService.findProductLine(productLine.getProductLineId());
        productLineService.updateProductLine(productLine);
        LogUtil.logWithComment(LogUtil.LogOperateObject.PRODUCTLINE,
                LogUtil.LogAction.EDITED,
                String.valueOf(productLine.getProductLineId()),
                UserUtils.getUserId(),
                null,
                null,
                productLineOld,
                productLine,
                productLine.getProductLineSpec());
        return "redirect:" + "/a/productLine/to";
    }



    @RequestMapping("/edit")
    public String edit(ProductLine productLine){
        productLineService.updateProductLine(productLine);
        return "/productLine/page/tabledemo/update.page";
    }

    @ResponseBody
    @RequestMapping("/delete")
    public Map delete(Integer productLineId, SystemAction systemAction) {
        Product product = new Product();
        product.setProductLineId(productLineId);
        product.setDeleted(0);
        List<Product> products = productService.findProductList(product);
        for(Product product1 : products){
            product1.setDeleted(1);
            productService.updateProduct(product1);
            LogUtil.logWithComment(LogUtil.LogOperateObject.PRODUCT,
                    LogUtil.LogAction.DELETED,
                    String.valueOf(product1.getProductId()),
                    UserUtils.getUserId(),
                    null,
                    null,
                    null,
                    null,
                    systemAction.getActionComment());
        }
        productLineService.deleteProductLine(productLineId);
        ProductUtils.removeProductLineList();
        ProductUtils.removeProductList(String.valueOf(productLineId));
        Map<String, String> map = new HashMap<String, String>();
        LogUtil.logWithComment(LogUtil.LogOperateObject.PRODUCTLINE,
                LogUtil.LogAction.DELETED,
                String.valueOf(productLineId),
                UserUtils.getUserId(),
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
        return "/productLine/page/tabledemo/update.page";
    }


    @RequestMapping("/shut")
    public String shut(SystemAction systemAction, ProductLine productLine) {
        ProductLine productLine1 = productLineService.findProductLine(productLine.getProductLineId());
        productLine.setDeleted(FieldUtil.DELETE_YES);
        productLineService.updateProductLine(productLine);
        ProductUtils.removeProductLineList();
        LogUtil.logWithComment(LogUtil.LogOperateObject.PRODUCTLINE,
                LogUtil.LogAction.CLOSED,
                String.valueOf(productLine.getProductLineId()),
                UserUtils.getUserId(),
                String.valueOf(productLine.getProductLineId()),
                null,
                productLine1,
                productLine,
                systemAction.getActionComment());
        return "redirect:" + "/productLine/page/tabledemo/list.page";
    }

    @RequestMapping("/list")
    public String list(ProductLine productLine, Integer start, Integer pagesize,
                       @RequestParam(required = false, defaultValue = "productLineId") String order,
                       @RequestParam(required = false, defaultValue = "asc") String ordertype,Integer status, Model model) {
        String condition = getStatusCondition(status);
        
        Pager<ProductLine> pagerProductLine = productLineService.findProductLinePager(start, pagesize,condition, productLine, order, ordertype);

        model.addAttribute("productLine", pagerProductLine);
        return "/productLine/data/productLinedata.pagelet";
    }

    @RequestMapping("/find/{forword}")
    public String find(@CookieValue("cookieProductLineId") String cookieProductLineId,
            @PathVariable(  value = "forword") String forword,Integer productId,Integer productLineId, Model model, HttpServletRequest request) {

    	if (productLineId == null) {
    		productLineId = Integer.parseInt(cookieProductLineId);
        }
    	
        ProductLine productLine = productLineService.findProductLine(productLineId);
        List<String> lineNameList = productService.getProductNameByLineId(productLineId);
        
        model.addAttribute("productLine", productLine);
        model.addAttribute("lineNameList", lineNameList);

        if ("overview".equals(forword)) {
            return "/productLine/page/project/overview.page";
        } else if ("productLineDetail".equals(forword)) {
            return "/productLine/page/tabledemo/other-information.pagelet";
        }
        return "";
    }

    @ResponseBody
    @RequestMapping("sessionset")
    public boolean sessionSet(Integer productLineId, HttpServletRequest request) {

        if (productLineId != null) {
            request.getSession().setAttribute("sessionProductLineId", productLineId);
            Product product = new Product();
            product.setProductLineId(productLineId);
            List<Product> list = productService.findProductList(product, "productId", "desc");
            if (list.size() > 0) {
                request.getSession().setAttribute("sessionProductId", list.get(0).getProductId());
            }
            request.getSession().setAttribute("productList", list);
            return true;
        } else {
            request.getSession().removeAttribute("sessionProductLineId");
            return false;
        }

    }

    @RequestMapping("/toProduct")
    public String productLineAction(HttpServletRequest request) {
        List<ProductLine> list = ProductUtils.getProductLineList();
//        if (list == null || list.size() == 0) {
//            list = productLineService.findProductLineList(new ProductLine(), "productLineId", "desc");
//            if(list.size()==0||list==null){
//            	return "redirect:" + adminPath + "/productLine/add";
//            }
//            request.getSession().setAttribute("productLineList", list);
//
//            if (request.getSession().getAttribute("sessionProductLineId") == null) {
//                request.getSession().setAttribute("sessionProductLineId", list.size() > 0 ? list.get(0).getProductLineId() : null);
//            }
//        }

        return "redirect:" + adminPath + "/product";
    }

    @RequestMapping("/to")
    public String to(HttpServletRequest request,Model model) {
        List<ProductLine> list = ProductUtils.getProductLineList();
//        if (list == null || list.size() == 0) {
//            list = productLineService.findProductLineList(new ProductLine(), "productLineId", "desc");
//            request.getSession().setAttribute("productLineList", list);
//
//            if (request.getSession().getAttribute("sessionProductLineId") == null) {
//                request.getSession().setAttribute("sessionProductLineId", list.size() > 0 ? list.get(0).getProductLineId() : null);
//            }
//        }
        String query = request.getQueryString();
        if(StringUtil.isBlank(query)||!query.contains("status")){
            model.addAttribute("status",1);
        }
        return "/productLine/page/project/productLine.page";
    }

    @RequestMapping("/listProduct")
    public String listProduct(HttpServletRequest request) {
    /*	int productLine = -1;
        if(request.getSession().getAttribute("sessionProductLineId")!=null){
			ProductLineId = (Integer)request.getSession().getAttribute("sessionProductLineId");
		}
		ProductLine productLine = productLineService.findProductLine(ProductLineId);
		model.addAttribute("ProductLine",ProductLine);
	}*/
        return "/productLine/page/project/productLine.page";

    }

    @ResponseBody
    @RequestMapping("/productLineList")
    public List<ProductLine> findProductLineList(ProductLine productLine) {

        List<ProductLine> list = productLineService.findList(new ProductLine());

        return list;
    }


    @ResponseBody
    @RequestMapping("/treeData")
    public List data(String check) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Product product = new Product();
        product.setDeleted(FieldUtil.DELETE_NO);
        List<ProductAndLine> productLists = productService.getProductAndLine(product);
        ProductLine productLine = new ProductLine();
        productLine.setDeleted(FieldUtil.DELETE_NO);
        List<ProductLine> productLines = productLineService.findList(productLine);
        ProjectBuild projectBuild = new ProjectBuild();
        projectBuild.setBuildDeleted(FieldUtil.DELETE_NO_S);
        List<ProjectBuild> projectBuilds = buildService.findListBuild(projectBuild);
        for(int i = 0; i<productLists.size();){
            if(productLists.get(i).getProductLineId()!=null&&productLists.get(i).getProductLineId()>0&&!productLines.contains(productLists.get(i).getProductLineId())){
                productLists.remove(productLists.get(i));
                continue;
            }
            i++;
        }
        for(int i = 0; i<projectBuilds.size();){
            if(!productLines.contains(projectBuilds.get(i).getBuildProduct())){
                productLists.remove(projectBuilds.get(i));
                continue;
            }
            i++;
        }
        for (ProductLine d : productLines) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", "p" + d.getProductLineId());
            map.put("pId", 0);
            map.put("name", d.getProductLineName());
            map.put("open", true);
            list.add(map);
        }
        for (ProductAndLine d : productLists) {
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
    public String totree(String treeId,Model model){

         if (!("".equals(treeId)||treeId==null)){
             String prefix = treeId.substring(0,1);
             if ("p".equals(prefix)){
                 model.addAttribute("productLineId",treeId.substring(1));
                 model.addAttribute("type","product");
             }
             if ("v".equals(prefix)){
                 model.addAttribute("buildProduct",treeId.substring(1));
                 model.addAttribute("type","build");
             }
         }



         return "/productLine/page/project/productLine.page";
    }


    @ResponseBody
    @RequestMapping("/overviewTree")
    public List overview(Integer productLineId) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        ProductLine productLine = null;
        if(productLineId!=null&&productLineId>0){
            productLine = productLineService.findProductLine(productLineId);
        }
        List<Product> productList = null;
        if(productLine!=null){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", "p" + productLine.getProductLineId());
            map.put("pId", 0);
            map.put("name", productLine.getProductLineName());
            map.put("open", true);
            map.put("clickAble", false);
            list.add(map);
            Product product = new Product();
            product.setProductLineId(productLine.getProductLineId());
            productList = productService.findProductList(product);
        }
        for (Product d : productList) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", "v" + d.getProductId());
            map.put("pId", "p" + productLine.getProductLineId());
            map.put("name", d.getProductName());
            map.put("open", true);
            map.put("clickAble", false);
            list.add(map);
        }

        return list;
    }

    private String getStatusCondition(Integer status){
        if(status==null||status<1)return "";
        switch (status){
            case 1:return "";
            case 2:return " product_line_owner = '"+UserUtils.getUserId()+"' ";
            case 3:return " product_line_quality_manager = '"+UserUtils.getUserId()+"' ";
            case 4:return " product_line_delivery_manager = '"+UserUtils.getUserId()+"' ";
        }
        return "";
    }

}

