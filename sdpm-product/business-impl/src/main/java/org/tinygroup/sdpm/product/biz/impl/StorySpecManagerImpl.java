package org.tinygroup.sdpm.product.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.common.util.common.NameUtil;
import org.tinygroup.sdpm.product.biz.inter.StorySpecManager;
import org.tinygroup.sdpm.product.dao.ProductStorySpecDao;
import org.tinygroup.sdpm.product.dao.impl.FieldUtil;
import org.tinygroup.sdpm.product.dao.pojo.ProductStorySpec;
import org.tinygroup.tinysqldsl.Pager;

@Service
@Transactional
public class StorySpecManagerImpl implements StorySpecManager{
	
	@Autowired
	private ProductStorySpecDao productStorySpecDao;

	public ProductStorySpec find(Integer storyId) {
		ProductStorySpec storySpec = new ProductStorySpec();
		storySpec.setStoryId(storyId);
		List<ProductStorySpec> list = productStorySpecDao.query(storySpec,  new OrderBy(NameUtil.resolveNameDesc("storyVersion"), false));
		return list.size()>0?list.get(0):null;
	}

	public List<ProductStorySpec> findList(ProductStorySpec storySpec,String order,String ordertype) {
		if(StringUtil.isBlank(order)){
			return  productStorySpecDao.query(storySpec);
		}
		return productStorySpecDao.query(storySpec, new OrderBy(NameUtil.resolveNameDesc(order), "asc".equals(ordertype)?true:false));
	}

	public Pager<ProductStorySpec> findPager(int page, int limit, ProductStorySpec storySpec, String order,String ordertype) {

		return productStorySpecDao.queryPager((page-1)*limit, limit, storySpec, (order==null||"".equals(order))?null:new OrderBy(NameUtil.resolveNameDesc(order), !("desc".equals(ordertype))?true:false));
	}

	public List<ProductStorySpec> findList(ProductStorySpec storySpec) {
		
		return productStorySpecDao.query(storySpec, null);
	}

	public List<ProductStorySpec> findList(Integer... storyspecId) {

		return productStorySpecDao.getByKeys(storyspecId);
	}

	public int getNewStoryVersion(Integer storyId) {

		return productStorySpecDao.getNewStoryVersion(storyId);
	}

	public Integer getMaxVersion(Integer storyId) {
		return productStorySpecDao.getMaxVersion(storyId);
	}

	public ProductStorySpec add(ProductStorySpec storySpec) {

		return productStorySpecDao.add(storySpec);
	}

}
