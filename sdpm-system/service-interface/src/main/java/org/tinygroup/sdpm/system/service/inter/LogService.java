package org.tinygroup.sdpm.system.service.inter;

import org.tinygroup.sdpm.system.dao.pojo.SystemAction;

/**
 * Created by wangll13383 on 2015/10/8.
 */
public interface LogService {
    public void log(SystemAction systemAction);
    public void log(Object oldObject, Object newObject, SystemAction systemAction);
}
