package org.tinygroup.sdpm.dao.condition;

import org.tinygroup.sdpm.dao.complexsearch.SearchInfos;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by wangll13383 on 2015/11/4.
 */
public class ConditionCarrier implements Serializable{
    private Map<String, String> operateMap;
    private Map<String, Object[]> valueMap;
    private Map<String, String> fieldTypeMap;

    public ConditionCarrier() {
        operateMap = new HashMap<String, String>();
        valueMap = new HashMap<String, Object[]>();
        fieldTypeMap = new HashMap<String, String>();
    }

    public ConditionCarrier(String field, String operate, Object... value) {
        operateMap.put(field, operate);
        valueMap.put(field, value);
    }

    public ConditionCarrier(String field, String operate, String fieldType, Object... value) {
        fieldTypeMap.put(field, fieldType);
        operateMap.put(field, operate);
        valueMap.put(field, value);
    }

    public void put(String field, String operate, String fieldType, Object... value) {
        fieldTypeMap.put(field, fieldType);
        operateMap.put(field, operate);
        valueMap.put(field, value);
    }

    public void putSearch(String field, SearchInfos searchInfos, String groupOperate) {
        fieldTypeMap.put(field, ConditionUtils.CommonFieldType.SEARCH.getCommonField());
        operateMap.put(field, ConditionUtils.Operate.NO_OPERATE.getOperate());
        valueMap.put(field, new Object[]{searchInfos, groupOperate});
    }

    public void putModuleIn(String field, String moduleId) {
        fieldTypeMap.put(field, ConditionUtils.CommonFieldType.MODULE.getCommonField());
        operateMap.put(field, ConditionUtils.Operate.IN.getOperate());
        valueMap.put(field, new Object[]{moduleId});
    }

    public void putModuleNotIn(String field, String moduleId) {
        fieldTypeMap.put(field, ConditionUtils.CommonFieldType.MODULE.getCommonField());
        operateMap.put(field, ConditionUtils.Operate.NOT_IN.getOperate());
        valueMap.put(field, new Object[]{moduleId});
    }

    public void putIns(String field, String[] ids) {
        fieldTypeMap.put(field, ConditionUtils.CommonFieldType.INS.getCommonField());
        operateMap.put(field, ConditionUtils.Operate.IN.getOperate());
        valueMap.put(field, new Object[]{ids});
    }

    public void putNotIns(String field, String[] ids) {
        fieldTypeMap.put(field, ConditionUtils.CommonFieldType.INS.getCommonField());
        operateMap.put(field, ConditionUtils.Operate.NOT_IN.getOperate());
        valueMap.put(field, new Object[]{ids});
    }

    public void putStatus(String field, String statusCondition) {
        fieldTypeMap.put(field, ConditionUtils.CommonFieldType.STATUS.getCommonField());
        operateMap.put(field, ConditionUtils.Operate.NO_OPERATE.getOperate());
        valueMap.put(field, new Object[]{statusCondition});
    }

    public String getOperate(String field) {
        return operateMap.get(field);
    }

    public Object[] getValue(String field) {
        return valueMap.get(field);
    }

    public String getFieldType(String field) {
        return fieldTypeMap.get(field);
    }

    public Set<String> getFields() {
        return operateMap.keySet();
    }

    public Integer size() {
        return operateMap.size();
    }



}
