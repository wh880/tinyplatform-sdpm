package org.tinygroup.sdpm.product.service;

import java.util.List;

import org.tinygroup.sdpm.common.util.ComplexSearch.SearchInfos;
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.product.dao.pojo.ProductStorySpec;
import org.tinygroup.tinysqldsl.Pager;

public interface StoryService {
	
	/**
	 * 添加需求
	 * @param story
	 * @return
	 */
	ProductStory addStory(ProductStory story,ProductStorySpec storySpec);
	/**
	 * 根据需求Id删除
	 * @param storyId
	 * @return
	 */
	Integer deleteStory(Integer storyId);
	
	/**
	 * 编辑
	 * @param story
	 * @return
	 */
	int updateStory(ProductStory story);
	/**
	 * 批量编辑
	 * @param stories
	 * @return
	 */
	int[] updateBatchStory(List<ProductStory> stories);
	/**
	 * 根据需求ID查找
	 * @param storyId
	 * @return
	 */
	ProductStory findStory(Integer storyId);
	
	/**
	 * 根据多个ID查找
	 * @param storyId
	 * @return
	 */
	List<ProductStory> findStoryList(Integer... storyId);
	
	/**
	 * 根据产品对象查找
	 * @return
	 */
	List<ProductStory> findStoryList(ProductStory story,String order,String ordertype);
	/**
	 * 分页查询
	 * @param start
	 * @param limit
	 * @param story
	 * @return
	 */
	Pager<ProductStory> findStoryPager(int start, int limit, ProductStory story,String statusCondition, SearchInfos searchInfos, String groupOperate, String columnName, boolean asc);
	
	
}
