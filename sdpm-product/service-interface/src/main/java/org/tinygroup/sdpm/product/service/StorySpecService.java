package org.tinygroup.sdpm.product.service;

import java.util.List;

import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.product.dao.pojo.ProductPlan;
import org.tinygroup.sdpm.product.dao.pojo.ProductStorySpec;
import org.tinygroup.tinysqldsl.Pager;

public interface StorySpecService {
	
	/**
	 * 根据需求Id查找
	 * @param storyId
	 * @return
	 */
	ProductStorySpec findStorySpec(Integer storyId);
	
	
	/**
	 * 根据需求对象查找
	 * @param storyId
	 * @return
	 */
	List<ProductStorySpec> findStorySpecList(ProductStorySpec storySpec,String columnName,boolean asc);
	/**
	 * 分页查询（排序）
	 * @param start
	 * @param limit
	 * @param storySpec
	 * @param orderBies
	 * @return
	 */
	Pager<ProductStorySpec> findStorySpecPager(int start,int limit,ProductStorySpec storySpec,String columnName,boolean asc);
	
}
