package org.tinygroup.sdpm.product.biz.inter;

import java.util.List;

import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.tinysqldsl.Pager;

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
	 * 批量编辑
	 * @param products
	 * @return
	 */
	int[] updateBatch(List<Product> products);
	
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
	 * 根据对象查找(排序)
	 * @param product
	 * @return
	 */
	List<Product> findList(Product product, String order,String ordertype);
	
	/**
	 * 根据对象查找(分页、排序)
	 * @param start
	 * @param limit
	 * @param product
	 * @param orderBies
	 * @return
	 */
	Pager<Product> findPager (int page ,int limit ,Product product, String order,String ordertype);
	
	
}
