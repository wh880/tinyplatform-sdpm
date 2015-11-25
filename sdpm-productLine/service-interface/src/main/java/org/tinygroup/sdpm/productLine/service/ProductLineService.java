package org.tinygroup.sdpm.productLine.service;

import org.tinygroup.sdpm.productLine.dao.pojo.ProductLine;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

public interface ProductLineService {
	
	/**
	 * 添加产品线
	 * @param productLine
	 * @return
	 */
	ProductLine addProductLine(ProductLine productLine);
	
	/**
	 * 查找所有产品线
	 * @param productLine
	 * @return
	 */
	List<ProductLine> findList(ProductLine productLine);
	
	/**
	 * 编辑产品线
	 * @param productLine
	 * @return
	 */
	int updateProductLine(ProductLine productLine);
	
	/**
	 * 批量编辑
	 * @param productLine
	 * @return
	 */
	int[]  updatebatchProductLine(List<ProductLine> productLine);
	
	/**
	 * 根据ID软删除
	 * @param productLineId
	 * @return
	 */
	int deleteProductLine(Integer productLineId );
	/**
	 * 根据主键查询
	 * @param productLineId
	 * @return
	 */
	ProductLine findProductLine(Integer productLineId);
	
	/**
	 * 对象查询(排序)
	 * @param productLine
	 * @param order
	 * @param ordertype
	 * @return
	 */
	List<ProductLine> findProductLineList(ProductLine productLine,String order,String ordertype);
	
	/**
	 * 分页查询(排序)
	 * @param page
	 * @param pagesize
	 * @param productLine
	 * @param order
	 * @param ordertype
	 * @return
	 */
	Pager<ProductLine> findProductLinePager(int page,int pagesize,String condition,ProductLine productLine,String order,String ordertype);

	Pager<ProductLine> findProductLinePagerInIds(int start, int limit, String condition, ProductLine productLine ,Integer[] ids, String order,String ordertype);
	
	List<ProductLine> getProductLineByIds(Integer ...ids);

	List<ProductLine> getUserProductLine(String userId);

	Integer[] getUserProductLineIds(String userId);
	
}
