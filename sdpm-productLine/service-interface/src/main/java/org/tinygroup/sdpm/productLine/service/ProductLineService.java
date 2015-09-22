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
	ProductLine addProductLine(ProductLine productLine);
	
	/**
	 * 编辑产品线
	 * @param productLine
	 * @return
	 */
	int updateProductLine(ProductLine productLine);
	/**
	 * 批量编辑
	 * @param productLines
	 * @return
	 */
	int[] updateBatch(List<ProductLine> productLines);
	
	/**
	 * 根据主键查询
	 * @param productLineId
	 * @return
	 */
	ProductLine findProductLine(Integer productLineId);
	
	/**
	 * 根据对象查询（排序）
	 * @param productLine
	 * @param columnName
	 * @param asc
	 * @return
	 */
	List<ProductLine> findProductLineList(ProductLine productLine,String columnName,boolean asc);
	/**
	 * 根据对象查找(分页、排序)
	 * @param start
	 * @param limit
	 * @param productLine
	 * @param columnName
	 * @param asc
	 * @return
	 */
	Pager<ProductLine> findProductLinePager(int start,int limit,ProductLine productLine,String columnName,boolean asc);
}
