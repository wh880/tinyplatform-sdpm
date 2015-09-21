package org.tinygroup.sdpm.common.log;

import java.lang.annotation.*;

/**
 * Created by wangll13383 on 2015/9/21.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogClass {
    String value() default "";
}
