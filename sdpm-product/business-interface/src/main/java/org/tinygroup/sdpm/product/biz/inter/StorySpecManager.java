package org.tinygroup.sdpm.product.biz.inter;

import java.util.List;

import org.tinygroup.sdpm.product.dao.pojo.ProductStorySpec;

public interface StorySpecManager {
	
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
	List<ProductStorySpec> findList(ProductStorySpec storySpec);
	

}
