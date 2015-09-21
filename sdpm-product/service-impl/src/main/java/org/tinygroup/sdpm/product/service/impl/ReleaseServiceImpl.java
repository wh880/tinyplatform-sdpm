package org.tinygroup.sdpm.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.product.biz.inter.ReleaseManger;
import org.tinygroup.sdpm.product.dao.pojo.ProductRelease;
import org.tinygroup.sdpm.product.service.ReleaseService;

@Component()
public class ReleaseServiceImpl implements ReleaseService{
	
	@Autowired
	private ReleaseManger releaseManger;
	
	public ProductRelease addRelease(ProductRelease release) {

		return releaseManger.add(release);
	}

	public int updateRelease(ProductRelease release) {

		return releaseManger.update(release);
	}

	public int deleteRelease(Integer releaseId) {

		return releaseManger.delete(releaseId);
	}

	public ProductRelease findRelease(Integer releaseId) {

		return releaseManger.find(releaseId);
	}

	public List<ProductRelease> findReleaseList(ProductRelease releaseId) {

		return releaseManger.findList(releaseId);
	}

}
