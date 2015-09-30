package org.tinygroup.sdpm.productLine.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.productLine.biz.inter.ProductLineManager;
import org.tinygroup.sdpm.productLine.dao.pojo.ProductLine;
import org.tinygroup.sdpm.productLine.service.ProductLineService;
import org.tinygroup.tinysqldsl.Pager;

@Component
public class ProductLineServiceImpl implements ProductLineService {
	
	@Autowired
	private ProductLineManager productLineManager;
	
	public ProductLine addProductLine(ProductLine productLine) {

		return productLineManager.add(productLine);
	}

	public int updateProductLine(ProductLine productLine) {

		return productLineManager.update(productLine);
	}

	public ProductLine findProductLine(Integer productLineId) {

		return productLineManager.find(productLineId);
	}

	public int[] updatebatchProductLine(List<ProductLine> productLine) {
		
		return productLineManager.updateBatch(productLine);
	}

	public int deleteProductLine(Integer productLineId) {
		
		return productLineManager.delete(productLineId);
	}

	public List<ProductLine> findProductLineList(ProductLine productLine, String order, String ordertype) {
		
		return productLineManager.findlist(productLine, order, ordertype);
	}

	public Pager<ProductLine> findProductLinePager(int page, int pagesize, ProductLine productLine, String order,
			String ordertype) {
		
		return productLineManager.findPager(page, pagesize, productLine, order, ordertype);
	}

	



}
