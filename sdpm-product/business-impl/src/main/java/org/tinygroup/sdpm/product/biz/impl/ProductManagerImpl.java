package org.tinygroup.sdpm.product.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.common.util.common.NameUtil;
import org.tinygroup.sdpm.org.dao.OrgRoleUserDao;
import org.tinygroup.sdpm.org.dao.pojo.OrgRoleUser;
import org.tinygroup.sdpm.product.biz.inter.ProductManager;
import org.tinygroup.sdpm.product.dao.ProductDao;
import org.tinygroup.sdpm.product.dao.ProductPlanDao;
import org.tinygroup.sdpm.product.dao.ProductReleaseDao;
import org.tinygroup.sdpm.product.dao.ProductStoryDao;
import org.tinygroup.sdpm.product.dao.impl.FieldUtil;
import org.tinygroup.sdpm.product.dao.pojo.*;
import org.tinygroup.sdpm.project.dao.impl.ProjectBuildDaoImpl;
import org.tinygroup.tinysqldsl.Pager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class ProductManagerImpl implements ProductManager{

	@Autowired
	private ProductDao productDao;
	@Autowired
	private OrgRoleUserDao orgRoleUserDao;
	@Autowired
	private ProductStoryDao productStoryDao;
	@Autowired
	private ProductReleaseDao productReleaseDao;
	@Autowired
	private ProductPlanDao productPlanDao;
	@Autowired
	private ProjectBuildDaoImpl projectBuildDao;
	
	public Product add(Product product) {
		return productDao.add(product);
	}

	public int update(Product product) {
		if(1!=product.getAcl()){
			product.setProductWhiteList("");
		}

		return productDao.edit(product);
	}

	public int delete(Integer productId) {
		//删计划
		ProductPlan plan = new ProductPlan();
		plan.setDeleted(0);
		plan.setProductId(productId);
		List<ProductPlan> plans = productPlanDao.query(plan);
		if(plans.size()>0){
			List<Integer> idList = new ArrayList<Integer>();
			for(ProductPlan plan1:plans){
				idList.add(plan1.getPlanId());
			}
			Integer[] ids = new Integer[idList.size()];
			productPlanDao.batchDelete(idList.toArray(ids));
		}
		//删需求
		ProductStory story = new ProductStory();
		story.setProductId(productId);
		story.setDeleted(0);
		List<ProductStory> stories = productStoryDao.query(story);
		if(stories.size()>0){
			List<Integer> idList = new ArrayList<Integer>();
			for(ProductStory story1:stories){
				idList.add(story1.getStoryId());
			}
			Integer[] ids = new Integer[idList.size()];
			productStoryDao.batchDelete(idList.toArray(ids));
		}
		//删发布
		ProductRelease release = new ProductRelease();
		release.setDeleted(0);
		release.setProductId(productId);
		List<ProductRelease> releases = productReleaseDao.query(release);
		if(releases.size()>0){
			List<Integer> idList = new ArrayList<Integer>();
			for(ProductRelease release1:releases){
				idList.add(release1.getReleaseId());
			}
			Integer[] ids = new Integer[idList.size()];
			productReleaseDao.batchDelete(idList.toArray(ids));
		}
		//删版本
//		projectBuildDao.

		Product product = new Product();
		product.setProductId(productId);
		product.setDeleted(FieldUtil.DELETE_YES);
		return productDao.edit(product);
	}

	public Product find(Integer productId) {
		return productDao.getByKey(productId);
	}


	public int[] updateBatch(List<Product> products) {
		return productDao.batchUpdate(products);
	}
	
	public List<Product> findList(Product product){
		return productDao.query(product, null);
	}
	
	public List<Product> findList(Product product, String order,String ordertype) {
		if(StringUtil.isBlank(order)){
			return productDao.query(product);
		}
		return productDao.query(product,new OrderBy(NameUtil.resolveNameDesc("product."+order), !("desc".equals(ordertype))?true:false));
	}

	public Pager<Product> findPager(int page, int limit, Product product, String order,String ordertype) {
		
		return productDao.queryPager((page-1)*limit, limit, product, (order==null||"".equals(order))?null:new OrderBy(NameUtil.resolveNameDesc("product."+order), !("desc".equals(ordertype))?true:false));
	}

	public List<Product> findList(Integer... productId) {

		return productDao.getByKeys(productId);
	}

	public List<ProductAndLine> getProductAndLine(Product product) {

		return productDao.getProductAndLine(product);
	}

	public List<String> getProductNameByLineId(Integer productLineId) {

		return productDao.getProductNameByLineId(productLineId);
	}

	public List<Product> getProductByUser(String userId) {
		List<Product> productList = productDao.getProductByUser(userId);
		Product product = new Product();
		product.setDeleted(0);
		product.setAcl(product.ACl_TEAM_AND_ROLE);
		List<Product> products = productDao.query(product);
		OrgRoleUser role = new OrgRoleUser();
		role.setOrgUserId(userId);
		List<OrgRoleUser> orgRoles = orgRoleUserDao.query(role);
		return mergeUserProducts(productList,products,orgRoles);
	}

	public List<Product> getProductByUserWithCount(String userId) {
		List<Product> productList = productDao.getProductByUserWithCount(userId);
		Product product = new Product();
		product.setDeleted(0);
		product.setAcl(product.ACl_TEAM_AND_ROLE);
		List<Product> products = productDao.queryWithCount(product);
		OrgRoleUser role = new OrgRoleUser();
		role.setOrgUserId(userId);
		List<OrgRoleUser> orgRoles = orgRoleUserDao.query(role);
		return mergeUserProducts(productList,products,orgRoles);
	}

	public List<Integer> getTeamRoleProductLineIds(String userId){
		List<Integer> pIds = new ArrayList<Integer>();
		for(Product product : getProductByUser(userId)){
			pIds.add(product.getProductLineId());
		}
		return pIds;
	}

	private List<Product> mergeUserProducts(List<Product> productWithOutRole,List<Product> productWithRole,List<OrgRoleUser> roleUsers){
		if(productWithRole.size()==0)return productWithOutRole;
		for(OrgRoleUser orgRoleUser : roleUsers) {
			for (Product product1:productWithRole) {
				String whiteList = product1.getProductWhiteList();
				if (whiteList != null){
					String[] ids = whiteList.split(",");
					List<String> idList = Arrays.asList(ids);
					if(idList.contains(String.valueOf(orgRoleUser.getOrgRoleId()))&&!productWithOutRole.contains(product1)){
						productWithOutRole.add(product1);
					}
				}
			}
		}
		return productWithOutRole;
	}
}
