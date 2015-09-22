package org.tinygroup.sdpm.product.biz.inter;

import java.util.List;

import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.product.dao.pojo.ProductPlan;
import org.tinygroup.sdpm.product.dao.pojo.ProductStorySpec;
import org.tinygroup.tinysqldsl.Pager;

public interface StorySpecManager {
	
	/**
	 * 根据需求Id查找
	 * @param storyId
	 * @return
	 */
	ProductStorySpec find(Integer storyId);
	
	/**
	 * 根据需求对象查找
	 * @param storySpec
	 * @param orderBies
	 * @return
	 */
	List<ProductStorySpec> findList(ProductStorySpec storySpec,String columnName,boolean asc);

	/**
	 * 分页查询
	 * @param start
	 * @param limit
	 * @param storySpec
	 * @param orderBies
	 * @return
	 */
	Pager<ProductStorySpec> findPager(int start, int limit, ProductStorySpec storySpec,String columnName,boolean asc);
	

}
