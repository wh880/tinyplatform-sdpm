package org.tinygroup.sdpm.product.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.common.util.common.NameUtil;
import org.tinygroup.sdpm.product.biz.inter.ReleaseManger;
import org.tinygroup.sdpm.product.dao.ProductReleaseDao;
import org.tinygroup.sdpm.product.dao.pojo.ProductRelease;
import org.tinygroup.tinysqldsl.Pager;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReleaseMangerImpl implements ReleaseManger {

    @Autowired
    private ProductReleaseDao productReleaseDao;

    public ProductRelease add(ProductRelease release) {
        Integer no = productReleaseDao.getMaxNo(release.getProductId());
        release.setNo(no == null ? 1 : no + 1);
        return productReleaseDao.add(release);
    }

    public int update(ProductRelease release) {

        return productReleaseDao.edit(release);
    }

    public Integer delete(Integer releaseId) {

        return productReleaseDao.softDelete(releaseId);
    }


    public ProductRelease find(Integer releaseId) {
        if (releaseId == null) return new ProductRelease();
        return productReleaseDao.getByKey(releaseId);
    }

    public int[] updateBatch(List<ProductRelease> releases) {

        return productReleaseDao.batchUpdate(releases);
    }

    public List<ProductRelease> findList(ProductRelease release, String order, String ordertype) {

        return productReleaseDao.query(release, (order == null || "".equals(order)) ? null : new OrderBy(NameUtil.resolveNameDesc(order), !("desc".equals(ordertype)) ? true : false));
    }

    public Pager<ProductRelease> findPager(int page, int limit, ProductRelease release, String order, String ordertype) {

        return productReleaseDao.queryPager((page - 1) * limit, limit, release, (order == null || "".equals(order)) ? null : new OrderBy(NameUtil.resolveNameDesc("productRelease." + order), !("desc".equals(ordertype)) ? true : false));
    }

    public List<ProductRelease> findList(ProductRelease productRelease) {

        return productReleaseDao.query(productRelease, null);
    }

    public List<ProductRelease> findList(Integer... releaseId) {
        if (releaseId == null || releaseId.length == 0) return new ArrayList<ProductRelease>();
        return productReleaseDao.getByKeys(releaseId);
    }


}
