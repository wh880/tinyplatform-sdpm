package org.tinygroup.sdpm.common.util;

import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.common.util.common.NameUtil;


/**
 * Created by wangll13383 on 2015/10/17.
 */
public class OrderByUtil {

    public static OrderBy mergeOrderBy(String orderBy, boolean asc, String table) {
        if(StringUtil.isBlank(orderBy))return null;
        if(!StringUtil.isBlank(table))return new OrderBy(table+"."+ NameUtil.resolveNameDesc(orderBy),asc);
        return new OrderBy(NameUtil.resolveNameDesc(orderBy),asc);
    }

}
