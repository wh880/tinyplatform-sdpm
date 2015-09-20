package org.tinygroup.sdpm.product.service;

import java.util.List;

import org.tinygroup.sdpm.product.dao.pojo.ProductStorySpec;

public interface StorySpecService {
	
	/**
	 * 添加描述
	 * @param storySpec
	 * @return
	 */
	ProductStorySpec addStorySpec(ProductStorySpec storySpec);
	
	/**
	 * 编辑需求
	 * @param storySpec
	 * @return
	 */
	int updateStorySpec(ProductStorySpec storySpec);
	
	/**
	 * 根据需求Id删除
	 * @param storyId
	 * @return
	 */
	int deleteStorySpec(Integer storyId);
	
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
	ProductStorySpec findStorySpecList(ProductStorySpec storySpec);
	

}
