package org.tinygroup.sdpm.product.biz.inter;

import java.util.List;

import org.tinygroup.sdpm.product.dao.pojo.Product;

public interface ProductManager {
	/**
	 * 添加产品
	 * @param product
	 * @return
	 */
	Product add(Product product);
	
	/**
	 * 修改
	 * @param product
	 * @return
	 */
	int update(Product product);
	
	/**
	 * 根据产品ID删除
	 * @param productId
	 * @return
	 */
	int delete(Integer productId);
	
	
	
	/**
	 * 根据产品ID查找
	 * @param productId
	 * @return
	 */
	Product find(Integer productId);
	
	/**
	 * 根据对象查找
	 * @param product
	 * @return
	 */
	List<Product> findList(Product product);
	
	
}
