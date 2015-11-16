package org.tinygroup.sdpm.common.web;

import org.tinygroup.beancontainer.BeanContainer;
import org.tinygroup.beancontainer.BeanContainerFactory;
import org.tinygroup.weblayer.AbstractTinyFilter;
import org.tinygroup.weblayer.WebContext;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * 通用处理过滤器
 * Created by Hulk on 2015/9/6.
 */
public class CommonFilter extends AbstractTinyFilter {
    private BeanContainer springUtil = BeanContainerFactory.getBeanContainer(CommonFilter.class.getClassLoader());

    @Override
    protected void customInit() {
    }

    public void preProcess(WebContext webContext) throws ServletException, IOException {
        Object menuManager = springUtil.getBean("menuUtils");
        webContext.put("menuUtils", menuManager);
    }

    public void postProcess(WebContext webContext) throws ServletException, IOException {
    }
}
