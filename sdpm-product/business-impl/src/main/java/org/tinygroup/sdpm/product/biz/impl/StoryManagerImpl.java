package org.tinygroup.sdpm.product.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.common.util.common.NameUtil;
import org.tinygroup.sdpm.dao.condition.CallBackFunction;
import org.tinygroup.sdpm.dao.condition.ConditionCarrier;
import org.tinygroup.sdpm.dao.condition.ConditionUtils;
import org.tinygroup.sdpm.product.biz.inter.StoryManager;
import org.tinygroup.sdpm.product.dao.ProductStoryDao;
import org.tinygroup.sdpm.product.dao.ProductStorySpecDao;
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.product.dao.pojo.ProductStorySpec;
import org.tinygroup.sdpm.product.dao.pojo.StoryCount;
import org.tinygroup.sdpm.product.dao.utils.FieldUtil;
import org.tinygroup.sdpm.quality.dao.QualityBugDao;
import org.tinygroup.sdpm.quality.dao.QualityTestCaseDao;
import org.tinygroup.sdpm.system.dao.SystemModuleDao;
import org.tinygroup.sdpm.system.dao.impl.util.ModuleUtil;
import org.tinygroup.tinysqldsl.Pager;
import org.tinygroup.tinysqldsl.base.Condition;

import java.util.*;


@Service
public class StoryManagerImpl implements StoryManager {

    @Autowired(required = false)
    private ProductStoryDao productStoryDao;
    @Autowired
    private ProductStorySpecDao storySpecDao;
    @Autowired
    private SystemModuleDao systemModuleDao;
    @Autowired
    private QualityBugDao qualityBugDao;
    @Autowired
    private QualityTestCaseDao qualityTestCaseDao;

    public ProductStory add(ProductStory story, ProductStorySpec storySpec) {
        Integer no = productStoryDao.getMaxNo(story.getProductId());
        story.setNo(no == null ? 1 : no + 1);
        story.setDeleted(FieldUtil.DELETE_NO);
        story = productStoryDao.add(story);
        storySpec.setStoryId(story.getStoryId());
        storySpecDao.add(storySpec);
        return story;
    }

    public int update(ProductStory story) {

        return productStoryDao.edit(story);
    }

    public ProductStory find(Integer storyId) {
        if (null == storyId) {
            return null;
        }
        return productStoryDao.getReleteStoryByKey(storyId);
    }

    public int[] updateBatch(List<ProductStory> stories) {

        return productStoryDao.batchUpdate(stories);
    }

    public List<ProductStory> findList(ProductStory story, String order, String ordertype) {
        if (StringUtil.isBlank(order)) {
            return productStoryDao.query(story);
        } else {
            order = "product_story." + NameUtil.resolveNameDesc(order);

            return productStoryDao.query(story, new OrderBy(order, !("desc".equals(ordertype)) ? true : false));
        }
    }

    public Pager<ProductStory> findPagerRel(int start, int limit, ProductStory story, ConditionCarrier carrier, String columnName, boolean asc) {

        if (StringUtil.isBlank(columnName)) {
            return productStoryDao.complexQueryRel(start, limit, story, mergeCondition(carrier));
        }
        return productStoryDao.complexQueryRel(start, limit, story, mergeCondition(carrier), new OrderBy("product_story." + NameUtil.resolveNameDesc(columnName), asc));
    }

    public List<ProductStory> findList(ProductStory story) {

        return productStoryDao.query(story, null);
    }

    public Integer delete(ProductStory story) {
        //删bug
        qualityBugDao.deleteBugsByStory(story.getStoryId());
        //删case
        qualityTestCaseDao.deleteCaseByStory(story.getStoryId());

        return productStoryDao.edit(story);
    }

    public List<ProductStory> findList(boolean storySpec, Integer... storyId) {
        if (storyId == null || storyId.length == 0) return new ArrayList<ProductStory>();
        return productStoryDao.getByKeys(storySpec, null, storyId);
    }

    @Override
    public List<ProductStory> findList(boolean withSpec, ProductStory story, Integer... storyId) {
        if (storyId == null || storyId.length == 0) return new ArrayList<ProductStory>();
        return productStoryDao.getByKeys(withSpec, story, storyId);
    }

