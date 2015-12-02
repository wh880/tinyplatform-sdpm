package org.tinygroup.sdpm.aop;

import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;
import org.springframework.core.annotation.AnnotationUtils;
import org.tinygroup.aopcache.annotation.CacheGet;
import org.tinygroup.aopcache.annotation.CachePut;
import org.tinygroup.aopcache.annotation.CacheRemove;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created by Hulk on 2015/12/2.
 */
public class AopCacheMatcherPointcutAdvisor extends StaticMethodMatcherPointcutAdvisor {
    private static final Class<? extends Annotation>[] CACHE_ANNOTATION_CLASSES =
            new Class[]{
                    CacheGet.class, CachePut.class, CacheRemove.class
            };

    /**
     * Returns <tt>true</tt> if the method has any aop cache annotations, false otherwise.
     * The annotations inspected are:
     * <ul>
     * <li>{@link CacheGet CachePut}</li>
     * <li>{@link CachePut CachePut}</li>
     * <li>{@link org.apache.shiro.authz.annotation.RequiresGuest RequiresGuest}</li>
     * <li>{@link org.apache.shiro.authz.annotation.RequiresRoles RequiresRoles}</li>
     * <li>{@link org.apache.shiro.authz.annotation.RequiresPermissions RequiresPermissions}</li>
     * </ul>
     *
     * @param method      the method to check for a aop cache annotation
     * @param targetClass the class potentially declaring aop cache annotations
     * @return <tt>true</tt> if the method has a aop cache annotation, false otherwise.
     * @see org.springframework.aop.MethodMatcher#matches(java.lang.reflect.Method, Class)
     */
    public boolean matches(Method method, Class targetClass) {
        Method m = method;
        if (isCacheAnnotationPresent(m)) {
            return true;
        }
        //The 'method' parameter could be from an interface that doesn't have the annotation.
        //Check to see if the implementation has it.
        if (targetClass != null) {
            try {
                m = targetClass.getMethod(m.getName(), m.getParameterTypes());
                if (isCacheAnnotationPresent(m)) {
                    return true;
                }
            } catch (NoSuchMethodException ignored) {
                //default return value is false.  If we can't find the method, then obviously
                //there is no annotation, so just use the default return value.
            }
        }
        return false;
    }

    private boolean isCacheAnnotationPresent(Method method) {
        for (Class<? extends Annotation> annClass : CACHE_ANNOTATION_CLASSES) {
            Annotation a = AnnotationUtils.findAnnotation(method, annClass);
            if (a != null) {
                return true;
            }
        }
        return false;
    }
}
