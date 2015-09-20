package org.tinygroup.sdpm.productLine.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.productLine.biz.inter.ProductLineManager;
import org.tinygroup.sdpm.productLine.dao.ProductLineDao;
import org.tinygroup.sdpm.productLine.dao.pojo.ProductLine;


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
	
	public int delete(Integer productLineId) {
		
		ProductLine productLine = new ProductLine();
		productLine.setProductLineId(productLineId);
		productLine.setDeleted(ProductLine.DELETE_YES);
		return productLineDao.edit(productLine);
	}

	public ProductLine find(Integer productLineId) {

		return productLineDao.getByKey(productLineId);
	}

	public List<ProductLine> findList(ProductLine productLine) {

		return productLineDao.query(productLine);
	}

	

}
