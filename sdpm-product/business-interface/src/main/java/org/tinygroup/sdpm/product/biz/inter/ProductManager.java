package org.tinygroup.sdpm.product.biz.inter;

import java.util.List;

import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.service.PagerPojo;
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
	 * 根据产品ID删除
	 * @param id
	 * @return
	 */
	int deleteById(Integer productId);
	
	/**
	 * 根据产品Id批量删除
	 * @param productIds
	 * @return
	 */
	int deleteByProductIds(Integer... productIds);
	
	/**
	 * 根据产品对象删除
	 * @param product
	 * @return
	 */
	int deleteByProduct(Product product);
	
	/**
	 * 根据产品批量删除
	 * @param products
	 * @return
	 */
	int[] deleteBatch(List<Product> products);
	
	
	
	/**
	 * 根据产品线Id删除
	 * @param integer
	 * @return
	 */
	int deleteByProductLineId(Integer productLineId);
	
	/**
	 * 根据产品ID查找
	 * @param id
	 * @return
	 */
	Product findById(Integer productId);
	
	/**
	 * 根据对象查找
	 * @param product
	 * @return
	 */
	List<Product> findProduct(Product product);
	
	/**
	 * 根据产品对象查找(分页、排序)
	 * @param product
	 * @return
	 */
	List<Product> findProduct(PagerPojo pagerPojo,Product product);
	
	/**
	 * 查找所有产品
	 * @return
	 */
	List<Product> findAll(PagerPojo pojo);
	
	/**
	 * 根据产品线Id查找产品
	 * @param id
	 * @return
	 */
	List<Product> findByProductLineId(PagerPojo pagerPojo,Integer productLineId);
	
	/**
	 * 根据产品对象分页查询
	 * @param currentPage
	 * @param limit
	 * @param product
	 * @return
	 */
	Pager<Product> findProduct(int currentPage,int limit ,Product product);
	
	
	
}
