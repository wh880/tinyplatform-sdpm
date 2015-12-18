package org.tinygroup.sdpm.security;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.project.service.inter.TeamService;
import org.tinygroup.sdpm.util.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by Hulk on 2015/11/13.
 */
@Aspect
@Service
public class PermissionInterceptor {

    @Autowired
    TeamService teamService;

    @Before("execution(* org.tinygroup.sdpm.action.project.*.*(..))")
    public void projectInterceptor(JoinPoint joinPoint) {
        HttpServletRequest request = Servlets.getRequest();
        String projectId = CookieUtils.getCookie(request, ProjectOperate.COOKIE_PROJECT_ID);
        if (StringUtil.isBlank(projectId)) {
            return;
        }
        List<String> menuList = teamService.getMenuIdListByProjectAndUser(Integer.valueOf(projectId), UserUtils.getUserId());
        MenuPermissionSubject subject = new MenuPermissionSubject();
        subject.setMenuList(menuList);
        interceptor(joinPoint, subject);
    }

    @Before("execution(* org.tinygroup.sdpm.action.product.*.*(..))")
    public void productInterceptor(JoinPoint joinPoint) {
        HttpServletRequest request = Servlets.getRequest();
        String projectId = CookieUtils.getCookie(request, ProductUtils.COOKIE_PRODUCT_ID);
        if (StringUtil.isBlank(projectId)) {
            return;
        }
        List<String> menuList = teamService.getMenuIdListByProductAndUser(Integer.valueOf(projectId), UserUtils.getUserId());
        MenuPermissionSubject subject = new MenuPermissionSubject();
        subject.setMenuList(menuList);
        interceptor(joinPoint, subject);
    }

    private void interceptor(JoinPoint joinPoint, MenuPermissionSubject subject) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        if (method != null) {
            RequiresPermissions annotation = method.getAnnotation(RequiresPermissions.class);
            if (annotation != null) {
                assertAuthorized(annotation, subject);
            }
        }
    }

    public void assertAuthorized(Annotation a, MenuPermissionSubject subject) throws AuthorizationException {
        if (!(a instanceof RequiresPermissions)) {
            return;
        }

        RequiresPermissions rpAnnotation = (RequiresPermissions) a;
        String[] perms = getAnnotationValue(a);

        if (perms.length == 1) {
            subject.checkPermission(perms[0]);
            return;
        }
        if (Logical.AND.equals(rpAnnotation.logical())) {
            subject.checkPermissions(perms);
            return;
        }
        if (Logical.OR.equals(rpAnnotation.logical())) {
            // Avoid processing exceptions unnecessarily - "delay" throwing the exception by calling hasRole first
            boolean hasAtLeastOnePermission = false;
            for (String permission : perms) {
                if (subject.isPermitted(permission)) hasAtLeastOnePermission = true;
            }
            // Cause the exception if none of the role match, note that the exception message will be a bit misleading
            if (!hasAtLeastOnePermission) {
                subject.checkPermission(perms[0]);
            }

        }
    }

    /**
     * Returns the annotation {@link RequiresPermissions#value value}, from which the Permission will be constructed.
     *
     * @param a the RequiresPermissions annotation being inspected.
     * @return the annotation's <code>value</code>, from which the Permission will be constructed.
     */
    protected String[] getAnnotationValue(Annotation a) {
        RequiresPermissions rpAnnotation = (RequiresPermissions) a;
        return rpAnnotation.value();
    }

}
