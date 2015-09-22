package org.tinygroup.sdpm.product.biz.inter;

import java.util.List;

import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.product.dao.pojo.ProductPlan;
import org.tinygroup.tinysqldsl.Pager;

public interface PlanManager {
	/**
	 * 添加计划
	 * @param plan
	 * @return
	 */
	ProductPlan add(ProductPlan plan);
	/**
	 * 编辑计划
	 * @parm plan
	 * @ruturn
	 */
	int update(ProductPlan plan);
	/**
	 * 批量编辑
	 * @param productplan
	 * @return
	 */
	int[] updateBatch(List<ProductPlan> productplan);
	/**
	 * 根据计划ID删除计划
	 * @parm plan
	 * @ruturn
	 */
	int delete(Integer planId);
	/**
	 * 根据Id查找
	 * @param planId
	 * @param oderby
	 * @return
	 */
	ProductPlan find(Integer planId);
	/**
	 * 根据对象查找(排序)
	 * @param productplan
	 * @param orderBies
	 * @return
	 */
	List<ProductPlan> findList(ProductPlan productplan,String columnName,boolean asc);
	/**
	 * 根据对象查找(分页、排序)
	 * @param start
	 * @param limit
	 * @param productplan
	 * @param orderBies
	 * @return
	 */
	Pager<ProductPlan> findPager(int start,int limit,ProductPlan productplan,String columnName,boolean asc);
	
	

}
