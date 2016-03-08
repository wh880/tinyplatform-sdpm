package org.tinygroup.sdpm.action.project.util;


import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.dao.condition.ConditionCarrier;
import org.tinygroup.sdpm.dao.condition.ConditionUtils;

/**
 * Created by shenly13343 on 2015-09-24.
 */

//0-等待，1-未开始，2-进行中，3-已完成，4-已暂停，5-已取消,6-已关闭
public class TaskStatusUtil {


    public static String getCondition(String statu, String choose, String userId, ConditionCarrier carrier) {
        String condition;
        if (!StringUtil.isBlank(statu)) {//&& StringUtil.isBlank(choose)
            switch (Integer.parseInt(statu)) {
                case 1:
                    return "completeByMe";
                case 2:
                    carrier.put("taskStatus",
                            ConditionUtils.Operate.EQ.getOperate(),
                            ConditionUtils.CommonFieldType.FIELD_OPERATE.getCommonField(),
                            1);
                    break;
                case 3:
                    carrier.put("taskStatus",
                            ConditionUtils.Operate.EQ.getOperate(),
                            ConditionUtils.CommonFieldType.FIELD_OPERATE.getCommonField(),
                            2);
                    break;
                case 4:
                    carrier.put("taskStatus",
                            ConditionUtils.Operate.NEQ.getOperate(),
                            ConditionUtils.CommonFieldType.FIELD_OPERATE.getCommonField(),
                            3);
                    break;
                case 5:
                    carrier.put("taskStatus",
                            ConditionUtils.Operate.EQ.getOperate(),
                            ConditionUtils.CommonFieldType.FIELD_OPERATE.getCommonField(),
                            3);
                    break;
                case 6:
                    carrier.put("taskStatus",
                            ConditionUtils.Operate.EQ.getOperate(),
                            ConditionUtils.CommonFieldType.FIELD_OPERATE.getCommonField(),
                            6);
                    break;
                case 7:
                    String[] ids = "1,2,4".split(",");
                    carrier.put("taskStatus",
                            ConditionUtils.Operate.IN.getOperate(),
                            ConditionUtils.CommonFieldType.INS.getCommonField(),
                            ids);
                    break;
                case 8:
                    break;
                case 9:
                    carrier.put("taskStatus",
                            ConditionUtils.Operate.EQ.getOperate(),
                            ConditionUtils.CommonFieldType.FIELD_OPERATE.getCommonField(),
                            5);
                    break;
                case 0:
                    carrier.put("taskStatus",
                            ConditionUtils.Operate.NEQ.getOperate(),
                            ConditionUtils.CommonFieldType.FIELD_OPERATE.getCommonField(),
                            6);
                    break;
            }
        } else if (!StringUtil.isBlank(choose)) {
            /**
             * choose = 1 未关闭
             * choose = 2 所有
             * choose = 7 指派给我
             */
            if ("1".equals(choose)) {
                carrier.put("taskStatus",
                        ConditionUtils.Operate.NEQ.getOperate(),
                        ConditionUtils.CommonFieldType.FIELD_OPERATE.getCommonField(),
                        6);
            } else if ("7".equals(choose)) {
                carrier.put("taskAssignedTo",
                        ConditionUtils.Operate.EQ.getOperate(),
                        ConditionUtils.CommonFieldType.FIELD_OPERATE.getCommonField(),
                        userId);
            }
        }
        return "";

    }
}
