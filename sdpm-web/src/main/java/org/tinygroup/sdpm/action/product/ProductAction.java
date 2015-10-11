package org.tinygroup.sdpm.action.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.sdpm.org.service.inter.UserService;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.dao.pojo.ProductPlan;
import org.tinygroup.sdpm.product.service.ProductService;
import org.tinygroup.sdpm.system.dao.pojo.SystemAction;
import org.tinygroup.sdpm.system.dao.pojo.SystemHistory;
import org.tinygroup.sdpm.system.service.inter.ActionService;
import org.tinygroup.sdpm.system.service.inter.HistoryService;
import org.tinygroup.tinysqldsl.Pager;
import org.tinygroup.weblayer.WebContext;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

/**
 * 产品控制器
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/product")
public class ProductAction  extends BaseController{

	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserService  userService;
	
	@Autowired
	private HistoryService historyService;
	
	@Autowired
	private ActionService actionService;

	@RequestMapping("")
	public String productAction(HttpServletRequest request, WebContext webContext){
		List<Product> list = (List<Product>) request.getSession().getAttribute("productList");
		String oldUrl = webContext.get("oldUrl");
		if(list == null|| list.size()==0){
			Product product = new Product();
			if(request.getSession().getAttribute("sessionProductLineId")!=null){
				product.setProductLineId((Integer)request.getSession().getAttribute("sessionProductLineId"));
			}
			list = productService.findProductList(product,"productId","desc");
			request.getSession().setAttribute("productList",list);
			
			if(request.getSession().getAttribute("sessionProductId")==null){
				request.getSession().setAttribute("sessionProductId",list.size()>0?list.get(0).getProductId():null);
			}
		}
		
		return "redirect:/product/story?"+(list.size()>0?("productId="+list.get(0).getProductId()):"")+"&choose=1"+(request.getQueryString()==null?"":("&"+request.getQueryString()));
	}	
	
	@RequestMapping("/save")
	public String save(Product product, Model model) {
		
		productService.addProduct(product);
		return "redirect:" + "/product/page/tabledemo/product-listall.page";

	}
	
	@RequestMapping("/update")
	public String update(Product product){

		productService.updateProduct(product);
		
		return "redirect:" + "/product/page/tabledemo/product-listall.page";
	}
	
	@ResponseBody
	@RequestMapping("sessionset")
	public boolean sessionSet(Integer productId,HttpServletRequest request){
		if(productId!=null){
			request.getSession().setAttribute("sessionProductId", productId);
			return true;
		}else{
			request.getSession().removeAttribute("sessionProductId");
			return false;
		}
		
	}
	
	@RequestMapping("/delete")
	public String delete(Integer productId){
		
		productService.deleteProduct(productId);
		return "redirect:" + "/product/page/tabledemo/product-listall.page";
	}
	
	@RequestMapping("/find")
	public String find(Integer productId,Model model){
		
		Product product = productService.findProduct(productId);
		model.addAttribute("product", product);
		return "/product/page/tabledemo/product-module-editor.page";
	}
	
	@RequestMapping("/find/{forward}")
	public String find(@PathVariable(value="forward")String forward,Integer productId,Model model,HttpServletRequest request){
		
		if(productId==null){
			productId = (Integer) request.getSession().getAttribute("sessionProductId");
		}
		
		Product product = productService.findProduct(productId);
		SystemHistory history = new SystemHistory();
		List<SystemHistory> histories = historyService.find(history);
		SystemAction action = new SystemAction();
		action.setActionObjectType("story");
		List<SystemAction> actions = actionService.find(action);
		model.addAttribute("action", actions);
		model.addAttribute("product", product);
		model.addAttribute("history", histories);
		
		if ("overview".equals(forward)) {
			return "/product/page/project/overview.page";
		}else if ("baseinfo".equals(forward)) {
			return "/product/page/tabledemo/baseinfo.pagelet";
		}
		return "";
	}

	@RequestMapping("/list")
	public String list(Product product,
			@RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "10") int pagesize,
			@RequestParam(required = false, defaultValue = "productId")String order, 
			@RequestParam(required = false, defaultValue = "asc")String ordertype, Model model) {

		Pager<Product> pagerProduct = productService.findProductPager(page, pagesize, product, order,ordertype);
		
		model.addAttribute("pagerProduct", pagerProduct);
		return "/product/data/allproductdata.pagelet";
	}
	
	@RequestMapping("/findManager")
	public String findManager(Integer productId,Model model){
		
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
    public List<Product> findProduct(Product product){
    	
    	List<Product> list = productService.findProductList(product);
    	
    	return list;
    }
}
