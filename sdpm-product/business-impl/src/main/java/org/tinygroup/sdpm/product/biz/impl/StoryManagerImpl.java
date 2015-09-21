package org.tinygroup.sdpm.product.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.product.biz.inter.StoryManager;
import org.tinygroup.sdpm.product.dao.ProductStoryDao;
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.tinysqldsl.Pager;

@Service
@Transactional
public class StoryManagerImpl implements StoryManager{
	
	@Autowired
	private ProductStoryDao productStoryDao;
	
	public ProductStory add(ProductStory story) {

		return productStoryDao.add(story);
	}

	public int delete(Integer storyId) {
		
		ProductStory story = new ProductStory();
		story.setStoryId(storyId);
		story.setDeleted(ProductStory.DELETE_YES);
		return productStoryDao.edit(story);
	}

	public int update(ProductStory story) {

		return productStoryDao.edit(story);
	}

	public ProductStory find(Integer storyId) {

		return productStoryDao.getByKey(storyId);
	}

	public List<ProductStory> findList(ProductStory story) {

		return productStoryDao.query(story);
	}
	
	
}
