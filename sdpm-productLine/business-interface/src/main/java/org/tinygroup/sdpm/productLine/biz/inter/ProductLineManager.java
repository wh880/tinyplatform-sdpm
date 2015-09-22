package org.tinygroup.sdpm.productLine.biz.inter;

import java.util.List;

import org.tinygroup.sdpm.productLine.dao.pojo.ProductLine;

public interface ProductLineManager {
	
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
	 * 根据Id删除
	 * @param productLineId
	 * @return
	 */
	int delete(Integer productLineId);
	
	/**
	 * 根据主键查询
	 * @param productLineId
	 * @return
	 */
	ProductLine find(Integer productLineId);
	
	/**
	 * 根据对象查找
	 * @param productLine
	 * @return
	 */
	List<ProductLine> findList(ProductLine productLine);
}
