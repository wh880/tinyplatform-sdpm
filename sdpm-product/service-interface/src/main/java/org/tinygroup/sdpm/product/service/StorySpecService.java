package org.tinygroup.sdpm.product.service;

import java.util.List;

import org.tinygroup.sdpm.product.dao.pojo.ProductStorySpec;

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
	List<ProductStorySpec> findStorySpecList(ProductStorySpec storySpec);
	

}
