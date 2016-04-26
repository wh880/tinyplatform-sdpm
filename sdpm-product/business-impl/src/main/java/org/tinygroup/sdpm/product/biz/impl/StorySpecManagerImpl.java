package org.tinygroup.sdpm.product.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.common.util.common.NameUtil;
import org.tinygroup.sdpm.product.biz.inter.StorySpecManager;
import org.tinygroup.sdpm.product.dao.ProductStorySpecDao;
import org.tinygroup.sdpm.product.dao.pojo.ProductStorySpec;
import org.tinygroup.tinysqldsl.Pager;

import java.util.ArrayList;
import java.util.List;

@Service
public class StorySpecManagerImpl implements StorySpecManager {

    @Autowired
    private ProductStorySpecDao productStorySpecDao;

    public ProductStorySpec find(Integer storyId, Integer version) {
        ProductStorySpec storySpec = new ProductStorySpec();
        storySpec.setStoryId(storyId);
        storySpec.setStoryVersion(version);
        List<ProductStorySpec> list = productStorySpecDao.query(storySpec, new OrderBy(NameUtil.resolveNameDesc("storyVersion"), false));
        return list.size() > 0 ? list.get(0) : null;
    }

    public List<ProductStorySpec> findList(ProductStorySpec storySpec, String order, String ordertype) {
        if (StringUtil.isBlank(order)) {
            return productStorySpecDao.query(storySpec);
        }
        return productStorySpecDao.query(storySpec, new OrderBy(NameUtil.resolveNameDesc(order), "asc".equals(ordertype) ? true : false));
    }

    public Pager<ProductStorySpec> findPager(int page, int limit, ProductStorySpec storySpec, String order, String ordertype) {
        if (StringUtil.isBlank(order)) {
            return productStorySpecDao.queryPager((page - 1) * limit, limit, storySpec);
        }
        return productStorySpecDao.queryPager((page - 1) * limit, limit, storySpec, new OrderBy(NameUtil.resolveNameDesc(order), "asc".equals(ordertype)));
    }

    public List<ProductStorySpec> findList(ProductStorySpec storySpec) {

        return productStorySpecDao.query(storySpec, null);
    }

    public List<ProductStorySpec> findList(Integer... storyspecId) {
        if (storyspecId == null || storyspecId.length == 0) return new ArrayList<ProductStorySpec>();
        return productStorySpecDao.getByKeys(storyspecId);
    }

    public int getNewStoryVersion(Integer storyId) {

        return productStorySpecDao.getNewStoryVersion(storyId);
    }

    public Integer getMaxVersion(Integer storyId) {
        return productStorySpecDao.getMaxVersion(storyId);
    }

    public ProductStorySpec add(ProductStorySpec storySpec) {

        return productStorySpecDao.add(storySpec);
    }

}
