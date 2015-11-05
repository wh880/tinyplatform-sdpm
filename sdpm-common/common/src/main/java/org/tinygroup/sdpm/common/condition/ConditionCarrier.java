package org.tinygroup.sdpm.common.condition;

import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.common.util.ComplexSearch.SearchInfos;
import org.tinygroup.sdpm.common.util.common.NameUtil;
import sun.swing.StringUIClientPropertyKey;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Created by wangll13383 on 2015/11/4.
 */
public class ConditionCarrier {
    private Map<String,String> operateMap;
    private Map<String,Object[]> valueMap;
    private Map<String,String> fieldTypeMap;
    private Map<String,String> conditionMap;
    private Map<String,String> conditionRelationMap;
    private Map<String,Boolean> fieldIsUsed;
    private CallBackFunction moduleFunction = null;
    private CallBackFunction customFunction = null;
    public static final String DEFAULT_RELATION = "and";
    public ConditionCarrier(){
        operateMap = new HashMap<String, String>();
        valueMap = new HashMap<String, Object[]>();
        fieldTypeMap = new HashMap<String, String>();
        conditionMap = new HashMap<String, String>();
        conditionRelationMap = new HashMap<String, String>();
        fieldIsUsed = new HashMap<String, Boolean>();
    }

    public ConditionCarrier(String field,String operate,boolean isFieldUsed,Object...value){
        operateMap.put(field,operate);
        valueMap.put(field,value);
        fieldIsUsed.put(field,isFieldUsed);
    }

    public ConditionCarrier(String field,String operate,String fieldType,boolean isFieldUsed,Object...value){
        fieldTypeMap.put(field,fieldType);
        operateMap.put(field,operate);
        valueMap.put(field,value);
        fieldIsUsed.put(field,isFieldUsed);
    }

    public void put(String field,String operate,String fieldType,boolean isFieldUsed,Object...value){
        fieldTypeMap.put(field,fieldType);
        operateMap.put(field,operate);
        valueMap.put(field,value);
        fieldIsUsed.put(field,isFieldUsed);
    }

    public void putSearch(String field,SearchInfos searchInfos,String groupOperate){
        fieldTypeMap.put(field,ConditionUtils.CommonFieldType.SEARCH.getOperate());
        operateMap.put(field,ConditionUtils.Operate.NO_OPERATE.getOperate());
        valueMap.put(field,new Object[]{searchInfos,groupOperate});
        fieldIsUsed.put(field,false);
    }

    public void putModuleIn(String field, String moduleId){
        fieldTypeMap.put(field,ConditionUtils.CommonFieldType.MODULE.getOperate());
        operateMap.put(field,ConditionUtils.Operate.IN.getOperate());
        valueMap.put(field,new Object[]{moduleId});
        fieldIsUsed.put(field,true);
    }

    public void putModuleNotIn(String field, String moduleId){
        fieldTypeMap.put(field,ConditionUtils.CommonFieldType.MODULE.getOperate());
        operateMap.put(field,ConditionUtils.Operate.NOT_IN.getOperate());
        valueMap.put(field,new Object[]{moduleId});
        fieldIsUsed.put(field,true);
    }

    public void putIdIn(String field,String[] ids){
        fieldTypeMap.put(field,ConditionUtils.CommonFieldType.ID.getOperate());
        operateMap.put(field,ConditionUtils.Operate.IN.getOperate());
        valueMap.put(field,new Object[]{ids});
        fieldIsUsed.put(field,true);
    }

    public void putIdNotIn(String field,String[] ids){
        fieldTypeMap.put(field,ConditionUtils.CommonFieldType.ID.getOperate());
        operateMap.put(field,ConditionUtils.Operate.NOT_IN.getOperate());
        valueMap.put(field,new Object[]{ids});
        fieldIsUsed.put(field,true);
    }

    public void putStatus(String field,String statusCondition){
        fieldTypeMap.put(field, ConditionUtils.CommonFieldType.STATUS.getOperate());
        operateMap.put(field,ConditionUtils.Operate.NO_OPERATE.getOperate());
        valueMap.put(field,new Object[]{statusCondition});
        fieldIsUsed.put(field,false);
    }


    public String getOperate(String field){
        return operateMap.get(field);
    }

    public Object[] getValue(String field){
        return valueMap.get(field);
    }

    public String getFieldType(String field){
        return fieldTypeMap.get(field);
    }

    public Set<String> getFields(){
        return operateMap.keySet();
    }

    public Integer size(){
        return operateMap.size();
    }

    public void setCondition(String field,String condition,String relation){
        conditionMap.put(field,condition);
        conditionRelationMap.put(field,StringUtil.isBlank(relation)?"":relation);
    }

    public String getCondition(String field){
        return conditionMap.get(field);
    }

    public Map<String,String> getConditionMap(){
        return conditionMap;
    }

    public void completeModuleFunction(CallBackFunction function){
        this.moduleFunction = function;
    }

    public void completeCustomFunction(CallBackFunction function){
        this.customFunction = function;
    }

    private void mergeCondition(){
        for(String field : getFields()){
            if(ConditionUtils.isSearch(this,field)){
                continue;
            }else if(ConditionUtils.isModule(this,field,moduleFunction)){
                continue;
            }else if(ConditionUtils.isId(this,field)){
                continue;
            }else if(ConditionUtils.isCustom(this,field,customFunction)){
                continue;
            }
        }
    }

    public String resultCondition(){
        mergeCondition();
        StringBuffer condition = new StringBuffer("");
        StringBuffer result = new StringBuffer("");
        for(String field : getFields()){
            if(!StringUtil.isBlank(conditionMap.get(field))) {
                if (!StringUtil.isBlank(condition.toString())) {
                    condition.append(" " + conditionRelationMap.get(field) + " ");
                }
                if(fieldIsUsed.get(field)){
                    condition.append(" "+NameUtil.resolveNameDesc(field)+" ").append(conditionMap.get(field));
                }else{
                    condition.append(conditionMap.get(field));
                }

            }
        }
        if(StringUtil.isBlank(condition.toString())){
            return "";
        }
        result.append("(").append(condition).append(")");
        return result.toString();
    }
}
