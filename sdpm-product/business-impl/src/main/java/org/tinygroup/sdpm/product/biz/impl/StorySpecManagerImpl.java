package org.tinygroup.sdpm.product.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.product.biz.inter.StoryManager;
import org.tinygroup.sdpm.product.biz.inter.StorySpecManager;
import org.tinygroup.sdpm.product.dao.ProductStorySpecDao;
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.product.dao.pojo.ProductStorySpec;

@Service
@Transactional
public class StorySpecManagerImpl implements StorySpecManager{
	
	@Autowired
	private ProductStorySpecDao storySpecDao;

	public ProductStorySpec find(Integer storyId) {

		return storySpecDao.getByKey(storyId);
	}

	public List<ProductStorySpec> findList(ProductStorySpec storySpec) {

		return storySpecDao.query(storySpec);
	}

	
	
}
