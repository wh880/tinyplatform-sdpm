package org.tinygroup.sdpm.action.product.util;

import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.util.UserUtils;

/**
 * Created by wangll13383 on 2015/9/27.
 */
public class StoryUtil {
    public static String getStatusCondition(String choose){
    	if(StringUtil.isBlank(choose)){
    		return "";
    	}
        if(choose == null||"".equals(choose))return null;
        switch (Integer.valueOf(choose)){
            case 1:return "story_status <> '2'";
            case 2:return "";
            case 3:return "story_assigned_to= '"+ UserUtils.getUserId()+"' ";
            case 4:return "story_opened_by= '"+ UserUtils.getUserId()+"' ";
            case 5:return "story_reviewed_by= '"+ UserUtils.getUserId()+"' ";
            case 6:return "story_closed_by= '"+ UserUtils.getUserId()+"' ";
            case 7:return "story_status='0'";
            case 8:return "story_status='1'";
            case 9:return "story_status='3'";
            case 11:return "story_status='2'";
        }
        return "";
    }
    

    

}
