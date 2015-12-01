package org.tinygroup.sdpm.util;

import org.tinygroup.context.Context;
import org.tinygroup.context.impl.ContextImpl;

/**
 * Created by wangll13383 on 2015/9/28.
 */
public interface CallBackFunction {
    Context context = new ContextImpl();

    public Context getContext();
}
