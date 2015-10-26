package org.tinygroup.sdpm.productLine.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.common.util.common.NameUtil;
import org.tinygroup.sdpm.productLine.biz.inter.ProductLineManager;
import org.tinygroup.sdpm.productLine.dao.ProductLineDao;
import org.tinygroup.sdpm.productLine.dao.impl.FieldUtil;
import org.tinygroup.sdpm.productLine.dao.pojo.ProductLine;
import org.tinygroup.tinysqldsl.Pager;
import org.tinygroup.tinysqldsl.base.Condition;
import org.tinygroup.tinysqldsl.base.FragmentSql;


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

	public Pager<ProductLine> findPager(int start, int pagesize,String condition, ProductLine productLine, String order,
			String ordertype) {
		Condition condition1 = null;
		if(!StringUtil.isBlank(condition)){
			condition1 = FragmentSql.fragmentCondition(condition);
		}
		if(!StringUtil.isBlank(order)) {
			return productLineDao.findList(start, pagesize, condition1, productLine,
					new OrderBy("product_line." + NameUtil.resolveNameDesc(order), !("desc".equals(ordertype)) ? true : false));
		}
		return productLineDao.findList(start, pagesize,condition1, productLine);
	}

	public int[] updateBatch(List<ProductLine> productLine) {
		
		return productLineDao.batchUpdate(productLine);
	}

	public List<ProductLine> findList(ProductLine productLine) {
	
		return productLineDao.query(productLine);
	}

	

	

	

}
