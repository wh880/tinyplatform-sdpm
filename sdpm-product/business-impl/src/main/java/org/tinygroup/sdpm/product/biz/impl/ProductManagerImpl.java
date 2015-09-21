package org.tinygroup.sdpm.product.biz.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.product.biz.inter.ProductManager;
import org.tinygroup.sdpm.product.dao.ProductDao;
import org.tinygroup.sdpm.product.dao.pojo.Product;

@Service
@Transactional
public class ProductManagerImpl implements ProductManager{
	
	@Autowired
	private ProductDao productDao;
	
	public Product add(Product product) {

		return productDao.add(product);
	}

	public int update(Product product) {

		return productDao.edit(product);
	}

	public int delete(Integer productId) {
		
		Product product = new Product();
		product.setProductId(productId);
		product.setDeleted(Product.DELETE_YES);
		return productDao.edit(product);
	}

	public Product find(Integer productId) {

		return productDao.getByKey(productId);
	}

	public List<Product> findList(Product product) {

		return productDao.query(product);
	}

}
