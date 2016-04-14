package org.tinygroup.sdpm.product.service.inter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.product.biz.inter.StorySpecManager;
import org.tinygroup.sdpm.product.dao.pojo.ProductStorySpec;
import org.tinygroup.sdpm.product.service.inter.StorySpecService;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

@Component
@Transactional
public class StorySpecServiceImpl implements StorySpecService {

    @Autowired
    private StorySpecManager storySpecManager;
    @Transactional(readOnly = true)
    public ProductStorySpec findStorySpec(Integer storyId, Integer version) {

        return storySpecManager.find(storyId, version);
    }
    @Transactional(readOnly = true)
    public List<ProductStorySpec> findStorySpecList(ProductStorySpec storySpec, String order, String orderType) {

        return storySpecManager.findList(storySpec, order, orderType);
    }
    @Transactional(readOnly = true)
    public Pager<ProductStorySpec> findStorySpecPager(int page, int limit, ProductStorySpec storySpec, String order, String ordertype) {

        return storySpecManager.findPager(page, limit, storySpec, order, ordertype);
    }
    @Transactional(readOnly = true)
    public Integer getStoryMaxVersion(Integer storyId) {
        return storySpecManager.getMaxVersion(storyId);
    }

    public ProductStorySpec addProductStorySpec(ProductStorySpec storySpec) {

        return storySpecManager.add(storySpec);
    }


}
