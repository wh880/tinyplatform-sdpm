package org.tinygroup.sdpm.product.biz.inter;

import org.tinygroup.sdpm.product.dao.pojo.ProductStorySpec;

public interface StorySpecManager {
	
	/**
	 * 添加描述
	 * @param storySpec
	 * @return
	 */
	ProductStorySpec add(ProductStorySpec storySpec);
	
	/**
	 * 编辑需求
	 * @param storySpec
	 * @return
	 */
	int update(ProductStorySpec storySpec);
	
	/**
	 * 根据需求Id删除
	 * @param storyId
	 * @return
	 */
	int delete(Integer storyId);
	
	/**
	 * 根据需求Id查找
	 * @param storyId
	 * @return
	 */
	ProductStorySpec find(Integer storyId);
	
	/**
	 * 根据需求对象查找
	 * @param storyId
	 * @return
	 */
	ProductStorySpec findList(ProductStorySpec storySpec);
	

}