    public List<StoryCount> productStoryCount(ProductStory story) {

        return productStoryDao.productStoryCount(story);
    }

    public List<StoryCount> modelStoryCount(ProductStory story) {

        return productStoryDao.modelStoryCount(story);
    }

    public List<StoryCount> planStoryCount(ProductStory story) {

        return productStoryDao.planStoryCount(story, null);
    }

    /*A_productCount,B_moduleCount,C_planCount,
     * storySource,storyStatus,storyStage,storyPri,storyEstimate,
     * storyOpenedBy,storyAssignedTo,storyClosedReason,storyVersion*/
    public Map<String, List<StoryCount>> report(String fields, ProductStory story) {
        if (fields == null || fields.equals("")) {
            return null;
        }
        Map<String, List<StoryCount>> map = new TreeMap<String, List<StoryCount>>(
                new Comparator<String>() {
                    public int compare(String obj1, String obj2) {
                        // 降序排序
                        return obj1.compareTo(obj2);
                    }
                });

        String newField = "";
        for (String field : fields.split(",")) {
            newField = field.substring(field.indexOf("_") + 1).trim();

            if ("A_productCount".equals(field.trim())) {
                map.put(field, productStoryDao.productStoryCount(story));
            } else if ("B_moduleCount".equals(field.trim())) {
                map.put(field, productStoryDao.modelStoryCount(story));
            } else if ("C_planCount".equals(field.trim())) {
                map.put(field, productStoryDao.planStoryCount(story, null));
            } else if ("I_storyOpenedBy".equals(field.trim()) || "G_storyAssignedTo".equals(field.trim())) {
                map.put(field, productStoryDao.userStoryCount(story, newField));
            } else {
                map.put(field, productStoryDao.fieldStoryCount(story, newField));
            }
        }

        return map;
    }

    public int countStatus(int productId, int status) {
        return productStoryDao.countStatus(productId, status);
    }

    public int[] deleteBatch(List<ProductStory> ids) {
        return productStoryDao.batchUpdateDel(ids);
    }

    public List<ProductStory> findProductNameByStoryId(Integer storyId) {
        return productStoryDao.findpNameBysId(storyId);
    }

    public Pager<ProductStory> findProjectLinkedStory(int start, int limit, ProductStory story, ConditionCarrier carrier, String columnName, boolean asc) {

        if (!StringUtil.isBlank(columnName)) {
            return productStoryDao.projectLinkedStory(start, limit, story, mergeCondition(carrier), new OrderBy(NameUtil.resolveNameDesc("productStory." + columnName), asc));
        }
        return productStoryDao.projectLinkedStory(start, limit, story, mergeCondition(carrier));
    }

    public Pager<ProductStory> findPager(int start, int limit, ProductStory story, ConditionCarrier carrier, Boolean ignoreDelete, String columnName, boolean asc) {
        if (!StringUtil.isBlank(columnName)) {
            return productStoryDao.complexQuery(start, limit, story, ignoreDelete, mergeCondition(carrier), new OrderBy(NameUtil.resolveNameDesc(columnName), asc));
        }
        return productStoryDao.complexQuery(start, limit, story, ignoreDelete, mergeCondition(carrier));
    }

    public Pager<ProductStory> findStoryByCondition(int start, int limit, ProductStory story, ConditionCarrier carrier, final String columnName, boolean asc) {
        return findPager(start, limit, story, carrier, true, columnName, asc);
    }

    public List<ProductStory> storyInCondition(String condition, Integer limit, Integer productId, Integer... ids) {
        return productStoryDao.storyInCondition(condition, limit, productId, ids);
    }

    private Condition mergeCondition(ConditionCarrier carrier) {
        return ConditionUtils.mergeCondition(carrier, new CallBackFunction() {
            public String moduleRoot(String moduleId) {
                return ModuleUtil.getConditionByRootWithoutOperate(Integer.parseInt(moduleId.substring(1)), "story");
            }

            public String module(String moduleId) {
                return ModuleUtil.getConditionWithoutOperate(Integer.parseInt(moduleId));
            }
        });
    }

    @Override
    public ProductStory findStoryByStoryId(Integer storyId) {
        return productStoryDao.findStoryByStoryId(storyId);
    }
}
