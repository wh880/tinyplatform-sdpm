package org.tinygroup.sdpm.action.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.service.ProductService;
import org.tinygroup.tinysqldsl.Pager;

/**
 * 产品控制器
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/product")
public class ProductAction {

	@Autowired
	private ProductService productService;

	@RequestMapping("/save")
	public String save(Product product, Model model) {
		productService.addProduct(product);
		return "/product/page/tabledemo/addProduct.page";

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
