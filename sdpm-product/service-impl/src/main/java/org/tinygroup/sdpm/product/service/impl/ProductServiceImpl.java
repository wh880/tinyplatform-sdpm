package org.tinygroup.sdpm.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.product.biz.inter.ProductManager;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.service.ProductService;

@Component()
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductManager productManager;
	
	public Product addProduct(Product product) {

		return productManager.add(product);
	}

	public int updateProduct(Product product) {

		return productManager.update(product);
	}

	public int deleteProduct(Integer productId) {

		return productManager.delete(productId);
	}

	public Product findProduct(Integer productId) {

		return productManager.find(productId);
	}

	public List<Product> findProductList(Product product) {

		return productManager.findList(product);
	}

}
