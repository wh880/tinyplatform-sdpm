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
	int edit(ProductLine productLine);
	
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
	
	/**
	 * 分页查找
	 * @param currentPage
	 * @param limit
	 * @param productLine
	 * @return
	 */
	Pager<ProductLine> findProductLine(int currentPage,int limit,ProductLine productLine);
}
