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
	 * 根据主键查询
	 * @param productLineId
	 * @return
	 */
	ProductLine findProductLine(Integer productLineId);
	
	/**
	 * 根据对象查找
	 * @param productLine
	 * @return
	 */
	List<ProductLine> findProductLineList(ProductLine productLine);
}
