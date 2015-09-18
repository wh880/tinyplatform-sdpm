package org.tinygroup.sdpm.productLine.service;

import java.util.List;

import org.tinygroup.sdpm.productLine.dao.pojo.ProductLine;
import org.tinygroup.tinysqldsl.Pager;

public interface ProductLineService {
	
	/**
	 * 添加产品线
	 * @param productLine
	 * @return
	 */
	ProductLine add(ProductLine productLine);
	
	/**
	 * 编辑产品线
	 * @param productLine
	 * @return
	 */
	int update(ProductLine productLine);
	
	/**
	 * 根据主键查询
	 * @param productLineId
	 * @return
	 */
	ProductLine findById(Integer productLineId);
	
	/**
	 * 根据状态查找
	 * @param productLineStatus
	 * @return
	 */
	List<ProductLine> findByStatus(String productLineStatus);
	
/*	*//**
	 * 根据对象查找(分页、排序)
	 * @param productLine
	 * @param pagerPojo
	 * @return
	 *//*
	List<ProductLine> findProductLine(ProductLine productLine,PagerPojo pagerPojo);
	
	*//**
	 * 根据对象查找(分页)
	 * @param currentPage
	 * @param limit
	 * @param pagerPojo
	 * @return
	 *//*
	List<ProductLine> findProductLinePager(int currentPage,int limit,PagerPojo pagerPojo);
	
	*//**
	 * 根据对象查找(排序)
	 * @param field
	 * @param sorting
	 * @param pagerPojo
	 * @return
	 *//*
	List<ProductLine> findProductLineSort(String field,String sorting,PagerPojo pagerPojo);
	
	*//**
	 * 分页查找
	 * @param currentPage
	 * @param limit
	 * @param productLine
	 * @return
	 *//*
	Pager<ProductLine> findProductLine(int currentPage,int limit,ProductLine productLine);*/
}
