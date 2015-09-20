package org.tinygroup.sdpm.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.product.biz.inter.StorySpecManager;
import org.tinygroup.sdpm.product.dao.pojo.ProductStorySpec;
import org.tinygroup.sdpm.product.service.StorySpecService;

@Component("storySpecService")
public class StorySpecServiceImpl implements StorySpecService {
	
	@Autowired
	private StorySpecManager storySpecManager;
	
	public ProductStorySpec findStorySpec(Integer storyId) {

		return storySpecManager.find(storyId);
	}

	public List<ProductStorySpec> findStorySpecList(ProductStorySpec storySpec) {

		return storySpecManager.findList(storySpec);
	}

}
