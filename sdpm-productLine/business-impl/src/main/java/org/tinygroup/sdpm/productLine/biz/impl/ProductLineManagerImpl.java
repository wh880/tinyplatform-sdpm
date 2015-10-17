package org.tinygroup.sdpm.productLine.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.productLine.biz.inter.ProductLineManager;
import org.tinygroup.sdpm.productLine.dao.ProductLineDao;
import org.tinygroup.sdpm.productLine.dao.impl.FieldUtil;
import org.tinygroup.sdpm.productLine.dao.pojo.ProductLine;
import org.tinygroup.tinysqldsl.Pager;


@Service
@Transactional
public class ProductLineManagerImpl implements ProductLineManager{
	
	@Autowired
	private ProductLineDao productLineDao;
	
	public ProductLine add(ProductLine productLine) {

		return productLineDao.add(productLine);
	}

	public int update(ProductLine productLine) {

		return productLineDao.edit(productLine);
	}

	public ProductLine find(Integer productLineId) {

		return productLineDao.getByKey(productLineId);
	}

	public int delete(Integer productLineId) {
		
		return productLineDao.softDelete(productLineId);
	}

	public List<ProductLine> findlist(ProductLine productLine, String order, String ordertype) {
		
		return productLineDao.query(productLine,  new OrderBy(FieldUtil.stringFormat(order), !("desc".equals(ordertype))?true:false));
	}

	public Pager<ProductLine> findPager(int page, int pagesize, ProductLine productLine, String order,
			String ordertype) {
		
		return productLineDao.queryPager((page-1)*pagesize, pagesize, productLine, new OrderBy(FieldUtil.stringFormat(order), !("desc".equals(ordertype))?true:false));
	}

	public int[] updateBatch(List<ProductLine> productLine) {
		
		return productLineDao.batchUpdate(productLine);
	}

	public List<ProductLine> findList(ProductLine productLine) {
	
		return productLineDao.query(productLine);
	}

	public List<ProductLine> getProductLineTree(ProductLine t) {

		return productLineDao.getProductLineTree(t);
	}

	

	

}
