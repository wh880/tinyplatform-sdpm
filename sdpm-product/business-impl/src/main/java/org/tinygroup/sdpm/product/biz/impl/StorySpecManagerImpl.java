package org.tinygroup.sdpm.product.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.product.biz.inter.StoryManager;
import org.tinygroup.sdpm.product.biz.inter.StorySpecManager;
import org.tinygroup.sdpm.product.dao.ProductStorySpecDao;
import org.tinygroup.sdpm.product.dao.pojo.ProductPlan;
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.product.dao.pojo.ProductStorySpec;
import org.tinygroup.tinysqldsl.Pager;

@Service
@Transactional
public class StorySpecManagerImpl implements StorySpecManager{
	
	@Autowired
	private ProductStorySpecDao productStorySpecDao;

	public ProductStorySpec find(Integer storyId) {

		return productStorySpecDao.getByKey(storyId);
	}

	public List<ProductStorySpec> findList(ProductStorySpec storySpec,String columnName,boolean asc) {
		
		return productStorySpecDao.query(storySpec, new OrderBy(columnName, asc));
	}

	public Pager<ProductStorySpec> findPager(int start, int limit, ProductStorySpec storySpec, String columnName,
			boolean asc) {

		return productStorySpecDao.queryPager(start, limit, storySpec,  new OrderBy(columnName, asc));
	}

	
	
}
