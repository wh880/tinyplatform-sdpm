package org.tinygroup.sdpm.product.service;

import org.tinygroup.sdpm.common.condition.ConditionCarrier;
import org.tinygroup.sdpm.common.util.ComplexSearch.SearchInfos;
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.product.dao.pojo.ProductStorySpec;
import org.tinygroup.sdpm.product.dao.pojo.StoryCount;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;
import java.util.Map;

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
	Integer deleteStory(ProductStory story);
	
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
	 * 根据id批量删除
	 * @param ids
	 * @return
	 */
	int[] deleteBatchStory(List<ProductStory> ids);
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
	
	/**
	 * 计划需求数量分类
	 * @param story
	 * @return
	 */
	List<StoryCount> planStoryCount(ProductStory story);
	
	/**
	 * 其他状态需求数量分类
	 * @param story
	 * @return
	 */
	 Map<String, List<StoryCount>> report(String fields,ProductStory story);

	/**
	 * 计算状态
	 * @param productId
	 * @param status
     * @return
     */
	int countStatus(int productId,int status);

	/**
	 * 获取需求所在product名称
	 * @param storyId
	 * @return
	 */
	List<ProductStory> findProductName(Integer storyId);

	/**
	 * 有条件page查询
	 * @param start
	 * @param limit
	 * @param story
	 * @param condition
	 * @param columnName
	 * @param asc
	 * @return
	 */
	Pager<ProductStory> findPager(int start, int limit,ProductStory story, String condition, String columnName, boolean asc);

	Pager<ProductStory> findProjectLinkedStory(int start, int limit,ProductStory story, String condition, String columnName, boolean asc);

	Pager<ProductStory> findStoryByCondition(int start, int limit,ProductStory story, ConditionCarrier carrier, String columnName, boolean asc);

	/**
	 * 根据输入名称查询
	 * @param condition
	 * @param productId
	 * @return
	 */
	List<ProductStory> storyInCondition(String condition, Integer productId);
}
