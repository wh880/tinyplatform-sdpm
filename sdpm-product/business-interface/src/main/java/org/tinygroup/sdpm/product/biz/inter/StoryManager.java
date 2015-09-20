package org.tinygroup.sdpm.product.biz.inter;

import java.util.List;

import org.tinygroup.sdpm.product.dao.pojo.ProductStory;

public interface StoryManager {
	
	/**
	 * 添加需求
	 * @param story
	 * @return
	 */
	ProductStory add(ProductStory story);
	/**
	 * 根据需求Id删除
	 * @param storyId
	 * @return
	 */
	int delete(Integer storyId);
	
	/**
	 * 编辑
	 * @param story
	 * @return
	 */
	int update(ProductStory story);
	
	

	
	/**
	 * 根据需求ID查找
	 * @param storyId
	 * @return
	 */
	ProductStory find(Integer storyId);
	
	/**
	 * 根据产品对象查找
	 * @param productId
	 * @return
	 */
	List<ProductStory> findList(ProductStory story);
	
	
}
