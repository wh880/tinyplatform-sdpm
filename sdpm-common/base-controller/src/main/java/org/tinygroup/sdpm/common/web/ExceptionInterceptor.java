package org.tinygroup.sdpm.common.web;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.tinygroup.logger.LogLevel;
import org.tinygroup.logger.Logger;
import org.tinygroup.logger.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Hulk on 16/4/20.
 */
public class ExceptionInterceptor implements HandlerInterceptor {
    /**
     * 日志对象
     */
    protected Logger logger = LoggerFactory.getLogger(ExceptionInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse,
                             Object o) throws Exception {
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
        if (e != null) {
            logger.logMessage(LogLevel.ERROR, "页面渲染出错", e);
            httpServletResponse.sendRedirect("/error");
        }
    }
}
