package org.tinygroup.sdpm.dao.condition;

import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.common.util.common.NameUtil;
import org.tinygroup.sdpm.dao.complexsearch.SearchInfos;
import org.tinygroup.sdpm.dao.complexsearch.SqlUtil;
import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Condition;
import org.tinygroup.tinysqldsl.base.FragmentSql;
import org.tinygroup.tinysqldsl.base.StatementSqlBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangll13383 on 2015/11/4.
 */
public class ConditionUtils {

    public static Condition mergeCondition(ConditionCarrier carrier, CallBackFunction callBackFunction) {
        if (carrier == null) return null;
        List<Condition> conditions = new ArrayList<Condition>();
        for (String field : carrier.getFields()) {
            Condition condition = null;
            Column column = new Column(NameUtil.resolveNameDesc(field));
            if (carrier.getFieldType(field).equals(ConditionUtils.CommonFieldType.MODULE.getCommonField())) {
                boolean isIn = carrier.getOperate(field).equals(ConditionUtils.Operate.IN.getOperate());
                String moduleId = (String) carrier.getValue(field)[0];
                if (moduleId.contains("p")) {
                    if (isIn) {
                        condition = column.in(callBackFunction.moduleRoot(moduleId));
                    } else {
                        condition = column.notIn(callBackFunction.moduleRoot(moduleId));
                    }
                } else {
                    String module = callBackFunction.module(moduleId);
                    String[] split;
                    if (module.contains(",")) {
                        split = module.split(",");
                    } else {
                        split = new String[]{module};
                    }
                    if (isIn) {
                        condition = column.in(split);
                    } else {
                        condition = column.notIn(split);
                    }
                }
            } else if (carrier.getFieldType(field).equals(ConditionUtils.CommonFieldType.SEARCH.getCommonField())) {
                if (carrier.getValue(field)[0] != null) {
                    String result = SqlUtil.toSql(((SearchInfos) carrier.getValue(field)[0]).getInfos(), (String) carrier.getValue(field)[1]);
                    if (!StringUtil.isBlank(result)) {
                        condition = FragmentSql.fragmentCondition(result);
                    }
                }
            } else if (carrier.getFieldType(field).equals(ConditionUtils.CommonFieldType.INS.getCommonField())) {
                boolean isIn = carrier.getOperate(field).equals(ConditionUtils.Operate.IN.getOperate());

                String[] result = (String[]) carrier.getValue(field)[0];
                String[] value = {"0"};
                result = result != null && result.length > 0 ? result : value;
                if (isIn) {
                    condition = column.in(result);
                } else {
                    condition = column.notIn(result);
                }
            } else if (carrier.getFieldType(field).equals(ConditionUtils.CommonFieldType.FIELD_OPERATE.getCommonField())) {
                String result = String.valueOf(carrier.getValue(field)[0]);
                if (!StringUtil.isBlank(result)) {
                    if (carrier.getOperate(field).equals(ConditionUtils.Operate.EQ.getOperate())) {
                        condition = column.eq(result);
                    } else if (carrier.getOperate(field).equals(ConditionUtils.Operate.NEQ.getOperate())) {
                        condition = column.neq(result);
                    } else if (carrier.getOperate(field).equals(ConditionUtils.Operate.GT.getOperate())) {
                        condition = column.gt(result);
                    } else if (carrier.getOperate(field).equals(ConditionUtils.Operate.LT.getOperate())) {
                        condition = column.lt(result);
                    }
                }
            } else if (carrier.getFieldType(field).equals(ConditionUtils.CommonFieldType.STATUS.getCommonField())) {
                String result = String.valueOf(carrier.getValue(field)[0]);
                if (!StringUtil.isBlank(result)) {
                    condition = FragmentSql.fragmentCondition(result);
                }
            }
            conditions.add(condition);
        }
        if (conditions.size() == 0) {
            return null;
        } else if (conditions.size() == 1) {
            return conditions.get(0);
        }
        Condition[] conditionArray = new Condition[conditions.size()];
        return StatementSqlBuilder.and(conditions.toArray(conditionArray));
    }

    public enum Operate {
        GT("greater_than"),
        LT("less_than"),
        EQ("eq"),
        NEQ("not_eq"),
        IN("in"),
        NOT_IN("not_in"),
        LIKE("like"),
        NOT_LIKE("not_like"),
        NO_OPERATE("");
        private String operate;

        Operate(String operate) {
            this.operate = operate;
        }

        public String getOperate() {
            return this.operate;
        }
    }

    public enum CommonFieldType {
        FIELD_OPERATE("field_operate"),
        SEARCH("search"),
        MODULE("module"),
        STATUS("status"),
        INS("ins");
        private String commonField;

        CommonFieldType(String commonField) {
            this.commonField = commonField;
        }

        public String getCommonField() {
            return this.commonField;
        }
    }
}

