package org.tinygroup.sdpm.action.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.service.ProductService;

public class SessionUtil {
	
	public static List<Product> ProductRefresh(HttpServletRequest request,ProductService productService){
		HttpSession session = request.getSession();
		Product product = new Product();
		if(session.getAttribute("sessionProductLineId")!=null){
			product.setProductLineId((Integer)request.getSession().getAttribute("sessionProductLineId"));
		}
		List<Product> list = productService.findProductList(product,"productId","desc");
		request.getSession().setAttribute("productList",list);
		return list;
		
	}
}
