package org.tinygroup.sdpm.action.quality.util;

import org.tinygroup.sdpm.org.dao.pojo.OrgUser;

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
        statusMap.put("storyChange",11);
    }

    public static String getCondition(String status, HttpServletRequest request){
        OrgUser user = (OrgUser) request.getSession().getAttribute("user");
        if(status == null||"".equals(status))return null;
        switch (statusMap.get(status)){
            case 1:return " bug_status <> 3";
            case 2:return "";
            case 3:return " bug_assigned_to = "+(user == null?"0":user.getOrgUserId())+" ";
            case 4:return " bug_opened_by = "+(user == null?"0":user.getOrgUserId())+" ";
            case 5:return " bug_resolved_by = "+(user == null?"0":user.getOrgUserId())+" ";
            case 6:return " (bug_confirmed <> 1 or bug_confirmed is null)";
            case 7:return " bug_assigned_to is null";
            case 8:return " bug_status <> 2";
            case 9:return " bug_status = 1";
            case 10:return "";
            case 11:return "";

        }
        return "";
    }
}
