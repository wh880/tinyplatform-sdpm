package org.tinygroup.sdpm.action.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.service.ProductService;

/**
 * 产品控制器
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/product")
public class ProductAction{
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping("/save")
	public String save(Product product,Model model){
		System.out.println("11111111111111111111111111111111111111111111111111");
		productService.addProduct(product);
		return "/product/page/tabledemo/addProduct.page";
		
	}

}
