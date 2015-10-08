package org.tinygroup.sdpm.common.filter;

import org.tinygroup.beancontainer.BeanContainer;
import org.tinygroup.beancontainer.BeanContainerFactory;
import org.tinygroup.sdpm.common.log.LogPrepareUtil;
import org.tinygroup.weblayer.AbstractTinyFilter;
import org.tinygroup.weblayer.WebContext;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Created by Hulk on 2015/9/6.
 */
public class CommonFilter extends AbstractTinyFilter {
    private BeanContainer springUtil = BeanContainerFactory.getBeanContainer(CommonFilter.class.getClassLoader());

    @Override
    protected void customInit() {
    }

    public void preProcess(WebContext webContext) throws ServletException, IOException {
        Object menuManager = springUtil.getBean("menuManager");
        webContext.put("menuManager", menuManager);
        LogPrepareUtil.setSession(webContext.getRequest().getSession());
    }

    public void postProcess(WebContext webContext) throws ServletException, IOException {

    }
}
