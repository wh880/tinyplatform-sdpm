package org.tinygroup.sdpm.product.biz.inter;

import java.util.List;

import org.tinygroup.sdpm.common.util.ComplexSearch.SearchInfos;
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.product.dao.pojo.ProductStorySpec;
import org.tinygroup.sdpm.product.dao.pojo.StoryCount;
import org.tinygroup.tinysqldsl.Pager;

public interface StoryManager {
	
	/**
	 * 添加需求
	 * @param story
	 * @return
	 */
	ProductStory add(ProductStory story,ProductStorySpec storySpec);
	/**
	 * 根据需求Id删除
	 * @param storyId
	 * @return
	 */
	Integer delete(Integer storyId);
	
	/**
	 * 编辑
	 * @param story
	 * @return
	 */
	int update(ProductStory story);
	/**
	 * 批量编辑
	 * @param stories
	 * @return
	 */
	int[] updateBatch(List<ProductStory> stories);
	/**
	 * 根据需求ID查找
	 * @param storyId
	 * @return
	 */
	ProductStory find(Integer storyId);
	
	/**
	 * 根据多个id查找
	 * @param storyId
	 * @return
	 */
	List<ProductStory> findList(Integer... storyId);
	/**
	 * 根据对象查询
	 * @param story
	 * @return
	 */
	List<ProductStory> findList(ProductStory story);
	/**
	 * 根据对象查找（排序）
	 * @param story
	 * @return
	 */
	List<ProductStory> findList(ProductStory story,String order,String ordertype);
	/**
	 * 分页查询（排序）
	 * @param start
	 * @param limit
	 * @param story
	 * @return
	 */
	Pager<ProductStory> findPager(int start, int limit, ProductStory story, String statusCondition, SearchInfos conditions, String groupOperate, String columnName, boolean asc);
	
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
	
	/**
	 * 计划需求数量分类
	 * @param story
	 * @return
	 */
	List<StoryCount> planStoryCount(ProductStory story);
	
	
}
