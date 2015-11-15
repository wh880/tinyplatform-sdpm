package org.tinygroup.sdpm.product.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.common.util.common.NameUtil;
import org.tinygroup.sdpm.org.dao.OrgRoleDao;
import org.tinygroup.sdpm.org.dao.OrgRoleUserDao;
import org.tinygroup.sdpm.org.dao.pojo.OrgRole;
import org.tinygroup.sdpm.org.dao.pojo.OrgRoleUser;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.sdpm.product.biz.inter.ProductManager;
import org.tinygroup.sdpm.product.dao.ProductDao;
import org.tinygroup.sdpm.product.dao.impl.FieldUtil;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.dao.pojo.ProductAndLine;
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

	private List<Product> mergeUserProducts(List<Product> productWithoutRole,List<Product> productWithRole,List<OrgRoleUser> roleUsers){
		for(OrgRoleUser orgRoleUser : roleUsers) {
			for (Product product1:productWithRole) {
				String whiteList = product1.getProductWhiteList();
				if (whiteList != null){
					String[] ids = whiteList.split(",");
					List<String> idList = Arrays.asList(ids);
					if(idList.contains(String.valueOf(product1.getProductId()))&&!productWithoutRole.contains(product1)){
						productWithoutRole.add(product1);
					}
				}
			}
		}
		return productWithoutRole;
	}
}
