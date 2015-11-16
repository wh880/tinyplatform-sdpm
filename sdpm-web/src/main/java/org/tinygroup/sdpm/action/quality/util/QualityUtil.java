package org.tinygroup.sdpm.action.quality.util;

import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.sdpm.util.UserUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangll13383 on 2015/10/12.
 */
public class QualityUtil {
    private static Map<String,Integer> statusMap = new HashMap<String, Integer>();

    static {
        statusMap.put("tbugstatus",1);
        statusMap.put("tbugall",2);
        statusMap.put("tbugassigntome",3);
        statusMap.put("tbugbuildbyme",4);
        statusMap.put("tbugsolvebyme",5);
        statusMap.put("tbugnotsure",6);
        statusMap.put("tbugnotassign",7);
        statusMap.put("tbugnotsolve",8);
        statusMap.put("tbuglong",9);
        statusMap.put("tbugtimeup",10);
        statusMap.put("tbugneedchange",11);
    }

    public static String getCondition(String status, HttpServletRequest request){
        if("".equals(status)||status==null)return "";
        if(status == null||"".equals(status))return null;
        switch (statusMap.get(status)){
            case 1:return " bug_status <> 3";
            case 2:return "";
            case 3:return " bug_assigned_to = "+(UserUtils.getUserId() == null?"0":"'"+UserUtils.getUserId())+"' ";
            case 4:return " bug_opened_by = "+(UserUtils.getUserId() == null?"0":"'"+UserUtils.getUserId())+"' ";
            case 5:return " bug_resolved_by = "+(UserUtils.getUserId() == null?"0":"'"+UserUtils.getUserId())+"' ";
            case 6:return " (bug_confirmed <> 1 or bug_confirmed is null)";
            case 7:return " bug_assigned_to is null";
            case 8:return " bug_status = 1";
            case 9:return " bug_status = 1";
            case 10:return " bug_resolution = 6";
            case 11:return "";

        }
        return "";
    }
}
