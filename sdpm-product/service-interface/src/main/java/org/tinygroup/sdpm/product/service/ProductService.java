package org.tinygroup.sdpm.product.service;

import java.util.List;

import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
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
	 * 批量修改
	 * @param products
	 * @return
	 */
	int[] updateBatchProduct(List<Product> products);
	
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
	 * 根据对象查找(排序)
	 * @param product
	 * @return
	 */
	List<Product> findProductList(Product product,String columnName,boolean asc);
	
	/**
	 * 根据对象查找(排序、分页)
	 * @param product
	 * @return
	 */
	Pager<Product> findProductPager (int start ,int limit ,Product product, String oeder,String ordertype);
	
	
}
