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
		// TODO Auto-generated method stub
		return null;
	}

	public int deleteStory(Integer storyId) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateStory(ProductStory story) {
		// TODO Auto-generated method stub
		return 0;
	}

	public ProductStory findStory(Integer storyId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ProductStory> findStoryList(ProductStory story) {
		// TODO Auto-generated method stub
		return null;
	}

}
