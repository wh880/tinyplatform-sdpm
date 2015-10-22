package org.tinygroup.sdpm.action.product.util;

import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wangll13383 on 2015/9/27.
 */
public class StoryUtil {
    public static String getStatusCondition(String choose, HttpServletRequest request){
    	if(StringUtil.isBlank(choose)){
    		return "";
    	}
        OrgUser user = (OrgUser) request.getSession().getAttribute("orgUser");
        if(choose == null||"".equals(choose))return null;
        switch (Integer.valueOf(choose)){
            case 1:return "story_status <> 'closed'";
            case 2:return "";
            case 3:return user != null?"story_assignedTo="+user.getOrgUserId():"";
            case 4:return user != null?"story_openedBy="+user.getOrgUserId():"";
            case 5:return user != null?"story_reviewedBy="+user.getOrgUserId():"";
            case 6:return user != null?"story_closedBy="+user.getOrgUserId():"";
            case 7:return "story_status='Draft'";
            case 8:return "story_status='Active'";
            case 9:return "story_status='changed'";
            case 10:return "story_status='Acceptance'";
            case 11:return "story_status='closed'";
        }
        return "";
    }
    

    

}
