package org.tinygroup.sdpm.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.product.biz.inter.ReleaseManger;
import org.tinygroup.sdpm.product.dao.pojo.ProductRelease;
import org.tinygroup.sdpm.product.service.ReleaseService;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

@Component
public class ReleaseServiceImpl implements ReleaseService {

    @Autowired
    private ReleaseManger releaseManger;

    public ProductRelease addRelease(ProductRelease release) {

        return releaseManger.add(release);
    }

    public int updateRelease(ProductRelease release) {

        return releaseManger.update(release);
    }

    public int[] updateBatchRelease(List<ProductRelease> releases) {

        return releaseManger.updateBatch(releases);
    }


    public List<ProductRelease> findReleaseList(ProductRelease productRelease, String order, String ordertype) {

        return releaseManger.findList(productRelease, order, ordertype);
    }


    public Pager<ProductRelease> findReleasePager(int page, int limit, ProductRelease productRelease, String order, String ordertype) {

        return releaseManger.findPager(page, limit, productRelease, order, ordertype);
    }

    public Integer deleteRelease(Integer releaseId) {

        return releaseManger.delete(releaseId);
    }

    public ProductRelease findRelease(Integer releaseId) {

        return releaseManger.find(releaseId);
    }

    public List<ProductRelease> findReleaseList(Integer... releaseId) {

        return releaseManger.findList(releaseId);
    }

    public List<ProductRelease> findReleaseList(ProductRelease release) {

        return releaseManger.findList(release);
    }


}
