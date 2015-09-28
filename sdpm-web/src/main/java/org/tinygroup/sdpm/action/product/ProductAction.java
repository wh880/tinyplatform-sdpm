package org.tinygroup.sdpm.action.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.service.ProductService;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 产品控制器
 * @author Administrator
 *
 */
@Controller
@RequestMapping("product")
public class ProductAction extends BaseController{
	
	@Autowired
	private ProductService productService;
	@RequestMapping("")
	public String productAction(HttpServletRequest request){
		List<Product> list = (List<Product>) request.getSession().getAttribute("productList");
		if(list == null){
			list = productService.findProductList(new Product(),"productId","desc");
			request.getSession().setAttribute("productList",list);
		}
		return "redirect:/product/story?"+"productId="+list.get(0).getProductId()+"&choose=1&"+request.getQueryString();
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Product product,Model model){
		System.out.println("11111111111111111111111111111111111111111111111111");
		productService.addProduct(product);
		return "/product/page/tabledemo/addProduct.page";
		
	}

}
