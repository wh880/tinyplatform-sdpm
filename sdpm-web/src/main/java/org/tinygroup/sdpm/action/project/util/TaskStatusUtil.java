package org.tinygroup.sdpm.action.project.util;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by shenly13343 on 2015-09-24.
 */

//0-等待，1-未开始，2-进行中，3-已完成，4-已暂停，5-已取消,6-已关闭
public class TaskStatusUtil {
    private static Map<String, String> status = new HashMap<String, String>();

    static {
        //status.put("1","statu = 2");
        status.put("2", "task_status = 1");
        status.put("3", "task_status = 2");
        status.put("4", "task_status != 3 ");
        status.put("5", "task_status = 3");
        status.put("6", "task_status = 6");
        status.put("7", "");//已延期
        status.put("8", "");//需求变动
        status.put("9", "task_status = 5");
    }

    public static String getCondition(String statu, String choose) {
        if (statu != null && choose == null) {
            return status.get(statu);
        } else if (statu == null && choose != null) {
            return null;

        } else return null;

    }
}