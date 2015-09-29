package org.tinygroup.sdpm.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.common.util.sql.SearchInfos;
import org.tinygroup.sdpm.product.biz.inter.StoryManager;
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.product.dao.pojo.ProductStorySpec;
import org.tinygroup.sdpm.product.service.StoryService;
import org.tinygroup.tinysqldsl.Pager;

@Component
public class StoryServiceImpl implements StoryService {
	
	@Autowired
	private StoryManager storyManager;
	
	public ProductStory addStory(ProductStory story,ProductStorySpec storySpec) {

		return storyManager.add(story, storySpec);
	}

	public int deleteStory(Integer storyId) {

		return storyManager.delete(storyId);
	}

	public int updateStory(ProductStory story) {

		return storyManager.update(story);
	}

	public ProductStory findStory(Integer storyId) {

		return storyManager.find(storyId);
	}

	public int[] updateBatchStory(List<ProductStory> stories) {
		
		return storyManager.updateBatch(stories);
	}

	public List<ProductStory> findStoryList(ProductStory story, String order,String ordertype) {
		
		return storyManager.findList(story, order, ordertype);
	}

	public Pager<ProductStory> findStoryPager(int start, int limit, ProductStory story, String statusCondition, SearchInfos conditions, String groupOperate, String columnName, boolean asc) {
		return storyManager.findPager(start, limit, story,statusCondition, conditions, groupOperate, columnName, asc);
	}


}
