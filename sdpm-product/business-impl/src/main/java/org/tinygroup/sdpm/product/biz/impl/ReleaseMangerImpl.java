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
	private ProductReleaseDao releaseDao;
	
	public ProductRelease add(ProductRelease release) {

		return releaseDao.add(release);
	}

	public int update(ProductRelease release) {

		return releaseDao.edit(release);
	}

	public int delete(Integer releaseId) {

		ProductRelease productRelease = new ProductRelease();
		productRelease.setReleaseId(releaseId);
		productRelease.setDeleted(ProductRelease.DELETE_YES);
		return releaseDao.edit(productRelease);
	}

	public ProductRelease find(Integer releaseId) {

		return releaseDao.getByKey(releaseId);
	}

	public List<ProductRelease> findList(ProductRelease releaseId) {

		return releaseDao.query(releaseId);
	}

	

}
