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

import java.util.List;
import java.util.Map;

@Component
public class StoryServiceImpl implements StoryService {

    @Autowired
    private StoryManager storyManager;
    @Autowired
    private ModuleManager moduleManager;

    public ProductStory addStory(ProductStory story, ProductStorySpec storySpec) {

        return storyManager.add(story, storySpec);
    }

    public Integer deleteStory(Integer storyId) {

        return storyManager.delete(storyId);
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

    public Pager<ProductStory> findStoryPager(int start, int limit, ProductStory story, String statusCondition, SearchInfos conditions, String groupOperate, String columnName, boolean asc) {

        return storyManager.findPager(start, limit, story, statusCondition, conditions, groupOperate, columnName, asc);
    }

    public List<ProductStory> findStoryList(Integer... storyId) {

        return storyManager.findList(storyId);
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

    public Pager<ProductStory> findPager(int start, int limit,
                                         ProductStory story, String condition, String columnName, boolean asc) {

        return storyManager.findPager(start, limit, story, condition, columnName, asc);
    }

    public Pager<ProductStory> findProjectLinkedStory(int start, int limit,ProductStory story, String condition, String columnName, boolean asc) {
        return storyManager.findProjectLinkedStory(start,limit,story,condition,columnName,asc);
    }

    public Pager<ProductStory> findStoryByCondition(int start, int limit, ProductStory story, ConditionCarrier carrier, final String columnName, boolean asc) {
        carrier.completeModuleFunction(new CallBackFunction() {
            public boolean process(ConditionCarrier carrier, String field,String relation) {
                String result = "";
                if(ConditionUtils.CommonFieldType.MODULE.getOperate().equals(carrier.getFieldType(field))){
                    String moduleId = (String)carrier.getValue(field)[0];
                    if(moduleId.contains("p")){
                        result = ModuleUtil.getConditionByRoot(Integer.parseInt(moduleId.substring(1)),moduleManager);
                    }else{
                        result = ModuleUtil.getCondition(Integer.parseInt(moduleId), moduleManager);
                    }
                    carrier.setCondition(field,result,carrier.DEFAULT_RELATION);
                    return true;
                }
                return false;
            }
        });
        carrier.completeCustomFunction(new CallBackFunction() {
            public boolean process(ConditionCarrier carrier, String field, String relation) {
                if(ConditionUtils.CommonFieldType.STATUS.getOperate().equals(carrier.getFieldType(field))){
                    String statusCondition = (String)carrier.getValue(field)[0];
                    carrier.setCondition(field,statusCondition,carrier.DEFAULT_RELATION);
                    return true;
                }
                return false;
            }
        });
        return storyManager.findPager(start,limit,story,carrier.resultCondition(),columnName,asc);
    }

}
