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
	
	@Autowired(required=false)
	private ProductStoryDao productStoryDao;
	
	public ProductStory add(ProductStory story) {

		return productStoryDao.add(story);
	}

	public int delete(Integer storyId) {
		
		ProductStory story = new ProductStory();
		story.setStoryId(storyId);
		story.setDeleted(FieldUtil.DELETE_YES);
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

	public List<ProductStory> findList(ProductStory story,String order,String ordertype) {
		
		return productStoryDao.query(story,new OrderBy(FieldUtil.stringFormat(order), !("desc".equals(ordertype))?true:false));
	}

	public Pager<ProductStory> findPager(int start, int limit, ProductStory story, String statusCondition, SearchInfos conditions, String groupOperate, String columnName, boolean asc) {
		String condition = SqlUtil.toSql(conditions.getInfos(),groupOperate);
		condition = condition!=null&&!"".equals(condition)?(statusCondition!=null&&!"".equals(statusCondition)?condition+" and "+statusCondition:condition):statusCondition;
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
