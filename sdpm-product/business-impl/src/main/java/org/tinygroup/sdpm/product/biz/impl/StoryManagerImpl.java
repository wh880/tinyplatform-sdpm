package org.tinygroup.sdpm.product.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.common.util.sql.SearchInfos;
import org.tinygroup.sdpm.common.util.sql.SqlUtil;
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

	public int[] updateBatch(List<ProductStory> stories) {
		
		return productStoryDao.batchUpdate(stories);
	}

	public List<ProductStory> findList(ProductStory story,String columnName,boolean asc) {
		
		return productStoryDao.query(story, new OrderBy(columnName, asc));
	}

	public Pager<ProductStory> findPager(int start, int limit, ProductStory story, SearchInfos conditions, String groupOperate, String columnName, boolean asc) {
		String condition = SqlUtil.toSql(conditions.getInfos(),groupOperate);
		OrderBy orderBy = null;
		if(columnName != null && !"".equals(columnName)){
			orderBy = new OrderBy(columnName, asc);
		}
		if(condition !=null && !"".equals(condition)){
			return productStoryDao.complexQuery(start,limit,story,condition,orderBy);
		}
		return productStoryDao.queryPager(start, limit, story, orderBy);
	}
	
	
}
