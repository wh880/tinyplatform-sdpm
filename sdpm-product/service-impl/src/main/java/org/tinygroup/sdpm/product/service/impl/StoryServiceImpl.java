package org.tinygroup.sdpm.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.common.condition.CallBackFunction;
import org.tinygroup.sdpm.common.condition.ConditionCarrier;
import org.tinygroup.sdpm.common.condition.ConditionUtils;
import org.tinygroup.sdpm.common.util.ComplexSearch.SearchInfos;
import org.tinygroup.sdpm.product.biz.inter.StoryManager;
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.product.dao.pojo.ProductStorySpec;
import org.tinygroup.sdpm.product.dao.pojo.StoryCount;
import org.tinygroup.sdpm.product.service.StoryService;
import org.tinygroup.sdpm.system.biz.impl.ModuleUtil;
import org.tinygroup.sdpm.system.biz.inter.ModuleManager;
import org.tinygroup.tinysqldsl.Pager;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class StoryServiceImpl implements StoryService {

    @Autowired
    private StoryManager storyManager;
    @Autowired
    private ModuleManager moduleManager;

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

    public ProductStory findStory(Integer storyId) {
        if(storyId == null){
            return null;
        }
        return storyManager.find(storyId);
    }

    public int[] updateBatchStory(List<ProductStory> stories) {
        return storyManager.updateBatch(stories);
    }

    public List<ProductStory> findStoryList(ProductStory story, String order, String ordertype) {

        return storyManager.findList(story, order, ordertype);
    }

    public Pager<ProductStory> findStoryPagerRel(int start, int limit, ProductStory story, ConditionCarrier carrier, String columnName, boolean asc) {

        return storyManager.findPagerRel(start, limit, story, carrier, columnName, asc);
    }

    public List<ProductStory> findStoryList(Integer... storyId) {

        return storyManager.findList(false,storyId);
    }

    public List<ProductStory> findStoryList(ProductStory story) {

        return storyManager.findList(story);
    }

    public List<StoryCount> productStoryCount(ProductStory story) {

        return storyManager.productStoryCount(story);
    }

    public List<StoryCount> modelStoryCount(ProductStory story) {

        return storyManager.modelStoryCount(story);
    }

    public List<StoryCount> planStoryCount(ProductStory story) {

        return storyManager.planStoryCount(story);
    }

    public Map<String, List<StoryCount>> report(String fields,
                                                ProductStory story) {

        return storyManager.report(fields, story);
    }

    public int countStatus(int productId, int status) {
        return storyManager.countStatus(productId, status);
    }

    public int[] deleteBatchStory(List<ProductStory> ids) {

        return storyManager.deleteBatch(ids);
    }

    public List<ProductStory> findProductName(Integer storyId) {
        return storyManager.findProductNameByStoryId(storyId);
    }

    public Pager<ProductStory> findProjectLinkedStory(int start, int limit,ProductStory story, ConditionCarrier carrier, String columnName, boolean asc) {
        return storyManager.findProjectLinkedStory(start,limit,story,carrier,columnName,asc);
    }

    public Pager<ProductStory> findStoryByCondition(int start, int limit, ProductStory story, ConditionCarrier carrier, final String columnName, boolean asc) {

        return storyManager.findPager(start,limit,story,carrier,columnName,asc);
    }

    public List<ProductStory> storyInCondition(String condition, Integer productId) {
        return storyManager.storyInCondition(condition,productId);
    }

    public List<ProductStory> getStoryWithSpecInIds(boolean isWithSpec, Integer... ids) {
        return storyManager.findList(isWithSpec,ids);
    }

}
