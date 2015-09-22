package org.tinygroup.sdpm.action.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.product.service.ProductService;
import org.tinygroup.weblayer.mvc.annotation.Controller;

/**
 * 产品控制器
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/product")
public class ProductAction extends BaseController{
	
	@Autowired
	private ProductService productService;

}
