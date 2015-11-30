package org.tinygroup.sdpm.action.product;

import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.service.ProductService;
import org.tinygroup.sdpm.productLine.dao.pojo.ProductLine;
import org.tinygroup.sdpm.productLine.service.ProductLineService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class SessionUtil {
	
	public static List<Product> ProductRefresh(HttpServletRequest request,ProductService productService){
		HttpSession session = request.getSession();
		Product product = new Product();
		if(session.getAttribute("sessionProductLineId")!=null){
			product.setProductLineId((Integer)request.getSession().getAttribute("sessionProductLineId"));
		}else{
			product.setProductLineId(null);
		}
		List<Product> list = productService.findProductList(product,"productId","desc");
		request.getSession().setAttribute("productList",list);
		return list;
		
	}
	
	public static List<ProductLine> ProductLineRefresh(HttpServletRequest request,ProductLineService productLineService){
		HttpSession session = request.getSession();
		List<ProductLine> list = productLineService.findProductLineList(new ProductLine(), "productLineId", "desc");
		request.getSession().setAttribute("productLineList",list);
		if(session.getAttribute("sessionProductLineId")==null){
			session.setAttribute("sessionProductLineId", list.size()>0?list.get(0).getProductLineId():null);
		}
		return list;
		
	}
}
