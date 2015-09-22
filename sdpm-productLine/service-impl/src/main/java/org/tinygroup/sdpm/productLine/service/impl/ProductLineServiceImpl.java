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

	public int[] updateBatch(List<ProductLine> productLines) {
		
		return productLineManager.updateBatch(productLines);
	}

	public List<ProductLine> findProductLineList(ProductLine productLine, String columnName, boolean asc) {
		
		return productLineManager.findList(productLine, columnName, asc);
	}

	public Pager<ProductLine> findProductLinePager(int start, int limit, ProductLine productLine, String columnName,
			boolean asc) {
		
		return productLineManager.findPager(start, limit, productLine, columnName, asc);
	}


}
