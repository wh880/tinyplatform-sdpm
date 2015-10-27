package org.tinygroup.sdpm.action.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.sdpm.org.service.inter.UserService;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.service.ProductService;
import org.tinygroup.sdpm.productLine.dao.pojo.ProductLine;
import org.tinygroup.sdpm.productLine.service.ProductLineService;
import org.tinygroup.sdpm.system.dao.pojo.SystemAction;
import org.tinygroup.sdpm.system.dao.pojo.SystemHistory;
import org.tinygroup.sdpm.system.service.inter.ActionService;
import org.tinygroup.sdpm.system.service.inter.HistoryService;
import org.tinygroup.sdpm.util.CmsUtils;
import org.tinygroup.sdpm.util.LogUtil;
import org.tinygroup.sdpm.util.UserUtils;
import org.tinygroup.tinysqldsl.Pager;
import org.tinygroup.weblayer.WebContext;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * 产品控制器
 *
 * @author Administrator
 */
@Controller
@RequestMapping("/a/product")
public class ProductAction extends BaseController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private ActionService actionService;

    @Autowired
    private ProductLineService productLineService;


    @RequestMapping("/{forward}/content")
    public String doc(@CookieValue(value = "cookieProductId") String cookieProductId,@PathVariable(value = "forward") String forward, HttpServletRequest request, Model model) {

        Product product = productService.findProduct(Integer.parseInt(cookieProductId));
        model.addAttribute("product", product);
        if ("doc".equals(forward)) {
            return "/product/page/project/archive-list.page";
        } else if ("project".equals(forward)) {
            return "/product/page/project/product-project-list.page";
        } else if ("dynamic".equals(forward)) {
            return "/product/page/project/dymanicdata.page";
        }
        return "";
    }



    @RequestMapping("")
    public String productAction(HttpServletRequest request, WebContext webContext) {
        return "redirect:"+adminPath+"/product/story?choose=1" + (request.getQueryString() == null ? "" : ("&" + request.getQueryString()));
    }


    @RequestMapping("/save")
    public String save(@CookieValue(value = "cookieProductLineId") String cookieProductLineId,Product product,SystemAction systemAction, HttpServletRequest request) {

        product.setProductLineId(Integer.parseInt(cookieProductLineId));
        product.setProductCreatedBy(UserUtils.getUserId());
        product.setProductCreatedDate(new Date());
        product.setProductStatus("0");
        
        product = productService.addProduct(product);
        CmsUtils.removeProductList();
        CmsUtils.removeProductList(String.valueOf(product.getProductLineId()));
        LogUtil.logWithComment(LogUtil.LogOperateObject.PRODUCT,
                LogUtil.LogAction.OPENED,
                String.valueOf(product.getProductId()),
                UserUtils.getUserId(),
                null,
                null,
                null,
                null,
                systemAction.getActionComment());

        return "redirect:" + adminPath + "/product/allproduct/addition";

    }

    @RequestMapping("/update")
    public String update(Product product, HttpServletRequest request, SystemAction systemAction) throws IOException {
        Product product1 = productService.findProduct(product.getProductId());
        productService.updateProduct(product);
        CmsUtils.removeProductList();
        CmsUtils.removeProductList(String.valueOf(product.getProductLineId()));
        LogUtil.logWithComment(LogUtil.LogOperateObject.PRODUCT,
                LogUtil.LogAction.EDITED,
                String.valueOf(product.getProductId()),
                UserUtils.getUserId(),
                null,
                null,
                product1,
                product,
                systemAction.getActionComment());

        return "redirect:" + "/product/page/tabledemo/product-listall.page";
    }

    @RequestMapping("/edit")
    public String edit(Product product, HttpServletRequest request) {

        productService.updateProduct(product);
        CmsUtils.removeProductList();
        CmsUtils.removeProductList(String.valueOf(product.getProductLineId()));
        return "redirect:" + adminPath + "/product/find/overview?productId=" + product.getProductId();
    }

    @ResponseBody
    @RequestMapping("/sessionset")
    public boolean sessionSet(Integer productId, HttpServletRequest request) {
        if (productId != null) {
            request.getSession().setAttribute("sessionProductId", productId);
            return true;
        } else {
            request.getSession().removeAttribute("sessionProductId");
            return false;
        }

    }

    @RequestMapping("/delete")
    public String delete(Integer productId, HttpServletRequest request,SystemAction systemAction) {
        Product product1 = productService.findProduct(productId);
        productService.deleteProduct(productId);
        Product product = productService.findProduct(productId);
        CmsUtils.removeProductList();
        CmsUtils.removeProductList(String.valueOf(product.getProductLineId()));
        LogUtil.logWithComment(LogUtil.LogOperateObject.PRODUCT,
                LogUtil.LogAction.DELETED,
                String.valueOf(productId),
                UserUtils.getUserId(),
                null,
                null,
                product1,
                product,
                systemAction.getActionComment());


        return "redirect:" + "/product/page/tabledemo/product-listall.page";
    }

    @RequestMapping("/find")
    public String find(Integer productId, Model model) {

        Product product = productService.findProduct(productId);
        model.addAttribute("product", product);
        return "/product/page/tabledemo/product-module-editor.page";
    }

    @RequestMapping("/find/{forward}")
    public String find(@CookieValue(value = "cookieProductId") String cookieProductId,
            @PathVariable(value = "forward") String forward, Integer productId, Model model, HttpServletRequest request) {
        if("close".equals(forward))return "/product/page/tabledemo/overview-close.pagelet";
        Product product = productService.findProduct(Integer.parseInt(cookieProductId));
        
        SystemHistory history = new SystemHistory();
        List<SystemHistory> histories = historyService.find(history);
        SystemAction action = new SystemAction();
        action.setActionObjectType("story");
       // List<SystemAction> actions = actionService.find(action);
       // model.addAttribute("action", actions);
        model.addAttribute("product", product);
        model.addAttribute("history", histories);

        if ("overview".equals(forward)) {
        	
            return "/product/page/project/overview.page";
        } else if ("baseinfo".equals(forward)) {
            return "/product/page/tabledemo/baseinfo.pagelet";
        }
        return "";
    }

    @RequestMapping("/list")
    public String list(Product product,String treeId,
                       @RequestParam(required = false, defaultValue = "1") int page,
                       @RequestParam(required = false, defaultValue = "10") int pagesize,
                       @RequestParam(required = false, defaultValue = "productId") String order,
                       @RequestParam(required = false, defaultValue = "asc") String ordertype, Model model) {

        Pager<Product> pagerProduct = productService.findProductPager(page, pagesize, product, order, ordertype);

        model.addAttribute("pagerProduct", pagerProduct);
        return  "/product/data/allproductdata.pagelet";
    }

    @RequestMapping("/findManager")
    public String findManager(Integer productId, Model model) {

        OrgUser productOwner = userService.findUser(productService.findProduct(productId).getProductOwner().toString());
        OrgUser productQualityManager = userService.findUser(productService.findProduct(productId).getProductQualityManager().toString());
        OrgUser productDeliveryManager = userService.findUser(productService.findProduct(productId).getProductDeliveryManager().toString());

        model.addAttribute("productOwner", productOwner);
        model.addAttribute("productQualityManager", productQualityManager);
        model.addAttribute("productDeliveryManager", productDeliveryManager);

        return "/organization/userbaseinfo.pagelet";
    }

    @ResponseBody
    @RequestMapping("/productList")
    public List<Product> findProduct(Product product) {

        List<Product> list = productService.findProductList(product);

        return list;
    }


    @RequestMapping("/{forward}/addition")
    public String addpro(@CookieValue(value = "cookieProductLineId") String cookieProductLineId,
            @PathVariable(value = "forward") String forward,HttpServletRequest request, Model model) {
        Integer productLineId = Integer.parseInt(cookieProductLineId);
		if ("addproduct".equals(forward)) {
			return "/product/page/tabledemo/addProduct.page";
		} else if ("allproduct".equals(forward)) {
            return "/product/page/tabledemo/product-listall.page";
        }
		return "";
    }

    @RequestMapping("/addDoc")
    public String addDoc(@CookieValue(value = "cookieProductId") String cookieProductId,HttpServletRequest request, Model model) {

        Product product = productService.findProduct(Integer.parseInt(cookieProductId));
        model.addAttribute("product", product);
       return  "/product/page/tabledemo/add-doc.page";
    }


}
