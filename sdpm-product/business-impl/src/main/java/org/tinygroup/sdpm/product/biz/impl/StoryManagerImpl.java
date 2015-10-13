package org.tinygroup.sdpm.product.biz.impl;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.common.util.ComplexSearch.SearchInfos;
import org.tinygroup.sdpm.common.util.ComplexSearch.SqlUtil;
import org.tinygroup.sdpm.common.util.common.NameUtil;
import org.tinygroup.sdpm.product.biz.inter.StoryManager;
import org.tinygroup.sdpm.product.dao.ProductStoryDao;
import org.tinygroup.sdpm.product.dao.ProductStorySpecDao;
import org.tinygroup.sdpm.product.dao.impl.FieldUtil;
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.product.dao.pojo.ProductStorySpec;
import org.tinygroup.sdpm.product.dao.pojo.StoryCount;
import org.tinygroup.tinysqldsl.Pager;

@Service
@Transactional
public class StoryManagerImpl implements StoryManager {

	@Autowired(required = false)
	private ProductStoryDao productStoryDao;

	@Autowired
	private ProductStorySpecDao storySpecDao;

	public ProductStory add(ProductStory story, ProductStorySpec storySpec) {

		story.setDeleted(FieldUtil.DELETE_NO);
		story = productStoryDao.add(story);
		storySpec.setStoryId(story.getStoryId());
		storySpecDao.add(storySpec);
		return story;
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

	public List<ProductStory> findList(ProductStory story, String order,
			String ordertype) {
		return productStoryDao.query(
				story,
				(order == null || "".equals(order)) ? null : new OrderBy(
						NameUtil.resolveNameDesc(order), !("desc"
								.equals(ordertype)) ? true : false));
	}

	public Pager<ProductStory> findPager(int start, int limit,
			ProductStory story, String statusCondition, SearchInfos conditions,
			String groupOperate, String columnName, boolean asc) {
		String condition = conditions != null ? SqlUtil.toSql(
				conditions.getInfos(), groupOperate) : "";
		condition = condition != null && !"".equals(condition) ? (statusCondition != null
				&& !"".equals(statusCondition) ? condition + " and "
				+ statusCondition : condition)
				: statusCondition;
		OrderBy orderBy = null;
		if (columnName != null && !"".equals(columnName)) {
			orderBy = new OrderBy(columnName, asc);
		}
		if (condition != null && !"".equals(condition)) {
			return productStoryDao.complexQuery(start, limit, story, condition,
					orderBy);
		}
		return productStoryDao.queryPager(start, limit, story, orderBy);
	}

	public List<ProductStory> findList(ProductStory story) {

		return productStoryDao.query(story, null);
	}

	public Integer delete(Integer storyId) {

		return productStoryDao.softDelete(storyId);
	}

	public List<ProductStory> findList(Integer... storyId) {

		return productStoryDao.getByKeys(storyId);
	}

	public List<StoryCount> productStoryCount(ProductStory story) {

		return productStoryDao.productStoryCount(story);
	}

	public List<StoryCount> modelStoryCount(ProductStory story) {

		return productStoryDao.modelStoryCount(story);
	}

	public List<StoryCount> planStoryCount(ProductStory story) {

		return productStoryDao.planStoryCount(story, null);
	}

	public Map<String, List<StoryCount>> report(String fields,ProductStory story) {
		if(fields.equals("")||fields==null){
			return  null;
		}
		Map<String, List<StoryCount>> map = new TreeMap<String, List<StoryCount>>(
				new Comparator<String>() {
					public int compare(String obj1, String obj2) {
						// 降序排序
						return obj1.compareTo(obj2);
					}
				});
		if(fields.contains("productCount")){
			map.put("A_productCount", productStoryDao.productStoryCount(story));
		}else if (fields.contains("B_moduleCount")) {
			map.put("B_moduleCount", productStoryDao.modelStoryCount(story));
		}else if (fields.contains("C_planCount")) {
			map.put("C_planCount", productStoryDao.planStoryCount(story, null));
		}
		char nm = 'D';
		for (String field : fields.split(",")) {
			
			if(!("A_productCount".equals(field.trim())||"B_moduleCount".equals(field.trim())||"C_planCount".equals(field.trim()))){
				map.put(nm+"_"+field, productStoryDao.fieldStoryCount(story, field));
				nm+=1;
			}
			
		}
		
		return map;
	}

	public int[] deleteBatch(List<ProductStory> ids) {
		// TODO Auto-generated method stub
		return productStoryDao.batchUpdateDel(ids);
	}



	
	

}
