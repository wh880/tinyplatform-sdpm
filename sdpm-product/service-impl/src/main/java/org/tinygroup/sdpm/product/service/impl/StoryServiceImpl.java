package org.tinygroup.sdpm.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.product.biz.inter.StoryManager;
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.product.service.StoryService;

@Component("storyService")
public class StoryServiceImpl implements StoryService {
	
	@Autowired
	private StoryManager storyManager;
	
	public ProductStory addStory(ProductStory story) {

		return storyManager.add(story);
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

	public List<ProductStory> findStoryList(ProductStory story) {

		return storyManager.findList(story);
	}

}
