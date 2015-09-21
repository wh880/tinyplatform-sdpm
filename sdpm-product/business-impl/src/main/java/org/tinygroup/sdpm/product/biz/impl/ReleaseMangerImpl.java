package org.tinygroup.sdpm.product.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.product.biz.inter.ReleaseManger;
import org.tinygroup.sdpm.product.dao.ProductReleaseDao;
import org.tinygroup.sdpm.product.dao.pojo.ProductRelease;

@Service
@Transactional
public class ReleaseMangerImpl implements ReleaseManger{
	
	@Autowired
	private ProductReleaseDao productReleaseDao;
	
	public ProductRelease add(ProductRelease release) {

		return productReleaseDao.add(release);
	}

	public int update(ProductRelease release) {

		return productReleaseDao.edit(release);
	}

	public int delete(Integer releaseId) {

		ProductRelease productRelease = new ProductRelease();
		productRelease.setReleaseId(releaseId);
		productRelease.setDeleted(ProductRelease.DELETE_YES);
		return productReleaseDao.edit(productRelease);
	}

	public ProductRelease find(Integer releaseId) {

		return productReleaseDao.getByKey(releaseId);
	}

	public List<ProductRelease> findList(ProductRelease releaseId) {

		return productReleaseDao.query(releaseId);
	}

	

}
