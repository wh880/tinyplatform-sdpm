package org.tinygroup.sdpm.product.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.common.condition.CallBackFunction;
import org.tinygroup.sdpm.common.condition.ConditionCarrier;
import org.tinygroup.sdpm.common.condition.ConditionUtils;
import org.tinygroup.sdpm.common.util.ComplexSearch.SearchInfos;
import org.tinygroup.sdpm.common.util.ComplexSearch.SqlUtil;
import org.tinygroup.sdpm.common.util.common.NameUtil;
import org.tinygroup.sdpm.product.biz.inter.StoryManager;
import org.tinygroup.sdpm.product.dao.ProductStoryDao;
import org.tinygroup.sdpm.product.dao.ProductStorySpecDao;
import org.tinygroup.sdpm.product.dao.impl.FieldUtil;
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.product.dao.pojo.ProductStorySpec;
import org.tinygroup.sdpm.product.dao.pojo.StoryCount;
import org.tinygroup.sdpm.system.dao.SystemModuleDao;
import org.tinygroup.sdpm.system.dao.impl.ModuleUtil;
import org.tinygroup.sdpm.system.dao.pojo.SystemModule;
import org.tinygroup.tinysqldsl.Pager;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
@Transactional
public class StoryManagerImpl implements StoryManager {

    @Autowired(required = false)
    private ProductStoryDao productStoryDao;
    @Autowired
    private ProductStorySpecDao storySpecDao;
    @Autowired
    private SystemModuleDao systemModuleDao;

    public ProductStory add(ProductStory story, ProductStorySpec storySpec) {
        Integer no = productStoryDao.getMaxNo(story.getProductId());
        story.setNo(no==null?1:no+1);
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

    public Pager<ProductStory> findPager(int start, int limit, ProductStory story, String statusCondition, SearchInfos conditions,
                                         String groupOperate, String columnName, boolean asc) {
        String condition = conditions != null ? SqlUtil.toSql(conditions.getInfos(), groupOperate) : "";

        condition = !StringUtil.isBlank(condition) ? (!StringUtil.isBlank(statusCondition) ? condition + " and "
                + statusCondition : " " + condition)
                : statusCondition;
        OrderBy orderBy = null;
        if (columnName != null && !"".equals(columnName)) {
            orderBy = new OrderBy("product_story." + NameUtil.resolveNameDesc(columnName), asc);
        }
        if (condition != null && !"".equals(condition)) {
            return productStoryDao.complexQueryRel(start, limit, story, condition,
                    orderBy);
        }
        return productStoryDao.queryPager(start, limit, story, orderBy);
    }

    public List<ProductStory> findList(ProductStory story) {

        return productStoryDao.query(story, null);
    }

    public Integer delete(Integer storyId) {

        return productStoryDao.softDelete(storyId);
    }

    public List<ProductStory> findList(Integer... storyId) {
        return productStoryDao.getByKeys(storyId);
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
        if (fields.equals("") || fields == null) {
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

    public Pager<ProductStory> findProjectLinkedStory(int start, int limit, ProductStory story, String condition, String columnName, boolean asc) {
        if (!StringUtil.isBlank(columnName)) {
            return productStoryDao.projectLinkedStory(start, limit, story, condition, new OrderBy(NameUtil.resolveNameDesc("productStory." + columnName), asc));
        }
        return productStoryDao.projectLinkedStory(start, limit, story, condition);
    }

    public Pager<ProductStory> findPager(int start, int limit, ProductStory story, String condition, String columnName, boolean asc) {

        if (!StringUtil.isBlank(columnName)) {
            return productStoryDao.complexQuery(start, limit, story, condition, new OrderBy(NameUtil.resolveNameDesc(columnName), asc));
        }
        return productStoryDao.complexQuery(start, limit, story, condition);
    }

    public Pager<ProductStory> findStoryByCondition(int start, int limit, ProductStory story, ConditionCarrier carrier, final String columnName, boolean asc) {
        carrier.completeModuleFunction(new CallBackFunction() {
            public boolean process(ConditionCarrier carrier, String field,String relation) {
                String result = "";
                if(ConditionUtils.CommonFieldType.MODULE.getOperate().equals(carrier.getFieldType(field))){
                    String moduleId = (String)carrier.getValue(field)[0];
                    if(moduleId.contains("p")){
                        result = ModuleUtil.getConditionByRoot(Integer.parseInt(moduleId.substring(1)), systemModuleDao);
                    }else{
                        result = ModuleUtil.getCondition(Integer.parseInt(moduleId.substring(1)), systemModuleDao);
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
        return findPager(start,limit,story,carrier.resultCondition(),columnName,asc);
    }
}
