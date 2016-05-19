package org.tinygroup.sdpm.product.service.inter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.dao.condition.ConditionCarrier;
import org.tinygroup.sdpm.product.biz.inter.StoryManager;
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.product.dao.pojo.ProductStorySpec;
import org.tinygroup.sdpm.product.dao.pojo.StoryCount;
import org.tinygroup.sdpm.product.service.inter.StoryService;
import org.tinygroup.tinysqldsl.Pager;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
@Transactional
public class StoryServiceImpl implements StoryService {

    @Autowired
    private StoryManager storyManager;

    public ProductStory addStory(ProductStory story,
                                 ProductStorySpec storySpec,
                                 String userId) {
        if (story.getPlanId() != null && story.getPlanId() > 0) {
            story.setStoryStage("2");
        } else {
            story.setStoryStage("1");
        }
        if ("0".equals(story.getStoryReviewedBy())) {
            story.setStoryStatus("1");
        } else {
            story.setStoryStatus("0");
        }
        story.setStoryVersion(1);
        story.setStoryOpenedBy(userId);
        story.setStoryOpenedDate(new Date());

        storySpec.setStoryVersion(1);
        return storyManager.add(story, storySpec);
    }

    public Integer deleteStory(ProductStory story) {
        return storyManager.delete(story);
    }

    public int updateStory(ProductStory story) {
        return storyManager.update(story);
    }
    @Transactional(readOnly = true)
    public ProductStory findStory(Integer storyId) {
        if (storyId == null) {
            return null;
        }
        return storyManager.find(storyId);
    }

    public int[] updateBatchStory(List<ProductStory> stories) {
        return storyManager.updateBatch(stories);
    }
    @Transactional(readOnly = true)
    public List<ProductStory> findStoryListByOrder(ProductStory story, String order, String orderType) {

        return storyManager.findList(story, order, orderType);
    }
    @Transactional(readOnly = true)
    public Pager<ProductStory> findStoryPagerRel(int start, int limit, ProductStory story, ConditionCarrier carrier, String columnName, boolean asc) {

        return storyManager.findPagerRel(start, limit, story, carrier, columnName, asc);
    }
    @Transactional(readOnly = true)
    public List<ProductStory> findStoryListByIds(Integer... storyId) {
        return storyManager.findList(false, storyId);
    }
    @Transactional(readOnly = true)
    public List<ProductStory> findStoryList(ProductStory story) {

        return storyManager.findList(story);
    }
    @Transactional(readOnly = true)
    public Map<String, List<StoryCount>> StoryCountReport(String fields,
                                                          ProductStory story) {

        return storyManager.report(fields, story);
    }

    public int[] deleteBatchStory(List<ProductStory> ids) {

        return storyManager.deleteBatch(ids);
    }
    @Transactional(readOnly = true)
    public List<ProductStory> findProductName(Integer storyId) {
        return storyManager.findProductNameByStoryId(storyId);
    }
    @Transactional(readOnly = true)
    public Pager<ProductStory> findProjectLinkedStory(int start, int limit, ProductStory story, ConditionCarrier carrier, String columnName, boolean asc) {
        return storyManager.findProjectLinkedStory(start, limit, story, carrier, columnName, asc);
    }
    @Transactional(readOnly = true)
    public Pager<ProductStory> findStoryByCondition(int start, int limit, ProductStory story, ConditionCarrier carrier, final String columnName, boolean asc) {
        Pager<ProductStory> pager = storyManager.findPager(start, limit, story, carrier, false, columnName, asc);
        return pager;
    }
    @Transactional(readOnly = true)
    public List<ProductStory> storyInCondition(String condition, Integer limit, Integer productId) {
        return storyManager.storyInCondition(condition, limit, productId);
    }
    @Transactional(readOnly = true)
    public List<ProductStory> getStoryWithSpecInIds(boolean isWithSpec, Integer... ids) {
        return storyManager.findList(isWithSpec, ids);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductStory findStoryByStoryId(Integer storyId) {
        return storyManager.findStoryByStoryId(storyId);
    }
}
