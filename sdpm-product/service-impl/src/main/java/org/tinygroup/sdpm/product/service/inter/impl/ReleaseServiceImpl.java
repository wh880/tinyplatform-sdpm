package org.tinygroup.sdpm.product.service.inter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.product.biz.inter.ReleaseManger;
import org.tinygroup.sdpm.product.dao.pojo.ProductRelease;
import org.tinygroup.sdpm.product.service.inter.ReleaseService;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

@Component
@Transactional
public class ReleaseServiceImpl implements ReleaseService {

    @Autowired
    private ReleaseManger releaseManger;

    public ProductRelease addRelease(ProductRelease release) {

        return releaseManger.add(release);
    }

    public int updateRelease(ProductRelease release) {

        return releaseManger.update(release);
    }

    @Transactional(readOnly = true)
    public List<ProductRelease> findReleaseListByOrder(ProductRelease productRelease, String order, String orderType) {

        return releaseManger.findList(productRelease, order, orderType);
    }

    @Transactional(readOnly = true)
    public Pager<ProductRelease> findReleasePager(int page, int limit, ProductRelease productRelease, String order, String ordertype) {

        return releaseManger.findPager(page, limit, productRelease, order, ordertype);
    }

    public Integer deleteRelease(Integer releaseId) {

        return releaseManger.delete(releaseId);
    }
    @Transactional(readOnly = true)
    public ProductRelease findRelease(Integer releaseId) {

        return releaseManger.find(releaseId);
    }
    @Transactional(readOnly = true)
    public List<ProductRelease> findReleaseList(ProductRelease release) {

        return releaseManger.findList(release);
    }


}
