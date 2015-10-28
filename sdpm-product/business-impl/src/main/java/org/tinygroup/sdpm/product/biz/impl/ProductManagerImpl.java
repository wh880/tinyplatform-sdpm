package org.tinygroup.sdpm.product.biz.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.common.util.common.NameUtil;
import org.tinygroup.sdpm.product.biz.inter.ProductManager;
import org.tinygroup.sdpm.product.dao.ProductDao;
import org.tinygroup.sdpm.product.dao.impl.FieldUtil;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.dao.pojo.ProductAndLine;
import org.tinygroup.sdpm.product.dao.pojo.ProductPlan;
import org.tinygroup.sdpm.productLine.dao.pojo.ProductLine;
import org.tinygroup.tinysqldsl.Pager;

@Service
@Transactional
public class ProductManagerImpl implements ProductManager{

	@Autowired
	private ProductDao productDao;
	
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
		
		
		return productDao.query(product,  (order==null||"".equals(order))?null:new OrderBy(NameUtil.resolveNameDesc("product."+order), !("desc".equals(ordertype))?true:false));
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

	
}
