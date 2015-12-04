package org.tinygroup.sdpm.system.service.inter;

import org.tinygroup.sdpm.system.dao.pojo.SystemAction;

/**
 * Created by wangll13383 on 2015/10/8.
 */
public interface LogService {
    /**
     * 记录动态日志
     * @param oldObject
     * @param newObject
     * @param systemAction
     */
    void log(Object oldObject, Object newObject, SystemAction systemAction);
}
