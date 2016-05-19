package org.tinygroup.sdpm.action.quality.util;

import org.tinygroup.sdpm.dao.condition.ConditionCarrier;
import org.tinygroup.sdpm.dao.condition.ConditionUtils;
import org.tinygroup.sdpm.quality.dao.pojo.QualityBug;
import org.tinygroup.sdpm.util.UserUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangll13383 on 2015/10/12.
 */
public class QualityUtil {
    private static Map<String, Integer> statusMap = new HashMap<String, Integer>();

    static {
        statusMap.put("tbugstatus", 1);
        statusMap.put("tbugall", 2);
        statusMap.put("tbugassigntome", 3);
        statusMap.put("tbugbuildbyme", 4);
        statusMap.put("tbugsolvebyme", 5);
        statusMap.put("tbugnotsure", 6);
        statusMap.put("tbugnotassign", 7);
        statusMap.put("tbugnotsolve", 8);
        statusMap.put("tbuglong", 9);
        statusMap.put("tbugtimeup", 10);
        statusMap.put("tbugneedchange", 11);
        statusMap.put("tBugDeleted", 12);
        statusMap.put("tbugsolved", 13);
        statusMap.put("tbugReActived", 14);
    }

    public static void getCondition(String status, ConditionCarrier carrier) {
        if (status == null || "".equals(status)) return;
        switch (statusMap.get(status)) {
            case 1:
                carrier.put("bugStatus",
                        ConditionUtils.Operate.NEQ.getOperate(),
                        ConditionUtils.CommonFieldType.FIELD_OPERATE.getCommonField(),
                        "3");
                break;
            case 2:
                break;
            case 3:
                carrier.put("bugAssignedTo",
                        ConditionUtils.Operate.EQ.getOperate(),
                        ConditionUtils.CommonFieldType.FIELD_OPERATE.getCommonField(),
                        UserUtils.getUserId());
                break;
            case 4:
                carrier.put("bugOpenedBy",
                        ConditionUtils.Operate.EQ.getOperate(),
                        ConditionUtils.CommonFieldType.FIELD_OPERATE.getCommonField(),
                        UserUtils.getUserId());
                break;
            case 5:
                carrier.put("bugResolvedBy",
                        ConditionUtils.Operate.EQ.getOperate(),
                        ConditionUtils.CommonFieldType.FIELD_OPERATE.getCommonField(),
                        UserUtils.getUserId());
                break;
            case 6:
                carrier.putStatus("status", " (bug_confirmed <> 1 or bug_confirmed is null)");
                break;
            case 7:
                carrier.putStatus("status", " bug_assigned_to is null or bug_assigned_to= ''");
                break;
            case 8:
                carrier.put("bugStatus",
                        ConditionUtils.Operate.EQ.getOperate(),
                        ConditionUtils.CommonFieldType.FIELD_OPERATE.getCommonField(),
                        "1");
                break;
            case 9:
                carrier.put("bugStatus",
                        ConditionUtils.Operate.EQ.getOperate(),
                        ConditionUtils.CommonFieldType.FIELD_OPERATE.getCommonField(),
                        "1");
                break;
            case 10:
                carrier.put("bugResolution",
                        ConditionUtils.Operate.EQ.getOperate(),
                        ConditionUtils.CommonFieldType.FIELD_OPERATE.getCommonField(),
                        "6");
                break;
            case 11:
                break;
            case 12:
                carrier.put("bugStatus",
                        ConditionUtils.Operate.EQ.getOperate(),
                        ConditionUtils.CommonFieldType.FIELD_OPERATE.getCommonField(),
                        QualityBug.STATUS_CLOSED);
                break;
            case 13:
                carrier.put("bugStatus",
                        ConditionUtils.Operate.EQ.getOperate(),
                        ConditionUtils.CommonFieldType.FIELD_OPERATE.getCommonField(),
                        QualityBug.STATUS_RESOLVED);
                break;
            case 14:
                carrier.put("bugActivatedCount",
                        ConditionUtils.Operate.GT.getOperate(),
                        ConditionUtils.CommonFieldType.FIELD_OPERATE.getCommonField(),
                        0);
                break;
        }
        return;
    }
}
