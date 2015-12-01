package org.tinygroup.sdpm.common.condition;

import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Condition;

/**
 * Created by wangll13383 on 2015/11/5.
 */
public interface CallBackFunction {
    String moduleRoot(String moduleId);
    String module(String moduleId);
}
