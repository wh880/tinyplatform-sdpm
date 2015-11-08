package org.tinygroup.sdpm.common.condition;

/**
 * Created by wangll13383 on 2015/11/5.
 */
public interface CallBackFunction {
    boolean process(ConditionCarrier carrier,String field,String relation);
}
