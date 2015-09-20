package org.tinygroup.sdpm.product.service;

import java.util.List;

import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.service.pojo.PagerPojo;
import org.tinygroup.tinysqldsl.Pager;

public interface ProductService {
	/**
	 * 添加产品
	 * @param product
	 * @return
	 */
	Product addProduct(Product product);
	
	/**
	 * 修改
	 * @param product
	 * @return
	 */
	int updateProduct(Product product);
	
	/**
	 * 根据产品ID删除
	 * @param productId
	 * @return
	 */
	int deleteProduct(Integer productId);
	
	
	
	/**
	 * 根据产品ID查找
	 * @param productId
	 * @return
	 */
	Product findProduct(Integer productId);
	
	/**
	 * 根据对象查找
	 * @param product
	 * @return
	 */
	List<Product> findProductList(Product product);
	
	/**
	 * 根据产品对象查找(分页、排序)
	 * @param product
	 * @return
	 */
	/*List<Product> findProduct(PagerPojo pagerPojo, Product product);
	
	*//**
	 * 查找所有产品
	 * @return
	 *//*
	List<Product> findAll(PagerPojo pojo);
	
	*//**
	 * 根据产品线Id查找产品
	 * @param id
	 * @return
	 *//*
	List<Product> findByProductLineId(PagerPojo pagerPojo,Integer productLineId);
	
	*//**
	 * 根据产品对象分页查询
	 * @param currentPage
	 * @param limit
	 * @param product
	 * @return
	 *//*
	Pager<Product> findProduct(int currentPage,int limit ,Product product);
	
	*/
	
	
	
}
