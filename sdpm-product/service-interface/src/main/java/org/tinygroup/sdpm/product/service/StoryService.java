package org.tinygroup.sdpm.product.service;

import java.util.List;

import org.tinygroup.sdpm.common.util.ComplexSearch.SearchInfos;
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.product.dao.pojo.ProductStorySpec;
import org.tinygroup.sdpm.product.dao.pojo.StoryCount;
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
	
/*	Pager<ProductStory> findUserPaging(Integer start, Integer limit, ProductStory story);
*/	
	/**
	 * 根据多个ID查找
	 * @param storyId
	 * @return
	 */
	List<ProductStory> findStoryList(Integer... storyId);
	/**
	 * 根据对象查找
	 * @param story
     * @return
     */
	List<ProductStory> findStoryList(ProductStory story);
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
	
	/**
	 * 产品需求数量分类
	 * @param story
	 * @return
	 */
	List<StoryCount> productStoryCount(ProductStory story);
	
	/**
	 * 模块需求数量分类
	 * @param story
	 * @return
	 */
	List<StoryCount> modelStoryCount(ProductStory story);
}
