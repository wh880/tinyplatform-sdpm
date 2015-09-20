package org.tinygroup.sdpm.product.service;

import java.util.List;

import org.tinygroup.sdpm.product.dao.pojo.ProductStory;

public interface StoryService {
	
	/**
	 * 添加需求
	 * @param story
	 * @return
	 */
	ProductStory addStory(ProductStory story);
	/**
	 * 根据需求Id删除
	 * @param storyId
	 * @return
	 */
	int deleteStory(Integer storyId);
	
	/**
	 * 编辑
	 * @param story
	 * @return
	 */
	int updateStory(ProductStory story);
	
	

	
	/**
	 * 根据需求ID查找
	 * @param storyId
	 * @return
	 */
	ProductStory findStory(Integer storyId);
	
	/**
	 * 根据产品对象查找
	 * @param productId
	 * @return
	 */
	List<ProductStory> findStoryList(ProductStory story);
	
	
}
