package org.tinygroup.sdpm.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.service.ProductService;

@Component("productService")
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductService productService;
	
	public Product addProduct(Product product) {

		return productService.addProduct(product);
	}

	public int updateProduct(Product product) {

		return productService.updateProduct(product);
	}

	public int deleteProduct(Integer productId) {

		return productService.deleteProduct(productId);
	}

	public Product findProduct(Integer productId) {

		return productService.findProduct(productId);
	}

	public List<Product> findProductList(Product product) {

		return productService.findProductList(product);
	}

}
