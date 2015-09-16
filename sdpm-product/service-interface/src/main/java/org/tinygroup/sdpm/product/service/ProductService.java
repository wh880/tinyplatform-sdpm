package org.tinygroup.sdpm.product.service;

import java.util.List;

import org.tinygroup.sdpm.product.dao.pojo.Product;

public interface ProductService {
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
	int etid(Product product);
	
	/**
	 * 根据产品ID删除
	 * @param id
	 * @return
	 */
	int deleteById(Integer id);
	
	/**
	 * 根据产品对象删除
	 * @param product
	 * @return
	 */
	int deleteByProduct(Product product);
	
	/**
	 * 批量删除
	 * @param products
	 * @return
	 */
	int batchDelete(List<Product> products);
	
	/**
	 * 根据产品线Id删除
	 * @param integer
	 * @return
	 */
	int deleteByProductLineId(Integer id);
	
	/**
	 * 根据产品ID查找
	 * @param id
	 * @return
	 */
	Product findById(Integer id);
	
	/**
	 * 根据产品对象查找
	 * @param product
	 * @return
	 */
	List<Product> findByProduct(Product product);
	
	/**
	 * 查找所有产品
	 * @return
	 */
	List<Product> findAll();
	
	/**
	 * 根据产品线Id查找产品
	 * @param id
	 * @return
	 */
	List<Product> findByProductLineId(Integer id);
	
	
	
}
