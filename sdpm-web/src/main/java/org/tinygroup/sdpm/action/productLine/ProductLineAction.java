package org.tinygroup.sdpm.action.productLine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.service.ProductService;
import org.tinygroup.sdpm.productLine.dao.pojo.ProductLine;
import org.tinygroup.sdpm.productLine.service.ProductLineService;
import org.tinygroup.template.parser.grammer.TinyTemplateParser.If_directiveContext;
import org.tinygroup.tinysqldsl.Pager;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
   @RequestMapping("{type}")
    public String productline(@PathVariable(value="type")String type){
	   if("add".equals(type)){
		   return "productLine/page/tabledemo/add.page";
	   }
	   if("all".equals(type)){
		   return"productLine/page/tabledemo/list.page";
	   }
	   return null;
    	
    }
    @RequestMapping("/save")
    public String save(ProductLine productLine, Model model) {
        productLineService.addProductLine(productLine);
        return "redirect:" + "/productLine/page/tabledemo/list.page";
    }

    @RequestMapping("/update")
    public String update(ProductLine productLine) {
        productLineService.updateProductLine(productLine);
        return "redirect:" + "/productLine/page/tabledemo/list.page";
    }

    @ResponseBody
    @RequestMapping("/delete")
    public Map delete(Integer productLineId) {
        productLineService.deleteProductLine(productLineId);
        Map<String, String> map = new HashMap<String, String>();
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

    @RequestMapping("/list")
    public String list(ProductLine productLine,
                       @RequestParam(required = false, defaultValue = "1") int page,
                       @RequestParam(required = false, defaultValue = "10") int pagesize,
                       @RequestParam(required = false, defaultValue = "productLineId") String order,
                       @RequestParam(required = false, defaultValue = "asc") String ordertype, Model model) {

        Pager<ProductLine> pagerProductLine = productLineService.findProductLinePager(page, pagesize, productLine, order, ordertype);

        model.addAttribute("productLine", pagerProductLine);
        return "/productLine/data/productLinedata.pagelet";
    }

    @RequestMapping("/find/{forword}")
    public String find(@PathVariable(value = "forword") String forword, Integer productLineId, Model model, HttpServletRequest request) {
        if (productLineId == null) {
            productLineId = (Integer) request.getSession().getAttribute("sessionProductLineId");
        }
        ProductLine productLine = productLineService.findProductLine(productLineId);
        model.addAttribute("productLine", productLine);

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
        List<ProductLine> list = (List<ProductLine>) request.getSession().getAttribute("productLineList");
        if (list == null || list.size() == 0) {
            list = productLineService.findProductLineList(new ProductLine(), "productLineId", "desc");
            request.getSession().setAttribute("productLineList", list);

            if (request.getSession().getAttribute("sessionProductLineId") == null) {
                request.getSession().setAttribute("sessionProductLineId", list.size() > 0 ? list.get(0).getProductLineId() : null);
            }
        }

        return "redirect:" + adminPath + "/product";
    }

    @RequestMapping("/to")
    public String to(HttpServletRequest request) {
        List<ProductLine> list = (List<ProductLine>) request.getSession().getAttribute("productLineList");
        if (list == null || list.size() == 0) {
            list = productLineService.findProductLineList(new ProductLine(), "productLineId", "desc");
            request.getSession().setAttribute("productLineList", list);

            if (request.getSession().getAttribute("sessionProductLineId") == null) {
                request.getSession().setAttribute("sessionProductLineId", list.size() > 0 ? list.get(0).getProductLineId() : null);
            }
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
}