package org.tinygroup.sdpm.dao.condition;

/**
 * Created by wangll13383 on 2015/11/5.
 */
public interface CallBackFunction {
    String moduleRoot(String moduleId);

    String module(String moduleId);
}
