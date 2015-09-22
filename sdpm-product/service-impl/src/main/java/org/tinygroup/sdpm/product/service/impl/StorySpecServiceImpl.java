package org.tinygroup.sdpm.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.product.biz.inter.StorySpecManager;
import org.tinygroup.sdpm.product.dao.pojo.ProductPlan;
import org.tinygroup.sdpm.product.dao.pojo.ProductStorySpec;
import org.tinygroup.sdpm.product.service.StorySpecService;
import org.tinygroup.tinysqldsl.Pager;

@Component
public class StorySpecServiceImpl implements StorySpecService {
	
	@Autowired
	private StorySpecManager storySpecManager;
	
	public ProductStorySpec findStorySpec(Integer storyId) {

		return storySpecManager.find(storyId);
	}

	public List<ProductStorySpec> findStorySpecList(ProductStorySpec storySpec,String columnName,boolean asc) {
		
		return storySpecManager.findList(storySpec, columnName, asc);
	}

	public Pager<ProductStorySpec> findStorySpecPager(int start, int limit, ProductStorySpec storySpec,String columnName,boolean asc) {
		
		return storySpecManager.findPager(start, limit, storySpec, columnName, asc);
	}


}
