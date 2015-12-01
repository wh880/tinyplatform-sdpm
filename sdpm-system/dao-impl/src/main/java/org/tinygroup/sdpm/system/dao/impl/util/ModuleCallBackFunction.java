package org.tinygroup.sdpm.system.dao.impl.util;

import org.tinygroup.sdpm.system.dao.pojo.SystemModule;

/**
 * Created by wangll13383 on 2015/11/16.
 */
public interface ModuleCallBackFunction {
    SystemModule getModule(Integer moduleId);
}
