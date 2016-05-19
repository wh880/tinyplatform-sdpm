package org.tinygroup.sdpm.util;

import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.dao.condition.ConditionCarrier;
import org.tinygroup.sdpm.dao.condition.ConditionUtils;

/**
 * Created by wangll13383 on 2015/9/27.
 */
public class StoryUtil {
    public static void getStatusCondition(String choose, ConditionCarrier carrier) {
        if (StringUtil.isBlank(choose)) {
            return;
        }
        if (choose == null || "".equals(choose)) return;
        switch (Integer.valueOf(choose)) {
            case 1:
                carrier.put("productStory.storyStatus",
                        ConditionUtils.Operate.NEQ.getOperate(),
                        ConditionUtils.CommonFieldType.FIELD_OPERATE.getCommonField(),
                        "2");
                break;
            case 2:
                return;
            case 3:
                carrier.put("productStory.storyAssignedTo",
                        ConditionUtils.Operate.EQ.getOperate(),
                        ConditionUtils.CommonFieldType.FIELD_OPERATE.getCommonField(),
                        UserUtils.getUserId());
                break;
            case 4:
                carrier.put("productStory.storyOpenedBy",
                        ConditionUtils.Operate.EQ.getOperate(),
                        ConditionUtils.CommonFieldType.FIELD_OPERATE.getCommonField(),
                        UserUtils.getUserId());
                break;
            case 5:
                carrier.put("productStory.storyReviewedBy",
                        ConditionUtils.Operate.EQ.getOperate(),
                        ConditionUtils.CommonFieldType.FIELD_OPERATE.getCommonField(),
                        UserUtils.getUserId());
                break;
            case 6:
                carrier.put("productStory.storyClosedBy",
                        ConditionUtils.Operate.EQ.getOperate(),
                        ConditionUtils.CommonFieldType.FIELD_OPERATE.getCommonField(),
                        UserUtils.getUserId());
                carrier.put("productStory.storyStatus",
                        ConditionUtils.Operate.EQ.getOperate(),
                        ConditionUtils.CommonFieldType.FIELD_OPERATE.getCommonField(),
                        "2");
                break;
            case 7:
                carrier.put("productStory.storyStatus",
                        ConditionUtils.Operate.EQ.getOperate(),
                        ConditionUtils.CommonFieldType.FIELD_OPERATE.getCommonField(),
                        "0");
                break;
            case 8:
                carrier.put("productStory.storyStatus",
                        ConditionUtils.Operate.EQ.getOperate(),
                        ConditionUtils.CommonFieldType.FIELD_OPERATE.getCommonField(),
                        "1");
                break;
            case 9:
                carrier.put("productStory.storyStatus",
                        ConditionUtils.Operate.EQ.getOperate(),
                        ConditionUtils.CommonFieldType.FIELD_OPERATE.getCommonField(),
                        "3");
                break;
            case 11:
                carrier.put("productStory.storyStatus",
                        ConditionUtils.Operate.EQ.getOperate(),
                        ConditionUtils.CommonFieldType.FIELD_OPERATE.getCommonField(),
                        "2");
                break;
        }
        return;
    }


}
