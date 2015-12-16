package org.tinygroup.sdpm.action.project.util;


import org.tinygroup.commons.tools.StringUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shenly13343 on 2015-09-24.
 */

//0-等待，1-未开始，2-进行中，3-已完成，4-已暂停，5-已取消,6-已关闭
public class TaskStatusUtil {
    private static Map<String, String> status = new HashMap<String, String>();

    static {
        status.put("1", "completeByMe");
        status.put("2", "task_status = 1");
        status.put("3", "task_status = 2");
        status.put("4", "task_status != 3 ");
        status.put("5", "task_status = 3");
        status.put("6", "task_status = 6");
        status.put("7", "overTime");//已延期
        status.put("8", "");//需求变动
        status.put("9", "task_status = 5");
        status.put("0", "task_status !=6");
    }

    public static String getCondition(String statu, String choose, String userId, String moduleIds) {
        String condition;
        if (!StringUtil.isBlank(statu)) {//&& StringUtil.isBlank(choose)
            condition = status.get(statu);
        } else if (!StringUtil.isBlank(choose)) {
            /**
             * choose = 1 未关闭
             * choose = 2 所有
             * choose = 7 指派给我
             */
            if ("1".equals(choose)) {
                condition = "task_status != 6";
            } else if ("2".equals(choose)) {
                condition = "";
            } else if ("7".equals(choose)) {
                condition = "task_assigned_to = '" + userId + "'";
            } else {
                condition = "";
            }
        } else {
            condition = "";
        }

        if (!StringUtil.isBlank(moduleIds)) {
            if (StringUtil.isBlank(condition)) {
                condition = "task_module " + moduleIds;
            } else {
                condition = condition + " and task_module " + moduleIds;
            }
        }
        return condition;
    }
}
