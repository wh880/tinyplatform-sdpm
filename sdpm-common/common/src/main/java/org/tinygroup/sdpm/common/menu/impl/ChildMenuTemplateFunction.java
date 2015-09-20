package org.tinygroup.sdpm.common.menu.impl;

import org.tinygroup.sdpm.common.menu.MenuManager;
import org.tinygroup.template.Template;
import org.tinygroup.template.TemplateContext;
import org.tinygroup.template.TemplateException;
import org.tinygroup.template.function.AbstractTemplateFunction;

/**
 * Created by Hulk on 2015/9/2.
 */
public class ChildMenuTemplateFunction extends AbstractTemplateFunction {

    private MenuManager menuManager;

    public ChildMenuTemplateFunction() {
        super("childMenus");
    }

    public Object execute(Template template, TemplateContext context, Object... parameters) throws TemplateException {
        if (parameters == null || parameters.length != 1
                || parameters[0] == null) {
            throw new TemplateException("childMenus函数必须输入1个父菜单Id");
        }
        String parentId = (String) parameters[0];
        return menuManager.getChildMenus(parentId);
    }

    public void setMenuManager(MenuManager menuManager) {
        this.menuManager = menuManager;
    }
}
