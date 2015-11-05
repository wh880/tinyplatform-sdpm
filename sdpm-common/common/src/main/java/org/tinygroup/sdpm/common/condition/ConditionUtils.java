package org.tinygroup.sdpm.common.condition;

import com.sun.tools.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.common.util.ComplexSearch.SearchInfos;
import org.tinygroup.sdpm.common.util.ComplexSearch.SqlUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangll13383 on 2015/11/4.
 */
public class ConditionUtils {
    private static Map<String,String> operateBefore = new HashMap<String, String>();
    private static Map<String,String> operateAfter = new HashMap<String, String>();
    private static final Integer DEFAULT_VALUE = 0;
    static{
        operateBefore.put(Operate.IN.getOperate(),"in(");
        operateAfter.put(Operate.IN.getOperate(),")");

        operateBefore.put(Operate.NOT_IN.getOperate(),"not in(");
        operateAfter.put(Operate.NOT_IN.getOperate(),")");

        operateBefore.put(Operate.NO_OPERATE.getOperate(),"");
        operateAfter.put(Operate.NO_OPERATE.getOperate(),"");
    }

    public enum Operate{
        IN("in"),
        NOT_IN("not_in"),
        NO_OPERATE("");
        private String operate;
        Operate(String operate){
            this.operate=operate;
        }
        public String getOperate(){
            return this.operate;
        }
    }

    public enum CommonFieldType{
        SEARCH("search"),
        MODULE("module"),
        STATUS("status"),
        ID("id");
        private String commonField;
        CommonFieldType(String commonField){
            this.commonField=commonField;
        }
        public String getOperate(){
            return this.commonField;
        }
    }

//    public static void mergeConditionWithOperate(ConditionCarrier carrier,String field,String relationOperator, boolean isWithDefault,CallBackFunction function){
//        StringBuffer result = new StringBuffer("");
//        String subCondition = function.process(carrier,field);
//        if(StringUtil.isBlank(subCondition)){
//            if(isWithDefault){
//                result.append(operateBefore.get(carrier.getOperate(field))).append(DEFAULT_VALUE).append(operateAfter.get(carrier.getOperate(field)));
//            }
//        }else{
//            result.append(operateBefore.get(carrier.getOperate(field))).append(subCondition).append(operateAfter.get(carrier.getOperate(field)));
//        }
//        carrier.setCondition(field,result.toString(),relationOperator);
//    }
//
//    public static void mergeConditionWithOutOperate(ConditionCarrier carrier,String field,String relationOperator,boolean isWithDefault,CallBackFunction function){
//        String subCondition = function.process(carrier,field);
//        if(StringUtil.isBlank(subCondition)){
//            if(isWithDefault){
//                subCondition = String.valueOf(DEFAULT_VALUE);
//            }
//        }
//        carrier.setCondition(field,StringUtil.isBlank(subCondition)?"":subCondition,relationOperator);
//    }

    public static boolean isSearch(ConditionCarrier carrier, String field){
        if(CommonFieldType.SEARCH.getOperate().equals(carrier.getFieldType(field))){
            Object[] values = carrier.getValue(field);
            SearchInfos searchInfos = null;
            String groupOperator = null;
            for(Object value : values){
                if(value instanceof SearchInfos){
                    searchInfos = (SearchInfos)value;
                }else if(value instanceof String){
                    groupOperator = (String)value;
                }
            }
            if(searchInfos==null){
                carrier.setCondition(field,"",carrier.DEFAULT_RELATION);
            }else{
               carrier.setCondition(field,
                       operateBefore.get(carrier.getOperate(field))+SqlUtil.toSql(searchInfos.getInfos(),groupOperator)+operateAfter.get(carrier.getOperate(field)),
                       carrier.DEFAULT_RELATION);
            }
            return true;
        }
        return false;
    }

    public static boolean isModule(ConditionCarrier carrier, String field, CallBackFunction function){
        if(function == null)return false;
        return function.process(carrier,field,carrier.DEFAULT_RELATION);
    }

    public static boolean isCustom(ConditionCarrier carrier, String field, CallBackFunction function){
        if(function == null)return false;
        return function.process(carrier,field,null);
    }

    public static boolean isId(ConditionCarrier carrier, String field){
        if(CommonFieldType.ID.getOperate().equals(carrier.getFieldType(field))){
            String result = "";
            if(carrier.getValue(field)!=null){
                String[] value = (String[])carrier.getValue(field)[0];
                if(value!=null&&value.length>0){
                    result = StringUtil.join(value,",");
                }
            }
            if(StringUtil.isBlank(result.toString())){
                carrier.setCondition(field,
                        operateBefore.get(carrier.getOperate(field))+DEFAULT_VALUE+operateAfter.get(carrier.getOperate(field)),
                        carrier.DEFAULT_RELATION);
            }else{
                carrier.setCondition(field,
                        operateBefore.get(carrier.getOperate(field))+result.toString()+operateAfter.get(carrier.getOperate(field)),
                        carrier.DEFAULT_RELATION);
            }
            return true;
        }
        return false;
    }

    public static ConditionCarrier mergeCarrier(String moduleField,String moduleId){
        ConditionCarrier carrier = new ConditionCarrier();
        carrier.putModuleIn(moduleField,moduleId);
        return carrier;
    }

    public static ConditionCarrier mergeCarrier(SearchInfos searchInfos,String groupOperator){
        ConditionCarrier carrier = new ConditionCarrier();
        carrier.putSearch("search",searchInfos,groupOperator);
        return carrier;
    }

    public static ConditionCarrier mergeCarrier(String idField,String[] ids){
        ConditionCarrier carrier = new ConditionCarrier();
        carrier.putIdIn(idField, ids);
        return carrier;
    }

    public static ConditionCarrier mergeCarrier(SearchInfos searchInfos,String groupOperator,String moduleField,String moduleId,String idField,String[] ids){
        ConditionCarrier carrier = mergeCarrier(moduleField,moduleId);
        carrier.putSearch("search", searchInfos, groupOperator);
        carrier.putIdIn(idField,ids);
        return carrier;
    }

    public static ConditionCarrier mergeCarrier(String moduleField,String moduleId,String idField,String[] ids){
        ConditionCarrier carrier = mergeCarrier(moduleField,moduleId);
        carrier.putIdIn(idField,ids);
        return carrier;
    }

    public static ConditionCarrier mergeCarrier(SearchInfos searchInfos,String groupOperator,String moduleField,String moduleId){
        ConditionCarrier carrier = mergeCarrier(moduleField,moduleId);
        carrier.putSearch("search",searchInfos,groupOperator);
        return carrier;
    }
}

