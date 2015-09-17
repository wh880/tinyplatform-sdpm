package org.tinygroup.sdpm.product.service;

import java.util.List;

import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.dao.pojo.Story;
import org.tinygroup.tinysqldsl.Pager;

public interface StoryService {
	
	/**
	 * 添加需求
	 * @param story
	 * @return
	 */
	Story add(Story story);
	
	/**
	 * 编辑需求
	 * @param story
	 * @return
	 */
	int edit(Story story);
	
	/**
	 * 根据需求ID批量关闭
	 * @param storyIds
	 * @return
	 */
	int batchClose(Integer...storyIds);
	
	/**
	 * 根据需求Id删除
	 * @param storyId
	 * @return
	 */
	int deleteById(Integer storyId);
	
	/**
	 * 根据需求对象删除
	 * @param story
	 * @return
	 */
	int deleteByStory(Story story);
	
	/**
	 * 根据需求Id批量删除
	 * @param storyIds
	 * @return
	 */
	int deleteByStoryIds(Integer... storyIds);
	
	/**
	 * 根据需求批量删除
	 * @param products
	 * @return
	 */
	int[] batchDelete(List<Story> storys);
	
	/**
	 * 根据需求ID查找
	 * @param storyId
	 * @return
	 */
	Story getById(Integer storyId);
	
	/**
	 * 根据产品ID查找
	 * @param productId
	 * @return
	 */
	Pager<Story> getByProductId(Integer productId);
	
	/**
	 * 根据模块ID查找
	 * @param moduleId
	 * @return
	 */
	Pager<Story> getByModuleId(Integer moduleId);
	
	/**
	 * 根据计划ID查找
	 * @param planId
	 * @return
	 */
	Pager<Story> getByPlanId(Integer planId);
	
	/**
	 * 根据状态查找
	 * @param status
	 * @return
	 */
	Pager<Story> getByStatus(String status);
	
	/**
	 * 分页查找
	 * @param story
	 * @return
	 */
	Pager<Story> getStory(Story story);
	
	
	
}
