package org.tinygroup.sdpm.product.service;

import org.tinygroup.sdpm.product.dao.pojo.ProductStorySpec;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

public interface StorySpecService {
	
	ProductStorySpec add(ProductStorySpec storySpec);
	/**
	 * 根据需求Id查找
	 * @param storyId
	 * @return
	 */
	ProductStorySpec findStorySpec(Integer storyId,Integer version);
	
	/**
	 * 根据多个ID查找
	 * @param storyspecId
	 * @return
	 */
	List<ProductStorySpec> findStorySpecList(Integer... storyspecId);
	
	
	/**
	 * 根据需求对象查找
	 * @param storyId
	 * @return
	 */
	List<ProductStorySpec> findStorySpecList(ProductStorySpec storySpec,String order,String ordertype);
	/**
	 * 分页查询（排序）
	 * @param start
	 * @param limit
	 * @param storySpec
	 * @param orderBies
	 * @return
	 */
	Pager<ProductStorySpec> findStorySpecPager(int page,int limit,ProductStorySpec storySpec,String order,String ordertype);
	
	/**
	 * 查找最新版本
	 * @param storyId
	 * @return
	 */
	int getNewStoryVersion(Integer storyId);

	Integer getMaxVersion(Integer storyId);
}
