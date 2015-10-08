package org.tinygroup.sdpm.action.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.spi.http.HttpContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.service.ProductService;
import org.tinygroup.tinysqldsl.Pager;
import org.tinygroup.weblayer.WebContext;

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

	@RequestMapping("")
	public String productAction(HttpServletRequest request, WebContext webContext){
		List<Product> list = (List<Product>) request.getSession().getAttribute("productList");
		String oldUrl = webContext.get("oldUrl");
		if(list == null|| list.size()==0){
			list = productService.findProductList(new Product(),"productId","desc");
			request.getSession().setAttribute("productList",list);
		}
		
		return "redirect:/product/story?"+(list.size()>0?("productId="+list.get(0).getProductId()):"")+"&choose=1"+(request.getQueryString()==null?"":("&"+request.getQueryString()));
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

}
